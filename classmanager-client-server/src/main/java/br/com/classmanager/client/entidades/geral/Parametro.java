package br.com.classmanager.client.entidades.geral;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.enums.EnumParametro;
import br.com.classmanager.client.utils.TamanhoCampo;

@Cacheable
@Entity
@Table(name = Parametro.NOME_ENTIDADE)
public class Parametro extends BeanJPA<EnumParametro> {

	private static final long serialVersionUID = 2365094586648461115L;

	public static final String NOME_ENTIDADE = "parametro";

	public Parametro() {
		super();
	}

	public Parametro(EnumParametro id) {
		super(id);
	}

	@Id
	@Enumerated(EnumType.ORDINAL)
	private EnumParametro id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_GRANDE, nullable = false)
	private String valor;

	public EnumParametro getId() {
		return id;
	}

	public void setId(EnumParametro id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
