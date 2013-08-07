package br.com.classmanager.core.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;

public class ClassManagerEmailException extends ClassManagerException {

	private static final long serialVersionUID = 6973942696744406378L;

	private static final String ESPACO = "    ";

	private List<DadosEmailException> listaErros = new ArrayList<DadosEmailException>();

	public ClassManagerEmailException(CodigoExcecao codigoExcecao) {
		super(codigoExcecao);
	}

	public ClassManagerEmailException(CodigoExcecao codigoExcecao, Exception e) {
		super(codigoExcecao, e);
	}

	public List<DadosEmailException> getListaErros() {
		return new ArrayList<DadosEmailException>(listaErros);
	}

	public void adicionarErroEmail(DadosEmailException dados) {
		this.listaErros.add(dados);
	}

	public String getMessage() {
		StringBuilder message = new StringBuilder("Erro no envio de email: "
				+ QUEBRA_LINHA);
		message.append(getCodigoExcecao().toString());
		message.append(QUEBRA_LINHA);

		if (CollectionUtils.isNotEmpty(getListaErros())) {
			message.append("CAUSA(S): " + QUEBRA_LINHA);
			for (DadosEmailException dados : getListaErros()) {
				message.append(SEPARADOR_ERROS);
				message.append(dados.getMessage());
				message.append(QUEBRA_LINHA);
				message.append(SEPARADOR_ERROS);
			}
		}

		return message.toString();
	}

	public static class DadosEmailException {
		private String de;
		private String para;
		private Date horario;
		private Exception causa;

		public DadosEmailException(String de, String para, Date horario,
				Exception causa) {
			super();
			this.de = de;
			this.para = para;
			this.horario = horario;
			this.causa = causa;
		}

		public String getDe() {
			return de;
		}

		public String getPara() {
			return para;
		}

		public Date getHorario() {
			return horario;
		}

		public Exception getCausa() {
			return causa;
		}

		public String getMessage() {
			StringBuilder message = new StringBuilder("EMAIL" + QUEBRA_LINHA);
			message.append(ESPACO + "DE: " + getDe() + QUEBRA_LINHA);
			message.append(ESPACO + "PARA: " + getPara() + QUEBRA_LINHA);
			message.append(ESPACO + "HORARIO: " + getHorario() + QUEBRA_LINHA);
			message.append(ESPACO + "CAUSA: "
					+ ExceptionUtils.getStackTrace(causa) + QUEBRA_LINHA);

			return message.toString();
		}

	}

}
