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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLCompositeRulePrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLCompositeRule<T extends NCLTestRule, P extends NCLElement, I extends NCLElementImpl>
        extends NCLCompositeRulePrototype<T, P, I> implements NCLTestRule<T, P> {


    public NCLCompositeRule(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }


    @Override
    public void setOperator(NCLOperator operator) {
        NCLOperator aux = this.operator;
        super.setOperator(operator);
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }


    @Override
    public boolean addRule(T rule) throws XMLException {
        if(super.addRule(rule)){
            impl.notifyInserted(NCLElementSets.RULES, rule);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeRule(T rule) throws XMLException {
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


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("compositeRule") && !insideRule){
//                cleanWarnings();
//                cleanErrors();
//                insideRule = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("operator")){
//                        for(NCLOperator op : NCLOperator.values()){
//                        if(op.toString().equals(attributes.getValue(i)))
//                            setOperator(op);
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("compositeRule") && insideRule){
//                // compositeRule e um elemento interno
//                T child = createCompositeRule();
//                child.startElement(uri, localName, qName, attributes);
//                addRule(child);
//            }
//            else if(localName.equals("rule")){
//                T child = createRule();
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
//        if(hasRule()){
//            for(T rule : rules){
//                rule.endDocument();
//                addWarning(rule.getWarnings());
//                addError(rule.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var, ch_name;
        int length;

        att_name = NCLElementAttributes.ID.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setId(att_var);

        att_name = NCLElementAttributes.OPERATOR.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setOperator(NCLOperator.getEnumType(att_var));

        ch_name = NCLElementAttributes.RULE.toString();
        NodeList nl = element.getElementsByTagName(ch_name);
        length = nl.getLength();
        for(int i=0; i<length; i++)
            addRule((T) new NCLRule((Element) nl.item(i)));
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
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
}
