package br.com.classmanager.core.service.core;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.PerfilUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.impl.ServicoManterBase;

@Named("ManterGrupoAction")
public class ManterGrupoService extends
		ServicoManterBase<ManterGrupoAction, Grupo, Long> {

	@Inject
	@DAO
	private IDaoGrupo dao;

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	protected IDaoGrupo getDao() {
		return dao;
	}

	@Override
	protected Grupo inserir(ManterGrupoAction request)
			throws ClassManagerException {

		Grupo grupo = request.getEntidade();
		grupo.setDataCriacao(new Date());
		grupo.setStatus(StatusGrupo.ATIVO);
		grupo.setUsuarioCriador(request.getUsuarioAtual());
		getDao().inserir(grupo);

		UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
		usuarioGrupo.setDataEfetivaEntradaGrupo(new Date());
		usuarioGrupo.setDataSolicitacaoEntradaGrupo(new Date());
		usuarioGrupo.setGrupo(grupo);
		usuarioGrupo.setUsuario(request.getUsuarioAtual());
		usuarioGrupo.setStatus(StatusUsuarioGrupo.CRIADOR);
		usuarioGrupo.setPerfil(PerfilUsuarioGrupo.ADM);
		daoUsuarioGrupo.inserir(usuarioGrupo);

		grupo = getDao().pesquisar(grupo.getId());
		return grupo;
	}

}
