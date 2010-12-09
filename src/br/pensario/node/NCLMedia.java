package br.pensario.node;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLMimeType;
import br.pensario.NCLValues.NCLUriType;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>media</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conteúdo de um documento NCL.<br>
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
public class NCLMedia<A extends NCLArea, P extends NCLProperty, N extends NCLNode, D extends NCLDescriptor>
        extends NCLIdentifiableElement implements NCLNode<N> {

    private String src;
    private NCLMimeType type;
    private D descriptor;
    
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
        if (src != null)
            this.src = new URI(src).toString();
        
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
        if (getType() == NCLMimeType.APPLICATION_X_GINGA_SETTINGS)
            throw new IllegalArgumentException("This media don't have src");
        
        String src_type = "";
        if (type != null)
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
     * @see NCLTime#isUTC()
     */
    public void setSrc(NCLTime time) throws IllegalArgumentException {
        if (!time.isUTC() || getType() != NCLMimeType.APPLICATION_X_GINGA_TIME)
            throw new IllegalArgumentException("Invalid src");

        this.src = time.toString();
    }
    
    
    /**
     * Retorna o valor da URI do conteúdo da mídia.
     * 
     * @return
     *          String contendo a URI do conteúdo da mídia.
     *
     * @see NCLMedia#setSrc(java.lang.String)
     * @see NCLMedia#setSrc(br.pensario.NCLValues.NCLUriType, java.lang.String)
     * @see NCLMedia#setSrc(br.pensario.interfaces.NCLTime)
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
        return areas.add(area);
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
        for (A area : areas){
            if (area.getId().equals(id))
                return areas.remove(area);
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
        return areas.remove(area);
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
        for (A area : areas){
            if (area.getId().equals(id))
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
     *          objeto Iterable contendo as âncoras da mídia.
     */
    public Iterable<A> getAreas() {
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
        return properties.add(property);
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
        for (P property : properties){
            if (property.getId().equals(name))
                return properties.remove(property);
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
        return properties.remove(property);
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
        for (P property : properties){
            if (property.getId().equals(name))
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
     *          objeto Iterable contendo as propriedades da mídia.
     */
    public Iterable<P> getProperties() {
        return properties;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <media> element and attributes declaration
        content = space + "<media";
        content += " id='" + getId() + "'";
        if (getSrc() != null)
            content += " src='" + getSrc() + "'";
        if (getType() != null)
            content += " type='" + getType().toString() + "'";
        if (getDescriptor() != null)
            content += " descriptor='" + getDescriptor().getId() + "'";
        
        // Test if the media has content
        if (hasArea() || hasProperty()){
            content += ">\n";
            
            if (hasArea()){
                for (A area : areas)
                    content += area.parse(ident + 1);
            }
            
            if (hasProperty()){
                for (P prop : properties)
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
}
