package br.pensario.connector;

import br.pensario.NCLValues.NCLActionOperator;

public class NCLSimpleAction extends NCLAction {

	//REV: falta has e set gerar excecao	
	private String value;	

	private Integer min;
	private Integer max;
	
	private NCLActionOperator qualifier;
	
	private NCLActionRole role;
	
	public NCLSimpleAction(NCLActionRole role) {
		this.setRole(role);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
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
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<simpleAction";

		content += " role='" + getValue() + "'";

		if(getDelay()!=null)
			content += " delay='" + getDelay() + "'";
		
		if(getMin()!=null)
			content += " min='" + getMin() + "'";
		
		if(getMax()!=null)
			content += " max='" + getMax() + "'";		
		
		if(getQualifier()!=null)
			content += " qualifier='" + getQualifier() + "'";
		
		content += " />\n";

		return content;
	}

	@Override
	public String toString() {
		return parse(0);
	}
	
}
