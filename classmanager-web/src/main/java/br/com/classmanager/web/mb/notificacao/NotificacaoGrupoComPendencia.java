package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.dto.action.core.ConsultaGrupoComSolicitacaoPendenteAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.PerfilUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.utils.CMCollectionUtils;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoPadrao;
import br.com.classmanager.web.notificacao.NotificacaoView;
import br.com.classmanager.web.service.ClassManagerServiceView;

@SessionScoped
@NotificacaoView
public class NotificacaoGrupoComPendencia extends GenericManagedBean implements
		NotificacaoBase {

	private static final long serialVersionUID = -7970955670574091916L;

	private List<NotificacaoPadrao> listaNotificacao;
	private Long idSelecionado;
	private Date ultimaAtualizacao;

	@Inject
	private SessionBean sessionBean;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	@Override
	public String getHeader() {
		return getMessage("Notificacao_Grupo_Com_Pendencia_Header");
	}

	@Override
	public String getHeaderTabela() {
		return getMessage("Notificacao_Grupo_Com_Pendencia_Header");
	}

	@Override
	public List<NotificacaoPadrao> getListaNotificacao() {
		atualizar();
		return listaNotificacao;
	}

	public void atualizar() {
		this.listaNotificacao = new ArrayList<NotificacaoPadrao>();

		Set<Long> idsGrupo = new HashSet<Long>();
		for (UsuarioGrupo usuarioGrupo : sessionBean.getListaGrupos()) {
			if (PerfilUsuarioGrupo.ADM.equals(usuarioGrupo.getPerfil())
					&& (StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo
							.getStatus()) || StatusUsuarioGrupo.PARTICIPANTE
							.equals(usuarioGrupo.getStatus()))) {
				idsGrupo.add(usuarioGrupo.getGrupo().getId());
			}
		}

		try {
			ConsultaGrupoComSolicitacaoPendenteAction consulta = new ConsultaGrupoComSolicitacaoPendenteAction();
			consulta.setIdGruposConsulta(new ArrayList<Long>(idsGrupo));

			List<Grupo> gruposComPendencia = ((ListaDTO<Grupo>) service
					.execute(consulta)).getLista();

			if (CollectionUtils.isNotEmpty(gruposComPendencia)) {
				for (Grupo grupo : gruposComPendencia) {
					NotificacaoPadrao notif = new NotificacaoPadrao();
					notif.setId(grupo.getId());
					notif.setTitulo(grupo.getTitulo());
					this.listaNotificacao.add(notif);
				}
				CMCollectionUtils.ordenarLista(this.listaNotificacao,
						new String[] { "titulo" });
			}
		} catch (ClassManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ultimaAtualizacao = new Date();
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
