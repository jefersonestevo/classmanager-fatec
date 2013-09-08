package br.com.classmanager.client.entidades.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoPostagem;

@Audited
@Entity
@Table(name = Aviso.NOME_ENTIDADE)
public class Aviso extends Postagem {

	private static final long serialVersionUID = 1L;

	public static final String NOME_ENTIDADE = "aviso";

	public Aviso() {
		super(TipoPostagem.AVISO);
	}

	public Aviso(Long id) {
		super(id, TipoPostagem.AVISO);
	}

}
