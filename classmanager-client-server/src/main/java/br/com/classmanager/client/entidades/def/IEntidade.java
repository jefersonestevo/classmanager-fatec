package br.com.classmanager.client.entidades.def;

import java.io.Serializable;

/**
 * Classe marcadora de uma entidade que será persistida.
 * 
 * @author Jeferson Estevo
 * 
 * @param <ID> - Classe que representa to tipo do ID da entidade
 */
public interface IEntidade<ID> extends Serializable, Cloneable {

    public void setId(ID id);

    public ID getId();

}
