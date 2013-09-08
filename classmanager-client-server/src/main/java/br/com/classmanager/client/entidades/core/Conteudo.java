package br.com.classmanager.client.entidades.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.enums.TipoPostagem;

@Audited
@Entity
@Table(name = Conteudo.NOME_ENTIDADE)
public class Conteudo extends Postagem {

	private static final long serialVersionUID = 1L;

	public static final String NOME_ENTIDADE = "conteudo";

	public Conteudo() {
		super(TipoPostagem.CONTEUDO);
	}

	public Conteudo(Long id) {
		super(id, TipoPostagem.CONTEUDO);
	}

}
