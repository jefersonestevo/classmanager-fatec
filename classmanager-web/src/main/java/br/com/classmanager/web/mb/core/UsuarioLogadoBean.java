package br.com.classmanager.web.mb.core;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
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
public class UsuarioLogadoBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private Usuario usuario = new Usuario();
	private List<Usuario> list;
	private StreamedContent foto;
	private UploadedFile file;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Inject
	private SessionBean sessionBean;

	@PostConstruct
	public void usuarioLogado() {
		pesquisarUsuario(sessionBean.getUsuario().getId());
	}

	public void pesquisarUsuario(long id) {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.PESQUISAR);
			action.setId(id);
			usuario = (Usuario) service.execute(action);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
	}

	public String alteraMeuUsuario() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.ALTERAR);
			action.setEntidade(usuario);
			service.execute(action);
			sessionBean.setAtualizarUsuario(true);
			sessionBean.getUsuario();
			addInfoMessage(getMessage("Usuario_Alterado_Sucesso"));
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
			return null;
		}
		return "/pages/web/restrito/usuario/altera_usuario_logado.xhtml";
	}

	public String irParaTelaHome() {
		return "/pages/web/restrito/main.jsf";
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
