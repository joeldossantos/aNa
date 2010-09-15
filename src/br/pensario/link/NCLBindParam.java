package br.pensario.link;

public class NCLBindParam {

	private String name;
	private String value;
	
	
	public NCLBindParam(String name, String value) throws Exception {
		if (!setName(name) || !setValue(value)){
			Exception ex = new Exception("Invalid name or value");
			throw ex;
		}
	}
	
	public boolean setName(String name) {
		if (name != null){
			this.name = name;
			return true;
		}
		else
			return false;
	}
	
	public String getName() {
		return name;
	}
	
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
	
	public String parse(int ident) {
		//a cada filho que entra adiciona 1 em ident
		return "";
	}
}
