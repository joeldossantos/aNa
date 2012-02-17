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
package br.uff.midiacom.ana.datatype.ncl.transition;

import br.uff.midiacom.ana.datatype.auxiliar.ReferenceType;
import br.uff.midiacom.ana.datatype.auxiliar.ReferredElement;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionDirection;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionSubtype;
import br.uff.midiacom.ana.datatype.enums.NCLTransitionType;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;


public abstract class NCLTransitionPrototype<T extends NCLTransitionPrototype,
                                             P extends NCLElement,
                                             I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement {

    protected NCLTransitionType type;
    protected NCLTransitionSubtype subtype;
    protected TimeType dur;
    protected Double startProgress;
    protected Double endProgress;
    protected NCLTransitionDirection direction;
    protected NCLColor fadeColor;
    protected Integer horRepeat;
    protected Integer vertRepeat;
    protected Integer borderWidth;
    protected NCLColor borderColor;
    
    protected TreeSet<ReferenceType> references;


    /**
     * Construtor do elemento <i>transition</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da transição.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da transição não for válido.
     */
    public NCLTransitionPrototype(String id) throws XMLException {
        super();
        setId(id);
        references = new TreeSet<ReferenceType>();
    }


    public NCLTransitionPrototype() throws XMLException {
        super();
        references = new TreeSet<ReferenceType>();
    }


    /**
     * Atribui um tipo a transição.
     *
     * @param type
     *          elemento representando o tipo da transição.
     */
    public void setType(NCLTransitionType type) {
        NCLTransitionType aux = this.type;
        this.type = type;
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
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
        NCLTransitionSubtype aux = this.subtype;
        this.subtype = subtype;
        impl.notifyAltered(NCLElementAttributes.SUBTYPE, aux, subtype);
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
    public void setDur(TimeType dur) {
        TimeType aux = this.dur;
        this.dur = dur;
        impl.notifyAltered(NCLElementAttributes.DUR, aux, dur);
    }


    /**
     * Retorna a duração da transição.
     *
     * @return
     *          elemento representando a duração da transição.
     */
    public TimeType getDur() {
        return dur;
    }


    /**
     * Atribui um delay de progresso inicial a transição.
     *
     * @param startProgress
     *          fracionário representando o delay inicial.
     */
    public void setStartProgress(Double startProgress) {
        Double aux = this.startProgress;
        this.startProgress = startProgress;
        impl.notifyAltered(NCLElementAttributes.STARTPROGRESS, aux, startProgress);
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
        Double aux = this.endProgress;
        this.endProgress = endProgress;
        impl.notifyAltered(NCLElementAttributes.ENDPROGRESS, aux, endProgress);
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
        NCLTransitionDirection aux = this.direction;
        this.direction = direction;
        impl.notifyAltered(NCLElementAttributes.DIRECTION, aux, direction);
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
        NCLColor aux = this.fadeColor;
        this.fadeColor = fadeColor;
        impl.notifyAltered(NCLElementAttributes.FADECOLOR, aux, fadeColor);
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
        Integer aux = this.horRepeat;
        this.horRepeat = horRepeat;
        impl.notifyAltered(NCLElementAttributes.HORREPEAT, aux, horRepeat);
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
        Integer aux = this.vertRepeat;
        this.vertRepeat = vertRepeat;
        impl.notifyAltered(NCLElementAttributes.VERTREPEAT, aux, vertRepeat);
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
        Integer aux = this.borderWidth;
        this.borderWidth = borderWidth;
        impl.notifyAltered(NCLElementAttributes.BORDERWIDTH, aux, borderWidth);
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
        NCLColor aux = this.borderColor;
        this.borderColor = borderColor;
        impl.notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, borderColor);
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
    
    
    @Override
    public boolean addReference(ReferenceType reference) {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) {
        return references.remove(reference);
    }
    
    
    @Override
    public TreeSet<ReferenceType> getReferences() {
        return references;
    }
}
