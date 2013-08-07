package br.com.classmanager.client.entidades.geral;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Arquivo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_arquivo", sequenceName = "seq_arquivo", initialValue = 1000)
public class Arquivo extends BeanJPA<Long> {

	private static final long serialVersionUID = -6481644915438994171L;

	public static final String NOME_ENTIDADE = "arquivo";

	public Arquivo() {
		super();
	}

	public Arquivo(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_arquivo", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

	@Column(length = TamanhoCampo.TAMANHO_MUITO_PEQUENO)
	private String extensao;

	@NotNull
	@Lob
	@Column(name = "arquivo", nullable = false)
	@Basic(fetch = FetchType.LAZY)
	private byte[] arquivo;

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

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

}
