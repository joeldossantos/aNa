package br.pensario.interfaces;

import br.pensario.NCLIdentifiable;

public abstract class NCLInterface extends NCLIdentifiable {
	
	/**
	 * Cria o código XML de uma interface.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML da interface.
	 */
	public abstract String parse(int ident);
}
