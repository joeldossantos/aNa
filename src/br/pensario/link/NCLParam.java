package br.pensario.link;

import br.pensario.NCLElement;
import br.pensario.connector.NCLConnectorParam;


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
public class NCLParam extends NCLElement implements Comparable<NCLParam>{

    private NCLConnectorParam name;
    private String value;

    private NCLElement parent;
    
    
    /**
     * Construtor do parâmetro interno a um elemento <i>link</i> ou <i>bind</i>.
     * 
     * @param parent
     *          elemento pai do parâmetro. Deve ser um elemento <i>link</i> ou <i>bind</i>.
     * @throws java.lang.IllegalArgumentException
     *          se o pai a ser atribuído não for um elemento <i>link</i> ou <i>bind</i>.
     */
    public NCLParam(NCLElement parent) throws IllegalArgumentException {
        if ( !(parent instanceof NCLLink) && !(parent instanceof NCLBind) )
            throw new IllegalArgumentException("Invalid parent type");

        this.parent = parent;
    }
    
    
    /**
     * Attribui um <i>connectorParam</i> ao parâmetro.
     * 
     * @param connectorParam
     *          elemento representando o parâmetro do conector ao qual este parâmetro se refere.
     */
    public void setName(NCLConnectorParam connectorParam) {
        this.name = connectorParam;
    }
    
    
    /**
     * Retorna o <i>connectorParam</i> do parâmetro.
     * 
     * @return NCLConnectorParam representando o nome do parâmetro.
     */
    public NCLConnectorParam getName() {
        return name;
    }
    
    
    /**
     * Determina o valor do atributo value do parâmetro.
     * 
     * @param title
     *          String contendo o valor a ser atribuído ao parâmetro.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public void setValue(String value)  throws IllegalArgumentException {
        if (value != null && "".equals(value.trim()))
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
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";
        
        
        // param element and attributes declaration
        if (parent instanceof NCLLink)
            content = space + "<linkParam";
        else
            content = space + "<bindParam";

        content += " name='" + getName().getName() + "'";
        content += " value='" + getValue() + "'";
        content += "/>\n";
        
        return content;
    }
    
    
    public int compareTo(NCLParam other) {
        return getName().getName().compareTo(other.getName().getName());
    }

}
