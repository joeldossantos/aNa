package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.reuse.NCLImport;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>connectorBase</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma base de conectores de um documento NCL.<br>
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
public class NCLConnectorBase<C extends NCLCausalConnector, I extends NCLImport> extends NCLIdentifiableElement {

    private Set<C> connectors = new TreeSet<C>();
    private Set<I> imports = new TreeSet<I>();
    

    /**
     * Construtor do elemento <i>connectorBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLConnectorBase() {}


    /**
     * Construtor do elemento <i>connectorBase</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLConnectorBase(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Adiciona um conector a base de conectores.
     * 
     * @param connector
     *          elemento representando o conector a ser adicionado.
     * @return
     *          verdadeiro se o conector foi adicionado.
     *
     * @see TreeSet#add(java.lang.Object)
     */
    public boolean addCausalConnector(C connector) {
        return connectors.add(connector);        
    }
    
    
    /**
     * Remove um conector da base de conectores.
     * 
     * @param connector
     *          elemento representando o conector a ser removido.
     * @return
     *          verdadeiro se o conector foi removido.
     *
     * @see TreeSet#remove(java.lang.Object)
     */    
    public boolean removeCausalConnector(C connector) {
        return connectors.remove(connector);        
    }
    
    
    /**
     * Verifica se a base de conectores possui um conector.
     * 
     * @param connector
     *          elemento representando o conector a ser verificado.
     * @return
     *          verdadeiro se o conector existir.
     */
    public boolean hasCausalConnector(C connector) {
        return connectors.contains(connector);        
    }
    
    
    /**
     * Verifica se a base de conectores possui pelo menos um conector.
     * 
     * @return
     *          verdadeiro se a base de conectores possui pelo menos um conector.
     */
    public boolean hasCausalConnector() {
        return !connectors.isEmpty();
    }

    
    /**
     * Retorna os conectores da base de conectores.
     * 
     * @return
     *          objeto Iterable contendo os conectores da base de conectores.
     */
    public Iterable<C> getCausalConnectors() {
        return connectors;        
    }


    /**
     * Adiciona um importador de base à base de conectores.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(I importBase) {
        return imports.add(importBase);
    }


    /**
     * Remove um importador de base da base de conectores.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(I importBase) {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de conectores contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de conectores possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de conectores possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de conectores.
     *
     * @return
     *          objeto Iterable contendo os importadores de base da base de conectores.
     */
    public Iterable<I> getImportBases() {
        return imports;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<connectorBase";
        if(getId() != null)
            content += " id='" + getId() + "'";

        if(hasImportBase() || hasCausalConnector()){
            content += ">\n";

            if(hasImportBase()){
                for(I imp : imports)
                    content += imp.parse(ident + 1);
            }

            if(hasCausalConnector()){
                for(C connector: connectors)
                    content += connector.parse(ident + 1);
            }

            content += space + "</connectorBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public boolean validate() {
        boolean valid = true;

        if(hasImportBase()){
            for(I imp : imports)
                valid &= imp.validate();
        }
        if(hasCausalConnector()){
            for(C connector: connectors)
                valid &= connector.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("connectorBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("importBase")){
                NCLImport i = new NCLImport(NCLImportType.BASE, getReader(), this);
                i.startElement(uri, localName, qName, attributes);
                addImportBase((I) i); //TODO: retirar o cast. Como melhorar isso?
                addWarning(i.getWarnings());
                addError(i.getErrors());
            }
            else if(localName.equals("causalConnector")){
                NCLCausalConnector c = new NCLCausalConnector(getReader(), this);
                c.startElement(uri, localName, qName, attributes);
                addCausalConnector((C) c); //TODO: retirar o cast. Como melhorar isso?
                addWarning(c.getWarnings());
                addError(c.getErrors());
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasImportBase()){
            for(I imp : imports){
                imp.endDocument();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }
        if(hasCausalConnector()){
            for(C connector : connectors){
                connector.endDocument();
                addWarning(connector.getWarnings());
                addError(connector.getErrors());
            }
        }
    }
}
