package br.com.classmanager.web.mb.core;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.EditarMeuUsuarioAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
public class ExemploBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;
	
	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	public String alterarMeuUsuario() {
		try {
			EditarMeuUsuarioAction action = new EditarMeuUsuarioAction();
			action.setUsuario(new Usuario());
			Usuario usuario = (Usuario) service.execute(action);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return "/pages/teste.jsf";
	}	
}
