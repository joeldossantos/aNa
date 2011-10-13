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

import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLActionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma ação composta de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCompoundAction<A extends NCLAction, P extends NCLConnectorParam> extends NCLElementImpl implements NCLAction<A, P> {

    private NCLActionOperator operator;
    private Integer delay;
    private P parDelay;
    
    private List<A> actions = new ArrayList<A>();

    private boolean insideAction;


    /**
     * Construtor do elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundAction() {}


    /**
     * Construtor do elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCompoundAction(XMLReader reader, NCLElementImpl parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideAction = false;
    }
    
    
    /**
     * Determina o operador da ação composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLActionOperator operator) {
        notifyAltered(NCLElementAttributes.OPERATOR, this.operator, operator);
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
    public boolean addAction(A action) {
        if(action != null && actions.add(action)){
            //atribui este como parente da acao
            action.setParent(this);

            notifyInserted(NCLElementSets.ACTIONS, action);
            return true;
        }
        return false;
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
    public boolean removeAction(A action) {
        if(actions.remove(action)){
            //Se action existe, retira o seu parentesco
            if(action != null)
                action.setParent(null);

            notifyRemoved(NCLElementSets.ACTIONS, action);
            return true;
        }
        return false;
    }


    /**
     * Verifica se a ação composta possui uma ação.
     *
     * @param action
     *          elemento representando a ação a ser verificada
     * @return
     *          verdadeiro se a ação existe.
     */
    public boolean hasAction(A action) {
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
    public List<A> getActions() {
        return actions;
    }


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if(delay != null && delay < 0)
            throw new IllegalArgumentException("Invalid delay");

        notifyAltered(NCLElementAttributes.DELAY, this.delay, delay);
        this.delay = delay;
        this.parDelay = null;
    }


    public void setDelay(P delay) {
        notifyAltered(NCLElementAttributes.DELAY, this.delay, delay);
        this.parDelay = delay;
        this.delay = null;
    }


    public Integer getDelay() {
        return delay;
    }


    public P getParamDelay() {
        return parDelay;
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
        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";
        if(getParamDelay() != null)
            content += " delay='$" + getParamDelay().getId() + "'";
        content += ">\n";

        if(hasAction()){
            for(A action : actions)
                content += action.parse(ident + 1);
        }

        content += space + "</compoundAction>\n";

        return content;
    }


    public int compareTo(A other) {
        int comp = 0;

        String this_act, other_act;
        NCLCompoundAction other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundAction))
            return 1;

        other_comp = (NCLCompoundAction) other;
        
        // Compara pelo operador
        if(comp == 0){
            if(getOperator() == null) this_act = ""; else this_act = getOperator().toString();
            if(other_comp.getOperator() == null) other_act = ""; else other_act = other_comp.getOperator().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo delay
        if(comp == 0){
            int this_del, other_del;
            if(getDelay() == null) this_del = 0; else this_del = getDelay();
            if(other_comp.getDelay() == null) other_del = 0; else other_del = other_comp.getDelay();
            comp = this_del - other_del;
        }

        // Compara pelo delay (parametro)
        if(comp == 0){
            if(getParamDelay() == null && other_comp.getParamDelay() == null)
                comp = 0;
            else if(getParamDelay() != null && other_comp.getParamDelay() != null)
                comp = getParamDelay().compareTo(other_comp.getParamDelay());
            // so um dos dois tem parametro, o que tiver vem depois
            else if(getParamDelay() == null)
                comp = -1;
            else
                comp = 1;
        }

        // Compara o número de acoes
        if(comp == 0)
            comp = actions.size() - ((List) other_comp.getActions()).size();

        // Compara as acoes
        if(comp == 0){
            Iterator it = other_comp.getActions().iterator();
            for(NCLAction a : actions){
                NCLAction other_a = (NCLAction) it.next();
                comp = a.compareTo(other_a);
                if(comp != 0)
                    break;
            }
        }

        
        return comp;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("compoundAction") && !insideAction){
                cleanWarnings();
                cleanErrors();
                insideAction = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("operator")){
                        for(NCLActionOperator o : NCLActionOperator.values()){
                            if(o.toString().equals(attributes.getValue(i)))
                                setOperator(o);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("delay")){
                        String var = attributes.getValue(i);
                        if(var.contains("$")){
                            var = var.substring(1);
                            setDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
                        }
                        else{
                            var = var.substring(0, var.length() - 1);
                            setDelay(new Integer(var));
                        }
                    }
                }
            }
            else if(localName.equals("simpleAction")){
                A child = createSimpleAction();
                child.startElement(uri, localName, qName, attributes);
                addAction(child);
            }
            else if(localName.equals("compoundAction") && insideAction){
                A child = createCompoundAction();
                child.startElement(uri, localName, qName, attributes);
                addAction(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getParamDelay() != null)
                setDelay(parameterReference(getParamDelay().getId()));
        }

        if(hasAction()){
            for(A action : actions){
                action.endDocument();
                addWarning(action.getWarnings());
                addError(action.getErrors());
            }
        }
    }


    private P parameterReference(String id) {
        NCLElementImpl connector = getParent();

        while(!(connector instanceof NCLCausalConnector)){
            connector = connector.getParent();
            if(connector == null){
                addWarning("Could not find a parent connector");
                return null;
            }
        }

        Set<P> params = ((NCLCausalConnector) connector).getConnectorParams();
        for(P param : params){
            if(param.getId().equals(id))
                return param;
        }

        addWarning("Could not find connectorParam in connector with id: " + id);
        return null;
    }


    /**
     * Função de criação do elemento filho <i>simpleAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleAction</i>.
     */
    protected A createSimpleAction() {
        return (A) new NCLSimpleAction(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundAction</i>.
     */
    protected A createCompoundAction() {
        return (A) new NCLCompoundAction(getReader(), this);
    }
}
