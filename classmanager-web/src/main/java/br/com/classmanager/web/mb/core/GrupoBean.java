package br.com.classmanager.web.mb.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.classmanager.client.dto.action.core.AdicionaUsuarioGrupoAction;
import br.com.classmanager.client.dto.action.core.AprovarParticipacaoGrupoAction;
import br.com.classmanager.client.dto.action.core.ExcluirUsuarioGrupoAction;
import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
import br.com.classmanager.client.dto.action.core.PesquisarServicoEnvioAction;
import br.com.classmanager.client.dto.action.core.SolicitaParticipacaoGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.PerfilUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.enums.AcaoPesquisar;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.utils.CMCollectionUtils;
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
	private String idPesquisa;
	private String tituloPesquisa;
	private Grupo grupo = new Grupo();
	private List<Grupo> list;
	private Long idUsuarioSelecionado;
	private Long idUsuarioGrupoSelecionado;

	private ConsultaUsuario consultaUsuario = new ConsultaUsuario();

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Inject
	private SessionBean sessionBean;

	public GrupoBean() {
	}

	public String inserir() {
		try {
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

			sessionBean.setAtualizarUsuario(true);
			sessionBean.getUsuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
			return null;
		}
		return visualizaMeuGrupo(grupo.getId());
	}

	public String pesquisar() {
		try {
			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR_LISTA);
			action.setTituloPesquisa(tituloPesquisa);

			Long id = null;
			if (idPesquisa != null && NumberUtils.isNumber(idPesquisa)
					&& !idPesquisa.contains(",") && !idPesquisa.contains(".")) {
				id = Long.parseLong(idPesquisa);
			}
			action.setIdPesquisa(id);
			this.list = ((ListaDTO<Grupo>) service.execute(action)).getLista();
			CMCollectionUtils.ordenarLista(this.list, new String[] { "titulo",
					"id" });
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
			return null;
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
			return null;
		}

		return pesquisar();
	}

	public String alteraMeuGrupo() {
		try {
			if (grupo.getServicosHabilitados() == null)
				grupo.setServicosHabilitados(new HashSet<ServicoEnvio>());
			grupo.getServicosHabilitados().clear();
			if (CollectionUtils.isNotEmpty(tiposPostagemSelecionados)) {
				for (String tipo : tiposPostagemSelecionados) {
					grupo.getTiposPostagensHabilitados().add(
							TipoPostagem.getTipoPostagem(tipo));
				}
			}

			if (grupo.getServicosHabilitados() == null)
				grupo.setServicosHabilitados(new HashSet<ServicoEnvio>());
			grupo.getServicosHabilitados().clear();
			if (CollectionUtils.isNotEmpty(tiposServicosEnvioSelecionados)) {
				for (String tipo : tiposServicosEnvioSelecionados) {
					grupo.getServicosHabilitados().add(
							new ServicoEnvio(Long.valueOf(tipo)));
				}
			}

			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.ALTERAR);
			action.setEntidade(grupo);
			service.execute(action);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
			return null;
		}

		return visualizaMeuGrupo(grupo.getId());
	}

	public void pesquisarUsuario() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.PESQUISAR_LISTA);
			action.setNome(consultaUsuario.getNome());
			action.setEmail(consultaUsuario.getEmail());
			action.setLogin(consultaUsuario.getLogin());
			List<Usuario> listaUsuario = ((ListaDTO) service.execute(action))
					.getLista();

			listaUsuario.remove(sessionBean.getUsuario());
			for (UsuarioGrupo usuarioGrupo : grupo.getUsuariosGrupo()) {
				listaUsuario.remove(usuarioGrupo.getUsuario());
			}

			this.consultaUsuario.setListaUsuario(listaUsuario);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void convidaUsuario() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.PESQUISAR);
			action.setId(idUsuarioSelecionado);
			Usuario usuario = (Usuario) service.execute(action);

			AdicionaUsuarioGrupoAction adiciona = new AdicionaUsuarioGrupoAction();
			adiciona.setUsuario(usuario);
			adiciona.setGrupo(grupo);

			UsuarioGrupo usuarioAdicionado = (UsuarioGrupo) service
					.execute(adiciona);
			grupo.getUsuariosGrupo().add(usuarioAdicionado);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
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
			this.consultaUsuario.clean();
			ManterGrupoAction action = new ManterGrupoAction(
					AcaoManter.PESQUISAR);
			action.setId(id);
			this.grupo = (Grupo) service.execute(action);

			this.tiposServicosEnvioSelecionados = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(this.grupo.getServicosHabilitados())) {
				for (ServicoEnvio servico : this.grupo.getServicosHabilitados()) {
					this.tiposServicosEnvioSelecionados.add(servico.getId()
							.toString());
				}
			}

			this.tiposPostagemSelecionados = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(this.grupo
					.getTiposPostagensHabilitados())) {
				for (TipoPostagem tipo : this.grupo
						.getTiposPostagensHabilitados()) {
					this.tiposPostagemSelecionados.add(Integer.toString(tipo
							.ordinal()));
				}
			}

			CMCollectionUtils.ordenarLista(this.grupo.getUsuariosGrupo(),
					new String[] { "grupo.titulo", "grupo.id" });
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
			return null;
		}
		return "/pages/web/restrito/grupo/visualiza_grupo.jsf";
	}

	public String retornarParaPesquisa() {
		this.grupo = new Grupo();
		return pesquisar();
	}

	public String retornarParaVisualizacaoGrupo() {
		return visualizaMeuGrupo(this.grupo.getId());
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
			CMCollectionUtils.ordenarLista(listaServicoEnvio,
					new String[] { "nome" });

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

	public static class ConsultaUsuario implements Serializable {

		private static final long serialVersionUID = -9067037336021699907L;

		private String nome;
		private String login;
		private String email;
		private List<Usuario> listaUsuario = new ArrayList<Usuario>();

		private void clean() {
			this.nome = null;
			this.login = null;
			this.email = null;
			this.listaUsuario = new ArrayList<Usuario>();
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Usuario> getListaUsuario() {
			CMCollectionUtils.ordenarLista(this.listaUsuario, new String[] {
					"nome", "login" });
			return listaUsuario;
		}

		public void setListaUsuario(List<Usuario> listaUsuario) {
			this.listaUsuario = listaUsuario;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

	}

	public ConsultaUsuario getConsultaUsuario() {
		return consultaUsuario;
	}

	public void setConsultaUsuario(ConsultaUsuario consultaUsuario) {
		this.consultaUsuario = consultaUsuario;
	}

	public Long getIdUsuarioSelecionado() {
		return idUsuarioSelecionado;
	}

	public void setIdUsuarioSelecionado(Long idUsuarioSelecionado) {
		this.idUsuarioSelecionado = idUsuarioSelecionado;
	}

	public boolean isUsuarioAtualAdministradorGrupo() {
		if (grupo != null
				&& CollectionUtils.isNotEmpty(grupo.getUsuariosGrupo())) {
			for (UsuarioGrupo usuarioGrupo : grupo.getUsuariosGrupo()) {
				if (sessionBean.getUsuario().equals(usuarioGrupo.getUsuario())) {
					if (StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo
							.getStatus())
							|| PerfilUsuarioGrupo.ADM.equals(usuarioGrupo
									.getPerfil())) {
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	public boolean isUsuarioAtualMembroGrupo() {
		if (grupo != null
				&& CollectionUtils.isNotEmpty(grupo.getUsuariosGrupo())) {
			for (UsuarioGrupo usuarioGrupo : grupo.getUsuariosGrupo()) {
				if (sessionBean.getUsuario().equals(usuarioGrupo.getUsuario())) {
					if (StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo
							.getStatus())
							|| PerfilUsuarioGrupo.ADM.equals(usuarioGrupo
									.getPerfil())
							|| PerfilUsuarioGrupo.MEMBRO.equals(usuarioGrupo
									.getPerfil())) {
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	public void solicitarParticipacaoGrupo() {
		try {
			SolicitaParticipacaoGrupoAction action = new SolicitaParticipacaoGrupoAction();
			action.setUsuario(sessionBean.getUsuario());
			action.setGrupo(grupo);
			UsuarioGrupo usuarioAdicionado = (UsuarioGrupo) service
					.execute(action);

			grupo.getUsuariosGrupo().add(usuarioAdicionado);
			sessionBean.setAtualizarUsuario(true);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void sairGrupo() {
		try {
			UsuarioGrupo usuarioGrupo = null;
			if (grupo != null
					&& CollectionUtils.isNotEmpty(grupo.getUsuariosGrupo())) {
				for (UsuarioGrupo usrGrupo : grupo.getUsuariosGrupo()) {
					if (sessionBean.getUsuario().equals(usrGrupo.getUsuario())) {
						usuarioGrupo = usrGrupo;
						break;
					}
				}
			}

			ExcluirUsuarioGrupoAction action = new ExcluirUsuarioGrupoAction();
			action.setUsuarioGrupo(usuarioGrupo);
			service.execute(action);
			grupo.getUsuariosGrupo().remove(usuarioGrupo);

			sessionBean.setAtualizarUsuario(true);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void removerUsuarioGrupo() {
		try {
			UsuarioGrupo usuarioGrupo = null;
			if (grupo != null
					&& CollectionUtils.isNotEmpty(grupo.getUsuariosGrupo())) {
				for (UsuarioGrupo usrGrupo : grupo.getUsuariosGrupo()) {
					if (idUsuarioGrupoSelecionado.equals(usrGrupo.getId())) {
						usuarioGrupo = usrGrupo;
						break;
					}
				}
			}

			ExcluirUsuarioGrupoAction action = new ExcluirUsuarioGrupoAction();
			action.setUsuarioGrupo(usuarioGrupo);
			service.execute(action);
			grupo.getUsuariosGrupo().remove(usuarioGrupo);

			sessionBean.setAtualizarUsuario(true);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public void confirmarEntradaUsuarioGrupo() {
		try {
			AprovarParticipacaoGrupoAction action = new AprovarParticipacaoGrupoAction();
			action.setIdUsuarioGrupo(idUsuarioGrupoSelecionado);
			UsuarioGrupo usuarioAlterado = (UsuarioGrupo) service
					.execute(action);
			grupo.getUsuariosGrupo().remove(usuarioAlterado);
			grupo.getUsuariosGrupo().add(usuarioAlterado);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public String getIdPesquisa() {
		return idPesquisa;
	}

	public void setIdPesquisa(String idPesquisa) {
		this.idPesquisa = idPesquisa;
	}

	public Long getIdUsuarioGrupoSelecionado() {
		return idUsuarioGrupoSelecionado;
	}

	public void setIdUsuarioGrupoSelecionado(Long idUsuarioGrupoSelecionado) {
		this.idUsuarioGrupoSelecionado = idUsuarioGrupoSelecionado;
	}

}
