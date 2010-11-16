package br.pensario.connector;

import br.pensario.NCLValues.NCLDefaultActionRole;

//DUVIDA - NCLActionRole e NCLDefaultActionRole estão competindo em responsabilidades.
//DUVIDA - NCLConditionRole e NCLDefaultConditionRole estão competindo em responsabilidades.
 
public class NCLActionRole extends NCLRole {

	private NCLDefaultActionRole id;
	
	/**
	 * Construtor padrão.
	 * @param action_role NCLDefaultActionRole Papel
	 */
	public NCLActionRole(NCLDefaultActionRole action_role)	
	{		
		setId(action_role);		
	}
	
	/**
	 * Retorna o nome do papel.
	 * @return Papel
	 */
	public String getId() {	
		return id.toString();
	}
	
	/**
	 * Atribui um papel à ação.
	 * 
	 * @param id NCLDefaultActionRole Papel da ação.
	 */
	public void setId(NCLDefaultActionRole id) {
		this.id = id;
	}
	
}