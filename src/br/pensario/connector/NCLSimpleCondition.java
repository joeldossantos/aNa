package br.pensario.connector;

import br.pensario.NCLValues.NCLConditionOperator;
import br.pensario.NCLValues.NCLKey;

public class NCLSimpleCondition extends NCLCondition{

	private NCLKey key;	

	private Integer min;
	private Integer max;
	
	private NCLConditionOperator qualifier;
	
	private NCLConditionRole role;
	
	/**
	 * Construtor padrão.
	 * @param role NCLConditionRole Papel utilizado pela condição
	 */	
	public NCLSimpleCondition(NCLConditionRole role)	
	{
		setRole(role);
	}
	
	/**
	 * Retorna o valor mínimo utilizado na condição.
	 * @return Valor mínimo
	 */	
	public Integer getMin() {
		return min;
	}

	/**
	 * Atribui um valor mínimo à condição.
	 * @param min Valor mínimo
	 */
	
	public void setMin(Integer min) {
		this.min = min;
	}

	
	/**
	 * Retorna o valor máximo esperado para a condição.
	 * @return Valor máximo
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * Atribui um novo valor máximo à condição.
	 * @param max Integer Valor máximo
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * Retorna o operador da condição.
	 * @return Operador da condição
	 */
	public NCLConditionOperator getQualifier() {
		return qualifier;
	}
	
	/**
	 * Atribui um novo operador à condição (seealso NCLConditionOperator).
	 * @param qualifier Operador
	 */
	public void setQualifier(NCLConditionOperator qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * Retorna o papel utilizado na condição.
	 * @return Papel
	 */
	public NCLConditionRole getRole() {
		return role;
	}

	/**
	 * Atribui um novo papel à condição.
	 * @param role Papel
	 */
	public void setRole(NCLConditionRole role) {
		this.role = role;
	}

	/**
	 * Retorna chave utilizado na condição.
	 * @return Chave
	 */
	public String getKey() {
		return key.toString();
	}

	/**
	 * Atribui uma nova chave à ação.
	 * @param key NCLKey Nova chave
	 */
	public void setKey(NCLKey key) {
		this.key = key;
	}	
	
	/**
	 * Retorna a representação do elemento em XML.
	 * @return Trecho XML referente ao elemento
	 */
	public String parse(int ident) {

		String space, content;

		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";

		content = space + "<simpleCondition";

		content += " role='" + getRole().getId() + "'";
		
		if(getKey()!=null)
			content += " key='" + getKey() + "'";

		if(getDelay()!=null)
			content += " delay='" + getDelay() + "'";

		if(getMin()!=null)
			content += " min='" + getMin() + "'";		
		
		if(getMax()!=null)
			content += " max='" + getMax() + "'";
			
		if(getQualifier()!=null)
			content += " qualifier='" + getQualifier() + "'";
		
		content += " />\n";

		return content;
	}

	@Override
	public String toString() {
		return parse(0);
	}
	
	@Override
	public int compareTo(NCLCondition arg0) {		
				
		if(!(arg0 instanceof NCLSimpleCondition))
			return -1;		
		
		NCLSimpleCondition scond = (NCLSimpleCondition) arg0;		
		
		if(!scond.getRole().toString().equals(this.getRole().toString()))
			return -1;	
		
		return 0;
	}
	
}
