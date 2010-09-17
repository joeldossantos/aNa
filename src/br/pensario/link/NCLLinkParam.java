package br.pensario.link;

import br.pensario.connector.NCLConnectorParam;

public class NCLLinkParam {

	private NCLConnectorParam name;
	private String value;
	
	
	/**
	 * recebe o <connectorParam> ao qual se refere.
	 * 
	 * @param connectorParam
	 * @param value
	 * @throws Exception
	 */
	public NCLLinkParam(NCLConnectorParam connectorParam, String value) throws Exception {
		if (!setName(connectorParam) || !setValue(value)){
			Exception ex = new Exception("Invalid connectorParam or value");
			throw ex;
		}
	}
	
	public boolean setName(NCLConnectorParam connectorParam) {
		if (connectorParam != null){
			this.name = connectorParam;
			return true;
		}
		else
			return false;
	}
	
	public NCLConnectorParam getName() {
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
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <linkParam> element and attributes declaration
		content = space + "<linkParam";
		content += " name='" + getName().getName() + "'";
		content += " value='" + getValue() + "'";
		content += "/>\n";
		
		return content;
	}
}
