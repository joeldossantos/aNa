package br.pensario.connector;

public class NCLSimpleCondition extends NCLCondition {

	private String key;	

	private int min = -1;
	private int max = -1;
	
	//DUVIDA - Onde fica este cara na modelagem?
	private NCLConditionOperator qualifier;
	
	private NCLConditionRole role;
	
	

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public NCLConditionOperator getQualifier() {
		return qualifier;
	}

	public void setQualifier(NCLConditionOperator qualifier) {
		this.qualifier = qualifier;
	}

	public NCLConditionRole getRole() {
		return role;
	}

	public void setRole(NCLConditionRole role) {
		this.role = role;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}	
	
}
