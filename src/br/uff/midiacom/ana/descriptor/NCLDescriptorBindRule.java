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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.RuleReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElementPrototype;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


/**
 * Class that represents the bindRule element used inside a switch of descriptors.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>constituent</i> - switch element component descriptor to be related
 *                           to a rule. This attribute is required.</li>
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
public class NCLDescriptorBindRule<T extends NCLDescriptorBindRule,
                                   P extends NCLElement,
                                   I extends NCLElementImpl,
                                   El extends DescriptorReference,
                                   Er extends RuleReference>
        extends NCLElementPrototype<T, P, I>
        implements NCLElement<T, P> {

    protected El constituent;
    protected Er rule;


    /**
     * BindRule element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDescriptorBindRule() throws XMLException {
        super();
    }


    /**
     * Sets the switch element component descriptor to be related to a rule. This
     * attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The referred descriptor must be a node internal to the switch element
     * parent of this bindRule element.
     * 
     * @param constituent
     *          element that makes reference to the descriptor that will be
     *          related to a rule.
     * @throws XMLException 
     *          if the constituent is null or any error occur while creating the
     *          reference to the descriptor.
     */
    public void setConstituent(El constituent) throws XMLException {
        if(constituent == null)
            throw new XMLException("Null constituent.");
        
        El aux = this.constituent;
        
        this.constituent = constituent;
        this.constituent.setOwner((T) this);
        this.constituent.setOwnerAtt(NCLElementAttributes.CONSTITUENT);
        
        impl.notifyAltered(NCLElementAttributes.CONSTITUENT, aux, constituent);
        if(aux != null)
            aux.clean();
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
    public El getConstituent() {
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
    public void setRule(Er rule) throws XMLException {
        if(rule == null)
            throw new XMLException("Null rule.");
        
        Er aux = this.rule;
        
        this.rule = rule;
        this.rule.setOwner((T) this);
        this.rule.setOwnerAtt(NCLElementAttributes.RULE);
        
        impl.notifyAltered(NCLElementAttributes.RULE, aux, rule);
        if(aux != null)
            aux.clean();
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
    public Er getRule() {
        return rule;
    }


    public boolean compare(T other) {
        boolean comp = false;

        // Compara pela regra
        if(getRule() != null)
            comp |= ((NCLTestRule) getRule().getTarget()).compare((NCLTestRule) other.getRule().getTarget());

        // Compara pelo constituent
        if(getConstituent() != null)
            comp |= ((NCLLayoutDescriptor) getConstituent().getTarget()).compare((NCLLayoutDescriptor) other.getConstituent().getTarget());

        return comp;
    }


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


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;

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
        Er aux = getRule();
        if(aux != null)
            return " rule='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadRule(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the rule (required)
        att_name = NCLElementAttributes.RULE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLTestRule rul = (NCLTestRule) ((NCLRuleBase) ((NCLHead) impl.getDoc().getHead()).getRuleBase()).findRule(att_var);
            setRule(createRuleRef(rul));
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseConstituent() {
        El aux = getConstituent();
        if(aux != null)
            return " constituent='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadConstituent(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the constituint (required)
        att_name = NCLElementAttributes.CONSTITUENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            P aux;
            if((aux = (P) getParent()) == null)
                throw new NCLParsingException("Could not find element " + att_var);

            NCLLayoutDescriptor desc = (NCLLayoutDescriptor) ((NCLDescriptorSwitch) aux).getDescriptors().get(att_var);
            if(desc == null)
                throw new NCLParsingException("Could not find element " + att_var);

            setConstituent(createDescriptorRef(desc));
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }


    /**
     * Function to create a reference to a descriptor.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a descriptor.
     */
    protected El createDescriptorRef(NCLLayoutDescriptor ref) throws XMLException {
        return (El) new DescriptorReference(ref, NCLElementAttributes.ID);
    }


    /**
     * Function to create a reference to a rule.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a rule.
     */
    protected Er createRuleRef(NCLTestRule ref) throws XMLException {
        return (Er) new RuleReference(ref, NCLElementAttributes.ID);
    }
}
