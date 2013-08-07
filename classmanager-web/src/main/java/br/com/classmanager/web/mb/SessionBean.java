package br.com.classmanager.web.mb;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.web.mb.def.GenericManagedBean;

@Named
@SessionScoped
public class SessionBean extends GenericManagedBean {

	private static final long serialVersionUID = 2290694929905000248L;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
