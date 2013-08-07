package br.com.classmanager.client.entidades.usuario;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoUsuario;

@Audited
@Entity
@Table(name = Administrador.NOME_ENTIDADE)
public class Administrador extends Usuario {

	private static final long serialVersionUID = -3835012374391988827L;

	public static final String NOME_ENTIDADE = "administrador";

	public Administrador() {
		super(TipoUsuario.ADM);
	}

	public Administrador(Long id) {
		super(id, TipoUsuario.ADM);
	}

}
