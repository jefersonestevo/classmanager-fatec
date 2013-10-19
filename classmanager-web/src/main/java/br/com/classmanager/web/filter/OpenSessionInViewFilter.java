package br.com.classmanager.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

public class OpenSessionInViewFilter implements Filter {

	private static final Logger log = Logger
			.getLogger(OpenSessionInViewFilter.class);

	@Resource
	UserTransaction tx;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			tx.begin();
			chain.doFilter(request, response);
		} catch (Exception e) {
			log.error("Erro ao executar " + getClass().getName(), e);
		} finally {
			try {
				tx.commit();
			} catch (Exception e) {
				log.error(
						"Erro ao executar finally de " + getClass().getName(),
						e);
			}
		}

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
