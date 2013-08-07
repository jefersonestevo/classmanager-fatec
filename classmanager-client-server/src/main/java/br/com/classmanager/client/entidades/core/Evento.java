package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.componentes.validators.NotEmpty;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.endereco.Local;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = Evento.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_evento", sequenceName = "seq_evento", initialValue = 1000)
public class Evento extends BeanJPA<Long> {

	private static final long serialVersionUID = 287640093730994882L;

	public static final String NOME_ENTIDADE = "evento";

	public Evento() {
		super();
	}

	public Evento(Long id) {
		super(id);
	}

	@Id
	@GeneratedValue(generator = "seq_evento", strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(length = TamanhoCampo.TAMANHO_MEDIO, nullable = false)
	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ocorrencia")
	private Date dataOcorrencia;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_criador")
	private Usuario criador;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH,
			CascadeType.REMOVE })
	@JoinTable(name = "rel_evento_conteudo", joinColumns = @JoinColumn(name = "id_evento"), inverseJoinColumns = @JoinColumn(name = "id_conteudo"))
	private List<Conteudo> listaConteudos = new ArrayList<Conteudo>();

	@ManyToOne
	@JoinColumn(name = "id_local")
	private Local local;

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

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public List<Conteudo> getListaConteudos() {
		return listaConteudos;
	}

	public void setListaConteudos(List<Conteudo> listaConteudos) {
		this.listaConteudos = listaConteudos;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
