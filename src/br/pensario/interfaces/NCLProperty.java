package br.pensario.interfaces;

import br.pensario.NCLValues.NCLSystemVariable;

public class NCLProperty extends NCLInterface {

	private String name;
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
		//TODO: validar entrada de nome pelo menos ver se eh nulo
		this.name = name;
		return true;
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
		return name;
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
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
