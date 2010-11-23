package br.pensario.connector;

import br.pensario.NCLValues.NCLActionOperator;
import br.pensario.NCLValues.NCLDefaultActionRole;
import br.pensario.NCLValues.NCLEventAction;
import br.pensario.NCLValues.NCLEventType;


/**
 * Esta classe define o elemento <i>simpleAction</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma ação simples de um conector de um documento NCL.<br>
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
public class NCLSimpleAction extends NCLAction {

    private String value;
    private Integer min;
    private Integer max;
    private NCLActionOperator qualifier;
    private NCLEventType eventType;
    private NCLEventAction actionType;
    private Integer repeat;
    private Integer repeatDelay;
    private Integer duration;
    private Integer by;
    private NCLRole role;


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          String representando o valor de atribuição.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value) {
        if (value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
    }
    
        
    /**
     * Retorna o valor de atribuição da ação.
     *
     * @return
     *          String representando o valor de atribuição.
     */
    public String getValue() {
        return value;
    }
    
    
    /**
     * Determina o número mínimo de binds que devem usar essa ação.
     *
     * @param min
     *          inteiro representando o número mínimo.
     */
    public void setMin(Integer min) {
        this.min = min;
    }


    /**
     * Retorna o número mínimo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro representando o número mínimo.
     */
    public Integer getMin() {
        return min;
    }


    /**
     * Determina o número máximo de binds que devem usar essa ação.
     *
     * @param max
     *          inteiro representando o número máximo.
     */
    public void setMax(Integer max) {
        this.max = max;
    }


    /**
     * Retorna o número máximo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro representando o número máximo.
     */
    public Integer getMax() {
        return max;
    }


    /**
     * Determina como serão disparados o conjunto de binds que usam essa ação.
     *
     * @param qualifier
     *          operador que representa como os binds serão disparados.
     */
    public void setQualifier(NCLActionOperator qualifier) {
        this.qualifier = qualifier;
    }


    /**
     * Retorna como serão disparados o conjunto de binds que usam essa ação.
     *
     * @return
     *          operador que representa como os binds serão disparados.
     */
    public NCLActionOperator getQualifier() {
        return qualifier;
    }


    /**
     * Determina o nome do papel de ação seguindo um dos nomes padrões.
     *
     * @param role
     *          elemento representando o nome do papel.
     */
    public void setRole(NCLDefaultActionRole role) {
        try {
            NCLRole r = new NCLRole(role.toString());
            this.role = r;
        }
        catch (IllegalArgumentException ex){}
    }


    /**
     * Determina o nome do papel de ação.
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
     * Retorna o papel utilizado na ação.
     *
     * @return
     *          elemento representando o papel.
     */
    public NCLRole getRole() {
        return role;
    }


    /**
     * Determina o tipo do evento da ação.
     *
     * @param eventType
     *          elemento representando o tipo do evento da ação.
     */
    public void setEventType(NCLEventType eventType) {
        this.eventType = eventType;
    }


    /**
     * Retorna o tipo do evento da ação.
     *
     * @return
     *          elemento representando o tipo do evento da ação.
     */
    public NCLEventType getEventType() {
        return eventType;
    }


    /**
     * Determina a ação do evento.
     *
     * @param actionType
     *          elemento representando a ação do evento.
     */
    public void setActionType(NCLEventAction actionType) {
        this.actionType = actionType;
    }


    /**
     * Retorna o tipo do evento da ação.
     *
     * @return
     *          elemento representando o tipo do evento da ação.
     */
    public NCLEventAction getActionType() {
        return actionType;
    }


    /**
     * Determina o número de repetições da ação.
     *
     * @param repeat
     *          inteiro representando o número de repetições.
     */
    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }


    /**
     * Retorna o número de repetições da ação.
     *
     * @return
     *          inteiro representando o número de repetições.
     */
    public Integer getRepeat() {
        return repeat;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          inteiro representando o delay entre repetições.
     */
    public void setRepeatDelay(Integer repeatDelay) {
        this.repeatDelay = repeatDelay;
    }


    /**
     * Retorna o delay entre repetições da ação.
     *
     * @return
     *          inteiro representando o delay entre repetições.
     */
    public Integer getRepeatDelay() {
        return repeatDelay;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          inteiro representando a duração da atribuição.
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    /**
     * Retorna a duração da ação de atribuição.
     *
     * @return
     *          inteiro representando a duração da atribuição.
     */
    public Integer getDuration() {
        return duration;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          inteiro representando o passo da atribuição.
     */
    public void setBy(Integer by) {
        this.by = by;
    }


    /**
     * Retorna o passo da ação de atribuição.
     *
     * @return
     *          inteiro representando o passo da atribuição.
     */
    public Integer getBy() {
        return by;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleAction";

        content += " role='" + getRole().getName() + "'";

        if(getValue() != null)
            content += " value='" + getValue() + "'";

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

        if (getActionType() != null)
            content += " actionType='" + getActionType().toString() + "'";

        if(getRepeat() != null)
            content += " repeat='" + getRepeat() + "'";

        if(getRepeatDelay() != null)
            content += " repeatDelay='" + getRepeatDelay() + "'";

        if(getDuration() != null)
            content += " duration='" + getDuration() + "'";

        if(getBy() != null)
            content += " by='" + getBy() + "'";
        
        content += " />\n";

        return content;
    }


    public int compareTo(NCLAction other) {
        int comp = 0;

        String this_act, other_act;
        int this_ac, other_ac;
        NCLSimpleAction other_simp = (NCLSimpleAction) other;

        // Verifica se sao do mesmo tipo
        if (!(other instanceof NCLSimpleAction))
            comp = -1;

        // Compara pelo role
        if (getRole() == null) this_act = ""; else this_act = getRole().getName();
        if (other_simp.getRole() == null) other_act = ""; else other_act = other_simp.getRole().getName();
        comp = this_act.compareTo(other_act);

        // Compara pelo número mínimo
        if (comp == 0){
            if (getMin() == null) this_ac = 0; else this_ac = getMin();
            if (other_simp.getMin() == null) other_ac = 0; else other_ac = other_simp.getMin();
            comp = this_ac - other_ac;
        }

        // Compara pelo número máximo
        if (comp == 0){
            if (getMax() == null) this_ac = 0; else this_ac = getMax();
            if (other_simp.getMax() == null) other_ac = 0; else other_ac = other_simp.getMax();
            comp = this_ac - other_ac;
        }

        // Compara pelo delay
        if (comp == 0){
            if (getDelay() == null) this_ac = 0; else this_ac = getDelay();
            if (other_simp.getDelay() == null) other_ac = 0; else other_ac = other_simp.getDelay();
            comp = this_ac - other_ac;
        }

        // Compara pelo qualifier
        if (comp == 0){
            if (getQualifier() == null) this_act = ""; else this_act = getQualifier().toString();
            if (other_simp.getQualifier() == null) other_act = ""; else other_act = other_simp.getQualifier().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo value
        if (comp == 0){
            if (getValue() == null) this_act = ""; else this_act = getValue();
            if (other_simp.getValue() == null) other_act = ""; else other_act = other_simp.getValue();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo tipo do evento
        if (comp == 0){
            if (getEventType() == null) this_act = ""; else this_act = getEventType().toString();
            if (other_simp.getEventType() == null) other_act = ""; else other_act = other_simp.getEventType().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pela acao do evento
        if (comp == 0){
            if (getActionType() == null) this_act = ""; else this_act = getActionType().toString();
            if (other_simp.getActionType() == null) other_act = ""; else other_act = other_simp.getActionType().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo repeat
        if (comp == 0){
            if (getRepeat() == null) this_ac = 0; else this_ac = getRepeat();
            if (other_simp.getRepeat() == null) other_ac = 0; else other_ac = other_simp.getRepeat();
            comp = this_ac - other_ac;
        }

        // Compara pelo repeatDelay
        if (comp == 0){
            if (getRepeatDelay() == null) this_ac = 0; else this_ac = getRepeatDelay();
            if (other_simp.getRepeatDelay() == null) other_ac = 0; else other_ac = other_simp.getRepeatDelay();
            comp = this_ac - other_ac;
        }

        // Compara pelo duration
        if (comp == 0){
            if (getDuration() == null) this_ac = 0; else this_ac = getDuration();
            if (other_simp.getDuration() == null) other_ac = 0; else other_ac = other_simp.getDuration();
            comp = this_ac - other_ac;
        }

        // Compara pelo by
        if (comp == 0){
            if (getBy() == null) this_ac = 0; else this_ac = getBy();
            if (other_simp.getBy() == null) other_ac = 0; else other_ac = other_simp.getBy();
            comp = this_ac - other_ac;
        }


        return comp;
    }

}
