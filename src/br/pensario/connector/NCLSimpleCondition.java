package br.pensario.connector;

import br.pensario.NCLValues.NCLConditionOperator;

public class NCLSimpleCondition extends NCLCondition {

	//REV: key tem um enum com os valores possiveis
	// falta has e set gerar excecao
	private String key;	

	private Integer min;
	private Integer max;
	
	private NCLConditionOperator qualifier;
	
	private NCLConditionRole role;
	
	//public NCLSimpleCondition(NCLDefault)
	
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
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<simpleCondition";

		content += " role='" + getKey() + "'";

		if(getDelay()!=null)
			content += " delay='" + getDelay() + "'";
		
		if(getMax()!=null)
			content += " max='" + getMax() + "'";
		
		if(getMin()!=null)
			content += " min='" + getMin() + "'";
		
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
