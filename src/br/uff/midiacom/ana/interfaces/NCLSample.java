package br.uff.midiacom.ana.interfaces;

import br.uff.midiacom.ana.NCLValues.NCLSampleType;


/**
 * Esta classe define uma amostra de um âncora da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLSample {

    private int value;
    private NCLSampleType type;
    
    
    /**
     * Construtor da amostra possuindo um valor e um tipo.
     * 
     * @param value
     *          inteiro contendo o valor da amostra.
     * @param type
     *          elemento representando o tipo da amostra.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma (inteiro positivo).
     *         java.lang.NullPointerException
     *          se o tipo for nulo.
     */
    public NCLSample(int value, NCLSampleType type) throws IllegalArgumentException, NullPointerException {
        setValue(value);
        setType(type);
    }


    /**
     * Construtor da amostra a partir de uma String.
     *
     * @param sample
     *          String representando a amostra.
     */
    public NCLSample(String sample) {
        stringToSample(sample);
    }
    
    
    /**
     * Determina o valor da amostra.
     * 
     * @param value
     *          inteiro representando o valor da amostra. O valor deve ser positivo.
     * @throws java.lang.IllegalArgumentException
     *          se o valor não estiver no formato definido pela norma (inteiro positivo).
     */
    public void setValue(int value) throws IllegalArgumentException {
        if(value < 0)
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
    }
    
    
    /**
     * Retorna o valor da amostra.
     * 
     * @return
     *          inteiro contendo o valor da amostra.
     */
    public int getValue() {
        return value;
    }
    
    
    /**
     * Determina o tipo da amostra.
     * 
     * @param type
     *          elemento representando o tipo da amostra.
     * @throws java.lang.NullPointerException
     *          se o tipo for nulo.
     */
    public void setType(NCLSampleType type) throws NullPointerException {
        if(type == null)
            throw new NullPointerException("Null type value");

        this.type = type;
    }
    
    
    /**
     * Retorna o tipo da amostra.
     * 
     * @return
     *          elemento representando o tipo da amostra.
     */
    public NCLSampleType getType() {
        return type;
    }


    /**
     * Transforma uma String em um objeto NCLSample.
     *
     * @param sample
     *          String representando a amostra.
     */
    public void stringToSample(String sample) {
        String var = null;

        if(sample.contains("s")){
            var = sample.substring(0, sample.length() - 1);
            setType(NCLSampleType.S);
        }
        else if(sample.contains("f")){
            var = sample.substring(0, sample.length() - 1);
            setType(NCLSampleType.F);
        }
        else if(sample.contains("npt")){
            var = sample.substring(0, sample.length() - 3);
            setType(NCLSampleType.NPT);
        }

        if(var != null)
            setValue(new Integer(var));
        else
            setValue((Integer) null);
    }
    
    
    public String toString() {
        switch(type){
        case S:
            return value + "s";
        case F:
            return value + "f";
        case NPT:
            return value + "npt";
        default:
            return null;
        }
    }


    public int compareTo(NCLSample other){
        String this_sample = toString();
        String other_sample = other.toString();

        if(this_sample == null) this_sample = "";
        if(other_sample == null) other_sample = "";

        return this_sample.compareTo(other_sample);
    }
}
