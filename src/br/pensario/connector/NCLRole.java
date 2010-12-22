package br.pensario.connector;

import br.pensario.NCLValues.NCLDefaultActionRole;
import br.pensario.NCLValues.NCLDefaultConditionRole;


/**
 * Esta classe define um papel de conector da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLRole {

    private String name;
    private NCLDefaultConditionRole cname;
    private NCLDefaultActionRole aname;
    
    
    /**
     * Construtor do papel.
     * 
     * @param name
     *          String com o nome do papel.
     * @throws java.lang.IllegalArgumentException
     *          Se o nome a ser atribuído for uma String vazia.
     */
    public NCLRole(String name) throws IllegalArgumentException {
        setName(name);
    }


    /**
     * Construtor do papel.
     *
     * @param name
     *          elemento representando o nome do papel.
     */
    public NCLRole(NCLDefaultConditionRole name) {
        setName(name);
    }


    /**
     * Construtor do papel.
     *
     * @param name
     *          elemento representando o nome do papel.
     */
    public NCLRole(NCLDefaultActionRole name) {
        setName(name);
    }


    /**
     * Determina o nome do papel
     *
     * @param name
     *          String com o nome do papel.
     * @throws java.lang.IllegalArgumentException
     *          Se o nome a ser atribuído for uma String vazia.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name != null && "".equals(name.trim()))
            throw new IllegalArgumentException("Empty name String");

        this.name = name;
        this.cname = null;
        this.aname = null;
    }


    /**
     * Determina o nome do papel seguindo um dos nomes de condição padrões.
     *
     * @param name
     *          elemento representando o nome do papel.
     */
    public void setName(NCLDefaultConditionRole name) {
        this.cname = name;
        this.name = null;
        this.aname = null;
    }


    /**
     * Determina o nome do papel seguindo um dos nomes de ação padrões.
     *
     * @param name
     *          elemento representando o nome do papel.
     */
    public void setName(NCLDefaultActionRole name) {
        aname = name;
        this.name = null;
        this.cname= null;
    }
    
    
    /**
     * Retorna o nome do papel
     *
     * @return
     *          String com o nome do papel.
     */
    public String getName() {
        if(cname != null)
            return cname.toString();
        if(aname != null)
            return aname.toString();
        else
            return name;
    }


    /**
     * Retorna o nome do papel
     *
     * @return
     *          NCLDefaultConditionRole representando o nome do papel
     */
    public NCLDefaultConditionRole getConditionName() {
        return cname;
    }


    /**
     * Retorna o nome do papel
     *
     * @return
     *          NCLDefaultActionRole representando o nome do papel
     */
    public NCLDefaultActionRole getActionName() {
        return aname;
    }
}
