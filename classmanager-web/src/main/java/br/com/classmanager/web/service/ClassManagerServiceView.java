package br.com.classmanager.web.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.dto.def.DTOManterAction;
import br.com.classmanager.client.dto.def.DTOPesquisarAction;
import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.enums.AcaoPesquisar;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;
import br.com.classmanager.client.service.IClassManagerService;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;

@Named
@ServiceView
public class ClassManagerServiceView implements Serializable {

	private static final long serialVersionUID = 7055810666518898499L;

	@Inject
	private IClassManagerService delegator;

	@Inject
	private SessionBean sessionBean;

	public DTO execute(DTOAction vo) throws ClassManagerException {
		return execute(vo, false);
	}

	public DTO execute(DTOAction vo, boolean acaoPublica)
			throws ClassManagerException {
		if (!acaoPublica && vo.getUsuarioAtual() == null) {
			vo.setUsuarioAtual(sessionBean.getUsuario());
		}
		return delegator.execute(vo);
	}

	public boolean hasPermissao(Class<DTOServicoAction> classVO)
			throws ClassManagerSecurityException {
		return delegator.hasPermissao(classVO, sessionBean.getUsuario());
	}

	public boolean hasPermissao(AcaoManter acao, Class<DTOManterAction> classVO)
			throws ClassManagerSecurityException {
		return delegator.hasPermissao(acao, classVO, sessionBean.getUsuario());
	}

	public boolean hasPermissao(AcaoPesquisar acao,
			Class<DTOPesquisarAction> classVO)
			throws ClassManagerSecurityException {
		return delegator.hasPermissao(acao, classVO, sessionBean.getUsuario());
	}

}
