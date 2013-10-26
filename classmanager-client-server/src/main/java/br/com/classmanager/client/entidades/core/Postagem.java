package br.com.classmanager.client.entidades.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.enums.TipoPostagem;
import br.com.classmanager.client.entidades.geral.Arquivo;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = Postagem.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_postagem", sequenceName = "seq_postagem", initialValue = 1000)
public class Postagem extends BeanJPA<Long> {

	private static final long serialVersionUID = 287640093730994882L;

	public static final String NOME_ENTIDADE = "postagem";

	protected Postagem() {
		super();
	}

	public Postagem(TipoPostagem tipoPostagem) {
		super();
		this.tipoPostagem = tipoPostagem;
	}

	public Postagem(Long id, TipoPostagem tipoPostagem) {
		super(id);
		this.tipoPostagem = tipoPostagem;
	}

	@Id
	@GeneratedValue(generator = "seq_postagem", strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_postagem")
	private TipoPostagem tipoPostagem;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_postagem_servico_envio", joinColumns = @JoinColumn(name = "id_postagem"), inverseJoinColumns = @JoinColumn(name = "id_servico_envio"))
	private List<ServicoEnvio> servicosUtilizados = new ArrayList<ServicoEnvio>();

	@Column(length = TamanhoCampo.TAMANHO_GRANDE, nullable = false)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rel_conteudo_arquivo", joinColumns = @JoinColumn(name = "id_conteudo"), inverseJoinColumns = @JoinColumn(name = "id_arquivo"))
	private List<Arquivo> listaArquivos = new ArrayList<Arquivo>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_grupo_gerador")
	private UsuarioGrupo usuarioGrupo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_geracao")
	private Date dataGeracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UsuarioGrupo getUsuarioGrupo() {
		return usuarioGrupo;
	}

	public void setUsuarioGrupo(UsuarioGrupo gerador) {
		this.usuarioGrupo = gerador;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public TipoPostagem getTipoPostagem() {
		return tipoPostagem;
	}

	public void setTipoPostagem(TipoPostagem tipoPostagem) {
		this.tipoPostagem = tipoPostagem;
	}

	public List<ServicoEnvio> getServicosUtilizados() {
		return servicosUtilizados;
	}

	public void setServicosUtilizados(List<ServicoEnvio> servicosUtilizados) {
		this.servicosUtilizados = servicosUtilizados;
	}

}
