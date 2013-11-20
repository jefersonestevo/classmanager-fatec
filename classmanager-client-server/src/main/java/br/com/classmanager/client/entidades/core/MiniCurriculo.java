package br.com.classmanager.client.entidades.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.classmanager.client.entidades.def.BeanJPA;

@Audited
@Entity
@Table(name = MiniCurriculo.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_mini_curriculo", sequenceName = "seq_mini_curriculo", initialValue = 1000)
public class MiniCurriculo extends BeanJPA<Long> {

	private static final long serialVersionUID = 287699595994882L;

	public static final String NOME_ENTIDADE = "mini_curriculo";

	public MiniCurriculo() {
	}

	@Id
	@GeneratedValue(generator = "seq_mini_curriculo", strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comentario_postagem")
	private ComentarioPostagem comentarioPostagem;

	@Column
	private String nome;

	@Column
	private String endereco;

	@Column
	private String email;

	@Column
	private String telefone;

	@Column(name = "pretensao_salarial")
	private String pretensaoSalarial;

	@Column(name = "descricao_geral")
	private String descricaoGeral;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ComentarioPostagem getComentarioPostagem() {
		return comentarioPostagem;
	}

	public void setComentarioPostagem(ComentarioPostagem comentarioPostagem) {
		this.comentarioPostagem = comentarioPostagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	public void setPretensaoSalarial(String pretensaoSalarial) {
		this.pretensaoSalarial = pretensaoSalarial;
	}

	public String getDescricaoGeral() {
		return descricaoGeral;
	}

	public void setDescricaoGeral(String descricaoGeral) {
		this.descricaoGeral = descricaoGeral;
	}

}
