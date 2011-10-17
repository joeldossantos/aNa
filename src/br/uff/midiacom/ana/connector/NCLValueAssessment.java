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
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.auxiliar.AssValueParamType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLValueAssessmentPrototype;
import br.uff.midiacom.xml.XMLException;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define de uma assertiva de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLValueAssessment<T extends NCLValueAssessment, P extends NCLElement, I extends NCLElementImpl, Ep extends NCLConnectorParam>
        extends NCLValueAssessmentPrototype<T, P, I, Ep> implements NCLElement<T, P> {


    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLValueAssessment() {}


    /**
     * Construtor do elemento <i>valueAssessment</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param value
     *          String contendo o valor da assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    public NCLValueAssessment(String value) throws XMLException {
        super(value);
        impl = (I) new NCLElementImpl(this);
    }
    

    /**
     * Determina o valor da assertiva do conector.
     *
     * @param value
     *          String contendo o valor da assertiva.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor a ser atribuído for uma String vazia.
     */
    @Override
    public void setValue(AssValueParamType value) {
        AssValueParamType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUEASSESSMENT, aux, value);
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("value")){
//                    String var = attributes.getValue(i);
//                    if(var.contains("$")){
//                        var = var.substring(1);
//                        setValue((P) new NCLConnectorParam(var));//cast retirado na correcao das referencias
//                    }
//                    else{
//                        setValue(var);
//                        //Try to find the value in one of the standard values
//                        for(NCLDefaultValueAssessment v : NCLDefaultValueAssessment.values()){
//                            if(v.toString().equals(var))
//                                setValue(v);
//                        }
//                    }
//                }
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
//        if(getParent() == null)
//            return;
//
//        if(getParamValue() != null)
//            setValue(parameterReference(getParamValue().getId()));
//    }
//
//
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


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
