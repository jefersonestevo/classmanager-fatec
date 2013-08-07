package br.com.classmanager.client.entidades.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = PeriodoEstudo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_periodo_estudo", sequenceName = "seq_periodo_estudo", initialValue = 1000)
public class PeriodoEstudo extends BeanJPA<Long> {

	private static final long serialVersionUID = -1749582516124107336L;

	public static final String NOME_ENTIDADE = "periodo_estudo";

	public PeriodoEstudo() {
		super();
	}

	public PeriodoEstudo(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_periodo_estudo", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

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

}
