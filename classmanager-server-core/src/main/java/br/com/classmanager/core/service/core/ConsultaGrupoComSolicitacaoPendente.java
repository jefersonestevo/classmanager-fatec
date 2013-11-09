package br.com.classmanager.core.service.core;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ConsultaGrupoComSolicitacaoPendenteAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultaGrupoComSolicitacaoPendenteAction")
public class ConsultaGrupoComSolicitacaoPendente extends
		Servico<ConsultaGrupoComSolicitacaoPendenteAction, ListaDTO<Grupo>> {

	@Inject
	@DAO
	private IDaoGrupo daoGrupo;

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	public ListaDTO<Grupo> execute(
			ConsultaGrupoComSolicitacaoPendenteAction request)
			throws ClassManagerException {

		List<Grupo> listaGruposConsulta = daoGrupo.pesquisarLista(request
				.getIdGruposConsulta());

		ListaDTO<Grupo> listaGrupos = new ListaDTO<Grupo>();
		for (Grupo grupo : listaGruposConsulta) {
			List<UsuarioGrupo> listaUsuarioGrupo = daoUsuarioGrupo
					.pesquisarPorGrupo(grupo.getId());
			for (UsuarioGrupo ug : listaUsuarioGrupo) {
				if (StatusUsuarioGrupo.SOLICITANDO_PARTICIPACAO.equals(ug
						.getStatus())) {
					listaGrupos.getLista().add(grupo);
					break;
				}
			}
		}
		return listaGrupos;
	}

}
