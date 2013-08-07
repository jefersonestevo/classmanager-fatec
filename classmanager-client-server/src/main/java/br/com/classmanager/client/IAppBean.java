package br.com.classmanager.client;

import java.util.LinkedList;

import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.exceptions.ClassManagerException;

public interface IAppBean {

	public void atualizar(DTOAction action, String texto)
			throws ClassManagerException;

	public LinkedList<String> getLista();
}
