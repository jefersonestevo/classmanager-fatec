package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.dto.action.core.EsqueciMinhaSenhaAction;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.core.utils.EmailSender;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;
import br.com.classmanager.server.domain.service.Servico;

@Named("EsqueciMinhaSenhaAction")
public class EsqueciMinhaSenhaService extends
		Servico<EsqueciMinhaSenhaAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuario daoUsuario;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(EsqueciMinhaSenhaAction request)
			throws ClassManagerException {
		Usuario usuario = null;
		if (StringUtils.isNotEmpty(request.getLogin())) {
			usuario = daoUsuario.pesquisarPorLogin(request.getLogin());
		} else if (StringUtils.isNotEmpty(request.getEmail())) {
			usuario = daoUsuario.pesquisarPorEmail(request.getEmail());
		}

		if (usuario == null) {
			throw new ClassManagerException(
					CodigoExcecao.ESQUECI_MINHA_SENHA_USUARIO_NAO_ENCONTRADO);
		}
		// TODO - Arrumar isso
		String assunto = "HINTER - " + usuario.getLogin();
		String conteudo = "Sua senha do Hinter, para o usuário \""
				+ usuario.getLogin() + "\" é: " + usuario.getSenha() + ".";
		conteudo += "<br /> Favor descartar este e-mail imediatamente.";
		EmailSender.enviarEmail(usuario.getEmail(), assunto, conteudo);

		return new NullDTO();
	}

}
