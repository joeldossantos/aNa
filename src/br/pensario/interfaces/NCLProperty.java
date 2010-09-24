package br.pensario.interfaces;

import br.pensario.NCLIdentification;
import br.pensario.NCLValues.NCLSystemVariable;

public class NCLProperty extends NCLInterface {

	private String name;
	private String value;
	
	
	public NCLProperty(String name) throws Exception {
		setName(name);
	}
	
	public NCLProperty(NCLSystemVariable name) throws Exception {
		setName(name);
	}
	
	/**
	 * define uma nome de propriedade que nao eh padrao
	 * pode ser no formato shared.xxx
	 * @param name nome da propriedade
	 * @return true se valido falso contrario
	 */
	public void setName(String name) throws Exception {
		NCLIdentification.validate(name);
		this.name = name;
	}
	
	/**
	 * define uma nome de propriedade da tabela de padrao
	 * @param name nome da propriedade
	 * @return true se valido falso contrario
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
	
	public String getName() {
		return name;
	}
	
	public String getIdentifier(){
		return getName();
	}
	
	/**
	 * define um valor para a propriedade
	 * @param value valor da propriedade
	 * @return true se valido falso contrario
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
	
	public String getValue() {
		return value;
	}
	
	public boolean hasValue() {
		if (value != null)
			return true;
		else
			return false;
	}
	
	public boolean equals(NCLProperty property) {
		if (!getName().equals(property.getName()))
			return false;
		else
			return true;
	}
	
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
	
	public String toString() {
		return parse(0);
	}
}
