package br.com.classmanager.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CMCollectionUtils {
	private static Logger log = Logger.getLogger(CMCollectionUtils.class);

	public static final int ORDENACAO_CRESCENTE = 1;
	public static final int ORDENACAO_DECRESCENTE = -1;

	/**
	 * Ordena uma lista de acordo com os parametros exigidos.
	 * 
	 * @param lista
	 *            - List<? extends Object> - Lista a ser ordenada.
	 * @param parametros
	 *            - String[] - Array de Strings contendo os parametros para a
	 *            ordenação.
	 * @param ordenacao
	 *            - int - Use as estaticas para referenciar a ordem de ordenacao
	 *            desejado
	 * @author César Voginski
	 * @since 10/05/2011
	 * @return List<?> - Lista ordenada de acordo com os parametros.
	 */
	public static void ordenarLista(List<? extends Object> lista,
			String[] parametros, int ordenacao) {
		if (!verificarCondicoes(lista, parametros, ordenacao)) {
			if (CollectionUtils.isNotEmpty(lista)) {
				String message = new Formatter()
						.format("A lista requisitada (tipo %s) para ordenação não possui as condições necessárias para realizar esta ação\nParâmetros: %s",
								lista.get(0).getClass().getName(),
								ArrayUtils.toString(parametros,
										"(sem parâmetros)")).toString();
				log.debug(message);
			}
			return;
		}

		Collections.sort(lista,
				new ComparatorPropriedade(parametros, ordenacao));
	}

	/**
	 * Ordena uma lista de acordo com os parametros exigidos.
	 * 
	 * @param lista
	 *            - List<? extends Object> - Lista a ser ordenada.
	 * @param parametros
	 *            - String[] - Array de Strings contendo os parametros para a
	 *            ordenação.
	 * @author César Voginski
	 * @since 10/05/2011
	 */
	public static void ordenarLista(List<? extends Object> lista,
			String[] parametros) {
		ordenarLista(lista, parametros, CMCollectionUtils.ORDENACAO_CRESCENTE);
	}

	/**
	 * Ordena uma lista de acordo com os parametros exigidos.
	 * 
	 * @param lista
	 *            - List<? extends Object> - Lista a ser ordenada.
	 * @param parametros
	 *            - String - String contendo o parametro para a ordenação.
	 * @author César Voginski
	 * @since 10/05/2011
	 */
	public static void ordenarLista(List<? extends Object> lista,
			String parametro) {
		ordenarLista(lista, new String[] { parametro },
				CMCollectionUtils.ORDENACAO_CRESCENTE);
	}

	/**
	 * Ordena uma lista que implementa a interface Comparable.
	 * 
	 * @param lista
	 *            - List<? extends Object> - Lista a ser ordenada.
	 * @author César Voginski
	 * @since 12/05/2011
	 */
	public static <T extends Comparable<? super T>> void ordenarLista(
			List<T> lista) {
		if (CollectionUtils.isEmpty(lista))
			return;

		if (lista.get(0) instanceof String)
			Collections.sort(lista, new ComparatorStringMurah());
		else
			Collections.sort(lista);
	}

	/**
	 * Verifica se os parametros sao suficientes para usar a ordenacao do
	 * ComparatorPropriedade.
	 * 
	 * @return True - Se os parametros estao corretos, False - se incorretos.
	 */
	private static boolean verificarCondicoes(List<? extends Object> lista,
			String[] parametros, int ordenacao) {
		if (lista == null || parametros == null)
			return false;
		if (lista.size() < 1)
			return false;
		if (parametros.length < 1)
			return false;
		if (ordenacao != ORDENACAO_CRESCENTE
				&& ordenacao != ORDENACAO_DECRESCENTE)
			return false;

		return true;
	}
}

class ComparatorStringMurah implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		if (o1 instanceof String) {
			Collator collator = Collator.getInstance(new Locale("pt", "BR"));
			return (collator.compare((String) o1, (String) o2));
		}
		return 0;
	}

}

class ComparatorPropriedade implements Comparator<Object> {
	private static Logger log = Logger.getLogger(ComparatorPropriedade.class);

	private String[] parametros;
	private int sinal = CMCollectionUtils.ORDENACAO_CRESCENTE;
	private Collator collator = Collator.getInstance(new Locale("pt", "BR"));
	private List<Method[]> listaChamadas;
	private static final String HIBERNATE_PROXY_SUFFIX = "_$$_javassist";

	public ComparatorPropriedade(String[] parametros, int ordenacao) {
		this.parametros = parametros;
		this.sinal = ordenacao;
	}

	public int compare(Object o1, Object o2) {
		return compareControle(o1, o2, 0);
	}

	/**
	 * Metodo responsavel pela logica da comparacao Este metodo foi criado para
	 * poder manipular os parametros.
	 * 
	 * @param o1
	 *            - Object - Objeto que faz a comparacao
	 * @param o2
	 *            - Ocject - Objeto a ser comparado
	 * @param param
	 *            - indice do paramatros
	 * @since 12/05/2011
	 * @author Cesar Voginski
	 * @return
	 */
	private int compareControle(Object o1, Object o2, int param) {
		int resultComparado = 0;

		if (o1 == null)
			return 1;
		if (o2 == null)
			return -1;

		if (!o1.getClass().equals(o2.getClass()))
			if (!o1.getClass().getSuperclass().equals(o2.getClass()))
				return 0;

		if (listaChamadas == null)
			listaChamadas = getListaMetodos(o1);
		try {
			Object result1 = getResultado(o1, param);
			Object result2 = getResultado(o2, param);

			if (result1 == null || result2 == null)
				return 0;

			resultComparado = comparar(result1, result2);

			if (resultComparado == 0) {
				if (param >= (parametros.length - 1)) {
					param = 0;
					return 0;
				}

				return compareControle(o1, o2, ++param);
			} else {
				param = 0;
				return resultComparado;
			}
		} catch (Exception e) {
			// Não precisa de Log.
		}

		param = 0;
		return 0;
	}

	/**
	 * Transforma uma string para um nome de metodo getter.
	 * 
	 * @param param1
	 * @author César Voginski
	 * @return String
	 * @since 12/05/2011
	 */
	private String converterMethodName(String param1) {
		return "get" + StringUtils.capitalize(param1);
	}

	/**
	 * Metodo com a logica de comparacao entre propriedades.
	 * 
	 * @param result1
	 *            - Object - Objeto que compara.
	 * @param result2
	 *            - Object - Objeto que e comparado.
	 * @return int - Inteiro negativo se o objecto que compara vier antes do
	 *         comparado, inteiro positivo caso o objeto comparado ser depois e
	 *         inteiro nulo caso as propriedades tenha mesmo valor de ordenacao.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @author Cesar Voginski
	 * @since 12/05/2011
	 */
	private int comparar(Object result1, Object result2)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Method metodoCompareTo = null;
		if (result1 instanceof String) {
			return (collator.compare((String) result1, (String) result2) * sinal);
		}
		if (result1 instanceof Date) {
			return ((Date) result1).compareTo((Date) result2) * sinal;
		} else {
			metodoCompareTo = null;
			metodoCompareTo = result1.getClass().getMethod(("compareTo"),
					new Class[] { result1.getClass() });
		}

		if (metodoCompareTo == null)
			return 0;

		return ((Integer) metodoCompareTo.invoke(result1,
				new Object[] { result2 }) * sinal);
	}

	/**
	 * Retorna o resultado pelo metodo getter da propriedade desejada para
	 * realizar a comparacao.
	 * 
	 * @param o
	 *            - Object - Objeto que contem a propriedade
	 * @param paramAtual
	 *            - Nome da propriedade a ser requisitada.
	 * @return Object - Valor retornado do metodo
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author Cesar Voginski
	 * @since 12/05/2011
	 */
	/**
	 * Retorna o resultado pelo metodo getter da propriedade desejada para
	 * realizar a comparacao.
	 * 
	 * @param listaChamadas
	 *            -List<Method> - Lista contendo as chamadas para a propriedade
	 *            a ser comparada.
	 * @param o
	 *            - Object - Objeto que contem a propriedade
	 * @return Object - Valor retornado do metodo
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @author Cesar Voginski
	 * @since 12/05/2011
	 */
	private Object getResultado(Object o, int param) {
		Object resultado = o;
		if (resultado == null)
			return null;

		for (Method metodo : listaChamadas.get(param)) {
			if (metodo == null) {
				Throwable thr = new Throwable();
				thr.fillInStackTrace();
				StackTraceElement[] pilha = thr.getStackTrace();

				return 0;
			}
			try {
				resultado = metodo.invoke(resultado, new Object[] {});
				if (resultado == null)
					return null;
			} catch (Exception e) {
			}
		}
		return resultado;
	}

	private List<Method[]> getListaMetodos(Object objetoModelo) {
		List<Method[]> listaMetodos = new ArrayList<Method[]>();

		if (objetoModelo == null)
			return null;

		for (String metodoNomeSequencia : parametros) {
			String[] split = StringUtils.split(metodoNomeSequencia, ".");
			Method[] arrayMethod = new Method[split.length];
			Class<?> classeComparavel = objetoModelo.getClass();

			if (objetoModelo.getClass().getName()
					.contains(HIBERNATE_PROXY_SUFFIX))
				classeComparavel = objetoModelo.getClass().getSuperclass();

			for (int i = 0; i < split.length; i++) {
				String param1 = split[i];
				Class<?> classeAtual = classeComparavel;
				Method get = findMethod(classeAtual,
						converterMethodName(param1));
				while (get == null && !classeAtual.equals(Object.class)) {
					classeAtual = classeAtual.getSuperclass();
					get = findMethod(classeAtual, converterMethodName(param1));
				}
				// if (get == null && classeAtual.equals(Object.class)) {
				// get = findMethod(MurahTreeNodeImpl.class,
				// converterMethodName(param1));
				// }
				if (get == null) {
					Throwable thr = new Throwable();
					thr.fillInStackTrace();
					StackTraceElement[] pilha = thr.getStackTrace();
					return null;
				}
				try {
					arrayMethod[i] = get;
					if (objetoModelo != null) {
						classeComparavel = get.getReturnType();
					} else
						return null;
				} catch (Exception e) {
					// TODO Montar Log Externo
				}

			}
			listaMetodos.add(arrayMethod);
		}
		return listaMetodos;
	}

	public static Method findMethod(Class<?> clazz, String name) {
		return findMethod(clazz, name, new Class[0]);
	}

	public static Method findMethod(Class<?> clazz, String name,
			Class[] paramTypes) {
		Class searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType
					.getMethods() : searchType.getDeclaredMethods());
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (name.equals(method.getName())
						&& (paramTypes == null || Arrays.equals(paramTypes,
								method.getParameterTypes()))) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();

		}
		return null;
	}
}
