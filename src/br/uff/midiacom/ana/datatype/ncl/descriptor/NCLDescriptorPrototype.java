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
package br.uff.midiacom.ana.datatype.ncl.descriptor;

import br.uff.midiacom.ana.datatype.aux.basic.FocusIndexType;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.aux.reference.TransitionReference;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.param.NCLDescriptorParam;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.number.PercentageType;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.string.StringType;


/**
 * Class that represents a descriptor element. A descriptor defines a set of
 * informations used for the presentation of a media object.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the descriptor element. This attribute is required.</li>
 *  <li><i>player</i> - identification of the presentation tool to be used for the
 *                      presentation of a media object. This attribute is optional.</li>
 *  <li><i>explicitDur</i> - ideal duration of a media object. This attribute is
 *                           optional.</li>
 *  <li><i>region</i> - region to be used for the presentation of a media object.
 *                      This attribute is optional.</li>
 *  <li><i>freeze</i> - behavior of the media object at the end of its presentation.
 *                      This attribute is optional.</li>
 *  <li><i>moveLeft</i> - index to receive the focus when the left key is pressed.
 *                        This attribute is optional.</li>
 *  <li><i>moveRight</i> - index to receive the focus when the right key is pressed.
 *                         This attribute is optional.</li>
 *  <li><i>moveUp</i> - index to receive the focus when the up key is pressed.
 *                      This attribute is optional.</li>
 *  <li><i>moveDown</i> - index to receive the focus when the down key is pressed.
 *                        This attribute is optional.</li>
 *  <li><i>focusIndex</i> - index for a media object receive the focus. This
 *                          attribute is optional.</li>
 *  <li><i>focusBorderColor</i> - color of the border of the media object that
 *                                received focus. This attribute is optional.</li>
 *  <li><i>focusBorderWidth</i> - width of the border of the media object that
 *                                received focus. This attribute is optional.</li>
 *  <li><i>focusBorderTransparency</i> - transparency of the border of the media
 *                                       object that received focus. This attribute
 *                                       is optional.</li>
 *  <li><i>focusSrc</i> - alternative content to be presented when the media
 *                        object receive focus. This attribute is optional.</li>
 *  <li><i>focusSelSrc</i> - alternative content to be presented when the media
 *                           object is selected. This attribute is optional.</li>
 *  <li><i>selBorderColor</i> - color of the border of the media object when it
 *                              is selected. This attribute is optional.</li>
 *  <li><i>transIn</i> - transition used at the beginning of a media object
 *                       presentation. This attribute is optional.</li>
 *  <li><i>transOut</i> - transition used at the end of a media object 
 *                        presentation. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>descriptorParam</i> - element representing a descriptor parameter. The
 *                          descriptor can have none or several parameter elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <El>
 * @param <Er>
 * @param <Ed>
 * @param <Et>
 * @param <Ep> 
 */
public abstract class NCLDescriptorPrototype<T extends NCLDescriptorPrototype,
                                             P extends NCLElement,
                                             I extends NCLElementImpl,
                                             El extends NCLLayoutDescriptor,
                                             Er extends RegionReference,
                                             Ed extends DescriptorReference,
                                             Et extends TransitionReference,
                                             Ep extends NCLDescriptorParam>
        extends NCLIdentifiableElementPrototype<El, P, I>
        implements NCLLayoutDescriptor<El, P> {

    protected StringType player;
    protected TimeType explicitDur;
    protected Boolean freeze;
    protected Ed moveLeft;
    protected Ed moveRight;
    protected Ed moveUp;
    protected Ed moveDown;
    protected FocusIndexType focusIndex;
    protected NCLColor focusBorderColor;
    protected Integer focusBorderWidth;
    protected PercentageType focusBorderTransparency;
    protected SrcType focusSrc;
    protected SrcType focusSelSrc;
    protected NCLColor selBorderColor;
    protected Et transIn;
    protected Et transOut;
    protected Er region;
    protected ElementList<Ep, T> params;
    
    protected ItemList<ReferenceType> references;


    /**
     * Descriptor element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorPrototype() throws XMLException {
        super();
        params = new ElementList<Ep, T>();
        references = new ItemList<ReferenceType>();
    }


    /**
     * Sets the identification of the presentation tool to be used for the
     * presentation of a media object. This attribute is optional. Set the
     * player to <i>null</i> to erase a player already defined.
     * 
     * @param player
     *          string representing the identification of the presentation tool
     *          to be used or <i>null</i> to erase a player already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setPlayer(String player) throws XMLException {
        StringType aux = this.player;
        this.player = new StringType(player);
        impl.notifyAltered(NCLElementAttributes.PLAYER, aux, player);
    }


    /**
     * Returns the identification of the presentation tool to be used for the
     * presentation of a media object or <i>null</i> if the attribute is not
     * defined.
     * 
     * @return 
     *          string representing the identification of the presentation tool
     *          to be used or <i>null</i> if the attribute is not defined.
     */
    public String getPlayer() {
        if(player != null)
            return player.getValue();
        else
            return null;
    }


    /**
     * Sets the ideal duration of a media object. This attribute is optional.
     * Set the duration to <i>null</i> to erase a duration already defined.
     * 
     * <br/>
     * 
     * Every media object that uses this descriptor will have the duration
     * defined in this attribute.
     * 
     * @param explicitDur 
     *          element representing the ideal duration of a media object or
     *          <i>null</i> to erase a duration already defined.
     */
    public void setExplicitDur(TimeType explicitDur) {
        TimeType aux = this.explicitDur;
        this.explicitDur = explicitDur;
        impl.notifyAltered(NCLElementAttributes.EXPLICITDUR, aux, explicitDur);
    }


    /**
     * Returns the ideal duration of a media object or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * Every media object that uses this descriptor will have the duration
     * defined in this attribute.
     * 
     * @return 
     *          element representing the ideal duration of a media object or
     *          <i>null</i> if the attribute is not defined.
     */
    public TimeType getExplicitDur() {
        return explicitDur;
    }


    /**
     * Sets the behavior of the media object at the end of its presentation.
     * This attribute is optional. Set the freeze to <i>null</i> to erase a
     * freeze already defined.
     * 
     * <br/>
     * 
     * This attribute indicates if the last frame of the media will be
     * continuously presented until the end of the ideal duration defined by the
     * descriptor.
     * 
     * @param freeze 
     *          boolean defining if the last frame of the media object will be
     *          continuously presented or <i>null</i> to erase a freeze already
     *          defined.
     */
    public void setFreeze(Boolean freeze) {
        Boolean aux = this.freeze;
        this.freeze = freeze;
        impl.notifyAltered(NCLElementAttributes.FREEZE, aux, freeze);
    }


    /**
     * Returns the behavior of the media object at the end of its presentation
     * or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * This attribute indicates if the last frame of the media will be
     * continuously presented until the end of the ideal duration defined by the
     * descriptor.
     * 
     * @return 
     *          boolean defining if the last frame of the media object will be
     *          continuously presented or <i>null</i> if the attribute is not
     *          defined.
     */
    public Boolean getFreeze() {
        return freeze;
    }

    
    /**
     * Sets the index to receive the focus when the left key is pressed. This
     * attribute is optional. Set the left to <i>null</i> to erase a left
     * already defined.
     * 
     * <br/>
     * 
     * The left attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @param descriptor
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> to erase a left already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setMoveLeft(Ed descriptor) throws XMLException {
        Ed aux = this.moveLeft;
        
        this.moveLeft = descriptor;
        if(this.moveLeft != null){
            this.moveLeft.setOwner((T) this);
            this.moveLeft.setOwnerAtt(NCLElementAttributes.MOVELEFT);
        }
        
        impl.notifyAltered(NCLElementAttributes.MOVELEFT, aux, descriptor);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the index to receive the focus when the left key is pressed or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The left attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @return 
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> if the attribute is not defined.
     */
    public Ed getMoveLeft() {
        return moveLeft;
    }


    /**
     * Sets the index to receive the focus when the right key is pressed. This
     * attribute is optional. Set the right to <i>null</i> to erase a right
     * already defined.
     * 
     * <br/>
     * 
     * The right attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @param descriptor
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> to erase a left already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setMoveRight(Ed descriptor) throws XMLException {
        Ed aux = this.moveRight;
        
        this.moveRight = descriptor;
        if(this.moveRight != null){
            this.moveRight.setOwner((T) this);
            this.moveRight.setOwnerAtt(NCLElementAttributes.MOVERIGHT);
        }
        
        impl.notifyAltered(NCLElementAttributes.MOVERIGHT, aux, descriptor);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the index to receive the focus when the right key is pressed or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The right attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @return 
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> if the attribute is not defined.
     */
    public Ed getMoveRight() {
        return moveRight;
    }


    /**
     * Sets the index to receive the focus when the up key is pressed. This
     * attribute is optional. Set the up to <i>null</i> to erase a up
     * already defined.
     * 
     * <br/>
     * 
     * The up attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @param descriptor
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> to erase a up already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setMoveUp(Ed descriptor) throws XMLException {
        Ed aux = this.moveUp;
        
        this.moveUp = descriptor;
        if(this.moveUp != null){
            this.moveUp.setOwner((T) this);
            this.moveUp.setOwnerAtt(NCLElementAttributes.MOVEUP);
        }
        
        impl.notifyAltered(NCLElementAttributes.MOVEUP, aux, descriptor);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the index to receive the focus when the up key is pressed or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The up attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @return 
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> if the attribute is not defined.
     */
    public Ed getMoveUp() {
        return moveUp;
    }


    /**
     * Sets the index to receive the focus when the down key is pressed. This
     * attribute is optional. Set the down to <i>null</i> to erase a down
     * already defined.
     * 
     * <br/>
     * 
     * The down attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @param descriptor
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> to erase a down already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the descriptor.
     */
    public void setMoveDown(Ed descriptor) throws XMLException {
        Ed aux = this.moveDown;
        
        this.moveDown = descriptor;
        if(this.moveDown != null){
            this.moveDown.setOwner((T) this);
            this.moveDown.setOwnerAtt(NCLElementAttributes.MOVEDOWN);
        }
        
        impl.notifyAltered(NCLElementAttributes.MOVEDOWN, aux, descriptor);
        if(aux != null)
            aux.clean();
    }

    
    /**
     * Returns the index to receive the focus when the down key is pressed or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The down attribute is used for navigating among the media objects
     * presented in the screen.
     * 
     * @return 
     *          element representing a reference to the descriptor that will
     *          receive focus or <i>null</i> if the attribute is not defined.
     */
    public Ed getMoveDown() {
        return moveDown;
    }  
    
    
    /**
     * Sets the index for a media object receive the focus. This attribute is
     * optional. Set the index to <i>null</i> to erase an index already defined.
     * 
     * <br/>
     * 
     * When a media object is presented, the media whose descriptor has the
     * smaller value of focus index will receive the focus.
     * 
     * @param focusIndex 
     *          element representing the focus index of the descriptor or
     *          <i>null</i> to erase a focus index already defined.
     *          
     */
    public void setFocusIndex(FocusIndexType focusIndex) {
        FocusIndexType aux = this.focusIndex;
        this.focusIndex = focusIndex;
        impl.notifyAltered(NCLElementAttributes.FOCUSINDEX, aux, focusIndex);
    }


    /**
     * Returns the index for a media object receive the focus or <i>null</i> if
     * the attribute is not defined.
     * 
     * <br/>
     * 
     * When a media object is presented, the media whose descriptor has the
     * smaller value of focus index will receive the focus.
     * 
     * @return 
     *          element representing the focus index of the descriptor or
     *          <i>null</i> if the attribute is not defined.
     */
    public FocusIndexType getFocusIndex() {
        return focusIndex;
    }


    /**
     * Sets the color of the border of the media object that received focus.
     * This attribute is optional. Set the border color to <i>null</i> to erase
     * a border color already defined.
     * 
     * @param focusBorderColor 
     *          element representing the color of the border or <i>null</i> to
     *          erase a border color already defined.
     */
    public void setFocusBorderColor(NCLColor focusBorderColor) {
        NCLColor aux = this.focusBorderColor;
        this.focusBorderColor = focusBorderColor;
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERCOLOR, aux, focusBorderColor);
    }


    /**
     * Returns the color of the border of the media object that received focus
     * or <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          element representing the color of the border or <i>null</i> if
     *          the attribute is not defined.
     */
    public NCLColor getFocusBorderColor() {
        return focusBorderColor;
    }

    
    /**
     * Sets the width of the border of the media object that received focus.
     * This attribute is optional. Set the border width to <i>null</i> to erase
     * a border width already defined.
     * 
     * @param focusBorderWidth 
     *          integer representing the width of the border or <i>null</i> to
     *          erase a border width already defined.
     */
    public void setFocusBorderWidth(Integer focusBorderWidth) {
        Integer aux = this.focusBorderWidth;
        this.focusBorderWidth = focusBorderWidth;
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERWIDTH, aux, focusBorderWidth);
    }


    /**
     * Returns the width of the border of the media object that received focus
     * or <i>null</i> if the attribute is not defined.
     *
     * @return
     *          integer representing the width of the border or <i>null</i> if
     *          the attribute is not defined.
     */
    public Integer getFocusBorderWidth() {
        return focusBorderWidth;
    }


    /**
     * Sets the transparency of the border of the media object that received focus.
     * This attribute is optional. Set the border transparency to <i>null</i> to
     * erase a border transparency already defined.
     * 
     * @param focusBorderTransparency 
     *          element representing the transparency of the border or <i>null</i>
     *          to erase a border transparency already defined.
     */
    public void setFocusBorderTransparency(PercentageType focusBorderTransparency) {
        PercentageType aux = this.focusBorderTransparency;
        this.focusBorderTransparency = focusBorderTransparency;
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERTRANSPARENCY, aux, focusBorderTransparency);
    }


    /**
     * Returns the transparency of the border of the media object that received
     * focus or <i>null</i> if the attribute is not defined.
     *
     * @return
     *          element representing the transparency of the border or <i>null</i>
     *          if the attribute is not defined.
     */
    public PercentageType getFocusBorderTransparency() {
        return focusBorderTransparency;
    }


    /**
     * Sets the alternative content to be presented when the media object receive
     * focus. This attribute is optional. Set the alternative content to <i>null</i>
     * to erase an alternative content already defined.
     * 
     * @param focusSrc 
     *          element representing the location of the alternative content or
     *          <i>null</i> to erase an alternative content already defined.
     */
    public void setFocusSrc(SrcType focusSrc) {
        SrcType aux = this.focusSrc;
        this.focusSrc = focusSrc;
        impl.notifyAltered(NCLElementAttributes.FOCUSSRC, aux, focusSrc);
    }


    /**
     * Returns the alternative content to be presented when the media object
     * receive focus or <i>null</i> if the attribute is not defined.
     *
     * @return
     *          element representing the location of the alternative content or
     *          <i>null</i> if the attribute is not defined.
     */
    public SrcType getFocusSrc() {
        return focusSrc;
    }


    /**
     * Sets the alternative content to be presented when the media object is
     * selected. This attribute is optional. Set the alternative content to
     * <i>null</i> to erase an alternative content already defined.
     * 
     * @param focusSelSrc 
     *          element representing the location of the alternative content or
     *          <i>null</i> to erase an alternative content already defined.
     */
    public void setFocusSelSrc(SrcType focusSelSrc) {
        SrcType aux = this.focusSelSrc;
        this.focusSelSrc = focusSelSrc;
        impl.notifyAltered(NCLElementAttributes.FOCUSSELSRC, aux, focusSelSrc);
    }


    /**
     * Returns the alternative content to be presented when the media object is
     * selected or <i>null</i> if the attribute is not defined.
     *
     * @return
     *          element representing the location of the alternative content or
     *          <i>null</i> if the attribute is not defined.
     */    
    public SrcType getFocusSelSrc() {
        return focusSelSrc;
    }


    /**
     * Sets the color of the border of the media object when it is selected.
     * This attribute is optional. Set the border color to <i>null</i> to erase
     * a border color already defined.
     * 
     * @param selBorderColor 
     *          element representing the color of the border or <i>null</i> to
     *          erase a border color already defined.
     */
    public void setSelBorderColor(NCLColor selBorderColor) {
        NCLColor aux = this.selBorderColor;
        this.selBorderColor = selBorderColor;
        impl.notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, selBorderColor);
    }


    /**
     * Returns the color of the border of the media object when it is selected
     * or <i>null</i> if the attribute is not defined.
     * 
     * @return
     *          element representing the color of the border or <i>null</i> if
     *          the attribute is not defined.
     */
    public NCLColor getSelBorderColor() {
        return selBorderColor;
    }


    /**
     * Sets the transition used at the beginning of a media object presentation.
     * This attribute is optional. Set the transition to <i>null</i> to erase a
     * transition already defined.
     * 
     * <br/>
     * 
     * The transition referred can be defined in the document base of transitions
     * or in a base defined in an external document, imported by the base of
     * transitions or by the base of imported documents. When the transition is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @param transIn
     *          element representing a reference to a transition or <i>null</i>
     *          to erase a transition already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the transition.
     */
    public void setTransIn(Et transIn) throws XMLException {
        Et aux = this.transIn;
        
        this.transIn = transIn;
        if(this.transIn != null){
            this.transIn.setOwner((T) this);
            this.transIn.setOwnerAtt(NCLElementAttributes.TRANSIN);
        }
        
        impl.notifyAltered(NCLElementAttributes.TRANSIN, aux, transIn);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the transition used at the beginning of a media object
     * presentation or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The transition referred can be defined in the document base of transitions
     * or in a base defined in an external document, imported by the base of
     * transitions or by the base of imported documents. When the transition is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     *
     * @return
     *          element representing a reference to a transition or <i>null</i>
     *          if the attribute is not defined.
     */
    public Et getTransIn() {
        return transIn;
    }


    /**
     * Sets the transition used at the end of a media object presentation. This
     * attribute is optional. Set the transition to <i>null</i> to erase a
     * transition already defined.
     * 
     * <br/>
     * 
     * The transition referred can be defined in the document base of transitions
     * or in a base defined in an external document, imported by the base of
     * transitions or by the base of imported documents. When the transition is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @param transOut
     *          element representing a reference to a transition or <i>null</i>
     *          to erase a transition already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the transition.
     */
    public void setTransOut(Et transOut) throws XMLException {
        Et aux = this.transOut;
        
        this.transOut = transOut;
        if(this.transOut != null){
            this.transOut.setOwner((T) this);
            this.transOut.setOwnerAtt(NCLElementAttributes.TRANSOUT);
        }
        
        impl.notifyAltered(NCLElementAttributes.TRANSOUT, aux, transOut);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the transition used at the end of a media object presentation or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The transition referred can be defined in the document base of transitions
     * or in a base defined in an external document, imported by the base of
     * transitions or by the base of imported documents. When the transition is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     *
     * @return
     *          element representing a reference to a transition or <i>null</i>
     *          if the attribute is not defined.
     */
    public Et getTransOut() {
        return transOut;
    }


    /**
     * Sets the region to be used for the presentation of a media object. This
     * attribute is optional. Set the region to <i>null</i> to erase a region
     * already defined.
     * 
     * <br/>
     * 
     * The region referred can be defined in the document base of regions or in
     * a base defined in an external document, imported by the base of regions
     * or by the base of imported documents. When the region is defined in an
     * external document, the alias of the imported document must be indicated
     * in the reference.
     *
     * @param region
     *          element representing a reference to a region or <i>null</i>
     *          to erase a region already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the region.
     */
    public void setRegion(Er region) throws XMLException {
        Er aux = this.region;
        
        this.region = region;
        if(this.region != null){
            this.region.setOwner((T) this);
            this.region.setOwnerAtt(NCLElementAttributes.REGION);
        }
        
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
        if(aux != null)
            aux.clean();
    }


    /**
     * Returns the region to be used for the presentation of a media object or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The region referred can be defined in the document base of regions or in
     * a base defined in an external document, imported by the base of regions
     * or by the base of imported documents. When the region is defined in an
     * external document, the alias of the imported document must be indicated
     * in the reference.
     *
     * @return
     *          element representing a reference to a region or <i>null</i>
     *          if the attribute is not defined.
     */
    public Er getRegion() {
        return region;
    }


    /**
     * Adds a descriptor parameter to the descriptor. The descriptor can have
     * none or several parameter elements.
     * 
     * <br/>
     * 
     * A descriptor parameter is used to define additional information to the
     * media object presentation. A parameter can redefine the value of
     * attributes defined by a region or define new attributes.
     * 
     * @param descriptorParam
     *          element representing a descriptor parameter.
     * @return
     *          true if the parameter was added.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean addDescriptorParam(Ep descriptorParam) throws XMLException {
        if(params.add(descriptorParam, (T) this)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORPARAM, descriptorParam);
            return true;
        }
        return false;
    }


    /**
     * Removes a descriptor parameter of the descriptor. The descriptor can have
     * none or several parameter elements.
     * 
     * <br/>
     * 
     * A descriptor parameter is used to define additional information to the
     * media object presentation. A parameter can redefine the value of
     * attributes defined by a region or define new attributes.
     * 
     * @param descriptorParam
     *          element representing a descriptor parameter.
     * @return
     *          true if the parameter was removed.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean removeDescriptorParam(Ep descriptorParam) throws XMLException {
        if(params.remove(descriptorParam)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORPARAM, descriptorParam);
            return true;
        }
        return false;
    }


    /**
     * Verifies if the descriptor has a specific element representing a descriptor
     * parameter. The descriptor can have none or several parameter elements.
     * 
     * <br/>
     * 
     * A descriptor parameter is used to define additional information to the
     * media object presentation. A parameter can redefine the value of
     * attributes defined by a region or define new attributes.
     * 
     * @param descriptorParam
     *          element representing a descriptor parameter.
     * @return
     *          true if the descriptor has the parameter element.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean hasDescriptorParam(Ep descriptorParam) throws XMLException {
        return params.contains(descriptorParam);
    }


    /**
     * Verifies if the descriptor has at least one descriptor parameter. The
     * descriptor can have none or several parameter elements.
     * 
     * <br/>
     * 
     * A descriptor parameter is used to define additional information to the
     * media object presentation. A parameter can redefine the value of
     * attributes defined by a region or define new attributes.
     * 
     * @return 
     *          true if the descriptor has at least one parameter.
     */
    public boolean hasDescriptorParam() {
        return !params.isEmpty();
    }


    /**
     * Returns the list of descriptor parameters that a descriptor have. The
     * descriptor can have none or several parameter elements.
     * 
     * <br/>
     * 
     * A descriptor parameter is used to define additional information to the
     * media object presentation. A parameter can redefine the value of
     * attributes defined by a region or define new attributes.
     * 
     * @return 
     *          element list with all parameters.
     */
    public ElementList<Ep, T> getDescriptorParams() {
        return params;
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
