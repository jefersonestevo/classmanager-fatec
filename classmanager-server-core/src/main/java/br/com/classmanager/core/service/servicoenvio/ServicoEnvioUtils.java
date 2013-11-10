package br.com.classmanager.core.service.servicoenvio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.entidades.usuario.Usuario;

public class ServicoEnvioUtils {

	public static Set<String> extrairEmails(List<Usuario> usuarios) {
		Set<String> emails = new HashSet<String>();
		if (CollectionUtils.isEmpty(usuarios)) {
			return emails;
		}

		for (Usuario usuario : usuarios) {
			if (usuario == null || StringUtils.isBlank(usuario.getEmail()))
				continue;
			emails.add(usuario.getEmail());
		}
		return emails;
	}

}
