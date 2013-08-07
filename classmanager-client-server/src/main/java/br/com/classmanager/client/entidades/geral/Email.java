package br.com.classmanager.client.entidades.geral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.usuario.Usuario;

@Audited
@Entity
@Table(name = Email.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_email", sequenceName = "seq_email", initialValue = 1000)
public class Email extends BeanJPA<Long> {

	private static final long serialVersionUID = 7224159191865364348L;

	public static final String NOME_ENTIDADE = "email";

	public Email() {
		super();
	}

	public Email(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_email", strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "data_envio_original", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvioOriginal;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
	@JoinColumn(name = "id_usuario_de")
	private Usuario de;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ConteudoEmail.class)
	@JoinColumn(name = "id_conteudo_email")
	private ConteudoEmail conteudoEmail;

	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "email")
	private List<EnvioEmail> listaEnvios = new ArrayList<EnvioEmail>();

	@ManyToMany(targetEntity = Arquivo.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "rel_email_anexo", joinColumns = @JoinColumn(name = "id_email", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_arquivo", referencedColumnName = "id"))
	private List<Arquivo> listaAnexos = new ArrayList<Arquivo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEnvioOriginal() {
		return dataEnvioOriginal;
	}

	public void setDataEnvioOriginal(Date dataEnvioOriginal) {
		this.dataEnvioOriginal = dataEnvioOriginal;
	}

	public Usuario getDe() {
		return de;
	}

	public void setDe(Usuario de) {
		this.de = de;
	}

	public ConteudoEmail getConteudoEmail() {
		return conteudoEmail;
	}

	public void setConteudoEmail(ConteudoEmail conteudoEmail) {
		this.conteudoEmail = conteudoEmail;
	}

	public List<Arquivo> getListaAnexos() {
		return listaAnexos;
	}

	public void setListaAnexos(List<Arquivo> listaAnexos) {
		this.listaAnexos = listaAnexos;
	}

	public List<EnvioEmail> getListaEnvios() {
		return listaEnvios;
	}

	public void setListaEnvios(List<EnvioEmail> listaEnvios) {
		this.listaEnvios = listaEnvios;
	}

}
