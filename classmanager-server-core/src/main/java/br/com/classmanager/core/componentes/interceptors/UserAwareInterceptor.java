package br.com.classmanager.core.componentes.interceptors;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.utils.ServerUtils;

@Interceptor
@UserAware
public class UserAwareInterceptor implements Serializable {

	private static final long serialVersionUID = 6527282411454567941L;

	@AroundInvoke
	public Object saveUser(InvocationContext ctx) throws Exception {

		if (ctx.getParameters().length > 0
				&& (ctx.getParameters()[0] instanceof DTOAction)) {
			DTOAction action = (DTOAction) ctx.getParameters()[0];
			// Insere no map de dados do contexto do container EJB para
			// posterior recuperacao
			// (A principio para salvar a EntidadeRevisao do Envers)
			if (action.getUsuarioAtual() != null) {
				ctx.getContextData().put(
						ServerUtils.getSessionContext().getCallerPrincipal()
								.getName(), action.getUsuarioAtual().getId());
			}
		}

		return ctx.proceed();
	}

}
