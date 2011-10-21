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
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.datatype.auxiliar.DoubleParamType;
import br.uff.midiacom.ana.datatype.enums.NCLConditionOperator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCompoundConditionPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class NCLCompoundCondition<T extends NCLCompoundCondition, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCondition, Es extends NCLStatement, Ep extends NCLConnectorParam>
        extends NCLCompoundConditionPrototype<T, P, I, Ec, Es, Ep> implements NCLCondition<Ec, P, Ep> {
    

    public NCLCompoundCondition() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }


    @Override
    public void setOperator(NCLConditionOperator operator) {
        NCLConditionOperator aux = this.operator;
        super.setOperator(operator);
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
    
    @Override
    public boolean addCondition(Ec condition) throws XMLException {
        if(super.addCondition(condition)){
            impl.notifyInserted(NCLElementSets.CONDITIONS, condition);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeCondition(Ec condition) throws XMLException {
        if(super.removeCondition(condition)){
            impl.notifyRemoved(NCLElementSets.CONDITIONS, condition);
            return true;
        }
        return false;
    }

    
    @Override
    public boolean addStatement(Es statement) throws XMLException {
        if(super.addStatement(statement)){
            impl.notifyInserted(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeStatement(Es statement) throws XMLException {
        if(super.removeStatement(statement)){
            impl.notifyRemoved(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
    }


    @Override
    public void setDelay(DoubleParamType delay) {
        DoubleParamType aux = this.delay;
        super.setDelay(delay);
        impl.notifyAltered(NCLElementAttributes.DELAY, aux, delay);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("compoundCondition") && !insideCondition){
//                cleanWarnings();
//                cleanErrors();
//                insideCondition = true;
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("operator")){
//                        for(NCLConditionOperator o : NCLConditionOperator.values()){
//                            if(o.toString().equals(attributes.getValue(i)))
//                                setOperator(o);
//                        }
//                    }
//                    else if(attributes.getLocalName(i).equals("delay")){
//                        String var = attributes.getValue(i);
//                        if(var.contains("$")){
//                            var = var.substring(1);
//                            setDelay((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                        }
//                        else{
//                            var = var.substring(0, var.length() - 1);
//                            setDelay(new Integer(var));
//                        }
//                    }
//                }
//            }
//            else if(localName.equals("simpleCondition")){
//                C child = createSimpleCondition();
//                child.startElement(uri, localName, qName, attributes);
//                addCondition(child);
//            }
//            else if(localName.equals("compoundCondition") && insideCondition){
//                C child = createCompoundCondition();
//                child.startElement(uri, localName, qName, attributes);
//                addCondition(child);
//            }
//            else if(localName.equals("assessmentStatement")){
//                S child = createAssessmentStatement();
//                child.startElement(uri, localName, qName, attributes);
//                addStatement(child);
//            }
//            else if(localName.equals("compoundStatement")){
//                S child = createCompoundStatement();
//                child.startElement(uri, localName, qName, attributes);
//                addStatement(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getParamDelay() != null)
//                setDelay(parameterReference(getParamDelay().getId()));
//        }
//
//        if(hasCondition()){
//            for(C condition : conditions){
//                condition.endDocument();
//                addWarning(condition.getWarnings());
//                addError(condition.getErrors());
//            }
//        }
//        if(hasStatement()){
//            for(S statement : statements){
//                statement.endDocument();
//                addWarning(statement.getWarnings());
//                addError(statement.getErrors());
//            }
//        }
//    }


//    private P parameterReference(String id) {
//        NCLElementImpl connector = getParent();
//
//        while(!(connector instanceof NCLCausalConnector)){
//            connector = connector.getParent();
//            if(connector == null){
//                addWarning("Could not find a parent connector");
//                return null;
//            }
//        }
//
//        Set<P> params = ((NCLCausalConnector) connector).getConnectorParams();
//        for(P param : params){
//            if(param.getId().equals(id))
//                return param;
//        }
//
//        addWarning("Could not find connectorParam in connector with id: " + id);
//        return null;
//    }


    public void load(Element element) throws XMLException, NCLParsingException {
        String att_name, att_var;
        NodeList nl;

        // set the operator (required)
        att_name = NCLElementAttributes.OPERATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setOperator(NCLConditionOperator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the delay (optional)
        att_name = NCLElementAttributes.DELAY.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setDelay(new DoubleParamType(att_var));

        // create the child nodes
        nl = element.getChildNodes();
        for(int i=0; i < nl.getLength(); i++){
            Node nd = nl.item(i);
            if(nd instanceof Element){
                Element el = (Element) nl.item(i);

                //create the conditions
                if(el.getTagName().equals(NCLElementSets.CONDITIONS.toString()))
                    addCondition(createSimpleCondition(el)); // --> Como saber se é simple ou compound?

                // create the assessments
                if(el.getTagName().equals(NCLElementSets.STATEMENTS.toString()))
                    addStatement(createAssessmentStatement(el)); // --> Como saber se é simple ou compound?
            }
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


    /**
     * Function to create the child element <i>simpleCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>simpleCondition</i>.
     */
    protected Ec createSimpleCondition(Element element) throws XMLException {
        return (Ec) new NCLSimpleCondition(element);
    }


    /**
     * Function to create the child element <i>compoundCondition</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundCondition</i>.
     */
    protected NCLCompoundCondition createCompoundCondition() throws XMLException {
        return new NCLCompoundCondition();
    }


    /**
     * Function to create the child element <i>assessmentStatement</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>assessmentStatement</i>.
     */
    protected Es createAssessmentStatement(Element element) throws XMLException {
        return (Es) new NCLAssessmentStatement(element);
    }


    /**
     * Function to create the child element <i>compoundStatement</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing the child <i>compoundStatement</i>.
     */
    protected NCLCompoundStatement createCompoundStatement() throws XMLException {
        return new NCLCompoundStatement();
    }
}
