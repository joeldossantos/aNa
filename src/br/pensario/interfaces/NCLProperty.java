package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSystemVariable;

/**
 * Propriedade (elemento property) da linguagem NCL.
 */
public class NCLProperty extends NCLInterface implements Comparable<NCLProperty> {

	private String value;
	
	
	/**
	 * Construtor da propriedade sem seguir os nomes padrão.
	 * 
	 * @param name String com o nome da propriedade.
	 * @throws Exception se o nome não for válido.
	 */
	public NCLProperty(String name) throws Exception {
		setName(name);
	}
	
	
	/**
	 * Construtor da propriedade seguindo os nomes padrão.
	 * 
	 * @param name NCLSystemVariable com o nome da propriedade.
	 * @throws NullPointerException se o nome for nulo.
	 */
	public NCLProperty(NCLSystemVariable name) throws Exception {
		setName(name);
	}
	
	
	/**
	 * Determina o nome da propriedade sem seguir os valores padrão especificados na norma.
	 * O nome, entretando pode estar na forma shared.xxx
	 * 
	 * @param name String com o nome da propriedade
	 * @throws Exception se o nome não for válido.
	 */
	public void setName(String name) throws Exception {
		setIdentification(name);
	}
	
	
	/**
	 * Determina o nome de uma propriedade seguindo os valore padrão especificados na norma.
	 * 
	 * @param name nome da propriedade
	 * @throws NullPointerException se o nome for nulo.
	 */
	public void setName(NCLSystemVariable name) throws Exception {
		if (name != null){
			setName(name.toString());
		}
		else{
			Exception ex = new NullPointerException("Null name");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o nome da propriedade.
	 * 
	 * @return String com o nome da propriedade.
	 */
	public String getName() {
		return getIdentification();
	}
	
	
	/**
	 * Obtém o atributo que identifica uma propriedade, neste caso o nome da propriedade.
	 * 
	 * @return String com o nome da propriedade.
	 */
	public String getIdentifier(){
		return getName();
	}
	
	
	/**
	 * Determina o valor do atributo value de uma propriedade.
	 * 
	 * @param value valor do atributo
	 * @throws NullPointerException se o valor for nulo.
	 */
	public void setValue(String value) throws Exception {
		if (value != null){
			this.value = value;
		}
		else{
			Exception ex = new NullPointerException("Null value");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo value da propriedade.
	 * 
	 * @return String com o valor do atributo.
	 */
	public String getValue() {
		return value;
	}
	
	
	/**
	 * Verifica se a propriedade possui o atributo value.
	 * 
	 * @return True se a propriedade possuir o atributo value.
	 */
	public boolean hasValue() {
		return (value != null);
	}
	
	
	/**
	 * Compara uma propriedade com outra propriedade.
	 * 
	 * @param property propriedade com a qual comparar.
	 * @return True se as propriedades forem iguais.
	 */
	public boolean equals(NCLProperty property) {
		if (!getName().equals(property.getName()))
			return false;
		else
			return true;
	}
	
	
	/**
	 * Cria o código XML de uma propriedade.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML da propriedade.
	 */
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <property> element and attributes declaration
		content = space + "<property";
		content += " name='" + getName() + "'";
		if (hasValue())
			content += " value='" + getValue() + "'";
		content += "/>\n";
		
		
		return content;
	}
	
	
	/**
	 * Cria o código XML da propriedade sem indentação.
	 * 
	 * @return String com o código XML.
	 */
	public String toString() {
		return parse(0);
	}
	
	
	/**
	 * Compara uma propriedade com outra propriedade.
	 * 
	 * @param property propriedade com a qual comparar.
	 * @return Inteiro indicando se as propriedades são iguais. O valor será 0 se as propriedades forem iguais.
	 */
	public int compareTo(NCLProperty property) {
		return getName().compareTo(property.getName());
	}
}
