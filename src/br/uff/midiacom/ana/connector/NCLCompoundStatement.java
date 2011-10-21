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
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCompoundStatementPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class NCLCompoundStatement<T extends NCLCompoundStatement, P extends NCLElement, I extends NCLElementImpl, Es extends NCLStatement>
        extends NCLCompoundStatementPrototype<T, P, I, Es> implements NCLStatement<Es, P> {


    public NCLCompoundStatement() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }

    
    @Override
    public void setOperator(NCLOperator operator) {
        NCLOperator aux = this.operator;
        super.setOperator(operator);
        impl.notifyAltered(NCLElementAttributes.OPERATOR, aux, operator);
    }
    
        
    @Override
    public void setIsNegated(Boolean isNegated) {
        Boolean aux = this.isNegated;
        super.setIsNegated(isNegated);
        impl.notifyAltered(NCLElementAttributes.ISNEGATED, aux, isNegated);
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
    

//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        if(localName.equals("compoundStatement") && !insideStatement){
//            cleanWarnings();
//            cleanErrors();
//            insideStatement = true;
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("operator")){
//                    for(NCLOperator o : NCLOperator.values()){
//                        if(o.toString().equals(attributes.getValue(i)))
//                            setOperator(o);
//                    }
//                }
//                else if(attributes.getLocalName(i).equals("isNegated"))
//                    setIsNegated(new Boolean(attributes.getValue(i)));
//            }
//        }
//        else if(localName.equals("assessmentStatement")){
//            S child = createAssessmentStatement();
//            child.startElement(uri, localName, qName, attributes);
//            addStatement(child);
//        }
//        else if(localName.equals("compoundStatement") && insideStatement){
//            S child = createCompoundStatement();
//            child.startElement(uri, localName, qName, attributes);
//            addStatement(child);
//        }
//    }


//    @Override
//    public void endDocument() {
//        if(hasStatement()){
//            for(S statement : statements){
//                statement.endDocument();
//                addWarning(statement.getWarnings());
//                addError(statement.getErrors());
//            }
//        }
//    }


    public void load(Element element) throws XMLException, NCLParsingException {
        String att_name, att_var, ch_name;
        NodeList nl;

        // set the operator (required)
        att_name = NCLElementAttributes.OPERATOR.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setOperator(NCLOperator.getEnumType(att_var));
        else
            throw new NCLParsingException("Could not find " + att_name + " attribute.");

        // set the isNegated (optional)
        att_name = NCLElementAttributes.ISNEGATED.toString();
        if(!(att_var = element.getAttribute(att_name)).isEmpty())
            setIsNegated(Boolean.parseBoolean(att_var));

        // create the assessmentStatement nodes
        ch_name = NCLElementSets.STATEMENTS.toString();
        nl = element.getElementsByTagName(ch_name);
        for(int i=0; i < nl.getLength(); i++){
            Element el = (Element) nl.item(i);
            addStatement(createAssessmentStatement(el));
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
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
