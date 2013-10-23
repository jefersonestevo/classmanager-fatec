package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ExcluirUsuarioGrupoAction;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("ExcluirUsuarioGrupoAction")
public class ExcluirUsuarioGrupoService extends
		Servico<ExcluirUsuarioGrupoAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(ExcluirUsuarioGrupoAction request)
			throws ClassManagerException {
		UsuarioGrupo usuarioGrupo = daoUsuarioGrupo.pesquisar(request
				.getUsuarioGrupo().getId());
		if (StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo.getStatus())) {
			throw new ClassManagerException(
					CodigoExcecao.ALTERAR_SENHA_SENHA_INCORRETA);
		}
		daoUsuarioGrupo.remover(usuarioGrupo);
		return new NullDTO();
	}
}
