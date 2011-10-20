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
package br.uff.midiacom.ana.link;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLParamInstance;
import br.uff.midiacom.ana.datatype.ncl.link.NCLParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLParam<T extends NCLParam, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLConnectorParam>
        extends NCLParamPrototype<T, P, I, Ec> implements NCLElement<T, P>{
    
    
    public NCLParam(NCLParamInstance paramType) throws XMLException {
        super(paramType);
    }

    public NCLParam(Element elem, NCLParamInstance paramType) throws XMLException {
        super(paramType);
        load(elem);
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<NCLIdentifiableElement, P>(this);
    }

    
    @Override
    public void setName(Ec connectorParam) {
        Ec aux = this.name;
        super.setName(connectorParam);
        impl.notifyAltered(NCLElementAttributes.NAME, aux, connectorParam);
    }
    
        
    @Override
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }
    
    
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            cleanWarnings();
//            cleanErrors();
//            for(int i = 0; i < attributes.getLength(); i++){
//                if(attributes.getLocalName(i).equals("name"))
//                    setName((C) new NCLConnectorParam(attributes.getValue(i)));//cast retirado na correcao das referencias
//                else if(attributes.getLocalName(i).equals("value"))
//                    setValue(attributes.getValue(i));
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
//        if(getName() != null)
//            nameReference();
//    }


//    private void nameReference() {
//        //Search for the connector parameter inside the connector
//        NCLElementImpl link = getParent();
//
//        while(!(link instanceof NCLLink)){
//            link = link.getParent();{
//                if(link == null){
//                    addWarning("Could not find a parent link");
//                    return;
//                }
//            }
//        }
//
//        if(((NCLLink) link).getXconnector() == null){
//            addWarning("Could not find a connector");
//            return;
//        }
//
//        Set<C> params = ((NCLLink) link).getXconnector().getConnectorParams();
//
//        for(C param : params){
//            if(param.getName().equals(getName().getName())){
//                setName(param);
//                return;
//            }
//        }
//
//        addWarning("Could not find connectorParam in connector with name: " + getName().getName());
//    }


    public void load(Element element) throws XMLException {
        String att_name, att_var;

        att_name = NCLElementAttributes.NAME.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setName(); // metodo de procura pelo id do connector

        att_name = NCLElementAttributes.VALUE.toString();
        if((att_var = element.getAttribute(att_name)) == null)
            throw new XMLException("Could not find " + att_name + " attribute.");
        else
            setValue(att_var);
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
}
