package br.com.classmanager.client.android.utils;

import java.util.HashMap;
import java.util.Map;

public class Propriedades {

	private static Map<String, String> mapPropriedades;

	public static final String DOMINIO = "Classmanager_Dominio";
	public static final String CONTEXTO = "Classmanager_Contexto";

	public static String getDominio() {
		return getPropriedade(DOMINIO);
	}

	public static String getContexto() {
		return getPropriedade(CONTEXTO);
	}

	public static String getPropriedade(String chave) {
		return getMapPropriedades().get(chave);
	}

	private static Map<String, String> getMapPropriedades() {
		if (mapPropriedades == null) {
			synchronized (Propriedades.class) {
				if (mapPropriedades == null) {
					mapPropriedades = new HashMap<String, String>();
					mapPropriedades.put(DOMINIO, "10.0.2.2:8080");
					mapPropriedades.put(CONTEXTO, "classmanager-web");
				}
			}
		}
		return mapPropriedades;
	}

}
