package br.pensario.connector;

public abstract class NCLCondition implements Comparable<NCLCondition> {

    private int delay = -1;    
    
    
    /**
     * Atribui um atraso à condição.
     * 
     * @param delay int de atraso, em segundos.
     */
    public void setDelay(int delay) throws IllegalArgumentException {
        if (delay < 0)
            throw new IllegalArgumentException("Invalid delay");
        
        this.delay = delay;
    }
    
    
    /**
     * Retorna o atraso em relação a condição
     * @return String Atraso em milisegundos
     */
    public int getDelay() {
        return delay;
    }
    
    
    /**
     * Verifica se a condição tem um delay.
     * 
     * @return Verdadeiro se possuir delay.
     */
    public boolean hasDelay() {
        return (delay != -1);
    }
    
    
    public abstract String parse(int ident);
    public abstract String toString();
}
