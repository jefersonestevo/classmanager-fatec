package br.com.classmanager.client.entidades.auditoria;

import javax.ejb.SessionContext;

import org.apache.log4j.Logger;
import org.hibernate.envers.RevisionListener;

import br.com.classmanager.client.utils.ServerUtils;

public class ListenerRevisao implements RevisionListener {

	private static final Logger log = Logger.getLogger(ListenerRevisao.class);

	public void newRevision(Object revisionEntity) {
		EntidadeRevisao rev = (EntidadeRevisao) revisionEntity;

		try {
			SessionContext session = null;
			try {
				session = ServerUtils.getSessionContext();
			} catch (IllegalStateException e) {
				return;
			}

			Long id = (Long) session.getContextData().get(
					session.getCallerPrincipal().getName());

			rev.setUsuario(session.getCallerPrincipal().getName());
			rev.setIdUsuario(id);
		} catch (Exception e) {
			log.error("Erro ao gerar dados específicos para revisão do Envers",
					e);
		}
	}

}
