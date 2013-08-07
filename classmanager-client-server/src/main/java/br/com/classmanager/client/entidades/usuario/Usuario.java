package br.com.classmanager.client.entidades.usuario;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.EmailValido;
import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.enums.Sexo;
import br.com.classmanager.client.entidades.enums.TipoUsuario;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = Usuario.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1000)
public class Usuario extends BeanJPA<Long> {

	private static final long serialVersionUID = 538789037702389025L;

	public static final String NOME_ENTIDADE = "usuario";

	public Usuario() {
		super();
	}

	public Usuario(TipoUsuario tipo) {
		super();
		this.tipo = tipo;
	}

	public Usuario(Long id, TipoUsuario tipo) {
		super(id);
		this.tipo = tipo;
	}

	@Id
	@GeneratedValue(generator = "seq_usuario", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String nome;

	@Column(name = "sobre_nome", length = TamanhoCampo.TAMANHO_MEDIO)
	private String sobreNome;

	@NotEmpty
	@EmailValido
	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String email;

	@Enumerated(EnumType.ORDINAL)
	@Column
	private TipoUsuario tipo;

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

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
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

}
