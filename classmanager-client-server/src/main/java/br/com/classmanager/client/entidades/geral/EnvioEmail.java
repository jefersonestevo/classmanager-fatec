package br.com.classmanager.client.entidades.geral;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = EnvioEmail.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_envio_email", sequenceName = "seq_envio_email", initialValue = 1000)
public class EnvioEmail extends BeanJPA<Long> {

	private static final long serialVersionUID = 1082875044697839328L;

	public static final String NOME_ENTIDADE = "envio_email";

	public EnvioEmail() {
		super();
	}

	public EnvioEmail(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_envio_email", strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(targetEntity = Email.class, cascade = { CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name = "id_email", referencedColumnName = "id")
	private Email email;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
	@JoinColumn(name = "id_usuario_para")
	private Usuario para;

	@Column(name = "ultima_data_envio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaDataEnvio;

	@Column(name = "enviado_sucesso")
	private boolean emailEnviado = false;

	@Column(name = "qntd_tentativa_envio", nullable = false)
	private Integer quantidadeTentativasEnvio = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUltimaDataEnvio() {
		return ultimaDataEnvio;
	}

	public void setUltimaDataEnvio(Date ultimaDataEnvio) {
		this.ultimaDataEnvio = ultimaDataEnvio;
	}

	public Integer getQuantidadeTentativasEnvio() {
		return quantidadeTentativasEnvio;
	}

	public void setQuantidadeTentativasEnvio(Integer quantidadeTentativasEnvio) {
		this.quantidadeTentativasEnvio = quantidadeTentativasEnvio;
	}

	public boolean isEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(boolean emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public Usuario getPara() {
		return para;
	}

	public void setPara(Usuario para) {
		this.para = para;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
