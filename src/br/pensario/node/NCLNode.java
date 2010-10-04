package br.pensario.node;

import br.pensario.NCLIdentification;

public abstract class NCLNode {
	
	private String id;
	
	
	/**
	 * Determina o id de um nó.
	 * 
	 * @param id String com o id do nó.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public void setId(String id) throws Exception {
		NCLIdentification.validate(id);
		this.id = id;
	}
	
	
	/**
	 * Obtém o id do nó.
	 * 
	 * @return String com o id do nó.
	 */
	public String getId() {
		return id;
	}
	
	
	/**
	 * Cria o código XML de um nó.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML do nó.
	 */
	public abstract String parse(int ident);
}
