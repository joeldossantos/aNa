/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.transition;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionDirection;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionSubtype;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionType;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.transition.NCLTransitionPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLTransition<T extends NCLTransition, P extends NCLElement, I extends NCLElementImpl>
        extends NCLTransitionPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {

    
    /**
     * Construtor do elemento <i>transition</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da transição.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da transição não for válido.
     */
    public NCLTransition(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    /**
     * Atribui um tipo a transição.
     *
     * @param type
     *          elemento representando o tipo da transição.
     */
    @Override
    public void setType(NCLTransitionType type) {
        NCLTransitionType aux = this.type;
        super.setType(type);
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }


    /**
     * Atribui um subtipo a transição.
     *
     * @param subtype
     *          elemento representando o subtipo da transição.
     */
    @Override
    public void setSubtype(NCLTransitionSubtype subtype) {
        NCLTransitionSubtype aux = this.subtype;
        super.setSubtype(subtype);
        impl.notifyAltered(NCLElementAttributes.SUBTYPE, aux, subtype);
    }


    /**
     * Atribui uma duração a transição.
     *
     * @param dur
     *          elemento representando a duração da transição.
     */
    @Override
    public void setDur(TimeType dur) {
        TimeType aux = this.dur;
        super.setDur(dur);
        impl.notifyAltered(NCLElementAttributes.DUR, aux, dur);
    }


    /**
     * Atribui um delay de progresso inicial a transição.
     *
     * @param startProgress
     *          fracionário representando o delay inicial.
     */
    @Override
    public void setStartProgress(Double startProgress) {
        Double aux = this.startProgress;
        super.setStartProgress(startProgress);
        impl.notifyAltered(NCLElementAttributes.STARTPROGRESS, aux, startProgress);
    }


    /**
     * Atribui um delay de progresso final a transição.
     *
     * @param startProgress
     *          fracionário representando o delay final.
     */
    @Override
    public void setEndProgress(Double endProgress) {
        Double aux = this.endProgress;
        super.setEndProgress(endProgress);
        impl.notifyAltered(NCLElementAttributes.STARTPROGRESS, aux, endProgress);
    }


    /**
     * Atribui uma direção a transição.
     *
     * @param direction
     *          elemento representando a direção.
     */
    @Override
    public void setDirection(NCLTransitionDirection direction) {
        NCLTransitionDirection aux = this.direction;
        super.setDirection(direction);
        impl.notifyAltered(NCLElementAttributes.DIRECTION, aux, direction);
    }


    /**
     * Atribui uma cor para as transições de fade com cor.
     *
     * @param fadeColor
     *          cor associada a transição de fade.
     */
    @Override
    public void setFadeColor(NCLColor fadeColor) {
        NCLColor aux = this.fadeColor;
        super.setFadeColor(fadeColor);
        impl.notifyAltered(NCLElementAttributes.FADECOLOR, aux, fadeColor);
    }


    /**
     * Determina o numero de repetições da transição no eixo horizontal.
     *
     * @param horRepeat
     *          inteiro representando o número de repetições.
     */
    @Override
    public void setHorRepeat(Integer horRepeat) {
        Integer aux = this.horRepeat;
        super.setHorRepeat(horRepeat);
        impl.notifyAltered(NCLElementAttributes.HORREPEAT, aux, horRepeat);
    }


    /**
     * Determina o numero de repetições da transição no eixo vertical.
     *
     * @param vertRepeat
     *          inteiro representando o número de repetições.
     */
    @Override
    public void setVertRepeat(Integer vertRepeat) {
        Integer aux = this.vertRepeat;
        super.setVertRepeat(vertRepeat);
        impl.notifyAltered(NCLElementAttributes.VERTREPEAT, aux, vertRepeat);
    }


    /**
     * Determina a largura da borda da transição.
     *
     * @param borderWidth
     *          inteiro representando a largura da borda.
     */
    @Override
    public void setBorderWidth(Integer borderWidth) {
        Integer aux = this.borderWidth;
        super.setBorderWidth(borderWidth);
        impl.notifyAltered(NCLElementAttributes.BORDERWIDTH, aux, borderWidth);
    }


    /**
     * Determina a cor da borda da transição.
     *
     * @param borderColor
     *          cor da borda.
     */
    @Override
    public void setBorderColor(NCLColor borderColor) {
        NCLColor aux = this.borderColor;
        super.setBorderColor(borderColor);
        impl.notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, borderColor);
    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("id"))
//                    setId(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("type")){
//                    for(NCLTransitionType t : NCLTransitionType.values()){
//                        if(t.toString().equals(attributes.getValue(i)))
//                            setType(t);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("subtype")){
//                    for(NCLTransitionSubtype s : NCLTransitionSubtype.values()){
//                        if(s.toString().equals(attributes.getValue(i)))
//                            setSubtype(s);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("dur")){
//                    setDur(new NCLTime(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("startProgress")){
//                    setStartProgress(new Double(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("endProgress")){
//                    setEndProgress(new Double(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("direction")){
//                    for(NCLTransitionDirection d : NCLTransitionDirection.values()){
//                        if(d.toString().equals(attributes.getValue(i)))
//                            setDirection(d);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("fadeColor")){
//                    for(NCLColor c : NCLColor.values()){
//                        if(c.toString().equals(attributes.getValue(i)))
//                            setFadeColor(c);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("horRepeat")){
//                    setHorRepeat(new Integer(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("vertRepeat")){
//                    setVertRepeat(new Integer(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("borderWidth")){
//                    setBorderWidth(new Integer(attributes.getValue(i)));
//                }
//                else if(attributes.getLocalName(i).equals("borderColor")){
//                    for(NCLColor c : NCLColor.values()){
//                        if(c.toString().equals(attributes.getValue(i)))
//                            setBorderColor(c);
//                    }
//                }
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
