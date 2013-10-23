package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.classmanager.client.dto.action.core.ConfirmarEntradaGrupoAction;
import br.com.classmanager.client.dto.action.core.ExcluirUsuarioGrupoAction;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoPadrao;
import br.com.classmanager.web.notificacao.NotificacaoView;
import br.com.classmanager.web.service.ClassManagerServiceView;

@SessionScoped
@NotificacaoView
public class NotificacaoGrupoPendentes extends GenericManagedBean implements
		NotificacaoBase {

	private static final long serialVersionUID = -7970955670574091916L;

	private List<NotificacaoPadrao> listaNotificacao;
	private Long idSelecionado;

	@Inject
	private SessionBean sessionBean;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

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
			if (StatusUsuarioGrupo.CONVIDADO.equals(usuarioGrupo
					.getStatus())) {
				notif.setId(usuarioGrupo.getId());
				notif.setTitulo(usuarioGrupo.getGrupo().getTitulo());
				this.listaNotificacao.add(notif);
			} else if (StatusUsuarioGrupo.SOLICITANDO_PARTICIPACAO
					.equals(usuarioGrupo.getStatus())) {
				notif.setId(usuarioGrupo.getId());
				notif.setTitulo(usuarioGrupo.getGrupo().getTitulo() + " ("
						+ getMessage("Notificacao_Grupo_Pendentes_Solicitacao")
						+ ")");
				this.listaNotificacao.add(notif);
			}
		}
	}

	public void confirmarEntradaGrupo() {
		try {
			UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
			usuarioGrupo.setId(idSelecionado);

			ConfirmarEntradaGrupoAction action = new ConfirmarEntradaGrupoAction();
			action.setUsuarioGrupo(usuarioGrupo);
			service.execute(action);

			sessionBean.setAtualizarUsuario(true);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		atualizar();
	}

	public void confirmarRejeicaoGrupo() {
		try {
			ExcluirUsuarioGrupoAction action = new ExcluirUsuarioGrupoAction();
			UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
			usuarioGrupo.setId(idSelecionado);
			action.setUsuarioGrupo(usuarioGrupo);
			service.execute(action);

			sessionBean.setAtualizarUsuario(true);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		atualizar();
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		System.out.println("ID " + idSelecionado);
		this.idSelecionado = idSelecionado;
	}

}
