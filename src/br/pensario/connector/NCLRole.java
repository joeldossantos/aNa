package br.pensario.connector;


/**
 * Esta classe define um papel de conector da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLRole {

    private String name;
    
    
    /**
     * Construtor do papel.
     * 
     * @param name
     *          String com o nome do papel.
     */
    public NCLRole(String name) {
        setName(name);
    }


    /**
     * Determina o nome do papel
     *
     * @param name
     *          String com o nome do papel.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    /**
     * Retorna o nome do papel
     * 
     * @return
     *          String com o nome do papel.
     */
    public String getName() {
        return name;
    }
    
}
