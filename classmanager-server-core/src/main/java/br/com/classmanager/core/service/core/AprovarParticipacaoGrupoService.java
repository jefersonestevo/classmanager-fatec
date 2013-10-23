package br.com.classmanager.core.service.core;

import java.util.Date;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AprovarParticipacaoGrupoAction;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("AprovarParticipacaoGrupoAction")
public class AprovarParticipacaoGrupoService extends
		Servico<AprovarParticipacaoGrupoAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(AprovarParticipacaoGrupoAction request)
			throws ClassManagerException {
		UsuarioGrupo usuarioGrupo = request.getUsuarioGrupo();
		usuarioGrupo.setDataEfetivaEntradaGrupo(new Date());
		usuarioGrupo.setStatus(StatusUsuarioGrupo.PARTICIPANTE);
		daoUsuarioGrupo.alterar(usuarioGrupo);
		return new NullDTO();
	}

}
