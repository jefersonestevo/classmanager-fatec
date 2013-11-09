package br.com.classmanager.core.service.email;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

@XmlRootElement(name = "template")
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateEmail {

	@XmlElement(name = "titulo")
	private String titulo;

	@XmlAnyElement
	private List<Element> conteudo;

	public String mergeTitulo(Map<String, String> atributos) {
		return merge(titulo, atributos);
	}

	public String mergeConteudo(Map<String, String> atributos) {
		StringBuilder builder = new StringBuilder();
		for (Element element : conteudo) {
			builder.append(element.getTextContent());
		}
		return merge(builder.toString(), atributos);
	}

	public static void main(String[] args) {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(TemplateEmail.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			TemplateEmail temp = (TemplateEmail) unmarshaller
					.unmarshal(new File(
							"C:\\Users\\Jeferson\\Desktop\\teste.xml"));

			Map<String, String> atributos = new HashMap<String, String>();
			atributos.put("login", "LOGIN TESTE");
			atributos.put("senha", "SENHA TESTE");
			System.out.println(temp.mergeConteudo(atributos));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String merge(String entrada, Map<String, String> atributos) {
		String nova = entrada;
		if (atributos != null) {
			for (Entry<String, String> entry : atributos.entrySet()) {
				nova = entrada.replaceAll("@@" + entry.getKey() + "@@",
						entry.getValue());
			}
		}
		return nova;
	}

}
