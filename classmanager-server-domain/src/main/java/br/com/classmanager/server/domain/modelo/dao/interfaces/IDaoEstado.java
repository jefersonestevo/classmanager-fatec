package br.com.classmanager.server.domain.modelo.dao.interfaces;

import java.util.List;

import br.com.classmanager.client.entidades.endereco.Estado;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoPesquisa;

public interface IDaoEstado extends IDaoPesquisa<Estado, Long> {

	public List<Estado> pesquisarPorPais(Long idPais)
			throws ClassManagerException;

}
