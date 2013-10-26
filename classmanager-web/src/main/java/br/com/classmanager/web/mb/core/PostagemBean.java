package br.com.classmanager.web.mb.core;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AdicionaPostagemGrupoAction;
import br.com.classmanager.client.dto.action.core.ConsultarPostagemGrupoAction;
import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
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
	private SessionBean sessionBean;

	private Postagem postagem;
	private Grupo grupoAtual;
	private SortedSet<Postagem> listaPostagens = new TreeSet<Postagem>(
			new ComparadorPostagemPorDataGeracao());

	private Integer tipoPostagemSelecionada;
	private List<SelectItem> listaTiposPostagensHabilitadas;

	private List<String> tiposServicosEnvioSelecionados;
	private List<SelectItem> tiposServicosEnvioHabilitados;

	public PostagemBean() {
	}

	public void carregarPostagem(Long idGrupo) {
		try {
			limparCampos();
			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR);
			action.setId(idGrupo);
			this.grupoAtual = (Grupo) service.execute(action);

			this.preencherTiposPostagensHabilitadas(grupoAtual);
			this.preencherTiposServicosEnvioHabilitados(grupoAtual);

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

	public void adicionarPostagem() {
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
			this.listaPostagens.add(postagemInserida);
			this.limparCampos();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

	}

	private void preencherTiposPostagensHabilitadas(Grupo grupo) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (TipoPostagem tipoPostagem : grupo.getTiposPostagensHabilitados()) {
			lista.add(new SelectItem(tipoPostagem.getValor(),
					getMessage("Tipo_Postagem_" + tipoPostagem.getValor())));
		}
		this.listaTiposPostagensHabilitadas = lista;
	}

	private void preencherTiposServicosEnvioHabilitados(Grupo grupo) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (ServicoEnvio servicoEnvio : grupo.getServicosHabilitados()) {
			lista.add(new SelectItem(servicoEnvio.getId(), servicoEnvio
					.getNome()));
		}
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

}
