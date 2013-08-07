package br.com.classmanager.client.entidades.endereco;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Cacheable
@Entity
@Table(name = Pais.NOME_ENTIDADE)
public class Pais extends BeanJPA<Long> {

	private static final long serialVersionUID = 6444371295066319841L;

	public static final String NOME_ENTIDADE = "pais";

	public Pais() {
		super();
	}

	public Pais(Long id) {
		super(id);
	}

	@Id
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String nome;

	@Column(length = TamanhoCampo.TAMANHO_PEQUENO)
	private String sigla;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
