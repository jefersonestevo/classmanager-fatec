package br.com.classmanager.client.dto.def;

import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;

public abstract class DTOManterAction implements DTOAction {

	private static final long serialVersionUID = -2512500153161968311L;

	protected Usuario usuarioAtual;
	protected AcaoManter acao;

	public DTOManterAction(AcaoManter acao) {
		super();
		this.acao = acao;
	}

	public AcaoManter getAcao() {
		return acao;
	}

	public void setAcao(AcaoManter acao) {
		this.acao = acao;
	}

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

}
