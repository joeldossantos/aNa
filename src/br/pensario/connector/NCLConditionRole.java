package br.pensario.connector;

public class NCLConditionRole extends NCLRole {

	private NCLDefaultConditionRole id;

	public String getId() {
		//DUVIDA - como sobrescrever Role.getId?
		return id.toString();
	}

	public void setId(NCLDefaultConditionRole id) {
		this.id = id;
	}
	
		
}
