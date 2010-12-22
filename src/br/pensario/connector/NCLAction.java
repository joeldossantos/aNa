package br.pensario.connector;

import br.pensario.Element;


/**
 * Esta classe define uma ação de um conector da <i>Nested Context Language</i> (NCL).<br>
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
public interface NCLAction<T, P extends NCLConnectorParam> extends Comparable<T>, Element {

    /**
     * Atribui um atraso à ação.
     * 
     * @param delay
     *          inteiro contendo o atraso, em segundos.
     * @throws java.lang.IllegalArgumentException
     *          se o inteiro for negativo.
     */
    public void setDelay(Integer delay);


    /**
     * Atribui um atraso à ação.
     *
     * @param delay
     *          parâmetro representando o atraso.
     */
    public void setDelay(P delay);
    
    
    /**
     * Retorna o atraso atribuido à ação.
     *
     * @return
     *          inteiro contendo o atraso, em segundos.
     */
    public Integer getDelay();


    /**
     * Retorna o atraso atribuido à ação.
     *
     * @return
     *          parâmetro representando o atraso.
     */
    public P getParamDelay();
}
