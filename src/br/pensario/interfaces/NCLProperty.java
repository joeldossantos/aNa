package br.pensario.interfaces;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLSystemVariable;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma propriedade de um nó.<br>
 *
 *@see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public class NCLProperty<I extends NCLInterface> extends NCLIdentifiableElement implements NCLInterface<I> {

    private String value;
    
    
    /**
     * Construtor do elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public NCLProperty(String name) throws NCLInvalidIdentifierException {
        setName(name);
    }    


    /**
     * Construtor do elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
     * Segue os nomes da variáveis de sistema de NCL.
     * 
     * @param name
     *          NCLSystemVariable contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public NCLProperty(NCLSystemVariable name) throws NCLInvalidIdentifierException {
        setName(name);
    }


    /**
     * Construtor do elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLProperty(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
    
    
    /**
     * Determina o nome da propriedade sem seguir os valores padrão especificados na norma.
     * O nome, entretando pode estar na forma shared.xxx
     * 
     * @param name
     *          String contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public void setName(String name) throws NCLInvalidIdentifierException {
        setId(name);
    }    


    /**
     * Determina o nome de uma propriedade seguindo os valore padrão especificados na norma.
     * 
     * @param name
     *          NCLSystemVariable contendo o nome da propriedade.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome da propriedade não for válido.
     */
    public void setName(NCLSystemVariable name) throws NCLInvalidIdentifierException {
        if(name == null)
            throw new NCLInvalidIdentifierException("Invalid name");

        setId(name.toString());
    }
    
    
    /**
     * Retorna o nome da propriedade.
     * 
     * @return
     *          String contendo o nome da propriedade.
     */
    public String getName() {
        return getId();
    }
    
    
    /**
     * Atribui um valor a propriedade.
     * 
     * @param value
     *          String representando o valor a ser atribuido.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws IllegalArgumentException {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
    }
    
    
    /**
     * Retorna o valor atributo a propriedade.
     * 
     * @return
     *          String representando o valor atribuido.
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
        
        // <property> element and attributes declaration
        content = space + "<property";
        if(getName() != null)
            content += " name='" + getName() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";
        
        
        return content;
    }
    
    
    public int compareTo(I other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getName() == null){
            addError("Elemento não possui atributo obrigatório name.");
            valid = false;
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("name"))
                    setName(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("value"))
                    setValue(attributes.getValue(i));
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }
}
