package br.com.classmanager.core.service.servicoenvio;

import java.io.Serializable;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.service.email.EmailSender;

@Named
@Stateless
public class ServicoEnvioEmailService implements Serializable {

	private static final long serialVersionUID = 360221428362128327L;

	private static Logger log = Logger
			.getLogger(ServicoEnvioEmailService.class);

	@Inject
	private EmailSender emailSender;

	@Asynchronous
	public void enviarEmail(@Observes ServicoEnvioEmailVO servicoEnvioEmail)
			throws ClassManagerException {
		log.debug("Início do servico de envio de email.");
		if (CollectionUtils.isEmpty(servicoEnvioEmail.getEmails())) {
			return;
		}

		for (String email : servicoEnvioEmail.getEmails()) {
			log.debug("Enviando e-mail para " + email);
			emailSender.enviarEmail(servicoEnvioEmail.getTemplate(),
					servicoEnvioEmail.getAtributos(), email);
			log.debug("Termino envio e-mail para " + email);
		}
	}

}
