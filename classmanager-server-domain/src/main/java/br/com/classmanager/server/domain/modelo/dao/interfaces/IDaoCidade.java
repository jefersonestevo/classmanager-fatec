package br.com.classmanager.server.domain.modelo.dao.interfaces;

import java.util.List;

import br.com.classmanager.client.entidades.endereco.Cidade;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoPesquisa;

public interface IDaoCidade extends IDaoPesquisa<Cidade, Long> {

	public List<Cidade> pesquisarPorEstado(Long idEstado)
			throws ClassManagerException;

}
