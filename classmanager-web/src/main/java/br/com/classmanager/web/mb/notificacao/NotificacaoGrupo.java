package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.utils.CMCollectionUtils;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoPadrao;
import br.com.classmanager.web.notificacao.NotificacaoView;

@SessionScoped
@NotificacaoView
public class NotificacaoGrupo extends GenericManagedBean implements
		NotificacaoBase {

	private static final long serialVersionUID = -7970955670574091916L;

	@Inject
	private SessionBean sessionBean;

	@Override
	public String getHeader() {
		return getMessage("Notificacao_Grupo_Header");
	}

	@Override
	public String getHeaderTabela() {
		return getMessage("Notificacao_Grupo_Header");
	}

	@Override
	public List<NotificacaoPadrao> getListaNotificacao() {
		List<NotificacaoPadrao> lista = new ArrayList<NotificacaoPadrao>();

		sessionBean.setAtualizarUsuario(true);
		for (UsuarioGrupo usuarioGrupo : sessionBean.getListaGrupos()) {
			NotificacaoPadrao notif = new NotificacaoPadrao();
			if (StatusUsuarioGrupo.PARTICIPANTE
					.equals(usuarioGrupo.getStatus())) {
				String titulo = usuarioGrupo.getGrupo().getTitulo();
				titulo += " (" + usuarioGrupo.getGrupo().getId() + ")";
				notif.setTitulo(titulo);
				notif.setId(usuarioGrupo.getGrupo().getId());
				lista.add(notif);
			} else if (StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo
					.getStatus())) {
				String titulo = "* " + usuarioGrupo.getGrupo().getTitulo();
				titulo += " (" + usuarioGrupo.getGrupo().getId() + ")";
				notif.setTitulo(titulo);
				notif.setId(usuarioGrupo.getGrupo().getId());
				lista.add(notif);
			}
		}

		CMCollectionUtils.ordenarLista(lista, new String[] { "titulo" });

		return lista;
	}

}
