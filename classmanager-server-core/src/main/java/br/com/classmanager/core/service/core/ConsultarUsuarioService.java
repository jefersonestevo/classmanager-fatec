package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.dto.action.core.ConsultarUsuarioAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultarUsuarioAction")
public class ConsultarUsuarioService extends
		Servico<ConsultarUsuarioAction, Usuario> {

	@Inject
	@DAO
	private IDaoUsuario daoUsuario;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario execute(ConsultarUsuarioAction request)
			throws ClassManagerException {
		Usuario usr = null;
		if (request.getId() != null) {
			usr = daoUsuario.pesquisar(request.getId());
		} else if (StringUtils.isNotEmpty(request.getLogin())) {
			usr = daoUsuario.pesquisarPorLogin(request.getLogin());
		}
		return usr;
	}

}
