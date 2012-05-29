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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.descriptor.param.NCLDescriptorParam;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.basic.FocusIndexType;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.PostReferenceElement;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.aux.reference.TransitionReference;
import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.descriptor.param.NCLBooleanDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLColorDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLDoubleDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLFitDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLFontVariantDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLFontWeightDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLIntegerDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLPercentDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLPlayerLifeDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLRelativeDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLScrollDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLStringDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLTranspColorDescriptorParam;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.aux.ItemList;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.number.PercentageType;
import br.uff.midiacom.xml.datatype.reference.ReferenceType;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


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
public class NCLDescriptor<T extends NCLDescriptor,
                           P extends NCLElement,
                           I extends NCLElementImpl,
                           El extends NCLLayoutDescriptor,
                           Er extends RegionReference,
                           Ed extends DescriptorReference,
                           Et extends TransitionReference,
                           Ep extends NCLDescriptorParam>
        extends NCLIdentifiableElementPrototype<El, P, I>
        implements NCLLayoutDescriptor<El, P>, PostReferenceElement {

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
    public NCLDescriptor() throws XMLException {
        super();
        params = new ElementList<Ep, T>();
        references = new ItemList<ReferenceType>();
    }
    
    
    public NCLDescriptor(String id) throws XMLException {
        super();
        params = new ElementList<Ep, T>();
        references = new ItemList<ReferenceType>();
        setId(id);
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


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<descriptor";
        content += parseAttributes();

        // Test if the descriptor has content
        if(hasDescriptorParam()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</descriptor>\n";
        }
        else
            content += "/>\n";

        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
            loadPlayer(element);
            loadExplicitDur(element);
            loadFreeze(element);
            loadMoveUp(element);
            loadMoveRight(element);
            loadMoveLeft(element);
            loadMoveDown(element);
            loadFocusIndex(element);
            loadFocusBorderColor(element);
            loadFocusBorderWidth(element);
            loadFocusBorderTransparency(element);
            loadFocusSrc(element);
            loadFocusSelSrc(element);
            loadSelBorderColor(element);
            loadTransIn(element);
            loadTransOut(element);
            loadRegion(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Descriptor" + aux + ":\n" + ex.getMessage());
        }


        try{
            loadDescriptorParams(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Descriptor" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseRegion();
        content += parseExplicitDur();
        content += parseFreeze();
        content += parsePlayer();
        content += parseMoveLeft();
        content += parseMoveRight();
        content += parseMoveDown();
        content += parseMoveUp();
        content += parseFocusIndex();
        content += parseFocusBorderColor();
        content += parseFocusBorderWidth();
        content += parseFocusBorderTransparency();
        content += parseFocusSrc();
        content += parseFocusSelSrc();
        content += parseSelBorderColor();
        content += parseTransIn();
        content += parseTransOut();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseDescriptorParams(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseRegion() {
        Er aux = getRegion();
        if(aux != null)
            return " region='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRegion(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setRegion((Er) NCLReferenceManager.getInstance().findRegionReference(impl.getDoc(), att_var));
        }
    }
    
    
    protected String parseExplicitDur() {
        TimeType aux = getExplicitDur();
        if(aux != null)
            return " explicitDur='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadExplicitDur(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the explicitDur (optional)
        att_name = NCLElementAttributes.EXPLICITDUR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setExplicitDur(new TimeType(att_var));
    }
    
    
    protected String parseFreeze() {
        Boolean aux = getFreeze();
        if(aux != null)
            return " freeze='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadFreeze(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the freeze (optional)
        att_name = NCLElementAttributes.FREEZE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFreeze(Boolean.parseBoolean(att_var));
    }
    
    
    protected String parsePlayer() {
        String aux = getPlayer();
        if(aux != null)
            return " player='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadPlayer(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the player (optional)
        att_name = NCLElementAttributes.PLAYER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setPlayer(att_var);
    }
    
    
    protected String parseMoveLeft() {
        Ed aux = getMoveLeft();
        if(aux != null)
            return " moveLeft='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveLeft(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveLeft (optional)
        att_name = NCLElementAttributes.MOVELEFT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T desc = (T) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(new FocusIndexType(att_var));
            setMoveLeft(createDescriptorRef(desc));
            NCLReferenceManager.getInstance().waitReference(this);
        }
    }
    
    
    protected String parseMoveRight() {
        Ed aux = getMoveRight();
        if(aux != null)
            return " moveRight='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveRight(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveRight (optional)
        att_name = NCLElementAttributes.MOVERIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T desc = (T) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(new FocusIndexType(att_var));
            setMoveRight(createDescriptorRef(desc));
            NCLReferenceManager.getInstance().waitReference(this);
        }
    }
    
    
    protected String parseMoveDown() {
        Ed aux = getMoveDown();
        if(aux != null)
            return " moveDown='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveDown(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveDown (optional)
        att_name = NCLElementAttributes.MOVEDOWN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T desc = (T) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(new FocusIndexType(att_var));
            setMoveDown(createDescriptorRef(desc));
            NCLReferenceManager.getInstance().waitReference(this);
        }
    }
    
    
    protected String parseMoveUp() {
        Ed aux = getMoveUp();
        if(aux != null)
            return " moveUp='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveUp(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveUp (optional)
        att_name = NCLElementAttributes.MOVEUP.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T desc = (T) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(new FocusIndexType(att_var));
            setMoveUp(createDescriptorRef(desc));
            NCLReferenceManager.getInstance().waitReference(this);
        }
    }
    
    
    protected String parseFocusIndex() {
        FocusIndexType aux = getFocusIndex();
        if(aux != null)
            return " focusIndex='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadFocusIndex(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusIndex (optional)
        att_name = NCLElementAttributes.FOCUSINDEX.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setFocusIndex(new FocusIndexType(att_var));
        }
    }
    
    
    protected String parseFocusBorderColor() {
        NCLColor aux = getFocusBorderColor();
        if(aux != null)
            return " focusBorderColor='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadFocusBorderColor(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusBorderColor (optional)
        att_name = NCLElementAttributes.FOCUSBORDERCOLOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFocusBorderColor(NCLColor.getEnumType(att_var));
    }
    
    
    protected String parseFocusBorderWidth() {
        Integer aux = getFocusBorderWidth();
        if(aux != null)
            return " focusBorderWidth='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadFocusBorderWidth(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusBorderWidth (optional)
        att_name = NCLElementAttributes.FOCUSBORDERWIDTH.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            try{
                setFocusBorderWidth(new Integer(att_var));
            }catch(Exception e){
                throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
            }
        }
    }
    
    
    protected String parseFocusBorderTransparency() {
        PercentageType aux = getFocusBorderTransparency();
        if(aux != null)
            return " focusBorderTransparency='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadFocusBorderTransparency(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusBorderTransparency (optional)
        att_name = NCLElementAttributes.FOCUSBORDERTRANSPARENCY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFocusBorderTransparency(new PercentageType(att_var));
    }
    
    
    protected String parseFocusSrc() {
        SrcType aux = getFocusSrc();
        if(aux != null)
            return " focusSrc='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadFocusSrc(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusSrc (optional)
        att_name = NCLElementAttributes.FOCUSSRC.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFocusSrc(new SrcType(att_var));
    }
    
    
    protected String parseFocusSelSrc() {
        SrcType aux = getFocusSelSrc();
        if(aux != null)
            return " focusSelSrc='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadFocusSelSrc(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusSelSrc (optional)
        att_name = NCLElementAttributes.FOCUSSELSRC.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setFocusSelSrc(new SrcType(att_var));
    }
    
    
    protected String parseSelBorderColor() {
        NCLColor aux = getSelBorderColor();
        if(aux != null)
            return " SelBorderColor='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadSelBorderColor(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the selBorderColor (optional)
        att_name = NCLElementAttributes.SELBORDERCOLOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setSelBorderColor(NCLColor.getEnumType(att_var));
    }
    
    
    protected String parseTransIn() {
        Et aux = getTransIn();
        if(aux != null)
            return " transIn='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadTransIn(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the transIn (optional)
        att_name = NCLElementAttributes.TRANSIN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setTransIn((Et) NCLReferenceManager.getInstance().findTransitionReference(impl.getDoc(), att_var));
        }
    }
    
    
    protected String parseTransOut() {
        Et aux = getTransOut();
        if(aux != null)
            return " transOut='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadTransOut(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the transOut (optional)
        att_name = NCLElementAttributes.TRANSOUT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setTransOut((Et) NCLReferenceManager.getInstance().findTransitionReference(impl.getDoc(), att_var));
        }
    }
    
    
    protected String parseDescriptorParams(int ident) {
        if(!hasDescriptorParam())
            return "";
        
        String content = "";
        for(Ep aux : params)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadDescriptorParams(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the descriptor child nodes
        ch_name = NCLElementAttributes.DESCRIPTORPARAM.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ep inst = createParamByType(el);
            addDescriptorParam(inst);
            inst.load(el);
        }
    }
    
    
    public El findDescriptor(String id) throws XMLException {
        if(getId().equals(id))
            return (El) this;
        else
            return null;
    }
    
    
    public El findDescriptor(FocusIndexType focusIndex) throws XMLException {
        if(this.focusIndex != null && this.focusIndex.parse().equals(focusIndex.parse()))
            return (El) this;
        else
            return null;
    }


    private Ep createParamByType(Element element) throws XMLException {
        String att_name, att_var;
        NCLAttributes att = NCLAttributes.DEFAULT;
        
        att_name = NCLElementAttributes.NAME.toString();
        if((att_var = element.getAttribute(att_name)).isEmpty())
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
        
        for(NCLAttributes a : NCLAttributes.values()){
            if(a.toString().equals(att_var))
                att = a;
        }

        switch(att){
            case TOP:
                return createRelativeDescriptorParam();
            case LEFT:
                return createRelativeDescriptorParam();
            case BOTTOM:
                return createRelativeDescriptorParam();
            case RIGHT:
                return createRelativeDescriptorParam();
            case WIDTH:
                return createRelativeDescriptorParam();
            case HEIGHT:
                return createRelativeDescriptorParam();
            case LOCATION:
                return createStringDescriptorParam();
            case SIZE:
                return createStringDescriptorParam();
            case BOUNDS:
                return createStringDescriptorParam();
            case BACKGROUND:
                return createTranspColorDescriptorParam();
            case VISIBLE:
                return createBooleanDescriptorParam();
            case TRANSPARENCY:
                return createPercentDescriptorParam();
            case FIT:
                return createFitDescriptorParam();
            case SCROLL:
                return createScrollDescriptorParam();
            case STYLE:
                return createStringDescriptorParam();
            case SOUND_LEVEL:
                return createPercentDescriptorParam();
            case BALANCE_LEVEL:
                return createPercentDescriptorParam();
            case TREBLE_LEVEL:
                return createPercentDescriptorParam();
            case BASS_LEVEL:
                return createPercentDescriptorParam();
            case ZINDEX:
                return createIntegerDescriptorParam();
            case FONT_FAMILY:
                return createStringDescriptorParam();
            case FONT_SIZE:
                return createDoubleDescriptorParam();
            case FONT_VARIANT:
                return createFontVariantDescriptorParam();
            case FONT_WEIGHT:
                return createFontWeightDescriptorParam();
            case FONT_COLOR:
                return createColorDescriptorParam();
            case REUSE_PLAYER:
                return createBooleanDescriptorParam();
            case PLAYER_LIFE:
                return createPlayerLifeDescriptorParam();
            default:
                return createStringDescriptorParam();
        }
    }
    
    
    public void fixReference() throws NCLParsingException {
        FocusIndexType aux;
        NCLElement base = getParent();

        while(!(base instanceof NCLDescriptorBase)){
            base = (NCLElement) base.getParent();
            if(base == null){
                throw new NCLParsingException("Could not find descriptor base element.");
            }
        }
        
        try{
            // set the moveUp (optional)
            if((aux = ((T) getMoveUp().getTarget()).getFocusIndex()) != null){
                T desc = (T) ((NCLDescriptorBase) base).findDescriptor(aux);
                setMoveUp(createDescriptorRef(desc));
            }

            // set the moveRight (optional)
            if((aux = ((T) getMoveRight().getTarget()).getFocusIndex()) != null){
                T desc = (T) ((NCLDescriptorBase) base).findDescriptor(aux);
                setMoveRight(createDescriptorRef(desc));
            }

            // set the moveLeft (optional)
            if((aux = ((T) getMoveLeft().getTarget()).getFocusIndex()) != null){
                T desc = (T) ((NCLDescriptorBase) base).findDescriptor(aux);
                setMoveLeft(createDescriptorRef(desc));
            }

            // set the moveDown (optional)
            if((aux = ((T) getMoveDown().getTarget()).getFocusIndex()) != null){
                T desc = (T) ((NCLDescriptorBase) base).findDescriptor(aux);
                setMoveDown(createDescriptorRef(desc));
            }
        }
        catch(XMLException ex){
            String ax = getId();
            if(ax != null)
                ax = "(" + ax + ")";
            else
                ax = "";
            
            throw new NCLParsingException("Descriptor" + ax + ". Fixing reference:\n" + ex.getMessage());
        }
    }
    

    /**
     * Function to create the child element <i>descriptorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorParam</i>.
     */
    protected Ep createStringDescriptorParam() throws XMLException {
        return (Ep) new NCLStringDescriptorParam();
    }


    protected Ep createDoubleDescriptorParam() throws XMLException {
        return (Ep) new NCLDoubleDescriptorParam();
    }


    protected Ep createBooleanDescriptorParam() throws XMLException {
        return (Ep) new NCLBooleanDescriptorParam();
    }


    protected Ep createColorDescriptorParam() throws XMLException {
        return (Ep) new NCLColorDescriptorParam();
    }


    protected Ep createPercentDescriptorParam() throws XMLException {
        return (Ep) new NCLPercentDescriptorParam();
    }


    protected Ep createFitDescriptorParam() throws XMLException {
        return (Ep) new NCLFitDescriptorParam();
    }


    protected Ep createScrollDescriptorParam() throws XMLException {
        return (Ep) new NCLScrollDescriptorParam();
    }


    protected Ep createFontVariantDescriptorParam() throws XMLException {
        return (Ep) new NCLFontVariantDescriptorParam();
    }


    protected Ep createFontWeightDescriptorParam() throws XMLException {
        return (Ep) new NCLFontWeightDescriptorParam();
    }


    protected Ep createPlayerLifeDescriptorParam() throws XMLException {
        return (Ep) new NCLPlayerLifeDescriptorParam();
    }


    protected Ep createRelativeDescriptorParam() throws XMLException {
        return (Ep) new NCLRelativeDescriptorParam();
    }


    protected Ep createTranspColorDescriptorParam() throws XMLException {
        return (Ep) new NCLTranspColorDescriptorParam();
    }


    protected Ep createIntegerDescriptorParam() throws XMLException {
        return (Ep) new NCLIntegerDescriptorParam();
    }


    /**
     * Function to create a reference to a region.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a region.
     */
    protected Er createRegionRef(NCLRegion ref) throws XMLException {
        return (Er) new RegionReference(ref, NCLElementAttributes.ID);
    }


    /**
     * Function to create a reference to a descriptor.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a descriptor.
     */
    protected Ed createDescriptorRef(NCLLayoutDescriptor ref) throws XMLException {
        return (Ed) new DescriptorReference(ref, NCLElementAttributes.FOCUSINDEX);
    }


    /**
     * Function to create a reference to a transition.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a transition.
     */
    protected Et createTransitionRef(NCLTransition ref) throws XMLException {
        return (Et) new TransitionReference(ref, NCLElementAttributes.ID);
    }
}
