package br.com.classmanager.web.mb.notificacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.dto.action.core.ConsultaUltimosGruposAtualizadosAction;
import br.com.classmanager.client.dto.core.GrupoAtualizado;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.SessionBean;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.notificacao.NotificacaoBase;
import br.com.classmanager.web.notificacao.NotificacaoPadrao;
import br.com.classmanager.web.notificacao.NotificacaoView;
import br.com.classmanager.web.service.ClassManagerServiceView;

@SessionScoped
@NotificacaoView
public class NotificacaoUltimosGruposAtualizados extends GenericManagedBean
		implements NotificacaoBase {

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
		return getMessage("Notificacao_Ultimos_Grupos_Atualizados_Header");
	}

	@Override
	public String getHeaderTabela() {
		return getMessage("Notificacao_Ultimos_Grupos_Atualizados_Header");
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
			if (StatusUsuarioGrupo.CRIADOR.equals(usuarioGrupo.getStatus())
					|| StatusUsuarioGrupo.PARTICIPANTE.equals(usuarioGrupo
							.getStatus())) {
				idsGrupo.add(usuarioGrupo.getGrupo().getId());
			}
		}

		try {
			ConsultaUltimosGruposAtualizadosAction consulta = new ConsultaUltimosGruposAtualizadosAction();
			consulta.setIdGrupos(new ArrayList<Long>(idsGrupo));

			List<GrupoAtualizado> gruposAtualizados = ((ListaDTO<GrupoAtualizado>) service
					.execute(consulta)).getLista();

			if (CollectionUtils.isNotEmpty(gruposAtualizados)) {
				for (GrupoAtualizado grupo : gruposAtualizados) {
					NotificacaoPadrao notif = new NotificacaoPadrao();
					notif.setId(grupo.getGrupo().getId());
					notif.setTitulo(grupo.getGrupo().getTitulo());
					notif.setUltimaAtualizacao(grupo.getUltimaAtualizacao());
					this.listaNotificacao.add(notif);
				}
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
