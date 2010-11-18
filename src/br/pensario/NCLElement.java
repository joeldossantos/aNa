package br.pensario;

public abstract class NCLElement {

	/**
	 * Cria o código XML do elemento NCL
	 * 
	 * @param ident Inteiro indicando o nível de identação.
	 * @return String com o código XML do elemento.
	 */
	public abstract String parse(int ident);
}
