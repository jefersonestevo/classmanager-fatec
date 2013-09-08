package br.com.classmanager.server.domain.modelo.dao.interfaces.core;

import java.util.List;
import java.util.Map;

import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoCRUD;

public interface IDaoPostagem extends IDaoCRUD<Postagem, Long> {

	public Map<Long, List<Postagem>> pesquisarPostagemPorGrupo(
			List<Long> idGrupos) throws ClassManagerException;

}
