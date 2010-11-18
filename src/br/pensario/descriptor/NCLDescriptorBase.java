package br.pensario.descriptor;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;

/**
 * Classe referente a base de descritores NCL.
 * 
 * @author Wagner Schau
 * @since 24/09/2010
 * @version 1.0
 */
public class NCLDescriptorBase extends NCLIdentifiableElement {

	Set<NCLDescriptor> descriptors = new TreeSet<NCLDescriptor>();

	/**
	 * Contrutor padrão vazio
	 *
	 */
	public NCLDescriptorBase(){}
	
	/**
	 * Contrutor padrão
	 * 
	 * @param id
	 */
	public NCLDescriptorBase(String id) throws NCLInvalidIdentifierException{
		super.setId(id);		
	}	
		
	/**
	 * Adiciona um novo descritor à base de descritores
	 * 
	 * @param descriptor Descritor NCL a ser adicionado
	 */
	public boolean addDescriptor(NCLDescriptor descriptor) {
		return descriptors.add(descriptor);

	}

	/**
	 * Remove um descritor da base de descritores
	 * 
	 * @param descriptor Descritor NCL a ser removido
	 */
	public boolean removeDescriptor(NCLDescriptor descriptor) {
		return descriptors.remove(descriptor);
	}

	/**
	 * Indica se a base de descritores contém um determinado descritor NCL
	 * 
	 * @param descriptor Descritor NCL a ser buscado
	 */
	public boolean hasDescriptor(NCLDescriptor descriptor) {
		return descriptors.contains(descriptor);
	}

	/**
	 * Indica se a base possui algum descritor NCL
	 * 
	 * @return Verdadeiro caso a base possua ao menos um Descritor NCL
	 */
	public boolean hasDescriptor() {
		return descriptors.size() > 0;
	}

	/**
	 * Retorna uma coleção iterável contendo os descritores NCL da base
	 * 
	 */
	public Iterable<NCLDescriptor> getDescriptors() {
		return descriptors;
	}

	/**
	 * Retorna a representação do elemento em XML.
	 * 
	 * @return Trecho XML referente ao elemento
	 */
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<descriptorBase ";

		if (super.getId() != null)
			content += " id='" + super.getId();

		content += "'>\n";

		if (hasDescriptor())

			for (NCLDescriptor descriptor : getDescriptors())
				content += descriptor.parse(ident + 1);

		content += space + "<descriptorBase/>\n";

		return content;
	}

	public String toString() {
		return parse(0);
	}

}
