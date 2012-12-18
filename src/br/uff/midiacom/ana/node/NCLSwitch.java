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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.util.reference.ExternalReferenceType;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.interfaces.NCLInterface;
import br.uff.midiacom.ana.rule.NCLBindRule;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ElementList;
import br.uff.midiacom.ana.util.exception.NCLRemovalException;
import br.uff.midiacom.ana.util.ncl.NCLElementPrototype;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class that represents a switch element. A switch element represents a content
 * control composition that, when executed, will present alternatively one of its
 * component nodes.
 * 
 * <br/>
 * 
 * This element defines the attributes:
 * <ul>
 *  <li><i>id</i> - id of the switch element. This attribute is required.</li>
 *  <li><i>refer</i> - reference to a switch element. This attribute is optional.</li>
 * </ul>
 * 
 * <br/>
 * 
 * This element has as children the elements:
 * <ul>
 *  <li><i>switchPort</i> - element representing a switch interface point. The
 *                          switch can have none or several switchPort elements.</li>
 *  <li><i>bindRule</i> - element relating a rule to a switch component node. The
 *                        switch can have none or several bindRule elements.</li>
 *  <li><i>defaultComponent</i> - element representing the switch component node
 *                                presented when no rule is true. This element
 *                                is optional.</li>
 *  <li><i>media</i> - element representing a media object. The switch can have
 *                     none or several media elements.</li>
 *  <li><i>context</i> - element representing a composition. The switch can have
 *                       none or several context elements.</li>
 *  <li><i>switch</i> - element representing a content control composition. The
 *                      switch can have none or several switch elements.</li>
 * </ul>
 * 
 * @param <T>
 * @param <P>
 * @param <I>
 * @param <En>
 * @param <Ep>
 * @param <Eb>
 * @param <Rn> 
 */
public class NCLSwitch<T extends NCLElement,
                       En extends NCLNode,
                       Ei extends NCLInterface,
                       Ep extends NCLSwitchPort,
                       Er extends NCLTestRule,
                       R extends ExternalReferenceType,
                       Eb extends NCLBindRule<T, En, Er, R>>
        extends NCLIdentifiableElementPrototype<T>
        implements NCLNode<T, En, Ei>, PostReferenceElement {

    protected Object refer;
    protected En defaultComponent;
    protected ElementList<Ep> ports;
    protected ElementList<Eb> binds;
    protected ElementList<En> nodes;
    
    protected ArrayList<T> references;


    /**
     * Switch element constructor.
     * 
     * @throws XMLException 
     *          if an error occur while creating the element.
     */
    public NCLSwitch() {
        super();
        ports = new ElementList<Ep>();
        binds = new ElementList<Eb>();
        nodes = new ElementList<En>();
        references = new ArrayList<T>();
    }
    
    
    public NCLSwitch(String id) throws XMLException {
        super();
        ports = new ElementList<Ep>();
        binds = new ElementList<Eb>();
        nodes = new ElementList<En>();
        references = new ArrayList<T>();
        setId(id);
    }
    
    
    @Override
    @Deprecated
    public void setDoc(T doc) {
        super.setDoc(doc);
        for (Ep aux : ports) {
            aux.setDoc(doc);
        }
        for (Eb aux : binds) {
            aux.setDoc(doc);
        }
        for (En aux : nodes) {
            ((NCLElementPrototype) aux).setDoc(doc);
        }
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        if(id == null)
            throw new XMLException("Null id string");
        
        super.setId(id);
    }


    /**
     * Sets the reference to a switch element. This attribute is optional. Set the
     * reference to <i>null</i> to erase a reference already defined.
     * 
     * <br/>
     * 
     * The referred element must be a switch element that represents the same
     * switch defined in the body of the NCL document where this switch is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child nodes.
     * It can not refer to another node.
     * 
     * <br/>
     * 
     * When a switch element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attribute and
     * child nodes defined by the switch that makes the reference are ignored,
     * except the id attribute.
     * 
     * <br/>
     * 
     * The referred element and the one that makes the reference must be
     * considered the same, in relation to its data structure. That means that
     * a node can be represented by more than one NCL element. Since nodes inside
     * a composite node defines a set of nodes, a node can not be represented by
     * more than one node inside a composition. That means that the id attribute
     * of an NCL element representing a node is not only a unique identifier for
     * the element, but also the only identifier corresponding to a node inside
     * a composition.
     * 
     * @param refer
     *          element representing a switch, a reference to a switch element
     *          or <i>null</i> to erase a reference already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the switch
     *          element.
     */
    public void setRefer(Object refer) throws XMLException {
        Object aux = this.refer;
        
        if(refer instanceof NCLSwitch)
            ((En) refer).addReference(this);
        else if(refer instanceof ExternalReferenceType){
            ((R) refer).getTarget().addReference(this);
            ((R) refer).getAlias().addReference(this);
        }
        
        this.refer = refer;
        notifyAltered(NCLElementAttributes.REFER, aux, refer);
        
        if(aux != null){
            if(aux instanceof NCLSwitch)
                ((En) aux).removeReference(this);
            else{
                ((R) aux).getTarget().removeReference(this);
                ((R) aux).getAlias().removeReference(this);
            }
        }
    }


    /**
     * Returns the reference to a switch element or <i>null</i> if the attribute
     * is not defined.
     * 
     * <br/>
     * 
     * The referred element must be a switch element that represents the same
     * switch defined in the body of the NCL document where this switch is or
     * in the body of an imported document. In the last case, the alias of the
     * imported document must be indicated in the reference.
     * 
     * <br/>
     * 
     * The referred element must directly define its attributes and child nodes.
     * It can not refer to another node.
     * 
     * <br/>
     * 
     * When a switch element defines the refer attribute, all attributes and
     * child nodes defined by the referred node are inherited. The attribute and
     * child nodes defined by the switch that makes the reference are ignored,
     * except the id attribute.
     * 
     * <br/>
     * 
     * The referred element and the one that makes the reference must be
     * considered the same, in relation to its data structure. That means that
     * a node can be represented by more than one NCL element. Since nodes inside
     * a composite node defines a set of nodes, a node can not be represented by
     * more than one node inside a composition. That means that the id attribute
     * of an NCL element representing a node is not only a unique identifier for
     * the element, but also the only identifier corresponding to a node inside
     * a composition.
     * 
     * @return 
     *          element representing a switch, a reference to a switch element
     *          or <i>null</i> if the attribute is not defined.
     */
    public Object getRefer() {
        return refer;
    }


    /**
     * Adds an element representing a switch node interface point to the
     * switch node. The switch node can have none or several port elements.
     *
     * @param port
     *          element representing a switch node interface point.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean addPort(Ep port) throws XMLException {
        if(ports.add(port)){
            notifyInserted((T) port);
            port.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a switch node interface point of the
     * switch node. The switch node can have none or several port elements.
     *
     * @param port
     *          element representing a switch node interface point.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean removePort(Ep port) throws XMLException {
        if(!port.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(ports.remove(port)){
            notifyRemoved((T) port);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a switch node interface point of the
     * switch node. The switch node can have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          switch node interface point.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removePort(String id) throws XMLException {
        Ep aux = ports.get(id);
        return removePort(aux);
    }


    /**
     * Verifies if the switch element has a specific element representing
     * a switch node interface point. The switch node can have none or several
     * port elements.
     * 
     * @param port
     *          element representing a switch node interface point.
     * @return
     *          true if the switch node element has the port element.
     * @throws XMLException 
     *          if the element representing the port is null.
     */
    public boolean hasPort(Ep port) throws XMLException {
        return ports.contains(port);
    }


    /**
     * Verifies if the switch element has a interface point with a specific id.
     * The switch node can have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          switch node interface point.
     * @return
     *          true if the switch element has the port element.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasPort(String id) throws XMLException {
        return ports.get(id) != null;
    }


    /**
     * Verifies if the switch element has at least one interface point.
     * The switch node can have none or several port elements.
     * 
     * @return 
     *          true if the switch node has at least one interface point.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Returns the list of interface points that a switch element have.
     * The switch node can have none or several port elements.
     * 
     * @return 
     *          element list with all interface points.
     */
    public ElementList<Ep> getPorts() {
        return ports;
    }


    /**
     * Returns the interface point with a specific id. The switch node can
     * have none or several port elements.
     * 
     * @param id
     *          string representing the id of the element representing a 
     *          switch node interface point.
     * @return 
     *          element representing a switch node interface point.
     */
    public Ep getPort(String id) throws XMLException {
        return ports.get(id);
    }


    /**
     * Sets the element representing the switch component node presented when no
     * rule is true. This element is optional. Set the default component to
     * <i>null</i> to erase a default component already defined.
     * 
     * @param defaultComponent 
     *          element representing a reference to a switch component node or
     *          <i>null</i> to erase a default component already defined.
     * @throws XMLException 
     *          if any error occur while creating the reference to the switch
     *          component.
     */
    public void setDefaultComponent(En defaultComponent) throws XMLException {
        if(this.defaultComponent != null)
            this.defaultComponent.removeReference(this);
        
        En aux = this.defaultComponent;
        this.defaultComponent = defaultComponent;
        notifyAltered(NCLElementAttributes.DEFAULTCOMPONENT, aux, defaultComponent);
        
        if(this.defaultComponent != null)
            this.defaultComponent.addReference(this);
    }


    /**
     * Returns the element representing the switch component node presented when
     * no rule is true or <i>null</i> if the attribute is not defined.
     * 
     * @return 
     *          element representing a reference to a switch component node or
     *          <i>null</i> if the attribute is not defined.
     */
    public En getDefaultComponent() {
        return defaultComponent;
    }


    /**
     * Adds an element relating a rule to a switch component node. The switch
     * can have none or several bindRule elements.
     * 
     * @param bind
     *          element relating a rule to a switch component node.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean addBind(Eb bind) throws XMLException {
        if(binds.add(bind)){
            notifyInserted((T) bind);
            bind.setParent((T) this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element relating a rule to a switch component node. The switch
     * can have none or several bindRule elements.
     *
     * @param bind
     *          element relating a rule to a switch component node.
     * @return
     *          true if the element was removed.
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
     * Verifies if the switch element has a specific element relating a rule to
     * a switch component node. The switch can have none or several bindRule
     * elements.
     *
     * @param bind
     *          element relating a rule to a switch component node.
     * @return
     *          true if the switch element has the bind.
     * @throws XMLException 
     *          if the element representing the bind is null.
     */
    public boolean hasBind(Eb bind) throws XMLException {
        return binds.contains(bind);
    }


    /**
     * Verifies if the switch element has at least one element relating a rule to
     * a switch component node. The switch can have none or several bindRule
     * elements.
     * 
     * @return 
     *          true if the switch has at least one bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Returns the list of binds that a switch element have. The switch can have
     * none or several bindRule elements.
     * 
     * @return 
     *          element list with all binds.
     */
    public ElementList<Eb> getBinds() {
        return binds;
    }


    /**
     * Adds an element representing a node to the switch element. The
     * node can be a media, a context or a switch element. The switch node
     * can have none or several node elements.
     * 
     * @param node
     *          element representing a node.
     * @return
     *          true if the element was added.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean addNode(En node) throws XMLException {
        if(nodes.add(node)){
            notifyInserted((T) node);
            node.setParent(this);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a node of the switch element. The
     * node can be a media, a context or a switch element. The switch node
     * can have none or several node elements.
     * 
     * @param node
     *          element representing a node.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean removeNode(En node) throws XMLException {
        if(!node.getReferences().isEmpty())
            throw new NCLRemovalException("This element has a reference to it."
                    + " The reference must be undone before erasing this element.");
        
        if(nodes.remove(node)){
            notifyRemoved((T) node);
            return true;
        }
        return false;
    }


    /**
     * Removes an element representing a node of the switch element. The node
     * can be a media, a context or a switch element. The switch node can have
     * none or several node elements.
     *
     * @param id
     *          string representing the id of the node.
     * @return
     *          true if the element was removed.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean removeNode(String id) throws XMLException {
        En aux = nodes.get(id);
        return removeNode(aux);
    }


    /**
     * Verifies if the switch element has a specific element representing
     * a node. The node can be a media, a context or a switch element. The
     * switch node can have none or several node elements.
     *
     * @param node
     *          element representing a node.
     * @return
     *          true if the switch node element has the node.
     * @throws XMLException 
     *          if the element representing the node is null.
     */
    public boolean hasNode(En node) throws XMLException {
        return nodes.contains(node);
    }


    /**
     * Verifies if the switch element has a node with a specific id. The node
     * can be a media, a context or a switch element. The switch node can have
     * none or several node elements.
     * 
     * @param id
     *          string representing the id of the node.
     * @return
     *          true if the switch node element has the node.
     * @throws XMLException 
     *          if the string is null or empty.
     */
    public boolean hasNode(String id) throws XMLException {
        return nodes.get(id) != null;
    }


    /**
     * Verifies if the switch element has at least one node. The node can be
     * a media, a context or a switch element. The switch node can have none or
     * several node elements.
     * 
     * @return 
     *          true if the switch node has at least one node.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Returns the list of nodes that the switch element have. The node can be
     * a media, a context or a switch element. The switch node can have none or
     * several node elements.
     * 
     * @return 
     *          element list with all nodes.
     */
    public ElementList<En> getNodes() {
        return nodes;
    }


    /**
     * Returns the node with a specific id. The node can be a media, a context
     * or a switch element. The switch node can have none or several node
     * elements.
     * 
     * @param id
     *          string representing the id of the node.
     * @return 
     *          element representing a node.
     */
    public En getNode(String id) throws XMLException {
        return nodes.get(id);
    }


    @Override
    public boolean compare(T other) {
        if(other == null || !(other instanceof NCLSwitch))
            return false;
        
        boolean result = true;
        
        String aux;
        if((aux = getId()) != null)
            result &= aux.equals(((NCLSwitch) other).getId());
        
        En def;
        if((def = getDefaultComponent()) != null)
            result &= def.compare(((NCLSwitch) other).getDefaultComponent());
        
        Object ref = getRefer();
        Object oref = ((NCLSwitch) other).getRefer();
        if(ref != null && oref != null){
            if(ref instanceof NCLSwitch && oref instanceof NCLSwitch)
                result &= ((En) ref).compare((En) oref);
            else if(ref instanceof ExternalReferenceType && oref instanceof ExternalReferenceType)
                result &= ((R) ref).equals((R) oref);
            else
                result = false;
        }
        
        ElementList<En> othernod = ((NCLSwitch) other).getNodes();
        result &= nodes.size() == othernod.size();
        for (En nod : nodes) {
            try {
                result &= othernod.contains(nod);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Ep> otherpor = ((NCLSwitch) other).getPorts();
        result &= ports.size() == otherpor.size();
        for (Ep por : ports) {
            try {
                result &= otherpor.contains(por);
            } catch (XMLException ex) {}
            if(!result)
                break;
        }
        
        ElementList<Eb> otherbin = ((NCLSwitch) other).getBinds();
        result &= binds.size() == otherbin.size();
        for (Eb bin : binds) {
            try {
                result &= otherbin.contains(bin);
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

        content = space + "<switch";
        content += parseAttributes();

        if(hasPort() || hasBind() || hasNode()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</switch>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    @Override
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
            
            throw new NCLParsingException("Switch" + aux + ":\n" + ex.getMessage());
        }

        try{
            // create the child nodes (except ports and binds)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadMedia(el);
                    loadContext(el);
                    loadSwitch(el);
                }
            }

            // create the child nodes (ports, binds and defaultComponent)
            nl = element.getChildNodes();
            for(int i=0; i < nl.getLength(); i++){
                Node nd = nl.item(i);
                if(nd instanceof Element){
                    Element el = (Element) nl.item(i);

                    loadPorts(el);
                    loadBinds(el);
                    loadDefaultComponent(el);
                }
            }
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + " > " + ex.getMessage());
        }

        try{
            loadRefer(element);
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + ":\n" + ex.getMessage());
        }
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        content += parseRefer();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parsePorts(ident);
        content += parseBinds(ident);
        content += parseDefaultComponent(ident);
        content += parseNodes(ident);
        
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
    
    
    protected String parseRefer() {
        Object aux = getRefer();
        if(aux == null)
            return "";
        
        if(aux instanceof NCLSwitch)
            return " refer='" + ((En) aux).getId() + "'";
        else
            return " refer='" + ((R) aux).toString() + "'";
    }
    
    
    protected void loadRefer(Element element) throws XMLException {
        String att_name, att_var;
        
        // set the refer (optional)
        att_name = NCLElementAttributes.REFER.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty()){
            En ref = (En) new NCLSwitch(att_var);
            setRefer(ref);
            ((NCLDoc) getDoc()).waitReference(this);
        }
    }
    
    
    protected String parsePorts(int ident) {
        if(!hasPort())
            return "";
        
        String content = "";
        for(Ep aux : ports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadPorts(Element element) throws XMLException {
        //create the switchPort
        if(element.getTagName().equals(NCLElementAttributes.SWITCHPORT.toString())){
            Ep inst = createSwitchPort();
            addPort(inst);
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
        // create the bindRule
        if(element.getTagName().equals(NCLElementAttributes.BINDRULE.toString())){
            Eb inst = createBindRule();
            addBind(inst);
            inst.load(element);
        }
    }
    
    
    protected String parseDefaultComponent(int ident) {
        En aux = getDefaultComponent();
        if(aux == null)
            return "";
        
        String space = "";
        if(ident < 0)
            ident = 0;
        
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        return space + "<defaultComponent component='" + aux.getId() + "'/>\n";
    }
    
    
    protected void loadDefaultComponent(Element element) throws XMLException {
        String att_name, att_var;
        
        // create the defaultComponent
        if(element.getTagName().equals(NCLElementAttributes.DEFAULTCOMPONENT.toString())){
            att_name = NCLElementAttributes.COMPONENT.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setDefaultComponent(nodes.get(att_var));
        }
    }
    
    
    protected String parseNodes(int ident) {
        if(!hasNode())
            return "";
        
        String content = "";
        for(En aux : nodes)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected void loadMedia(Element element) throws XMLException {
        // create the media
        if(element.getTagName().equals(NCLElementAttributes.MEDIA.toString())){
            En inst = createMedia();
            addNode(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadContext(Element element) throws XMLException {
        // create the context
        if(element.getTagName().equals(NCLElementAttributes.CONTEXT.toString())){
            En inst = createContext();
            addNode(inst);
            inst.load(element);
        }
    }
    
    
    protected void loadSwitch(Element element) throws XMLException {
        // create the switch
        if(element.getTagName().equals(NCLElementAttributes.SWITCH.toString())){
            En inst = createSwitch();
            addNode(inst);
            inst.load(element);
        }
    }
    
    
    @Override
    public Ei findInterface(String id) throws XMLException {
        Ei result;
        
        // if reuses another switch search in it
        Object aux;
        if((aux = getRefer()) != null){
            if(aux instanceof NCLSwitch)
                return (Ei) ((En) aux).findInterface(id);
            else
                return (Ei) ((En) ((R) aux).getTarget()).findInterface(id);
        }
        
        // search as a switchPort
        result = (Ei) ports.get(id);
        if(result != null)
            return result;
        
        // search in inner nodes
        for(En node : nodes){
            result = (Ei) node.findInterface(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    @Override
    public En findNode(String id) throws XMLException {
        En result;
        
        if(getId().equals(id))
            return (En) this;
        
        // if reuses another switch search in it
        Object aux;
        if((aux = getRefer()) != null){
            if(aux instanceof NCLSwitch)
                return (En) ((En) aux).findNode(id);
            else
                return (En) ((En) ((R) aux).getTarget()).findNode(id);
        }
        
        for(En node : nodes){
            result = (En) node.findNode(id);
            if(result != null)
                return result;
        }
        
        return null;
    }
    
    
    @Override
    @Deprecated
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // set the refer (optional)
            if((aux = ((En) getRefer()).getId()) != null){
                En ref = (En) ((NCLBody) ((NCLDoc) getDoc()).getBody()).findNode(aux);
                setRefer(ref);
            }
        }
        catch(XMLException ex){
            aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Switch" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
    }
    
    
    @Override
    @Deprecated
    public boolean addReference(T reference) throws XMLException {
        return references.add(reference);
    }
    
    
    @Override
    @Deprecated
    public boolean removeReference(T reference) throws XMLException {
        return references.remove(reference);
    }
    
    
    @Override
    public ArrayList getReferences() {
        return references;
    }

    
    @Override
    public void clean() throws XMLException {
        setParent(null);
        
        if(refer != null){
            if(refer instanceof NCLSwitch)
                ((NCLSwitch)refer).removeReference(this);
            else if(refer instanceof ExternalReferenceType){
                ((R) refer).getTarget().removeReference(this);
                ((R) refer).getAlias().removeReference(this);
            }
        }
        
        if(defaultComponent != null)
            defaultComponent.removeReference(this);
        
        refer = null;
        defaultComponent = null;
        
        for(Ep p : ports)
            p.clean();
        
        for(Eb b : binds)
            b.clean();
        
        for(En n : nodes)
            n.clean();
    }
    

    /**
     * Function to create the child element <i>bindRule</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>bindRule</i>.
     */
    protected Eb createBindRule() throws XMLException {
        return (Eb) new NCLBindRule<T, En, Er, R>();
    }


    /**
     * Function to create the child element <i>switchPort</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switchPort</i>.
     */
    protected Ep createSwitchPort() throws XMLException {
        return (Ep) new NCLSwitchPort();
    }


    /**
     * Function to create the child element <i>media</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>media</i>.
     */
    protected En createMedia() throws XMLException {
        return (En) new NCLMedia();
    }


    /**
     * Function to create the child element <i>context</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>context</i>.
     */
    protected En createContext() throws XMLException {
        return (En) new NCLContext();
    }


    /**
     * Function to create the child element <i>switch</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>switch</i>.
     */
    protected En createSwitch() throws XMLException {
        return (En) new NCLSwitch();
    }
}
