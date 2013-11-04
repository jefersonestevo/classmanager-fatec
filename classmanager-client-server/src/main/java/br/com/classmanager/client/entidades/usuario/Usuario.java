package br.com.classmanager.client.entidades.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.enums.PerfilUsuario;
import br.com.classmanager.client.entidades.enums.Sexo;
import br.com.classmanager.client.entidades.enums.StatusUsuario;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Usuario.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1000)
public class Usuario extends BeanJPA<Long> {

	private static final long serialVersionUID = 538789037702389025L;

	public static final String NOME_ENTIDADE = "usuario";

	public Usuario() {
		super();
	}

	@Id
	@GeneratedValue(generator = "seq_usuario", strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String nome;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String email;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String login;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String senha;

	@Enumerated(EnumType.ORDINAL)
	@Column
	private PerfilUsuario perfilUsuario;

	@Enumerated(EnumType.ORDINAL)
	@Column
	private StatusUsuario statusUsuario;

	@Enumerated(EnumType.ORDINAL)
	@Column
	private Sexo sexo;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Column(length = TamanhoCampo.TAMANHO_PEQUENO)
	private String telefone;

	@Column(length = TamanhoCampo.TAMANHO_PEQUENO)
	private String celular1;

	@Column(length = TamanhoCampo.TAMANHO_PEQUENO)
	private String celular2;

	@NotAudited
	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.LAZY, mappedBy = "usuario")
	@JoinColumn(name = "id_foto_usuario")
	private FotoUsuario fotoUsuario;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "rel_usuario_servico_envio", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_servico_envio"))
	private List<ServicoEnvio> servicosHabilitados = new ArrayList<ServicoEnvio>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular1() {
		return celular1;
	}

	public void setCelular1(String celular1) {
		this.celular1 = celular1;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public List<ServicoEnvio> getServicosHabilitados() {
		return servicosHabilitados;
	}

	public void setServicosHabilitados(List<ServicoEnvio> servicosHabilitados) {
		this.servicosHabilitados = servicosHabilitados;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public FotoUsuario getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(FotoUsuario fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public StatusUsuario getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusUsuario statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

}
