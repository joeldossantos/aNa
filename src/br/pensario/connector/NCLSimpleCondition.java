package br.pensario.connector;

import br.pensario.NCLValues.NCLConditionOperator;
import br.pensario.NCLValues.NCLKey;

public class NCLSimpleCondition extends NCLCondition{

	// falta has e set gerar excecao

	private NCLKey key;	

	private Integer min;
	private Integer max;
	
	private NCLConditionOperator qualifier;
	
	private NCLConditionRole role;
	
	public NCLSimpleCondition(NCLConditionRole role)
	{
		setRole(role);
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
		return key.toString();
	}

	public void setKey(NCLKey key) {
		this.key = key;
	}	
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<simpleCondition";

		content += " role='" + getRole().getId() + "'";
		
		if(getKey()!=null)
			content += " key='" + getKey() + "'";

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
	
	@Override
	public int compareTo(NCLCondition arg0) {		
				
		if(!(arg0 instanceof NCLSimpleCondition))
			return -1;		
		
		NCLSimpleCondition scond = (NCLSimpleCondition) arg0;		
		
		if(!scond.getRole().toString().equals(this.getRole().toString()))
			return -1;	
		
		return 0;
	}
	
}
