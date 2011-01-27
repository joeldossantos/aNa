package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLDefaultValueAssessment;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define de uma assertiva de um conector de um documento NCL.<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public class NCLValueAssessment<V extends NCLValueAssessment, P extends NCLConnectorParam> extends NCLElement implements Comparable<V> {

    private String value;
    private NCLDefaultValueAssessment defValue;
    private P parValue;
    

    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLValueAssessment() {}


    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLValueAssessment(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param value
     *          String contendo o valor da assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public NCLValueAssessment(String value) throws IllegalArgumentException {
        setValue(value);
    }
    

    /**
     * Determina o valor da assertiva do conector.
     *
     * @param value
     *          String contendo o valor da assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value) throws IllegalArgumentException {
        if(value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
        this.defValue = null;
        this.parValue = null;
    }


    /**
     * Determina o valor da assertiva do conector.
     *
     * @param value
     *          parâmetro contendo o valor da assertiva.
     */
    public void setValue(P value) {
        this.parValue = value;
        this.value = null;
        this.defValue = null;
    }


    /**
     * Determina o valor da assertiva do conector. Usa um dos valores padrão.
     *
     * @param value
     *          elemento representando o valor da assertiva.
     */
    public void setValue(NCLDefaultValueAssessment value) {
        this.defValue = value;
        this.value = null;
        this.parValue = null;
    }
    

    /**
     * Retorna o valor da assetiva do conector.
     *
     * @return
     *          String contendo o valor da assertiva.
     */
    public String getValue() {
        if(defValue != null)
            return defValue.toString();
        else
            return value;
    }


    /**
     * Retorna o valor da assetiva do conector.
     *
     * @return
     *          elemento representando o valor da assertiva.
     */
    public NCLDefaultValueAssessment getDefaultValue() {
        return defValue;
    }


    /**
     * Retorna o valor da assetiva do conector.
     *
     * @return
     *          parâmetro representando o valor da assertiva.
     */
    public P getParamValue() {
        return parValue;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<valueAssessment";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        if(getParamValue() != null)
            content += " value='$" + getParamValue().getId() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(V other) {
        int comp = 0;

        String this_stat, other_stat;

        if(getValue() == null) this_stat = ""; else this_stat = getValue();
        if(other.getValue() == null) other_stat = ""; else other_stat = other.getValue();
        comp = this_stat.compareTo(other_stat);

        if(comp == 0){
            if(getParamValue() == null && other.getParamValue() == null)
                comp = 0;
            else if(getParamValue() != null && other.getParamValue() != null)
                comp = getParamValue().compareTo(other.getParamValue());
            else
                comp = 1;
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        if(getValue() == null && getParamValue() == null){
            addError("Elemento não possui atributo obrigatório value.");
            return false;
        }

        return true;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("value")){
                    String var = attributes.getValue(i);
                    if(var.contains("$")){
                        var = var.substring(1);
                        setValue((P) new NCLConnectorParam(var));//TODO: precisa retirar cast?
                    }
                    else{
                        setValue(var);
                        //Try to find the value in one of the standard values
                        for(NCLDefaultValueAssessment v : NCLDefaultValueAssessment.values()){
                            if(v.toString().equals(var))
                                setValue(v);
                        }
                    }
                }
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

        if(getParamValue() != null)
            setValue(parameterReference(getParamValue().getId()));
    }


    private P parameterReference(String id) {
        NCLElement connector = getParent();

        while(!(connector instanceof NCLCausalConnector)){
            connector = connector.getParent();
            if(connector == null){
                addWarning("Could not find a parent connector");
                return null;
            }
        }

        Iterable<P> params = ((NCLCausalConnector) connector).getConnectorParams();
        for(P param : params){
            if(param.getId().equals(id))
                return param;
        }

        addWarning("Could not find connectorParam in connector with id: " + id);
        return null;
    }
}
