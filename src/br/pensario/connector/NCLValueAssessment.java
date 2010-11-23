package br.pensario.connector;


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
public class NCLValueAssessment {

    private String value;
    

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
        if (value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        this.value = value;
    }
    

    /**
     * Retorna o valor da assetiva do conector.
     *
     * @return
     *          String contendo o valor da assertiva.
     */
    public String getValue() {
        return value;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<valueAssessment";

        content += " value='" + getValue() + "'";        
        
        content += ">\n";

        return content;
    }
    
}
