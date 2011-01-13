package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLValues.NCLDefaultValueAssessment;


/**
 * Esta classe define o elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define de uma assertiva de um conector de um documento NCL.<br>
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
public class NCLValueAssessment<V extends NCLValueAssessment, P extends NCLConnectorParam> extends NCLElement implements Comparable<V> {

    private String value;
    private NCLDefaultValueAssessment defValue;
    private P parValue;
    

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
    public void setValueAssessment(NCLDefaultValueAssessment value) {
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
        return (getValue() != null || getParamValue() != null);
    }
}
