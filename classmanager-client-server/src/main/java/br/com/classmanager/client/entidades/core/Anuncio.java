package br.com.classmanager.client.entidades.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoPostagem;

@Audited
@Entity
@Table(name = Anuncio.NOME_ENTIDADE)
public class Anuncio extends Postagem {

	private static final long serialVersionUID = 1L;

	public static final String NOME_ENTIDADE = "anuncio";

	public Anuncio() {
		super(TipoPostagem.ANUNCIO);
	}

	public Anuncio(Long id) {
		super(id, TipoPostagem.ANUNCIO);
	}

}
