package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.entidades.geral.Arquivo;
import br.com.classmanager.client.entidades.geral.Email;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoArquivo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoEmail;

@Named
@DAO
public class DaoEmailJPA extends DaoCRUDJPA<Email, Long> implements
		IDaoEmail {

	private static final long serialVersionUID = -4768728649262635433L;

	@Inject
	@DAO
	private IDaoArquivo daoArquivo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserir(Email entidade) throws ClassManagerException {
		if (CollectionUtils.isNotEmpty(entidade.getListaAnexos())) {
			for (Arquivo anexo : entidade.getListaAnexos()) {
				if (anexo.getId() == null) {
					daoArquivo.inserir(anexo);
				}
			}
		}
		getTemplate().inserir(entidade);
	}

	@Override
	protected Class<Email> getEntidadePersistente() {
		return Email.class;
	}

}
