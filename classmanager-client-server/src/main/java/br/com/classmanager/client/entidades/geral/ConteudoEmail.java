package br.com.classmanager.client.entidades.geral;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = ConteudoEmail.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_conteudo_email", sequenceName = "seq_conteudo_email", initialValue = 1000)
public class ConteudoEmail extends BeanJPA<Long> {

	private static final long serialVersionUID = 9009381144696452580L;

	public static final String NOME_ENTIDADE = "conteudo_email";

	public ConteudoEmail() {
		super();
	}

	public ConteudoEmail(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_conteudo_email", strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String assunto;

	@Column(length = TamanhoCampo.TAMANHO_MUITO_GRANDE)
	private String conteudo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
