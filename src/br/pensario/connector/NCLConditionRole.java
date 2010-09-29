package br.pensario.connector;

import br.pensario.NCLValues.NCLDefaultConditionRole;

public class NCLConditionRole extends NCLRole {

	//DUVIDA - vai ser livre ou de um domínio especifico? String ou enum?
	private NCLDefaultConditionRole id;
	
	public NCLConditionRole(NCLDefaultConditionRole condition_role)
	{
		setId(condition_role);
	}
	
	public String getId() {		
		return id.toString();
	}

	public void setId(NCLDefaultConditionRole id) {
		this.id = id;
	}
	
		
}
