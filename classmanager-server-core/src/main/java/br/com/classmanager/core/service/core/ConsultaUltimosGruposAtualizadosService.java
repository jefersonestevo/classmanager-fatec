package br.com.classmanager.core.service.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.dto.action.core.ConsultaUltimosGruposAtualizadosAction;
import br.com.classmanager.client.dto.core.GrupoAtualizado;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.utils.GrupoAtualizadoComparator;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultaUltimosGruposAtualizadosAction")
public class ConsultaUltimosGruposAtualizadosService
		extends
		Servico<ConsultaUltimosGruposAtualizadosAction, ListaDTO<GrupoAtualizado>> {

	@Inject
	@DAO
	private IDaoGrupo daoGrupo;

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Inject
	@DAO
	private IDaoPostagem daoPostagem;

	@Override
	public ListaDTO<GrupoAtualizado> execute(
			ConsultaUltimosGruposAtualizadosAction request)
			throws ClassManagerException {

		List<Postagem> listaPostagens = daoPostagem
				.pesquisarPostagensDosGrupos(request.getIdGrupos());

		Map<Grupo, Date> mapGrupoUltimaDataAtualizacao = new HashMap<Grupo, Date>();

		if (CollectionUtils.isNotEmpty(listaPostagens)) {
			for (Postagem post : listaPostagens) {
				Date ultimaAtualizacao = mapGrupoUltimaDataAtualizacao.get(post
						.getGrupo());
				if (ultimaAtualizacao == null) {
					mapGrupoUltimaDataAtualizacao.put(post.getGrupo(),
							post.getUltimaAtualizacao());
					continue;
				}

				if (ultimaAtualizacao.before(post.getUltimaAtualizacao())) {
					mapGrupoUltimaDataAtualizacao.put(post.getGrupo(),
							post.getUltimaAtualizacao());
				}
			}
		}

		List<GrupoAtualizado> listaGrupos = new ArrayList<GrupoAtualizado>();
		for (Entry<Grupo, Date> entry : mapGrupoUltimaDataAtualizacao
				.entrySet()) {
			listaGrupos.add(new GrupoAtualizado(entry.getKey(), entry
					.getValue()));
		}

		Collections.sort(listaGrupos, new GrupoAtualizadoComparator());

		return new ListaDTO<GrupoAtualizado>(listaGrupos);
	}

}
