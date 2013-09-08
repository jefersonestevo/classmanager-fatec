package br.com.classmanager.server.domain.service;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOManterAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;

public abstract class ServicoManter<REQ extends DTOManterAction, RESP extends DTO>
		implements IService<REQ, DTO> {

	@Override
	public DTO execute(REQ request) throws ClassManagerException {
		if (request.getAcao() != null) {
			switch (request.getAcao()) {
			case INSERIR:
				return inserir(request);
			case ALTERAR:
				return alterar(request);
			case REMOVER:
				remover(request);
				return null;
			case PESQUISAR:
				return pesquisar(request);
			case PESQUISAR_LISTA:
				return pesquisarLista(request);
			}
		}
		return null;
	}

	public boolean hasPermissao(AcaoManter acao, Usuario usuario)
			throws ClassManagerSecurityException {
		if (acao != null) {
			switch (acao) {
			case INSERIR:
				return hasPermissaoInserir(usuario);
			case ALTERAR:
				return hasPermissaoAlterar(usuario);
			case REMOVER:
				return hasPermissaoRemover(usuario);
			case PESQUISAR:
				return hasPermissaoPesquisar(usuario);
			case PESQUISAR_LISTA:
				return hasPermissaoPesquisarLista(usuario);
			}
		}
		return false;
	}

	protected abstract RESP inserir(REQ request) throws ClassManagerException;

	protected abstract RESP alterar(REQ request) throws ClassManagerException;

	protected abstract void remover(REQ request) throws ClassManagerException;

	protected abstract RESP pesquisar(REQ request) throws ClassManagerException;

	protected abstract ListaDTO<RESP> pesquisarLista(REQ request)
			throws ClassManagerException;

	protected abstract boolean hasPermissaoInserir(Usuario usuario)
			throws ClassManagerSecurityException;

	protected abstract boolean hasPermissaoAlterar(Usuario usuario)
			throws ClassManagerSecurityException;

	protected abstract boolean hasPermissaoRemover(Usuario usuario)
			throws ClassManagerSecurityException;

	protected abstract boolean hasPermissaoPesquisar(Usuario usuario)
			throws ClassManagerSecurityException;

	protected abstract boolean hasPermissaoPesquisarLista(Usuario usuario)
			throws ClassManagerSecurityException;

}
