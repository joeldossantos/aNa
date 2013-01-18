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

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.SrcType;
import br.uff.midiacom.ana.util.TimeType;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import br.uff.midiacom.ana.util.enums.NCLColor;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.PercentageType;
import java.util.ArrayList;
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
public class NCLDescriptor<T extends NCLElement,
                           Er extends NCLRegion,
                           Ed extends NCLDescriptor,
                           Et extends NCLTransition,
                           Ep extends NCLDescriptorParam,
                           El extends NCLLayoutDescriptor,
                           R extends ExternalReferenceType>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLLayoutDescriptor<T, El>, PostReferenceElement {

    protected String player;
    protected TimeType explicitDur;
    protected Boolean freeze;
    protected Ed moveLeft;
    protected Ed moveRight;
    protected Ed moveUp;
    protected Ed moveDown;
    protected Integer focusIndex;
    protected NCLColor focusBorderColor;
    protected Integer focusBorderWidth;
    protected PercentageType focusBorderTransparency;
    protected SrcType focusSrc;
    protected SrcType focusSelSrc;
    protected NCLColor selBorderColor;
    protected Object transIn;
    protected Object transOut;
    protected Object region;
    protected ElementList<Ep> params;
    
    protected ArrayList<T> references;
    protected boolean waiting;


    /**
     * Descriptor element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptor() {
        super();
        params = new ElementList<Ep>();
        references = new ArrayList<T>();
        waiting = false;
    }
    
    
    public NCLDescriptor(String id) throws XMLException {
        super();
        params = new ElementList<Ep>();
        references = new ArrayList<T>();
        waiting = false;
        setId(id);
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ep aux : params) {
            aux.setDoc(doc);
        }
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
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
        String aux = this.player;
        this.player = player;
        notifyAltered(NCLElementAttributes.PLAYER, aux, player);
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
        return player;
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
    public void setExplicitDur(TimeType explicitDur) throws XMLException {
        TimeType aux = this.explicitDur;
        this.explicitDur = explicitDur;
        notifyAltered(NCLElementAttributes.EXPLICITDUR, aux, explicitDur);
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
    public void setFreeze(Boolean freeze) throws XMLException {
        Boolean aux = this.freeze;
        this.freeze = freeze;
        notifyAltered(NCLElementAttributes.FREEZE, aux, freeze);
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
        if(this.moveLeft != null)
            this.moveLeft.addReference(this);
        
        notifyAltered(NCLElementAttributes.MOVELEFT, aux, descriptor);
        if(aux != null)
            aux.removeReference(this);
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
        if(this.moveRight != null)
            this.moveRight.addReference(this);
        
        notifyAltered(NCLElementAttributes.MOVERIGHT, aux, descriptor);
        if(aux != null)
            aux.removeReference(this);
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
        if(this.moveUp != null)
            this.moveUp.addReference(this);
        
        notifyAltered(NCLElementAttributes.MOVEUP, aux, descriptor);
        if(aux != null)
            aux.removeReference(this);
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
        if(this.moveDown != null)
            this.moveDown.addReference(this);
        
        notifyAltered(NCLElementAttributes.MOVEDOWN, aux, descriptor);
        if(aux != null)
            aux.removeReference(this);
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
     *          string or integer representing the focus index of the descriptor
     *          or <i>null</i> to erase a focus index already defined.
     *          
     */
    public void setFocusIndex(Object focusIndex) throws XMLException {
        Object aux = this.focusIndex;
        
        if(focusIndex == null){
            this.focusIndex = null;
            notifyAltered(NCLElementAttributes.FOCUSINDEX, aux, focusIndex);
            return;
        }
        
        if(focusIndex instanceof String){
            String value = (String) focusIndex;
            
            if("".equals(value.trim()))
                throw new XMLException("Empty focus index String");
            
            try{
                this.focusIndex = new Integer(value);
                
            }catch(Exception e){
                throw new XMLException("focus index must be an integer value");
            }
        }
        else if(focusIndex instanceof Integer)
            this.focusIndex = (Integer) focusIndex;
        
        notifyAltered(NCLElementAttributes.FOCUSINDEX, aux, this.focusIndex);
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
     *          string or integer representing the focus index of the descriptor
     *          or <i>null</i> if the attribute is not defined.
     */
    public Integer getFocusIndex() {
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
    public void setFocusBorderColor(NCLColor focusBorderColor) throws XMLException {
        NCLColor aux = this.focusBorderColor;
        this.focusBorderColor = focusBorderColor;
        notifyAltered(NCLElementAttributes.FOCUSBORDERCOLOR, aux, focusBorderColor);
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
    public void setFocusBorderWidth(Integer focusBorderWidth) throws XMLException {
        Integer aux = this.focusBorderWidth;
        this.focusBorderWidth = focusBorderWidth;
        notifyAltered(NCLElementAttributes.FOCUSBORDERWIDTH, aux, focusBorderWidth);
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
    public void setFocusBorderTransparency(PercentageType focusBorderTransparency) throws XMLException {
        PercentageType aux = this.focusBorderTransparency;
        this.focusBorderTransparency = focusBorderTransparency;
        notifyAltered(NCLElementAttributes.FOCUSBORDERTRANSPARENCY, aux, focusBorderTransparency);
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
    public void setFocusSrc(SrcType focusSrc) throws XMLException {
        SrcType aux = this.focusSrc;
        this.focusSrc = focusSrc;
        notifyAltered(NCLElementAttributes.FOCUSSRC, aux, focusSrc);
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
    public void setFocusSelSrc(SrcType focusSelSrc) throws XMLException {
        SrcType aux = this.focusSelSrc;
        this.focusSelSrc = focusSelSrc;
        notifyAltered(NCLElementAttributes.FOCUSSELSRC, aux, focusSelSrc);
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
    public void setSelBorderColor(NCLColor selBorderColor) throws XMLException {
        NCLColor aux = this.selBorderColor;
        this.selBorderColor = selBorderColor;
        notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, selBorderColor);
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
     *          element representing a transition or a reference to a transition
     *          or <i>null</i> to erase a transition already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the transition.
     */
    public void setTransIn(Object transIn) throws XMLException {
        Object aux = this.transIn;
        
        if(transIn instanceof NCLTransition){
            this.transIn = transIn;
            ((Et) transIn).addReference(this);
            
        }
        else if(transIn instanceof ExternalReferenceType){
            this.transIn = transIn;
            ((R) transIn).getTarget().addReference(this);
            ((R) transIn).getAlias().addReference(this);
        }
        
        this.transIn = transIn;
        notifyAltered(NCLElementAttributes.TRANSIN, aux, transIn);
        
        if(aux != null){
            if(aux instanceof NCLTransition)
                ((Et) transIn).removeReference(this);
            else{
                ((R) transIn).getTarget().removeReference(this);
                ((R) transIn).getAlias().removeReference(this);
            }
        }
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
     *          element representing a transition or a reference to a transition
     *          or <i>null</i> if the attribute is not defined.
     */
    public Object getTransIn() {
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
     *          element representing a transition or a reference to a transition
     *          or <i>null</i> to erase a transition already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the transition.
     */
    public void setTransOut(Object transOut) throws XMLException {
        Object aux = this.transOut;
        
        if(transOut instanceof NCLTransition){
            this.transOut = transOut;
            ((Et) transOut).addReference(this);
            
        }
        else if(transOut instanceof ExternalReferenceType){
            this.transOut = transOut;
            ((R) transOut).getTarget().addReference(this);
            ((R) transOut).getAlias().addReference(this);
        }
        
        this.transOut = transOut;
        notifyAltered(NCLElementAttributes.TRANSOUT, aux, transOut);
        
        if(aux != null){
            if(aux instanceof NCLTransition)
                ((Et) transOut).removeReference(this);
            else{
                ((R) transOut).getTarget().removeReference(this);
                ((R) transOut).getAlias().removeReference(this);
            }
        }
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
     *          element representing a transition or a reference to a transition
     *          or <i>null</i> if the attribute is not defined.
     */
    public Object getTransOut() {
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
     *          element representing a region or a reference to a region or
     *          <i>null</i> to erase a region already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the region.
     */
    public void setRegion(Object region) throws XMLException {
        Object aux = this.region;
        
        if(region instanceof NCLRegion){
            this.region = region;
            ((Er) region).addReference(this);
            
        }
        else if(region instanceof ExternalReferenceType){
            this.region = region;
            ((R) region).getTarget().addReference(this);
            ((R) region).getAlias().addReference(this);
        }
        
        this.region = region;
        notifyAltered(NCLElementAttributes.REGION, aux, region);
        
        if(aux != null){
            if(aux instanceof NCLRegion)
                ((Er) region).removeReference(this);
            else{
                ((R) region).getTarget().removeReference(this);
                ((R) region).getAlias().removeReference(this);
            }
        }
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
     *          element representing a region or a reference to a region or
     *          <i>null</i> if the attribute is not defined.
     */
    public Object getRegion() {
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
        if(params.add(descriptorParam)){
            notifyInserted((T) descriptorParam);
            descriptorParam.setParent(this);
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
            notifyRemoved((T) descriptorParam);
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
    public ElementList<Ep> getDescriptorParams() {
        return params;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLDescriptor))
            return false;
        
        boolean result = true;
        
        Object aux;
        if((aux = getId()) != null)
            result &= aux.equals(((NCLDescriptor) other).getId());
        if((aux = getPlayer()) != null)
            result &= aux.equals(((NCLDescriptor) other).getPlayer());
        if((aux = getExplicitDur()) != null)
            result &= aux.equals(((NCLDescriptor) other).getExplicitDur());
        if((aux = getFreeze()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFreeze());
        if((aux = getFocusIndex()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFocusIndex());
        if((aux = getFocusBorderColor()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFocusBorderColor());
        if((aux = getFocusBorderWidth()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFocusBorderWidth());
        if((aux = getFocusBorderTransparency()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFocusBorderTransparency());
        if((aux = getFocusSrc()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFocusSrc());
        if((aux = getFocusSelSrc()) != null)
            result &= aux.equals(((NCLDescriptor) other).getFocusSelSrc());
        if((aux = getSelBorderColor()) != null)
            result &= aux.equals(((NCLDescriptor) other).getSelBorderColor());
        
        
        Ed elThis, elOther;
        if((elThis = (Ed) getMoveLeft()) != null){
            if((elOther = (Ed) ((NCLDescriptor) other).getMoveLeft()) != null)
                result &= elThis.getFocusIndex().equals(elOther.getFocusIndex());
            else
                result &= false;
        }
        if((elThis = (Ed) getMoveRight()) != null){
            if((elOther = (Ed) ((NCLDescriptor) other).getMoveRight()) != null)
                result &= elThis.getFocusIndex().equals(elOther.getFocusIndex());
            else
                result &= false;
        }
        if((elThis = (Ed) getMoveUp()) != null){
            if((elOther = (Ed) ((NCLDescriptor) other).getMoveUp()) != null)
                result &= elThis.getFocusIndex().equals(elOther.getFocusIndex());
            else
                result &= false;
        }
        if((elThis = (Ed) getMoveDown()) != null){
            if((elOther = (Ed) ((NCLDescriptor) other).getMoveDown()) != null)
                result &= elThis.getFocusIndex().equals(elOther.getFocusIndex());
            else
                result &= false;
        }
        
        Object oaux;
        aux = getTransIn();
        oaux = ((NCLDescriptor) other).getTransIn();
        if(aux != null && oaux != null){
            if(aux instanceof NCLTransition && oaux instanceof NCLTransition)
                result &= ((Et) aux).compare((Et) oaux);
            else if(aux instanceof ExternalReferenceType && oaux instanceof ExternalReferenceType)
                result &= ((R) aux).equals((R) oaux);
            else
                result = false;
        }
        aux = getTransOut();
        oaux = ((NCLDescriptor) other).getTransOut();
        if(aux != null && oaux != null){
            if(aux instanceof NCLTransition && oaux instanceof NCLTransition)
                result &= ((Et) aux).compare((Et) oaux);
            else if(aux instanceof ExternalReferenceType && oaux instanceof ExternalReferenceType)
                result &= ((R) aux).equals((R) oaux);
            else
                result = false;
        }
        aux = getRegion();
        oaux = ((NCLDescriptor) other).getRegion();
        if(aux != null && oaux != null){
            if(aux instanceof NCLRegion && oaux instanceof NCLRegion)
                result &= ((Er) aux).compare((Er) oaux);
            else if(aux instanceof ExternalReferenceType && oaux instanceof ExternalReferenceType)
                result &= ((R) aux).equals((R) oaux);
            else
                result = false;
        }
        
        
        ElementList<Ep> otherpar = ((NCLDescriptor) other).getDescriptorParams();
        result &= params.size() == otherpar.size();
        for (Ep par : params) {
            try {
                result &= otherpar.contains(par);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        return result;
    }


    @Override
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


    @Override
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
        Object aux = getRegion();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLRegion)
            return " region='" + ((Er) aux).getId() + "'";
        else
            return " region='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadRegion(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] reg = adjustReference(att_var);
            setRegion(d.getHead().findRegion(null, reg[0], reg[1]));
        }
    }
    
    
    protected String parseExplicitDur() {
        TimeType aux = getExplicitDur();
        if(aux != null)
            return " explicitDur='" + aux.toString() + "'";
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
            return " moveLeft='" + aux.getFocusIndex().toString() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveLeft(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveLeft (optional)
        att_name = NCLElementAttributes.MOVELEFT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            Ed desc = (Ed) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(att_var);
            setMoveLeft((Ed) desc);
            if(!waiting){
                ((NCLDoc) getDoc()).waitReference(this);
                waiting = true;
            }
        }
    }
    
    
    protected String parseMoveRight() {
        Ed aux = getMoveRight();
        if(aux != null)
            return " moveRight='" + aux.getFocusIndex().toString() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveRight(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveRight (optional)
        att_name = NCLElementAttributes.MOVERIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            Ed desc = (Ed) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(att_var);
            setMoveRight((Ed) desc);
            if(!waiting){
                ((NCLDoc) getDoc()).waitReference(this);
                waiting = true;
            }
        }
    }
    
    
    protected String parseMoveDown() {
        Ed aux = getMoveDown();
        if(aux != null)
            return " moveDown='" + aux.getFocusIndex().toString() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveDown(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveDown (optional)
        att_name = NCLElementAttributes.MOVEDOWN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            Ed desc = (Ed) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(att_var);
            setMoveDown((Ed) desc);
            if(!waiting){
                ((NCLDoc) getDoc()).waitReference(this);
                waiting = true;
            }
        }
    }
    
    
    protected String parseMoveUp() {
        Ed aux = getMoveUp();
        if(aux != null)
            return " moveUp='" + aux.getFocusIndex().toString() + "'";
        else
            return "";
    }
    
    
    protected void loadMoveUp(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the moveUp (optional)
        att_name = NCLElementAttributes.MOVEUP.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            Ed desc = (Ed) new NCLDescriptor();
            desc.setId("aux" + att_var);
            desc.setFocusIndex(att_var);
            setMoveUp((Ed) desc);
            if(!waiting){
                ((NCLDoc) getDoc()).waitReference(this);
                waiting = true;
            }
        }
    }
    
    
    protected String parseFocusIndex() {
        Integer aux = getFocusIndex();
        if(aux != null)
            return " focusIndex='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadFocusIndex(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the focusIndex (optional)
        att_name = NCLElementAttributes.FOCUSINDEX.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setFocusIndex(att_var);
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
            return " focusSrc='" + aux.toString() + "'";
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
            return " focusSelSrc='" + aux.toString() + "'";
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
        Object aux = getTransIn();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLTransition)
            return " transIn='" + ((Et) aux).getId() + "'";
        else
            return " transIn='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadTransIn(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the transIn (optional)
        att_name = NCLElementAttributes.TRANSIN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] tra = adjustReference(att_var);
            setTransIn(d.getHead().findTransition(tra[0], tra[1]));
        }
    }
    
    
    protected String parseTransOut() {
        Object aux = getTransOut();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLTransition)
            return " transOut='" + ((Et) aux).getId() + "'";
        else
            return " transOut='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadTransOut(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the transOut (optional)
        att_name = NCLElementAttributes.TRANSOUT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] tra = adjustReference(att_var);
            setTransOut(d.getHead().findTransition(tra[0], tra[1]));
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
            Ep inst = createDescriptorParam();
            addDescriptorParam(inst);
            inst.load(el);
        }
    }
    
    
    @Override
    public El findDescriptor(String id) throws XMLException {
        if(getId().equals(id))
            return (El) this;
        else
            return null;
    }
    
    
    @Override
    public El findDescriptor(Integer focusIndex) throws XMLException {
        if(this.focusIndex != null && this.focusIndex.toString().equals(focusIndex.toString()))
            return (El) this;
        else
            return null;
    }
    
    
    @Override
    @Deprecated
    public void fixReference() throws NCLParsingException {
        Object aux;
        T base = getParent();

        while(!(base instanceof NCLDescriptorBase)){
            if(base == null){
                throw new NCLParsingException("Could not find descriptor base element.");
            }
            base = (T) base.getParent();
        }
        
        try{
            // set the moveUp (optional)
            if((aux = getMoveUp()) != null && (aux = getMoveUp().getFocusIndex()) != null){
                Ed desc = (Ed) ((NCLDescriptorBase) base).findDescriptor((Integer) aux);
                setMoveUp(desc);
            }

            // set the moveRight (optional)
            if((aux = getMoveRight()) != null && (aux = getMoveRight().getFocusIndex()) != null){
                Ed desc = (Ed) ((NCLDescriptorBase) base).findDescriptor((Integer) aux);
                setMoveRight(desc);
            }

            // set the moveLeft (optional)
            if((aux = getMoveLeft()) != null && (aux = getMoveLeft().getFocusIndex()) != null){
                Ed desc = (Ed) ((NCLDescriptorBase) base).findDescriptor((Integer) aux);
                setMoveLeft(desc);
            }

            // set the moveDown (optional)
            if((aux = getMoveDown()) != null && (aux = getMoveDown().getFocusIndex()) != null){
                Ed desc = (Ed) ((NCLDescriptorBase) base).findDescriptor((Integer) aux);
                setMoveDown(desc);
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
    
    
    @Override
    @Deprecated
    public boolean addReference(T reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(T reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
    
    
    @Override
    public void clean() throws XMLException {
        
        moveLeft.removeReference(this);
        moveRight.removeReference(this);
        moveUp.removeReference(this);
        moveDown.removeReference(this);
        
        if(region != null){
            if(region instanceof NCLRegion)
                ((Er)region).removeReference(this);

            else if(region instanceof ExternalReferenceType){
                ((R) region).getTarget().removeReference(this);
                ((R) region).getAlias().removeReference(this);
            }
        }
        
        player = null;
        explicitDur = null;
        freeze = null;
        moveLeft = null;
        moveRight = null;
        moveUp = null;
        moveDown = null;
        focusIndex = null;
        focusBorderColor = null;
        focusBorderWidth = null;
        focusBorderTransparency = null;
        focusSrc = null;
        focusSelSrc = null;
        selBorderColor = null;
        transIn = null;
        transOut = null;
        region = null;
        
        for(Ep p : params)
            p.clean();
    }
    

    /**
     * Function to create the child element <i>descriptorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorParam</i>.
     */
    protected Ep createDescriptorParam() throws XMLException {
        return (Ep) new NCLDescriptorParam();
    }
}
