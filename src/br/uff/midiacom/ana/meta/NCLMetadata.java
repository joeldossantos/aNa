package br.uff.midiacom.ana.meta;

import br.uff.midiacom.ana.NCLElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>metadata</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma árvore RDF de metadados para o documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
