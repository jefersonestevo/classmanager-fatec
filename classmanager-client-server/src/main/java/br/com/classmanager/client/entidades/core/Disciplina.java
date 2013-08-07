package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.usuario.Professor;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Disciplina.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_disciplina", sequenceName = "seq_disciplina", initialValue = 1000)
public class Disciplina extends BeanJPA<Long> {

	private static final long serialVersionUID = -7859419023721178019L;

	public static final String NOME_ENTIDADE = "disciplina";

	public Disciplina() {
		super();
	}

	public Disciplina(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_disciplina", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

	@Column(length = TamanhoCampo.TAMANHO_GRANDE)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_disciplina_professor", joinColumns = @JoinColumn(name = "id_disciplina"), inverseJoinColumns = @JoinColumn(name = "id_professor"))
	private List<Professor> professores = new ArrayList<Professor>();

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

}
