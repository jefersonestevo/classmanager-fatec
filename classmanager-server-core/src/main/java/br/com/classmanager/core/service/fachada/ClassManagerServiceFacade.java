package br.com.classmanager.core.service.fachada;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.dto.def.DTOManterAction;
import br.com.classmanager.client.dto.def.DTOPesquisarAction;
import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.enums.AcaoPesquisar;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.client.service.IClassManagerService;
import br.com.classmanager.core.componentes.interceptors.UserAware;
import br.com.classmanager.core.utils.ServiceResolver;
import br.com.classmanager.server.domain.service.IService;
import br.com.classmanager.server.domain.service.Servico;
import br.com.classmanager.server.domain.service.ServicoManter;
import br.com.classmanager.server.domain.service.ServicoPesquisar;

@Stateless
public class ClassManagerServiceFacade implements IClassManagerService {

	private static final long serialVersionUID = 767939690887932488L;

	private static Logger log = Logger
			.getLogger(ClassManagerServiceFacade.class);

	@Inject
	private ServiceResolver resolver;

	@UserAware
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DTO execute(DTOAction vo) throws ClassManagerException {
		try {
			if (!possuiPermissao(vo)) {
				throw new ClassManagerSecurityException(
						CodigoExcecao.ACESSO_RESTRITO);
			}

			IService service = resolver.getServiceFor(vo.getClass());
			DTO voRetorno = service.execute(vo);
			return voRetorno;

		} catch (ClassManagerException e) {
			// TODO
			throw e;
		} catch (Exception e) {
			// TODO
			throw new ClassManagerException(CodigoExcecao.ERRO_DESCONHECIDO, e);
		}

	}

	@SuppressWarnings("unchecked")
	private boolean possuiPermissao(DTOAction dto)
			throws ClassManagerSecurityException {
		if (dto instanceof DTOServicoAction) {
			return hasPermissao((Class<DTOServicoAction>) dto.getClass(),
					dto.getUsuarioAtual());
		}

		if (dto instanceof DTOManterAction) {
			DTOManterAction dtoManter = (DTOManterAction) dto;
			return hasPermissao(dtoManter.getAcao(),
					(Class<DTOManterAction>) dtoManter.getClass(),
					dto.getUsuarioAtual());
		}

		if (dto instanceof DTOPesquisarAction) {
			DTOPesquisarAction dtoPesquisar = (DTOPesquisarAction) dto;
			return hasPermissao(dtoPesquisar.getAcao(),
					(Class<DTOPesquisarAction>) dtoPesquisar.getClass(),
					dto.getUsuarioAtual());
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean hasPermissao(Class<DTOServicoAction> classVO, Usuario usuario)
			throws ClassManagerSecurityException {
		IService service = resolver.getServiceFor(classVO);
		if (service instanceof Servico) {
			return ((Servico) service).hasPermissao(usuario);
		}
		throw new ClassManagerSecurityException(CodigoExcecao.ACESSO_RESTRITO);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean hasPermissao(AcaoManter acao,
			Class<DTOManterAction> classVO, Usuario usuario)
			throws ClassManagerSecurityException {
		IService service = resolver.getServiceFor(classVO);
		if (service instanceof ServicoManter) {
			return ((ServicoManter) service).hasPermissao(acao, usuario);
		}
		throw new ClassManagerSecurityException(CodigoExcecao.ACESSO_RESTRITO);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean hasPermissao(AcaoPesquisar acao,
			Class<DTOPesquisarAction> classVO, Usuario usuario)
			throws ClassManagerSecurityException {
		IService service = resolver.getServiceFor(classVO);
		if (service instanceof ServicoManter) {
			return ((ServicoPesquisar) service).hasPermissao(acao, usuario);
		}
		throw new ClassManagerSecurityException(CodigoExcecao.ACESSO_RESTRITO);
	}

}
