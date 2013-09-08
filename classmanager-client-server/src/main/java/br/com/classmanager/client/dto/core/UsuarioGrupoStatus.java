package br.com.classmanager.client.dto.core;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.entidades.enums.PerfilUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.entidades.usuario.Usuario;

public class UsuarioGrupoStatus implements DTO {

	private static final long serialVersionUID = 4691117762828370228L;

	private Long idUsuarioGrupo;
	private Usuario usuario;
	private StatusUsuarioGrupo status;
	private PerfilUsuarioGrupo perfil;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusUsuarioGrupo getStatus() {
		return status;
	}

	public void setStatus(StatusUsuarioGrupo status) {
		this.status = status;
	}

	public PerfilUsuarioGrupo getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioGrupo perfil) {
		this.perfil = perfil;
	}

	public Long getIdUsuarioGrupo() {
		return idUsuarioGrupo;
	}

	public void setIdUsuarioGrupo(Long idUsuarioGrupo) {
		this.idUsuarioGrupo = idUsuarioGrupo;
	}

}
