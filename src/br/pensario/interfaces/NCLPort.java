package br.pensario.interfaces;

import br.pensario.NCLIdentification;
import br.pensario.node.NCLNode;

/**
 * Classe que representa o elemento porta da linguagem NCL.
 */
public class NCLPort extends NCLInterface implements Comparable<NCLPort> {

	private String id;
	private NCLNode component;
	private NCLInterface interfac;
	
	
	/**
	 * Construtor da porta.
	 * 
	 * @param id id da porta.
	 * @param component componente que a porta mapeará.
	 * @throws IllegalArgumentException se o id for inválido ou NullPointerException se o componente for nulo.
	 */
	public NCLPort(String id, NCLNode component) throws Exception {
		setId(id);
		setComponent(component);
	}
	
	
	/**
	 * Determina o id de uma porta.
	 * 
	 * @param id String com o id da porta.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	
	/**
	 * Obtém o id da porta.
	 * 
	 * @return String com o id da porta.
	 */
	public String getId() {
		return id.toString();
	}
	
	
	/**
	 * Obtém o atributo que identifica uma porta, neste caso o id da porta.
	 * 
	 * @return String com o id da porta.
	 */
	public String getIdentifier(){
		return getId();
	}
	
	
	/**
	 * Determina o conteúdo do atributo component da porta.
	 * 
	 * @param component componente relacionado pela porta.
	 * @throws NullPointerException se o componente for nulo.
	 */
	public void setComponent(NCLNode component) throws Exception {
		if (component != null){
			this.component = component;
		}
		else{
			Exception ex = new NullPointerException("Null component");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo component da porta.
	 * 
	 * @return valor do atributo.
	 */
	public NCLNode getComponent() {
		return component;
	}
	
	
	/**
	 * Determina o valor do atributo interface da porta.
	 * 
	 * @param interfac valor do atributo interface da porta.
	 * @throws NullPointerException se a interface for nula.
	 */
	public void setInterface(NCLInterface interfac) throws Exception {
		if (interfac != null){
			this.interfac = interfac;
		}
		else{
			Exception ex = new NullPointerException("Null component");
			throw ex;
		}
	}
	
	
	/**
	 * Obtém o valor do atributo interface da porta.
	 * 
	 * @return String com o valor do atributo.
	 */
	public NCLInterface getInterface() {
		return interfac;
	}
	
	
	/**
	 * Verifica se a porta possui o atributo interface.
	 * 
	 * @return True se a porta possuir o atributo interface.
	 */
	public boolean hasInterface() {
		return (interfac != null);
	}
	
	
	/**
	 * Compara uma porta com outra porta.
	 * 
	 * @param port porta com a qual comparar.
	 * @return True se as portas forem iguais.
	 */
	public boolean equals(NCLPort port) {
		if (getId().equals(port.getId()))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Cria o código XML de uma porta.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML da porta.
	 */
	public String parse(int ident) {
		String space, content;
		
		// Element indentation
		space = "";
		for (int i = 0; i < ident; i++)
			space += "\t";
		
		
		// <port> element and attributes declaration
		content = space + "<port";
		content += " id='" + getId() + "'";
		content += " component='" + getComponent().getId() + "'";
		if (hasInterface())
			content += " interface='" + getInterface().getIdentifier() + "'";
		content += "/>\n";
		
		return content;
	}
	
	
	/**
	 * Cria o código XML da porta sem indentação.
	 * 
	 * @return String com o código XML.
	 */
	public String toString() {
		return parse(0);
	}
	
	
	/**
	 * Compara uma porta com outra porta.
	 * 
	 * @param port porta com a qual comparar.
	 * @return Inteiro indicando se as portas são iguais. O valor será 0 se as portas forem iguais.
	 */
	public int compareTo(NCLPort port) {
		return getId().compareTo(port.getId());
	}
}
