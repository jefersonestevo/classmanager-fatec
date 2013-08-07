package br.com.classmanager.client.dto.def;

import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoPesquisar;

public abstract class DTOPesquisarAction implements DTOAction {

	private static final long serialVersionUID = 6795442425952395458L;

	protected Usuario usuarioAtual;
	protected AcaoPesquisar acao;

	public DTOPesquisarAction(AcaoPesquisar acao) {
		super();
		this.acao = acao;
	}

	public AcaoPesquisar getAcao() {
		return acao;
	}

	public void setAcao(AcaoPesquisar acao) {
		this.acao = acao;
	}

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

}
