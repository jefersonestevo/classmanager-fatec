package br.com.classmanager.client.entidades.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.enums.TipoLogradouro;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Endereco.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", initialValue = 1000)
public class Endereco extends BeanJPA<Long> {

	private static final long serialVersionUID = 8627152489687644335L;

	public static final String NOME_ENTIDADE = "endereco";

	public Endereco() {
		super();
	}

	public Endereco(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_endereco", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String logradouro;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_logradouro")
	private TipoLogradouro tipoLogradouro;

	@Column(length = TamanhoCampo.TAMANHO_PEQUENO, nullable = false)
	private String numero;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String complemento;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

}
