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
package br.uff.midiacom.ana.rule;

import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLModificationListener;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.NCLReferenceManager;
import br.uff.midiacom.ana.datatype.auxiliar.PostReferenceElement;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRulePrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import org.w3c.dom.Element;


public class NCLRule<T extends NCLTestRule, P extends NCLElement, I extends NCLElementImpl, Ep extends NCLProperty>
        extends NCLRulePrototype<T, P, I, Ep> implements NCLTestRule<T, P>, PostReferenceElement {


    public NCLRule(String id) throws XMLException {
        super(id);
    }

    
    public NCLRule() throws XMLException {
        super();
    }


    @Override
    protected void createImpl() throws XMLException {
        impl = (I) new NCLElementImpl<T, P>(this);
    }


    @Override
    public void setVar(Ep var) {
        NCLElement aux = (NCLElement) this.var;
        super.setVar(var);
        impl.notifyAltered(NCLElementAttributes.VAR, aux, var);
    }


    @Override
    public void setComparator(NCLComparator comparator) {
        NCLComparator aux = this.comparator;
        super.setComparator(comparator);
        impl.notifyAltered(NCLElementAttributes.COMPARATOR, aux, comparator);
    }


    @Override
    public void setValue(String value) throws XMLException {
        StringType aux = this.value;
        super.setValue(value);
        impl.notifyAltered(NCLElementAttributes.VALUE, aux, value);
    }


    public void load(Element element) throws NCLParsingException {
        String att_name, att_var;

        try{
            // set the id (required)
            att_name = NCLElementAttributes.ID.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setId(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the var (required)
            att_name = NCLElementAttributes.VAR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty()){
                Ep prop = (Ep) new NCLProperty(att_var);
                setVar(prop);
                NCLReferenceManager.getInstance().waitReference(this);
            }
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the comparator (required)
            att_name = NCLElementAttributes.COMPARATOR.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setComparator(NCLComparator.getEnumType(att_var));
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");

            // set the value (required)
            att_name = NCLElementAttributes.VALUE.toString();
            if(!(att_var = element.getAttribute(att_name)).isEmpty())
                setValue(att_var);
            else
                throw new NCLParsingException("Could not find " + att_name + " attribute.");
        }
        catch(XMLException ex){
            String aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Rule" + aux + ":\n" + ex.getMessage());
        }
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }
    
    
    public T findRule(String id) throws XMLException {
        if(getId().equals(id))
            return (T) this;
        else
            return null;
    }
    
    
    public void fixReference() throws NCLParsingException {
        String aux;
        
        try{
            // set the var (required)
            if((aux = getVar().getName()) != null){
                Ep prop = (Ep) NCLReferenceManager.getInstance().findPropertyReference(impl.getDoc(), aux);
                setVar(prop);
            }
        }
        catch(XMLException ex){
            aux = getId();
            if(aux != null)
                aux = "(" + aux + ")";
            else
                aux = "";
            
            throw new NCLParsingException("Rule" + aux + ". Fixing reference:\n" + ex.getMessage());
        }
    }
}
