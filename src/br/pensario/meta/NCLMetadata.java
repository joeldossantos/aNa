package br.pensario.meta;

import br.pensario.NCLElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>metadata</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma árvore RDF de metadados para o documento NCL.<br>
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
public class NCLMetadata<M extends NCLMetadata> extends NCLElement implements Comparable<M> {

    private String rdfTree;


    /**
     * Construtor do elemento <i>metadata</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLMetadata() {}


    /**
     * Construtor do elemento <i>metadata</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLMetadata(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Determina a árvore RDF de metadados do elemento metadata.
     *
     * @param rdfTree
     *          String representando a árvore RDF.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setRDFTree(String rdfTree) throws IllegalArgumentException {
        if (rdfTree != null && "".equals(rdfTree.trim()))
            throw new IllegalArgumentException("Empty String");

        this.rdfTree = rdfTree;
    }


    /**
     * Retorna a árvore RDF de metadados do elemento metadata.
     *
     * @return
     *          String representando a árvore RDF.
     */
    public String getRDFTree() {
        return rdfTree;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<metadata>\n";
        if(getRDFTree() != null)
            content += getRDFTree() + "\n";
        content += space + "</metadata>\n";

        return content;
    }


    public int compareTo(M other) {
        return getRDFTree().compareTo(other.getRDFTree());
    }

    
    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        if(getRDFTree() == null){
            addWarning("Elemento vazio.");
            return false;
        }

        return true;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        cleanWarnings();
        cleanErrors();
    }
//TODO: o parser vai encarar o RDF como xml também, tem que recuprerar de outra forma

    @Override
    public void characters(char[] ch, int start, int length) {
        setRDFTree(new String(ch, start, length));
    }
}
