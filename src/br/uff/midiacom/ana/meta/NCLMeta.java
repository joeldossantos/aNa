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
package br.uff.midiacom.ana.meta;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLNamedElementPrototype;
import org.w3c.dom.Element;


/**
 * Class that represents the meta element. This element is used to describe an
 * NCL document.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>name</i> - name of the metadata property. This attribute is required.</li>
 *  <li><i>content</i> - value of the metadata property. This attribute is
 *                     required.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I> 
 */
public class NCLMeta<T extends NCLElement>
        extends NCLNamedElementPrototype<T, String>
        implements NCLElement<T> {

    protected String mcontent;


    /**
     * Meta element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLMeta() {
        super();
    }


    /**
     * Sets the name of the metadata property. This attribute is required and
     * can not be set to <i>null</i>.
     * 
     * @param name
     *          string representing the name of the metadata property.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    @Override
    public void setName(String name) throws XMLException {
        if(name == null)
            throw new XMLException("Null name.");
        
        this.name = name;
    }


    /**
     * Returns the name of the metadata property or <i>null</i> if the attribute is
     * not defined.
     *
     * @return
     *          string representing the name of the metadata property or null if
     *          the attribute is not defined.
     */
    @Override
    public String getName() {
        return super.getName();
    }


    /**
     * Sets the value of the metadata property. This attribute is required and
     * can not be set to <i>null</i>.
     * 
     * @param content
     *          string representing the name of the metadata property.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public void setContent(String content) throws XMLException {
        if(content == null)
            throw new XMLException("Null content.");
        if("".equals(content.trim()))
            throw new XMLException("Empty content.");
        
        String aux = this.mcontent;
        this.mcontent = content;
        notifyAltered(NCLElementAttributes.CONTENT, aux, content);
    }


    /**
     * Returns the value of the metadata property or <i>null</i> if the attribute
     * is not defined.
     *
     * @return
     *          string representing the value of the metadata property or null
     *          if the attribute is not defined.
     */
    public String getContent() {
        return mcontent;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLMeta))
            return false;
        
        boolean result = true;
        String aux;
        
        if((aux = getName()) != null)
            result &= aux.equals(((NCLMeta) other).getName());
        if((aux = getContent()) != null)
            result &= aux.equals(((NCLMeta) other).getContent());
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
        content = space + "<meta";
        content += parseAttributes();
        content += "/>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadName(element);

            loadContent(element);
        }
        catch(XMLException ex){
            String aux = getName();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Meta" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseName();
        content += parseContent();
        
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
    
    
    protected String parseContent() {
        String aux = getContent();
        if(aux != null)
            return " content='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadContent(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the content (required)
        att_name = NCLElementAttributes.CONTENT.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setContent(att_var);
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }

    @Override
    public void clean() throws XMLException {
        setParent(null);
        mcontent = null;
    }
}
