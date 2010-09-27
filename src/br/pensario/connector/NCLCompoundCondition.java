package br.pensario.connector;

import br.pensario.NCLValues.NCLConditionOperator;

public class NCLCompoundCondition {//extends NCLCondition {
	
	private NCLConditionOperator operator;
	
	//REV condition deve ser um List tambem
	private NCLCondition condition;

	public NCLConditionOperator getOperator() {
		return operator;
	}

	public void setOperator(NCLConditionOperator operator) {
		this.operator = operator;
	}

	public NCLCondition getCondition() {
		return condition;
	}

	public void setCondition(NCLCondition condition) {
		this.condition = condition;
	}
	
	
}
