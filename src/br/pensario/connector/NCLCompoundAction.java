package br.pensario.connector;

import java.util.List;

import br.pensario.NCLValues.NCLActionOperator;

public class NCLCompoundAction {//extends NCLAction{

	private List<NCLAction> actions; 
	
	private NCLActionOperator operator;

	public NCLActionOperator getOperator() {
		return operator;
	}

	public void setOperator(NCLActionOperator operator) {
		this.operator = operator;
	}
	
	
}
