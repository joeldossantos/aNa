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
package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.descriptor.*;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.reference.ReferredElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLBindConstituent;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import org.w3c.dom.Element;


/**
 * Class that represents the bindRule element used inside a switch or descriptor
 * switch element.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>constituent</i> - switch element component to be related to a rule.
 *                           This attribute is required.</li>
 *  <li><i>rule</i> - rule tested when selecting the descriptor to be used. This
 *                    attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <En>
 * @param <Er> 
 */
public class NCLBindRule<T extends NCLElement,
                         Ec extends NCLBindConstituent,
                         Er extends NCLTestRule,
                         R extends ExternalReferenceType>
        extends NCLElementPrototype<T>
        implements NCLElement<T> {

    protected Ec constituent;
    protected Object rule;


    /**
     * BindRule element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLBindRule() {
        super();
    }


    /**
     * Sets the switch element component to be related to a rule. This attribute
     * is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The referred component must be a node internal to the switch element
     * parent of this bindRule element.
     * 
     * @param constituent
     *          element that makes reference to the component that will be
     *          related to a rule.
     * @throws XMLException 
     *          if the constituent is null or any error occur while creating the
     *          reference to the descriptor.
     */
    public void setConstituent(Ec constituent) throws XMLException {
        if(constituent == null)
            throw new XMLException("Null constituent.");
        
        Ec aux = this.constituent;
        
        this.constituent = constituent;
        ((ReferredElement) this.constituent).addReference(this);
        
        notifyAltered(NCLElementAttributes.CONSTITUENT, aux, constituent);
        if(aux != null)
            aux.removeReference(this);
    }


    /**
     * Returns the switch element component descriptor to be related to a rule or
     * <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The referred descriptor must be a node internal to the switch element parent
     * of this bindRule element.
     * 
     * @return 
     *          element that makes reference to the descriptor that will be
     *          related to a rule or <i>null</i> if the attribute is not defined.
     */
    public Ec getConstituent() {
        return constituent;
    }


    /**
     * Sets the rule tested when presenting the node indicated in the constituent
     * attribute. This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The rule referred can be defined in the document base of rules or in a
     * base defined in an external document, imported by the base of rules or
     * by the base of imported documents. When the rule is defined in an external
     * document, the alias of the imported document must be indicated in the
     * reference.
     * 
     * @param rule
     *          element representing a reference to a rule element.
     * @throws XMLException 
     *          if the rule is null or any error occur while creating the
     *          reference to the rule.
     *          
     */
    public void setRule(Object rule) throws XMLException {
        if(rule == null)
            throw new XMLException("Null rule.");
        
        Object aux = this.rule;
        
        if(rule instanceof NCLTestRule)
            ((Er) rule).addReference(this);
        else if(rule instanceof ExternalReferenceType){
            ((R) rule).getTarget().addReference(this);
            ((R) rule).getAlias().addReference(this);
        }
        
        this.rule = rule;
        notifyAltered(NCLElementAttributes.RULE, aux, rule);
        
        if(aux != null){
            if(aux instanceof NCLTestRule)
                ((Er) aux).removeReference(this);
            else{
                ((R) aux).getTarget().removeReference(this);
                ((R) aux).getAlias().removeReference(this);
            }
        }
    }


    /**
     * Returns the rule tested when presenting the node indicated in the
     * constituent attribute or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The rule referred can be defined in the document base of rules or in a
     * base defined in an external document, imported by the base of rules or
     * by the base of imported documents. When the rule is defined in an external
     * document, the alias of the imported document must be indicated in the
     * reference.
     * 
     * @return 
     *          element representing a reference to a rule element or <i>null</i>
     *          if the attribute is not defined.
     */
    public Object getRule() {
        return rule;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLBindRule))
            return false;
        
        boolean result = true;
        
        T el;
        if((el = (T) getConstituent()) != null)
            result &= el.compare(((NCLBindRule) other).getConstituent());
        
        Object aux = getRule();
        Object oaux = ((NCLBindRule) other).getRule();
        if(aux != null && oaux != null){
            if(aux instanceof NCLTestRule && oaux instanceof NCLTestRule)
                result &= ((Er) aux).compare((Er) oaux);
            else if(aux instanceof ExternalReferenceType && oaux instanceof ExternalReferenceType)
                result &= ((R) aux).equals((R) oaux);
            else
                result = false;
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

        content = space + "<bindRule";
        content += parseAttributes();
        content += "/>\n";


        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadConstituent(element);
            loadRule(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("BindRule:\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseConstituent();
        content += parseRule();
        
        return content;
    }
    
    
    protected String parseRule() {
        Object aux = getRule();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLTestRule)
            return " rule='" + ((Er) aux).getId() + "'";
        else
            return " rule='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadRule(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the rule (required)
        att_name = NCLElementAttributes.RULE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] rul = adjustReference(att_var);
            setRule(d.getHead().findRule(rul[0], rul[1]));
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseConstituent() {
        Ec aux = getConstituent();
        if(aux != null)
            return " constituent='" + aux.getId() + "'";
        else
            return "";
    }
    
    
    protected void loadConstituent(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the constituint (required)
        att_name = NCLElementAttributes.CONSTITUENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            T aux;
            if((aux = (T) getParent()) == null)
                throw new NCLParsingException("Could not find element " + att_var);

            Ec comp = null;
            if(aux instanceof NCLDescriptor)
                comp = (Ec) ((NCLDescriptorSwitch) aux).getDescriptors().get(att_var);
            else if(aux instanceof NCLNode)
                comp = (Ec) ((NCLNode) aux).findNode(att_var);
            
            if(comp == null)
                throw new NCLParsingException("Could not find element " + att_var);

            setConstituent(comp);
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }

    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        constituent.removeReference(this);
        
        if(rule instanceof NCLTestRule)
            ((Er) rule).removeReference(this);
        else if(rule instanceof ExternalReferenceType){
            ((R) rule).getTarget().removeReference(this);
            ((R) rule).getAlias().removeReference(this);
        }
        
        constituent = null;
        rule = null;
    }
}
