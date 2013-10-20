package br.com.classmanager.server.domain.modelo.dao.interfaces.core;

import java.util.List;

import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoCRUD;

public interface IDaoUsuarioGrupo extends IDaoCRUD<UsuarioGrupo, Long> {

	public List<UsuarioGrupo> pesquisarPorUsuario(Long id)
			throws ClassManagerException;

	public List<UsuarioGrupo> pesquisarPorGrupo(Long id)
			throws ClassManagerException;
}
