package br.com.classmanager.client.entidades.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Entity
@Table(name = ServicoEnvio.NOME_ENTIDADE)
public class ServicoEnvio extends BeanJPA<Long> {

	private static final long serialVersionUID = 2365094586648461115L;

	public static final String NOME_ENTIDADE = "servico_envio";

	public static final Long SERVICO_EMAIL = 1l;
	public static final Long SERVICO_SMS = 2l;

	public ServicoEnvio() {
		super();
	}

	public ServicoEnvio(Long id) {
		super(id);
	}

	@Id
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

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

}
