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
package br.uff.midiacom.ana.datatype.ncl.interfaces;

import br.uff.midiacom.ana.datatype.aux.basic.SampleType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.array.ArrayType;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents an area element. An area represents a media node anchor.
 * A media node anchor represents a subpart of that node content.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the area element. This attribute is required.</li>
 *  <li><i>coords</i> - coordinates of the spatial anchor. This attribute is
 *                      optional.</li>
 *  <li><i>begin</i> - start of the temporal anchor. This attribute is optional.</li>
 *  <li><i>end</i> - end of the temporal anchor. This attribute is optional.</li>
 *  <li><i>text</i> - characters that represents the textual anchor. This attribute
 *                    is optional.</li>
 *  <li><i>position</i> - occurrence of the characters that represents the textual
 *                        anchor. This attribute is optional.</li>
 *  <li><i>first</i> - initial sample of the anchor. This attribute is optional.</li>
 *  <li><i>last</i> - final sample of the anchor. This attribute is optional.</li>
 *  <li><i>label</i> - characters used to identify a content of the media. This
 *                     attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ei> 
 */
public abstract class NCLAreaPrototype<T extends NCLAreaPrototype,
                                       P extends NCLElement,
                                       I extends NCLElementImpl,
                                       Ei extends NCLInterface>
        extends NCLIdentifiableElementPrototype<Ei, P, I>
        implements NCLInterface<Ei, P> {

    protected ArrayType coords;
    protected TimeType begin;
    protected TimeType end;
    protected StringType text;
    protected Integer position;
    protected SampleType first;
    protected SampleType last;
    protected StringType label;
    
    protected ItemList<ReferenceType> references;
    
    
    /**
     * Area element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLAreaPrototype() throws XMLException {
        super();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Sets the coordinates that defines a spatial anchor. This attribute is
     * optional. Set the coors to <i>null</i> to erase a coordinate already
     * defined.
     * 
     * <br/>
     * 
     * A spatial anchor defines a portion of the media object (in space).
     * This attribute can be used together with the begin and end attribute to
     * define a spatio-temporal anchor.
     * 
     * @param coords 
     *          element representing the coordinates of the anchor or <i>null</i>
     *          to erase a coordinate already defined.
     */
    public void setCoords(ArrayType coords){
        ArrayType aux = this.coords;
        this.coords = coords;
        impl.notifyAltered(NCLElementAttributes.COORDS, aux, coords);
    }
    
    
    /**
     * Returns the coordinates that defines a spatial anchor or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * A spatial anchor defines a portion of the media object (in space).
     * This attribute can be used together with the begin and end attribute to
     * define a spatio-temporal anchor.
     * 
     * @return
     *          element representing the coordinates of the anchor or <i>null</i>
     *          if the attribute is not defined.
     */
    public ArrayType getCoords() {
        return coords;
    }
    
    
    /**
     * Sets the start time of a temporal anchor. This attribute is optional.
     * Set the begin to <i>null</i> to erase a start time already defined.
     * 
     * <br/>
     * 
     * A temporal anchor defines a portion of the media object (time interval).
     * The ending time of a temporal anchor is defined by the attribute end.
     * If the attribute begin is defined but the attribute end is not, than the
     * temporal anchor end time will be considered the end of the media object,
     * that is, its duration.
     * 
     * @see #setEnd(br.uff.midiacom.ana.datatype.aux.basic.TimeType) 
     * 
     * <br/>
     * 
     * This attribute can be used together with the coords attribute to define
     * a spatio-temporal anchor.
     * 
     * <br/>
     * 
     * In case the media element parent of the anchor has the type
     * <i>application/x-ginga-time</i>, the start time must be defined as a
     * absolute time of the Universal Time Coordinated (UTC). In this case, the
     * begin attribute must be defined with the following syntax:
     * <i>Year:Month:Day:Hour:Minute:Second:Fraction</i>.
     * 
     * <br/>
     * 
     * Otherwise, the begin attribute must be defined with the following syntax:
     * <i>Hour:Minute:Second:Fraction</i> or <i>Seconds</i>
     * 
     * @param begin 
     *          element representing the start time of the anchor or <i>null</i>
     *          to erase a begin already defined.
     */
    public void setBegin(TimeType begin) {
        TimeType aux = this.begin;
        this.begin = begin;
        impl.notifyAltered(NCLElementAttributes.BEGIN, aux, begin);
    }
    
    
    /**
     * Returns the start time of a temporal anchor or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * A temporal anchor defines a portion of the media object (time interval).
     * The ending time of a temporal anchor is defined by the attribute end.
     * If the attribute begin is defined but the attribute end is not, than the
     * temporal anchor end time will be considered the end of the media object,
     * that is, its duration.
     * 
     * @see #setEnd(br.uff.midiacom.ana.datatype.aux.basic.TimeType) 
     * 
     * <br/>
     * 
     * This attribute can be used together with the coords attribute to define
     * a spatio-temporal anchor.
     * 
     * <br/>
     * 
     * In case the media element parent of the anchor has the type
     * <i>application/x-ginga-time</i>, the start time must be defined as a
     * absolute time of the Universal Time Coordinated (UTC). In this case, the
     * begin attribute must be defined with the following syntax:
     * <i>Year:Month:Day:Hour:Minute:Second:Fraction</i>.
     * 
     * <br/>
     * 
     * Otherwise, the begin attribute must be defined with the following syntax:
     * <i>Hour:Minute:Second:Fraction</i> or <i>Seconds</i>
     * 
     * @return
     *          element representing the start time of the anchor or <i>null</i>
     *          if the attribute is not defined.
     */
    public TimeType getBegin() {
        return begin;
    }
    
    
    /**
     * Sets the ending time of a temporal anchor. This attribute is optional.
     * Set the end to <i>null</i> to erase a ending time already defined.
     * 
     * <br/>
     * 
     * A temporal anchor defines a portion of the media object (time interval).
     * The start time of a temporal anchor is defined by the attribute begin.
     * If the attribute end is defined but the attribute begin is not, than the
     * temporal anchor start time will be considered the start of the media object.
     * 
     * @see #setBegin(br.uff.midiacom.ana.datatype.aux.basic.TimeType) 
     * 
     * <br/>
     * 
     * This attribute can be used together with the coords attribute to define
     * a spatio-temporal anchor.
     * 
     * <br/>
     * 
     * In case the media element parent of the anchor has the type
     * <i>application/x-ginga-time</i>, the ending time must be defined as a
     * absolute time of the Universal Time Coordinated (UTC). In this case, the
     * end attribute must be defined with the following syntax:
     * <i>Year:Month:Day:Hour:Minute:Second:Fraction</i>.
     * 
     * <br/>
     * 
     * Otherwise, the end attribute must be defined with the following syntax:
     * <i>Hour:Minute:Second:Fraction</i> or <i>Seconds</i>
     * 
     * @param end 
     *          element representing the ending time of the anchor or <i>null</i>
     *          to erase a end already defined.
     */
    public void setEnd(TimeType end) {
        TimeType aux = this.end;
        this.end = end;
        impl.notifyAltered(NCLElementAttributes.END, aux, end);
    }
    
    
    /**
     * Returns the ending time of a temporal anchor or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * A temporal anchor defines a portion of the media object (time interval).
     * The start time of a temporal anchor is defined by the attribute begin.
     * If the attribute end is defined but the attribute begin is not, than the
     * temporal anchor start time will be considered the start of the media object.
     * 
     * @see #setBegin(br.uff.midiacom.ana.datatype.aux.basic.TimeType) 
     * 
     * <br/>
     * 
     * This attribute can be used together with the coords attribute to define
     * a spatio-temporal anchor.
     * 
     * <br/>
     * 
     * In case the media element parent of the anchor has the type
     * <i>application/x-ginga-time</i>, the ending time must be defined as a
     * absolute time of the Universal Time Coordinated (UTC). In this case, the
     * end attribute must be defined with the following syntax:
     * <i>Year:Month:Day:Hour:Minute:Second:Fraction</i>.
     * 
     * <br/>
     * 
     * Otherwise, the end attribute must be defined with the following syntax:
     * <i>Hour:Minute:Second:Fraction</i> or <i>Seconds</i>
     * 
     * @return
     *          element representing the ending time of the anchor or <i>null</i>
     *          if the attribute is not defined.
     */
    public TimeType getEnd() {
        return end;
    }
    
    
    /**
     * Sets the characters that represents the textual anchor. This attribute
     * is optional. Set the text to <i>null</i> to erase a text already defined.
     * 
     * <br/>
     * 
     * Since the same characters can appear more than once in the media object
     * text, the position attribute indicates which instance is considered by
     * the anchor.
     * 
     * @see #setPosition(java.lang.Integer) 
     * 
     * @param text
     *          string representing the characters that defined the textual
     *          anchor or <i>null</i> to erase a text already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setText(String text) throws XMLException {
        StringType aux = this.text;
        
        if(text != null)
            this.text = new StringType(text);
        else
            this.text = null;
        
        impl.notifyAltered(NCLElementAttributes.TEXT, aux, text);
    }
    
    
    /**
     * Returns the characters that represents the textual anchor or <i>null</i>
     * if the attribute is not defined.
     * 
     * <br/>
     * 
     * Since the same characters can appear more than once in the media object
     * text, the position attribute indicates which instance is considered by
     * the anchor.
     * 
     * @see #setPosition(java.lang.Integer) 
     * 
     * @return
     *          string representing the characters that defined the textual
     *          anchor or <i>null</i> if the attribute is not defined.
     */
    public String getText() {
        if(text != null)
            return text.getValue();
        else
            return null;
    }
    
    
    /**
     * Sets the occurrence of the characters that represents the textual anchor.
     * This attribute is optional. Set the text to <i>null</i> to erase a
     * position already defined.
     * 
     * <br/>
     * 
     * Since the same characters can appear more than once in the media object
     * text, this attribute indicates which instance is considered by
     * the anchor.
     * 
     * @see #setText(java.lang.String) 
     * 
     * @param position
     *          positive integer that represents the character position in the
     *          text or <i>null</i> to erase a position already defined.
     * @throws XMLException 
     *          if the integer is negative.
     */
    public void setPosition(Integer position) throws XMLException {
        if(position != null && position < 0)
            throw new XMLException("Invalid position");
        
        Integer aux = this.position;
        this.position = position;
        impl.notifyAltered(NCLElementAttributes.POSITION, aux, position);
    }
    
    
    /**
     * Returns the occurrence of the characters that represents the textual
     * anchor or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * Since the same characters can appear more than once in the media object
     * text, this attribute indicates which instance is considered by
     * the anchor.
     * 
     * @see #setText(java.lang.String) 
     * 
     * @return
     *          positive integer that represents the character position in the
     *          text or <i>null</i> if the attribute is not defined.
     */
    public Integer getPosition() {
        return position;
    }
    
    
    /**
     * Sets the initial sample of a content anchor. This attribute is optional.
     * Set the first to <i>null</i> to erase a initial sample already defined.
     * 
     * <br/>
     * 
     * A content anchor defines a portion of the media object (sample interval).
     * The final sample of a content anchor is defined by the attribute last.
     * If the attribute first is defined but the attribute last is not, than the
     * content anchor final sample will be considered the last sample of the
     * media object.
     * 
     * @see #setLast(br.uff.midiacom.ana.datatype.aux.basic.SampleType) 
     * 
     * <br/>
     * 
     * The first attribute must be defined with the following syntax:
     * <i>Samples</i>, <i>Frames</i> or <i>Normal Play Time</i>. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @param first 
     *          element representing the initial sample of the anchor or <i>null</i>
     *          to erase a initial sample already defined.
     */
    public void setFirst(SampleType first) {
        SampleType aux = this.first;
        this.first = first;
        impl.notifyAltered(NCLElementAttributes.FIRST, aux, first);
    }
    
    
    /**
     * Returns the initial sample of a temporal anchor or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * A content anchor defines a portion of the media object (sample interval).
     * The final sample of a content anchor is defined by the attribute last.
     * If the attribute first is defined but the attribute last is not, than the
     * content anchor final sample will be considered the last sample of the
     * media object.
     * 
     * @see #setLast(br.uff.midiacom.ana.datatype.aux.basic.SampleType) 
     * 
     * <br/>
     * 
     * The first attribute must be defined with the following syntax:
     * <i>Samples</i>, <i>Frames</i> or <i>Normal Play Time</i>. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @return
     *          element representing the initial sample of the anchor or <i>null</i>
     *          if the attribute is not defined.
     */
    public SampleType getFirst() {
        return first;
    }
    
    
    /**
     * Sets the final sample of a content anchor. This attribute is optional.
     * Set the last to <i>null</i> to erase a final sample already defined.
     * 
     * <br/>
     * 
     * A content anchor defines a portion of the media object (sample interval).
     * The initial sample of a content anchor is defined by the attribute first.
     * If the attribute last is defined but the attribute first is not, than the
     * content anchor initial sample will be considered the first sample of the
     * media object.
     * 
     * @see #setFirst(br.uff.midiacom.ana.datatype.aux.basic.SampleType) 
     * 
     * <br/>
     * 
     * The last attribute must be defined with the following syntax:
     * <i>Samples</i>, <i>Frames</i> or <i>Normal Play Time</i>. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @param last 
     *          element representing the final sample of the anchor or <i>null</i>
     *          to erase a final sample already defined.
     */
    public void setLast(SampleType last) {
        SampleType aux = this.last;
        this.last = last;
        impl.notifyAltered(NCLElementAttributes.LAST, aux, last);
    }
    
    
    /**
     * Returns the final sample of a temporal anchor or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * A content anchor defines a portion of the media object (sample interval).
     * The initial sample of a content anchor is defined by the attribute first.
     * If the attribute last is defined but the attribute first is not, than the
     * content anchor initial sample will be considered the first sample of the
     * media object.
     * 
     * @see #setFirst(br.uff.midiacom.ana.datatype.aux.basic.SampleType) 
     * 
     * <br/>
     * 
     * The last attribute must be defined with the following syntax:
     * <i>Samples</i>, <i>Frames</i> or <i>Normal Play Time</i>. See the reference
     * (ABNT NBR 15606-2) to more information.
     * 
     * @return
     *          element representing the final sample of the anchor or <i>null</i>
     *          if the attribute is not defined.
     */
    public SampleType getLast() {
        return last;
    }
    
    
    /**
     * Sets the characters used to identify a content of the media. This attribute
     * is optional. Set the label to <i>null</i> to erase a label already defined.
     * 
     * @param label
     *          string representing the characters that identify a content of
     *          the media or <i>null</i> to erase a label already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setLabel(String label) throws XMLException {
        StringType aux = this.label;
        
        if(label != null)
            this.label = new StringType(label);
        else
            this.label = null;
        
        impl.notifyAltered(NCLElementAttributes.LABEL, aux, label);
    }
    
    
    /**
     * Returns the characters used to identify a content of the media or
     * <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          string representing the characters that identify a content of
     *          the media or <i>null</i> if the attribute is not defined.
     */
    public String getLabel() {
        if(label != null)
            return label.getValue();
        else
            return null;
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
