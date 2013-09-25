package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.dto.action.core.EditarMeuUsuarioAction;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;
import br.com.classmanager.server.domain.service.Servico;

@Named("EditarMeuUsuarioAction")
public class EditarMeuUsuarioService extends
		Servico<EditarMeuUsuarioAction, Usuario> {

	@Inject
	@DAO
	private IDaoUsuario daoUsuario;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario execute(EditarMeuUsuarioAction request)
			throws ClassManagerException {

		Usuario us = request.getUsuario();
		Usuario usuarioAlterado = daoUsuario.pesquisar(us.getId());

		usuarioAlterado.setNome(us.getNome());
		usuarioAlterado.setTelefone(us.getTelefone());
		usuarioAlterado.setCelular1(us.getCelular1());
		usuarioAlterado.setCelular2(us.getCelular2());

		usuarioAlterado.getServicosHabilitados().clear();
		if (CollectionUtils.isNotEmpty(us.getServicosHabilitados())) {
			for (ServicoEnvio servico : us.getServicosHabilitados()) {
				usuarioAlterado.getServicosHabilitados().add(servico);
			}
		}

		usuarioAlterado = daoUsuario.alterar(usuarioAlterado);

		return usuarioAlterado;
	}

}
