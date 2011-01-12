package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLValues.NCLActionOperator;
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
public class NCLSimpleAction<A extends NCLAction, R extends NCLRole, P extends NCLConnectorParam> extends NCLElement implements NCLAction<A, P> {

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
    private R role;
    private Integer delay;

    private P parValue;
    private P parRepeat;
    private P parRepeatDelay;
    private P parDuration;
    private P parBy;
    private P parDelay;


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          String representando o valor de atribuição.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value) {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
        this.parValue = null;
    }


    /**
     * Determina o valor de atribuição da ação.
     *
     * @param value
     *          Parâmetro representando o valor de atribuição.
     */
    public void setValue(P value) {
        this.parValue = value;
        this.value = null;
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
     * Retorna o valor de atribuição da ação.
     *
     * @return
     *          Parâmetro representando o valor de atribuição.
     */
    public P getParamValue() {
        return parValue;
    }
    
    
    /**
     * Determina o número mínimo de binds que devem usar essa ação.
     *
     * @param min
     *          inteiro positivo representando o número mínimo.
     */
    public void setMin(Integer min) {
        if(min != null && min < 0)
            throw new IllegalArgumentException("Invalid min");
        
        this.min = min;
    }


    /**
     * Retorna o número mínimo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro positivo representando o número mínimo.
     */
    public Integer getMin() {
        return min;
    }


    /**
     * Determina o número máximo de binds que devem usar essa ação.
     *
     * @param max
     *          inteiro positivo representando o número máximo ou um inteiro negativo
     *          caso o número máximo seja a String "umbouded".
     */
    public void setMax(Integer max) {
        if(max != null && max < 0)
            this.max = -1;
        
        this.max = max;
    }


    /**
     * Retorna o número máximo de binds que devem usar essa ação.
     *
     * @return
     *          inteiro positivo representando o número máximo ou -1
     *          caso o número máximo seja a String "umbouded".
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
    public void setRole(R role) {
        this.role = role;
    }


    /**
     * Retorna o papel utilizado na ação.
     *
     * @return
     *          elemento representando o papel.
     */
    public R getRole() {
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
        this.parRepeat = null;
    }


    /**
     * Determina o número de repetições da ação.
     *
     * @param repeat
     *          Parâmetro representando o número de repetições.
     */
    public void setRepeat(P repeat) {
        this.parRepeat = repeat;
        this.repeat = null;
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
     * Retorna o número de repetições da ação.
     *
     * @return
     *          parametro representando o número de repetições.
     */
    public P getParamRepeat() {
        return parRepeat;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          inteiro representando o delay entre repetições.
     */
    public void setRepeatDelay(Integer repeatDelay) {
        this.repeatDelay = repeatDelay;
        this.parRepeatDelay = null;
    }


    /**
     * Determina o delay entre repetições da ação.
     *
     * @param repeatDelay
     *          parâmetro representando o delay entre repetições.
     */
    public void setRepeatDelay(P repeatDelay) {
        this.parRepeatDelay = repeatDelay;
        this.repeatDelay = null;
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
     * Retorna o delay entre repetições da ação.
     *
     * @return
     *          parâmetro representando o delay entre repetições.
     */
    public P getParamRepeatDelay() {
        return parRepeatDelay;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          inteiro representando a duração da atribuição.
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
        this.parDuration = null;
    }


    /**
     * Determina a duração da ação de atribuição.
     *
     * @param duration
     *          parâmetro representando a duração da atribuição.
     */
    public void setDuration(P duration) {
        this.parDuration = duration;
        this.duration = null;
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
     * Retorna a duração da ação de atribuição.
     *
     * @return
     *          parâmetro representando a duração da atribuição.
     */
    public P getParamDuration() {
        return parDuration;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          inteiro representando o passo da atribuição.
     */
    public void setBy(Integer by) {
        this.by = by;
        this.parBy = null;
    }


    /**
     * Determina o passo da ação de atribuição.
     *
     * @param by
     *          parâmetro representando o passo da atribuição.
     */
    public void setBy(P by) {
        this.parBy = by;
        this.by = null;
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


    /**
     * Retorna o passo da ação de atribuição.
     *
     * @return
     *          parâmetro representando o passo da atribuição.
     */
    public P getParamBy() {
        return parBy;
    }


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if(delay != null && delay < 0)
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

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<simpleAction";

        content += " role='" + getRole().getName() + "'";

        if(getValue() != null)
            content += " value='" + getValue() + "'";
        if(getParamValue() != null)
            content += " value='$" + getParamValue().getId() + "'";

        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";
        if(getParamDelay() != null)
            content += " delay='$" + getParamDelay().getId() + "'";
        
        if(getMin() != null)
            content += " min='" + getMin() + "'";
        
        if(getMax() != null)
            if(getMax() < 0)
                content += " max='unbounded'";
            else
                content += " max='" + getMax() + "'";
        
        if(getQualifier() != null)
            content += " qualifier='" + getQualifier().toString() + "'";

        if(getEventType() != null)
            content += " eventType='" + getEventType().toString() + "'";

        if(getActionType() != null)
            content += " actionType='" + getActionType().toString() + "'";

        if(getRepeat() != null)
            content += " repeat='" + getRepeat() + "'";
        if(getParamRepeat() != null)
            content += " repeat='$" + getParamRepeat().getId() + "'";

        if(getRepeatDelay() != null)
            content += " repeatDelay='" + getRepeatDelay() + "s'";
        if(getParamRepeatDelay() != null)
            content += " repeatDelay='$" + getParamRepeatDelay().getId() + "'";

        if(getDuration() != null)
            content += " duration='" + getDuration() + "s'";
        if(getParamDuration() != null)
            content += " duration='$" + getParamDuration().getId() + "'";

        if(getBy() != null)
            content += " by='" + getBy() + "'";
        if(getParamBy() != null)
            content += " by='$" + getParamBy().getId() + "'";
        
        content += "/>\n";

        return content;
    }


    public int compareTo(A other) {
        //retorna 0 se forem iguais e 1 se forem diferentes (mantem a ordem de insercao)
        int comp = 0;

        String this_act, other_act;
        int this_ac, other_ac;
        NCLSimpleAction other_simp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLSimpleAction))
            return 1;

         other_simp = (NCLSimpleAction) other;

        // Compara pelo role
        if(getRole() == null) this_act = ""; else this_act = getRole().getName();
        if(other_simp.getRole() == null) other_act = ""; else other_act = other_simp.getRole().getName();
        comp = this_act.compareTo(other_act);

        // Compara pelo número mínimo
        if(comp == 0){
            if(getMin() == null) this_ac = 0; else this_ac = getMin();
            if(other_simp.getMin() == null) other_ac = 0; else other_ac = other_simp.getMin();
            comp = this_ac - other_ac;
        }

        // Compara pelo número máximo
        if(comp == 0){
            if(getMax() == null) this_ac = 0; else this_ac = getMax();
            if(other_simp.getMax() == null) other_ac = 0; else other_ac = other_simp.getMax();
            comp = this_ac - other_ac;
        }

        // Compara pelo delay
        if(comp == 0){
            if(getDelay() == null) this_ac = 0; else this_ac = getDelay();
            if(other_simp.getDelay() == null) other_ac = 0; else other_ac = other_simp.getDelay();
            comp = this_ac - other_ac;
        }

        // Compara pelo delay (parametro)
        if(comp == 0){
            if(getParamDelay() == null && other_simp.getParamDelay() == null)
                comp = 0;
            else if(getParamDelay() != null && other_simp.getParamDelay() != null)
                comp = getParamDelay().compareTo(other_simp.getParamDelay());
            else
                comp = 1;
        }

        // Compara pelo qualifier
        if(comp == 0){
            if(getQualifier() == null) this_act = ""; else this_act = getQualifier().toString();
            if(other_simp.getQualifier() == null) other_act = ""; else other_act = other_simp.getQualifier().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo value
        if(comp == 0){
            if(getValue() == null) this_act = ""; else this_act = getValue();
            if(other_simp.getValue() == null) other_act = ""; else other_act = other_simp.getValue();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo value (parametro)
        if(comp == 0){
            if(getParamValue() == null && other_simp.getParamValue() == null)
                comp = 0;
            else if(getParamValue() != null && other_simp.getParamValue() != null)
                comp = getParamValue().compareTo(other_simp.getParamValue());
            else
                comp = 1;
        }

        // Compara pelo tipo do evento
        if(comp == 0){
            if(getEventType() == null) this_act = ""; else this_act = getEventType().toString();
            if(other_simp.getEventType() == null) other_act = ""; else other_act = other_simp.getEventType().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pela acao do evento
        if(comp == 0){
            if(getActionType() == null) this_act = ""; else this_act = getActionType().toString();
            if(other_simp.getActionType() == null) other_act = ""; else other_act = other_simp.getActionType().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo repeat
        if(comp == 0){
            if(getRepeat() == null) this_ac = 0; else this_ac = getRepeat();
            if(other_simp.getRepeat() == null) other_ac = 0; else other_ac = other_simp.getRepeat();
            comp = this_ac - other_ac;
        }

        // Compara pelo repeat (parametro)
        if(comp == 0){
            if(getParamRepeat() == null && other_simp.getParamRepeat() == null)
                comp = 0;
            else if(getParamRepeat() != null && other_simp.getParamRepeat() != null)
                comp = getParamRepeat().compareTo(other_simp.getParamRepeat());
            else
                comp = 1;
        }

        // Compara pelo repeatDelay
        if(comp == 0){
            if(getRepeatDelay() == null) this_ac = 0; else this_ac = getRepeatDelay();
            if(other_simp.getRepeatDelay() == null) other_ac = 0; else other_ac = other_simp.getRepeatDelay();
            comp = this_ac - other_ac;
        }

        // Compara pelo repeatDelay (parametro)
        if(comp == 0){
            if(getParamRepeatDelay() == null && other_simp.getParamRepeatDelay() == null)
                comp = 0;
            else if(getParamRepeatDelay() != null && other_simp.getParamRepeatDelay() != null)
                comp = getParamRepeatDelay().compareTo(other_simp.getParamRepeatDelay());
            else
                comp = 1;
        }

        // Compara pelo duration
        if(comp == 0){
            if(getDuration() == null) this_ac = 0; else this_ac = getDuration();
            if(other_simp.getDuration() == null) other_ac = 0; else other_ac = other_simp.getDuration();
            comp = this_ac - other_ac;
        }

        // Compara pelo duration (parametro)
        if(comp == 0){
            if(getParamDuration() == null && other_simp.getParamDuration() == null)
                comp = 0;
            else if(getParamDuration() != null && other_simp.getParamDuration() != null)
                comp = getParamDuration().compareTo(other_simp.getParamDuration());
            else
                comp = 1;
        }

        // Compara pelo by
        if(comp == 0){
            if(getBy() == null) this_ac = 0; else this_ac = getBy();
            if(other_simp.getBy() == null) other_ac = 0; else other_ac = other_simp.getBy();
            comp = this_ac - other_ac;
        }

        // Compara pelo by (parametro)
        if(comp == 0){
            if(getParamBy() == null && other_simp.getParamBy() == null)
                comp = 0;
            else if(getParamBy() != null && other_simp.getParamBy() != null)
                comp = getParamBy().compareTo(other_simp.getParamBy());
            else
                comp = 1;
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }

}
