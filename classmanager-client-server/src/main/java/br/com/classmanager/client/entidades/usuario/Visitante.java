package br.com.classmanager.client.entidades.usuario;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoUsuario;

@Audited
@Entity
@Table(name = Visitante.NOME_ENTIDADE)
public class Visitante extends Usuario {

	private static final long serialVersionUID = 1621734722647002311L;

	public static final String NOME_ENTIDADE = "visitante";

	public Visitante() {
		super(TipoUsuario.VISITANTE);
	}

	public Visitante(Long id) {
		super(id, TipoUsuario.VISITANTE);
	}

}
