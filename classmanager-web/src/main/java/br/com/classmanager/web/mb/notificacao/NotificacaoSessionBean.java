package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoBeanExtension;

@Named
@SessionScoped
public class NotificacaoSessionBean extends GenericManagedBean {

	private static final long serialVersionUID = 6562557614579783248L;

	private List<NotificacaoBase> notificacoes;

	@Inject
	private BeanManager manager;

	@PostConstruct
	public void inicializar() {

		notificacoes = new ArrayList<NotificacaoBase>();
		for (Bean<?> bean : NotificacaoBeanExtension.notificacaoEventos) {
			notificacoes
					.add((NotificacaoBase) manager.getReference(bean,
							bean.getBeanClass(),
							manager.createCreationalContext(bean)));
		}

	}

	public List<NotificacaoBase> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<NotificacaoBase> notificacoes) {
		this.notificacoes = notificacoes;
	}

}
