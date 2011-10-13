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

import br.uff.midiacom.ana.datatype.TimeType;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.interfaces.*;
import br.uff.midiacom.ana.NCLBody;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLInstanceType;
import br.uff.midiacom.ana.datatype.enums.NCLMediaType;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.datatype.enums.NCLUriType;
import br.uff.midiacom.ana.descriptor.NCLLayoutDescriptor;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conteúdo de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLMedia<A extends NCLArea, P extends NCLProperty, N extends NCLNode, D extends NCLLayoutDescriptor, M extends NCLMedia>
        extends NCLIdentifiableElement implements NCLNode<N> {

    private String src;
    private NCLMimeType type;
    private D descriptor;
    private M refer;
    private NCLInstanceType instance;
    
    private Set<A> areas = new TreeSet<A>();
    private Set<P> properties = new TreeSet<P>();
    
    
    /**
     * Construtor do elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do elemento media.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da media for inválido.
     */
    public NCLMedia(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLMedia(XMLReader reader, NCLElementImpl parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
    
    
    /**
     * Atribui a URI do conteúdo da mídia.
     * 
     * @param src
     *          String contendo a URI do conteúdo da mídia.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida.
     *
     * @see java.net.URI
     */
    public void setSrc(String src) throws URISyntaxException {
        if(src != null)
            this.src = new URI(src).toString();

        notifyAltered(NCLElementAttributes.SRC, this.src, src);
        this.src = src;
    }
    
    
    /**
     * Atribui a URI do conteúdo da mídia usando uma URI padrão.
     * Mídias do tipo settings não tem src.
     * 
     * @param type
     *          tipo da URI.
     * @param src
     *          String contendo a URI do conteúdo da mídia.
     * @throws java.net.URISyntaxException
     *          se a URI não for válida.
     *         java.lang.IllegalArgumentException
     *          se a mídia for do tipo settings.
     *
     * @see java.net.URI
     */
    public void setSrc(NCLUriType type, String src) throws URISyntaxException, IllegalArgumentException {
        if(getType() == NCLMimeType.APPLICATION_X_GINGA_SETTINGS)
            throw new IllegalArgumentException("This media don't have src");
        
        String src_type = "";
        if(type != null)
            src_type = type.toString();
        
        setSrc(src_type + src);
    }
    
    
    /**
     * Atribui a URI do conteúdo da mídia do tipo Time.
     * Nesse caso o conteúdo da mídia será um identificador de tempo no formato UTC.
     * 
     * @param time
     *          indicador de tempo no formato UTC.
     * @throws java.lang.IllegalArgumentException
     *          se o indicador não estiver no formato correto ou se a
     *          mídia não é do tipo Time.
     *
     * @see TimeType#isUTC()
     */
    public void setSrc(TimeType time) throws IllegalArgumentException {
        if(!time.isUTC() || getType() != NCLMimeType.APPLICATION_X_GINGA_TIME)
            throw new IllegalArgumentException("Invalid src");

        notifyAltered(NCLElementAttributes.SRC, this.src, src);
        this.src = time.toString();
    }
    
    
    /**
     * Retorna o valor da URI do conteúdo da mídia.
     * 
     * @return
     *          String contendo a URI do conteúdo da mídia.
     *
     * @see NCLMedia#setSrc(java.lang.String)
     * @see NCLMedia#setSrc(br.pensario.datatype.NCLUriType, java.lang.String)
     * @see NCLMedia#setSrc(br.pensario.interfaces.TimeType)
     */
    public String getSrc() {
        return src;
    }
    
    
    /**
     * Determina o tipo da mídia.
     * O tipo da mídia será um dos tipos padronizados na norma.
     * 
     * @param type
     *          tipo da mídia.
     */
    public void setType(NCLMimeType type) {
        notifyAltered(NCLElementAttributes.TYPE, this.type, type);
        this.type = type;
    }
    
    
    /**
     * Retorna o tipo da mídia.
     * 
     * @return
     *          tipo da mídia.
     */
    public NCLMimeType getType() {
        return type;
    }
    
    
    /**
     * Determina o descritor da mídia.
     * 
     * @param descriptor
     *          elemento representando o descritor da mídia.
     */
    public void setDescriptor(D descriptor) {
        notifyAltered(NCLElementAttributes.DESCRIPTOR, this.descriptor, descriptor);
        this.descriptor = descriptor;
    }
    
    
    /**
     * Retorna o descritor da mídia.
     * 
     * @return
     *          elemento representando o descritor da mídia.
     */
    public D getDescriptor() {
        return descriptor;
    }


    /**
     * Atribui uma media para ser reutilizada pela media.
     *
     * @param refer
     *          elemento representando a media a ser reutilizado.
     */
    public void setRefer(M refer) {
        notifyAltered(NCLElementAttributes.REFER, this.refer, refer);
        this.refer = refer;
    }


    /**
     * Retorna a media reutilizada pela media.
     *
     * @return
     *          elemento representando a media a ser reutilizado.
     */
    public M getRefer() {
        return refer;
    }


    /**
     * Determina o tipo de instância de outra media essa media é.
     *
     * @param instance
     *          elemento representando o tipo de instancia.
     */
    public void setInstance(NCLInstanceType instance) {
        notifyAltered(NCLElementAttributes.INSTANCE, this.instance, instance);
        this.instance = instance;
    }


    /**
     * Retorna o tipo de instância de outra media essa media é.
     *
     * @return
     *          elemento representando o tipo de instancia.
     */
    public NCLInstanceType getInstance() {
        return instance;
    }
    
    
    /**
     * Adiciona uma âncora a mídia.
     * 
     * @param area
     *          elemento representando a âncora a ser adicionada.
     * @return
     *          Verdadeiro se a âncora foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addArea(A area) {
        if(areas.add(area)){
            //Se area existe, atribui este como seu parente
            if(area != null)
                area.setParent(this);

            notifyInserted(NCLElementSets.AREAS, area);
            return true;
        }
        return false;
    }
    
    
    /**
     * Remove uma âncora da mídia.
     *
     * @param id
     *          identificador da âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeArea(String id) {
        for(A area : areas){
            if(area.getId().equals(id))
                return removeArea(area);
        }
        return false;
    }
    
    
    /**
     * Remove uma âncora a mídia.
     *
     * @param area
     *          elemento representando a âncora a ser removida.
     * @return
     *          Verdadeiro se a âncora foi removida.
     *
     * @see TreeSet#add
     */
    public boolean removeArea(A area) {
        if(areas.remove(area)){
            //Se area existe, retira o seu parentesco
            if(area != null)
                area.setParent(null);

            notifyRemoved(NCLElementSets.AREAS, area);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifica se a mídia possui uma âncora.
     * 
     * @param id
     *          identificador da âncora a ser verificada.
     * @return
     *          verdadeiro se a âncora existir.
     */
    public boolean hasArea(String id) {
        for(A area : areas){
            if(area.getId().equals(id))
                return true;
        }
        return false;
    }
    
    
    /**
     * Verifica se a mídia possui uma âncora.
     * 
     * @param area
     *          elemento representando a âncora a ser verificada.
     * @return
     *          verdadeiro se a âncora existir.
     */
    public boolean hasArea(A area) {
        return areas.contains(area);
    }
    
    
    /**
     * Verifica se a mídia possui alguma âncora.
     * 
     * @return
     *          verdadeiro se a mídia possuir alguma âncora.
     */
    public boolean hasArea() {
        return !areas.isEmpty();
    }
    
    
    /**
     *  Retorna as âncoras da mídia.
     *
     * @return
     *          lista contendo as âncoras da mídia.
     */
    public Set<A> getAreas() {
        return areas;
    }
    
    
    /**
     * Adiciona uma propriedade a mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser adicionada.
     * @return
     *          Verdadeiro se a propriedade foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addProperty(P property) {
        if(properties.add(property)){
            //Se property existe, atribui este como seu parente
            if(property != null)
                property.setParent(this);

            notifyInserted(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Remove uma propriedade da mídia.
     *
     * @param name
     *          nome da propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(String name) {
        for(P property : properties){
            if(property.getId().equals(name))
                return removeProperty(property);
        }
        return false;
    }


    /**
     * Remove uma propriedade da mídia.
     *
     * @param property
     *          elemento representando a propriedade a ser removida.
     * @return
     *          Verdadeiro se a propriedade foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeProperty(P property) {
        if(properties.remove(property)){
            //Se property existe, retira o seu parentesco
            if(property != null)
                property.setParent(null);

            notifyRemoved(NCLElementSets.PROPERTIES, property);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a mídia possui uma propriedade.
     *
     * @param name
     *          nome da propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(String name) {
        for(P property : properties){
            if(property.getId().equals(name))
                return true;
        }
        return false;
    }


    /**
     * Verifica se a mídia possui uma propriedade.
     *
     * @param property
     *          elemento representando a propriedade a ser verificada.
     * @return
     *          verdadeiro se a propriedade existir.
     */
    public boolean hasProperty(P property) {
        return properties.contains(property);
    }


    /**
     * Verifica se a mídia possui alguma propriedade.
     *
     * @return
     *          verdadeiro se a mídia possuir alguma propriedade.
     */
    public boolean hasProperty() {
        return !properties.isEmpty();
    }


    /**
     * Retorna as propriedades da mídia.
     *
     * @return
     *          lista contendo as propriedades da mídia.
     */
    public Set<P> getProperties() {
        return properties;
    }


    /**
     * Retorna o tipo da mídia de acordo com seu tipo especificado ou sua URL.
     *
     * @return
     *          elemento representando o tipo da mídia.
     */
    public NCLMediaType getMediaType() {
        if(getType() != null){
            boolean status = false;

            status |= (getType() == NCLMimeType.APPLICATION_X_GINGA_NCLET);
            status |= (getType() == NCLMimeType.APPLICATION_X_GINGA_NCLUA);
            if(status)
                return NCLMediaType.PROCEDURAL;

            status |= (getType() == NCLMimeType.AUDIO_BASIC);
            status |= (getType() == NCLMimeType.AUDIO_MP2);
            status |= (getType() == NCLMimeType.AUDIO_MP3);
            status |= (getType() == NCLMimeType.AUDIO_MPEG);
            status |= (getType() == NCLMimeType.AUDIO_MPEG4);
            if(status)
                return NCLMediaType.AUDIO;

            status |= (getType() == NCLMimeType.IMAGE_BMP);
            status |= (getType() == NCLMimeType.IMAGE_GIF);
            status |= (getType() == NCLMimeType.IMAGE_JPEG);
            status |= (getType() == NCLMimeType.IMAGE_PNG);
            if(status)
                return NCLMediaType.IMAGE;

            status |= (getType() == NCLMimeType.VIDEO_MPEG);
            if(status)
                return NCLMediaType.VIDEO;

            status |= (getType() == NCLMimeType.TEXT_CSS);
            status |= (getType() == NCLMimeType.TEXT_HTML);
            status |= (getType() == NCLMimeType.TEXT_PLAIN);
            status |= (getType() == NCLMimeType.TEXT_XML);
            if(status)
                return NCLMediaType.TEXT;
        }

        if(getSrc() != null){
            boolean status = false;
            String ext = getSrc().substring(getSrc().lastIndexOf("."));

            status |= ext.contentEquals(".html");
            status |= ext.contentEquals(".html");
            status |= ext.contentEquals(".xhtml");
            status |= ext.contentEquals(".css");
            status |= ext.contentEquals(".xml");
            status |= ext.contentEquals(".txt");
            if(status)
                return NCLMediaType.TEXT;

            status |= ext.contentEquals(".bmp");
            status |= ext.contentEquals(".png");
            status |= ext.contentEquals(".gif");
            status |= ext.contentEquals(".jpg");
            status |= ext.contentEquals(".jpeg");
            status |= ext.contentEquals(".jpe");
            if(status)
                return NCLMediaType.IMAGE;

            status |= ext.contentEquals(".ua");
            status |= ext.contentEquals(".wav");
            status |= ext.contentEquals(".mp1");
            status |= ext.contentEquals(".mp2");
            status |= ext.contentEquals(".mp3");
            status |= ext.contentEquals(".mp4");
            status |= ext.contentEquals(".mpg4");
            if(status)
                return NCLMediaType.AUDIO;

            status |= ext.contentEquals(".mpeg");
            status |= ext.contentEquals(".mpg");
            status |= ext.contentEquals(".mpe");
            status |= ext.contentEquals(".mng");
            status |= ext.contentEquals(".qt");
            status |= ext.contentEquals(".mov");
            status |= ext.contentEquals(".avi");
            if(status)
                return NCLMediaType.VIDEO;

            status |= ext.contentEquals(".class");
            status |= ext.contentEquals(".xlet");
            status |= ext.contentEquals(".xlt");
            status |= ext.contentEquals(".lua");
            if(status)
                return NCLMediaType.PROCEDURAL;
        }

        return NCLMediaType.OTHER;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <media> element and attributes declaration
        content = space + "<media";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getSrc() != null)
            content += " src='" + getSrc() + "'";
        if(getType() != null)
            content += " type='" + getType().toString() + "'";
        if(getDescriptor() != null)
            content += " descriptor='" + getDescriptor().getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer().getId() + "'";
        if(getInstance() != null)
            content += " instance='" + getInstance().toString() + "'";
        
        // Test if the media has content
        if(hasArea() || hasProperty()){
            content += ">\n";
            
            if(hasArea()){
                for(A area : areas)
                    content += area.parse(ident + 1);
            }
            if(hasProperty()){
                for(P prop : properties)
                    content += prop.parse(ident + 1);
            }
            
            content += space + "</media>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }


    public int compareTo(N other) {
        return getId().compareTo(other.getId());
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("media")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("src")){
                        try{
                            setSrc(attributes.getValue(i));
                        }
                        catch(URISyntaxException ex){
                            setSrc(new TimeType(attributes.getValue(i)));
                        }
                    }
                    else if(attributes.getLocalName(i).equals("type")){
                        for(NCLMimeType m : NCLMimeType.values()){
                            if(m.toString().equals(attributes.getValue(i)))
                                setType(m);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("descriptor"))
                        setDescriptor((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("refer"))
                        setRefer((M) new NCLMedia(attributes.getValue(i)));//cast retirado na correcao das referencias
                    else if(attributes.getLocalName(i).equals("instance")){
                        for(NCLInstanceType in : NCLInstanceType.values()){
                            if(in.toString().equals(attributes.getValue(i)))
                                setInstance(in);
                        }
                    }
                }
            }
            else if(localName.equals("area")){
                A child = createArea();
                child.startElement(uri, localName, qName, attributes);
                addArea(child);
            }
            else if(localName.equals("property")){
                P child = createProperty();
                child.startElement(uri, localName, qName, attributes);
                addProperty(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getDescriptor() != null)
                descriptorReference();
            if(getRefer() != null)
                mediaReference();
        }

        if(hasArea()){
            for(A area : areas){
                area.endDocument();
                addWarning(area.getWarnings());
                addError(area.getErrors());
            }
        }
        if(hasProperty()){
            for(P property : properties){
                property.endDocument();
                addWarning(property.getWarnings());
                addError(property.getErrors());
            }
        }
    }


    private Set<D> getDescriptors() {
        NCLElementImpl root = getParent();

        while(!(root instanceof NCLDoc)){
            root = root.getParent();
            if(root == null){
                addWarning("Could not find root element");
                return null;
            }
        }

        if(((NCLDoc) root).getHead() == null){
            addWarning("Could not find a head");
            return null;
        }
        if(((NCLDoc) root).getHead().getDescriptorBase() == null){
            addWarning("Could not find a descriptorBase");
            return null;
        }

        return ((NCLDoc) root).getHead().getDescriptorBase().getDescriptors();
    }


    private void descriptorReference() {
        //Search for the interface inside the node
        Set<D> descriptors = getDescriptors();
        for(D desc : descriptors){
            if(desc.getId().equals(getDescriptor().getId())){
                setDescriptor(desc);
                return;
            }
        }
        //@todo: descritores internos a switch de descritores podem ser utilizados?

        addWarning("Could not find descriptor in descriptorBase with id: " + getDescriptor().getId());
    }


    private void mediaReference() {
        //Search for the interface inside the node
        NCLElementImpl body = getParent();

        while(!(body instanceof NCLBody)){
            body = body.getParent();
            if(body == null){
                addWarning("Could not find a body");
                return;
            }
        }

        setRefer(findMedia(((NCLBody) body).getNodes()));
    }


    private M findMedia(Set<N> nodes) {
        for(N n : nodes){
            if(n instanceof NCLMedia){
                if(n.getId().equals(getRefer().getId()))
                    return (M) n;
            }
            else if(n instanceof NCLContext){
                if( ((NCLContext) n).hasNode()){
                    Set<N> cnodes = ((NCLContext) n).getNodes();
                    M m = findMedia(cnodes);
                    if(m != null)
                        return (M) m;
                }
            }
            else if(n instanceof NCLSwitch){
                if( ((NCLSwitch) n).hasNode()){
                    Set<N> snodes = ((NCLSwitch) n).getNodes();
                    M m = findMedia(snodes);
                    if(m != null)
                        return (M) m;
                }
            }
        }

        addWarning("Could not find media with id: " + getRefer().getId());
        return null;
    }


    /**
     * Função de criação do elemento filho <i>area</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>area</i>.
     */
    protected A createArea() {
        return (A) new NCLArea(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>property</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>property</i>.
     */
    protected P createProperty() {
        return (P) new NCLProperty(getReader(), this);
    }
}
