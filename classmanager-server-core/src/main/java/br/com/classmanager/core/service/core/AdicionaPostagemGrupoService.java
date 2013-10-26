package br.com.classmanager.core.service.core;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AdicionaPostagemGrupoAction;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoServicoEnvio;
import br.com.classmanager.server.domain.service.Servico;

@Named("AdicionaPostagemGrupoAction")
public class AdicionaPostagemGrupoService extends
		Servico<AdicionaPostagemGrupoAction, Postagem> {

	@Inject
	@DAO
	private IDaoPostagem daoPostagem;

	@Inject
	@DAO
	private IDaoServicoEnvio daoServicoEnvio;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Postagem execute(AdicionaPostagemGrupoAction request)
			throws ClassManagerException {

		Postagem postagem = request.getPostagem();
		postagem.setUsuario(request.getUsuarioAtual());
		postagem.setGrupo(request.getGrupo());
		postagem.setDataGeracao(new Date());

		postagem.setServicosUtilizados(new ArrayList<ServicoEnvio>());
		for (Long idServ : request.getServicosEnvio()) {
			if (idServ != null) {
				ServicoEnvio servico = daoServicoEnvio.pesquisar(idServ);
				postagem.getServicosUtilizados().add(servico);
				// TODO - Realizar o envio para cada servico
			}
		}

		if (!TipoPostagem.VAGA.equals(postagem.getTipoPostagem())) {
			postagem.setCargo(null);
			postagem.setEndereco(null);
			postagem.setDataInicio(null);
			postagem.setContato(null);
		}

		daoPostagem.inserir(postagem);
		return postagem;
	}

}
