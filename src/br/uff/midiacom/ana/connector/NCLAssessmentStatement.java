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
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLAssessmentStatementPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLAssessmentStatement<T extends NCLAssessmentStatement,
                                    P extends NCLElement,
                                    I extends NCLElementImpl,
                                    Ea extends NCLAttributeAssessment,
                                    Ev extends NCLValueAssessment,
                                    Es extends NCLStatement,
                                    Er extends NCLRole>
        extends NCLAssessmentStatementPrototype<T, P, I, Ea, Ev, Es>
        implements NCLStatement<Es, P, Er> {


    public NCLAssessmentStatement() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<assessmentStatement";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);
        
        content += space + "</assessmentStatement>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseComparator();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseAttributeAssessments(ident);
        content += parseValueAssessment(ident);
        
        return content;
    }
    
    
    protected String parseComparator() {
        NCLComparator aux = getComparator();
        if(aux != null)
            return " comparator='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseAttributeAssessments(int ident) {
        if(!hasAttributeAssessment())
            return "";
        
        String content = "";
        for(Ea aux : attributeAssessments)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseValueAssessment(int ident) {
        Ev aux = getValueAssessment();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        try{
            // set the comparator (required)
            att_name = NCLElementAttributes.COMPARATOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setComparator(NCLComparator.getEnumType(att_var));
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");
        }
        catch(XMLException ex){
            throw new NCLParsingException("AssessmentStatement:\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    //create the valueAssessment
                    if(el.getTagName().equals(NCLElementAttributes.VALUEASSESSMENT.toString())){
                        Ev inst = createValueAssessment();
                        setValueAssessment(inst);
                        inst.load(el);
                    }
                    // create the connectors
                    if(el.getTagName().equals(NCLElementAttributes.ATTRIBUTEASSESSMENT.toString())){
                        Ea inst = createAttributeAssessment();
                        addAttributeAssessment(inst);
                        inst.load(el);
                    }
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("AssessmentStatement > " + ex.getMessage());
        }
    }
    
    
    public Er findRole(String name) {
        Er result;
        
        for(Ea attribute : attributeAssessments){
            result = (Er) attribute.findRole(name);
            if(result != null)
                return result;
        }
        
        return null;
    }


    /**
     * Function to create the child element <i>attributeAssessment</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>attributeAssessment</i>.
     */
    protected Ea createAttributeAssessment() throws XMLException {
        return (Ea) new NCLAttributeAssessment();
    }


    /**
     * Function to create the child element <i>valueAssessment</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>valueAssessment</i>.
     */
    protected Ev createValueAssessment() throws XMLException {
        return (Ev) new NCLValueAssessment();
    }
}
