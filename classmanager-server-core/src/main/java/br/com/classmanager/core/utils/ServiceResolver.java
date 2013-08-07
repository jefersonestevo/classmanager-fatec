package br.com.classmanager.core.utils;

import java.io.Serializable;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.server.domain.service.IService;


@Named
public class ServiceResolver implements Serializable {

    private static final long serialVersionUID = 3975917731289360922L;

    @Inject
    private BeanManager beanManager;

    /**
     * 
     * Devolve um serviço com base no DTOAction informado. <br />
     * Utiliza a convenção da interface IService para encontrar o serviço para o DTOAction
     * informado.
     * 
     * @param classeVO - Classe para realizar a busca pelo serviço específico
     * @return - Serviço referente à classe informada.
     */
    public IService<? extends DTOAction, ? extends DTO> getServiceFor(Class<? extends DTOAction> classeVO) {
        String nomeService = classeVO.getSimpleName();
        return (IService<?, ?>) CDIUtils.getBean(nomeService, beanManager);
    }

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

}
