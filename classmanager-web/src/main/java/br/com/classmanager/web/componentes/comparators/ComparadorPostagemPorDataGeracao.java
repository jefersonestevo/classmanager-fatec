package br.com.classmanager.web.componentes.comparators;

import java.util.Comparator;

import br.com.classmanager.client.entidades.core.Postagem;

public class ComparadorPostagemPorDataGeracao implements Comparator<Postagem> {

	@Override
	public int compare(Postagem p1, Postagem p2) {
		if (p1 == null && p2 != null)
			return -1;
		if (p1 != null && p2 == null)
			return 1;
		if (p1 == null && p2 == null)
			return 0;

		if (p1.getDataGeracao() == null && p2.getDataGeracao() != null)
			return -1;
		if (p1.getDataGeracao() != null && p2.getDataGeracao() == null)
			return 1;
		if (p1.getDataGeracao() == null && p2.getDataGeracao() == null)
			return 0;

		return p2.getDataGeracao().compareTo(p1.getDataGeracao());
	}

}
