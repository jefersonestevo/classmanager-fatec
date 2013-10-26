package br.com.classmanager.client.entidades.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.enums.PerfilUsuarioGrupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.entidades.usuario.Usuario;

@Audited
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = UsuarioGrupo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_usuario_grupo", sequenceName = "seq_usuario_grupo", initialValue = 1000)
public class UsuarioGrupo extends BeanJPA<Long> {

	private static final long serialVersionUID = 287640093730994882L;

	public static final String NOME_ENTIDADE = "usuario_grupo";

	public UsuarioGrupo() {
		super();
	}

	public UsuarioGrupo(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_usuario_grupo", strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private StatusUsuarioGrupo status;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "perfil_grupo")
	private PerfilUsuarioGrupo perfil;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_solicitacao_entrada_grupo")
	private Date dataSolicitacaoEntradaGrupo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_efetiva_entrada_grupo")
	private Date dataEfetivaEntradaGrupo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public StatusUsuarioGrupo getStatus() {
		return status;
	}

	public void setStatus(StatusUsuarioGrupo status) {
		this.status = status;
	}

	public Date getDataSolicitacaoEntradaGrupo() {
		return dataSolicitacaoEntradaGrupo;
	}

	public void setDataSolicitacaoEntradaGrupo(Date dataSolicitacaoEntradaGrupo) {
		this.dataSolicitacaoEntradaGrupo = dataSolicitacaoEntradaGrupo;
	}

	public Date getDataEfetivaEntradaGrupo() {
		return dataEfetivaEntradaGrupo;
	}

	public void setDataEfetivaEntradaGrupo(Date dataEfetivaEntradaGrupo) {
		this.dataEfetivaEntradaGrupo = dataEfetivaEntradaGrupo;
	}

	public PerfilUsuarioGrupo getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioGrupo perfil) {
		this.perfil = perfil;
	}

	public Integer getStatusNumber() {
		if (getStatus() == null) {
			return null;
		}
		return getStatus().ordinal();
	}

}
