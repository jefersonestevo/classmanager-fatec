package br.com.classmanager.web.componentes.comparators;

import java.util.Comparator;

import br.com.classmanager.client.entidades.core.ComentarioPostagem;

public class ComparadorComentarioPostagemPorDataGeracao implements
		Comparator<ComentarioPostagem> {

	@Override
	public int compare(ComentarioPostagem cp1, ComentarioPostagem cp2) {
		if (cp1 == null && cp2 != null)
			return -1;
		if (cp1 != null && cp2 == null)
			return 1;
		if (cp1 == null && cp2 == null)
			return 0;

		if (cp1.getDataGeracao() == null && cp2.getDataGeracao() != null)
			return -1;
		if (cp1.getDataGeracao() != null && cp2.getDataGeracao() == null)
			return 1;
		if (cp1.getDataGeracao() == null && cp2.getDataGeracao() == null)
			return 0;

		return cp2.getDataGeracao().compareTo(cp1.getDataGeracao());
	}

}
