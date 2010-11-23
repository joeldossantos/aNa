package br.pensario.connector;

import br.pensario.NCLValues.NCLConditionOperator;
import br.pensario.NCLValues.NCLDefaultConditionRole;
import br.pensario.NCLValues.NCLEventTransition;
import br.pensario.NCLValues.NCLEventType;
import br.pensario.NCLValues.NCLKey;


/**
 * Esta classe define o elemento <i>simpleCondition</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma condição simples de um conector de um documento NCL.<br>
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
public class NCLSimpleCondition extends NCLCondition {

    private NCLKey key;
    private Integer min;
    private Integer max;
    private NCLConditionOperator qualifier;
    private NCLEventType eventType;
    private NCLEventTransition transition;
    private NCLRole role;
    
    
    /**
     * Determina o número mínimo de binds que devem usar essa condição.
     * 
     * @param min
     *          inteiro representando o número mínimo.
     */
    public void setMin(Integer min) {
        this.min = min;
    }


    /**
     * Retorna o número mínimo de binds que devem usar essa condição.
     *
     * @return
     *          inteiro representando o número mínimo.
     */    
    public Integer getMin() {
        return min;
    }
    

    /**
     * Determina o número máximo de binds que devem usar essa condição.
     *
     * @param max
     *          inteiro representando o número máximo.
     */
    public void setMax(Integer max) {
        this.max = max;
    }


    /**
     * Retorna o número máximo de binds que devem usar essa condição.
     *
     * @return
     *          inteiro representando o número máximo.
     */
    public Integer getMax() {
        return max;
    }


    /**
     * Determina como serão avaliados o conjunto de binds que usam essa condição.
     *
     * @param qualifier
     *          operador lógico que representa como os binds serão avaliados.
     */
    public void setQualifier(NCLConditionOperator qualifier) {
        this.qualifier = qualifier;
    }


    /**
     * Retorna como serão avaliados o conjunto de binds que usam essa condição.
     *
     * @return
     *          operador lógico que representa como os binds serão avaliados.
     */
    public NCLConditionOperator getQualifier() {
        return qualifier;
    }


    /**
     * Determina o nome do papel de condição seguindo um dos nomes padrões.
     *
     * @param role
     *          elemento representando o nome do papel.
     */
    public void setRole(NCLDefaultConditionRole role) {
        try {
            NCLRole r = new NCLRole(role.toString());
            this.role = r;
        }
        catch (IllegalArgumentException ex){}
    }
    
    
    /**
     * Determina o nome do papel de condição.
     *
     * @param role
     *          String representando o nome do papel.
     * @throws java.lang.IllegalArgumentException
     *          Se o nome a ser atribuído for uma String vazia.
     */
    public void setRole(String role) throws IllegalArgumentException {
            NCLRole r = new NCLRole(role);
            this.role = r;
    }


    /**
     * Retorna o papel utilizado na condição.
     *
     * @return
     *          elemento representando o papel.
     */
    public NCLRole getRole() {
        return role;
    }


    /**
     * Determina a tecla da condição.
     *
     * @param key
     *          elemento representando a tecla da condição.
     */
    public void setKey(NCLKey key) {
        this.key = key;
    }


    /**
     * Retorna a tecla da condição.
     *
     * @return
     *          elemento representando a tecla da condição.
     */
    public NCLKey getKey() {
        return key;
    }


    /**
     * Determina o tipo do evento da condição.
     *
     * @param eventType
     *          elemento representando o tipo do evento da condição.
     */
    public void setEventType(NCLEventType eventType) {
        this.eventType = eventType;
    }


    /**
     * Retorna o tipo do evento da condição.
     *
     * @return
     *          elemento representando o tipo do evento da condição.
     */
    public NCLEventType getEventType() {
        return eventType;
    }


    /**
     * Determina a transição do evento da condição.
     *
     * @param transition
     *          elemento representando a transição do evento da condição.
     */
    public void setTransition(NCLEventTransition transition) {
        this.transition = transition;
    }


    /**
     * Retorna a transição do evento da condição.
     *
     * @return
     *          elemento representando a transição do evento da condição.
     */
    public NCLEventTransition getTransition() {
        return transition;
    }


    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleCondition";

        content += " role='" + getRole().getName() + "'";
        
        if(getKey() != null)
            content += " key='" + getKey().toString() + "'";

        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";

        if(getMin() != null)
            content += " min='" + getMin() + "'";        
        
        if(getMax() != null)
            content += " max='" + getMax() + "'";
            
        if(getQualifier() != null)
            content += " qualifier='" + getQualifier().toString() + "'";

        if (getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";

        if (getTransition() != null)
            content += " transition='" + getTransition().toString() + "'";
        
        content += " />\n";

        return content;
    }

    
    public int compareTo(NCLCondition other) {
        int comp = 0;

        String this_cond, other_cond;
        NCLSimpleCondition other_simp = (NCLSimpleCondition) other;

        // Verifica se sao do mesmo tipo
        if (!(other instanceof NCLSimpleCondition))
            comp = -1;

        // Compara pelo role
        if (getRole() == null) this_cond = ""; else this_cond = getRole().getName();
        if (other_simp.getRole() == null) other_cond = ""; else other_cond = other_simp.getRole().getName();
        comp = this_cond.compareTo(other_cond);

        // Compara pelo número mínimo
        if (comp == 0){
            int this_min, other_min;
            if (getMin() == null) this_min = 0; else this_min = getMin();
            if (other_simp.getMin() == null) other_min = 0; else other_min = other_simp.getMin();
            comp = this_min - other_min;
        }

        // Compara pelo número máximo
        if (comp == 0){
            int this_max, other_max;
            if (getMax() == null) this_max = 0; else this_max = getMax();
            if (other_simp.getMax() == null) other_max = 0; else other_max = other_simp.getMax();
            comp = this_max - other_max;
        }

        // Compara pelo delay
        if (comp == 0){
            int this_del, other_del;
            if (getDelay() == null) this_del = 0; else this_del = getDelay();
            if (other_simp.getDelay() == null) other_del = 0; else other_del = other_simp.getDelay();
            comp = this_del - other_del;
        }

        // Compara pelo qualifier
        if (comp == 0){
            if (getQualifier() == null) this_cond = ""; else this_cond = getQualifier().toString();
            if (other_simp.getQualifier() == null) other_cond = ""; else other_cond = other_simp.getQualifier().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pelo tecla
        if (comp == 0){
            if (getKey() == null) this_cond = ""; else this_cond = getKey().toString();
            if (other_simp.getKey() == null) other_cond = ""; else other_cond = other_simp.getKey().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pelo tipo do evento
        if (comp == 0){
            if (getEventType() == null) this_cond = ""; else this_cond = getEventType().toString();
            if (other_simp.getEventType() == null) other_cond = ""; else other_cond = other_simp.getEventType().toString();
            comp = this_cond.compareTo(other_cond);
        }

        // Compara pela transicao do evento
        if (comp == 0){
            if (getTransition() == null) this_cond = ""; else this_cond = getTransition().toString();
            if (other_simp.getTransition() == null) other_cond = ""; else other_cond = other_simp.getTransition().toString();
            comp = this_cond.compareTo(other_cond);
        }


        return comp;
    }
}
