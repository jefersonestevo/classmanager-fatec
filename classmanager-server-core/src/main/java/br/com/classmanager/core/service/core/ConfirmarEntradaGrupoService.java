package br.com.classmanager.core.service.core;

import java.util.Date;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ConfirmarEntradaGrupoAction;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConfirmarEntradaGrupoAction")
public class ConfirmarEntradaGrupoService extends
		Servico<ConfirmarEntradaGrupoAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(ConfirmarEntradaGrupoAction request)
			throws ClassManagerException {
		UsuarioGrupo usuarioGrupo = daoUsuarioGrupo.pesquisar(request
				.getUsuarioGrupo().getId());
		usuarioGrupo.setStatus(StatusUsuarioGrupo.PARTICIPANTE);
		usuarioGrupo.setDataEfetivaEntradaGrupo(new Date());

		daoUsuarioGrupo.alterar(usuarioGrupo);
		return new NullDTO();
	}

}
