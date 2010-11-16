package br.pensario.connector;

import br.pensario.NCLValues.NCLActionOperator;

public class NCLSimpleAction extends NCLAction {

	//REV: falta has e set gerar excecao	
	private String value;	

	private Integer min;
	private Integer max;
	
	private NCLActionOperator qualifier;
	
	private NCLActionRole role;
	
	/**
	 * Construtor padrão
	 * @param role Papel que irá integrar a nova ação
	 */
	public NCLSimpleAction(NCLActionRole role) {
		this.setRole(role);
	}

	/**
	 * Retorna o valor utilizado na ação.
	 * @return Valor
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Indica se um valor foi atribuído à ação.
	 * @return Verdadeiro caso um valor tenha sido atribuído
	 */
	public boolean hasValue() {
		
		if(value!=null && !"".trim().equals(value))
			return true;
		else 
			return false;
	}
	
	/**
	 * Atribui um novo valor à ação.
	 * @param value String Novo valor utilizado na ação
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Retorna o valor mínimo atribuído à ação NCL.
	 * @return Valor mínimo
	 */
	public Integer getMin() {
		return min;
	}
	
	/**
	 * Atribui um novo valor mínimo para a ação NCL.
	 * @param min String Novo valor mínim
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * Retorna o valor mínimo atribuído à ação NCL.
	 * @return String Valor máximo
	 */
	public Integer getMax() {
		return max;
	}

	/**
	 * Atribui um novo valor máximo para a ação NCL.
	 * @param max String Novo valor máximo
	 */
	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * Retorna o operador da ação.
	 * @return Operador da ação
	 */
	public NCLActionOperator getQualifier() {
		return qualifier;
	}

	/**
	 * Atribui um novo operador a ação (seealso NCLActionOperator).
	 * @param qualifier Operador da ação
	 */
	public void setQualifier(NCLActionOperator qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * Retorna o papel utilizado na ação.
	 * @return Papel
	 */
	public NCLActionRole getRole() {
		return role;
	}

	/**
	 * Atribui um novo papel à ação (seealso NCLActionRole).
	 * @param role Novo papel
	 */
	public void setRole(NCLActionRole role) {
		this.role = role;
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

		content = space + "<simpleAction";

		content += " role='" + getValue() + "'";

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
	
}
