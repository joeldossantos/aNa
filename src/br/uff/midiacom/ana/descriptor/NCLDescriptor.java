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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.descriptor.param.NCLDescriptorParam;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.aux.reference.PostReferenceElement;
import br.uff.midiacom.ana.datatype.aux.basic.SrcType;
import br.uff.midiacom.ana.datatype.aux.basic.TimeType;
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
import br.uff.midiacom.ana.descriptor.param.NCLPercentDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLPlayerLifeDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLScrollDescriptorParam;
import br.uff.midiacom.ana.descriptor.param.NCLStringDescriptorParam;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.PercentageType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLDescriptor<T extends NCLDescriptor,
                           P extends NCLElement,
                           I extends NCLElementImpl,
                           Er extends NCLRegion,
                           El extends NCLLayoutDescriptor,
                           Et extends NCLTransition,
                           Ep extends NCLDescriptorParam>
        extends NCLDescriptorPrototype<T, P, I, Er, El, Et, Ep>
        implements NCLLayoutDescriptor<El, P>, PostReferenceElement {


    public NCLDescriptor(String id) throws XMLException {
        super(id);
    }
    
    
    public NCLDescriptor() throws XMLException {
        super();
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
    
    
    protected String parseRegion() {
        Er aux = getRegion();
        if(aux != null)
            return " region='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseExplicitDur() {
        TimeType aux = getExplicitDur();
        if(aux != null)
            return " explicitDur='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseFreeze() {
        Boolean aux = getFreeze();
        if(aux != null)
            return " freeze='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parsePlayer() {
        String aux = getPlayer();
        if(aux != null)
            return " player='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseMoveLeft() {
        T aux = getMoveLeft();
        if(aux != null)
            return " moveLeft='" + aux.getFocusIndex() + "'";
        else
            return "";
    }
    
    
    protected String parseMoveRight() {
        T aux = getMoveRight();
        if(aux != null)
            return " moveRight='" + aux.getFocusIndex() + "'";
        else
            return "";
    }
    
    
    protected String parseMoveDown() {
        T aux = getMoveDown();
        if(aux != null)
            return " moveDown='" + aux.getFocusIndex() + "'";
        else
            return "";
    }
    
    
    protected String parseMoveUp() {
        T aux = getMoveUp();
        if(aux != null)
            return " moveUp='" + aux.getFocusIndex() + "'";
        else
            return "";
    }
    
    
    protected String parseFocusIndex() {
        Integer aux = getFocusIndex();
        if(aux != null)
            return " focusIndex='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseFocusBorderColor() {
        NCLColor aux = getFocusBorderColor();
        if(aux != null)
            return " focusBorderColor='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseFocusBorderWidth() {
        Integer aux = getFocusBorderWidth();
        if(aux != null)
            return " focusBorderWidth='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseFocusBorderTransparency() {
        PercentageType aux = getFocusBorderTransparency();
        if(aux != null)
            return " focusBorderTransparency='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseFocusSrc() {
        SrcType aux = getFocusSrc();
        if(aux != null)
            return " focusSrc='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseFocusSelSrc() {
        SrcType aux = getFocusSelSrc();
        if(aux != null)
            return " focusSelSrc='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected String parseSelBorderColor() {
        NCLColor aux = getSelBorderColor();
        if(aux != null)
            return " SelBorderColor='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseTransIn() {
        Et aux = getTransIn();
        if(aux != null)
            return " transIn='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseTransOut() {
        Et aux = getTransOut();
        if(aux != null)
            return " transOut='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected String parseDescriptorParams(int ident) {
        if(!hasDescriptorParam())
            return "";
        
        String content = "";
        for(Ep aux : params)
            content += aux.parse(ident);
        
        return content;
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var, ch_name;
        NodeList nl;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the player (optional)
            att_name = NCLElementAttributes.PLAYER.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setPlayer(att_var);

            // set the explicitDur (optional)
            att_name = NCLElementAttributes.EXPLICITDUR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setExplicitDur(new TimeType(att_var));

            // set the freeze (optional)
            att_name = NCLElementAttributes.FREEZE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setFreeze(Boolean.parseBoolean(att_var));

            // set the moveUp (optional)
            att_name = NCLElementAttributes.MOVEUP.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Integer aux;
                try{
                    aux = new Integer(att_var);
                }catch(Exception e){
                    throw new NCLParsingException("Could not create integer from value: " + att_var + ".");
                }
                T desc = (T) new NCLDescriptor("aux" + att_var);
                desc.setFocusIndex(aux);
                setMoveUp(desc);
                NCLReferenceManager.getInstance().waitReference(this);
            }

            // set the moveRight (optional)
            att_name = NCLElementAttributes.MOVERIGHT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Integer aux;
                try{
                    aux = new Integer(att_var);
                }catch(Exception e){
                    throw new NCLParsingException("Could not create integer from value: " + att_var + ".");
                }
                T desc = (T) new NCLDescriptor("aux" + att_var);
                desc.setFocusIndex(aux);
                setMoveRight(desc);
                NCLReferenceManager.getInstance().waitReference(this);
            }

            // set the moveLeft (optional)
            att_name = NCLElementAttributes.MOVELEFT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Integer aux;
                try{
                    aux = new Integer(att_var);
                }catch(Exception e){
                    throw new NCLParsingException("Could not create integer from value: " + att_var + ".");
                }
                T desc = (T) new NCLDescriptor("aux" + att_var);
                desc.setFocusIndex(aux);
                setMoveLeft(desc);
                NCLReferenceManager.getInstance().waitReference(this);
            }

            // set the moveDown (optional)
            att_name = NCLElementAttributes.MOVEDOWN.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Integer aux;
                try{
                    aux = new Integer(att_var);
                }catch(Exception e){
                    throw new NCLParsingException("Could not create integer from value: " + att_var + ".");
                }
                T desc = (T) new NCLDescriptor("aux" + att_var);
                desc.setFocusIndex(aux);
                setMoveDown(desc);
                NCLReferenceManager.getInstance().waitReference(this);
            }

            // set the focusIndex (optional)
            att_name = NCLElementAttributes.FOCUSINDEX.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setFocusIndex(new Integer(att_var));
                }catch(Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the focusBorderColor (optional)
            att_name = NCLElementAttributes.FOCUSBORDERCOLOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setFocusBorderColor(NCLColor.getEnumType(att_var));

            // set the focusBorderWidth (optional)
            att_name = NCLElementAttributes.FOCUSBORDERWIDTH.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                try{
                    setFocusBorderWidth(new Integer(att_var));
                }catch(Exception e){
                    throw new NCLParsingException("Could not set " + att_name + " value: " + att_var + ".");
                }
            }

            // set the focusBorderTransparency (optional)
            att_name = NCLElementAttributes.FOCUSBORDERTRANSPARENCY.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setFocusBorderTransparency(new PercentageType(att_var));

            // set the focusSrc (optional)
            att_name = NCLElementAttributes.FOCUSSRC.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setFocusSrc(new SrcType(att_var));

            // set the focusSelSrc (optional)
            att_name = NCLElementAttributes.FOCUSSELSRC.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setFocusSelSrc(new SrcType(att_var));

            // set the selBorderColor (optional)
            att_name = NCLElementAttributes.SELBORDERCOLOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setSelBorderColor(NCLColor.getEnumType(att_var));

            // set the transIn (optional)
            att_name = NCLElementAttributes.TRANSIN.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Et tran = (Et) NCLReferenceManager.getInstance().findTransitionReference(impl.getDoc(), att_var);
                setTransIn(tran);
            }

            // set the transOut (optional)
            att_name = NCLElementAttributes.TRANSOUT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Et tran = (Et) NCLReferenceManager.getInstance().findTransitionReference(impl.getDoc(), att_var);
                setTransOut(tran);
            }

            // set the region (optional)
            att_name = NCLElementAttributes.REGION.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Er reg = (Er) NCLReferenceManager.getInstance().findRegionReference(impl.getDoc(), att_var);
                setRegion(reg);
            }
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
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Descriptor" + aux + " > " + ex.getMessage());
        }
    }
    
    
    public El findDescriptor(String id) throws XMLException {
        if(getId().equals(id))
            return (El) this;
        else
            return null;
    }
    
    
    public El findDescriptor(Integer focusIndex) throws XMLException {
        if(this.focusIndex != null && this.focusIndex.intValue() == focusIndex.intValue())
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
                return createDoubleDescriptorParam();
            case LEFT:
                return createDoubleDescriptorParam();
            case BOTTOM:
                return createDoubleDescriptorParam();
            case RIGHT:
                return createDoubleDescriptorParam();
            case WIDTH:
                return createDoubleDescriptorParam();
            case HEIGHT:
                return createDoubleDescriptorParam();
            case LOCATION:
                return createStringDescriptorParam();
            case SIZE:
                return createStringDescriptorParam();
            case BOUNDS:
                return createStringDescriptorParam();
            case BACKGROUND:
                return createColorDescriptorParam();
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
                return createDoubleDescriptorParam();
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
        Integer aux;
        
        try{
            // set the moveUp (optional)
            if((aux = getMoveUp().getFocusIndex()) != null){
                T desc = (T) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), aux);
                setMoveUp(desc);
            }

            // set the moveRight (optional)
            if((aux = getMoveRight().getFocusIndex()) != null){
                T desc = (T) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), aux);
                setMoveRight(desc);
            }

            // set the moveLeft (optional)
            if((aux = getMoveLeft().getFocusIndex()) != null){
                T desc = (T) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), aux);
                setMoveLeft(desc);
            }

            // set the moveDown (optional)
            if((aux = getMoveDown().getFocusIndex()) != null){
                T desc = (T) NCLReferenceManager.getInstance().findDescriptorReference(impl.getDoc(), aux);
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
}
