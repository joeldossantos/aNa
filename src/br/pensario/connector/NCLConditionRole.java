package br.pensario.connector;

import br.pensario.NCLValues.NCLDefaultConditionRole;

public class NCLConditionRole extends NCLRole {

	private NCLDefaultConditionRole id;
	
	/**
	 * Construtor padrão.
	 * @param condition_role NCLDefaultCondiitonRole Papel
	 */
	public NCLConditionRole(NCLDefaultConditionRole condition_role)
	{
		setId(condition_role);
	}
	
	/**
	 * Retorna o nome do papel.
	 * @return Papel
	 */
	public String getId() {		
		return id.toString();
	}

	/**
	 * Atribui um papel à condição.
	 * 
	 * @param id NCLDefaultConditionRole Papel da condição.
	 */
	public void setId(NCLDefaultConditionRole id) {
		this.id = id;
	}
	
		
}
