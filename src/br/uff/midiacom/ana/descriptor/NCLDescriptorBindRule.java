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
package br.uff.midiacom.ana.descriptor;

import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLHead;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.rule.NCLRule;
import br.uff.midiacom.ana.rule.NCLTestRule;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>bindRule</i> de um switch de descritor da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um bind de um switch de descritor de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLDescriptorBindRule<B extends NCLDescriptorBindRule, D extends NCLLayoutDescriptor, R extends NCLTestRule> extends NCLElementImpl implements Comparable<B> {

    private D constituent;
    private R rule;


    /**
     * Construtor do elemento <i>bindRule</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLDescriptorBindRule() {}


    /**
     * Construtor do elemento <i>bindRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLDescriptorBindRule(XMLReader reader, NCLElementImpl parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um constituent ao bind.
     *
     * @param constituent
     *          elemento representando o descritor mapeado pelo bind.
     */
    public void setConstituent(D constituent) {
        notifyAltered(NCLElementAttributes.CONSTITUENT, this.constituent, constituent);
        this.constituent = constituent;
    }


    /**
     * Retorna o constituent do bind.
     *
     * @return
     *          elemento representando o descritor mapeado pelo bind.
     */
    public D getConstituent() {
        return constituent;
    }


    /**
     * Atribui uma regra de avaliação ao bind.
     *
     * @param rule
     *          elemento representando a regra de avaliação do bind.
     */
    public void setRule(R rule) {
        notifyAltered(NCLElementAttributes.RULE, this.rule, rule);
        this.rule = rule;
    }


    /**
     * Retorna a regra de avaliação do bind.
     * 
     * @return
     *          elemento representando a regra de avaliação do bind.
     */
    public R getRule() {
        return rule;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<bindRule";
        if(getRule() != null)
            content += " rule='" + getRule().getId() + "'";
        if(getConstituent() != null)
            content += " constituent='" + getConstituent().getId() + "'";
        content += "/>\n";


        return content;
    }


    public int compareTo(B other) {
        int comp = 0;

        // Compara pela regra
        if(getRule() != null)
            comp = getRule().compareTo(other.getRule());
        else
            comp = -1;

        // Compara pelo constituent
        if(comp == 0){
            if(getConstituent() != null)
                comp = getConstituent().compareTo(other.getConstituent());
            else
                comp = -1;
        }

        return comp;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("rule"))
                    setRule((R) new NCLRule(attributes.getValue(i)));//cast retirado na correcao das referencias
                else if(attributes.getLocalName(i).equals("constituent"))
                    setConstituent((D) new NCLDescriptor(attributes.getValue(i)));//cast retirado na correcao das referencias
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

        if(getConstituent() != null)
            constituentReference();

        if(getRule() != null)
            ruleReference();
    }


    private Set<R> getRules() {
        NCLElementImpl head = getParent();

        while(!(head instanceof NCLHead)){
            head = head.getParent();
            if(head == null){
                addWarning("Could not find a head");
                return null;
            }
        }

        if(((NCLHead) head).getRuleBase() == null){
            addWarning("Could not find a ruleBase");
            return null;
        }

        return ((NCLHead) head).getRuleBase().getRules();
    }


    private void constituentReference() {
        //Search for a component node in its parent
        Set<D> descriptors = ((NCLDescriptorSwitch) getParent()).getDescriptors();

        for(D descriptor : descriptors){
            if(descriptor.getId().equals(getConstituent().getId())){
                setConstituent(descriptor);
                return;
            }
        }

        addWarning("Could not find descriptor in descriptorSwitch with id: " + getConstituent().getId());
    }


    private void ruleReference() {
        //Search for the interface inside the node
        Set<R> rules = getRules();
        if(rules == null)
            return;

        for(R rul : rules){
            if(rul.getId().equals(getRule().getId())){
                setRule(rul);
                return;
            }
        }
        //@todo: regras internas a regras compostas podem ser utilizadas?

        addWarning("Could not find rule in ruleBase with id: " + getRule().getId());
    }
}
