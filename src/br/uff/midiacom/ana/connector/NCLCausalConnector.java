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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCausalConnectorPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLCausalConnector<T extends NCLCausalConnector,
                                P extends NCLElement,
                                I extends NCLElementImpl,
                                Ec extends NCLCondition,
                                Ea extends NCLAction,
                                Ep extends NCLConnectorParam,
                                Er extends NCLRole>
        extends NCLCausalConnectorPrototype<T, P, I, Ec, Ea, Ep>
        implements NCLIdentifiableElement<T, P> {


    public NCLCausalConnector() throws XMLException {
        super();
    }
    
    
    public NCLCausalConnector(String id) throws XMLException {
        super();
        setId(id);
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

        content = space + "<causalConnector";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</causalConnector>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadId(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("CausalConnector" + aux + ":\n" + ex.getMessage());
        }

        try{
            loadConnectorParams(element);

            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadSimpleConditions(el);
                    loadCompoundConditions(el);
                    loadSimpleActions(el);
                    loadCompoundActions(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("CausalConnector" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseConnectorParams(ident);
        content += parseCondition(ident);
        content += parseAction(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadId(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the id (required)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseConnectorParams(int ident) {
        if(!hasConnectorParam())
            return "";
        
        String content = "";
        for(Ep aux : conn_params)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadConnectorParams(Element element) throws XMLException {
        String ch_name;
        NodeList nl;
        
        // create the connectorParam nodes
        ch_name = NCLElementAttributes.CONNECTORPARAM.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            Ep inst = createConnectorParam();
            addConnectorParam(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseCondition(int ident) {
        Ec aux = getCondition();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadSimpleConditions(Element element) throws XMLException {
        //create the simpleCondition
        if(element.getTagName().equals(NCLElementAttributes.SIMPLECONDITION.toString())){
            Ec inst = createSimpleCondition();
            setCondition(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundConditions(Element element) throws XMLException {
        // create the compoundCondition
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDCONDITION.toString())){
            Ec inst = createCompoundCondition();
            setCondition(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseAction(int ident) {
        Ea aux = getAction();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadSimpleActions(Element element) throws XMLException {
        //create the simpleAction
        if(element.getTagName().equals(NCLElementAttributes.SIMPLEACTION.toString())){
            Ea inst = createSimpleAction();
            setAction(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadCompoundActions(Element element) throws XMLException {
        // create the compoundAction
        if(element.getTagName().equals(NCLElementAttributes.COMPOUNDACTION.toString())){
            Ea inst = createCompoundAction();
            setAction(inst);
            inst.load(element);
        }
    }
    
    
    /**
     * Searches for a role inside the connector.
     * 
     * @param name
     *          name of the role to be found.
     * @return 
     *          role or null if no role was found.
     */
    public Er findRole(String name) {
        Er result;
        
        result = (Er) getCondition().findRole(name);
        if(result != null)
            return result;
        
        result = (Er) getAction().findRole(name);
        if(result != null)
            return result;
        
        return null;
    }


    /**
     * Function to create the child element <i>connectorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>connectorParam</i>.
     */
    protected Ep createConnectorParam() throws XMLException {
        return (Ep) new NCLConnectorParam();
    }


    /**
     * Function to create the child element <i>simpleCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleCondition</i>.
     */
    protected Ec createSimpleCondition() throws XMLException {
        return (Ec) new NCLSimpleCondition();
    }


    /**
     * Function to create the child element <i>compoundCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundCondition</i>.
     */
    protected Ec createCompoundCondition() throws XMLException {
        return (Ec) new NCLCompoundCondition();
    }


    /**
     * Function to create the child element <i>simpleAction</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleAction</i>.
     */
    protected Ea createSimpleAction() throws XMLException {
        return (Ea) new NCLSimpleAction();
    }


    /**
     * Function to create the child element <i>compoundAction</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundAction</i>.
     */
    protected Ea createCompoundAction() throws XMLException {
        return (Ea) new NCLCompoundAction();
    }
}
