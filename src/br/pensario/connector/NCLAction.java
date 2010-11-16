package br.pensario.connector;

public abstract class NCLAction {

	private String delay;
	
	/**
	 * Retorna o atraso da ação
	 * @return String Atraso em milisegundos
	 */
	//TODO - confirmar o formato do atraso;
	public String getDelay() {
		return delay;
	}
	
	/**
	 * Atribui um atraso à ação.
	 * 
	 * @param delay String Atraso.
	 */
	public void setDelay(String delay) {
		this.delay = delay;
	}
	
	public abstract String parse(int ident);
	public abstract String toString();
	
}
