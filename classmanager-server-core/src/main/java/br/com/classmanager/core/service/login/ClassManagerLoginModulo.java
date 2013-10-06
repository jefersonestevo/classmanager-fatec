package br.com.classmanager.core.service.login;

import java.security.acl.Group;
import java.util.Map;

import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import br.com.classmanager.client.dto.action.core.ConsultarUsuarioAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.utils.CDIUtils;
import br.com.classmanager.core.utils.ServiceResolver;
import br.com.classmanager.server.domain.service.IService;

public class ClassManagerLoginModulo extends UsernamePasswordLoginModule {

	private static final Logger log = Logger
			.getLogger(ClassManagerLoginModulo.class);

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		super.initialize(subject, callbackHandler, sharedState, options);
	}

	public boolean login() throws LoginException {
		return super.login();
	}

	@Override
	protected String getUsersPassword() throws LoginException {
		Usuario usuario = getResposta(getUsernameAndPassword()[0],
				getUsernameAndPassword()[1]);

		if (usuario == null || usuario.getId() == null)
			return null;
		return usuario.getSenha();
	}

	@Override
	protected Group[] getRoleSets() throws LoginException {
		Usuario usuario = getResposta(getUsernameAndPassword()[0],
				getUsernameAndPassword()[1]);

		if (usuario == null || usuario.getId() == null)
			return null;

		SimpleGroup group = new SimpleGroup("Roles");
		group.addMember(new SimplePrincipal(usuario.getPerfilUsuario()
				.getNome()));
		return new Group[] { group };
	}

	@SuppressWarnings("unchecked")
	private Usuario getResposta(String login, String senha) {
		ServiceResolver resolver = new ServiceResolver();
		try {
			CDIUtils.programmaticInjection(ServiceResolver.class, resolver);
			ConsultarUsuarioAction consultaUsuario = new ConsultarUsuarioAction();
			consultaUsuario.setLogin(login);

			IService<ConsultarUsuarioAction, Usuario> service = (IService<ConsultarUsuarioAction, Usuario>) resolver
					.getServiceFor(ConsultarUsuarioAction.class);
			return service.execute(consultaUsuario);
		} catch (NamingException e) {
			log.error("Erro ao realizar login.", e);
		} catch (ClassManagerException e) {
		} catch (Exception e) {
			log.error("Erro ao realizar login.", e);
		}
		return null;
	}

}
