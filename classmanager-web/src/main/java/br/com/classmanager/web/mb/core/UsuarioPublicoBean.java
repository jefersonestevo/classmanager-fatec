package br.com.classmanager.web.mb.core;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class UsuarioPublicoBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private Usuario usuario = new Usuario();
	private String confirmaSenha;
	private StreamedContent foto;
	private UploadedFile file;
		
	@Inject
	@ServiceView
	private ClassManagerServiceView service;
	
	public UsuarioPublicoBean() {
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

		return "login";
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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