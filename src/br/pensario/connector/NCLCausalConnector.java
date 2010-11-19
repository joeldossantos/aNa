package br.pensario.connector;


import br.pensario.NCLIdentifiableElement;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NCLCausalConnector extends NCLIdentifiableElement implements Comparable<NCLCausalConnector> {

    private NCLCondition condition;
    private NCLAction action;
    private Set<NCLConnectorParam> conn_params = new TreeSet<NCLConnectorParam>();

    /**
     * Construtor padrão de um conector causal.
     * 
     * @param id String Identificador do conector
     * @throws Exception Caso o identificador seja inválido
     */    
    public NCLCausalConnector(String id) throws Exception {
        this.setId(id);
    }
    
    
    /**
     * Atribui uma condição ao conector causal NCL.
     * 
     * @param condition NCLCondition Condição NCL.
     */    
    public void setCondition(NCLCondition condition) {
        this.condition = condition;
    }
    
    
    /**
     * Retorna condição NCL utilizada pelo conector causal NCL.
     * 
     * @return Condição NCL
     */    
    public NCLCondition getCondition() {
        return condition;
    }


    /**
     * Atribui uma ação ao conector causal NCL.
     * 
     * @param action NCLAction Ação NCL.
     */    
    public void setAction(NCLAction action) {
        this.action = action;
    }
    
    
    /**
     * Retorna ação NCL utilizada pelo conector causal NCL.
     * 
     * @return Ação NCL
     */
    public NCLAction getAction() {
        return action;
    }

    
    /**
     * Adiciona um novo parâmetro NCL ao conector causal NCL.     
     * 
     * @param param NCLConnectorParam Parâmetro a ser adicionado.
     */    
    public boolean addConnectorParam(String name) throws Exception {
        NCLConnectorParam param = new NCLConnectorParam(name);
        
        return conn_params.add(param);
    }
    
    
    /**
     * Retorna o parâmetro do conector NCL de acordo com o nome.     
     * 
     * @param nome String Nome do parâmetro a ser buscado.
     * @return Parâmetro do conector
     */    
    public NCLConnectorParam getConnectorParam(String nome) {

        for (NCLConnectorParam connp : conn_params)
            if (connp.getName().equals(nome))
                return connp;

        return null;
    }

    
    /**
     * Remove um parâmetro do conector NCL de acordo com o nome.     
     * 
     * @param nome String Nome do parâmetro a ser removido.
     */    
    public boolean removeConnectorParam(String nome) {

        Iterator<NCLConnectorParam> it = conn_params.iterator();

        while (it.hasNext()) {
            NCLConnectorParam connp = it.next();
            if (connp.getName().equals(nome)){
                it.remove();
                return true;
            }
        }
        return false;
    }

    
    public String toString() {
        return parse(0);
    }

    
    /**
     * Retorna a representação do elemento em XML.
     * 
     * @return Trecho XML referente ao elemento
     */
    public String parse(int ident) {

        String space, content;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<causalConnector";

        if (getId() != null)
            content += " id='" + getId() + "'";

        content += ">\n";

        for(NCLConnectorParam connp : conn_params)
            content += connp.parse(ident + 1);
        
        content += getCondition().parse(ident + 1);
        content += getAction().parse(ident + 1);

        content += space + "<causalConnector/>\n";

        return content;
    }

    
    public int compareTo(NCLCausalConnector cconn) {
        return cconn.getId().compareTo(this.getId());
    }

}
