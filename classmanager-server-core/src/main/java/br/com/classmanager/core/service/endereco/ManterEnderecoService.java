package br.com.classmanager.core.service.endereco;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.endereco.ManterEnderecoAction;
import br.com.classmanager.client.entidades.endereco.Endereco;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.endereco.IDaoEndereco;
import br.com.classmanager.server.domain.service.impl.ServicoManterBase;

@Named("ManterEnderecoAction")
public class ManterEnderecoService extends
		ServicoManterBase<ManterEnderecoAction, Endereco, Long> {

	@Inject
	@DAO
	private IDaoEndereco dao;

	public IDaoEndereco getDao() {
		return dao;
	}

}
