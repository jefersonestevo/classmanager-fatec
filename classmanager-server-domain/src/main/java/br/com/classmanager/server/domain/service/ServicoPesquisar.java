package br.com.classmanager.server.domain.service;

import br.com.classmanager.client.dto.core.ListaDTO;
import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOPesquisarAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoPesquisar;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;

public abstract class ServicoPesquisar<REQ extends DTOPesquisarAction, RESP extends DTO>
		implements IService<REQ, DTO> {

	@Override
	public DTO execute(REQ request) throws ClassManagerException {
		if (request.getAcao() != null) {
			switch (request.getAcao()) {
			case PESQUISAR:
				return pesquisar(request);
			case PESQUISAR_LISTA:
				return pesquisarLista(request);
			}
		}
		return null;
	}

	public boolean hasPermissao(AcaoPesquisar acao, Usuario usuario)
			throws ClassManagerSecurityException {
		if (acao != null) {
			switch (acao) {
			case PESQUISAR:
				return hasPermissaoPesquisar(usuario);
			case PESQUISAR_LISTA:
				return hasPermissaoPesquisarLista(usuario);
			}
		}
		return false;
	}

	protected abstract RESP pesquisar(REQ request) throws ClassManagerException;

	protected abstract ListaDTO<RESP> pesquisarLista(REQ request)
			throws ClassManagerException;

	protected abstract boolean hasPermissaoPesquisar(Usuario usuario)
			throws ClassManagerSecurityException;

	protected abstract boolean hasPermissaoPesquisarLista(Usuario usuario)
			throws ClassManagerSecurityException;

}
