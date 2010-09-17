package br.pensario.interfaces;

public abstract class NCLInterface {
	
	/**
	 * This method returns the name if the element is a <property> or the id in the other cases.
	 * @return String with the name or id
	 */
	public abstract String getIdentifier();
	
	public abstract String parse(int ident);
}
