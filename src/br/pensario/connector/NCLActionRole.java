package br.pensario.connector;

public class NCLActionRole extends NCLRole {

	private NCLDefaultActionRole id;

	public String getId() {
		//DUVIDA - como sobrescrever Role.getId?
		return id.toString();
	}

	public void setId(NCLDefaultActionRole id) {
		this.id = id;
	}
	
}
