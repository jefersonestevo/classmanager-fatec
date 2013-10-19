package br.com.classmanager.client.entidades.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public enum TipoPostagem {
	CONTEUDO, //
	AVISO, //
	VAGA, //
	;

	public static TipoPostagem getTipoPostagem(String valor) {
		if (StringUtils.isBlank(valor))
			return null;
		return getTipoPostagem(Integer.valueOf(valor));
	}

	public static TipoPostagem getTipoPostagem(Integer valor) {
		if (valor == null)
			return null;
		for (TipoPostagem tipo : values()) {
			if (tipo.ordinal() == valor) {
				return tipo;
			}
		}
		return null;
	}

	public static List<Integer> getValores() {
		List<Integer> listaIds = new ArrayList<Integer>();
		for (TipoPostagem tipo : values()) {
			listaIds.add(tipo.ordinal());
		}
		return listaIds;
	}
}
