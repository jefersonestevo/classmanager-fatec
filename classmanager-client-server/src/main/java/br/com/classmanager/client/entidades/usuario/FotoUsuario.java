package br.com.classmanager.client.entidades.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.classmanager.client.entidades.def.BeanJPA;

@Entity
@Table(name = FotoUsuario.NOME_ENTIDADE)
@SequenceGenerator(name = "seq_foto_usuario", sequenceName = "seq_foto_usuario", initialValue = 1000)
public class FotoUsuario extends BeanJPA<Long> {

	private static final long serialVersionUID = 538789037702389025L;

	public static final String NOME_ENTIDADE = "foto_usuario";

	public FotoUsuario() {
		super();
	}

	@Id
	@GeneratedValue(generator = "seq_foto_usuario", strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	private byte[] foto;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
