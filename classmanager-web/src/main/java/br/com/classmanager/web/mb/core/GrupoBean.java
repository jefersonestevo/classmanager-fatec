package br.com.classmanager.web.mb.core;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusGrupo;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class GrupoBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

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
			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.INSERIR);
			action.setEntidade(grupo);
			this.grupo = (Grupo) service.execute(action);
			this.grupo = new Grupo();  
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}
	
	public String pesquisar() {
		try {
			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.PESQUISAR_LISTA);
			action.setUsuarioAtual(sessionBean.getUsuario());
			this.list = ((ListaDTO) service.execute(action)).getLista();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return "/pages/web/restrito/grupo/pesquisa_grupo.jsf";
	}
	
	public String excluiMeuGrupo(){
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
	
	public String alteraMeuGrupo(){
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
	
	public void convidaUsuario(Usuario usuario){
		UsuarioGrupo usuario_x_grupo = new UsuarioGrupo();
		usuario_x_grupo.setUsuario(usuario);
		usuario_x_grupo.setGrupo(grupo);
		grupo.getUsuariosGrupo().add(usuario_x_grupo);
		for (UsuarioGrupo usuarioGrupo :grupo.getUsuariosGrupo()){
			System.out.print(usuarioGrupo.getUsuario().getLogin());
		}
	}
	
	public String irParaTelaInsercao(){
		this.grupo = new Grupo();
		return "/pages/web/restrito/grupo/insere_grupo.jsf";
	}	
	public String visualizaMeuGrupo(){
		return "/pages/web/restrito/grupo/visualiza_grupo.jsf";
	}
		
	public String retornarParaPesquisa(){
		this.grupo = new Grupo();
		return "/pages/web/restrito/grupo/pesquisa_grupo.jsf";
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
	
	public StatusGrupo[] getStatusGrupo(){
		return StatusGrupo.values();		
	}
}
