package br.pensario.connector;

import java.util.List;

public class NCLCompoundAction {

	//DUVIDA - Verificar o caminho de navegação;
	private List<NCLAction> actions; 
	
	private NCLActionOperator operator;

	public NCLActionOperator getOperator() {
		return operator;
	}

	public void setOperator(NCLActionOperator operator) {
		this.operator = operator;
	}
	
	
}
