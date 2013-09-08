package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.enums.StatusGrupo;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Grupo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_grupo", sequenceName = "seq_grupo", initialValue = 1000)
public class Grupo extends BeanJPA<Long> {

	private static final long serialVersionUID = 1L;

	public static final String NOME_ENTIDADE = "grupo";

	public Grupo() {
		super();
	}

	public Grupo(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_grupo", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String titulo;

	@Column(length = TamanhoCampo.TAMANHO_GRANDE, nullable = false)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_criador")
	private Usuario usuarioCriador;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private StatusGrupo status;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_grupo_servico_envio_habilitado", joinColumns = @JoinColumn(name = "id_grupo"), inverseJoinColumns = @JoinColumn(name = "id_servico_envio"))
	private List<ServicoEnvio> servicosHabilitados = new ArrayList<ServicoEnvio>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	private List<UsuarioGrupo> usuariosGrupo = new ArrayList<UsuarioGrupo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<ServicoEnvio> getServicosHabilitados() {
		return servicosHabilitados;
	}

	public void setServicosHabilitados(List<ServicoEnvio> servicosHabilitados) {
		this.servicosHabilitados = servicosHabilitados;
	}

	public List<UsuarioGrupo> getUsuariosGrupo() {
		return usuariosGrupo;
	}

	public void setUsuariosGrupo(List<UsuarioGrupo> usuariosGrupo) {
		this.usuariosGrupo = usuariosGrupo;
	}

	public StatusGrupo getStatus() {
		return status;
	}

	public void setStatus(StatusGrupo status) {
		this.status = status;
	}

}
