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
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
     * Construtor do elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCausalConnector(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }
    
    
    /**
     * Atribui uma condição ao conector causal.
     * 
     * @param condition
     *          elemento representando uma condição do conector.
     */    
    public void setCondition(Co condition) {
        //Retira o parentesco do condition atual
        if(this.condition != null){
            this.condition.setParent(null);
            notifyRemoved(NCLElementSets.CONDITIONS, this.condition);
        }

        this.condition = condition;
        //Se condition existe, atribui este como seu parente
        if(this.condition != null){
            this.condition.setParent(this);
            notifyInserted(NCLElementSets.CONDITIONS, condition);
        }
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
        //Retira o parentesco do action atual
        if(this.action != null){
            this.action.setParent(null);
            notifyRemoved(NCLElementSets.ACTIONS, this.action);
        }

        this.action = action;
        //Se action existe, atribui este como seu parente
        if(this.action != null){
            this.action.setParent(this);
            notifyInserted(NCLElementSets.ACTIONS, action);
        }
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
     *          parâmetro a ser adicionado ao conector.
     * @return
     *          verdadeiro se o parâmetro for adicionado.
     *
     * @see TreeSet#add
     */    
    public boolean addConnectorParam(P param) throws NCLInvalidIdentifierException {
        if(conn_params.add(param)){
            //Se param existe, atribui este como seu parente
            if(param != null)
                param.setParent(this);

            notifyInserted(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
        }
        return false;
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
                return removeConnectorParam(connp);
        }

        return false;
    }


    /**
     * Remove um parâmetro do conector causal.
     *
     * @param param
     *          parâmetro a ser removido do conector.
     * @return
     *          verdadeiro se o parâmetro for removido.
     */
    public boolean removeConnectorParam(P param) {
        if(conn_params.remove(param)){
            //Se param existe, retira o seu parentesco
            if(param != null)
                param.setParent(null);

            notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
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
     *          lista contendo os parâmetros do conector.
     */
    public Set<P> getConnectorParams() {
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


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("causalConnector")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("connectorParam")){
                P child = createConnectorParam();
                child.startElement(uri, localName, qName, attributes);
                addConnectorParam(child);
            }
            else if(localName.equals("simpleCondition")){
                setCondition(createSimpleCondition());
                getCondition().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("compoundCondition")){
                setCondition(createCompoundCondition());
                getCondition().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("simpleAction")){
                setAction(createSimpleAction());
                getAction().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("compoundAction")){
                setAction(createCompoundAction());
                getAction().startElement(uri, localName, qName, attributes);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasConnectorParam()){
            for(P param : conn_params){
                param.endDocument();
                addWarning(param.getWarnings());
                addError(param.getErrors());
            }
        }
        if(getCondition() != null){
            getCondition().endDocument();
            addWarning(getCondition().getWarnings());
            addError(getCondition().getErrors());
        }
        if(getAction() != null){
            getAction().endDocument();
            addWarning(getAction().getWarnings());
            addError(getAction().getErrors());
        }
    }


    /**
     * Função de criação do elemento filho <i>connectorParam</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>connectorParam</i>.
     */
    protected P createConnectorParam() {
        return (P) new NCLConnectorParam(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>simpleCondition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleCondition</i>.
     */
    protected Co createSimpleCondition() {
        return (Co) new NCLSimpleCondition(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundCondition</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundCondition</i>.
     */
    protected Co createCompoundCondition() {
        return (Co) new NCLCompoundCondition(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>simpleAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleAction</i>.
     */
    protected Ac createSimpleAction() {
        return (Ac) new NCLSimpleAction(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundAction</i>.
     */
    protected Ac createCompoundAction() {
        return (Ac) new NCLCompoundAction(getReader(), this);
    }
}
