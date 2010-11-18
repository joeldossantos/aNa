package br.pensario.node;

import br.pensario.NCLIdentifiableElement;


public abstract class NCLNode extends NCLIdentifiableElement implements Comparable<NCLNode> {
	
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
