package br.com.classmanager.web.mb.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.dto.action.core.AdicionaComentarioPostagemAction;
import br.com.classmanager.client.dto.action.core.AdicionaPostagemGrupoAction;
import br.com.classmanager.client.dto.action.core.ConsultarPostagemGrupoAction;
import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.ComentarioPostagem;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.MiniCurriculo;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.utils.CMCollectionUtils;
import br.com.classmanager.web.componentes.comparators.ComparadorComentarioPostagemPorDataGeracao;
import br.com.classmanager.web.componentes.comparators.ComparadorPostagemPorDataGeracao;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class PostagemBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459521111111135L;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Inject
	private GrupoBean grupoBean;

	@Inject
	private SessionBean sessionBean;

	private Postagem postagemAtual;
	private Grupo grupoAtual;

	private Postagem postagem;
	private SortedSet<Postagem> listaPostagens = new TreeSet<Postagem>(
			new ComparadorPostagemPorDataGeracao());

	private String comentarioPostagem;
	private MiniCurriculo miniCurriculo;

	private Integer tipoPostagemSelecionada;
	private List<SelectItem> listaTiposPostagensHabilitadas;

	private List<String> tiposServicosEnvioSelecionados;
	private List<SelectItem> tiposServicosEnvioHabilitados;

	public PostagemBean() {
	}

	public void carregarListaPostagem(Long idGrupo) {
		try {
			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR);
			action.setId(idGrupo);
			this.grupoAtual = (Grupo) service.execute(action);

			ConsultarPostagemGrupoAction consultaPostagem = new ConsultarPostagemGrupoAction();
			consultaPostagem.setIdGrupo(idGrupo);
			List<Postagem> listaPost = ((ListaDTO<Postagem>) service
					.execute(consultaPostagem)).getLista();

			this.listaPostagens.clear();
			this.listaPostagens.addAll(listaPost);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void carregarTelaInserirPostagem(Long idGrupo) {
		try {
			limparCampos();

			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR);
			action.setId(idGrupo);
			this.grupoAtual = (Grupo) service.execute(action);

			this.preencherTiposPostagensHabilitadas(grupoAtual);
			this.preencherTiposServicosEnvioHabilitados(grupoAtual);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void alterarTipoPostagem(ValueChangeEvent event) {
		TipoPostagem anterior = TipoPostagem
				.getTipoPostagem(this.tipoPostagemSelecionada);
		TipoPostagem sel = TipoPostagem.getTipoPostagem((Integer) event
				.getNewValue());

		if (!TipoPostagem.VAGA.equals(sel)) {
			if (!sel.equals(anterior)) {
				this.postagem.setCargo(null);
				this.postagem.setEndereco(null);
				this.postagem.setDataInicio(null);
				this.postagem.setContato(null);
			}
		}
		this.tipoPostagemSelecionada = sel.getValor();
		this.postagem.setTipoPostagem(sel);
	}

	public String adicionarPostagem() {
		try {
			List<Long> servicosEnvio = new ArrayList<Long>();
			for (String serv : tiposServicosEnvioSelecionados) {
				servicosEnvio.add(Long.parseLong(serv));
			}

			AdicionaPostagemGrupoAction action = new AdicionaPostagemGrupoAction();
			action.setGrupo(grupoAtual);
			action.setPostagem(postagem);
			action.setServicosEnvio(servicosEnvio);
			Postagem postagemInserida = (Postagem) service.execute(action);
			this.limparCampos();

			return this.irParaTelaVisualizarPostagemGrupo(postagemInserida
					.getId());
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return null;
	}

	public void adicionarComentarioPostagem() {
		try {
			AdicionaComentarioPostagemAction action = new AdicionaComentarioPostagemAction();
			action.setComentarioPostagem(this.comentarioPostagem);
			action.setPostagem(this.postagemAtual);
			action.setMiniCurriculo(null);
			service.execute(action);

			Long idPostagem = this.postagemAtual.getId();
			this.preenchePostagemAtual(idPostagem);
			this.comentarioPostagem = null;
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void adicionarComentarioPostagemComCurriculo() {
		// try {
		// AdicionaComentarioPostagemAction action = new
		// AdicionaComentarioPostagemAction();
		// action.setPostagem(this.postagemAtual);
		// action.setMiniCurriculo(this.miniCurriculo);
		// action.setComentarioPostagem(getMessage("Message_Padrao_Envio_Mini_Curriculo_Vaga"));
		// service.execute(action);
		//
		// Long idPostagem = this.postagemAtual.getId();
		// this.preenchePostagemAtual(idPostagem);
		// this.comentarioPostagem = null;
		// } catch (ClassManagerException e) {
		// addExceptionMessage(e);
		// }
	}

	public String irParaTelaVisualizarPostagemGrupo(Long idPostagem) {
		try {
			this.preenchePostagemAtual(idPostagem);
			this.comentarioPostagem = null;
			this.miniCurriculo = new MiniCurriculo();
			this.grupoBean.visualizaMeuGrupo(this.grupoAtual.getId());

			for (ComentarioPostagem coment : this.postagemAtual
					.getListaComentarios()) {
				if (coment != null) {
					// Forçar a inicializacao dos comentários
					coment.getDescricao();
				}
			}

		} catch (ClassManagerException e) {
			addExceptionMessage(e);
			return null;
		}

		return "/pages/web/restrito/grupo/visualiza_postagem_grupo.jsf";
	}

	private void preenchePostagemAtual(Long idPostagem)
			throws ClassManagerException {
		ConsultarPostagemGrupoAction consultaPostagem = new ConsultarPostagemGrupoAction();
		consultaPostagem.setIdGrupo(this.grupoAtual.getId());
		List<Postagem> listaPost = ((ListaDTO<Postagem>) service
				.execute(consultaPostagem)).getLista();

		this.postagemAtual = null;
		for (Postagem post : listaPost) {
			if (post.getId().equals(idPostagem)) {
				this.postagemAtual = post;
				break;
			}
		}

		if (this.postagemAtual != null
				&& CollectionUtils.isNotEmpty(this.postagemAtual
						.getListaComentarios())) {
			Collections.sort(this.postagemAtual.getListaComentarios(),
					new ComparadorComentarioPostagemPorDataGeracao());
		}
	}

	private void preencherTiposPostagensHabilitadas(Grupo grupo) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (TipoPostagem tipoPostagem : grupo.getTiposPostagensHabilitados()) {
			lista.add(new SelectItem(tipoPostagem.getValor(),
					getMessage("Tipo_Postagem_" + tipoPostagem.getValor())));
		}
		CMCollectionUtils.ordenarLista(lista, new String[] { "value" });
		this.listaTiposPostagensHabilitadas = lista;

	}

	private void preencherTiposServicosEnvioHabilitados(Grupo grupo) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (ServicoEnvio servicoEnvio : grupo.getServicosHabilitados()) {
			lista.add(new SelectItem(servicoEnvio.getId(), servicoEnvio
					.getNome()));
		}
		CMCollectionUtils.ordenarLista(lista, new String[] { "value" });
		this.tiposServicosEnvioHabilitados = lista;
	}

	private void limparCampos() {
		this.tipoPostagemSelecionada = null;
		this.tiposServicosEnvioSelecionados = new ArrayList<String>();
		this.postagem = new Postagem(null);
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Grupo getGrupoAtual() {
		return grupoAtual;
	}

	public void setGrupoAtual(Grupo grupoAtual) {
		this.grupoAtual = grupoAtual;
	}

	public SortedSet<Postagem> getListaPostagens() {
		return listaPostagens;
	}

	public List<Postagem> getListaPostagensExibicao() {
		return new ArrayList<Postagem>(listaPostagens);
	}

	public void setListaPostagens(SortedSet<Postagem> listaPostagens) {
		this.listaPostagens = listaPostagens;
	}

	public Integer getTipoPostagemSelecionada() {
		return tipoPostagemSelecionada;
	}

	public void setTipoPostagemSelecionada(Integer tipoPostagemSelecionada) {
		this.tipoPostagemSelecionada = tipoPostagemSelecionada;
	}

	public List<SelectItem> getListaTiposPostagensHabilitadas() {
		return listaTiposPostagensHabilitadas;
	}

	public void setListaTiposPostagensHabilitadas(
			List<SelectItem> listaTiposPostagensHabilitadas) {
		this.listaTiposPostagensHabilitadas = listaTiposPostagensHabilitadas;
	}

	public List<String> getTiposServicosEnvioSelecionados() {
		return tiposServicosEnvioSelecionados;
	}

	public void setTiposServicosEnvioSelecionados(
			List<String> tiposServicosEnvioSelecionados) {
		this.tiposServicosEnvioSelecionados = tiposServicosEnvioSelecionados;
	}

	public List<SelectItem> getTiposServicosEnvioHabilitados() {
		return tiposServicosEnvioHabilitados;
	}

	public void setTiposServicosEnvioHabilitados(
			List<SelectItem> tiposServicosEnvioHabilitados) {
		this.tiposServicosEnvioHabilitados = tiposServicosEnvioHabilitados;
	}

	public Postagem getPostagemAtual() {
		return postagemAtual;
	}

	public void setPostagemAtual(Postagem postagemAtual) {
		this.postagemAtual = postagemAtual;
	}

	public String getComentarioPostagem() {
		return comentarioPostagem;
	}

	public void setComentarioPostagem(String comentarioPostagem) {
		this.comentarioPostagem = comentarioPostagem;
	}

	public MiniCurriculo getMiniCurriculo() {
		return miniCurriculo;
	}

	public void setMiniCurriculo(MiniCurriculo miniCurriculo) {
		this.miniCurriculo = miniCurriculo;
	}

}
