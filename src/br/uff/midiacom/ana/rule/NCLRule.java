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
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLComparator;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import java.util.ArrayList;
import org.w3c.dom.Element;


/**
 * Class that represents a simple rule. A rule represents a test of the value of
 * a variable. This variable can be a system variable or defined by the document.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the simple rule element. This attribute is required.</li>
 *  <li><i>var</i> - variable tested by the rule. The variable must be a property
 *                   of media with type settings. This attribute is required.</li>
 *  <li><i>comparator</i> - relates the variable to the value. This attribute is
 *                          required.</li>
 *  <li><i>value</i> - value to be tested by the rule. This attribute is required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ep> 
 */
public class NCLRule<T extends NCLElement,
                     Ev extends NCLVariable,
                     Eb extends NCLBindRule,
                     Et extends NCLTestRule>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLTestRule<T, Eb, Et> {

    protected Ev var;
    protected NCLComparator comparator;
    protected String value;
    
    protected ArrayList<Eb> references;


    /**
     * Simple rule constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLRule() {
        super();
        references = new ArrayList<Eb>();
    }
    
    
    public NCLRule(String id) throws XMLException {
        super();
        references = new ArrayList<Eb>();
        setId(id);
    }


    /**
     * Sets the variable to be tested by the rule. This attribute is required
     * and can not be set to <i>null</i>. This method receives an element that
     * makes reference to that variable.
     * 
     * <br/>
     * 
     * The variable used by the rule is a global variable in the list defined
     * by the document. The variable tested by the rule must be indicated by a
     * property element child of a media element with type <i>settings</i>.
     * 
     * <br/>
     * 
     * Since a rule can be defined in a document without a body, the variable
     * remains without a reference from a property element until the document
     * that uses the rule defines a media node of type <i>settings</i>, whose
     * property indicates this variable.
     *
     * @param var
     *          element that makes reference to the variable to be tested.
     * @throws XMLException 
     *          if the variable is null or any error occur while creating the
     *          reference to the variable.
     */
    public void setVar(Ev var) throws XMLException {
        if(var == null)
            throw new XMLException("Null variable");
        
        Ev aux = this.var;
        
        this.var = var;
        this.var.addReference((T) this);
        
        notifyAltered(NCLElementAttributes.VAR, aux, var);
        if(aux != null)
            aux.removeReference(this);
    }


    /**
     * Returns the variable to be tested by the rule or <i>null</i> if the
     * attribute is not defined.
     * 
     * <br/>
     * 
     * The variable tested by the rule must be indicated by a property element
     * child of a media element with type <i>settings</i>. This method receives
     * an element to make reference to that property.
     *
     * @return
     *          element that makes reference to the variable to be tested.
     */
    public Ev getVar() {
        return var;
    }


    /**
     * Sets the relation between the variable to the value. This attribute is
     * required and can not be set to <i>null</i>. The possible comparators to
     * be used are defined in the enumeration <i>NCLComparator</i>.
     *
     * @param comparator
     *          relation between the variable to the value from the enumeration
     *          <i>NCLComparator</i>.
     * @throws XMLException 
     *          if the value representing the comparator is null.
     */
    public void setComparator(NCLComparator comparator) throws XMLException {
        if(comparator == null)
            throw new XMLException("Null comparator.");
        
        NCLComparator aux = this.comparator;
        this.comparator = comparator;
        notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }


    /**
     * Returns the relation between the variable to the value or <i>null</i> if
     * the attribute is not defined. The possible comparators to be used are
     * defined in the enumeration <i>NCLComparator</i>.
     * 
     * @return
     *          elation between the variable to the value from the enumeration
     *          <i>NCLComparator</i> or <i>null</i> if the comparator is not defined.
     */
    public NCLComparator getComparator() {
        return comparator;
    }


    /**
     * Sets the value to be tested by the rule. This attribute is required and
     * can not be set to <i>null</i>.
     *
     * @param value
     *          string representing the value to be tested by the rule.
     * @throws XMLException 
     *          if the string representing the value is null or empty.
     */
    public void setValue(String value) throws XMLException {
        if(value == null)
            throw new XMLException("Null value.");
        
        String aux = this.value;
        this.value = value;
        notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }


    /**
     * Returns the value to be tested by the rule or <i>null</i> if no value is
     * defined.
     * 
     * @return
     *         string representing the value to be tested by the rule or
     *          <i>null</i> if no value is defined.
     */
    public String getValue() {
        return value;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLRule))
            return false;
        
        boolean result = true;
        
        Object aux;
        if((aux = getId()) != null)
            result &= aux.equals(((NCLRule) other).getId());
        if((aux = getComparator()) != null)
            result &= aux.equals(((NCLRule) other).getComparator());
        if((aux = getValue()) != null)
            result &= aux.equals(((NCLRule) other).getValue());
        
        Ev el;
        if((el = getVar()) != null)
            result &= el.compare(((NCLRule) other).getVar());
        
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


        // param element and attributes declaration
        content = space + "<rule";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    @Override
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
        Ev aux = getVar();
        if(aux != null)
            return " var='" + aux.parse(0) + "'";
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
    
    
    @Override
    public Et findRule(String id) throws XMLException {
        if(getId().equals(id))
            return (Et) this;
        else
            return null;
    }
    
    
    public Ev findVariableReference(String name) throws XMLException {
        NCLDoc doc = (NCLDoc) getDoc();
        
        if(doc == null)
            throw new NCLParsingException("Could not find document doc element");

        Ev result;
        
        result = (Ev) doc.getGlobalVariable(name);
        if (result != null)
            return result;
        
        result = createVariableRef(name);
        doc.addGlobalVariable(result);
        return result;
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(Eb reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(Eb reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }
    
    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        var.removeReference(this);
        
        var = null;
        comparator = null;
        value = null;
    }
    
    
    /**
     * Function to create a reference to a interface.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to a interface.
     */
    protected Ev createVariableRef(String name) throws XMLException {
        return (Ev) new NCLVariable(name);
    }
}
