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
import br.uff.midiacom.ana.datatype.auxiliar.SrcType;
import br.uff.midiacom.ana.datatype.auxiliar.TimeType;
import br.uff.midiacom.ana.datatype.enums.NCLColor;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorPrototype;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.number.PercentageType;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLDescriptor<T extends NCLDescriptor, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegion, El extends NCLLayoutDescriptor, Et extends NCLTransition, Ep extends NCLDescriptorParam>
        extends NCLDescriptorPrototype<T, P, I, Er, El, Et, Ep> implements NCLLayoutDescriptor<El, P> {


    public NCLDescriptor(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
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


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("descriptor")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("region"))
//                        setRegion((R) new NCLRegion(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("explicitDur")){
//                        String value = attributes.getValue(i);
//                        if(value.contains("s"))
//                            value = value.substring(0, value.length() - 1);
//                        setExplicitDur(new Integer(value));
//                    }
//                    else if(attributes.getLocalName(i).equals("freeze"))
//                        setFreeze(Boolean.valueOf(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("player"))
//                        setPlayer(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("moveLeft")){
//                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
//                        d.setFocusIndex(new Integer(attributes.getValue(i)));
//                        setMoveLeft((D) d);
//                    }
//                    else if(attributes.getLocalName(i).equals("moveRight")){
//                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
//                        d.setFocusIndex(new Integer(attributes.getValue(i)));
//                        setMoveRight((D) d);
//                    }
//                    else if(attributes.getLocalName(i).equals("moveDown")){
//                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
//                        d.setFocusIndex(new Integer(attributes.getValue(i)));
//                        setMoveDown((D) d);
//                    }
//                    else if(attributes.getLocalName(i).equals("moveUp")){
//                        NCLDescriptor d = new NCLDescriptor("_" + attributes.getValue(i));//cast retirado na correcao das referencias
//                        d.setFocusIndex(new Integer(attributes.getValue(i)));
//                        setMoveUp((D) d);
//                    }
//                    else if(attributes.getLocalName(i).equals("focusIndex"))
//                        setFocusIndex(new Integer(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("focusBorderColor")){
//                        for(NCLColor c : NCLColor.values()){
//                            if(c.toString().equals(attributes.getValue(i)))
//                                setFocusBorderColor(c);
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("focusBorderWidth"))
//                        setFocusBorderWidth(new Integer(attributes.getValue(i)));
//                    else if(attributes.getLocalName(i).equals("focusBorderTransparency")){
//                        String value = attributes.getValue(i);
//                        if(value.contains("%"))
//                            value = value.substring(0, value.length() - 1);
//                        setFocusBorderTransparency(new Integer(value));
//                    }
//                    else if(attributes.getLocalName(i).equals("focusSrc"))
//                        setFocusSrc(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("focusSelSrc"))
//                        setFocusSelSrc(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("SelBorderColor")){
//                        for(NCLColor c : NCLColor.values()){
//                            if(c.toString().equals(attributes.getValue(i)))
//                                setSelBorderColor(c);
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("transIn"))
//                        setTransIn((T) new NCLTransition(attributes.getValue(i)));//cast retirado na correcao das referencias
//                    else if(attributes.getLocalName(i).equals("transOut"))
//                        setTransOut((T) new NCLTransition(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("descriptorParam")){
//                P child = createParamByType(attributes);
//                //@todo verificar o nome para definir o tipo do parâmetro
//                child.startElement(uri, localName, qName, attributes);
//                addDescriptorParam(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//        catch(URISyntaxException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getRegion() != null)
//                regionReference();
//
//            if(getMoveUp() != null || getMoveDown() != null || getMoveLeft() != null || getMoveRight() != null)
//                descriptorReference();
//
//            if(getTransIn() != null)
//                setTransIn(transitionReference(getTransIn()));
//
//            if(getTransOut() != null)
//                setTransOut(transitionReference(getTransOut()));
//        }
//
//        if(hasDescriptorParam()){
//            for(P param : params){
//                param.endDocument();
//                addWarning(param.getWarnings());
//                addError(param.getErrors());
//            }
//        }
//    }


//    private P createParamByType(Attributes attributes) {
//        NCLAttributes att = NCLAttributes.DEFAULT;
//        for(int i = 0; i < attributes.getLength(); i++){
//            if(attributes.getLocalName(i).equals("name")){
//                for(NCLAttributes a : NCLAttributes.values()){
//                    if(a.toString().equals(attributes.getValue(i)))
//                        att = a;
//                }
//            }
//        }
//
//        switch(att){
//            case TOP:
//                return createDoubleDescriptorParam();
//            case LEFT:
//                return createDoubleDescriptorParam();
//            case BOTTOM:
//                return createDoubleDescriptorParam();
//            case RIGHT:
//                return createDoubleDescriptorParam();
//            case WIDTH:
//                return createDoubleDescriptorParam();
//            case HEIGHT:
//                return createDoubleDescriptorParam();
//            case LOCATION:
//                return createStringDescriptorParam();
//            case SIZE:
//                return createStringDescriptorParam();
//            case BOUNDS:
//                return createStringDescriptorParam();
//            case BACKGROUND:
//                return createColorDescriptorParam();
//            case VISIBLE:
//                return createBooleanDescriptorParam();
//            case TRANSPARENCY:
//                return createPercentDescriptorParam();
//            case FIT:
//                return createFitDescriptorParam();
//            case SCROLL:
//                return createScrollDescriptorParam();
//            case STYLE:
//                return createStringDescriptorParam();
//            case SOUND_LEVEL:
//                return createPercentDescriptorParam();
//            case BALANCE_LEVEL:
//                return createPercentDescriptorParam();
//            case TREBLE_LEVEL:
//                return createPercentDescriptorParam();
//            case BASS_LEVEL:
//                return createPercentDescriptorParam();
//            case ZINDEX:
//                return createDoubleDescriptorParam();
//            case FONT_FAMILY:
//                return createStringDescriptorParam();
//            case FONT_SIZE:
//                return createDoubleDescriptorParam();
//            case FONT_VARIANT:
//                return createFontVariantDescriptorParam();
//            case FONT_WEIGHT:
//                return createFontWeightDescriptorParam();
//            case FONT_COLOR:
//                return createColorDescriptorParam();
//            case REUSE_PLAYER:
//                return createBooleanDescriptorParam();
//            case PLAYER_LIFE:
//                return createPlayerLifeDescriptorParam();
//            default:
//                return createStringDescriptorParam();
//        }
//    }


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


    public void load(Element element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
    

//    /**
//     * Função de criação do elemento filho <i>descriptorParam</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>descriptorParam</i>.
//     */
//    protected P createStringDescriptorParam() {
//        return (P) new NCLStringDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createDoubleDescriptorParam() {
//        return (P) new NCLDoubleDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createBooleanDescriptorParam() {
//        return (P) new NCLBooleanDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createColorDescriptorParam() {
//        return (P) new NCLColorDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createPercentDescriptorParam() {
//        return (P) new NCLPercentDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createFitDescriptorParam() {
//        return (P) new NCLFitDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createScrollDescriptorParam() {
//        return (P) new NCLScrollDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createFontVariantDescriptorParam() {
//        return (P) new NCLFontVariantDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createFontWeightDescriptorParam() {
//        return (P) new NCLFontWeightDescriptorParam(getReader(), this);
//    }
//
//
//    protected P createPlayerLifeDescriptorParam() {
//        return (P) new NCLPlayerLifeDescriptorParam(getReader(), this);
//    }
}
