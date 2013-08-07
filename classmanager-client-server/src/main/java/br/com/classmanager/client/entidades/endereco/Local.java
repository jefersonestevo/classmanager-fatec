package br.com.classmanager.client.entidades.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Local.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_local", sequenceName = "seq_local", initialValue = 1000)
public class Local extends BeanJPA<Long> {

	private static final long serialVersionUID = 6444371295066319841L;

	public static final String NOME_ENTIDADE = "local";

	public Local() {
		super();
	}

	public Local(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_local", strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = TamanhoCampo.TAMANHO_MEDIO)
	private String titulo;

	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
