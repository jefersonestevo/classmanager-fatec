package br.com.classmanager.client.entidades.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = PeriodoLetivo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_periodo_letivo", sequenceName = "seq_periodo_letivo", initialValue = 1000)
public class PeriodoLetivo extends BeanJPA<Long> {

	private static final long serialVersionUID = 9170252225077986871L;

	public static final String NOME_ENTIDADE = "periodo_letivo";

	public PeriodoLetivo() {
		super();
	}

	public PeriodoLetivo(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_periodo_letivo", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino")
	private Date dataTermino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

}
