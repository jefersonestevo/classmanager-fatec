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
@Table(name = Cidade.NOME_ENTIDADE)
public class Cidade extends BeanJPA<Long> {

	private static final long serialVersionUID = -5552214899174947948L;

	public static final String NOME_ENTIDADE = "cidade";

	public Cidade() {
		super();
	}

	public Cidade(Long id) {
		super(id);
	}

	@Id
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
