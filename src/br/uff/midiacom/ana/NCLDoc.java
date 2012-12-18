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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.enums.NCLNamespace;
import br.uff.midiacom.ana.util.ncl.NCLVariable;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * Class that represents the NCL document root element.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the document root element. This attribute is optional.</li>
 *  <li><i>title</i> - additional information about the document. This attribute
 *                     is optional.</li>
 *  <li><i>xmlns</i> - NCL document namespace. It defines the
 *                     possible elements to be used inside the document. This
 *                     attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>head</i> - NCL document head element. This element is optional.</li>
 *  <li><i>body</i> - NCL document body element. This element is optional.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Eh>
 * @param <Eb> 
 */
public class NCLDoc<T extends NCLElement,
                    Eh extends NCLHead,
                    Eb extends NCLBody,
                    Ev extends NCLVariable>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLElement<T> {

    protected String title;
    protected NCLNamespace xmlns;
    protected Eh head;
    protected Eb body;
    
    protected String location;
    protected String fileName;
    protected ElementList<Ev> globalVariables;
    protected ArrayList<PostReferenceElement> references;


    /**
     * NCL document constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLDoc() {
        super();
        globalVariables = new ElementList<Ev>();
        references = new ArrayList<PostReferenceElement>();
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
    }

    
    /**
     * Sets the root element title. The title defines additional information
     * about the document. This attribute is optional. Set the title to
     * <i>null</i> to erase the title already defined.
     * 
     * @param title
     *          string with additional information about the document or
     *          <i>null</i> to erase the title already defined.
     * @throws XMLException 
     *          if the string representing the title is empty.
     */
    public void setTitle(String title) throws XMLException {
        if(title != null && "".equals(title.trim()))
            throw new XMLException("Empty title String");
        
        String aux = this.title;
        this.title = title;
        notifyAltered(NCLElementAttributes.TITLE, aux, title);
    }


    /**
     * Returns the root element title. The title defines additional information
     * about the document or <i>null</i> if no title is defined.
     * 
     * @return
     *          string with additional information about the document or
     *          <i>null</i> if no title is defined.
     */
    public String getTitle() {
        return title;
    }


    /**
     * Sets the namespace to be used by the document. It defines the possible
     * elements to be used inside the document. This attribute is required and
     * can not be set to <i>null</i>. The possible namespaces to be used are
     * defined in the enumeration <i>NCLNamespace</i>.
     * 
     * @param xmlns
     *          document namespace from the enumeration <i>NCLNamespace</i>.
     * @throws XMLException 
     *          if the value representing the namespace is null.
     */
    public void setXmlns(NCLNamespace xmlns) throws XMLException {
        if(xmlns == null)
            throw new XMLException("Null xmlns.");

        NCLNamespace aux = this.xmlns;
        this.xmlns = xmlns;
        notifyAltered(NCLElementAttributes.XMLNS, aux, xmlns);
    }


    /**
     * Returns the namespace to be used by the document or <i>null</i> if the
     * attribute is not defined. It defines the possible elements to be used
     * inside the document. The possible namespaces to be used are defined in
     * the enumeration <i>NCLNamespace</i>.
     * 
     * @return
     *          document namespace from the enumeration <i>NCLNamespace</i> or
     *          <i>null</i> if the namespace is not defined.
     */
    public NCLNamespace getXmlns() {
        return xmlns;
    }


    /**
     * Adds a head element to the document. The head element is optional. Set the
     * head to <i>null</i> to remove the head already defined.
     * 
     * @param head
     *          element representing the document head or <i>null</i> to remove
     *          the head already defined.
     */
    public void setHead(Eh head) throws XMLException {
        // Remove the parent of the actual head, if it exists
        if(this.head != null){
            this.head.setParent(null);
            notifyRemoved((T) this.head);
        }
        // Add the new head element
        this.head = head;
        // Set the parent of the new head, if it exists
        if(this.head != null){
            this.head.setParent(this);
            notifyInserted((T) this.head);
        }
    }


    /**
     * Returns the document head element or <i>null</i> if the document does
     * not have a head.
     * 
     * @return
     *          element representing the document head or <i>null</i> if the
     *          document does not have a head.
     */
    public Eh getHead() {
        return head;
    }


    /**
     * Adds a body element to the document. The body element is optional. Set the
     * body to <i>null</i> to remove the body already defined.
     * 
     * @param body
     *          element representing the document body or <i>null</i> to remove
     *          the body already defined.
     */
    public void setBody(Eb body) throws XMLException {
        // Remove the parent of the actual body, if it exists
        if(this.body != null){
            this.body.setParent(null);
            notifyRemoved((T) this.body);
        }
        // Add the new body element
        this.body = body;
        // Set the parent of the new body, if it exists
        if(this.body != null){
            this.body.setParent(this);
            notifyInserted((T) this.body);
        }
    }


    /**
     * Returns the document body element or <i>null</i> if the document does
     * not have a body.
     * 
     * @return
     *          element representing the document body or <i>null</i> if the
     *          document does not have a body.
     */
    public Eb getBody() {
        return body;
    }
    
    
    /**
     * Returns the location of the document file. This location is set while
     * parsing a document from a file.
     * 
     * @return 
     *          string representing the document location.
     */
    public String getLocation() {
        return location;
    }
    
    
    /**
     * Returns the name of the document file. This name is set while parsing a
     * document from a file.
     * 
     * @return 
     *          string representing the document name.
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * Adds an element representing a global variable to the document. The global
     * variables are referred by rule and property elements.
     *
     * @param variable
     *          element representing a global variable.
     * @return
     *          true if the variable was added.
     * @throws XMLException 
     *          if the element representing the variable is null.
     */
    public boolean addGlobalVariable(Ev variable) throws XMLException {
        boolean result = globalVariables.add(variable);
        if(result)
            variable.setDoc(this);
        return result;
    }


    /**
     * Removes an element representing a global variable of the document. The
     * global variables are referred by rule and property elements.
     *
     * @param variable
     *          element representing a global variable.
     * @return
     *          true if the variable was removed.
     * @throws XMLException 
     *          if the element representing the variable is null.
     */
    public boolean removeGlobalVariable(Ev variable) throws XMLException {
        return globalVariables.remove(variable);
    }


    /**
     * Removes an element representing a global variable of the document. The
     * global variables are referred by rule and property elements.
     *
     * @param name
     *          string representing the name of the global variable.
     * @return
     *          true if the variable was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeGlobalVariable(String name) throws XMLException {
        for(Ev aux : globalVariables){
            if(aux.parse(0).equals(name))
                return globalVariables.remove(aux);
        }
        
        return false;
    }


    /**
     * Verifies if the document has a specific global variable. The global
     * variables are referred by rule and property elements.
     *
     * @param variable
     *          element representing a global variable.
     * @return
     *          true if the document element has the global variable.
     * @throws XMLException 
     *          if the element representing the variable is null.
     */
    public boolean hasGlobalVariable(Ev variable) throws XMLException {
        return globalVariables.contains(variable);
    }


    /**
     * Verifies if the document element has a global variable with a specific
     * name. The global variables are referred by rule and property elements.
     * 
     * @param name
     *          string representing the name of the global variable.
     * @return
     *          true if the document element has the variable.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasGlobalVariable(String name) throws XMLException {
        for(Ev aux : globalVariables){
            if(aux.parse(0).equals(name))
                return true;
        }
        
        return false;
    }


    /**
     * Verifies if the document element has at least one global variable. The
     * global variables are referred by rule and property elements.
     * 
     * @return 
     *          true if the document has at least one variable.
     */
    public boolean hasGlobalVariable() {
        return !globalVariables.isEmpty();
    }


    /**
     * Returns the list of global variables that a document element have. The
     * global variables are referred by rule and property elements.
     * 
     * @return 
     *          element list with all variables.
     */
    public ElementList<Ev> getGlobalVariables() {
        return globalVariables;
    }


    /**
     * Returns the global variables with a specific name. The global variables
     * are referred by rule and property elements.
     * 
     * @param name
     *          string representing the name of the global variable.
     * @return 
     *          element representing a variable.
     */
    public Ev getGlobalVariable(String name) {
        for(Ev aux : globalVariables){
            if(aux.getName().equals(name))
                return aux;
        }
        return null;
    }
    
    
    /**
     * Merges the list of global variables that the document have with the
     * variables in another list.
     * 
     * @param list 
     *          element list with all variables.
     */
    public void addGlobalVariableLists(ElementList<Ev> list) {
        globalVariables.addAll(list);
    }
    

    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLDoc))
            return false;
        
        boolean result = true;
        Object aux;
        
        if((aux = getId()) != null)
            result &= aux.equals(((NCLDoc) other).getId());
        if((aux = getTitle()) != null)
            result &= aux.equals(((NCLDoc) other).getTitle());
        if((aux = getXmlns()) != null)
            result &= aux.equals(((NCLDoc) other).getXmlns());
        
        T el;
        if((el = (T) getHead()) != null)
            result &= el.compare(((NCLDoc) other).getHead());
        if((el = (T) getBody()) != null)
            result &= el.compare(((NCLDoc) other).getBody());
        
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

        // XML document start declaration
        content = space + "<?xml version='1.0' encoding='ISO-8859-1'?>\n";

        content += space + "<!-- Generated with aNa - API for NCL Authoring -->\n\n";

        // <ncl> element and attributes declaration
        content += space + "<ncl";
        content += parseAttributes();
        content += ">\n";

        // <ncl> element content
        content += parseElements(ident + 1);

        // <ncl> element end declaration
        content += space + "</ncl>\n";

        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        try{
            loadId(element);

            loadTitle(element);

            loadXmlns(element);
        }
        catch(XMLException ex){
            throw new NCLParsingException("NCLDocument:\n" + ex.getMessage());
        }

        try{
            loadHead(element);

            loadBody(element);

            // fix the references needed
            fixReferences();
        }
        catch(XMLException ex){
            throw new NCLParsingException("Error pasring " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseTitle();
        content += parseXmlns();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseHead(ident);
        content += parseBody(ident);
        
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
        
        // set the id (optional)
        att_name = NCLElementAttributes.ID.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setId(att_var);
    }
    
    
    protected String parseTitle() {
        String aux = getTitle();
        if(aux != null)
            return " title='" + aux + "'";
        else
            return "";
    }
    
    
    protected void loadTitle(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the title (optional)
        att_name = NCLElementAttributes.TITLE.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setTitle(att_var);
    }
    
    
    protected String parseXmlns() {
        NCLNamespace aux = getXmlns();
        if(aux != null)
            return " xmlns='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected void loadXmlns(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the xmlns (optional)
        att_name = NCLElementAttributes.XMLNS.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setXmlns(NCLNamespace.getEnumType(att_var));
    }
    
    
    protected String parseHead(int ident) {
        Eh aux = getHead();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadHead(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the head
        ch_name = NCLElementAttributes.HEAD.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Eh inst = createHead();
            setHead(inst);
            inst.load(el);
        }
    }
    
    
    protected String parseBody(int ident) {
        Eb aux = getBody();
        if(aux != null)
            return aux.parse(ident);
        else
            return "";
    }
    
    
    protected void loadBody(Element element) throws XMLException {
        String ch_name;
        Element el;
        
        // create the body
        ch_name = NCLElementAttributes.BODY.toString();
        el = (Element) element.getElementsByTagName(ch_name).item(0);
        if(el != null){
            Eb inst = createBody();
            setBody(inst);
            inst.load(el);
        }
    }


    /**
     * Loads the objects structure representing an NCL document from an XML file.
     *
     * @param xmlFile
     *          file with the NCL document content.
     * @throws NCLParsingException
     *          if an error occur while parsing the document.
     */
    public void loadXML(File xmlFile) throws XMLException {
        // Store the file name and location
        location = xmlFile.getParent();
        fileName = xmlFile.getName();
        
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.parse(xmlFile);
            load(doc.getDocumentElement());
        }catch(SAXException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }catch(ParserConfigurationException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }catch(IOException e){
            throw new NCLParsingException(e.fillInStackTrace());
        }
    }
    
    
    public void mergeGlobalVariables(NCLDoc other) throws XMLException {
        ElementList<Ev> other_vars = other.getGlobalVariables();
        if(other_vars.isEmpty())
            return;
        
        for(Ev var : getGlobalVariables()){
            for(Ev ovar : other_vars){
                if(var.compare(ovar)){
                    var.mergeVariables(ovar);
                    other_vars.remove(ovar);
                    break;
                }
            }
        }
        
        if(!other_vars.isEmpty())
            addGlobalVariableLists(other_vars);
    }
    
    
    public void waitReference(PostReferenceElement element) {
        references.add(element);
    }
    
    
    public void fixReferences() throws XMLException {
        for(PostReferenceElement el : references)
            el.fixReference();
    }

    
    @Override
    public void clean() throws XMLException {
        
        if(head != null)
            head.clean();
        
        if(body != null)
            body.clean();
        
        title = null;
        xmlns = null;
        head = null;
        body = null;
        location = null;
        fileName = null;
        
        references.clear();
        references = null;
        
        for(Ev g : globalVariables)
            g.clean();
    }
    

    /**
     * Function to create the child element <i>head</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>head</i>.
     */
    protected Eh createHead() throws XMLException {
        return (Eh) new NCLHead();
    }


    /**
     * Function to create the child element <i>body</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>body</i>.
     */
    protected Eb createBody() throws XMLException {
        return (Eb) new NCLBody();
    }
}
