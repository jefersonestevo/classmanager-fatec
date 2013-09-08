package br.com.classmanager.web.mb.core;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.primefaces.push.PushContextFactory;

import br.com.classmanager.client.EventoTeste;
import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.service.ClassManagerServiceView;

@ApplicationScoped
public class AplicacaoBeanWeb implements Serializable {

	private static final long serialVersionUID = 6002645531392370230L;

	public static final String BROADCAST_CHANNEL = "/chat-web";

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	public void atualizar(@Observes EventoTeste evento) {
		PushContextFactory.getDefault().getPushContext()
				.push(BROADCAST_CHANNEL, evento.getTexto());

		try {
			ManterGrupoAction action = new ManterGrupoAction(AcaoManter.INSERIR);
			action.setEntidade(new Grupo());
			service.execute(action);
		} catch (ClassManagerException e) {
		}
	}

}
