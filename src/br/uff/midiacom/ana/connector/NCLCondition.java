package br.pensario.connector;

import br.pensario.Element;


/**
 * Esta classe define uma condição de um conector da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public interface NCLCondition<T, P extends NCLConnectorParam> extends Comparable<T>, Element {

    


    /**
     * Atribui um atraso à condição.
     *
     * @param delay
     *          inteiro contendo o atraso, em segundos.
     * @throws java.lang.IllegalArgumentException
     *          se o inteiro for negativo.
     */
    public void setDelay(Integer delay);


    /**
     * Atribui um atraso à condição.
     *
     * @param delay
     *          parâmetro representando o atraso.
     */
    public void setDelay(P delay);


    /**
     * Retorna o atraso atribuido à condição.
     *
     * @return
     *          inteiro contendo o atraso, em segundos.
     */
    public Integer getDelay();


    /**
     * Retorna o atraso atribuido à condição.
     *
     * @return
     *          parâmetro representando o atraso.
     */
    public P getParamDelay();
}
