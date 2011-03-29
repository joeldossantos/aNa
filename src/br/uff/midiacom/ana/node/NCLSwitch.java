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
package br.uff.midiacom.ana.node;

import br.uff.midiacom.ana.interfaces.NCLSwitchPort;
import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define a apresentação de conteudos alternativos em um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLSwitch<N extends NCLNode, S extends NCLSwitch, P extends NCLSwitchPort, B extends NCLBindRule> extends NCLIdentifiableElement implements NCLNode<N> {

    private S refer;
    private N defaultComponent;
    private Set<P> ports = new TreeSet<P>();
    private List<B> binds = new ArrayList<B>();
    private Set<N> nodes = new TreeSet<N>();

    private boolean insideSwitch;


    /**
     * Construtor do elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch for inválido.
     */
    public NCLSwitch(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLSwitch(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideSwitch = false;
    }


    /**
     * Atribui um switch para ser reutilizado pelo switch.
     *
     * @param refer
     *          elemento representando o switch a ser reutilizado.
     */
    public void setRefer(S refer) {
        this.refer = refer;
    }


    /**
     * Retorna o switch reutilizado pelo switch.
     *
     * @return
     *          elemento representando o switch a ser reutilizado.
     */
    public S getRefer() {
        return refer;
    }


    /**
     * Adiciona uma porta ao switch.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addPort(P port) {
        if(ports.add(port)){
            //Se port existe, atribui este como seu parente
            if(port != null)
                port.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove uma porta do switch.
     *
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(String id) {
        for(P port : ports){
            if(port.getId().equals(id))
                return removePort(port);
        }
        return false;
    }


    /**
     * Remove uma porta do switch.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(P port) {
        if(ports.remove(port)){
            //Se port existe, retira o seu parentesco
            if(port != null)
                port.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o switch possui uma porta.
     *
     * @param id
     *          identificador da porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(String id) {
        for(P port : ports){
            if(port.getId().equals(id))
                return true;
        }
        return false;
    }


    /**
     * Verifica se o switch possui uma porta.
     *
     * @param port
     *          elemento representando a porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(P port) {
        return ports.contains(port);
    }


    /**
     * Verifica se o switch possui alguma porta.
     *
     * @return
     *          verdadeiro se o switch possuir alguma porta.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Retorna as portas do switch.
     *
     * @return
     *          objeto Iterable contendo as portas do switch.
     */
    public Iterable<P> getPorts() {
        return ports;
    }


    /**
     * Determina o componente padrão do switch.
     *
     * @param defaultComponent
     *          elemento representando o componente padrão.
     */
    public void setDefaultComponent(N defaultComponent) {
        this.defaultComponent = defaultComponent;
    }


    /**
     * Retorna o componente padrão do switch.
     *
     * @return
     *          elemento representando o componente padrão.
     */
    public N getDefaultComponent() {
        return defaultComponent;
    }


    /**
     * Adiciona um bind ao switch.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see ArrayList#add
     */
    public boolean addBind(B bind) {
        if(bind != null && binds.add(bind)){
            //atribui este como parente do bind
            bind.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um bind do switch.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeBind(B bind) {
        if(binds.remove(bind)){
            //Se bind existe, retira o seu parentesco
            if(bind != null)
                bind.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o switch contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(B bind) {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch possui algum bind.
     *
     * @return
     *          verdadeiro se o switch possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch.
     *
     * @return
     *          objeto Iterable contendo os binds do switch.
     */
    public Iterable<B> getBinds() {
        return binds;
    }


    /**
     * Adiciona um nó ao switch.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addNode(N node) {
        if(nodes.add(node)){
            //Se node existe, atribui este como seu parente
            if(node != null)
                node.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um nó do switch.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(String id) {
        for(N node : nodes){
            if(node.getId().equals(id))
                return removeNode(node);
        }
        return false;
    }


    /**
     * Remove um nó do switch.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(N node) {
        if(nodes.remove(node)){
            //Se node existe, retira o seu parentesco
            if(node != null)
                node.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se o switch possui um nó.
     *
     * @param id
     *          identificador do nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(String id) {
        for(N node : nodes){
            if(node.getId().equals(id))
                return true;
        }
        return false;
    }


    /**
     * Verifica se o switch possui um nó.
     *
     * @param node
     *          elemento representando o nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(N node) {
        return nodes.contains(node);
    }


    /**
     * Verifica se o switch possui algum nó.
     *
     * @return
     *          verdadeiro se o switch possuir algum nó.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Retorna os nós do switch.
     *
     * @return
     *          objeto Iterable contendo os nós do switch.
     */
    public Iterable<N> getNodes() {
        return nodes;
    }

    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<switch";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer() + "'";

        if(hasPort() || hasBind() || hasNode()){
            content += ">\n";

            if(hasPort()){
                for(P port : ports)
                    content += port.parse(ident + 1);
            }

            if(hasBind()){
                for(B bind : binds)
                    content += bind.parse(ident + 1);
            }

            if(getDefaultComponent() != null)
                content += space + "\t" + "<defaultComponent component='" + getDefaultComponent().getId() + "'/>\n";

            if(hasNode()){
                for(N node : nodes)
                    content += node.parse(ident + 1);
            }

            content += space + "</switch>\n";
        }
        else
            content += "/>\n";

        return content;
    }

    public int compareTo(N other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }
        if(getRefer() != null && (getRefer().compareTo(this) == 0 || findSwitch(nodes) != null)){
            addError("Elemento não pode fazer referência a si mesmo ou a um elemento interno.");
            valid = false;
        }
        if(!hasNode() || !hasBind()){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir ao menos um nó e um bindRule.");
            valid = false;
        }

        if(hasPort()){
            for(P port : ports){
                valid &= port.validate();
                addWarning(port.getWarnings());
                addError(port.getErrors());
            }
        }
        if(hasBind()){
            for(B bind : binds){
                valid &= bind.validate();
                addWarning(bind.getWarnings());
                addError(bind.getErrors());
            }
        }
        if(hasNode()){
            for(N node : nodes){
                valid &= node.validate();
                addWarning(node.getWarnings());
                addError(node.getErrors());
            }
        }

        if(getDefaultComponent() != null){
            if(!(getDefaultComponent().getParent() instanceof NCLSwitch)){
                addError("Atributo component do elemento defaultComponent deve referênciar um nó contido no switch.");
                valid = false;
            }
            else if(((NCLSwitch) getDefaultComponent().getParent()).compareTo(this) != 0){
                addError("Atributo component do elemento defaultComponent deve referênciar um nó contido no switch.");
                valid = false;
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("switch") && !insideSwitch){
                cleanWarnings();
                cleanErrors();
                insideSwitch = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("refer"))
                        setRefer((S) new NCLSwitch(attributes.getValue(i)));//cast retirado na correcao das referencias
                }
            }
            else if(localName.equals("bindRule")){
                B child = createBindRule();
                child.startElement(uri, localName, qName, attributes);
                addBind(child);
            }
            else if(localName.equals("defaultComponent")){
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("component"))
                        setDefaultComponent((N) new NCLContext(attributes.getValue(i)));//cast retirado na correcao das referencias
                }
            }
            else if(localName.equals("switchPort")){
                P child = createSwitchPort();
                child.startElement(uri, localName, qName, attributes);
                addPort(child);
            }
            else if(localName.equals("media")){
                N child = createMedia();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("context")){
                N child = createContext();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
            else if(localName.equals("switch") && insideSwitch){
                N child = createSwitch();
                child.startElement(uri, localName, qName, attributes);
                addNode(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if(localName.equals("switch"))
            super.endElement(uri, localName, qName);
    }


    @Override
    public void endDocument() {
        if(getDefaultComponent() != null)
            defaultComponentReference();
        if(getParent() != null && getRefer() != null)
                switchReference();

        if(hasBind()){
            for(B bind : binds){
                bind.endDocument();
                addWarning(bind.getWarnings());
                addError(bind.getErrors());
            }
        }
        if(hasPort()){
            for(P port : ports){
                port.endDocument();
                addWarning(port.getWarnings());
                addError(port.getErrors());
            }
        }
        if(hasNode()){
            for(N node : nodes){
                node.endDocument();
                addWarning(node.getWarnings());
                addError(node.getErrors());
            }
        }
    }


    private void defaultComponentReference() {
        //Search for a component node in its parent
        for(N node : nodes){
            if(node.getId().equals(getDefaultComponent().getId())){
                setDefaultComponent(node);
                return;
            }
        }

        addWarning("Could not find node in switch with id: " + getDefaultComponent().getId());
    }


    private void switchReference() {
        //Search for the interface inside the node
        NCLElement body = getParent();

        while(!(body instanceof NCLBody)){
            body = body.getParent();
            if(body == null){
                addWarning("Could not find a body");
                return;
            }
        }

        setRefer(findSwitch(((NCLBody) body).getNodes()));
    }


    private S findSwitch(Iterable<N> nodes) {
        for(N n : nodes){
            if(n instanceof NCLContext){
                if( ((NCLContext) n).hasNode()){
                    Iterable<N> cnodes = ((NCLContext) n).getNodes();
                        S s = findSwitch(cnodes);
                        if(s != null)
                            return (S) s;
                }
            }
            else if(n instanceof NCLSwitch){
                if(n.getId().equals(getRefer().getId()))
                    return (S) n;
                else if( ((NCLSwitch) n).hasNode()){
                    Iterable<N> snodes = ((NCLSwitch) n).getNodes();
                    S s = findSwitch(snodes);
                    if(s != null)
                        return (S) s;
                }
            }
        }

        addWarning("Could not find switch with id: " + getRefer().getId());
        return null;
    }


    /**
     * Função de criação do elemento filho <i>bindRule</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>bindRule</i>.
     */
    protected B createBindRule() {
        return (B) new NCLBindRule(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>switchPort</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>switchPort</i>.
     */
    protected P createSwitchPort() {
        return (P) new NCLSwitchPort(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>media</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>media</i>.
     */
    protected N createMedia() {
        return (N) new NCLMedia(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>context</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>context</i>.
     */
    protected N createContext() {
        return (N) new NCLContext(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>switch</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>switch</i>.
     */
    protected N createSwitch() {
        return (N) new NCLSwitch(getReader(), this);
    }
}
