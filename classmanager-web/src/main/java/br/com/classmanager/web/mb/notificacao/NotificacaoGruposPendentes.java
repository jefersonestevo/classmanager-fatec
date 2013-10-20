package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.classmanager.client.dto.action.core.ExcluirUsuarioGrupoAction;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoPadrao;
import br.com.classmanager.web.notificacao.NotificacaoView;

@SessionScoped
@NotificacaoView
public class NotificacaoGruposPendentes extends GenericManagedBean implements
		NotificacaoBase {

	private static final long serialVersionUID = -7970955670574091916L;

	private List<NotificacaoPadrao> listaNotificacao;

	@Inject
	private SessionBean sessionBean;

	@Override
	public String getHeader() {
		return getMessage("Notificacao_Grupo_Pendentes_Header");
	}

	@Override
	public String getHeaderTabela() {
		return getMessage("Notificacao_Grupo_Pendentes_Header");
	}

	@Override
	public List<NotificacaoPadrao> getListaNotificacao() {
		if (listaNotificacao == null) {
			atualizar();
		}
		return listaNotificacao;
	}

	public void atualizar() {
		this.listaNotificacao = new ArrayList<NotificacaoPadrao>();

		for (UsuarioGrupo usuarioGrupo : sessionBean.getListaGrupos()) {
			NotificacaoPadrao notif = new NotificacaoPadrao();
			if (StatusUsuarioGrupo.AGUARDANDO_APROVACAO.equals(usuarioGrupo
					.getStatus())) {
				notif.setTitulo(usuarioGrupo.getGrupo().getTitulo());
				this.listaNotificacao.add(notif);
			} else if (StatusUsuarioGrupo.SOLICITANDO_PARTICIPACAO
					.equals(usuarioGrupo.getStatus())) {
				notif.setTitulo(usuarioGrupo.getGrupo().getTitulo() + " ("
						+ getMessage("Notificacao_Grupo_Pendentes_Solicitacao")
						+ ")");
				this.listaNotificacao.add(notif);
			}
		}
	}

	public void confirmarEntradaGrupo() {

		atualizar();
	}

	public void confirmarRejeicaoGrupo() {
		ExcluirUsuarioGrupoAction action = new ExcluirUsuarioGrupoAction();

		atualizar();
	}

}
