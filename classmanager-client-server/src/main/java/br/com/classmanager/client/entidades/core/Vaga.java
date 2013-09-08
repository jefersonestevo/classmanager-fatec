package br.com.classmanager.client.entidades.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoPostagem;

@Audited
@Entity
@Table(name = Vaga.NOME_ENTIDADE)
public class Vaga extends Postagem {

	private static final long serialVersionUID = 1L;

	public static final String NOME_ENTIDADE = "vaga";

	public Vaga() {
		super(TipoPostagem.VAGA);
	}

	public Vaga(Long id) {
		super(id, TipoPostagem.VAGA);
	}

}
