package br.pensario.link;

import br.pensario.NCLElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLParamInstance;
import br.pensario.connector.NCLConnectorParam;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define um parâmetro interno a um elemento <i>link</i> ou <i>bind</i>
 * da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLParam<P extends NCLParam, C extends NCLConnectorParam> extends NCLElement implements Comparable<P>{

    private C name;
    private String value;

    private NCLParamInstance paramType;
    
    
    /**
     * Construtor do parâmetro interno a um elemento <i>link</i> ou <i>bind</i>.
     * 
     * @param paramType
     *          define se o parâmetro é de um elemento <i>link</i> ou <i>bind</i>.
     *
     * @throws java.lang.NullPointerException
     *          se o tipo for nulo.
     */
    public NCLParam(NCLParamInstance paramType) throws NullPointerException {
        if(paramType == null)
            throw new NullPointerException("Null type");

        this.paramType = paramType;
    }


    /**
     * Construtor do parâmetro interno a um elemento <i>link</i> ou <i>bind</i>.
     *
     * @param paramType
     *          define se o parâmetro é de um elemento <i>link</i> ou <i>bind</i>.
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     *
     * @throws java.lang.NullPointerException
     *          se o tipo for nulo.
     */
    public NCLParam(NCLParamInstance paramType, XMLReader reader, NCLElement parent) throws NullPointerException {
        if(paramType == null)
            throw new NullPointerException("Null type");

        this.paramType = paramType;
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
    
    
    /**
     * Attribui um <i>connectorParam</i> ao parâmetro.
     * 
     * @param connectorParam
     *          elemento representando o parâmetro do conector ao qual este parâmetro se refere.
     */
    public void setName(C connectorParam) {
        this.name = connectorParam;
    }
    
    
    /**
     * Retorna o <i>connectorParam</i> do parâmetro.
     * 
     * @return NCLConnectorParam representando o nome do parâmetro.
     */
    public C getName() {
        return name;
    }
    
    
    /**
     * Determina o valor do atributo value do parâmetro.
     * 
     * @param value
     *          String contendo o valor a ser atribuído ao parâmetro.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value)  throws IllegalArgumentException {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
    }
    
    
    /**
     * Retorna o valor do atributo value do parâmetro.
     * 
     * @return
     *          String contendo o valor atribuído ao parâmetro.
     */
    public String getValue() {
        return value;
    }


    public NCLParamInstance getType() {
        return paramType;
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
        content = space + "<" + paramType.toString();
        if(getName() != null)
            content += " name='" + getName().getName() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";
        
        return content;
    }
    
    
    public int compareTo(P other) {
        return getName().compareTo(other.getName());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getName() != null);
        valid &= (getValue() != null);
        //TODO validar o valor do parâmetro com o tipo definido no conector (?)

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("name"))
                    setName((C) new NCLConnectorParam(attributes.getValue(i)));//TODO: precisa retirar cast?
                else if(attributes.getLocalName(i).equals("value"))
                    setValue(attributes.getValue(i));
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() == null)
            return;

        if(getName() != null)
            nameReference();
    }


    private void nameReference() {
        //Search for the connector parameter inside the connector
        NCLElement link = getParent();

        while(!(link instanceof NCLLink)){
            link = link.getParent();{
                if(link == null){
                    addWarning("Could not find a parent link");
                    return;
                }
            }
        }

        if(((NCLLink) link).getXconnector() == null){
            addWarning("Could not find a connector");
            return;
        }

        Iterable<C> params = ((NCLLink) link).getXconnector().getConnectorParams();

        for(C param : params){
            if(param.getName().equals(getName().getName())){
                setName(param);
                return;
            }
        }

        addWarning("Could not find connectorParam in connector with name: " + getName().getName());
    }
}
