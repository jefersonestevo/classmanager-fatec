package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoPadrao;
import br.com.classmanager.web.notificacao.NotificacaoView;

@SessionScoped
@NotificacaoView
public class NotificacaoGrupo extends GenericManagedBean implements
		NotificacaoBase {

	private static final long serialVersionUID = -7970955670574091916L;

	@Override
	public String getHeader() {
		return "Teste01";
	}

	@Override
	public String getHeaderTabela() {
		return "Teste Table 01";
	}

	@Override
	public List<NotificacaoPadrao> getListaNotificacao() {
		List<NotificacaoPadrao> lista = new ArrayList<NotificacaoPadrao>();

		NotificacaoPadrao notif = new NotificacaoPadrao();
		notif.setTitulo("titulo 01");
		lista.add(notif);

		notif = new NotificacaoPadrao();
		notif.setTitulo("titulo 02");
		lista.add(notif);
		return lista;
	}

	@Override
	public void atualizar() {

	}

	@Override
	public String executar() {
		return null;
	}

}
