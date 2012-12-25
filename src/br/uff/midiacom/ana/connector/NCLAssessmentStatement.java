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
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLComparator;
import br.uff.midiacom.ana.util.enums.NCLDefaultValueAssessment;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import java.util.Iterator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a assessment statement element. This element is used
 * to compares to values. The values can be an attribute of a node or event or
 * a value.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>comparator</i> - indicates the comparison made. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>attributeAssessment</i> - element that represents the attribute whose
 *                                   value will be compared. The assessment
 *                                   statement can have one or two attribute
 *                                   assessment elements.</li>
 *  <li><i>valueAssessment</i> - element representing value to be compared. The
 *                               assessment statement can have none or one value
 *                               assessment element.</li>
 * </ul>
 * 
 * Note that the assessment statement will compare either two attributes or an
 * attribute with a value.
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ea>
 * @param <Ev>
 * @param <Es> 
 */
public class NCLAssessmentStatement<T extends NCLElement,
                                    Ea extends NCLAttributeAssessment,
                                    Ep extends NCLConnectorParam,
                                    Es extends NCLStatement,
                                    Er extends NCLRoleElement>
        extends ParamElement<T>
        implements NCLStatement<T, Er> {

    protected NCLComparator comparator;
    protected Object valueAssessment;
    protected ElementList<Ea> attributeAssessments;


    /**
     * Assessment statement element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLAssessmentStatement() {
        super();
        attributeAssessments = new ElementList<Ea>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ea aux : attributeAssessments) {
            aux.setDoc(doc);
        }
    }


    /**
     * Sets the type of comparison to be made. This attribute is required an can
     * not be set to <i>null</i>. The possible comparison values are defined in
     * the enumeration <i>NCLComparator</i>.
     * 
     * @param comparator
     *          element representing the type of comparison from the enumeration
     *          <i>NCLComparator</i>.
     * @throws XMLException 
     *          if the element is null.
     */
    public void setComparator(NCLComparator comparator) throws XMLException {
        if(comparator == null)
            throw new XMLException("Null comparison.");
        
        NCLComparator aux = this.comparator;
        this.comparator = comparator;
        notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }
    
    
    /**
     * Returns the type of comparison to be made or <i>null</i> if the attribute
     * is not defined. The possible comparison values are defined in the
     * enumeration <i>NCLComparator</i>.
     * 
     * @return
     *          element representing the type of comparison from the enumeration
     *          <i>NCLComparator</i> or <i>null</i> if the attribute is not
     *          defined.
     */
    public NCLComparator getComparator() {
        return comparator;
    }
    
    
    /**
     * Sets the element representing value to be compared. The assessment
     * statement can have none or one value assessment element. Set the value
     * assessment to <i>null</i> to erase a value assessment already defined.
     * 
     * @param value
     *          string, element from the enumeration <i>NCLDefaultValueAssessment</i>
     *          or connector parameter representing a value assessment or <i>null</i>
     *          to erase a value already defined.
     */
    public void setValueAssessment(Object valueAssessment) throws XMLException {
        Object aux = this.valueAssessment;
        
        if(valueAssessment == null){
            this.valueAssessment = valueAssessment;
            notifyAltered(NCLElementAttributes.VALUEASSESSMENT, aux, valueAssessment);
            
            if(aux != null && aux instanceof NCLCausalConnector)
                ((Ep) aux).removeReference(this);
            return;
        }
        
        if(valueAssessment instanceof String){
            String value = (String) valueAssessment;
            if("".equals(value.trim()))
                throw new XMLException("Empty valueAssessment String");
            
            if(!value.contains("$")){
                this.valueAssessment = NCLDefaultValueAssessment.getEnumType(value);
                if(this.valueAssessment == null)
                    this.valueAssessment = valueAssessment;
            }
            else{
                this.valueAssessment = findConnectorParam(value.substring(1));
                ((Ep) this.valueAssessment).addReference(this);
            }
        }
        else if(valueAssessment instanceof NCLDefaultValueAssessment)
            this.valueAssessment = valueAssessment;
        else if(valueAssessment instanceof NCLConnectorParam){
            this.valueAssessment = valueAssessment;
            ((Ep) this.valueAssessment).addReference(this);
        }
        else
            throw new XMLException("Wrong repeat type.");
        
        if(aux != null && aux instanceof NCLCausalConnector)
                ((Ep) aux).removeReference(this);
        
        notifyAltered(NCLElementAttributes.VALUEASSESSMENT, aux, valueAssessment);
    }
    
    
    /**
     * Returns the element representing value to be compared or <i>null</i> if
     * the value is not defined. The assessment statement can have none or one
     * value assessment element.
     * 
     * @return
     *          element representing a value assessment or <i>null</i> if the
     *          value is not defined.
     */
    public Object getValueAssessment() {
        return valueAssessment;
    }
    
    
    /**
     * Adds an element that represents the attribute whose value will be compared.
     * The assessment statement can have one or two attribute assessment elements.
     * 
     * @param attribute
     *          element representing an attribute assessment.
     * @return
     *          true if the element representing an attribute assessment was added.
     * @throws XMLException 
     *          if the element representing the attribute assessment is null or
     *          the assessment statement already have two attribute assessments.
     */
    public boolean addAttributeAssessment(Ea attribute) throws XMLException {
        if(valueAssessment != null && attributeAssessments.size() == 1)
            throw new XMLException("can't have more than one attribute");
        if(attributeAssessments.size() == 2)
            throw new XMLException("can't have more than two attributes");
        
        if(attributeAssessments.add(attribute)){
            notifyInserted((T) attribute);
            attribute.setParent(this);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes an element that represents the attribute whose value will be compared.
     * The assessment statement can have one or two attribute assessment elements.
     * 
     * @param attribute
     *          element representing an attribute assessment.
     * @return
     *          true if the element representing an attribute assessment was removed.
     * @throws XMLException 
     *          if the element representing the attribute assessment is null.
     */
    public boolean removeAttributeAssessment(Ea attribute) throws XMLException {
        if(!attribute.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(attributeAssessments.remove(attribute)){
            notifyRemoved((T) attribute);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the assessment statement has a specific element that represents
     * the attribute whose value will be compared. The assessment statement can
     * have one or two attribute assessment elements.
     * 
     * @param attribute
     *          element representing an attribute assessment.
     * @return
     *          true if the assessment statement has the element representing an
     *          attribute assessment.
     * @throws XMLException 
     *          if the element representing the attribute assessment is null.
     */
    public boolean hasAttributeAssessment(Ea attribute) throws XMLException {
        return attributeAssessments.contains(attribute);
    }
    
    
    /**
     * Verifies if the assessment statement has at least one element that represents
     * the attribute whose value will be compared. The assessment statement can
     * have one or two attribute assessment elements.
     * 
     * @return
     *          true if the assessment statement has at least one attribute
     *          assessment.
     */
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }


    /**
     * Returns the list of attribute assessments that an assessment statement have.
     * The assessment statement can have one or two attribute assessment elements.
     * 
     * @return 
     *          element list with all attribute assessments.
     */
    public ElementList<Ea> getAttributeAssessments() {
        return attributeAssessments;
    }
    
    
    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLAssessmentStatement))
            return false;
        
        boolean comp = true;

        String this_stat, other_stat;
        NCLAssessmentStatement other_asses = (NCLAssessmentStatement) other;
        
        // Compara pelo comparador
        if(getComparator() == null) this_stat = ""; else this_stat = getComparator().toString();
        if(other_asses.getComparator() == null) other_stat = ""; else other_stat = other_asses.getComparator().toString();
        comp &= this_stat.equals(other_stat);

        // Compara o número de attributeAssessment
        comp &= attributeAssessments.size() == other_asses.getAttributeAssessments().size();

        // Compara os attributeAssessment
        Iterator it = other_asses.getAttributeAssessments().iterator();
        for(NCLAttributeAssessment att : attributeAssessments){
            if(!it.hasNext())
                continue;
            NCLAttributeAssessment other_att = (NCLAttributeAssessment) it.next();
            comp &= att.compare(other_att);
            if(comp)
                break;
        }

        // Compara os valueAssessment
        if(getValueAssessment() != null && other_asses.getValueAssessment() != null)
            comp &= getValueAssessment().equals(other_asses.getValueAssessment());//@todo


        return comp;
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

        content = space + "<assessmentStatement";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);
        
        content += space + "</assessmentStatement>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadComparator(element);
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

                    loadValueAssessment(el);
                    loadAttributeAssessments(el);
                }
            }
        }
        catch(XMLException ex){
            throw new NCLParsingException("AssessmentStatement > " + ex.getMessage());
        }
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
    
    
    protected void loadComparator(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the comparator (required)
        att_name = NCLElementAttributes.COMPARATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setComparator(NCLComparator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseAttributeAssessments(int ident) {
        if(!hasAttributeAssessment())
            return "";
        
        String content = "";
        for(Ea aux : attributeAssessments)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadAttributeAssessments(Element element) throws XMLException {
        // create the attribute assessments
        if(element.getTagName().equals(NCLElementAttributes.ATTRIBUTEASSESSMENT.toString())){
            Ea inst = createAttributeAssessment();
            addAttributeAssessment(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseValueAssessment(int ident) {
        Object aux = getValueAssessment();
        if(aux == null)
            return "";
        
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<valueAssessment";
        if(aux instanceof NCLConnectorParam)
            content += " value='$" + ((Ep) aux).getName() + "'";
        else
            content += " value='" + aux.toString() + "'";
        content += "/>\n";

        return content;
    }
    
    
    protected void loadValueAssessment(Element element) throws XMLException {
        //create the valueAssessment
        if(element.getTagName().equals(NCLElementAttributes.VALUEASSESSMENT.toString())){
            String att_name, att_var;

            try{
                // set the value (required)
                att_name = NCLElementAttributes.VALUE.toString();
                if(!(att_var = element.getAttribute(att_name)).isEmpty())
                    setValueAssessment(att_var);
                else
                    throw new NCLParsingException("Could not find " + att_name + " attribute.");
            }
            catch(XMLException ex){
                throw new NCLParsingException("ValueAssessment:\n" + ex.getMessage());
            }
        }
    }
    
    
    @Override
    public Er findRole(String name) {
        Er result;
        
        for(Ea attribute : attributeAssessments){
            result = (Er) attribute.findRole(name);
            if(result != null)
                return result;
        }
        
        return null;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(valueAssessment != null && valueAssessment instanceof NCLConnectorParam)
            ((Ep)valueAssessment).removeReference(this);
        
        comparator = null;
        valueAssessment = null;
        
        for(Ea a : attributeAssessments)
            a.clean();
        
//        protected NCLComparator comparator;
//        protected Object valueAssessment;
//        protected ElementList<Ea> attributeAssessments;
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
}
