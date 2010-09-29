package br.pensario.connector;

import java.util.Set;

import br.pensario.NCLValues.NCLActionOperator;

public class NCLCompoundAction extends NCLAction{

	private Set<NCLAction> actions; 
	
	private NCLActionOperator operator;

	public NCLActionOperator getOperator() {
		return operator;
	}

	public void setOperator(NCLActionOperator operator) {
		this.operator = operator;
	}
	
	public boolean addAction(NCLAction action) {
		return actions.add(action);

	}

	public boolean removeAction(NCLAction action) {
		return actions.remove(action);
	}

	public boolean hasAction(NCLAction action) {
		return actions.contains(action);
	}

	public boolean hasActions() {
		return actions.size() > 0;
	}
	
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<compoundAction";

		if(getDelay()!=null)
			content += " delay='" + getDelay() + "'";		
		
		content += ">\n";
		
		for(NCLAction action : actions)
			content += action.parse(ident + 1);
		
		content += space + "</compoundAction>";

		return content;
	}

	@Override
	public String toString() {
		return parse(0);		
	}
	
	
}
