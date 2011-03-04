package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLValues.NCLDefaultActionRole;
import br.pensario.NCLValues.NCLDefaultConditionRole;


/**
 * Esta classe define um papel de conector da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public class NCLRole {

    private String name;
    private NCLDefaultConditionRole cname;
    private NCLDefaultActionRole aname;

    private NCLElement parent;
    
    
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
        if(name != null && "".equals(name.trim()))
            throw new IllegalArgumentException("Empty name String");

        
        if(name != null){
            for(NCLDefaultConditionRole role : NCLDefaultConditionRole.values()){
                if(name.equals(role.toString())){
                    setName(role);
                    return;
                }
            }
            for(NCLDefaultActionRole role : NCLDefaultActionRole.values()){
                if(name.equals(role.toString())){
                    setName(role);
                    return;
                }
            }
        }

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
     * Retorna o nome do papel. Retorna a String que representa um papel padrao
     * caso o nome do papel tenha sido determinado desta forma.
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
     * Retorna o nome do papel caso este tenha sido determinado como uma condicao padrao.
     *
     * @return
     *          NCLDefaultConditionRole representando o nome do papel
     */
    public NCLDefaultConditionRole getConditionName() {
        return cname;
    }


    /**
     * Retorna o nome do papel caso este tenha sido determinado como uma acao padrao.
     *
     * @return
     *          NCLDefaultActionRole representando o nome do papel
     */
    public NCLDefaultActionRole getActionName() {
        return aname;
    }


    /**
     * Atribui um elemento pai ao papel.
     *
     * @param parent
     *          elemento NCL representando o elemento pai.
     * @return
     *          verdadeiro se o elemento pai foi atribuido. Caso o papel já possua um elemento pai, o retorno será falso.
     */
    public boolean setParent(NCLElement parent) {
        if(this.parent != null && parent != null)
            return false;

        this.parent = parent;
        return true;
    }


    /**
     * Retorna o elemento pai do papel.
     *
     * @return
     *          elemento NCL representando o elemento pai.
     */
    public NCLElement getParent() {
        return parent;
    }
}
