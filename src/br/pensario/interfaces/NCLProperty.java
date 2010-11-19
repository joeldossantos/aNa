package br.pensario.interfaces;

import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLSystemVariable;


/**
 * Esta classe define o elemento <i>property</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma propriedade de um nó.<br>
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
public class NCLProperty extends NCLInterface implements Comparable<NCLProperty> {

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
        if (name != null)
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
        if (value != null && "".equals(value.trim()))
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

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";
        
        // <property> element and attributes declaration
        content = space + "<property";
        content += " name='" + getName() + "'";
        if (getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";
        
        
        return content;
    }
    
    
    public int compareTo(NCLProperty property) {
        return getName().compareTo(property.getName());
    }

}
