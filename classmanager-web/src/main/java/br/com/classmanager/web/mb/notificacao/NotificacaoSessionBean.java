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
	private NotificacaoGrupoPendentes notificacaoGrupoPendente;

	@Inject
	@NotificacaoView
	private NotificacaoGrupoComPendencia notificacaoGrupoComPendencia;

	@Inject
	@NotificacaoView
	private NotificacaoUltimosGruposAtualizados notificacaoUltimosGruposAtualizados;

	public NotificacaoGrupo getNotificacaoGrupo() {
		return notificacaoGrupo;
	}

	public void setNotificacaoGrupo(NotificacaoGrupo notificacaoGrupo) {
		this.notificacaoGrupo = notificacaoGrupo;
	}

	public NotificacaoGrupoPendentes getNotificacaoGrupoPendente() {
		return notificacaoGrupoPendente;
	}

	public void setNotificacaoGrupoPendente(
			NotificacaoGrupoPendentes notificacaoGrupoPendente) {
		this.notificacaoGrupoPendente = notificacaoGrupoPendente;
	}

	public NotificacaoGrupoComPendencia getNotificacaoGrupoComPendencia() {
		return notificacaoGrupoComPendencia;
	}

	public void setNotificacaoGrupoComPendencia(
			NotificacaoGrupoComPendencia notificacaoGrupoComPendencia) {
		this.notificacaoGrupoComPendencia = notificacaoGrupoComPendencia;
	}

	public NotificacaoUltimosGruposAtualizados getNotificacaoUltimosGruposAtualizados() {
		return notificacaoUltimosGruposAtualizados;
	}

	public void setNotificacaoUltimosGruposAtualizados(
			NotificacaoUltimosGruposAtualizados notificacaoUltimosGruposAtualizados) {
		this.notificacaoUltimosGruposAtualizados = notificacaoUltimosGruposAtualizados;
	}

}
