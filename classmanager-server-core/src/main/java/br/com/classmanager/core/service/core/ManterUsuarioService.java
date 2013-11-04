package br.com.classmanager.core.service.core;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.enums.PerfilUsuario;
import br.com.classmanager.client.entidades.enums.StatusUsuario;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;
import br.com.classmanager.server.domain.service.impl.ServicoManterBase;

@Named("ManterUsuarioAction")
public class ManterUsuarioService extends
		ServicoManterBase<ManterUsuarioAction, Usuario, Long> {

	@Inject
	@DAO
	private IDaoUsuario dao;

	@Override
	protected IDaoUsuario getDao() {
		return dao;
	}

	@Override
	protected Usuario inserir(ManterUsuarioAction request)
			throws ClassManagerException {
		if (!validaLogin(request.getEntidade().getLogin())) {
			throw new ClassManagerException(CodigoExcecao.ERRO_LOGIN_DUPLICADO);
		}

		if (request.getEntidade() != null
				&& request.getEntidade().getFotoUsuario() != null
				&& request.getEntidade().getFotoUsuario().getFoto() == null) {
			request.getEntidade().setFotoUsuario(null);
		}
		if (request.getEntidade().getPerfilUsuario() == null) {
			request.getEntidade().setPerfilUsuario(PerfilUsuario.MEMBRO);
		}

		if (request.getEntidade().getStatusUsuario() == null) {
			request.getEntidade().setStatusUsuario(StatusUsuario.ATIVO);
		}

		getDao().inserir(request.getEntidade());
		return request.getEntidade();
	}

	@Override
	protected Usuario alterar(ManterUsuarioAction request)
			throws ClassManagerException {
		if (request.getEntidade() != null
				&& request.getEntidade().getFotoUsuario() != null
				&& request.getEntidade().getFotoUsuario().getFoto() == null) {
			request.getEntidade().setFotoUsuario(null);
		}
		return getDao().alterar(request.getEntidade());
	}

	@Override
	protected ListaDTO<Usuario> pesquisarLista(ManterUsuarioAction request)
			throws ClassManagerException {
		return new ListaDTO<Usuario>(getDao().pesquisarLista(request.getNome(),
				request.getLogin(), request.getEmail()));
	}

	public boolean validaLogin(String login) {
		try {
			if (getDao().pesquisarPorLogin(login) == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
