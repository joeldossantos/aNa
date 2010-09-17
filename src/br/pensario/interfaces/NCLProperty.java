package br.pensario.interfaces;

import br.pensario.NCLIdentification;
import br.pensario.NCLValues.NCLSystemVariable;

public class NCLProperty extends NCLInterface {

	private NCLIdentification name;
	private String value;
	
	
	public NCLProperty(String name) throws Exception {
		if (!setName(name)){
			Exception ex = new Exception("Invalid name format");
			throw ex;
		}
	}
	
	public NCLProperty(NCLSystemVariable name) throws Exception {
		if (!setName(name)){
			Exception ex = new Exception("Invalid name format");
			throw ex;
		}
	}
	
	/**
	 * define uma nome de propriedade que nao eh padrao
	 * pode ser no formato shared.xxx
	 * @param name nome da propriedade
	 * @return true se valido falso contrario
	 */
	public boolean setName(String name) {
		try{
			this.name = new NCLIdentification(name);
			return true;
		}
		catch(Exception ex){
			System.err.println(ex);
			return false;
		}
	}
	
	/**
	 * define uma nome de propriedade da tabela de padrao
	 * @param name nome da propriedade
	 * @return true se valido falso contrario
	 */
	public boolean setName(NCLSystemVariable name) {
		if (name != null){
			return setName(name.toString());
		}
		else
			return false;
	}
	
	public String getName() {
		return name.toString();
	}
	
	public String getIdentifier(){
		return getName();
	}
	
	/**
	 * define um valor para a propriedade
	 * @param value valor da propriedade
	 * @return true se valido falso contrario
	 */
	public boolean setValue(String value) {
		if (value != null){
			this.value = value;
			return true;
		}
		else
			return false;
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
}
