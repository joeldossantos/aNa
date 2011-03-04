package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLValues.NCLAttributes;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>descriptorParam</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um parametro de descritor em um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLDescriptorParam<P extends NCLDescriptorParam> extends NCLElement implements Comparable<P> {

    private NCLAttributes name;
    private String value;


    /**
     * Construtor do elemento <i>descriptorParam</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLDescriptorParam() {}


    /**
     * Construtor do elemento <i>descriptorParam</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLDescriptorParam(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um nome ao parâmetro. Segue os nomes padronizados de atributos do descritor.
     *
     * @param name
     *          Elemento representando o nome do parâmetro.
     */
    public void setName(NCLAttributes name) {
        this.name = name;
    }


    /**
     * Retorna o nome do parâmetro.
     *
     * @return
     *          elemento representando o nome do parâmetro.
     */
    public NCLAttributes getName() {
        return name;
    }


    /**
     * Atribui um valor ao parâmetro.
     *
     * @param value
     *          String representando o valor do parâmetro.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws IllegalArgumentException {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
    }


    /**
     * Retorna o valor do parâmetro.
     *
     * @return
     *          String representando o valor do parâmetro.
     */
    public String getValue() {
        return value;
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
        content = space + "<descriptorParam";
        if(getName() != null)
            content += " name='" + getName().toString() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(P other) {
        return getName().compareTo(other.getName());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getName() == null){
            addError("Elemento não possui atributo obrigatório name");
            valid = false;
        }
        if(getValue() == null){
            addError("Elemento não possui atributo obrigatório value");
            valid = false;
        }
        //@todo: validar o valor com relacao ao nome

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        cleanWarnings();
        cleanErrors();
        for(int i = 0; i < attributes.getLength(); i++){
            if(attributes.getLocalName(i).equals("name")){
                    for(NCLAttributes a : NCLAttributes.values()){
                        if(a.toString().equals(attributes.getValue(i)))
                            setName(a);
                    }
                }
            else if(attributes.getLocalName(i).equals("value"))
                setValue(attributes.getValue(i));
        }
    }
}
