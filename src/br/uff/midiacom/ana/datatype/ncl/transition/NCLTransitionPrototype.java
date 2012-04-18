/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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

import br.uff.midiacom.ana.datatype.aux.basic.BlendColorType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
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
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.reference.ReferredElement;


/**
 * Class that represents a transition. A transition defines a transition effect
 * used when presenting (begin or end) an media object.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the transition. This attribute is required.</li>
 *  <li><i>type</i> - type of the transition. This attribute is required.</li>
 *  <li><i>subtype</i> - subtype of the transition. This attribute is optional.</li>
 *  <li><i>dur</i> - transition duration. This attribute is optional.</li>
 *  <li><i>startProgress</i> - transition begin progress. This attribute is optional.</li>
 *  <li><i>endProgress</i> - transition end progress. This attribute is optional.</li>
 *  <li><i>direction</i> - transition direction. This attribute is optional.</li>
 *  <li><i>fadeColor</i> - color to fade when the type is fade. This attribute is
 *                         optional.</li>
 *  <li><i>horRepeat</i> - repetitions of the transition in the horizontal axis.
 *                         This attribute is optional.</li>
 *  <li><i>vertRepeat</i> -  repetitions of the transition in the vertical axis.
 *                           This attribute is optional.</li>
 *  <li><i>borderWidth</i> - width of the border. This attribute is optional.</li>
 *  <li><i>borderColor</i> - color of the border. This attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public abstract class NCLTransitionPrototype<T extends NCLTransitionPrototype,
                                             P extends NCLElement,
                                             I extends NCLElementImpl>
        extends NCLIdentifiableElementPrototype<T, P, I>
        implements NCLIdentifiableElement<T, P>, ReferredElement<ReferenceType> {

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
    protected BlendColorType borderColor;
    
    protected ItemList<ReferenceType> references;


    /**
     * Transition element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLTransitionPrototype() throws XMLException {
        super();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Sets the transition type. The type is required and can not be set to
     * <i>null</i>. The possible transition type values are defined in the
     * enumeration <i>NCLTransitionType</i>.
     * 
     * @param type
     *          element representing the transition type from the enumeration
     *          <i>NCLTransitionType</i>.
     * @throws XMLException 
     *          if the element representing the transition type is null.
     */
    public void setType(NCLTransitionType type) throws XMLException {
        if(type == null)
            throw new XMLException("Null type.");
        
        NCLTransitionType aux = this.type;
        this.type = type;
        impl.notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }


    /**
     * Sets the transition type or <i>null</i> if the attribute is not defined.
     * The possible transition type values are defined in the enumeration
     * <i>NCLTransitionType</i>.
     *
     * @return
     *          element representing the transition type from the enumeration
     *          <i>NCLTransitionType</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLTransitionType getType() {
        return type;
    }


    /**
     * Sets the transition subtype. This attribute is optional. Set the subtype
     * to <i>null</i> to erase a subtype already defined.
     * 
     * <br/>
     * 
     * The subtype value must be related to the type attribute value. The possible
     * transition subtype values are defined in the enumeration 
     * <i>NCLTransitionSubtype</i>.
     *
     * @param subtype
     *          element representing the transition subtype from the enumeration
     *          <i>NCLTransitionSubtype</i>  or <i>null</i> to erase a subtype
     *          already defined.
     */
    public void setSubtype(NCLTransitionSubtype subtype) {
        NCLTransitionSubtype aux = this.subtype;
        this.subtype = subtype;
        impl.notifyAltered(NCLElementAttributes.SUBTYPE, aux, subtype);
    }


    /**
     * Returns the transition subtype or <i>null</i> if the attribute is not 
     * defined.
     * 
     * <br/>
     * 
     * The subtype value must be related to the type attribute value. The possible
     * transition subtype values are defined in the enumeration 
     * <i>NCLTransitionSubtype</i>.
     *
     * @return
     *          element representing the transition subtype from the enumeration
     *          <i>NCLTransitionSubtype</i> or <i>null</i> if the attribute is
     *          not defined.
     */
    public NCLTransitionSubtype getSubtype() {
        return subtype;
    }


    /**
     * Sets the transition duration. This attribute is optional. Set the duration
     * to <i>null</i> to erase a duration already defined.
     * 
     * <br/>
     * 
     * The default transition duration is <i>1 second</i>.
     *
     * @param dur
     *          element representing the transition duration or <i>null</i> to
     *          erase a duration already defined.
     */
    public void setDur(TimeType dur) {
        TimeType aux = this.dur;
        this.dur = dur;
        impl.notifyAltered(NCLElementAttributes.DUR, aux, dur);
    }


    /**
     * Returns the transition duration or <i>null</i> if the attribute is not
     * defined.
     * 
     * <br/>
     * 
     * The default transition duration is <i>1 second</i>.
     *
     * @return
     *          element representing the transition duration or <i>null</i> if
     *          the attribute is not defined.
     */
    public TimeType getDur() {
        return dur;
    }


    /**
     * Sets the transition progress in the beginning of the transition. This
     * attribute is optional. Set the progress to <i>null</i> to erase an
     * progress already defined.
     * 
     * <br/>
     * 
     * This progress means that the transition will begin already with a certain
     * amount of it done. For example, in a transition that presents the media
     * object increasing its transparency, the start progress indicates the
     * initial transparency value.
     * 
     * <br/>
     * 
     * The default start progress value is <i>0</i>.
     *
     * @param startProgress
     *          number between 0 and 1, or <i>null</i> to erase a progress
     *          already defined.
     * @throws XMLException 
     *          if the number is not between 0 and 1.
     */
    public void setStartProgress(Double startProgress) throws XMLException {
        if(startProgress != null & (startProgress < 0 || startProgress > 1))
            throw new XMLException("Invalid progress value.");
        
        Double aux = this.startProgress;
        this.startProgress = startProgress;
        impl.notifyAltered(NCLElementAttributes.STARTPROGRESS, aux, startProgress);
    }


    /**
     * Returns the transition progress in the beginning of the transition or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * This progress means that the transition will begin already with a certain
     * amount of it done. For example, in a transition that presents the media
     * object increasing its transparency, the start progress indicates the
     * initial transparency value.
     * 
     * <br/>
     * 
     * The default start progress value is <i>0</i>.
     *
     * @return
     *          number between 0 and 1, or <i>null</i> if the attribute is not
     *          defined.
     */
    public Double getStartProgress() {
        return startProgress;
    }


    /**
     * Sets the transition progress in the end of the transition. This
     * attribute is optional. Set the progress to <i>null</i> to erase an
     * progress already defined.
     * 
     * <br/>
     * 
     * This progress means that the transition will end with a certain amount
     * of it not done. For example, in a transition that presents the media
     * object increasing its transparency, the end progress indicates the
     * final transparency value.
     * 
     * <br/>
     * 
     * The default end progress value is <i>1</i>.
     *
     * @param endProgress
     *          number between 0 and 1, or <i>null</i> to erase a progress
     *          already defined.
     * @throws XMLException 
     *          if the number is not between 0 and 1.
     */
    public void setEndProgress(Double endProgress) throws XMLException {
        if(endProgress != null & (endProgress < 0 || endProgress > 1))
            throw new XMLException("Invalid progress value.");
        
        Double aux = this.endProgress;
        this.endProgress = endProgress;
        impl.notifyAltered(NCLElementAttributes.ENDPROGRESS, aux, endProgress);
    }


    /**
     * Returns the transition progress in the end of the transition or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * This progress means that the transition will end with a certain amount
     * of it not done. For example, in a transition that presents the media
     * object increasing its transparency, the end progress indicates the
     * final transparency value.
     * 
     * <br/>
     * 
     * The default end progress value is <i>1</i>.
     *
     * @return
     *          number between 0 and 1, or <i>null</i> if the attribute is not
     *          defined.
     */
    public Double getEndProgress() {
        return endProgress;
    }


    /**
     * Sets the transition direction, that is, <i>forward</i> or <i>reverse</i>.
     * This attribute is optional. Set direction to <i>null</i> to erase a
     * direction already defined.
     * 
     * <br/>
     * 
     * Not all transition types will have an reverse interpretation. In general
     * this attribute is used with geometric transitions. The possible direction
     * values are defined in the enumeration <i>NCLTransitionDirection</i>.
     * 
     * <br/>
     * 
     * The default direction value is <i>forward</i>.
     *
     * @param direction
     *          element representing the transition direction from the enumeration
     *          <i>NCLTransitionDirection</i>.
     */
    public void setDirection(NCLTransitionDirection direction) {
        NCLTransitionDirection aux = this.direction;
        this.direction = direction;
        impl.notifyAltered(NCLElementAttributes.DIRECTION, aux, direction);
    }


    /**
     * Returns the transition direction, that is, <i>forward</i> or <i>reverse</i>
     * or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * Not all transition types will have an reverse interpretation. In general
     * this attribute is used with geometric transitions. The possible direction
     * values are defined in the enumeration <i>NCLTransitionDirection</i>.
     * 
     * <br/>
     * 
     * The default direction value is <i>forward</i>
     *
     * @return
     *          element representing the transition direction from the enumeration
     *          <i>NCLTransitionDirection</i> or <i>null</i> if the attribute is
     *          not defined.
     */
    public NCLTransitionDirection getDirection() {
        return direction;
    }


    /**
     * Sets the fade color when the transition type is either <i>fade to color</i>
     * or <i>fade from color</i>. This attribute is optional. Set the fade color
     * to <i>null</i> to erase a fade color already defined.
     * 
     * <br/>
     * 
     * In both cases the fade color attribute defines the final and initial
     * colors, respectively. The possible color values are defined in the
     * enumeration <i>NCLColor</i>.
     * 
     * <br/>
     * 
     * The default fade color is <i>black</i>.
     *
     * @param fadeColor
     *          element representing the fade color from the enumeration
     *          <i>NCLColor</i> or <i>null</i> to erase a color already defined.
     */
    public void setFadeColor(NCLColor fadeColor) {
        NCLColor aux = this.fadeColor;
        this.fadeColor = fadeColor;
        impl.notifyAltered(NCLElementAttributes.FADECOLOR, aux, fadeColor);
    }


    /**
     * Returns the fade color when the transition type is either <i>fade to color</i>
     * or <i>fade from color</i> or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * In both cases the fade color attribute defines the final and initial
     * colors, respectively. The possible color values are defined in the
     * enumeration <i>NCLColor</i>.
     * 
     * <br/>
     * 
     * The default fade color is <i>black</i>.
     *
     * @return
     *          element representing the fade color from the enumeration
     *          <i>NCLColor</i> or <i>null</i> if the attribute is not defined.
     */
    public NCLColor getFadeColor() {
        return fadeColor;
    }


    /**
     * Sets the number of repetitions of the transition in the horizontal axis.
     * This attribute is optional. Set the repetition to <i>null</i> to erase
     * a repetition already defined.
     * 
     * <br/>
     * 
     * The default repetition value is <i>1</i>.
     *
     * @param horRepeat
     *          integer representing the number of horizontal repetitions or
     *          <i>null</i> to erase a repetition already defined.
     */
    public void setHorRepeat(Integer horRepeat) {
        Integer aux = this.horRepeat;
        this.horRepeat = horRepeat;
        impl.notifyAltered(NCLElementAttributes.HORREPEAT, aux, horRepeat);
    }


    /**
     * Returns the number of repetitions of the transition in the horizontal axis
     * or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The default repetition value is <i>1</i>.
     *
     * @return
     *          integer representing the number of horizontal repetitions or
     *          <i>null</i> if the attribute is not defined.
     */
    public Integer getHorRepeat() {
        return horRepeat;
    }


    /**
     * Sets the number of repetitions of the transition in the vertical axis.
     * This attribute is optional. Set the repetition to <i>null</i> to erase
     * a repetition already defined.
     * 
     * <br/>
     * 
     * The default repetition value is <i>1</i>.
     *
     * @param vertRepeat
     *          integer representing the number of vertical repetitions or
     *          <i>null</i> to erase a repetition already defined.
     */
    public void setVertRepeat(Integer vertRepeat) {
        Integer aux = this.vertRepeat;
        this.vertRepeat = vertRepeat;
        impl.notifyAltered(NCLElementAttributes.VERTREPEAT, aux, vertRepeat);
    }


    /**
     * Returns the number of repetitions of the transition in the vertical axis
     * or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The default repetition value is <i>1</i>.
     *
     * @return
     *          integer representing the number of vertical repetitions or
     *          <i>null</i> if the attribute is not defined.
     */
    public Integer getVertRepeat() {
        return vertRepeat;
    }


    /**
     * Sets the width of the border generated in the area where the transition
     * is applied. This attribute is optional. Set the width to <i>null</i> to
     * erase a width already defined.
     * 
     * <br/>
     * 
     * The border width must be a positive integer. If the width is 0, that means
     * that no border will be generated.
     * 
     * <br/>
     * 
     * The default border width is <i>0</i>.
     *
     * @param borderWidth
     *          positive integer representing the border width or <i>null</i> to
     *          erase a width already defined.
     * @throws XMLException 
     *          if the width is negative.
     */
    public void setBorderWidth(Integer borderWidth) throws XMLException {
        if(borderWidth != null && borderWidth < 0)
            throw new XMLException("Negative border width.");
        
        Integer aux = this.borderWidth;
        this.borderWidth = borderWidth;
        impl.notifyAltered(NCLElementAttributes.BORDERWIDTH, aux, borderWidth);
    }


    /**
     * Returns the width of the border generated in the area where the transition
     * is applied or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The border width must be a positive integer. If the width is 0, that means
     * that no border will be generated.
     * 
     * <br/>
     * 
     * The default border width is <i>0</i>.
     *
     * @return
     *          positive integer representing the border width or <i>null</i> if
     *          the attribute is not defined.
     */
    public Integer getBorderWidth() {
        return borderWidth;
    }


    /**
     * Sets the color of the border generated in the area where the transition
     * is applied. This attribute is optional. Set the width to <i>null</i> to
     * erase a width already defined.
     * 
     * <br/>
     * 
     * If the transition type is fade, that the border color defines the content
     * of the generated border. If the attribute value is a color, than the
     * border will be filled with the color. If the attribute is the value
     * <i>blend</i> then the border will be filled with the an additive mixture
     * of the media object colors.
     * 
     * <br/>
     * 
     * The default border color is <i>black</i>.
     *
     * @param borderColor
     *          element representing the border color or <i>null</i> to erase
     *          a color already defined.
     */
    public void setBorderColor(BlendColorType borderColor) {
        BlendColorType aux = this.borderColor;
        this.borderColor = borderColor;
        impl.notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, borderColor);
    }


    /**
     * Returns the color of the border generated in the area where the transition
     * is applied or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * If the transition type is fade, that the border color defines the content
     * of the generated border. If the attribute value is a color, than the
     * border will be filled with the color. If the attribute is the value
     * <i>blend</i> then the border will be filled with the an additive mixture
     * of the media object colors.
     * 
     * <br/>
     * 
     * The default border color is <i>black</i>.
     *
     * @return
     *          element representing the border color or <i>null</i> if the
     *          attribute is not defined.
     */
    public BlendColorType getBorderColor() {
        return borderColor;
    }
    
    
    @Override
    public boolean addReference(ReferenceType reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    public boolean removeReference(ReferenceType reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ItemList<ReferenceType> getReferences() {
        return references;
    }
}
