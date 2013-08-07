package br.com.classmanager.web.mb.core;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.primefaces.push.PushContextFactory;

import br.com.classmanager.client.EventoTeste;

@ApplicationScoped
public class AplicacaoBeanWeb implements Serializable {

	private static final long serialVersionUID = 6002645531392370230L;

	public static final String BROADCAST_CHANNEL = "/chat-web";

	public void atualizar(@Observes EventoTeste evento) {
		PushContextFactory.getDefault().getPushContext()
				.push(BROADCAST_CHANNEL, evento.getTexto());
	}

}
