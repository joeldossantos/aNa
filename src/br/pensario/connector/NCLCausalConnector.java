package br.pensario.connector;

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
	
	

}
