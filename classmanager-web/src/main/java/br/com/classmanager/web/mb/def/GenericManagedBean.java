package br.com.classmanager.web.mb.def;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;

import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.exceptions.ClassManagerConstraintException;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;

/**
 * Classe genérica com métodos utilitários para representar um Bean
 * Gerenciado do JSF. <br />
 * Aconselha-se que todos os beans gerenciados pelo JSF estendam esta classe.
 * 
 * @author Jeferson Estevo
 * 
 */
public abstract class GenericManagedBean implements Serializable {

	private static final long serialVersionUID = -5410886668050787843L;

	private static final String BUNDLE_MESSAGE = "message";
	private static final String BUNDLE_MESSAGE_EXCEPTION = "messageException";
	private static final String BUNDLE_MESSAGE_VALIDATION = "messageValidation";

	/**
	 * 
	 * Adiciona uma mensagem de nível FacesMessage.SEVERITY_ERROR às mensagens
	 * do JSF.
	 * 
	 * @param titulo
	 *            - Título da mensagem a ser exibida
	 * @param message
	 *            - Descrição da mensagem a ser exibida
	 */
	public void addErrorMessage(String titulo, Object... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, null));
	}

	/**
	 * 
	 * Adiciona uma mensagem de nível FacesMessage.SEVERITY_ERROR às mensagens
	 * do JSF.
	 * 
	 * @param titulo
	 *            - Título da mensagem a ser exibida
	 * @param message
	 *            - Descrição da mensagem a ser exibida
	 */
	public void addErrorMessage(String titulo) {
		addErrorMessage(titulo, (Object[]) null);
	}

	/**
	 * 
	 * Adiciona uma mensagem de nível FacesMessage.SEVERITY_INFO às mensagens
	 * do JSF.
	 * 
	 * @param titulo
	 *            - Título da mensagem a ser exibida
	 * @param message
	 *            - Descrição da mensagem a ser exibida
	 */
	public void addInfoMessage(String titulo, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, message));
	}

	/**
	 * 
	 * Adiciona uma mensagem de nível FacesMessage.SEVERITY_INFO às mensagens
	 * do JSF.
	 * 
	 * @param titulo
	 *            - Título da mensagem a ser exibida
	 */
	public void addInfoMessage(String titulo) {
		addInfoMessage(titulo, null);
	}

	/**
	 * 
	 * Adiciona uma mensagem de nível FacesMessage.SEVERITY_WARN às mensagens
	 * do JSF.
	 * 
	 * @param titulo
	 *            - Título da mensagem a ser exibida
	 * @param message
	 *            - Descrição da mensagem a ser exibida
	 */
	public void addWarnMessage(String titulo, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, message));
	}

	/**
	 * 
	 * Adiciona uma mensagem de nível FacesMessage.SEVERITY_WARN às mensagens
	 * do JSF.
	 * 
	 * @param titulo
	 *            - Título da mensagem a ser exibida
	 */
	public void addWarnMessage(String titulo) {
		addWarnMessage(titulo, null);
	}

	/**
	 * 
	 * Exibe a mensagem "Message_Sucesso" do Resource Bundle "message" do JSF. <br />
	 * Esta mensagem é exibida sob o nível FacesMessage.SEVERITY_INFO do JSF.
	 * 
	 */
	public void showSuccessMessage() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						getMessage("Message_Sucesso"), null));
	}

	/**
	 * 
	 * Adiciona uma mensagem para ser exibida para o usuário através do
	 * código de exceção informado. <br />
	 * Utiliza o Resource Bundle "messageException" do JSF para procurar pelas
	 * mensagens, informando como chave o próprio código de exceção. <br />
	 * 
	 * @param e
	 *            - Exceção cujo código de Exceção será utilizado como
	 *            chave para procura da mensagem a ser exibida.
	 */
	public void addExceptionMessage(ClassManagerException e) {
		String chave = e.getCodigoExcecao().getCodigo().toString();
		if (e instanceof ClassManagerConstraintException) {
			ClassManagerConstraintException exception = (ClassManagerConstraintException) e;
			for (ConstraintViolation<?> constraint : exception
					.getConstraintViolations()) {
				chave = constraint.getMessageTemplate();
				if (chave.startsWith("{"))
					chave = chave.substring(1);
				if (chave.endsWith("}"))
					chave = chave.substring(0, chave.length() - 1);
				addErrorMessage(getMessage(BUNDLE_MESSAGE_VALIDATION, chave,
						constraint.getPropertyPath(),
						constraint.getInvalidValue()));
			}
		} else {
			addErrorMessage(getMessage(BUNDLE_MESSAGE_EXCEPTION, chave));
		}
	}

	/**
	 * 
	 * Procura por uma mensagem no Resource Bundle "message" do JSF.
	 * 
	 * @param chave
	 *            - Chave para ser procurada dentro do Resource Bundle
	 *            "message".
	 * @return - Valor da chave mapeada dentro do Resource Bundle ou a própria
	 *         chave de consulta caso nada seja encontrado.
	 */
	public String getMessage(String chave) {
		return getMessage(BUNDLE_MESSAGE, chave);
	}

	public String getMessage(String chave, Object[] params) {
		return getMessage(BUNDLE_MESSAGE, chave, params);
	}

	/**
	 * 
	 * Procura por uma mensagem nos Resource Bundles do JSF.
	 * 
	 * @param resource
	 *            - Nome mapeado do Resource Bundle no JSF.
	 * @param chave
	 *            - Chave para ser procurada dentro do Resource Bundle.
	 * @return - Valor da chave mapeada dentro do Resource Bundle ou a própria
	 *         chave de consulta caso nada seja encontrado.
	 */
	private String getMessage(String resource, String chave) {
		return getMessage(resource, chave, (Object[]) null);
	}

	private static String getMessage(String resource, String chave,
			Object... params) {

		String message;
		ResourceBundle bundle = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), resource);

		try {
			message = bundle.getString(chave);
		} catch (MissingResourceException e) {
			return chave;
		}

		if (params != null) {
			MessageFormat mf = new MessageFormat(message);
			message = mf.format(params, new StringBuffer(), null).toString();
		}

		return message;
	}

	/**
	 * 
	 * Procura entre os parâmetros de request da aplicação por um parâmetro
	 * com o nome informado e o traz já convertido para o formato Long.
	 * 
	 * @param param
	 * @return
	 */
	public Long getLongParameter(String param) {
		return new Long(getStringParameter(param));
	}

	/**
	 * 
	 * Procura entre os parâmetros de request da aplicação por um parâmetro
	 * com o nome informado. <br />
	 * Retorna null caso nada seja encontrado.
	 * 
	 * @param param
	 * @return
	 */
	public String getStringParameter(String param) {
		try {
			return getStringParameter(param, false);
		} catch (ClassManagerException e) {
			return null;
		}
	}

	/**
	 * 
	 * Procura entre os parâmetros de request da aplicação por um parâmetro
	 * com o nome informado. <br />
	 * 
	 * @param param
	 *            - Nome do parâmetro para ser procurado
	 * @param obrigatorio
	 *            - Se este parâmetro for informado com true e não existir o
	 *            parametro informado, lança uma ClassManagerException.
	 * @return - Parâmetro de request com o nome informado.
	 * @throws ClassManagerException
	 */
	public String getStringParameter(String param, boolean obrigatorio)
			throws ClassManagerException {
		String parametro = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(param);
		if (obrigatorio
				&& ("null".equals(parametro) || StringUtils.isEmpty(parametro))) {
			throw new ClassManagerException(
					CodigoExcecao.PARAMETRO_OBRIGATORIO_NULO);
		}
		return parametro;
	}

}
