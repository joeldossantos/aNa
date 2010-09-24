package br.pensario.connector;

import br.pensario.region.NCLRegion;

public class NCLCausalConnector {

	private String id;
	private NCLCondition condition;
	private NCLAction action;
	private NCLConnectorParam param;

	public NCLCausalConnector (NCLCondition condition, NCLAction action, NCLConnectorParam param)
	{
		
		this.setCondition(condition);
		this.setAction(action);
		this.setParam(param);
	}
	
	public NCLCondition getCondition() {
		return condition;
	}

	public void setCondition(NCLCondition condition) {
		this.condition = condition;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NCLAction getAction() {
		return action;
	}

	public void setAction(NCLAction action) {
		this.action = action;
	}

	public NCLConnectorParam getParam() {
		return param;
	}

	public void setParam(NCLConnectorParam param) {
		this.param = param;
	}
	
	
	public String toString() {		
		return parse(0);
	}

	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<causalConnector";
		content += " id='" + getId() + "'>";

		content += getCondition().parse(ident + 1);
		content += getAction().parse(ident + 1);
		
		content += space + "<causalConnector/>\n";

		return content;
	}	
	

}
