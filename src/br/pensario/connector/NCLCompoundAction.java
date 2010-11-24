package br.pensario.connector;

import java.util.Set;

import br.pensario.NCLValues.NCLActionOperator;
import java.util.Iterator;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma ação composta de um conector de um documento NCL.<br>
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
public class NCLCompoundAction extends NCLAction {

    private NCLActionOperator operator;
    
    private Set<NCLAction> actions; 
    
    
    /**
     * Determina o operador da ação composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLActionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a ação composta.
     *
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLActionOperator getOperator() {
        return operator;
    }

    
    /**
     * Adiciona uma ação a ação composta.
     *
     * @param action
     *          elemento representando a ação a ser adicionada
     * @return
     *          verdadeiro se a ação foi adicionada.
     *
     * @see TreeSet#add(java.lang.Object) 
     */
    public boolean addAction(NCLAction action) {
        return actions.add(action);
    }


    /**
     * Remove uma ação a ação composta.
     *
     * @param action
     *          elemento representando a ação a ser removida
     * @return
     *          verdadeiro se a ação foi removida.
     *
     * @see TreeSet#remove(java.lang.Object)
     */
    public boolean removeAction(NCLAction action) {
        return actions.remove(action);
    }


    /**
     * Verifica se a ação composta possui uma ação.
     *
     * @param action
     *          elemento representando a ação a ser verificada
     * @return
     *          verdadeiro se a ação existe.
     */
    public boolean hasAction(NCLAction action) {
        return actions.contains(action);
    }


    /**
     * Verifica se a ação composta possui alguma ação.
     *
     * @return
     *          verdadeiro se a ação composta possui alguma ação.
     */
    public boolean hasActions() {
        return !actions.isEmpty();
    }


    /**
     * Retorna as ações da ação composta.
     *
     * @return
     *          objeto Iterable contendo as ações da ação composta.
     */
    public Iterable<NCLAction> getActions() {
        return actions;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundAction";

        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";        
        
        content += ">\n";
        
        for(NCLAction action : actions)
            content += action.parse(ident + 1);
        
        content += space + "</compoundAction>";

        return content;
    }


    public int compareTo(NCLAction other) {
        int comp = 0;

        String this_act, other_act;
        NCLCompoundAction other_comp = (NCLCompoundAction) other;

        // Verifica se sao do mesmo tipo
        if (!(other instanceof NCLCompoundAction))
            comp = 1;

        // Compara pelo operador
        if (comp == 0){
            if (getOperator() == null) this_act = ""; else this_act = getOperator().toString();
            if (other_comp.getOperator() == null) other_act = ""; else other_act = other_comp.getOperator().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo delay
        if (comp == 0){
            int this_del, other_del;
            if (getDelay() == null) this_del = 0; else this_del = getDelay();
            if (other_comp.getDelay() == null) other_del = 0; else other_del = other_comp.getDelay();
            comp = this_del - other_del;
        }

        // Compara o número de acoes
        if (comp == 0)
            comp = actions.size() - ((Set) other_comp.getActions()).size();

        // Compara as acoes
        if (comp == 0){
            Iterator it = other_comp.getActions().iterator();
            for (NCLAction a : actions){
                NCLAction other_a = (NCLAction) it.next();
                comp = a.compareTo(other_a);
                if (comp != 0)
                    break;
            }
        }


        return comp;
    }
}
