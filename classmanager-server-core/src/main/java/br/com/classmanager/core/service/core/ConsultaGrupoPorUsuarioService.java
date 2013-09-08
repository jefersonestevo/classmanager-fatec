package br.com.classmanager.core.service.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ConsultaGrupoPorUsuarioAction;
import br.com.classmanager.client.dto.core.GrupoSimples;
import br.com.classmanager.client.dto.core.PostagemSimples;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultaGrupoPorUsuarioAction")
public class ConsultaGrupoPorUsuarioService extends
		Servico<ConsultaGrupoPorUsuarioAction, ListaDTO<GrupoSimples>> {

	@Inject
	@DAO
	private IDaoPostagem daoPostagem;

	@Inject
	@DAO
	private IDaoGrupo daoGrupo;

	@Override
	public ListaDTO<GrupoSimples> execute(ConsultaGrupoPorUsuarioAction request)
			throws ClassManagerException {

		List<Grupo> listaGrupoUsuario = daoGrupo
				.pesquisarGrupoAtivoPorUsuario(request.getIdUsuario());

		List<Long> idGrupos = new ArrayList<Long>();
		for (Grupo grupo : listaGrupoUsuario) {
			idGrupos.add(grupo.getId());
		}
		Map<Long, List<Postagem>> mapPostagemPorGrupo = daoPostagem
				.pesquisarPostagemPorGrupo(idGrupos);

		ListaDTO<GrupoSimples> listaGrupoSimples = new ListaDTO<GrupoSimples>();
		for (Grupo grupo : listaGrupoUsuario) {
			GrupoSimples gs = new GrupoSimples(grupo);

			for (Postagem postagem : mapPostagemPorGrupo.get(grupo.getId())) {
				gs.getListaPostagem().add(new PostagemSimples(postagem));
			}
			listaGrupoSimples.getLista().add(gs);
		}

		return listaGrupoSimples;
	}

}
