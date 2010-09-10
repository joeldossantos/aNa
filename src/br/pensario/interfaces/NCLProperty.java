package br.pensario.interfaces;

public class NCLProperty extends NCLInterface {

	private String name;
	private String value;
	
	private Boolean hasValue = false;
	
	
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
	public Boolean setName(String name) {
		//TODO: validar entrada de nome pelo menos ver se eh nulo
		this.name = name;
		return true;
	}
	
	/**
	 * define uma nome de propriedade da tabela de padrao
	 * @param name nome da propriedade
	 * @return true se valido falso contrario
	 */
	public Boolean setName(NCLSystemVariable name) {
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
	public Boolean setValue(String value) {
		if (value != null){
			this.value = value;
			hasValue = true;
			return true;
		}
		else
			return false;
	}
	
	public String getValue() {
		return value;
	}
	
	public Boolean hasValue() {
		return hasValue;
	}
}
