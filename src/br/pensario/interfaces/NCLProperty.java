package br.pensario.interfaces;

import br.pensario.NCLIdentification;
import br.pensario.NCLValues.NCLSystemVariable;

/**
 * This class implements the NCL property element.
 * @author joel
 *
 */
public class NCLProperty extends NCLInterface implements Comparable<NCLProperty> {

	/**
	 * property name
	 */
	private String name;
	/**
	 * property value
	 */
	private String value;
	
	
	/**
	 * class constructor. Receives the required property name
	 * @param name - String with the name of the property
	 * @throws Exception - if the name is a invalid name
	 */
	public NCLProperty(String name) throws Exception {
		setName(name);
	}
	
	/**
	 * class constructor. Receives the required property name
	 * @param name - NCLSystemVariable a standard property name
	 * @throws Exception - if the name is null
	 */
	public NCLProperty(NCLSystemVariable name) throws Exception {
		setName(name);
	}
	
	/**
	 * define the name of the propriedade. It may not be a standard name
	 * It may be in the format shared.xxx
	 * @param name - String with the name of the property
	 * @throws Exception - if the name is a invalid name
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
	
	/**
	 * Gets the property name
	 * @return String with the property name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the attribute tha identifies the property, in this case the property name.
	 * @return String with the property name.
	 */
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
	
	/**
	 * Gets the property value
	 * @return String with the value.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return True if the property has a value and false otherwise
	 */
	public boolean hasValue() {
		if (value != null)
			return true;
		else
			return false;
	}
	
	/**
	 * Compares two properties.
	 * @param property - property to each compare
	 * @return true if the properties are equals and false otherwise
	 */
	public boolean equals(NCLProperty property) {
		if (!getName().equals(property.getName()))
			return false;
		else
			return true;
	}
	
	/**
	 * Creates the XML code of the property.
	 * @param ident - indentation level
	 * @return String with the XML code.
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
	 * Creates the XML code of the property without indentation.
	 * @return String with the XML code.
	 */
	public String toString() {
		return parse(0);
	}

	public int compareTo(NCLProperty property) {
		return getName().compareTo(property.getName());
	}
}
