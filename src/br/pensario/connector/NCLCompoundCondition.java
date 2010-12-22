package br.pensario.connector;

import br.pensario.NCLElement;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLValues.NCLConditionOperator;
import java.util.Iterator;


/**
 * Esta classe define o elemento <i>compoundCondition</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma condição composta de um conector de um documento NCL.<br>
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
public class NCLCompoundCondition<C extends NCLCondition, S extends NCLStatement, P extends NCLConnectorParam> extends NCLElement implements NCLCondition<C, P> {
    
    private NCLConditionOperator operator;
    
    private Set<C> conditions = new TreeSet<C>();
    private Set<S> statements = new TreeSet<S>();
    private Integer delay;
    private P parDelay;
    
    
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
    public boolean addCondition(C condition) {
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
    public boolean removeCondition(C condition) {
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
    public boolean hasCondition(C condition) {
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
    public Iterable<C> getConditions() {
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
    public boolean addStatement(S statement) {
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
    public boolean removeStatement(S statement) {
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
    public boolean hasStatement(S statement) {
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
    public Iterable<S> getStatements() {
        return statements;
    }


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if (delay != null && delay < 0)
            throw new IllegalArgumentException("Invalid delay");

        this.delay = delay;
        this.parDelay= null;
    }


    public void setDelay(P delay) {
        this.parDelay = delay;
        this.delay = null;
    }


    public Integer getDelay() {
        return delay;
    }


    public P getParamDelay() {
        return parDelay;
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
        content += " operator='" + getOperator() + "'";

        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";
        if(getParamDelay() != null)
            content += " delay='$" + getParamDelay() + "'";
        
        content += ">\n";
        
        for(C condition : conditions)
            content += condition.parse(ident + 1);
        
        for(S statement : statements)
            content += statement.parse(ident + 1);

        content += space + "</compoundCondition>\n";

        return content;
    }
    
    
    public int compareTo(C other) {
        //retorna 0 se forem iguais e 1 se forem diferentes (mantem a ordem de insercao)
        int comp = 0;

        String this_cond, other_cond;
        NCLCompoundCondition other_comp;

        // Verifica se sao do mesmo tipo
        if (!(other instanceof NCLCompoundCondition))
            return 1;

        other_comp = (NCLCompoundCondition) other;
        
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

        // Compara pelo delay (parametro)
        if(comp == 0){
            if(getParamDelay() == null && other_comp.getParamDelay() == null)
                comp = 0;
            else if(getParamDelay() != null && other_comp.getParamDelay() != null)
                comp = getParamDelay().compareTo(other_comp.getParamDelay());
            else
                comp = 1;
        }

        // Compara o número de condicoes
        if (comp == 0)
            comp = conditions.size() - ((Set) other_comp.getConditions()).size();

        // Compara as condicoes
        if (comp == 0){
            Iterator it = other_comp.getConditions().iterator();
            for (NCLCondition c : conditions){
                NCLCondition other_c = (NCLCondition) it.next();
                comp = c.compareTo(other_c);
                if (comp != 0)
                    break;
            }
        }

        // Compara o número de statements
        if (comp == 0)
            comp = statements.size() - ((Set) other_comp.getStatements()).size();

        // Compara as statements
        if (comp == 0){
            Iterator it = other_comp.getStatements().iterator();
            for (NCLStatement st : statements){
                NCLStatement other_st = (NCLStatement) it.next();
                comp = st.compareTo(other_st);
                if (comp != 0)
                    break;
            }
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }
}
