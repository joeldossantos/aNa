package br.pensario.connector;

import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLValues.NCLConditionOperator;


/**
 * Esta classe define o elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma condição composta de um conector de um documento NCL.<br>
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
public class NCLCompoundCondition extends NCLCondition {
    
    private NCLConditionOperator operator;
    
    private Set<NCLCondition> conditions = new TreeSet<NCLCondition>();
    private Set<NCLStatement> statements = new TreeSet();
    
    
    /**
     * Determina o operador da condição composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLConditionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a condição composta.
     *
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLConditionOperator getOperator() {
        return operator;
    }


    /**
     * Adiciona uma condição a condição composta.
     * 
     * @param condition
     *          elemento representando a condição a ser adicionada
     * @return
     *          verdadeiro se a condição foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addCondition(NCLCondition condition) {
        return conditions.add(condition);
    }


    /**
     * Remove uma condição a condição composta.
     *
     * @param condition
     *          elemento representando a condição a ser removida
     * @return
     *          verdadeiro se a condição foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeCondition(NCLCondition condition) {
        return conditions.remove(condition);
    }

    
    /**
     * Verifica se a condição composta possui uma condição.
     * 
     * @param condition
     *          elemento representando a condição a ser verificada
     * @return
     *          verdadeiro se a condição existe.
     */
    public boolean hasCondition(NCLCondition condition) {
        return conditions.contains(condition);
    }

    
    /**
     * Verifica se a condição composta possui alguma condição.
     *
     * @return
     *          verdadeiro se a condição composta possui alguma condição.
     */
    public boolean hasConditions() {
        return !conditions.isEmpty();
    }


    /**
     * Retorna as condições da condição composta.
     *
     * @return
     *          objeto Iterable contendo as condições da condição composta.
     */
    public Iterable<NCLCondition> getConditions() {
        return conditions;
    }
    
    
    /**
     * Adiciona uma assertiva a condição composta.
     *
     * @param statement
     *          elemento representando a assertiva a ser adicionada.
     * @return
     *          verdadeiro se a assertiva foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addStatement(NCLStatement statement) {
        return statements.add(statement);
    }


    /**
     * Remove uma assertiva da condição composta.
     *
     * @param statement
     *          elemento representando a assertiva a ser removida.
     * @return
     *          verdadeiro se a assertiva foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeStatement(NCLStatement statement) {
        return statements.remove(statement);
    }


    /**
     * Verifica se a condição composta possui uma assertiva.
     *
     * @param statement
     *          elemento representando a assertiva a ser verificada.
     * @return
     *          verdadeiro se a assertiva existe.
     */
    public boolean hasStatement(NCLStatement statement) {
        return statements.contains(statement);
    }


    /**
     * Verifica se a condição composta possui pelo menos uma assertiva.
     *
     * @return
     *          verdadeiro se a condição composta possuir pelo menos uma assertiva.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }


    /**
     * Retorna as assertivas da condição composta.
     *
     * @return
     *          objeto Iterable contendo as assertivas da condição composta.
     */
    public Iterable<NCLStatement> getStatements() {
        return statements;
    }

    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundCondition";

        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";        
        
        content += ">\n";
        
        for(NCLCondition condition : conditions)
            content += condition.parse(ident + 1);
        
        for(NCLStatement statement : statements)
            content += statement.parse(ident + 1);
        
        content += space + "</compoundCondition>\n";

        return content;
    }
    
    
    public int compareTo(NCLCondition other) {
        int comp = 0;

        String this_cond, other_cond;
        NCLCompoundCondition other_comp = (NCLCompoundCondition) other;

        // Verifica se sao do mesmo tipo
        if (!(other instanceof NCLCompoundCondition))
            comp = 1;

        // Compara pelo operador
        if (comp == 0){
            if (getOperator() == null) this_cond = ""; else this_cond = getOperator().toString();
            if (other_comp.getOperator() == null) other_cond = ""; else other_cond = other_comp.getOperator().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pelo delay
        if (comp == 0){
            int this_del, other_del;
            if (getDelay() == null) this_del = 0; else this_del = getDelay();
            if (other_comp.getDelay() == null) other_del = 0; else other_del = other_comp.getDelay();
            comp = this_del - other_del;
        }

        // Compara o número de condicoes
        if (comp == 0)
            comp = conditions.size() - ((Set) other_comp.getConditions()).size();

        // Compara as condicoes
        if (comp == 0){
            NCLCondition conds[] = (NCLCondition[]) conditions.toArray();
            NCLCondition other_conds[] = (NCLCondition[]) ((Set) other_comp.getConditions()).toArray();

            for (int i = 0; i < conds.length; i++){
                comp = conds[i].compareTo(other_conds[i]);
                if (comp != 0)
                    break;
            }
        }

        // Compara o número de statements
        if (comp == 0)
            comp = statements.size() - ((Set) other_comp.getStatements()).size();

        // Compara as statements
        if (comp == 0){
            NCLStatement stats[] = (NCLStatement[]) statements.toArray();
            NCLStatement other_stats[] = (NCLStatement[]) ((Set) other_comp.getStatements()).toArray();

            for (int i = 0; i < stats.length; i++){
                comp = stats[i].compareTo(other_stats[i]);
                if (comp != 0)
                    break;
            }
        }


        return comp;
    }
}
