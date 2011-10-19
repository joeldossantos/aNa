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
package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLImportType;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRuleBasePrototype;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLRuleBase<T extends NCLRuleBase, P extends NCLElement, I extends NCLElementImpl, Et extends NCLTestRule, Ei extends NCLImport>
        extends NCLRuleBasePrototype<T, P, I, Et, Ei> implements NCLIdentifiableElement<T, P> {


    public NCLRuleBase() throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public boolean addRule(Et rule) throws XMLException {
        if(super.addRule(rule)){
            impl.notifyInserted(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRule(Et rule) throws XMLException {
        if(super.removeRule(rule)){
            impl.notifyRemoved(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRule(String id) throws XMLException {
        if(super.removeRule(id)){
            impl.notifyRemoved(NCLElementSets.RULES, id);
            return true;
        }
        return false;
    }


    @Override
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(super.addImportBase(importBase)){
            impl.notifyInserted(NCLElementSets.IMPORTEDDOCUMENTBASE, importBase);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(super.removeImportBase(importBase)){
            impl.notifyRemoved(NCLElementSets.IMPORTEDDOCUMENTBASE, importBase);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("ruleBase")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("importBase")){
//                I child = createImportBase();
//                child.startElement(uri, localName, qName, attributes);
//                addImportBase(child);
//            }
//            else if(localName.equals("rule")){
//                T child = createRule();
//                child.startElement(uri, localName, qName, attributes);
//                addRule(child);
//            }
//            else if(localName.equals("compositeRule")){
//                T child = createCompositeRule();
//                child.startElement(uri, localName, qName, attributes);
//                addRule(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(hasImportBase()){
//            for(I imp : imports){
//                imp.endDocument();
//                addWarning(imp.getWarnings());
//                addError(imp.getErrors());
//            }
//        }
//        if(hasRule()){
//            for(T rule : rules){
//                rule.endDocument();
//                addWarning(rule.getWarnings());
//                addError(rule.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException {
        String ch_name;
        int length;

        ch_name = NCLElementAttributes.RULE.toString();
        NodeList nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();

        for(int i=0; i<length; i++)
            addRule((Et) (Element) nl.item(i));

        ch_name = NCLElementAttributes.IMPORTEDDOCUMENTBASE.toString();
        nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();

        for(int i=0; i<length; i++){
            Element elem_child = (Element) nl.item(i);
            NCLImport imp = new NCLImport(NCLImportType.BASE);
            addImportBase((Ei) imp);
            imp.load(elem_child);
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>importBase</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>importBase</i>.
     */
    protected NCLImport createImportBase() throws XMLException {
        return new NCLImport(NCLImportType.BASE);
    }


    /**
     * Function to create the child element <i>rule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>rule</i>.
     */
    protected NCLRule createRule(String id) throws XMLException {
        return new NCLRule(id);
    }


    /**
     * Function to create the child element <i>compositeRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compositeRule</i>.
     */
    protected NCLCompositeRule createCompositeRule(String id) throws XMLException {
        return new NCLCompositeRule(id);
    }
}
