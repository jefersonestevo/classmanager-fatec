package br.com.classmanager.server.domain.modelo.dao.impl.jpa.usuario;

import java.util.List;

import javax.inject.Named;

import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;

@Named
@DAO
public class DaoUsuarioJPA extends DaoCRUDJPA<Usuario, Long> implements
		IDaoUsuario {

	private static final long serialVersionUID = -6158245061878940514L;

	@Override
	protected Class<Usuario> getEntidadePersistente() {
		return Usuario.class;
	}

	@Override
	public List<Usuario> pesquisarLista(List<Long> idUsuarios)
			throws ClassManagerException {
		return getTemplate().pesquisarLista(getEntidadePersistente(),
				idUsuarios);
	}

}
