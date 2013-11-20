package br.com.classmanager.core.service.core;

import java.util.Date;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AdicionaComentarioPostagemAction;
import br.com.classmanager.client.entidades.core.ComentarioPostagem;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoComentarioPostagem;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.service.Servico;

@Named("AdicionaComentarioPostagemAction")
public class AdicionaComentarioPostagemService extends
		Servico<AdicionaComentarioPostagemAction, ComentarioPostagem> {

	@Inject
	@DAO
	private IDaoComentarioPostagem daoComentarioPostagem;

	@Inject
	@DAO
	private IDaoPostagem daoPostagem;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComentarioPostagem execute(AdicionaComentarioPostagemAction request)
			throws ClassManagerException {

		ComentarioPostagem comentario = new ComentarioPostagem();
		comentario.setUsuario(request.getUsuarioAtual());
		comentario.setDataGeracao(new Date());
		comentario.setDescricao(request.getComentarioPostagem());
		comentario.setPostagem(request.getPostagem());

		daoComentarioPostagem.inserir(comentario);

		Postagem post = daoPostagem.pesquisar(comentario.getPostagem().getId());
		post.setUltimaAtualizacao(new Date());
		daoPostagem.alterar(post);

		comentario.setPostagem(post);

		return comentario;
	}

}
