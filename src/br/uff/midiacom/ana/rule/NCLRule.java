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
import br.uff.midiacom.ana.NCLParsingException;
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
    }

    
    public NCLRule(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
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


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the var (required)
        att_name = NCLElementAttributes.VAR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            ;//setVar(); //@todo: tem que usar o método de busca pelo id da media
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the comparator (required)
        att_name = NCLElementAttributes.COMPARATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setComparator(NCLComparator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the value (required)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
