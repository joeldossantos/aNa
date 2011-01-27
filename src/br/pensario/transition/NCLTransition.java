package br.pensario.transition;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLColor;
import br.pensario.NCLValues.NCLTransitionDirection;
import br.pensario.NCLValues.NCLTransitionSubtype;
import br.pensario.NCLValues.NCLTransitionType;
import br.pensario.interfaces.NCLTime;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


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
     * Construtor do elemento <i>transition</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLTransition(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
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
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getType() != null)
            content += " type='" + getType().toString() + "'";
        if(getSubtype() != null)
            content += " subtype='" + getSubtype().toString() + "'";
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


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() != null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }
        if(getType() != null){
            addError("Elemento não possui atributo obrigatório type.");
            valid = false;
        }

        if(getSubtype() != null){
            if(getType() != null){
                if(!getSubtype().getType().equals(getType())){
                    addError("Tipo e subtipo da transição não estão corretos.");
                    valid = false;
                }
            }
            else{
                addError("Atributo subType não deve ser especificado sem o atributo type.");
                valid = false;
            }
        }

        if(getType() != null && getType().equals(NCLTransitionType.FADE) && getSubtype() != null && 
                (getSubtype().equals(NCLTransitionSubtype.FADE_FROM_COLOR) || getSubtype().equals(NCLTransitionSubtype.FADE_FROM_COLOR))){
            if(getFadeColor() == null){
                addWarning("Atributo fadeColor deve ser especificado.");
                valid = false;
            }
        }
        else{
            if(getFadeColor() != null){
                addWarning("Atributo fadeColor não deve ser especificado.");
                valid = false;
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("type")){
                    for(NCLTransitionType t : NCLTransitionType.values()){
                        if(t.toString().equals(attributes.getValue(i)))
                            setType(t);
                    }
                }
                else if(attributes.getLocalName(i).equals("subtype")){
                    for(NCLTransitionSubtype s : NCLTransitionSubtype.values()){
                        if(s.toString().equals(attributes.getValue(i)))
                            setSubtype(s);
                    }
                }
                else if(attributes.getLocalName(i).equals("dur")){
                    setDur(new NCLTime(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("startProgress")){
                    setStartProgress(new Double(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("endProgress")){
                    setEndProgress(new Double(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("direction")){
                    for(NCLTransitionDirection d : NCLTransitionDirection.values()){
                        if(d.toString().equals(attributes.getValue(i)))
                            setDirection(d);
                    }
                }
                else if(attributes.getLocalName(i).equals("fadeColor")){
                    for(NCLColor c : NCLColor.values()){
                        if(c.toString().equals(attributes.getValue(i)))
                            setFadeColor(c);
                    }
                }
                else if(attributes.getLocalName(i).equals("horRepeat")){
                    setHorRepeat(new Integer(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("vertRepeat")){
                    setVertRepeat(new Integer(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("borderWidth")){
                    setBorderWidth(new Integer(attributes.getValue(i)));
                }
                else if(attributes.getLocalName(i).equals("borderColor")){
                    for(NCLColor c : NCLColor.values()){
                        if(c.toString().equals(attributes.getValue(i)))
                            setBorderColor(c);
                    }
                }
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }
}
