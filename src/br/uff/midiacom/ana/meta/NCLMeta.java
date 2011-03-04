package br.uff.midiacom.ana.meta;

import br.uff.midiacom.ana.NCLElement;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um metadado para o documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLMeta<M extends NCLMeta> extends NCLElement implements Comparable<M> {

    private String name;
    private String mcontent;


    /**
     * Construtor do elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLMeta() {}


    /**
     * Construtor do elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLMeta(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um nome ao metadado.
     *
     * @param name
     *          String representando o nome do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name != null && "".equals(name.trim()))
            throw new IllegalArgumentException("Empty String");

        this.name = name;
    }


    /**
     * Retorna o nome do metadado.
     *
     * @return
     *          String representando o nome do metadado.
     */
    public String getName() {
        return name;
    }


    /**
     * Atribui um conteúdo ao metadado.
     *
     * @param content
     *          String representando o conteúdo do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setContent(String content) throws IllegalArgumentException {
        if (content != null && "".equals(content.trim()))
            throw new IllegalArgumentException("Empty String");

        this.mcontent = content;
    }


    /**
     * Retorna o conteúdo do metadado.
     * 
     * @return
     *          String representando o conteúdo do metadado.
     */
    public String getContent() {
        return mcontent;
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
        content = space + "<meta";
        if(getName() != null)
            content += " name='" + getName() + "'";
        if(getContent() != null)
            content += " content='" + getContent() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(M other) {
        return getName().compareTo(other.getName());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getName() == null){
            addError("Elemento não possui atributo obrigatório name.");
            valid = false;
        }
        if(getContent() == null){
            addError("Elemento não possui atributo obrigatório content.");
            valid = false;
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        cleanWarnings();
        cleanErrors();
        for(int i = 0; i < attributes.getLength(); i++){
            if(attributes.getLocalName(i).equals("name"))
                setName(attributes.getValue(i));
            else if(attributes.getLocalName(i).equals("content"))
                setContent(attributes.getValue(i));
        }
    }
}
