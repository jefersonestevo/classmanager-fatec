package br.com.classmanager.core.service.endereco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.endereco.ConsultaCidadeAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.endereco.Cidade;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.service.utils.AtualizadorLista;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.endereco.IDaoCidade;
import br.com.classmanager.server.domain.service.impl.ServicoPesquisarBase;

@ApplicationScoped
@Named("ConsultaCidadeAction")
public class ConsultaCidadeService extends
		ServicoPesquisarBase<ConsultaCidadeAction, Cidade, Long> {

	private Map<Long, AtualizadorCidade> mapCidadesPorEstado = new HashMap<Long, AtualizadorCidade>();

	@Inject
	@DAO
	private IDaoCidade dao;

	@Override
	protected ListaDTO<Cidade> pesquisarLista(ConsultaCidadeAction request)
			throws ClassManagerException {
		if (request.getIdEstado() != null) {
			AtualizadorCidade holder = mapCidadesPorEstado.get(request
					.getIdEstado());
			if (holder == null) {
				synchronized (this) {
					if (holder == null) {
						holder = new AtualizadorCidade(request.getIdEstado());
						mapCidadesPorEstado.put(request.getIdEstado(), holder);
					}
				}
			}
			return new ListaDTO<Cidade>(holder.getLista());
		}
		return super.pesquisarLista(request);
	}

	public IDaoCidade getDao() {
		return dao;
	}

	protected class AtualizadorCidade extends AtualizadorLista<Cidade> {

		private static final long serialVersionUID = -1788701681889265991L;

		private Long idEstado;

		public AtualizadorCidade(Long idEstado) {
			this.idEstado = idEstado;
		}

		@Override
		protected List<Cidade> getListaNova() throws ClassManagerException {
			return dao.pesquisarPorEstado(idEstado);
		}
	}


}
