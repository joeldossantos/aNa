package br.pensario.link;

import br.pensario.connector.NCLConnectorParam;

/**
 * Classe que representa o elemento parâmetro da linguagem NCL.
 */
public class NCLParam implements Comparable<NCLParam>{

	private NCLConnectorParam name;
	private String value;
	
	
	/**
	 * Construtor do parâmetro. Recebe o <connectorParam> ao qual se refere.
	 * 
	 * @param connectorParam representa o nome do parâmetro.
	 * @param value valor do atributo value do parâmetro.
	 * @throws NullPointerException se o nome ou o valor for nulo.
	 */
	protected NCLParam(NCLConnectorParam connectorParam, String value) throws Exception {
		setName(connectorParam);
		setValue(value);
	}
	
	
	/**
	 * Determina o nome do parâmetro.
	 * 
	 * @param connectorParam NCLConnectorParam representando o nome do parâmetro.
	 * @throws NullPointerException se o nome for nulo.
	 */
	private void setName(NCLConnectorParam connectorParam) throws Exception {
		if (connectorParam != null){
			this.name = connectorParam;
		}
		else{
			Exception ex = new NullPointerException("Null name");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo name do parâmetro.
	 * 
	 * @return NCLConnectorParam representando o nome do parâmetro.
	 */
	protected NCLConnectorParam getName() {
		return name;
	}
	
	
	/**
	 * Determina o valor do atributo value do parâmetro.
	 * 
	 * @param value String com o valor do atributo.
	 * @throws NullPointerException se o valor for nulo.
	 */
	private void setValue(String value) throws Exception {
		if (value != null){
			this.value = value;
		}
		else{
			Exception ex = new NullPointerException("Null value");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo value do parâmetro.
	 * 
	 * @return String com o valor do atributo.
	 */
	protected String getValue() {
		return value;
	}
	
	
	/**
	 * Cria o código XML do parâmetro.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @param type tipo do parâmtro (linkParam ou bindParam).
	 * @return String com o código XML.
	 */
	protected String parse(int ident, String type) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <linkParam> element and attributes declaration
		content = space + "<" + type;
		content += " name='" + getName().getName() + "'";
		content += " value='" + getValue() + "'";
		content += "/>\n";
		
		return content;
	}
	
	
	/**
	 * Compara dois parâmetros.
	 * 
	 * @param param parâmetro com o qual comparar.
	 * @return Inteiro indicando se os parâmtros são iguais. O valor será 0 se os parâmtros forem iguais.
	 */
	public int compareTo(NCLParam param) {
		return getName().getName().compareTo(param.getName().getName());
	}
}
