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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class NCLCompoundActionPrototype<T extends NCLCompoundActionPrototype, P extends NCLElement, I extends XMLElementImpl, Ea extends NCLAction, Ep extends NCLConnectorParamPrototype>
        extends XMLElementPrototype<Ea, P, I> implements NCLAction<Ea, P, Ep> {

    protected NCLActionOperator operator;
    protected DoubleParamType<Ep> delay;
    protected ElementList<Ea, T> actions;


    /**
     * Construtor do elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundActionPrototype() throws XMLException {
        super();
        actions = new ElementList<Ea, T>();
    }
    
    
    /**
     * Determina o operador da ação composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLActionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a ação composta.
     *
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLActionOperator getOperator() {
        return operator;
    }

    
    /**
     * Adiciona uma ação a ação composta.
     *
     * @param action
     *          elemento representando a ação a ser adicionada
     * @return
     *          verdadeiro se a ação foi adicionada.
     *
     * @see ArrayList#add(java.lang.Object)
     */
    public boolean addAction(Ea action) throws XMLException {
        return actions.add(action, (T) this);
    }


    /**
     * Remove uma ação a ação composta.
     *
     * @param action
     *          elemento representando a ação a ser removida
     * @return
     *          verdadeiro se a ação foi removida.
     *
     * @see ArrayList#remove(java.lang.Object)
     */
    public boolean removeAction(Ea action) throws XMLException {
        return actions.remove(action);
    }


    /**
     * Verifica se a ação composta possui uma ação.
     *
     * @param action
     *          elemento representando a ação a ser verificada
     * @return
     *          verdadeiro se a ação existe.
     */
    public boolean hasAction(Ea action) throws XMLException {
        return actions.contains(action);
    }


    /**
     * Verifica se a ação composta possui alguma ação.
     *
     * @return
     *          verdadeiro se a ação composta possui alguma ação.
     */
    public boolean hasAction() {
        return !actions.isEmpty();
    }


    /**
     * Retorna as ações da ação composta.
     *
     * @return
     *          lista contendo as ações da ação composta.
     */
    public ElementList<Ea, T> getActions() {
        return actions;
    }


    public void setDelay(DoubleParamType<Ep> delay) {
        this.delay = delay;
    }


    public DoubleParamType<Ep> getDelay() {
        return delay;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundAction";
        if(getOperator() != null)
            content += " operator='" + getOperator() + "'";
        if(getDelay() != null){
            content += " delay='" + getDelay().parse();
            if(getDelay().getValue() != null)
                content += "s'";
            else
                content += "'";
        }
        content += ">\n";

        if(hasAction()){
            for(Ea action : actions)
                content += action.parse(ident + 1);
        }

        content += space + "</compoundAction>\n";

        return content;
    }


    public boolean compare(Ea other) {
        boolean comp = true;

        String this_act, other_act;
        NCLCompoundActionPrototype other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundActionPrototype))
            return false;

        other_comp = (NCLCompoundActionPrototype) other;
        
        // Compara pelo operador
        if(getOperator() == null) this_act = ""; else this_act = getOperator().toString();
        if(other_comp.getOperator() == null) other_act = ""; else other_act = other_comp.getOperator().toString();
        comp = this_act.equals(other_act);

        // Compara pelo delay
        if(getDelay() == null) this_act = ""; else this_act = getDelay().parse();
        if(other_comp.getDelay() == null) other_act = ""; else other_act = other_comp.getDelay().parse();
        comp &= this_act.equals(other_act);

        // Compara o número de acoes
        comp &= actions.size() == other_comp.getActions().size();

        // Compara as acoes
        Iterator it = other_comp.getActions().iterator();
        for(NCLAction a : actions){
            NCLAction other_a = (NCLAction) it.next();
            comp &= a.compare(other_a);
            if(comp)
                break;
        }

        
        return comp;
    }
}
