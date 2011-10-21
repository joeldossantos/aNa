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
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLAssessmentStatementPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLAssessmentStatement<T extends NCLAssessmentStatement, P extends NCLElement, I extends NCLElementImpl, Ea extends NCLAttributeAssessment, Ev extends NCLValueAssessment, Er extends NCLRole>
        extends NCLAssessmentStatementPrototype<T, P, I, Ea, Ev> implements NCLStatement<T, P, Er> {


    public NCLAssessmentStatement() throws XMLException {
        super();
    }
    
    
    public NCLAssessmentStatement(Element element) throws XMLException {
        super();
        load(element);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setComparator(NCLComparator comparator) {
        NCLComparator aux = this.comparator;
        super.setComparator(comparator);
        impl.notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }
    
    
    @Override
    public void setValueAssessment(Ev value) {
        Ev aux = this.valueAssessment;
        super.setValueAssessment(value);
        impl.notifyAltered(NCLElementAttributes.VALUEASSESSMENT, aux, value);
    }
    
        
    @Override
    public boolean addAttributeAssessment(Ea attribute) throws XMLException {
        if(super.addAttributeAssessment(attribute)){
            impl.notifyInserted(NCLElementSets.ATTRIBUTEASSESSMENTS, attribute);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean removeAttributeAssessment(Ea attribute) throws XMLException {
        if(super.removeAttributeAssessment(attribute)){
            impl.notifyRemoved(NCLElementSets.ATTRIBUTEASSESSMENTS, attribute);
            return true;
        }
        return false;
    }


    public void load(Element element) throws XMLException {
        String att_name, att_var, ch_name;
        NodeList nl;

        // set the comparator (required)
        att_name = NCLElementAttributes.COMPARATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setComparator(NCLComparator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // create the child nodes
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                //create the valueAssessment
                if(el.getTagName().equals(NCLElementAttributes.VALUEASSESSMENT.toString()))
                    setValueAssessment(createValueAssessment(el));
                // create the connectors
                if(el.getTagName().equals(NCLElementAttributes.ATTRIBUTEASSESSMENT.toString()))
                    addAttributeAssessment(createAttributeAssessment(el));
            }
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
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
    protected Ea createAttributeAssessment(Element element) throws XMLException {
        return (Ea) new NCLAttributeAssessment(element);
    }


    /**
     * Function to create the child element <i>valueAssessment</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>valueAssessment</i>.
     */
    protected Ev createValueAssessment(Element element) throws XMLException {
        return (Ev) new NCLValueAssessment(element);
    }
}
