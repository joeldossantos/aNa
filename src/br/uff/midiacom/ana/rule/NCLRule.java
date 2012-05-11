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
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.aux.reference.VariableReference;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLVariable;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRulePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import org.w3c.dom.Element;


public class NCLRule<T extends NCLTestRule,
                     P extends NCLElement,
                     I extends NCLElementImpl,
                     Ep extends VariableReference>
        extends NCLRulePrototype<T, P, I, Ep>
        implements NCLTestRule<T, P> {


    public NCLRule() throws XMLException {
        super();
    }

    
    public NCLRule(String id) throws XMLException {
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


        // param element and attributes declaration
        content = space + "<rule";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);
            loadVar(element);
            loadComparator(element);
            loadValue(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Rule" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseVar();
        content += parseComparator();
        content += parseValue();
        
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
    
    
    protected String parseVar() {
        Ep aux = getVar();
        if(aux != null)
            return " var='" + aux.parse() + "'";
        else
            return "";
    }
    
    
    protected void loadVar(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the var (required)
        att_name = NCLElementAttributes.VAR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            setVar(findVariableReference(att_var));
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseComparator() {
        NCLComparator aux = getComparator();
        if(aux != null)
            return " comparator='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadComparator(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the comparator (required)
        att_name = NCLElementAttributes.COMPARATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setComparator(NCLComparator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseValue() {
        String aux = getValue();
        if(aux != null)
            return " value='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadValue(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the value (required)
        att_name = NCLElementAttributes.VALUE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setValue(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    public T findRule(String id) throws XMLException {
        if(getId().equals(id))
            return (T) this;
        else
            return null;
    }
    
    
    public Ep findVariableReference(String name) throws XMLException {
        NCLDoc doc = impl.getDoc();
        
        if(doc == null)
            throw new NCLParsingException("Could not find document doc element");

        for(NCLVariable docVar : (ElementList<NCLVariable, NCLDoc>) doc.getGlobalVariables()){
            if(docVar.getName().equals(name))
                return createVariableRef(docVar);
        }
        
        NCLVariable newVar = new NCLVariable(name);
        return createVariableRef(newVar);
    }


    /**
     * Function to create a reference to a interface.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a interface.
     */
    protected Ep createVariableRef(NCLVariable ref) throws XMLException {
        return (Ep) new VariableReference(ref);
    }
}
