package br.pensario.rule;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLValues.NCLOperator;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define uma regra de teste composta da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLCompoundRule<T extends NCLTestRule> extends NCLIdentifiableElement implements NCLTestRule<T> {

    private NCLOperator operator;
    private Set<T> rules = new TreeSet<T>();


    /**
     * Atribui um operador a regra composta.
     *
     * @param operator
     *          elemento representando o operador da regra composta.
     */
    public void setOperator(NCLOperator operator) {
        this.operator = operator;
    }


    /**
     * Retorna o operador da regra composta.
     *
     * @return
     *          elemento representando o operador da regra composta.
     */
    public NCLOperator getOperator() {
        return operator;
    }


    /**
     * Adiciona uma regra a regra composta.
     *
     * @param rule
     *          elemento representando a regra a ser adicionada.
     * @return
     *          verdadeiro se a regra foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRule(T rule) {
        return rules.add(rule);
    }


    /**
     * Remove uma regra da regra composta.
     *
     * @param rule
     *          elemento representando a regra a ser removida.
     * @return
     *          verdadeiro se a regra foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRule(T rule) {
        return rules.remove(rule);
    }


    /**
     * Verifica se a regra composta possui uma regra.
     *
     * @param rule
     *          elemento representando a regra a ser verificada.
     * @return
     *          verdadeiro se a regra existir.
     */
    public boolean hasRule(T rule) {
        return rules.contains(rule);
    }


    /**
     * Verifica se a regra composta possui alguma regra.
     *
     * @return
     *          verdadeiro se a regra composta possui alguma regra.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Retorna as regras da regra composta.
     *
     * @return
     *          objeto Iterable contendo as regras da regra composta.
     */
    public Iterable<T> getRules() {
        return rules;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<compositeRule";
        content += " id='" + getId() + "'";
        content += " operator='" + getOperator() + "'";
        content += ">\n";

        if(hasRule()){
            for(T rule : rules)
                content += rule.parse(ident + 1);
        }

        content += "</compositeRule>\n";

        return content;
    }


    public int compareTo(T other) {
        return getId().compareTo(other.getId());
    }
}
