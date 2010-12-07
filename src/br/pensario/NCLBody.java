package br.pensario;

import br.pensario.node.NCLNode;
import br.pensario.interfaces.NCLPort;
import br.pensario.interfaces.NCLProperty;
import br.pensario.link.NCLLink;

import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>body</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define o corpo de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLBody extends NCLIdentifiableElement {

    private Set<NCLPort> ports = new TreeSet();
    private Set<NCLProperty> properties = new TreeSet();
    private Set<NCLNode> nodes = new TreeSet();
    private Set<NCLLink> links = new TreeSet();    


    /**
     * Adiciona uma porta ao corpo do documento NCL.
     * 
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addPort(NCLPort port) {
        return ports.add(port);
    }
    
    
    /**
     * Remove uma porta do corpo do documento NCL.
     * 
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(String id) {
        for (NCLPort port : ports){
            if (port.getId().equals(id))
                return ports.remove(port);
        }
        return false;
    }
    
    
    /**
     * Remove uma porta do corpo do documento NCL.
     * 
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(NCLPort port) {
        return ports.remove(port);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui uma porta.
     * 
     * @param id
     *          identificador da porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(String id) {
        for (NCLPort port : ports){
            if (port.getId().equals(id))
                return true;
        }
        return false;
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui uma porta.
     *
     * @param port
     *          elemento representando a porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(NCLPort port) {
        return ports.contains(port);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui alguma porta.
     * 
     * @return
     *          verdadeiro se o corpo do documento NCL possuir alguma porta.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }
    
    
    /**
     * Retorna as portas do corpo do documento NCL.
     * 
     * @return
     *          objeto Iterable contendo as portas do corpo do documento NCL.
     */
    public Iterable<NCLPort> getPorts() {
        return ports;
    }
    
    
    /**
     * Adiciona uma propriedade ao corpo do documento NCL.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addProperty(NCLProperty property) {
        return properties.add(property);
    }
    
    
    /**
     * Remove uma propriedade do corpo do documento NCL.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(String name) {
        for (NCLProperty property : properties){
            if (property.getId().equals(name))
                return properties.remove(property);
        }
        return false;
    }
    
    
    /**
     * Remove uma propriedade do corpo do documento NCL.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(NCLProperty property) {
        return properties.remove(property);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui uma propriedade.
     *
     * @param name
     *          nome da propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(String name) {
        for (NCLProperty property : properties){
            if (property.getId().equals(name))
                return true;
        }
        return false;
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui uma propriedade.
     *
     * @param property
     *          elemento representando a propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(NCLProperty property) {
        return properties.contains(property);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui alguma propriedade.
     *
     * @return
     *          verdadeiro se o corpo do documento NCL possuir alguma propriedade.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }
    
    
    /**
     * Retorna as propriedades do corpo do documento NCL.
     *
     * @return
     *          objeto Iterable contendo as propriedades do corpo do documento NCL.
     */
    public Iterable<NCLProperty> getProperties() {
        return properties;
    }
    
    
    /**
     * Adiciona um nó ao corpo do documento NCL.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addNode(NCLNode node) {
        return nodes.add(node);
    }
    
    
    /**
     * Remove um nó do corpo do documento NCL.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(String id) {
        for (NCLNode node : nodes){
            if (node.getId().equals(id))
                return nodes.remove(node);
        }
        return false;
    }
    
    
    /**
     * Remove um nó do corpo do documento NCL.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(NCLNode node) {
        return nodes.remove(node);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui um nó.
     *
     * @param id
     *          identificador do nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(String id) {
        for (NCLNode node : nodes){
            if (node.getId().equals(id))
                return true;
        }
        return false;
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui um nó.
     *
     * @param node
     *          elemento representando o nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(NCLNode node) {
        return nodes.contains(node);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui algum nó.
     *
     * @return
     *          verdadeiro se o corpo do documento NCL possuir algum nó.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }
    
    
    /**
     * Retorna os nós do corpo do documento NCL.
     *
     * @return
     *          objeto Iterable contendo os nós do corpo do documento NCL.
     */
    public Iterable<NCLNode> getNodes() {
        return nodes;
    }
    
    
    /**
     * Adiciona um link ao corpo do documento NCL.
     *
     * @param link
     *          elemento representando o link a ser adicionado.
     * @return
     *          Verdadeiro se o link foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLink(NCLLink link) {
        return links.add(link);
    }
    
    
    /**
     * Remove um link do corpo do documento NCL.
     *
     * @param link
     *          elemento representando o link a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLink(NCLLink link) {
        return links.remove(link);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui um link.
     *
     * @param link
     *          elemento representando o link a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasLink(NCLLink link) {
        return links.contains(link);
    }
    
    
    /**
     * Verifica se o corpo do documento NCL possui algum link.
     * 
     * @return
     *          verdadeiro se o corpo do documento NCL possuir algum link.
     */
    public boolean hasLink() {
        return !links.isEmpty();
    }
    
    
    /**
     * Retorna os links do corpo do documento NCL.
     * 
     * @return
     *          objeto Iterable contendo os links do corpo do documento NCL.
     */
    public Iterable<NCLLink> getLinks() {
        return links;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";        
        
        // <body> element and attributes declaration
        content = space + "<body";
        if (getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";
        
        
        // <body> element content
        if (hasPort()){
            for (NCLPort port : ports)
                content += port.parse(ident + 1);
        }
        
        if (hasProperty()){
            for (NCLProperty property : properties)
                content += property.parse(ident + 1);
        }
        
        if (hasNode()){
            for (NCLNode node : nodes)
                content += node.parse(ident + 1);
        }
        
        if (hasLink()){
            for (NCLLink link : links)
                content += link.parse(ident + 1);
        }
        
        
        // <body> element end declaration
        content += space + "</body>\n";
        
        return content;
    }
    
}
