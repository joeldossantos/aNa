package br.pensario.connector;

public abstract class NCLCondition implements Comparable<NCLCondition> {

	private String delay;	
	
	
	/**
	 * Retorna o atraso em relação a condição
	 * @return String Atraso em milisegundos
	 */
	public String getDelay() {
		return delay;
	}

	/**
	 * Atribui um atraso à condição.
	 * 
	 * @param delay String Atraso.
	 */
	public void setDelay(String delay) {
		this.delay = delay;
	}
	
	public abstract String parse(int ident);
	public abstract String toString();

	
	
}
