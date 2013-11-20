package br.com.classmanager.core.utils;

import java.util.Comparator;

import br.com.classmanager.client.dto.core.GrupoAtualizado;

public class GrupoAtualizadoComparator implements Comparator<GrupoAtualizado> {

	@Override
	public int compare(GrupoAtualizado g1, GrupoAtualizado g2) {
		if (g1 == null && g2 != null)
			return 1;
		if (g1 != null && g2 == null)
			return -1;
		if (g1 == null && g2 == null)
			return 0;

		if (g1.getUltimaAtualizacao() == null
				&& g2.getUltimaAtualizacao() != null)
			return 1;
		if (g1.getUltimaAtualizacao() != null
				&& g2.getUltimaAtualizacao() == null)
			return -1;
		if (g1.getUltimaAtualizacao() == null
				&& g2.getUltimaAtualizacao() == null)
			return 0;

		return g2.getUltimaAtualizacao().compareTo(g1.getUltimaAtualizacao());

	}

}
