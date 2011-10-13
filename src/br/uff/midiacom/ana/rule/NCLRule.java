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
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLMimeType;
import br.uff.midiacom.ana.datatype.ncl.rule.NCLRulePrototype;
import br.uff.midiacom.ana.node.NCLContext;
import br.uff.midiacom.ana.node.NCLMedia;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.node.NCLSwitch;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma regra de teste da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLRule<T extends NCLTestRule, P extends NCLElement, I extends NCLElementImpl, Ep extends NCLProperty>
        extends NCLRulePrototype<T, P, I, Ep> implements NCLTestRule<T, P> {

    private P var;
    private NCLComparator comparator;
    private String value;


    /**
     * Construtor do elemento <i>rule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLRule(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>rule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLRule(XMLReader reader, NCLElementImpl parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui uma propriedade ao atributo var.
     *
     * @param var
     *          elemento representando a propriedade associada ao atributo.
     */
    public void setVar(P var) {
        notifyAltered(NCLElementAttributes.VAR, this.var, var);
        this.var = var;
    }


    /**
     * Retorna a propriedade relacionada ao atributo var.
     *
     * @return
     *          elemento representando a propriedade associada ao atributo.
     */
    public P getVar() {
        return var;
    }


    /**
     * Atribui um comparador a regra.
     *
     * @param comparator
     *          elemento representando o comparador da regra.
     */
    public void setComparator(NCLComparator comparator) {
        notifyAltered(NCLElementAttributes.COMPARATOR, this.comparator, comparator);
        this.comparator = comparator;
    }


    /**
     * Retorna o comparador da regra.
     *
     * @return
     *          elemento representando o comparador da regra.
     */
    public NCLComparator getComparator() {
        return comparator;
    }


    /**
     * Atribui um valor de comparação a regra.
     *
     * @param value
     *          String representando o valor de comparação.
     *
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws IllegalArgumentException {
        if (value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty String");

        notifyAltered(NCLElementAttributes.VALUE, this.value, value);
        this.value = value;
    }


    /**
     * Retorna o valor de comparação da regra.
     *
     * @return
     *          String representando o valor de comparação.
     */
    public String getValue() {
        return value;
    }
    

    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<rule";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getVar() != null)
            content += " var='" + getVar().getName() + "'";
        if(getComparator() != null)
            content += " comparator='" + getComparator().toString() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(T other) {
        return getId().compareTo(other.getId());
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("var"))
                    setVar((P) new NCLProperty(attributes.getValue(i)));//cast retirado na correcao das referencias
                else if(attributes.getLocalName(i).equals("comparator")){
                    for(NCLComparator c : NCLComparator.values()){
                        if(c.toString().equals(attributes.getValue(i)))
                            setComparator(c);
                    }
                }
                else if(attributes.getLocalName(i).equals("value"))
                    setValue(attributes.getValue(i));
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() == null)
            return;

        if(getVar() != null)
            propertyReference();
    }


    private void propertyReference() {
        //Search for the interface inside the node
        NCLElementImpl doc = getParent();

        while(!(doc instanceof NCLDoc)){
            doc = doc.getParent();
            if(doc == null){
                addWarning("Could not find a root element");
                return;
            }
        }

        if(((NCLDoc) doc).getBody() == null){
            addWarning("Could not find a body");
        }

        setVar(findProperty(((NCLDoc) doc).getBody().getNodes()));
    }


    private P findProperty(Set<NCLNode> nodes) {
        for(NCLNode n : nodes){
            if(n instanceof NCLMedia){
                if( ((NCLMedia) n).hasProperty()){
                    Set<P> properties = ((NCLMedia) n).getProperties();
                    for(P prop : properties){
                        if(prop.getName().equals(getVar().getName()))
                            return prop;
                    }
                }
            }
            else if(n instanceof NCLContext){
                if( ((NCLContext) n).hasNode()){
                    Set<NCLNode> cnodes = ((NCLContext) n).getNodes();
                    NCLProperty p = findProperty(cnodes);
                    if(p != null)
                        return (P) p;
                }
            }
            else if(n instanceof NCLSwitch){
                if( ((NCLSwitch) n).hasNode()){
                    Set<NCLNode> snodes = ((NCLSwitch) n).getNodes();
                    NCLProperty p = findProperty(snodes);
                    if(p != null)
                        return (P) p;
                }
            }
        }

        addWarning("Could not find property with name: " + getVar().getName());
        return null;
    }


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
