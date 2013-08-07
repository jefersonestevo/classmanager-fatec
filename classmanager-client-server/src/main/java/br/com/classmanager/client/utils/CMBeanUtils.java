package br.com.classmanager.client.utils;

import org.apache.commons.beanutils.BeanUtils;

public class CMBeanUtils {

	public static void copyProperties(Object destino, Object origem) {
		try {
			BeanUtils.copyProperties(destino, origem);
		} catch (Exception e) {
		}
	}

}
