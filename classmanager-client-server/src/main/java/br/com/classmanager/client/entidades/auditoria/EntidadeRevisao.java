package br.com.classmanager.client.entidades.auditoria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.classmanager.client.entidades.def.IEntidade;

@Table(name = EntidadeRevisao.NOME_ENTIDADE)
@Entity
@RevisionEntity(ListenerRevisao.class)
@SequenceGenerator(name = "seq_historico_revisao", sequenceName = "seq_historico_revisao", initialValue = 1000)
public class EntidadeRevisao implements IEntidade<Long> {

	private static final long serialVersionUID = 6069410658943327800L;

	public static final String NOME_ENTIDADE = "historico_revisao";

	@Id
	@GeneratedValue(generator = "seq_historico_revisao")
	@RevisionNumber
	@Column(name = "revisao")
	private Long id;

	@RevisionTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_revisao")
	private Date timestamp;

	@Column(name = "id_usuario")
	private Long idUsuario;

	@Column(name = "nome_usuario")
	private String usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
