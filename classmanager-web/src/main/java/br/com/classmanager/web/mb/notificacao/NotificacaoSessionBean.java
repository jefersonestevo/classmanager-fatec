package br.com.classmanager.web.mb.notificacao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoView;

@Named
@SessionScoped
public class NotificacaoSessionBean extends GenericManagedBean {

	private static final long serialVersionUID = 6562557614579783248L;

	@Inject
	@NotificacaoView
	private NotificacaoGrupo notificacaoGrupo;

	@Inject
	@NotificacaoView
	private NotificacaoGruposPendentes notificacaoGrupoPendente;

	public NotificacaoGrupo getNotificacaoGrupo() {
		return notificacaoGrupo;
	}

	public void setNotificacaoGrupo(NotificacaoGrupo notificacaoGrupo) {
		this.notificacaoGrupo = notificacaoGrupo;
	}

	public NotificacaoGruposPendentes getNotificacaoGrupoPendente() {
		return notificacaoGrupoPendente;
	}

	public void setNotificacaoGrupoPendente(
			NotificacaoGruposPendentes notificacaoGrupoPendente) {
		this.notificacaoGrupoPendente = notificacaoGrupoPendente;
	}

}
