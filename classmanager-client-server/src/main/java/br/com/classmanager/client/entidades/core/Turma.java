package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.endereco.Local;
import br.com.classmanager.client.entidades.usuario.Aluno;
import br.com.classmanager.client.entidades.usuario.Professor;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Turma.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_turma", sequenceName = "seq_turma", initialValue = 1000)
public class Turma extends BeanJPA<Long> {

	private static final long serialVersionUID = 6152725252661766647L;

	public static final String NOME_ENTIDADE = "turma";

	public Turma() {
		super();
	}

	public Turma(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_turma", strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_turma_professor", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_professor"))
	private List<Professor> listaProfessores = new ArrayList<Professor>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_turma_aluno", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	private List<Aluno> listaAlunos = new ArrayList<Aluno>();

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periodo_estudo")
	private PeriodoEstudo periodoEstudo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periodo_letivo")
	private PeriodoLetivo periodoLetivo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;

	@ManyToOne
	@JoinColumn(name = "id_local")
	private Local local;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "rel_turma_evento", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_evento"))
	private List<Evento> listaEventos = new ArrayList<Evento>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PeriodoEstudo getPeriodoEstudo() {
		return periodoEstudo;
	}

	public void setPeriodoEstudo(PeriodoEstudo periodoEstudo) {
		this.periodoEstudo = periodoEstudo;
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
