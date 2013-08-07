package br.com.classmanager.web.mb.core;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.classmanager.client.IAppBean;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.context.annotation.ClassManagerViewScoped;
import br.com.classmanager.web.mb.def.GenericManagedBean;

import com.google.gson.Gson;

@Named
@ClassManagerViewScoped
public class TesteBean extends GenericManagedBean {

	private static final long serialVersionUID = 539588484164979841L;

	@Inject
	private IAppBean appBean;

	private String texto;

	public void publish() {
		try {
			appBean.atualizar(new DTOAction() {
				@Override
				public void setUsuarioAtual(Usuario usuario) {
				}

				@Override
				public Usuario getUsuarioAtual() {
					Usuario usuario = new Usuario();
					usuario.setId(1000l);
					return usuario;
				}
			}, this.texto);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		this.texto = "";
	}

	public void atualizarLista() {
		if (!appBean.getLista().isEmpty()) {
			List<String> lista = new ArrayList<String>();
			for (String str : appBean.getLista()) {
				lista.add(str.trim());
			}
			String json = new Gson().toJson(lista, ArrayList.class);
			RequestContext.getCurrentInstance().addCallbackParam("lista", json);
		}
	}

	public String testar() {
		return "pm:viewB";
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
