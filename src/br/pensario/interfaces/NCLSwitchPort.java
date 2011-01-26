package br.pensario.interfaces;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma porta de um switch da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLSwitchPort<I extends NCLInterface, M extends NCLMapping> extends NCLIdentifiableElement implements NCLInterface<I> {

    private Set<M> mappings = new TreeSet<M>();


    /**
     * Construtor do elemento <i>switchPort</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da porta de switch.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador for inválido.
     */
    public NCLSwitchPort(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>switchPort</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLSwitchPort(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Adiciona um mapeamento a porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser adicionado.
     * @return
     *          Verdadeiro se o mapeamento foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMapping(M mapping) {
        if(mappings.add(mapping)){
            //Se mapping existe, atribui este como seu parente
            if(mapping != null)
                mapping.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove um mapeamento da porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser removido.
     * @return
     *          Verdadeiro se o mapeamento foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMapping(M mapping) {
        if(mappings.remove(mapping)){
            //Se mapping existe, retira o seu parentesco
            if(mapping != null)
                mapping.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se a porta de switch possui um mapeamento.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser verificado.
     * @return
     *          verdadeiro se o mapeamento existir.
     */
    public boolean hasMapping(M mapping) {
        return mappings.contains(mapping);
    }


    /**
     * Verifica se a porta de switch possui algum mapeamento.
     *
     * @return
     *          verdadeiro se a porta de switch possuir algum mapeamento.
     */
    public boolean hasMapping() {
        return !mappings.isEmpty();
    }


    /**
     * Retorna os mapeamentos da porta de switch.
     *
     * @return
     *          objeto Iterable contendo os mapeamentos da porta de switch.
     */
    public Iterable<M> getMappings() {
        return mappings;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // <port> element and attributes declaration
        content = space + "<switchPort";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasMapping()){
            for(M mapping : mappings)
                content += mapping.parse(ident + 1);
        }

        content += "</switchPort>\n";

        return content;
    }


    public int compareTo(I other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        valid &= hasMapping();

        if(hasMapping()){
            for(M mapping : mappings)
                valid &= mapping.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            if(localName.equals("switchPort")){
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("mapping")){
                M child = createMapping();
                child.startElement(uri, localName, qName, attributes);
                addMapping(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasMapping()){
            for(M mapping : mappings){
                mapping.endDocument();
                addWarning(mapping.getWarnings());
                addError(mapping.getErrors());
            }
        }
    }


    /**
     * Função de criação do elemento filho <i>mapping</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>mapping</i>.
     */
    protected M createMapping() {
        return (M) new NCLMapping(getReader(), this);
    }
}
