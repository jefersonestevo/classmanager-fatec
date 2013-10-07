package br.com.classmanager.server.domain.modelo.dao.interfaces.usuario;

import java.util.List;

import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoCRUD;

public interface IDaoUsuario extends IDaoCRUD<Usuario, Long> {

	public List<Usuario> pesquisarLista(List<Long> idUsuarios)
			throws ClassManagerException;

	public List<Usuario> pesquisarLista(String nome, String login, String email)
			throws ClassManagerException;

	public Usuario pesquisarPorLogin(String login)
			throws ClassManagerException;

	public Usuario pesquisarPorEmail(String email) throws ClassManagerException;

}
