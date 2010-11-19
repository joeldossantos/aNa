package br.pensario.connector;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;


/**
 * Esta classe define o elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um parâmetro de um conector de um documento NCL.<br>
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
public class NCLConnectorParam extends NCLIdentifiableElement implements Comparable<NCLConnectorParam> {
    
    private String type;
    
    
    /**
     * Construtor do elemento <i>connectorParam</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     */
    public NCLConnectorParam(String name) throws NCLInvalidIdentifierException {
        setName(name);        
    }


    /**
     * Atribui um nome ao parâmetro
     *
     * @param name
     *          String contendo o nome a ser atribuido ao parâmetro.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     */
    public void setName(String name) throws NCLInvalidIdentifierException {
        setId(name);
    }
    
    
    /**
     * Retorna o nome do parâmetro
     * 
     * @return
     *          String contendo o nome atribuido ao parâmetro.
     */
    public String getName() {
        return getId();
    }
    
    
    /**
     * Atribui um tipo ao parâmetro
     *
     * @param type
     *      String contendo o tipo do parâmetro.
     * @throws java.lang.IllegalArgumentException
     *          se a String for vazia.
     */
    public void setType(String type) {
        if (type != null && "".equals(type.trim()))
            throw new IllegalArgumentException("Empty type String");

        this.type = type;
    }


    /**
     * Retorna o tipo do parâmetro
     * 
     * @return
     *      String contendo o tipo do parâmetro.
     */
    public String getType() {
        return type;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<connectorParam";
        
        if(getName() != null)
            content += " name='" + getName() + "'";

        if(getType() != null)
            content += " type='" + getType() + "'";        
        
        content += " />\n";

        return content;
    }

    
    public int compareTo(NCLConnectorParam other) {
        return getName().compareTo(other.getName());
    }

}
