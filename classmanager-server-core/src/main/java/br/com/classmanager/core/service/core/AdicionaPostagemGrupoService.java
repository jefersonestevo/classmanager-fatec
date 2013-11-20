package br.com.classmanager.core.service.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AdicionaPostagemGrupoAction;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.service.email.TemplateEmailMapper;
import br.com.classmanager.core.service.servicoenvio.ServicoEnvioEmailVO;
import br.com.classmanager.core.service.servicoenvio.ServicoEnvioSMSVO;
import br.com.classmanager.core.service.servicoenvio.ServicoEnvioUtils;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoServicoEnvio;
import br.com.classmanager.server.domain.service.Servico;

@Named("AdicionaPostagemGrupoAction")
public class AdicionaPostagemGrupoService extends
		Servico<AdicionaPostagemGrupoAction, Postagem> {

	@Inject
	@DAO
	private IDaoPostagem daoPostagem;

	@Inject
	@DAO
	private IDaoServicoEnvio daoServicoEnvio;

	@Inject
	@DAO
	private IDaoGrupo daoGrupo;

	@Inject
	private Event<ServicoEnvioEmailVO> servicoEmail;

	@Inject
	private Event<ServicoEnvioSMSVO> servicoSMS;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Postagem execute(AdicionaPostagemGrupoAction request)
			throws ClassManagerException {

		Postagem postagem = request.getPostagem();
		postagem.setUsuario(request.getUsuarioAtual());
		postagem.setGrupo(request.getGrupo());
		postagem.setDataGeracao(new Date());

		postagem.setServicosUtilizados(new ArrayList<ServicoEnvio>());
		for (Long idServ : request.getServicosEnvio()) {
			if (idServ != null) {
				ServicoEnvio servico = daoServicoEnvio.pesquisar(idServ);
				postagem.getServicosUtilizados().add(servico);
			}
		}

		if (!TipoPostagem.VAGA.equals(postagem.getTipoPostagem())) {
			postagem.setCargo(null);
			postagem.setEndereco(null);
			postagem.setDataInicio(null);
			postagem.setContato(null);
		}

		postagem.setUltimaAtualizacao(new Date());
		daoPostagem.inserir(postagem);

		Grupo grupo = daoGrupo.pesquisar(postagem.getGrupo().getId());
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		for (UsuarioGrupo usuarioGrupo : grupo.getUsuariosGrupo()) {
			if (StatusUsuarioGrupo.PARTICIPANTE
					.equals(usuarioGrupo.getStatus())
					|| StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo
							.getStatus())) {
				listaUsuarios.add(usuarioGrupo.getUsuario());
			}
		}

		Map<String, String> atributos = new HashMap<String, String>();
		atributos.put("login", request.getUsuarioAtual().getNome());
		atributos.put("grupo", grupo.getTitulo());

		for (ServicoEnvio servico : postagem.getServicosUtilizados()) {
			if (ServicoEnvio.SERVICO_EMAIL.equals(servico.getId())) {
				ServicoEnvioEmailVO servEmailVO = new ServicoEnvioEmailVO();
				servEmailVO.setTemplate(TemplateEmailMapper.CRIACAO_POST_NOVO);
				servEmailVO.setAtributos(atributos);
				servEmailVO.setEmails(ServicoEnvioUtils
						.extrairEmails(listaUsuarios));
				servicoEmail.fire(servEmailVO);
			} else if (ServicoEnvio.SERVICO_SMS.equals(servico.getId())) {
				// TODO - Implementar
			}
		}

		return postagem;
	}
}
