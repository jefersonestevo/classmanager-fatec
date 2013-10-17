package br.com.classmanager.web.notificacao;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;

public class NotificacaoBeanExtension implements Extension {

	public static final Set<Bean<?>> notificacaoEventos = new LinkedHashSet<Bean<?>>();

	<X> void processBean(@Observes ProcessBean<X> event) {
		if (event.getAnnotated().isAnnotationPresent(NotificacaoView.class)) {
			notificacaoEventos.add(event.getBean());
		}
	}

}