package br.com.classmanager.web.mb.core;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.usuario.FotoUsuario;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class UsuarioBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private Usuario usuario = new Usuario();
	private List<Usuario> list;
	private String confirmaSenha;
	private UploadedFile file;
	@Inject
	@ServiceView
	
	private ClassManagerServiceView service;

	public UsuarioBean() {
	}

	public String inserir() {
		try {
			if (this.confirmaSenha!=null && usuario.getSenha()!=null && this.confirmaSenha.equals(usuario.getSenha())){
				try{					
					if (file!=null){
						byte[] foto = new byte[(int)file.getSize()];
						file.getInputstream().read(foto);
						FotoUsuario fotoUsuario= new FotoUsuario();
						fotoUsuario.setFoto(foto);
						usuario.setFotoUsuario(fotoUsuario);
					}
					
				}catch(Exception e){
					System.out.println("Erro ao fazer upload do arquivo");
				}
				ManterUsuarioAction action = new ManterUsuarioAction(
						AcaoManter.INSERIR);
				action.setEntidade(usuario);
				this.usuario = (Usuario) service.execute(action);
				this.usuario = new Usuario();  
			}else{
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("A senha não foi confirmada corretamente"));
				return null;
			}
				
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}
	
	public String pesquisar() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(AcaoManter.PESQUISAR_LISTA);
			action.setEmail(usuario.getEmail());
			action.setNome(usuario.getNome());
			action.setLogin(usuario.getLogin());
			this.list = ((ListaDTO) service.execute(action)).getLista();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return "/pages/web/restrito/usuario/pesquisa_usuario.jsf";
	}
	
	public String excluiMeuUsuario(){
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(AcaoManter.REMOVER);
			action.setEntidade(usuario);			
			service.execute(action);
			usuario = new Usuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		
		return pesquisar();	
	}
	
	public String alteraMeuUsuario(){
		try {
			if (this.confirmaSenha!=null && usuario.getSenha()!=null && this.confirmaSenha.equals(usuario.getSenha())){
				ManterUsuarioAction action = new ManterUsuarioAction(AcaoManter.ALTERAR);
				action.setEntidade(usuario);
				service.execute(action);
			}else{
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("A senha não foi confirmada corretamente"));
				return null;
			}
			this.usuario = new Usuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		
		return pesquisar();	
	}
	
	
	public String irParaTelaInsercao(){
		this.usuario = new Usuario();
		return "/pages/web/restrito/usuario/insere_usuario.jsf";
	}
	
	public String visualizaMeuUsuario(){
		return "/pages/web/restrito/usuario/visualiza_usuario.jsf";
	}
	
	
	public String retornarParaPesquisa(){
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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public void doUpload(FileUploadEvent e){
		this.file = e.getFile();
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}


}
