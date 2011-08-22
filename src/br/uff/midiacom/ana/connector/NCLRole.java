/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana.connector;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.datatype.NCLDefaultActionRole;
import br.uff.midiacom.ana.datatype.NCLDefaultConditionRole;


/**
 * Esta classe define um papel de conector da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
