package br.pensario.connector;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conector de um documento NCL.<br>
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
public class NCLCausalConnector extends NCLIdentifiableElement implements Comparable<NCLCausalConnector> {

    private NCLCondition condition;
    private NCLAction action;
    private Set<NCLConnectorParam> conn_params = new TreeSet();


    /**
     * Construtor do elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do conector.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          caso o identificador seja inválido.
     */    
    public NCLCausalConnector(String id) throws NCLInvalidIdentifierException {
        this.setId(id);
    }
    
    
    /**
     * Atribui uma condição ao conector causal.
     * 
     * @param condition
     *          elemento representando uma condição do conector.
     */    
    public void setCondition(NCLCondition condition) {
        this.condition = condition;
    }
    
    
    /**
     * Retorna a condição atribuida ao conector causal.
     * 
     * @return
     *          elemento representando uma condição do conector.
     */    
    public NCLCondition getCondition() {
        return condition;
    }


    /**
     * Atribui uma ação ao conector causal.
     * 
     * @param action
     *          elemento representando uma ação do conector.
     */    
    public void setAction(NCLAction action) {
        this.action = action;
    }
    
    
    /**
     * Retorna a ação atribuida ao conector causal.
     *
     * @return
     *          elemento representando uma ação do conector.
     */
    public NCLAction getAction() {
        return action;
    }

    
    /**
     * Adiciona um parâmetro ao conector causal NCL.     
     * 
     * @param name
     *          nome do parâmetro a ser adicionado ao conector.
     * @param type
     *          tipo do parâmetro a ser adicionado ao conector.
     * @return
     *          verdadeiro se o parâmetro for adicionado.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o nome do parâmetro for inválido.
     *         java.lang.IllegalArgumentException
     *          se a String do tipo for vazia.
     *
     * @see TreeSet#add
     */    
    public boolean addConnectorParam(String name, String type) throws NCLInvalidIdentifierException {
        NCLConnectorParam param = new NCLConnectorParam(name, type);
        
        return conn_params.add(param);
    }

    
    /**
     * Remove um parâmetro do conector causal.
     * 
     * @param name
     *          nome do parâmetro a ser removido do conector.
     * @return
     *          verdadeiro se o parâmetro for removido.
     */    
    public boolean removeConnectorParam(String name) {
        for (NCLConnectorParam connp : conn_params){
            if (connp.getName().equals(name))
                return conn_params.remove(connp);
        }

        return false;
    }


    /**
     * Verifica se o conector possui um parâmetro.
     *
     * @param name
     *          nome do parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasAttributeAssessment(String name) {
        for (NCLConnectorParam connp : conn_params){
            if (connp.getName().equals(name))
                return true;
        }

        return false;
    }


    /**
     * Verifica se o conector possui pelo menos um parâmetro.
     *
     * @return
     *          verdadeiro se o conector possuir pelo menos um parâmetro.
     */
    public boolean hasAttributeAssessment() {
        return !conn_params.isEmpty();
    }


    /**
     * Retorna os parâmetros do conector.
     *
     * @return
     *          objeto Iterable contendo os parâmetros do conector.
     */
    public Iterable<NCLConnectorParam> getAttributeAssessments() {
        return conn_params;
    }

    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<causalConnector";
        content += " id='" + getId() + "'";
        content += ">\n";

        for(NCLConnectorParam connp : conn_params)
            content += connp.parse(ident + 1);
        
        content += getCondition().parse(ident + 1);
        content += getAction().parse(ident + 1);

        content += space + "<causalConnector/>\n";

        return content;
    }

    
    public int compareTo(NCLCausalConnector other) {
        return getId().compareTo(other.getId());
    }

}
