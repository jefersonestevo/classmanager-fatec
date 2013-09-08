package br.com.classmanager.core.service.core;

import java.util.Date;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AdicionaUsuarioGrupoAction;
import br.com.classmanager.client.dto.core.UsuarioGrupoStatus;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("AdicionaUsuarioGrupoAction")
public class AdicionaUsuarioGrupoService extends
		Servico<AdicionaUsuarioGrupoAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(AdicionaUsuarioGrupoAction request)
			throws ClassManagerException {

		for (UsuarioGrupoStatus insercao : request.getListaUsuarioInsercao()) {
			UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
			usuarioGrupo.setGrupo(request.getGrupo());
			usuarioGrupo.setDataSolicitacaoEntradaGrupo(new Date());
			usuarioGrupo.setUsuario(insercao.getUsuario());
			usuarioGrupo.setStatus(insercao.getStatus());
			usuarioGrupo.setPerfil(insercao.getPerfil());
			usuarioGrupo.setDataEfetivaEntradaGrupo(null);

			daoUsuarioGrupo.inserir(usuarioGrupo);
		}

		return new NullDTO();
	}

}
