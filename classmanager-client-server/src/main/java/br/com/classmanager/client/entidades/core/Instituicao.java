package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.endereco.Local;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Instituicao.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_instituicao", sequenceName = "seq_instituicao", initialValue = 1000)
public class Instituicao extends BeanJPA<Long> {

	private static final long serialVersionUID = 6815736621994382839L;

	public static final String NOME_ENTIDADE = "instituicao";

	public Instituicao() {
		super();
	}

	public Instituicao(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_instituicao", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "instituicao")
	private List<PeriodoLetivo> listaPeriodosLetivos = new ArrayList<PeriodoLetivo>();

	@ManyToOne
	@JoinColumn(name = "id_local")
	private Local local;

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

	public List<PeriodoLetivo> getListaPeriodosLetivos() {
		return listaPeriodosLetivos;
	}

	public void setListaPeriodosLetivos(List<PeriodoLetivo> listaPeriodosLetivos) {
		this.listaPeriodosLetivos = listaPeriodosLetivos;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}
