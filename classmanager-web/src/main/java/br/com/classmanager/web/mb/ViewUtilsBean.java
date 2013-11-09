package br.com.classmanager.web.mb;

import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.web.mb.def.GenericManagedBean;

@Named("viewUtils")
public class ViewUtilsBean extends GenericManagedBean {

	private static final long serialVersionUID = 91919929905000248L;

	public String nomeGrupoFormatado(Grupo grupo) {
		if (grupo == null) {
			return null;
		}
		return grupo.getTitulo() + " (" + grupo.getId() + ")";
	}

	public String nomeTipoPostagem(TipoPostagem tipoPostagem) {
		if (tipoPostagem == null) {
			return null;
		}
		return getMessage("Tipo_Postagem_" + tipoPostagem.getValor());
	}

	public String servicosEnvioUtilizados(Postagem post) {
		if (post == null
				|| CollectionUtils.isEmpty(post.getServicosUtilizados())) {
			return null;
		}
		String serv = "";
		boolean inicial = true;
		for (ServicoEnvio servico : post.getServicosUtilizados()) {
			if (inicial) {
				serv += "(";
				inicial = false;
			} else {
				serv += ", ";
			}
			serv += servico.getNome();
		}
		if (StringUtils.isNotBlank(serv)) {
			serv += ")";
		}
		return serv;
	}

	public String statusUsuarioGrupo(StatusUsuarioGrupo status) {
		if (status == null)
			return null;
		switch (status) {
		case CONVIDADO:
			return getMessage("Usuario_Grupo_Convidado");
		case CRIADOR:
			return getMessage("Usuario_Grupo_Criador");
		case PARTICIPANTE:
			return getMessage("Usuario_Grupo_Participante");
		case SOLICITANDO_PARTICIPACAO:
			return getMessage("Usuario_Grupo_Solicitando_Participacao");
		}
		return null;
	}

}
