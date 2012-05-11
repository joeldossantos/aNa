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
import br.uff.midiacom.ana.datatype.aux.reference.PostReferenceElement;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.aux.reference.TransitionReference;
import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorPrototype;
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
import br.uff.midiacom.xml.datatype.number.PercentageType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLDescriptor<T extends NCLDescriptor,
                           P extends NCLElement,
                           I extends NCLElementImpl,
                           El extends NCLLayoutDescriptor,
                           Er extends RegionReference,
                           Ed extends DescriptorReference,
                           Et extends TransitionReference,
                           Ep extends NCLDescriptorParam>
        extends NCLDescriptorPrototype<T, P, I, El, Er, Ed, Et, Ep>
        implements NCLLayoutDescriptor<El, P>, PostReferenceElement {


    public NCLDescriptor() throws XMLException {
        super();
    }
    
    
    public NCLDescriptor(String id) throws XMLException {
        super();
        setId(id);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
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
            T desc = (T) new NCLDescriptor("aux" + att_var);
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
            T desc = (T) new NCLDescriptor("aux" + att_var);
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
            T desc = (T) new NCLDescriptor("aux" + att_var);
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
            T desc = (T) new NCLDescriptor("aux" + att_var);
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
