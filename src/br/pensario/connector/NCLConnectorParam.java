package br.pensario.connector;

import br.pensario.InvalidIdentifierException;
import br.pensario.NCLIdentifiable;

public class NCLConnectorParam extends NCLIdentifiable implements Comparable<NCLConnectorParam>{
    
    private String type;
    
    
    /**
     * Construtor padrão
     * 
     * @param name Nome do parâmetro de conector
     */
    public NCLConnectorParam(String name) throws InvalidIdentifierException {
        setName(name);        
    }
    
    
    /**
     * Retorna o nome do parâmetro
     * 
     * @return name Nome do parâmetro
     */
    public String getName() {
        return getIdentification();
    }
    
    
    /**
     * Atribui um novo nome ao parâmetro
     * 
     * @param name Novo nome do parâmetro
     */
    public void setName(String name) throws InvalidIdentifierException {
        setIdentification(name);
    }
    
    
    /**
     * Retorna o tipo do parâmetro
     * 
     * @return Tipo do parâmetro
     */
    public String getType() {
        return type;
    }
    
    
    /**
     * Atribui um novo tipo ao parâmetro
     * 
     * @param type Novo tipo do parâmetro
     */
    public void setType(String type) throws NullPointerException {
        if (type == null)
            throw new NullPointerException("Null type");
        
        this.type = type;
    }
    
    
    /**
     * Verifica se o parâmetro possui um tipo.
     * 
     * @return Verdadeiro se possuir um tipo.
     */
    public boolean hasType() {
        return (type != null);
    }
    
    
    /**
     * Retorna a representação do elemento em XML.
     * @return Trecho XML referente ao elemento
     */
    public String parse(int ident) {

        String space, content;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<connectorParam";
        
        if(getName()!=null)
            content += " name='" + getName() + "'";

        if(getType()!=null)
            content += " type='" + getType() + "'";        
        
        content += " />\n";

        return content;
    }
    
    
    public String toString()    
    {
        return parse(0);        
    }

    
    public int compareTo(NCLConnectorParam arg0) {
        return this.getName().compareTo(arg0.getName());
    }

}
