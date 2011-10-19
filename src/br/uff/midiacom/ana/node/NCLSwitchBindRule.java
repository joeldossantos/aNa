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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.node.NCLSwitchBindRulePrototype;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


public class NCLSwitchBindRule<T extends NCLSwitchBindRule, P extends NCLElement, I extends NCLElementImpl, En extends NCLNode, Er extends NCLTestRule>
        extends NCLSwitchBindRulePrototype<T, P, I, En, Er> implements NCLElement<T, P> {


    public NCLSwitchBindRule() throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
    }

    public NCLSwitchBindRule(Element elem) throws XMLException {
        super();
        impl = (I) new NCLElementImpl(this);
        load(elem);
    }

    
    @Override
    public void setConstituent(En constituent) {
        En aux = this.constituent;
        super.setConstituent(constituent);
        impl.notifyAltered(NCLElementAttributes.CONSTITUENT, aux, constituent);
    }


    @Override
    public void setRule(Er rule) {
        Er aux = this.rule;
        super.setRule(rule);
        impl.notifyAltered(NCLElementAttributes.RULE, aux, rule);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("rule"))
//                    setRule((R) new NCLRule(attributes.getValue(i)));//cast retirado na correcao das referencias
//                else if(attributes.getLocalName(i).equals("constituent"))
//                    setConstituent((N) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
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
//        if(getConstituent() != null)
//            constituentReference();
//
//        if(getRule() != null)
//            ruleReference();
//    }


//    private void constituentReference() {
//        //Search for a component node in its parent
//        Set<N> nodes = ((NCLSwitch) getParent()).getNodes();
//
//        for(N node : nodes){
//            if(node.getId().equals(getConstituent().getId())){
//                setConstituent(node);
//                return;
//            }
//        }
//
//        addWarning("Could not find node in switch with id: " + getConstituent().getId());
//    }


//    private void ruleReference() {
//        //Search for the interface inside the node
//        Set<R> rules = getRules();
//        if(rules == null)
//            return;
//
//        for(R rul : rules){
//            if(rul.getId().equals(getRule().getId())){
//                setRule(rul);
//                return;
//            }
//        }
//        //@todo: regras internas a regras compostas podem ser utilizadas?
//
//        addWarning("Could not find rule in ruleBase with id: " + getRule().getId());
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        att_name = NCLElementAttributes.CONSTITUENT.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setConstituent(); // metodo de busca pelo id

        att_name = NCLElementAttributes.RULE.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setRule(); // metodo de busca pelo id
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
