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
import br.uff.midiacom.ana.util.reference.ReferredElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLNamedElementPrototype;
import java.util.ArrayList;
import org.w3c.dom.Element;


/**
 * Class that represents a connector parameter element. This element is used
 * to define a parameterized value to be used in a connector.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the parameter. This attribute is required.</li>
 *  <li><i>type</i> - type of the parameter. This attribute is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public class NCLConnectorParam<T extends NCLElement,
                               Ep extends NCLElement>
        extends NCLNamedElementPrototype<T, String>
        implements NCLElement<T>, ReferredElement<Ep> {
    
    protected String type;
    
    protected ArrayList<Ep> references;
    
    
    /**
     * Parameter element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLConnectorParam() {
        super();
        references = new ArrayList<Ep>();
    }
    
    
    public NCLConnectorParam(String name) throws XMLException {
        super();
        references = new ArrayList<Ep>();
        setName(name);
    }
    
    
    /**
     * Sets the name of the parameter. This attribute is required and can not be
     * set to <i>null</i>.
     * 
     * <br/>
     * 
     * The name of the parameter must be unique inside the connector.
     * 
     * @param name
     *          string representing the name of the parameter.
     * @throws XMLException 
     *          if the name is null or empty.
     */
    @Override
    public void setName(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        String aux = this.getName();
        this.name = name;
        notifyAltered(NCLElementAttributes.NAME, aux, name);
    }
    
    
    /**
     * Returns the name of the parameter or <i>null</i> if the attribute is not
     * defined.
     * 
     * <br/>
     * 
     * The name of the parameter must be unique inside the connector.
     * 
     * @return
     *          string representing the name of the parameter or <i>null</i> if
     *          the attribute is not defined.
     */
    @Override
    public String getName() {
        return super.getName();
    }
    
    
    /**
     * Sets the type of the parameter. This attribute is optional. Set the type
     * to <i>null</i> to erase a type already defined.
     * 
     * @param type
     *          string representing the type of the parameter or <i>null</i> to
     *          erase a type already defined.
     * @throws XMLException 
     *          if the string is empty.
     */
    public void setType(String type) throws XMLException {
        String aux = this.type;
        this.type = type;
        notifyAltered(NCLElementAttributes.TYPE, aux, type);
    }


    /**
     * Returns the type of the parameter or <i>null</i> if the attribute is not
     * defined.
     * 
     * @return
     *          string representing the type of the parameter or <i>null</i> if
     *          the attribute is not defined.
     */
    public String getType() {
        return type;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLConnectorParam))
            return false;
        
        boolean result = true;
        String aux;
        
        if((aux = getName()) != null)
            result &= aux.equals(((NCLConnectorParam) other).getName());
        if((aux = getType()) != null)
            result &= aux.equals(((NCLConnectorParam) other).getType());
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

        content = space + "<connectorParam";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadName(element);
            loadType(element);
        }
        catch(XMLException ex){
            String aux = getName();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("ConnectorParam" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseName();
        content += parseType();
        
        return content;
    }
    
    
    protected String parseName() {
        String aux = getName();
        if(aux != null)
            return " name='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadName(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the name (required)
        att_name = NCLElementAttributes.NAME.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setName(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseType() {
        String aux = getType();
        if(aux != null)
            return " type='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadType(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the type (optional)
        att_name = NCLElementAttributes.TYPE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setType(att_var);
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(Ep reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(Ep reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }

    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        type = null;
        name = null;
    }
}
