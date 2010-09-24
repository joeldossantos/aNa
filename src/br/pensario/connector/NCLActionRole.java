package br.pensario.connector;

import br.pensario.NCLValues.NCLDefaultActionRole;

public class NCLActionRole extends NCLRole {

	private NCLDefaultActionRole id;

	public String getId() {	
		return id.toString();
	}

	public void setId(NCLDefaultActionRole id) {
		this.id = id;
	}
	
}
