package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.geral.Arquivo;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Conteudo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_conteudo", sequenceName = "seq_conteudo", initialValue = 1000)
public class Conteudo extends BeanJPA<Long> {

	private static final long serialVersionUID = 287640093730994882L;

	public static final String NOME_ENTIDADE = "conteudo";

	public Conteudo() {
		super();
	}

	public Conteudo(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_conteudo", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String titulo;

	@Column(length = TamanhoCampo.TAMANHO_GRANDE, nullable = false)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_conteudo_arquivo", joinColumns = @JoinColumn(name = "id_conteudo"), inverseJoinColumns = @JoinColumn(name = "id_arquivo"))
	private List<Arquivo> listaArquivos = new ArrayList<Arquivo>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gerador")
	private Usuario gerador;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_geracao")
	private Date dataGeracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Arquivo> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<Arquivo> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public Usuario getGerador() {
		return gerador;
	}

	public void setGerador(Usuario gerador) {
		this.gerador = gerador;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

}
