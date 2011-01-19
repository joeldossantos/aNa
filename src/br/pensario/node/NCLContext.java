package br.pensario.node;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.interfaces.NCLProperty;
import br.pensario.interfaces.NCLPort;
import br.pensario.link.NCLLink;
import br.pensario.meta.NCLMeta;
import br.pensario.meta.NCLMetadata;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>context</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um contexto de um documento NCL.<br>
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
public class NCLContext<C extends NCLContext, Pt extends NCLPort, Pp extends NCLProperty, N extends NCLNode, L extends NCLLink, M extends NCLMeta, MT extends NCLMetadata>
        extends NCLIdentifiableElement implements NCLNode<N> {

    private C refer;
    private Set<Pt> ports = new TreeSet<Pt>();
    private Set<Pp> properties = new TreeSet<Pp>();
    private Set<N> nodes = new TreeSet<N>();
    private Set<L> links = new TreeSet<L>();
    private Set<M> metas = new TreeSet<M>();
    private Set<MT> metadatas = new TreeSet<MT>();

    private boolean insideContext;
    
    
    /**
     * Construtor do elemento <i>context</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do contexto.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do contexto for inválido.
     */
    public NCLContext(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>context</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLContext(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideContext = false;
    }


    /**
     * Atribui um contexto para ser reutilizado pelo contexto.
     *
     * @param refer
     *          elemento representando o contexto a ser reutilizado.
     */
    public void setRefer(C refer) {
        this.refer = refer;
    }


    /**
     * Retorna o contexto reutilizado pelo contexto.
     *
     * @return
     *          elemento representando o contexto a ser reutilizado.
     */
    public C getRefer() {
        return refer;
    }
    
    
    /**
     * Adiciona uma porta ao contexto.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addPort(Pt port) {
        return ports.add(port);
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(String id) {
        for(Pt port : ports){
            if(port.getId().equals(id))
                return ports.remove(port);
        }
        return false;
    }


    /**
     * Remove uma porta do contexto.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(Pt port) {
        return ports.remove(port);
    }


    /**
     * Verifica se o contexto possui uma porta.
     *
     * @param id
     *          identificador da porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(String id) {
        for(Pt port : ports){
            if(port.getId().equals(id))
                return true;
        }
        return false;
    }


    /**
     * Verifica se o contexto possui uma porta.
     *
     * @param port
     *          elemento representando a porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(Pt port) {
        return ports.contains(port);
    }


    /**
     * Verifica se o contexto possui alguma porta.
     *
     * @return
     *          verdadeiro se o contexto possuir alguma porta.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Retorna as portas do contexto.
     *
     * @return
     *          objeto Iterable contendo as portas do contexto.
     */
    public Iterable<Pt> getPorts() {
        return ports;
    }


    /**
     * Adiciona uma propriedade ao contexto.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addProperty(Pp property) {
        return properties.add(property);
    }


    /**
     * Remove uma propriedade do contexto.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(String name) {
        for(Pp property : properties){
            if(property.getId().equals(name))
                return properties.remove(property);
        }
        return false;
    }


    /**
     * Remove uma propriedade do contexto.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(Pp property) {
        return properties.remove(property);
    }


    /**
     * Verifica se o contexto possui uma propriedade.
     *
     * @param name
     *          nome da propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(String name) {
        for(Pp property : properties){
            if(property.getId().equals(name))
                return true;
        }
        return false;
    }


    /**
     * Verifica se o contexto possui uma propriedade.
     *
     * @param property
     *          elemento representando a propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(Pp property) {
        return properties.contains(property);
    }


    /**
     * Verifica se o contexto possui alguma propriedade.
     *
     * @return
     *          verdadeiro se o contexto possuir alguma propriedade.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Retorna as propriedades do contexto.
     *
     * @return
     *          objeto Iterable contendo as propriedades do contexto.
     */
    public Iterable<Pp> getProperties() {
        return properties;
    }


    /**
     * Adiciona um nó ao contexto.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addNode(N node) {
        return nodes.add(node);
    }


    /**
     * Remove um nó do contexto.
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
                return nodes.remove(node);
        }
        return false;
    }


    /**
     * Remove um nó do contexto.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(N node) {
        return nodes.remove(node);
    }


    /**
     * Verifica se o contexto possui um nó.
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
     * Verifica se o contexto possui um nó.
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
     * Verifica se o contexto possui algum nó.
     *
     * @return
     *          verdadeiro se o contexto possuir algum nó.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Retorna os nós do contexto.
     *
     * @return
     *          objeto Iterable contendo os nós do contexto.
     */
    public Iterable<N> getNodes() {
        return nodes;
    }


    /**
     * Adiciona um link ao contexto.
     *
     * @param link
     *          elemento representando o link a ser adicionado.
     * @return
     *          Verdadeiro se o link foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLink(L link) {
        return links.add(link);
    }


    /**
     * Remove um link do contexto.
     *
     * @param link
     *          elemento representando o link a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLink(L link) {
        return links.remove(link);
    }


    /**
     * Verifica se o contexto possui um link.
     *
     * @param link
     *          elemento representando o link a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasLink(L link) {
        return links.contains(link);
    }


    /**
     * Verifica se o contexto possui algum link.
     *
     * @return
     *          verdadeiro se o contexto possuir algum link.
     */
    public boolean hasLink() {
        return !links.isEmpty();
    }


    /**
     * Retorna os links do contexto.
     *
     * @return
     *          objeto Iterable contendo os links do contexto.
     */
    public Iterable<L> getLinks() {
        return links;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMeta(M meta) {
        return metas.add(meta);
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param meta
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMeta(M meta) {
        return metas.remove(meta);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param meta
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMeta(M meta) {
        return metas.contains(meta);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui algum metadado.
     *
     * @return
     *          verdadeiro se o corpo do cabeçalho NCL possuir algum metadado.
     */
    public boolean hasMeta() {
        return !metas.isEmpty();
    }


    /**
     * Retorna os metadados do cabeçalho do documento NCL.
     *
     * @return
     *          objeto Iterable contendo os metadados do cabeçalho do documento NCL.
     */
    public Iterable<M> getMetas() {
        return metas;
    }


    /**
     * Adiciona um metadado ao cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser adicionado.
     * @return
     *          Verdadeiro se o metadado foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMetadata(MT metadata) {
        return metadatas.add(metadata);
    }


    /**
     * Remove um metadado do cabeçalho do documento NCL.
     *
     * @param metadata
     *          elemento representando o metadado a ser removido.
     * @return
     *          Verdadeiro se o link foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMetadata(MT metadata) {
        return metadatas.remove(metadata);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui um metadado.
     *
     * @param metadata
     *          elemento representando o metadado a ser verificado.
     * @return
     *          verdadeiro se o link existir.
     */
    public boolean hasMetadata(MT metadata) {
        return metadatas.contains(metadata);
    }


    /**
     * Verifica se o cabeçalho do documento NCL possui algum metadado.
     *
     * @return
     *          verdadeiro se o corpo do cabeçalho NCL possuir algum metadado.
     */
    public boolean hasMetadata() {
        return !metadatas.isEmpty();
    }


    /**
     * Retorna os metadados do cabeçalho do documento NCL.
     *
     * @return
     *          objeto Iterable contendo os metadados do cabeçalho do documento NCL.
     */
    public Iterable<MT> getMetadatas() {
        return metadatas;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <context> element and attributes declaration
        content = space + "<context";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer().getId() + "'";
        
        // Test if the media has content
        if(hasMeta() || hasMetadata() || hasPort() || hasProperty() || hasNode() || hasLink()){
            content += ">\n";

            if(hasMeta()){
                for(M meta : metas)
                    content += meta.parse(ident + 1);
            }
            if(hasMetadata()){
                for(MT metadata : metadatas)
                    content += metadata.parse(ident + 1);
            }
            if(hasPort()){
                for(Pt port : ports)
                    content += port.parse(ident + 1);
            }
            if(hasProperty()){
                for(Pp property : properties)
                    content += property.parse(ident + 1);
            }
            if(hasNode()){
                for(N node : nodes)
                    content += node.parse(ident + 1);
            }
            if(hasLink()){
                for(L link : links)
                    content += link.parse(ident + 1);
            }
            
            // <context> element end declaration
            content += space + "</context>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }


    public int compareTo(N other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        if(getRefer() != null)
            valid &= (getRefer().compareTo(this) != 0);

        if(hasMeta()){
            for(M meta : metas)
                valid &= meta.validate();
        }
        if(hasMetadata()){
            for(MT metadata : metadatas)
                valid &= metadata.validate();
        }
        if(hasPort()){
            for(Pt port : ports)
                valid &= port.validate();
        }
        if(hasProperty()){
            for(Pp property : properties)
                valid &= property.validate();
        }
        if(hasNode()){
            for(N node : nodes)
                valid &= node.validate();
        }
        if(hasLink()){
            for(L link : links)
                valid &= link.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("context") && !insideContext){
                insideContext = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("refer"))
                        setRefer((C) new NCLContext(attributes.getValue(i)));//FIXME: reparar a referência ao contexto correto
                }
            }
            else if(localName.equals("meta")){
                NCLMeta m = new NCLMeta(getReader(), this);
                m.startElement(uri, localName, qName, attributes);
                addMeta((M) m); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("metadata")){
                NCLMetadata m = new NCLMetadata(getReader(), this);
                m.startElement(uri, localName, qName, attributes);
                addMetadata((MT) m); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("port")){
                NCLPort p = new NCLPort(getReader(), this);
                p.startElement(uri, localName, qName, attributes);
                addPort((Pt) p); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("property")){
                NCLProperty p = new NCLProperty(getReader(), this);
                p.startElement(uri, localName, qName, attributes);
                addProperty((Pp) p); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("media")){
                NCLMedia m = new NCLMedia(getReader(), this);
                m.startElement(uri, localName, qName, attributes);
                addNode((N) m); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("context") && insideContext){
                NCLContext c = new NCLContext(getReader(), this);
                c.startElement(uri, localName, qName, attributes);
                addNode((N) c); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("switch")){
                NCLSwitch s = new NCLSwitch(getReader(), this);
                s.startElement(uri, localName, qName, attributes);
                addNode((N) s); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("link")){
                NCLLink l = new NCLLink(getReader(), this);
                l.startElement(uri, localName, qName, attributes);
                addLink((L) l); //TODO: retirar o cast. Como melhorar isso?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            //TODO: fazer o que?
        }
    }
}
