package br.pensario.interfaces;

public abstract class NCLInterface {
	
	/**
	 * Obtém o atributo que identifica uma interface. O nome de uma propriedade ou o id nos demais casos.
	 * 
	 * @return String com o nome ou o id.
	 */
	public abstract String getIdentifier();
	
	
	/**
	 * Cria o código XML de uma interface.
	 * 
	 * @param ident Inteiro indicando o nível de indentação.
	 * @return String com o código XML da interface.
	 */
	public abstract String parse(int ident);
}
