package br.com.classmanager.client.entidades.endereco;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Cacheable
@Entity
@Table(name = Estado.NOME_ENTIDADE)
public class Estado extends BeanJPA<Long> {

	private static final long serialVersionUID = -5552214899174947948L;

	public static final String NOME_ENTIDADE = "estado";

	public Estado() {
		super();
	}

	public Estado(Long id) {
		super(id);
	}

	@Id
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String nome;

	@Column(length = TamanhoCampo.TAMANHO_PEQUENO)
	private String uf;

	@ManyToOne
	@JoinColumn(name = "id_pais")
	private Pais pais;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
