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
public class NCLCausalConnector<C extends NCLCausalConnector, Co extends NCLCondition, Ac extends NCLAction, P extends NCLConnectorParam>
        extends NCLIdentifiableElement implements Comparable<C> {

    private Co condition;
    private Ac action;
    private Set<P> conn_params = new TreeSet<P>();


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
    public void setCondition(Co condition) {
        this.condition = condition;
    }
    
    
    /**
     * Retorna a condição atribuida ao conector causal.
     * 
     * @return
     *          elemento representando uma condição do conector.
     */    
    public Co getCondition() {
        return condition;
    }


    /**
     * Atribui uma ação ao conector causal.
     * 
     * @param action
     *          elemento representando uma ação do conector.
     */    
    public void setAction(Ac action) {
        this.action = action;
    }
    
    
    /**
     * Retorna a ação atribuida ao conector causal.
     *
     * @return
     *          elemento representando uma ação do conector.
     */
    public Ac getAction() {
        return action;
    }

    
    /**
     * Adiciona um parâmetro ao conector causal NCL.     
     * 
     * @param param
     *          nome do parâmetro a ser adicionado ao conector.
     * @return
     *          verdadeiro se o parâmetro for adicionado.
     *
     * @see TreeSet#add
     */    
    public boolean addConnectorParam(P param) throws NCLInvalidIdentifierException {
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
        for(P connp : conn_params){
            if(connp.getName().equals(name))
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
    public boolean hasConnectorParam(String name) {
        for(P connp : conn_params){
            if(connp.getName().equals(name))
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
    public boolean hasConnectorParam() {
        return !conn_params.isEmpty();
    }


    /**
     * Retorna os parâmetros do conector.
     *
     * @return
     *          objeto Iterable contendo os parâmetros do conector.
     */
    public Iterable<P> getConnectorParams() {
        return conn_params;
    }

    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<causalConnector";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasConnectorParam()){
            for(P connp : conn_params)
                content += connp.parse(ident + 1);
        }
        if(getCondition() != null)
            content += getCondition().parse(ident + 1);
        if(getAction() != null)
            content += getAction().parse(ident + 1);

        content += space + "</causalConnector>\n";

        return content;
    }

    
    public int compareTo(C other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        valid &= (getCondition() != null);
        valid &= (getAction() != null);

        if(getCondition() != null)
            valid &= getCondition().validate();
        if(getAction() != null)
            valid &= getAction().validate();

        if(hasConnectorParam()){
            for(P connp : conn_params)
                valid &= connp.validate();
        }

        return valid;
    }
}
