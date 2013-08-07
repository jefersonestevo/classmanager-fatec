package br.com.classmanager.client.utils;

import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServerUtils {

	public static SessionContext getSessionContext() {
		try {
			InitialContext ic = new InitialContext();
			SessionContext sctxLookup = (SessionContext) ic
					.lookup("java:comp/EJBContext");
			return sctxLookup;
		} catch (NamingException ex) {
			throw new IllegalStateException(ex);
		}
	}
}
