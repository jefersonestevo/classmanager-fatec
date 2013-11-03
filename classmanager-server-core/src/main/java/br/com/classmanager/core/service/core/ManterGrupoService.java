package br.com.classmanager.core.service.core;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
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

		// Força o carregamento da nova listagem do usuario dentro da entidade
		// Grupo
		grupo.getUsuariosGrupo().add(usuarioGrupo);
		for (UsuarioGrupo usrGrupo : grupo.getUsuariosGrupo()) {
			if (usrGrupo != null) {
				usrGrupo.getPerfil();
			}
		}

		return grupo;
	}

	@Override
	protected Grupo alterar(ManterGrupoAction request)
			throws ClassManagerException {
		Grupo grupo = request.getEntidade();
		grupo = getDao().alterar(grupo);
		return grupo;
	}

	@Override
	protected ListaDTO<Grupo> pesquisarLista(ManterGrupoAction request)
			throws ClassManagerException {
		List<Grupo> lista = getDao().pesquisarLista(request.getIdPesquisa(),
				request.getTituloPesquisa());
		return new ListaDTO<Grupo>(lista);
	}

}
