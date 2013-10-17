package br.com.classmanager.web.notificacao;

import java.io.Serializable;
import java.util.List;

public interface NotificacaoBase extends Serializable {

	public abstract String getHeader();

	public abstract String getHeaderTabela();

	public abstract List<NotificacaoPadrao> getListaNotificacao();

	public abstract void atualizar();

	public abstract String executar();

}
