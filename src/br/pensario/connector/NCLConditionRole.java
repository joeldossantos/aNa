package br.pensario.connector;

import br.pensario.NCLValues.NCLDefaultConditionRole;

public class NCLConditionRole extends NCLRole {

	private NCLDefaultConditionRole id;

	public String getId() {		
		return id.toString();
	}

	public void setId(NCLDefaultConditionRole id) {
		this.id = id;
	}
	
		
}
