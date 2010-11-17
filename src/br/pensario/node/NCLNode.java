package br.pensario.node;

import br.pensario.NCLIdentifiable;


public abstract class NCLNode extends NCLIdentifiable implements Comparable<NCLNode> {
	
	
	/**
	 * Determina o id de um nó.
	 * 
	 * @param id String com o id do nó.
	 * @throws IllegalArgumentException se o id for inválido.
	 */
	public void setId(String id) throws Exception {
		setIdentification(id);
	}
	
	
	/**
	 * Obtém o id do nó.
	 * 
	 * @return String com o id do nó.
	 */
	public String getId() {
		return getIdentification();
	}
	
	
	/**
	 * Cria o código XML de um nó.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML do nó.
	 */
	public abstract String parse(int ident);
	
	
	/**
	 * Determina se dois nós são iguais.
	 * 
	 * @param node nó com a qual comparar.
	 * @return Inteiro indicando se os nós são iguais. O valor será 0 se os nós forem iguais.
	 */
	public int compareTo(NCLNode node) {
		return getId().compareTo(node.getId());
	}
}
