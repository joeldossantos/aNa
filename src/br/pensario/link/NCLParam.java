package br.pensario.link;

import br.pensario.connector.NCLConnectorParam;

public class NCLParam {

	private NCLConnectorParam name;
	private String value;
	
	
	/**
	 * recebe o <connectorParam> ao qual se refere.
	 * 
	 * @param connectorParam
	 * @param value
	 * @throws Exception
	 */
	protected NCLParam(NCLConnectorParam connectorParam, String value) throws Exception {
		setName(connectorParam);
		setValue(value);
	}
	
	private void setName(NCLConnectorParam connectorParam) throws Exception {
		if (connectorParam != null){
			this.name = connectorParam;
		}
		else{
			Exception ex = new NullPointerException("Null name");
			throw ex;
		}
	}
	
	protected NCLConnectorParam getName() {
		return name;
	}
	
	private void setValue(String value) throws Exception {
		if (value != null){
			this.value = value;
		}
		else{
			Exception ex = new NullPointerException("Null value");
			throw ex;
		}
	}
	
	protected String getValue() {
		return value;
	}
	
	protected String parse(int ident, String type) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <linkParam> element and attributes declaration
		content = space + "<" + type;
		content += " name='" + getName().getName() + "'";
		content += " value='" + getValue() + "'";
		content += "/>\n";
		
		return content;
	}
}
