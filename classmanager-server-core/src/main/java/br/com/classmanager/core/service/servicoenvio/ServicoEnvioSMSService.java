package br.com.classmanager.core.service.servicoenvio;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.classmanager.client.exceptions.ClassManagerException;

@Named
@Stateless
public class ServicoEnvioSMSService implements Serializable {

	private static final long serialVersionUID = 360221428362128327L;

	public void enviarEmail(Map<String, String> atributos,
			List<String> telefones) throws ClassManagerException {

	}

}
