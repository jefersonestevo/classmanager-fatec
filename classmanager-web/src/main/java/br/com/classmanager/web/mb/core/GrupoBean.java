package br.com.classmanager.web.mb.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.dto.action.core.PesquisarServicoEnvioAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusGrupo;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.enums.AcaoPesquisar;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class GrupoBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private List<String> tiposPostagemSelecionados = new ArrayList<String>();
	private List<String> tiposServicosEnvioSelecionados = new ArrayList<String>();
	private String tituloPesquisa;
	private Grupo grupo = new Grupo();
	private List<Grupo> list;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Inject
	private SessionBean sessionBean;

	public GrupoBean() {
	}

	public String inserir() {
		try {
			grupo.setDataCriacao(new Date());
			grupo.setUsuarioCriador(sessionBean.getUsuario());
			grupo.setStatus(StatusGrupo.ATIVO);

			if (CollectionUtils.isNotEmpty(tiposPostagemSelecionados)) {
				for (String tipo : tiposPostagemSelecionados) {
					grupo.getTiposPostagensHabilitados().add(
							TipoPostagem.getTipoPostagem(tipo));
				}
			}

			if (CollectionUtils.isNotEmpty(tiposServicosEnvioSelecionados)) {
				for (String tipo : tiposServicosEnvioSelecionados) {
					grupo.getServicosHabilitados().add(
							new ServicoEnvio(Long.valueOf(tipo)));
				}
			}

			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.INSERIR);
			action.setEntidade(grupo);
			this.grupo = (Grupo) service.execute(action);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return visualizaMeuGrupo(grupo.getId());
	}

	public String pesquisar() {
		try {
			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR_LISTA);
			action.setTituloPesquisa(tituloPesquisa);
			this.list = ((ListaDTO<Grupo>) service.execute(action)).getLista();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return "/pages/web/restrito/grupo/pesquisa_grupo.jsf";
	}

	public String excluiMeuGrupo() {
		try {
			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.REMOVER);
			action.setEntidade(grupo);
			service.execute(action);
			grupo = new Grupo();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}

	public String alteraMeuGrupo() {
		try {
			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.ALTERAR);
			action.setEntidade(grupo);
			service.execute(action);
			this.grupo = new Grupo();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}

	public void convidaUsuario(Usuario usuario) {
		UsuarioGrupo usuario_x_grupo = new UsuarioGrupo();
		usuario_x_grupo.setUsuario(usuario);
		usuario_x_grupo.setGrupo(grupo);
		grupo.getUsuariosGrupo().add(usuario_x_grupo);
		for (UsuarioGrupo usuarioGrupo : grupo.getUsuariosGrupo()) {
			System.out.print(usuarioGrupo.getUsuario().getLogin());
		}
	}

	public String irParaTelaInsercao() {
		this.grupo = new Grupo();
		this.tiposPostagemSelecionados = new ArrayList<String>();
		this.tiposServicosEnvioSelecionados = new ArrayList<String>();
		return "/pages/web/restrito/grupo/insere_grupo.jsf";
	}

	public String visualizaMeuGrupo() {
		Long id = getLongParameter("id");
		return visualizaMeuGrupo(id);
	}

	public String visualizaMeuGrupo(Long id) {
		try {
			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR);
			action.setId(id);
			this.grupo = (Grupo) service.execute(action);

			if (CollectionUtils.isNotEmpty(this.grupo.getServicosHabilitados())) {
				this.grupo.getServicosHabilitados().get(0).getId();
			}
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return "/pages/web/restrito/grupo/visualiza_grupo.jsf";
	}

	public String retornarParaPesquisa() {
		this.grupo = new Grupo();
		return "/pages/web/restrito/grupo/pesquisa_grupo.jsf";
	}

	public List<SelectItem> getTiposPostagem() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (Integer tipo : TipoPostagem.getValores()) {
			lista.add(new SelectItem(tipo, getMessage("Tipo_Postagem_" + tipo)));
		}
		return lista;
	}

	public List<SelectItem> getTiposServicosEnvio() {
		try {
			PesquisarServicoEnvioAction action = new PesquisarServicoEnvioAction(
					AcaoPesquisar.PESQUISAR_LISTA);
			List<ServicoEnvio> listaServicoEnvio = ((ListaDTO) service
					.execute(action)).getLista();

			List<SelectItem> lista = new ArrayList<SelectItem>();
			for (ServicoEnvio serv : listaServicoEnvio) {
				lista.add(new SelectItem(serv.getId(), serv.getNome()));
			}
			return lista;
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return new ArrayList<SelectItem>();
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getList() {
		return list;
	}

	public void setList(List<Grupo> list) {
		this.list = list;
	}

	public StatusGrupo[] getStatusGrupo() {
		return StatusGrupo.values();
	}

	public String getTituloPesquisa() {
		return tituloPesquisa;
	}

	public void setTituloPesquisa(String tituloPesquisa) {
		this.tituloPesquisa = tituloPesquisa;
	}

	public List<String> getTiposPostagemSelecionados() {
		return tiposPostagemSelecionados;
	}

	public void setTiposPostagemSelecionados(
			List<String> tiposPostagemSelecionados) {
		this.tiposPostagemSelecionados = tiposPostagemSelecionados;
	}

	public List<String> getTiposServicosEnvioSelecionados() {
		return tiposServicosEnvioSelecionados;
	}

	public void setTiposServicosEnvioSelecionados(
			List<String> tiposServicosEnvioSelecionados) {
		this.tiposServicosEnvioSelecionados = tiposServicosEnvioSelecionados;
	}
}
