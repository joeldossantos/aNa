package br.pensario.descriptor;

import br.pensario.NCLElement;
import br.pensario.rule.NCLTestRule;


/**
 * Esta classe define o elemento <i>bindRule</i> de um switch de descritor da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um bind de um switch de descritor de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.1
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLBindRule<B extends NCLBindRule, D extends NCLDescriptor, R extends NCLTestRule> extends NCLElement implements Comparable<B> {

    private D constituent;
    private R rule;


    /**
     * Atribui um constituent ao bind.
     *
     * @param constituent
     *          elemento representando o descritor mapeado pelo bind.
     */
    public void setConstituent(D constituent) {
        this.constituent = constituent;
    }


    /**
     * Retorna o constituent do bind.
     *
     * @return
     *          elemento representando o descritor mapeado pelo bind.
     */
    public D getConstituent() {
        return constituent;
    }


    /**
     * Atribui uma regra de avaliação ao bind.
     *
     * @param rule
     *          elemento representando a regra de avaliação do bind.
     */
    public void setRule(R rule) {
        this.rule = rule;
    }


    /**
     * Retorna a regra de avaliação do bind.
     * 
     * @return
     *          elemento representando a regra de avaliação do bind.
     */
    public R getRule() {
        return rule;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<bindRule";
        if(getRule() != null)
            content += " rule='" + getRule().getId() + "'";
        if(getConstituent() != null)
            content += " constituent='" + getConstituent().getId() + "'";
        content += "/>\n";


        return content;
    }


    public int compareTo(B other) {
        //retorna 0 se forem iguais e 1 se forem diferentes (mantem a ordem de insercao)
        int comp = 0;

        // Compara pela regra
        comp = getRule().compareTo(other.getRule());

        // Compara pelo constituent
        if(comp == 0){
            comp = getConstituent().compareTo(other.getConstituent());
        }

        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getRule() != null);
        valid &= (getConstituent() != null);

        //TODO validar se o constituent é do mesmo switch

        return valid;
    }
}