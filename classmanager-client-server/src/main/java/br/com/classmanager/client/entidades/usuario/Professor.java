package br.com.classmanager.client.entidades.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.core.Disciplina;
import br.com.classmanager.client.entidades.enums.TipoUsuario;

@Audited
@Entity
@Table(name = Professor.NOME_ENTIDADE)
public class Professor extends Usuario {

	private static final long serialVersionUID = -2101551908486857935L;

	public static final String NOME_ENTIDADE = "professor";

	public Professor() {
		super(TipoUsuario.PROFESSOR);
	}

	public Professor(Long id) {
		super(id, TipoUsuario.PROFESSOR);
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_disciplina_professor", joinColumns = @JoinColumn(name = "id_professor"), inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
