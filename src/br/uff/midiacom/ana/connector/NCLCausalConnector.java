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
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCausalConnectorPrototype;
import br.uff.midiacom.xml.XMLException;
import java.util.TreeSet;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCausalConnector<T extends NCLCausalConnector, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCondition, Ea extends NCLAction, Ep extends NCLConnectorParam>
        extends NCLCausalConnectorPrototype<T, P, I, Ec, Ea, Ep> implements NCLIdentifiableElement<T, P> {


    /**
     * Construtor do elemento <i>causalConnector</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          identificador do conector.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          caso o identificador seja inválido.
     */    
    public NCLCausalConnector(String id) throws XMLException {
        super(id);
        impl = (I) new NCLElementImpl(this);
    }

    
    /**
     * Atribui uma condição ao conector causal.
     * 
     * @param condition
     *          elemento representando uma condição do conector.
     */    
    @Override
    public void setCondition(Ec condition) {
        Ec aux = this.condition;
        super.setCondition(condition);
        impl.notifyAltered(NCLElementSets.CONDITIONS, aux, condition);
    }
    
    
    /**
     * Atribui uma ação ao conector causal.
     * 
     * @param action
     *          elemento representando uma ação do conector.
     */    
    @Override
    public void setAction(Ea action) {
        Ea aux = this.action;
        super.setAction(action);
        impl.notifyAltered(NCLElementSets.ACTIONS, aux, action);
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
    @Override
    public boolean addConnectorParam(Ep param) throws XMLException {
        if(super.addConnectorParam(param)){
            impl.notifyInserted(NCLElementSets.CONNECTOR_PARAMS, param);
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
    @Override
    public boolean removeConnectorParam(String name) throws XMLException {
        for(Ep connp : conn_params){
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
    @Override
    public boolean removeConnectorParam(Ep param) throws XMLException {
        if(super.removeConnectorParam(param)){
            impl.notifyRemoved(NCLElementSets.CONNECTOR_PARAMS, param);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("causalConnector")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                }
//            }
//            else if(localName.equals("connectorParam")){
//                P child = createConnectorParam();
//                child.startElement(uri, localName, qName, attributes);
//                addConnectorParam(child);
//            }
//            else if(localName.equals("simpleCondition")){
//                setCondition(createSimpleCondition());
//                getCondition().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("compoundCondition")){
//                setCondition(createCompoundCondition());
//                getCondition().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("simpleAction")){
//                setAction(createSimpleAction());
//                getAction().startElement(uri, localName, qName, attributes);
//            }
//            else if(localName.equals("compoundAction")){
//                setAction(createCompoundAction());
//                getAction().startElement(uri, localName, qName, attributes);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(hasConnectorParam()){
//            for(P param : conn_params){
//                param.endDocument();
//                addWarning(param.getWarnings());
//                addError(param.getErrors());
//            }
//        }
//        if(getCondition() != null){
//            getCondition().endDocument();
//            addWarning(getCondition().getWarnings());
//            addError(getCondition().getErrors());
//        }
//        if(getAction() != null){
//            getAction().endDocument();
//            addWarning(getAction().getWarnings());
//            addError(getAction().getErrors());
//        }
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


//    /**
//     * Função de criação do elemento filho <i>connectorParam</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>connectorParam</i>.
//     */
//    protected P createConnectorParam() {
//        return (P) new NCLConnectorParam(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>simpleCondition</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>simpleCondition</i>.
//     */
//    protected Co createSimpleCondition() {
//        return (Co) new NCLSimpleCondition(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>compoundCondition</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>compoundCondition</i>.
//     */
//    protected Co createCompoundCondition() {
//        return (Co) new NCLCompoundCondition(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>simpleAction</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>simpleAction</i>.
//     */
//    protected Ac createSimpleAction() {
//        return (Ac) new NCLSimpleAction(getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>compoundAction</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>compoundAction</i>.
//     */
//    protected Ac createCompoundAction() {
//        return (Ac) new NCLCompoundAction(getReader(), this);
//    }
}
