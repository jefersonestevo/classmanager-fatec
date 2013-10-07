package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AlterarSenhaAction;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;
import br.com.classmanager.server.domain.service.Servico;

@Named("AlterarSenhaAction")
public class AlterarSenhaService extends Servico<AlterarSenhaAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuario daoUsuario;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(AlterarSenhaAction request)
			throws ClassManagerException {

		if (!request.getSenhaNova().equals(request.getSenhaNovaConfirmacao())) {
			throw new ClassManagerException(
					CodigoExcecao.ALTERAR_SENHA_SENHAS_NOVAS_DIFERENTES);
		}

		Usuario usuario = daoUsuario.pesquisar(request.getUsuarioAtual()
				.getId());

		if (!usuario.getSenha().equals(request.getSenhaAtual())) {
			throw new ClassManagerException(
					CodigoExcecao.ALTERAR_SENHA_SENHA_INCORRETA);
		}

		usuario.setSenha(request.getSenhaNova());
		daoUsuario.alterar(usuario);

		return new NullDTO();
	}

}
