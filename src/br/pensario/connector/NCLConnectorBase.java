package br.pensario.connector;

import br.pensario.NCLIdentifiableElement;
import java.util.Set;
import java.util.TreeSet;


public class NCLConnectorBase extends NCLIdentifiableElement {

    Set<NCLCausalConnector> connectors = new TreeSet<NCLCausalConnector>();
    
    
    /**
     * Adiciona um conector causal NCL à base de conectores.
     * 
     * @param connector NCLCausalConnector Conector a ser adicionado 
     * @return Se a adição foi realizada
     */
    public boolean addCausalConnector(NCLCausalConnector connector)
    {
        return connectors.add(connector);        
    }
    
    
    /**
     * Remove um conector causal NCL da base de conectores.
     * 
     * @param connector NCLCausalConnector Conector a ser removido 
     * @return Se a remoção foi realizada
     */    
    public boolean removeCausalConnector(NCLCausalConnector connector)
    {
        return connectors.remove(connector);        
    }
    
    
    /**
     * Busca um determinado conector de acordo com o id
     * @param id String Id do conector a ser buscado
     * @return O próprio conector caso exista na base
     */
    public NCLCausalConnector getCausalConnector(String id)
    {
        NCLCausalConnector rconn = null;
        
        for(NCLCausalConnector cconn : connectors)
            if(cconn.getId().equals(id)) rconn = cconn;
        
        return rconn;
    }
    
    
    /**
     * Busca um conector causal NCL na base de conectores.
     * 
     * @param connector NCLCausalConnector Conector a ser buscado 
     * @return Se o conector existe na base
     */
    public boolean hasCausalConnector(NCLCausalConnector connector)
    {
        return connectors.contains(connector);        
    }
    
    
    /**
     * Indica se a base possui ao menos um conector.
     * 
     * @return Se a base possui conectores
     */
    public boolean hasCausalConnector()
    {
        return connectors.size() > 0;        
    }

    
    /**
     * Retorna os conectores da base.
     * 
     * @return Conjunto iterável de conectores
     */
    public Iterable<NCLCausalConnector> getCausalConnectors()
    {
        return connectors;        
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

        content = space + "<connectorBase ";

        if (getId() != null)
            content += " id='" + getId() + "'";

        content += ">\n";

        if (hasCausalConnector())

            for (NCLCausalConnector connector: getCausalConnectors())
                content += connector.parse(ident + 1);

        content += space + "<connectorBase/>\n";

        return content;
    }

    
    public String toString() {
        return parse(0);
    }
    
}
