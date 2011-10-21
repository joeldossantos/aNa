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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
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
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLDescriptor<T extends NCLDescriptor, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegion, El extends NCLLayoutDescriptor, Et extends NCLTransition, Ep extends NCLDescriptorParam>
        extends NCLDescriptorPrototype<T, P, I, Er, El, Et, Ep> implements NCLLayoutDescriptor<El, P> {


    public NCLDescriptor(String id) throws XMLException {
        super(id);
    }
    
    
    public NCLDescriptor(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setPlayer(String player) throws XMLException {
        StringType aux = this.player;
        super.setPlayer(player);
        impl.notifyAltered(NCLElementAttributes.PLAYER, aux, player);
    }


    @Override
    public void setExplicitDur(TimeType explicitDur) {
        TimeType aux = this.explicitDur;
        super.setExplicitDur(explicitDur);
        impl.notifyAltered(NCLElementAttributes.EXPLICITDUR, aux, explicitDur);
    }


    @Override
    public void setFreeze(Boolean freeze) {
        Boolean aux = this.freeze;
        super.setFreeze(freeze);
        impl.notifyAltered(NCLElementAttributes.FREEZE, aux, freeze);
    }

    
    @Override
    public void setMoveLeft(T descriptor) {
        T aux = this.moveLeft;
        super.setMoveLeft(descriptor);
        impl.notifyAltered(NCLElementAttributes.MOVELEFT, aux, descriptor);
    }


    @Override
    public void setMoveRight(T descriptor) {
        T aux = this.moveRight;
        super.setMoveRight(descriptor);
        impl.notifyAltered(NCLElementAttributes.MOVERIGHT, aux, descriptor);
    }


    @Override
    public void setMoveUp(T descriptor) {
        T aux = this.moveUp;
        super.setMoveUp(descriptor);
        impl.notifyAltered(NCLElementAttributes.MOVEUP, aux, descriptor);
    }


    @Override
    public void setMoveDown(T descriptor) {
        T aux = this.moveDown;
        super.setMoveDown(descriptor);
        impl.notifyAltered(NCLElementAttributes.MOVEDOWN, aux, descriptor);
    }

        
    @Override
    public void setFocusIndex(Integer focusIndex) {
        Integer aux = this.focusIndex;
        super.setFocusIndex(focusIndex);
        impl.notifyAltered(NCLElementAttributes.FOCUSINDEX, aux, focusIndex);
    }


    @Override
    public void setFocusBorderColor(NCLColor focusBorderColor) {
        NCLColor aux = this.focusBorderColor;
        super.setFocusBorderColor(focusBorderColor);
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERCOLOR, aux, focusBorderColor);
    }

    
    @Override
    public void setFocusBorderWidth(Integer focusBorderWidth) {
        Integer aux = this.focusBorderWidth;
        super.setFocusBorderWidth(focusBorderWidth);
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERWIDTH, aux, focusBorderWidth);
    }


    @Override
    public void setFocusBorderTransparency(PercentageType focusBorderTransparency) {
        PercentageType aux = this.focusBorderTransparency;
        super.setFocusBorderTransparency(focusBorderTransparency);
        impl.notifyAltered(NCLElementAttributes.FOCUSBORDERTRANSPARENCY, aux, focusBorderTransparency);
    }


    @Override
    public void setFocusSrc(SrcType focusSrc) {
        SrcType aux = this.focusSrc;
        super.setFocusSrc(focusSrc);
        impl.notifyAltered(NCLElementAttributes.FOCUSSRC, aux, focusSrc);
    }


    @Override
    public void setFocusSelSrc(SrcType focusSelSrc) {
        SrcType aux = this.focusSelSrc;
        super.setFocusSelSrc(focusSelSrc);
        impl.notifyAltered(NCLElementAttributes.FOCUSSELSRC, aux, focusSelSrc);
    }


    @Override
    public void setSelBorderColor(NCLColor selBorderColor) {
        NCLColor aux = this.selBorderColor;
        super.setSelBorderColor(selBorderColor);
        impl.notifyAltered(NCLElementAttributes.BORDERCOLOR, aux, selBorderColor);
    }


    @Override
    public void setTransIn(Et transIn) {
        Et aux = this.transIn;
        super.setTransIn(transIn);
        impl.notifyAltered(NCLElementAttributes.TRANSIN, aux, transIn);
    }


    @Override
    public void setTransOut(Et transOut) {
        Et aux = this.transOut;
        super.setTransOut(transOut);
        impl.notifyAltered(NCLElementAttributes.TRANSOUT, aux, transOut);
    }


    @Override
    public void setRegion(Er region) {
        Er aux = this.region;
        super.setRegion(region);
        impl.notifyAltered(NCLElementAttributes.REGION, aux, region);
    }


    @Override
    public boolean addDescriptorParam(Ep descriptorParam) throws XMLException {
        if(super.addDescriptorParam(descriptorParam)){
            impl.notifyInserted(NCLElementSets.DESCRIPTORPARAM, descriptorParam);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeDescriptorParam(Ep descriptorParam) throws XMLException {
        if(super.removeDescriptorParam(descriptorParam)){
            impl.notifyRemoved(NCLElementSets.DESCRIPTORPARAM, descriptorParam);
            return true;
        }
        return false;
    }


//    private void regionReference() {
//        //Search for the interface inside the node
//        NCLElementImpl head = getParent();
//
//        while(!(head instanceof NCLHead)){
//            head = head.getParent();
//            if(head == null){
//                addWarning("Could not find a head");
//                return;
//            }
//        }
//
//        if(!((NCLHead) head).hasRegionBase()){
//            addWarning("Could not find a regionBase");
//        }
//
//        R reg = null;
//        for(NCLRegionBase base : (Set<NCLRegionBase>) ((NCLHead) head).getRegionBases()){
//            reg = findRegion(base.getRegions());
//        }
//        if(reg == null)
//            addWarning("Could not find region in regionBase with id: " + getRegion().getId());
//
//        setRegion(reg);
//    }


//    private R findRegion(Set<R> regions) {
//        for(R reg : regions){
//            if(reg.getId().equals(getRegion().getId()))
//                return (R) reg;
//            else if(reg.hasRegion())
//            {
//                R r = findRegion(reg.getRegions());
//                if(r != null)
//                    return (R) r;
//            }
//        }
//
//        return null;
//    }


//    private void descriptorReference() {
//        //Search for the interface inside the node
//        NCLElementImpl head = getParent();
//
//        while(!(head instanceof NCLHead)){
//            head = head.getParent();
//            if(head == null){
//                addWarning("Could not find a head");
//                return;
//            }
//        }
//
//        if(((NCLHead) head).getDescriptorBase() == null){
//            addWarning("Could not find a descriptorBase");
//            return;
//        }
//        if(getMoveUp() != null)
//            setMoveUp(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveUp()));
//        if(getMoveDown() != null)
//            setMoveDown(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveDown()));
//        if(getMoveLeft() != null)
//            setMoveLeft(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveLeft()));
//        if(getMoveRight() != null)
//            setMoveRight(findDescriptor(((NCLHead) head).getDescriptorBase().getDescriptors(), getMoveRight()));
//    }


//    private D findDescriptor(Set<NCLLayoutDescriptor> descriptors, D move) {
//        for(NCLLayoutDescriptor descriptor : descriptors){
//            if(descriptor instanceof NCLDescriptorSwitch){
//                NCLDescriptor desc = findDescriptor(((NCLDescriptorSwitch)descriptor).getDescriptors(), move);
//                if(desc != null)
//                    return (D) desc;
//            }
//            else{
//                int indexa, indexb;
//                if(((NCLDescriptor) descriptor).getFocusIndex() != null) indexa = ((NCLDescriptor) descriptor).getFocusIndex(); else indexa = 0;
//                if(move.getFocusIndex() != null) indexb = move.getFocusIndex(); else indexb = 0;
//                if(indexa == indexb)
//                    return (D) descriptor;
//            }
//        }
//
//        addWarning("Could not find descriptor in descriptorBase with focusIndex: " + move.getFocusIndex());
//        return null;
//    }


//    private T transitionReference(T transition) {
//        //Search for the interface inside the node
//        NCLElementImpl head = getParent();
//
//        while(!(head instanceof NCLHead)){
//            head = head.getParent();
//            if(head == null){
//                addWarning("Could not find a head");
//                return null;
//            }
//        }
//
//        if(((NCLHead) head).getTransitionBase() == null){
//            addWarning("Could not find a transitionBase");
//            return null;
//        }
//
//        Set<T> transitions = ((NCLHead) head).getTransitionBase().getTransitions();
//        for(T trans : transitions){
//            if(trans.getId().equals(transition.getId()))
//             return (T) trans;
//        }
//
//        addWarning("Could not find transition in transitionBase with id: " + transition.getId());
//        return null;
//    }


    public void load(Element element) throws XMLException, NCLParsingException{
        String att_name, att_var, ch_name;
        NodeList nl;

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
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setMoveUp(); //@todo: metodo de busca pelo id do descritor

        // set the moveRight (optional)
        att_name = NCLElementAttributes.MOVERIGHT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setMoveRight(); //@todo: metodo de busca pelo id do descritor

        // set the moveLeft (optional)
        att_name = NCLElementAttributes.MOVELEFT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setMoveLeft(); //@todo: metodo de busca pelo id do descritor

        // set the moveDown (optional)
        att_name = NCLElementAttributes.MOVEDOWN.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setMoveDown(); //@todo: metodo de busca pelo id do descritor

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
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setTransIn(); //@todo: metodo de procura pelo id

        // set the transOut (optional)
        att_name = NCLElementAttributes.TRANSOUT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setTransOut(); //@todo: metodo de procura pelo id

        // set the region (optional)
        att_name = NCLElementAttributes.REGION.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setRegion(); //@todo: metodo de procura pelo id


        // create the descriptor child nodes
        ch_name = NCLElementAttributes.DESCRIPTORPARAM.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            addDescriptorParam(createParamByType(el));
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
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
                return createDoubleDescriptorParam(element);
            case LEFT:
                return createDoubleDescriptorParam(element);
            case BOTTOM:
                return createDoubleDescriptorParam(element);
            case RIGHT:
                return createDoubleDescriptorParam(element);
            case WIDTH:
                return createDoubleDescriptorParam(element);
            case HEIGHT:
                return createDoubleDescriptorParam(element);
            case LOCATION:
                return createStringDescriptorParam(element);
            case SIZE:
                return createStringDescriptorParam(element);
            case BOUNDS:
                return createStringDescriptorParam(element);
            case BACKGROUND:
                return createColorDescriptorParam(element);
            case VISIBLE:
                return createBooleanDescriptorParam(element);
            case TRANSPARENCY:
                return createPercentDescriptorParam(element);
            case FIT:
                return createFitDescriptorParam(element);
            case SCROLL:
                return createScrollDescriptorParam(element);
            case STYLE:
                return createStringDescriptorParam(element);
            case SOUND_LEVEL:
                return createPercentDescriptorParam(element);
            case BALANCE_LEVEL:
                return createPercentDescriptorParam(element);
            case TREBLE_LEVEL:
                return createPercentDescriptorParam(element);
            case BASS_LEVEL:
                return createPercentDescriptorParam(element);
            case ZINDEX:
                return createDoubleDescriptorParam(element);
            case FONT_FAMILY:
                return createStringDescriptorParam(element);
            case FONT_SIZE:
                return createDoubleDescriptorParam(element);
            case FONT_VARIANT:
                return createFontVariantDescriptorParam(element);
            case FONT_WEIGHT:
                return createFontWeightDescriptorParam(element);
            case FONT_COLOR:
                return createColorDescriptorParam(element);
            case REUSE_PLAYER:
                return createBooleanDescriptorParam(element);
            case PLAYER_LIFE:
                return createPlayerLifeDescriptorParam(element);
            default:
                return createStringDescriptorParam(element);
        }
    }
    

    /**
     * Function to create the child element <i>descriptorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>descriptorParam</i>.
     */
    protected Ep createStringDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLStringDescriptorParam(element);
    }


    protected Ep createDoubleDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLDoubleDescriptorParam(element);
    }


    protected Ep createBooleanDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLBooleanDescriptorParam(element);
    }


    protected Ep createColorDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLColorDescriptorParam(element);
    }


    protected Ep createPercentDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLPercentDescriptorParam(element);
    }


    protected Ep createFitDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLFitDescriptorParam(element);
    }


    protected Ep createScrollDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLScrollDescriptorParam(element);
    }


    protected Ep createFontVariantDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLFontVariantDescriptorParam(element);
    }


    protected Ep createFontWeightDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLFontWeightDescriptorParam(element);
    }


    protected Ep createPlayerLifeDescriptorParam(Element element) throws XMLException {
        return (Ep) new NCLPlayerLifeDescriptorParam(element);
    }
}
