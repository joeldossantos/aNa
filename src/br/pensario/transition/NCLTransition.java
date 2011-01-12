package br.pensario.transition;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLColor;
import br.pensario.NCLValues.NCLTransitionDirection;
import br.pensario.NCLValues.NCLTransitionSubtype;
import br.pensario.NCLValues.NCLTransitionType;
import br.pensario.interfaces.NCLTime;


/**
 * Esta classe define uma transição da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLTransition<T extends NCLTransition> extends NCLIdentifiableElement implements Comparable<T> {

    private NCLTransitionType type;
    private NCLTransitionSubtype subtype;
    private NCLTime dur;
    private Double startProgress;
    private Double endProgress;
    private NCLTransitionDirection direction;
    private NCLColor fadeColor;
    private Integer horRepeat;
    private Integer vertRepeat;
    private Integer borderWidth;
    private NCLColor borderColor;


    /**
     * Construtor do elemento <i>transition</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da transição.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da transição não for válido.
     */
    public NCLTransition(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Atribui um tipo a transição.
     *
     * @param type
     *          elemento representando o tipo da transição.
     */
    public void setType(NCLTransitionType type) {
        this.type = type;
    }


    /**
     * Retorna o tipo da transição.
     *
     * @return
     *          elemento representando o tipo da transição.
     */
    public NCLTransitionType getType() {
        return type;
    }


    /**
     * Atribui um subtipo a transição.
     *
     * @param subtype
     *          elemento representando o subtipo da transição.
     */
    public void setSubtype(NCLTransitionSubtype subtype) {
        this.subtype = subtype;
    }


    /**
     * Retorna o subtipo da transição.
     *
     * @return
     *          elemento representando o subtipo da transição.
     */
    public NCLTransitionSubtype getSubtype() {
        return subtype;
    }


    /**
     * Atribui uma duração a transição.
     *
     * @param dur
     *          elemento representando a duração da transição.
     */
    public void setDur(NCLTime dur) {
        this.dur = dur;
    }


    /**
     * Retorna a duração da transição.
     *
     * @return
     *          elemento representando a duração da transição.
     */
    public NCLTime getDur() {
        return dur;
    }


    /**
     * Atribui um delay de progresso inicial a transição.
     *
     * @param startProgress
     *          fracionário representando o delay inicial.
     */
    public void setStartProgress(Double startProgress) {
        this.startProgress = startProgress;
    }


    /**
     * Retorna o delay de progresso inicial da transição.
     *
     * @return
     *          fracionário representando o delay inicial.
     */
    public Double getStartProgress() {
        return startProgress;
    }


    /**
     * Atribui um delay de progresso final a transição.
     *
     * @param startProgress
     *          fracionário representando o delay final.
     */
    public void setEndProgress(Double endProgress) {
        this.endProgress = endProgress;
    }


    /**
     * Retorna o delay de progresso final da transição.
     *
     * @return
     *          fracionário representando o delay final.
     */
    public Double getEndProgress() {
        return endProgress;
    }


    /**
     * Atribui uma direção a transição.
     *
     * @param direction
     *          elemento representando a direção.
     */
    public void setDirection(NCLTransitionDirection direction) {
        this.direction = direction;
    }


    /**
     * Retorna a direção da transição.
     *
     * @return
     *          elemento representando a direção.
     */
    public NCLTransitionDirection getDirection() {
        return direction;
    }


    /**
     * Atribui uma cor para as transições de fade com cor.
     *
     * @param fadeColor
     *          cor associada a transição de fade.
     */
    public void setFadeColor(NCLColor fadeColor) {
        this.fadeColor = fadeColor;
    }


    /**
     * Retorna a cor da transição de fade.
     *
     * @return
     *          cor associada a transição de fade.
     */
    public NCLColor getFadeColor() {
        return fadeColor;
    }


    /**
     * Determina o numero de repetições da transição no eixo horizontal.
     *
     * @param horRepeat
     *          inteiro representando o número de repetições.
     */
    public void setHorRepeat(Integer horRepeat) {
        this.horRepeat = horRepeat;
    }


    /**
     * Retorna o numero de repetições da transição no eixo horizontal.
     *
     * @return
     *          inteiro representando o número de repetições.
     */
    public Integer getHorRepeat() {
        return horRepeat;
    }


    /**
     * Determina o numero de repetições da transição no eixo vertical.
     *
     * @param vertRepeat
     *          inteiro representando o número de repetições.
     */
    public void setVertRepeat(Integer vertRepeat) {
        this.vertRepeat = vertRepeat;
    }


    /**
     * Retorna o numero de repetições da transição no eixo vertical.
     *
     * @return
     *          inteiro representando o número de repetições.
     */
    public Integer getVertRepeat() {
        return vertRepeat;
    }


    /**
     * Determina a largura da borda da transição.
     *
     * @param borderWidth
     *          inteiro representando a largura da borda.
     */
    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }


    /**
     * Retorna a largura da borda da transição.
     *
     * @return
     *          inteiro representando a largura da borda.
     */
    public Integer getBorderWidth() {
        return borderWidth;
    }


    /**
     * Determina a cor da borda da transição.
     *
     * @param borderColor
     *          cor da borda.
     */
    public void setBorderColor(NCLColor borderColor) {
        this.borderColor = borderColor;
    }


    /**
     * Retorna a cor da borda da transição.
     *
     * @return
     *          cor da borda.
     */
    public NCLColor getBorderColor() {
        return borderColor;
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
        content = space + "<transition";
        content += " id='" + getId() + "'";
        content += " type='" + getType() + "'";
        if(getSubtype() != null)
            content += " subtype='" + getSubtype() + "'";
        if(getDur() != null)
            content += " dur='" + getDur() + "'";
        if(getStartProgress() != null)
            content += " startProgress='" + getStartProgress() + "'";
        if(getEndProgress() != null)
            content += " endProgress='" + getEndProgress() + "'";
        if(getDirection() != null)
            content += " direction='" + getDirection().toString() + "'";
        if(getFadeColor() != null)
            content += " fadeColor='" + getFadeColor().toString() + "'";
        if(getHorRepeat() != null)
            content += " horRepeat='" + getHorRepeat() + "'";
        if(getVertRepeat() != null)
            content += " vertRepeat='" + getVertRepeat() + "'";
        if(getBorderWidth() != null)
            content += " borderWidth='" + getBorderWidth() + "'";
        if(getBorderColor() != null)
            content += " borderColor='" + getBorderColor().toString() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(T other) {
        return getId().compareTo(other.getId());
    }
}
