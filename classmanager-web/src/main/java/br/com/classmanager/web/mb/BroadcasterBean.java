package br.com.classmanager.web.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import br.com.classmanager.web.mb.def.GenericManagedBean;

@Named
@ApplicationScoped
public class BroadcasterBean extends GenericManagedBean {

	private static final long serialVersionUID = -2318089416029685106L;

	private PushContext pushContext;

	public static final String MESSAGE_GRUPO = "messageGrupo";

	@PostConstruct
	public void inicializar() {
		pushContext = PushContextFactory.getDefault().getPushContext();
	}

	public void broadcastGrupo(Long idGrupo, String mensagem) {
		String CHANNEL = "/" + MESSAGE_GRUPO + idGrupo;
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("wdMessageGrupo" + idGrupo + ".connect('"
				+ CHANNEL + "')");

		pushContext.push(CHANNEL, mensagem);
	}

}
