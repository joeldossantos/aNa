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
package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a link element. A link represents a relationship among
 * nodes in an NCL document.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the link element. This attribute is optional.</li>
 *  <li><i>xconnector</i> - connector that defines the relation represented by
 *                          the link element. This attribute is required.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>linkParam</i> - element that defines a value to a parameter defined
 *                         in the connector. The link can have none or several
 *                         parameter elements.</li>
 *  <li><i>bind</i> - element that associates a role defined in the connector to
 *                    a node. The link must have at least two bind elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <Ep>
 * @param <Eb>
 * @param <Ec> 
 */
public class NCLLink<T extends NCLElement,
                     Ep extends NCLLinkParam,
                     Eb extends NCLBind,
                     Ec extends NCLCausalConnector,
                     R extends ExternalReferenceType>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLElement<T> {

    protected Object xconnector;
    protected ElementList<Ep> linkParams;
    protected ElementList<Eb> binds;
    

    /**
     * Link element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLLink() {
        super();
        linkParams = new ElementList<Ep>();
        binds = new ElementList<Eb>();
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ep aux : linkParams) {
            aux.setDoc(doc);
        }
        for (Eb aux : binds) {
            aux.setDoc(doc);
        }
    }


    /**
     * Sets the connector that defines the relation represented by the link
     * element. This attribute is required and can not be set to <i>null</i>.
     * 
     * <br/>
     * 
     * The connector referred can be defined in the document base of connectors
     * or in a base defined in an external document, imported by the base of
     * connectors or by the base of imported documents. When the connector is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @param xconnector
     *          element representing a connector or a reference to a connector
     *          element.
     * @throws XMLException 
     *          if the connector is null or any error occur while creating the
     *          reference to the connector.
     */
    public void setXconnector(Object xconnector) throws XMLException {
        if(xconnector == null)
            throw new XMLException("Null connector.");
        
        Object aux = this.xconnector;
        
        if(xconnector instanceof NCLCausalConnector){
            this.xconnector = xconnector;
            ((Ec) xconnector).addReference(this);
            
        }
        else if(xconnector instanceof ExternalReferenceType){
            this.xconnector = xconnector;
            ((R) xconnector).getTarget().addReference(this);
            ((R) xconnector).getAlias().addReference(this);
        }
        
        this.xconnector = xconnector;
        notifyAltered(NCLElementAttributes.DESCRIPTOR, aux, xconnector);
        
        if(aux != null){
            if(aux instanceof NCLCausalConnector)
                ((Ec) xconnector).removeReference(this);
            else{
                ((R) xconnector).getTarget().removeReference(this);
                ((R) xconnector).getAlias().removeReference(this);
            }
        }
    }
    
    
    /**
     * Returns the connector that defines the relation represented by the link
     * element or <i>null</i> if the attribute is not defined.
     * 
     * <br/>
     * 
     * The connector referred can be defined in the document base of connectors
     * or in a base defined in an external document, imported by the base of
     * connectors or by the base of imported documents. When the connector is
     * defined in an external document, the alias of the imported document must
     * be indicated in the reference.
     * 
     * @return
     *          element representing a reference to a connector element or
     *          <i>null</i> if the attribute is not defined.
     */
    public Object getXconnector() {
        return xconnector;
    }
    
    
    /**
     * Adds a link parameter to the link. A link parameter defines a value to a
     * parameter defined in the connector. The link can have none or several
     * parameter elements.
     * 
     * @param param
     *          element representing a link parameter.
     * @return
     *          true if the parameter was added.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean addLinkParam(Ep param) throws XMLException {
        if(linkParams.add(param)){
            notifyInserted((T) param);
            param.setParent(this);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a link parameter of the link. A link parameter defines a value to
     * a parameter defined in the connector. The link can have none or several
     * parameter elements.
     * 
     * @param param
     *          element representing a link parameter.
     * @return
     *          true if the parameter was removed.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean removeLinkParam(Ep param) throws XMLException {
        if(linkParams.remove(param)){
            notifyRemoved((T) param);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the link has a specific element representing a link parameter.
     * A link parameter defines a value to a parameter defined in the connector.
     * The link can have none or several parameter elements.
     * 
     * @param param
     *          element representing a link parameter.
     * @return
     *          true if the link has the parameter element.
     * @throws XMLException 
     *          if the element representing the parameter is null.
     */
    public boolean hasLinkParam(Ep param) throws XMLException {
        return linkParams.contains(param);
    }
    
    
    /**
     * Verifies if the link has at least one link parameter. A link parameter
     * defines a value to a parameter defined in the connector. The link can have
     * none or several parameter elements.
     * 
     * @return 
     *          true if the link has at least one parameter.
     */
    public boolean hasLinkParam() {
        return !linkParams.isEmpty();
    }
    
    
    /**
     * Returns the list of link parameters that a link have. A link parameter
     * defines a value to a parameter defined in the connector. The link can have
     * none or several parameter elements.
     * 
     * @return 
     *          element list with all parameters.
     */
    public ElementList<Ep> getLinkParams() {
        return linkParams;
    }
    
    
    /**
     * Adds a bind to the link. A bind element associates a role defined in the
     * connector to a node. The link must have at least two bind elements.
     * 
     * @param bind
     *          element representing a bind.
     * @return
     *          true if the bind was added.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean addBind(Eb bind) throws XMLException {
        if(binds.add(bind)){
            notifyInserted((T) bind);
            bind.setParent(this);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes a bind of the link. A bind element associates a role defined in
     * the connector to a node. The link must have at least two bind elements.
     * 
     * @param bind
     *          element representing a bind.
     * @return
     *          true if the bind was removed.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean removeBind(Eb bind) throws XMLException {
        if(binds.remove(bind)){
            notifyRemoved((T) bind);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifies if the link has a specific bind element. A bind element
     * associates a role defined in the connector to a node. The link must have
     * at least two bind elements.
     * 
     * @param bind
     *          element representing a bind.
     * @return
     *          true if the link has the bind element.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifies if the link has at least one bind element. A bind element
     * associates a role defined in the connector to a node. The link must have
     * at least two bind elements.
     * 
     * @return 
     *          true if the link has at least one bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }
    
    
    /**
     * Returns the list of bind elements that a link have. A bind element
     * associates a role defined in the connector to a node. The link must have
     * at least two bind elements.
     * 
     * @return 
     *          element list with all binds.
     */
    public ElementList<Eb> getBinds() {
        return binds;
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLLink))
            return false;
        
        boolean result = true;

        Object aux;

        if((aux = getId()) != null)
            result &= aux.equals(((NCLLink) other).getId());

        aux = getXconnector();
        Object oaux = ((NCLLink) other).getXconnector();
        if(aux != null && oaux != null){
            if(aux instanceof NCLCausalConnector && oaux instanceof NCLCausalConnector)
                result &= ((Ec) aux).compare((Ec) oaux);
            else
                result &= ((R) aux).equals((R) oaux);
        }

        ElementList<Eb> otherbin = ((NCLLink) other).getBinds();
        result &= binds.size() == otherbin.size();
        for (Eb bin : binds) {
            try {
                result &= otherbin.contains(bin);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Ep> otherpar = ((NCLLink) other).getLinkParams();
        result &= linkParams.size() == otherpar.size();
        for (Ep par : linkParams) {
            try {
                result &= otherpar.contains(par);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }

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
        
        
        // <link> element and attributes declaration
        content = space + "<link";
        content += parseAttributes();
        content += ">\n";
        
        // <link> element content
        content += parseElements(ident + 1);

        // <link> element end declaration
        content += space + "</link>\n";
        
        return content;
    }


    @Override
    public void load(Element element) throws NCLParsingException {
        NodeList nl;

        try{
            loadId(element);
            loadXconnector(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Link" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadLinkParams(el);
                    loadBinds(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Link" + aux + " > " + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseXconnector();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseLinkParams(ident);
        content += parseBinds(ident);
        
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
    
    
    protected String parseXconnector() {
        Object aux = getXconnector();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLCausalConnector)
            return " xconnector='" + ((Ec) aux).getId() + "'";
        else
            return " xconnector='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadXconnector(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the xconnector (required)
        att_name = NCLElementAttributes.XCONNECTOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            NCLDoc d = (NCLDoc) getDoc();
            String[] con = adjustReference(att_var);
            setXconnector(d.getHead().findConnector(con[0], con[1]));
        }
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");
    }
    
    
    protected String parseLinkParams(int ident) {
        if(!hasLinkParam())
            return "";
        
        String content = "";
        for(Ep aux : linkParams)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadLinkParams(Element element) throws XMLException {
        //create the link params
        if(element.getTagName().equals(NCLElementAttributes.LINKPARAM.toString())){
            Ep inst = createLinkParam();
            addLinkParam(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseBinds(int ident) {
        if(!hasBind())
            return "";
        
        String content = "";
        for(Eb aux : binds)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadBinds(Element element) throws XMLException {
        // create the binds
        if(element.getTagName().equals(NCLElementAttributes.BIND.toString())){
            Eb inst = createBind();
            addBind(inst);
            inst.load(element);
        }
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(xconnector instanceof NCLCausalConnector)
            ((Ec)xconnector).removeReference(this);
        else{
            ((R) xconnector).getTarget().removeReference(this);
            ((R) xconnector).getAlias().removeReference(this);
        }
        
        xconnector = null;
        
        for(Ep ep : linkParams)
            ep.clean();
        
        for(Eb eb : binds)
            eb.clean();
    }
    

    /**
     * Function to create the child element <i>linkParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>linkParam</i>.
     */
    protected Ep createLinkParam() throws XMLException {
        return (Ep) new NCLLinkParam();
    }


    /**
     * Function to create the child element <i>bind</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bind</i>.
     */
    protected Eb createBind() throws XMLException {
        return (Eb) new NCLBind();
    }
}
