package br.pensario.connector;

public class NCLSimpleAction extends NCLAction {

	
	private String value;	

	private int min = -1;
	private int max = -1;
	
	private NCLActionOperator qualifier;
	
	private NCLActionRole role;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

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

	public NCLActionOperator getQualifier() {
		return qualifier;
	}

	public void setQualifier(NCLActionOperator qualifier) {
		this.qualifier = qualifier;
	}

	public NCLActionRole getRole() {
		return role;
	}

	public void setRole(NCLActionRole role) {
		this.role = role;
	}	
	
}
