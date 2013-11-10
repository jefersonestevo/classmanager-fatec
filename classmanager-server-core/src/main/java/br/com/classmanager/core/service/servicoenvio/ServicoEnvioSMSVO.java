package br.com.classmanager.core.service.servicoenvio;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import br.com.classmanager.core.service.email.TemplateEmailMapper;

public class ServicoEnvioSMSVO implements Serializable {

	private static final long serialVersionUID = 3896462241921665749L;

	private TemplateEmailMapper template;
	private Map<String, String> atributos;
	private Set<String> emails;

	public TemplateEmailMapper getTemplate() {
		return template;
	}

	public void setTemplate(TemplateEmailMapper template) {
		this.template = template;
	}

	public Map<String, String> getAtributos() {
		return atributos;
	}

	public void setAtributos(Map<String, String> atributos) {
		this.atributos = atributos;
	}

	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}

}
