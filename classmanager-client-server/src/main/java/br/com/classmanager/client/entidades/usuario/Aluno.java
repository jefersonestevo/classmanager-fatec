package br.com.classmanager.client.entidades.usuario;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoUsuario;

@Audited
@Entity
@Table(name = Aluno.NOME_ENTIDADE)
public class Aluno extends Usuario {

	private static final long serialVersionUID = -2101551908486857935L;

	public static final String NOME_ENTIDADE = "aluno";

	public Aluno() {
		super(TipoUsuario.ALUNO);
	}

	public Aluno(Long id) {
		super(id, TipoUsuario.ALUNO);
	}

}
