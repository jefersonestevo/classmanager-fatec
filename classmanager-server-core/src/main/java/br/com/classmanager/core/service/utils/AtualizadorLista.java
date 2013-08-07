package br.com.classmanager.core.service.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.classmanager.client.exceptions.ClassManagerException;

public abstract class AtualizadorLista<ENT> implements Serializable {

	private static final long serialVersionUID = 6302500820173624388L;

	// 10 Minutos
	private static final long TEMPO_PADRAO = 10 * 60 * 60 * 1000;

	private List<ENT> lista;
	protected Date ultimaAtualizacao;

	/**
	 * Metodo que retornara uma nova lista para atualizacao da lista atual
	 * 
	 * @return
	 * @throws ClassManagerException
	 */
	protected abstract List<ENT> getListaNova() throws ClassManagerException;

	protected long getTempoExpiracao() {
		return TEMPO_PADRAO;
	}

	public List<ENT> getLista() throws ClassManagerException {
		Date data = new Date();
		Date prazoExpiracao = null;
		if (this.ultimaAtualizacao != null) {
			prazoExpiracao = new Date(this.ultimaAtualizacao.getTime()
					+ getTempoExpiracao());
		}

		if (this.lista == null || this.ultimaAtualizacao == null
				|| data.after(prazoExpiracao)) {
			synchronized (this) {
				if (this.lista == null || this.ultimaAtualizacao == null
						|| data.after(prazoExpiracao)) {
					this.lista = getListaNova();
				}
			}
		}
		return new ArrayList<ENT>(this.lista);
	}

}
