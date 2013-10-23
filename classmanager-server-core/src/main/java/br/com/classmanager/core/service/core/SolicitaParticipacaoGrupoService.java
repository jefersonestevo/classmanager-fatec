package br.com.classmanager.core.service.core;

import java.util.Date;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.SolicitaParticipacaoGrupoAction;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.PerfilUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("SolicitaParticipacaoGrupoAction")
public class SolicitaParticipacaoGrupoService extends
		Servico<SolicitaParticipacaoGrupoAction, UsuarioGrupo> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioGrupo execute(SolicitaParticipacaoGrupoAction request)
			throws ClassManagerException {

		UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
		usuarioGrupo.setDataSolicitacaoEntradaGrupo(new Date());
		usuarioGrupo.setGrupo(request.getGrupo());
		usuarioGrupo.setUsuario(request.getUsuario());
		usuarioGrupo.setStatus(StatusUsuarioGrupo.SOLICITANDO_PARTICIPACAO);
		usuarioGrupo.setPerfil(PerfilUsuarioGrupo.MEMBRO);
		daoUsuarioGrupo.inserir(usuarioGrupo);

		return usuarioGrupo;
	}

}
