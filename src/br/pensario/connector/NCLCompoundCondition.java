package br.pensario.connector;

public class NCLCompoundCondition extends NCLCondition {
	
	private NCLConditionOperator operator;
	
	//TODO - verificar navegabilidade
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
