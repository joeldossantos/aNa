package br.pensario.connector;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLIdentification;

public class NCLCausalConnector implements Comparable<NCLCausalConnector> {

	private String id;
	private NCLCondition condition;
	private NCLAction action;
	private Set<NCLConnectorParam> conn_params = new TreeSet<NCLConnectorParam>();

	/**
	 * Construtor padrão de um conector causal.
	 * @param id String Identificador do conector
	 * @param condition NCLCondition Condição NCL utilizada pelo conector causal.
	 * @param action NCLAction Ação NCL utilizada pelo conector causal.
	 * @throws Exception Caso o identificador seja inválido
	 */	
	public NCLCausalConnector(String id, NCLCondition condition,
			NCLAction action) throws Exception {
		this.setId(id);
		this.setCondition(condition);
		this.setAction(action);
	}


	/**
	 * Retorna condição NCL utilizada pelo conector causal NCL.
	 * @return Condição NCL
	 */	
	public NCLCondition getCondition() {
		return condition;
	}


	/**
	 * Atribui uma condição ao conector causal NCL.
	 * 
	 * @param condition NCLCondition Condição NCL.
	 * @throws IllegalArgumentException Se a condição for nula.
	 */	
	public void setCondition(NCLCondition condition) throws IllegalArgumentException{
		if(condition!=null)
			this.condition = condition;
		else
			throw new IllegalArgumentException("O conector causal não pode conter condição nula");
	}

	/**
	 * Retorna o identificador do elemento NCL.
	 * @return Identificador
	 */	
	public String getId() {
		return id;
	}	
	
	/**
	 * Atribui um id ao conector causal NCL.
	 * 
	 * @param id String Identificador (compatível com NCLIdentification).
	 * @throws IllegalArgumentException Se o id for inválido.
	 */	
	public void setId(String id) throws Exception{
		
		NCLIdentification.validate(id);
		this.id = id;		
	}

	
	/**
	 * Retorna ação NCL utilizada pelo conector causal NCL.
	 * @return Ação NCL
	 */
	public NCLAction getAction() {
		return action;
	}

	
	/**
	 * Atribui uma ação ao conector causal NCL.
	 * 
	 * @param action NCLAction Ação NCL.
	 * @throws IllegalArgumentException Se a ação for nula.
	 */	
	public void setAction(NCLAction action) {
		if(action != null)
			this.action = action;
		else
			throw new IllegalArgumentException("O conector causal não pode conter ação nula");
	}

	/**
	 * Retorna o parâmetro do conector NCL de acordo com o nome.	 
	 * 
	 * @param nome String Nome do parâmetro a ser buscado.
	 * @return Parâmetro do conector
	 */	
	public NCLConnectorParam getConnectorParam(String nome) {

		for (NCLConnectorParam connp : conn_params)
			if (connp.getName().equals(nome))
				return connp;

		return null;
	}

	
	/**
	 * Remove um parâmetro do conector NCL de acordo com o nome.	 
	 * 
	 * @param nome String Nome do parâmetro a ser removido.
	 */	
	public void removeConnectorParam(String nome) {

		Iterator<NCLConnectorParam> it = conn_params.iterator();

		while (it.hasNext()) {
			NCLConnectorParam connp = it.next();
			if (connp.getName().equals(nome))
				it.remove();
		}
		
	}

	/**
	 * Adiciona um novo parâmetro NCL ao conector causal NCL.	 
	 * 
	 * @param param NCLConnectorParam Parâmetro a ser adicionado.
	 */	
	public void addConnectorParam(NCLConnectorParam param) {

		conn_params.add(param);
	}

	public String toString() {
		return parse(0);
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

		content = space + "<causalConnector";

		if (getId() != null)
			content += " id='" + getId() + "'";

		content += ">\n";

		for(NCLConnectorParam connp : conn_params)
			content += connp.parse(ident + 1);
		
		content += getCondition().parse(ident + 1);
		content += getAction().parse(ident + 1);

		content += space + "<causalConnector/>\n";

		return content;
	}

	@Override
	public int compareTo(NCLCausalConnector cconn) {

		return cconn.getId().compareTo(this.getId());

	}

}
