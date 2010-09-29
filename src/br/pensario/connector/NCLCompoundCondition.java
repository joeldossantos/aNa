package br.pensario.connector;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLValues.NCLConditionOperator;

public class NCLCompoundCondition extends NCLCondition {
	
	private NCLConditionOperator operator;
	
	//DUVIDA - na especificacao está 1 ou mais (simpleCondition | compoundCompound)+
	private Set<NCLCondition> conditions = new TreeSet<NCLCondition>();	
	
	public NCLConditionOperator getOperator() {
		return operator;
	}

	public void setOperator(NCLConditionOperator operator) {
		this.operator = operator;
	}

	public boolean addCondition(NCLCondition condition) {
		return conditions.add(condition);

	}

	public boolean removeCondition(NCLCondition condition) {
		return conditions.remove(condition);
	}

	public boolean hasCondition(NCLCondition condition) {
		return conditions.contains(condition);
	}

	public boolean hasConditions() {
		return conditions.size() > 0;
	}
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<compoundCondition";

		if(getDelay()!=null)
			content += " delay='" + getDelay() + "'";		
		
		content += ">\n";
		
		for(NCLCondition condition : conditions)
			content += condition.parse(ident + 1);
		
		content += space + "</compoundCondition>\n";

		return content;
	}

	@Override
	public String toString() {
		return parse(0);		
	}

	@Override
	public int compareTo(NCLCondition arg0) {		
				
		if(!(arg0 instanceof NCLCompoundCondition))
			return -1;		
		
		NCLCompoundCondition ccond = (NCLCompoundCondition) arg0;		
		
		for(NCLCondition condition : conditions)		
			if(!ccond.hasCondition(condition)) return -1;		
		
		if(ccond.compareTo(this) != 0 ) return -1;
		
		return 0;
	}	
}
