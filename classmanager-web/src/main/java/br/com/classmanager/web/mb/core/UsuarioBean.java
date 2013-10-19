package br.com.classmanager.web.mb.core;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.usuario.FotoUsuario;
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
	private String confirmaSenha;
	private StreamedContent foto;
	UploadedFile file;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Inject
	private SessionBean sessionBean;

	public UsuarioBean() {
	}

	public String inserir() {
		try {
			if (this.confirmaSenha != null && usuario.getSenha() != null
					&& this.confirmaSenha.equals(usuario.getSenha())) {
				ManterUsuarioAction action = new ManterUsuarioAction(
						AcaoManter.INSERIR);
				action.setEntidade(usuario);
				this.usuario = (Usuario) service.execute(action);
				this.usuario = new Usuario();
			} else {
				addErrorMessage(getMessage("Senha_Nao_Confere"));
				return null;
			}

		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}

	public String pesquisar() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.PESQUISAR_LISTA);
			action.setEmail(usuario.getEmail());
			action.setNome(usuario.getNome());
			action.setLogin(usuario.getLogin());
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
			usuario = new Usuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}

	public String alteraMeuUsuario() {
		try {
			if (this.confirmaSenha != null && usuario.getSenha() != null
					&& this.confirmaSenha.equals(usuario.getSenha())) {
				ManterUsuarioAction action = new ManterUsuarioAction(
						AcaoManter.ALTERAR);
				action.setEntidade(usuario);
				service.execute(action);

				sessionBean.setAtualizarUsuario(true);
				sessionBean.getUsuario();
			} else {
				addErrorMessage(getMessage("Senha_Nao_Confere"));
				return null;
			}
			this.usuario = new Usuario();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return pesquisar();
	}

	public String irParaTelaInsercao() {
		this.usuario = new Usuario();
		return "/pages/web/restrito/usuario/insere_usuario.jsf";
	}

	public String visualizaMeuUsuario() {
		return "/pages/web/restrito/usuario/visualiza_usuario.jsf";
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

	public void doUpload(FileUploadEvent e) {
		file = e.getFile();
		try {
			if (file != null) {
				byte[] foto = new byte[(int) file.getSize()];
				file.getInputstream().read(foto);

				if (getUsuario().getFotoUsuario() == null) {
					FotoUsuario fotoUsuario = new FotoUsuario();
					fotoUsuario.setFoto(foto);
					fotoUsuario.setUsuario(usuario);
					usuario.setFotoUsuario(fotoUsuario);
				} else {
					usuario.getFotoUsuario().setFoto(foto);
				}
			}
		} catch (Exception er) {
			System.out.println("Erro ao fazer upload do arquivo");
		}
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public StreamedContent getFoto() {
		if (usuario.getFotoUsuario() != null) {
			foto = new DefaultStreamedContent(new ByteArrayInputStream(usuario
					.getFotoUsuario().getFoto()));
		} else {
			foto = new DefaultStreamedContent();
		}
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
