package br.com.classmanager.core.service.endereco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.endereco.ConsultaEstadoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.endereco.Estado;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.service.utils.AtualizadorLista;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.endereco.IDaoEstado;
import br.com.classmanager.server.domain.service.impl.ServicoPesquisarBase;

@ApplicationScoped
@Named("ConsultaEstadoAction")
public class ConsultaEstadoService extends
		ServicoPesquisarBase<ConsultaEstadoAction, Estado, Long> {

	private Map<Long, AtualizadorEstado> mapEstadosPorPais = new HashMap<Long, AtualizadorEstado>();

	@Inject
	@DAO
	private IDaoEstado dao;

	@Override
	protected ListaDTO<Estado> pesquisarLista(ConsultaEstadoAction request)
			throws ClassManagerException {
		if (request.getIdPais() != null) {
			AtualizadorEstado holder = mapEstadosPorPais.get(request
					.getIdPais());
			if (holder == null) {
				synchronized (this) {
					if (holder == null) {
						holder = new AtualizadorEstado(request.getIdPais());
						mapEstadosPorPais.put(request.getIdPais(), holder);
					}
				}
			}
			return new ListaDTO<Estado>(holder.getLista());
		}
		return super.pesquisarLista(request);
	}

	@Override
	protected IDaoEstado getDao() {
		return dao;
	}

	protected class AtualizadorEstado extends AtualizadorLista<Estado> {

		private static final long serialVersionUID = -1788701681889265991L;

		private Long idPais;

		public AtualizadorEstado(Long idPais) {
			this.idPais = idPais;
		}

		@Override
		protected List<Estado> getListaNova() throws ClassManagerException {
			return dao.pesquisarPorPais(idPais);
		}
	}

}
