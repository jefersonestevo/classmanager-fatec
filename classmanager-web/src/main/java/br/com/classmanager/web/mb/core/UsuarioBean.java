package br.com.classmanager.web.mb.core;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class UsuarioBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private Usuario usuario = new Usuario();
	private List<Usuario> list;
	private String nome;
	private String login;
	private String email;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Inject
	private SessionBean sessionBean;

	public UsuarioBean() {
	}

	public String pesquisar() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.PESQUISAR_LISTA);
			action.setEmail(getEmail());
			action.setNome(getNome());
			action.setLogin(getLogin());
			this.list = ((ListaDTO) service.execute(action)).getLista();
			usuario = new Usuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return "/pages/web/restrito/usuario/pesquisa_usuario.jsf";
	}

	public String excluiMeuUsuario() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.REMOVER);
			action.setEntidade(usuario);
			service.execute(action);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}

	public String alteraMeuUsuario() {
		try {
				ManterUsuarioAction action = new ManterUsuarioAction(AcaoManter.ALTERAR);
				action.setEntidade(usuario);
				service.execute(action);
				sessionBean.setAtualizarUsuario(true);
				sessionBean.getUsuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		addInfoMessage(getMessage("Usuario_Alterado_Sucesso"));
		return pesquisar();
	}
	
	public String reiniciaSenha(){
		usuario.setSenha("1234");
		alteraMeuUsuario();
		addInfoMessage(getMessage("Senha_Reiniciada_Sucesso"));
		return "/pages/web/restrito/usuario/pesquisa_usuario.jsf";
	}

	public String irParaTelaAlteracao() {
		return "/pages/web/restrito/usuario/altera_usuario.jsf";
	}	

	public String retornarParaPesquisa() {
		this.usuario = new Usuario();
		return "/pages/web/restrito/usuario/pesquisa_usuario.jsf";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getList() {
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
