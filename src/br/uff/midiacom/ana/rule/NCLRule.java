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

import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRulePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLRule<T extends NCLTestRule, P extends NCLElement, I extends NCLElementImpl, Ep extends NCLProperty>
        extends NCLRulePrototype<T, P, I, Ep> implements NCLTestRule<T, P> {


    public NCLRule(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }

    public NCLRule(Element elem) throws XMLException {
        super(elem.getAttribute(NCLElementAttributes.ID.toString()));
        impl = (I) new NCLElementImpl(this);
        load(elem);
    }

    @Override
    public void setVar(Ep var) {
        NCLElement aux = (NCLElement) this.var;
        super.setVar(var);
        impl.notifyAltered(NCLElementAttributes.VAR, aux, var);
    }


    @Override
    public void setComparator(NCLComparator comparator) {
        NCLComparator aux = this.comparator;
        super.setComparator(comparator);
        impl.notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }


    @Override
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }


//    private void propertyReference() {
        //Search for the interface inside the node
//        NCLElementImpl doc = getParent();
//
//        while(!(doc instanceof NCLDoc)){
//            doc = doc.getParent();
//            if(doc == null){
//                addWarning("Could not find a root element");
//                return;
//            }
//        }
//
//        if(((NCLDoc) doc).getBody() == null){
//            addWarning("Could not find a body");
//        }
//
//        setVar(findProperty(((NCLDoc) doc).getBody().getNodes()));
//    }


//    private P findProperty(Set<NCLNode> nodes) {
//        for(NCLNode n : nodes){
//            if(n instanceof NCLMedia){
//                if( ((NCLMedia) n).hasProperty()){
//                    Set<P> properties = ((NCLMedia) n).getProperties();
//                    for(P prop : properties){
//                        if(prop.getName().equals(getVar().getName()))
//                            return prop;
//                    }
//                }
//            }
//            else if(n instanceof NCLContext){
//                if( ((NCLContext) n).hasNode()){
//                    Set<NCLNode> cnodes = ((NCLContext) n).getNodes();
//                    NCLProperty p = findProperty(cnodes);
//                    if(p != null)
//                        return (P) p;
//                }
//            }
//            else if(n instanceof NCLSwitch){
//                if( ((NCLSwitch) n).hasNode()){
//                    Set<NCLNode> snodes = ((NCLSwitch) n).getNodes();
//                    NCLProperty p = findProperty(snodes);
//                    if(p != null)
//                        return (P) p;
//                }
//            }
//        }
//
//        addWarning("Could not find property with name: " + getVar().getName());
//        return null;
//    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("id"))
//                    setId(attributes.getValue(i));
//                else if(attributes.getLocalName(i).equals("var"))
//                    setVar((P) new NCLProperty(attributes.getValue(i)));//cast retirado na correcao das referencias
//                else if(attributes.getLocalName(i).equals("comparator")){
//                    for(NCLComparator c : NCLComparator.values()){
//                        if(c.toString().equals(attributes.getValue(i)))
//                            setComparator(c);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("value"))
//                    setValue(attributes.getValue(i));
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(getParent() == null)
//            return;
//
//        if(getVar() != null)
//            propertyReference();
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        att_name = NCLElementAttributes.VAR.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setVar(); // tem que usar o método de busca pelo id da media

        att_name = NCLElementAttributes.COMPARATOR.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setComparator(NCLComparator.getEnumType(att_var));

        att_name = NCLElementAttributes.VALUE.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setValue(att_var);
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
