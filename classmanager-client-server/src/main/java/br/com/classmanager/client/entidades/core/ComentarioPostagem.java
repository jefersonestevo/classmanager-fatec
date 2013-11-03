package br.com.classmanager.client.entidades.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.utils.TamanhoCampo;

@Audited
@Entity
@Table(name = ComentarioPostagem.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_comentario_postagem", sequenceName = "seq_comentario_postagem", initialValue = 1000)
public class ComentarioPostagem extends BeanJPA<Long> {

	private static final long serialVersionUID = 287699595994882L;

	public static final String NOME_ENTIDADE = "comentario_postagem";

	public ComentarioPostagem() {
	}

	@Id
	@GeneratedValue(generator = "seq_comentario_postagem", strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postagem")
	private Postagem postagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_gerador")
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_geracao")
	private Date dataGeracao;

	@Column(length = TamanhoCampo.TAMANHO_GRANDE, nullable = false)
	private String descricao;

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

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

}
