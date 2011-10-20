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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.enums.NCLComparator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class NCLAssessmentStatementPrototype<T extends NCLAssessmentStatementPrototype, P extends NCLElement, I extends XMLElementImpl, Ea extends NCLAttributeAssessmentPrototype, Ev extends NCLValueAssessmentPrototype>
        extends XMLElementPrototype<T, P, I> implements NCLStatement<T, P> {

    protected NCLComparator comparator;
    protected Ev valueAssessment;
    protected ElementList<Ea, T> attributeAssessments;
    

    /**
     * Construtor do elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLAssessmentStatementPrototype() throws XMLException {
        super();
        attributeAssessments = new ElementList<Ea, T>();
    }


    /**
     * Determina o comparador da assertiva.
     * 
     * @param comparator
     *          comparador utilizado pela assertiva.
     */
    public void setComparator(NCLComparator comparator) {
        this.comparator = comparator;
    }
    
    
    /**
     * Retorna o comparador da assertiva.
     * 
     * @return
     *          comparador utilizado pela assertiva.
     */
    public NCLComparator getComparator() {
        return comparator;
    }
    
    
    /**
     * Determina um valor de comparação a assertiva.
     * 
     * @param value
     *          String representando o valor de comparação a ser utilizado.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor for uma String vazia.
     *
     * @see NCLValueAssessmentPrototype
     */
    public void setValueAssessment(Ev value) {
        //Retira o parentesco do valueAssessment atual
        if(this.valueAssessment != null){
            this.valueAssessment.setParent(null);
        }

        this.valueAssessment = value;
        //Set valueAssessment existe, atribui este como seu parente
        if(this.valueAssessment != null){
            this.valueAssessment.setParent(this);
        }
    }
    
    
    /**
     * Retorna o valor de comparação a assertiva.
     * 
     * @return
     *          elemento representando o valor de comparação utilizado.
     */
    public Ev getValueAssessment() {
        return valueAssessment;
    }
    
    
    /**
     * Adiciona um atributo de comparação a assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser adicionado.
     * @return
     *          verdadeiro se o atributo foi adicionado.
     * @throws java.lang.Exception
     *          se o número máximo de atributos for ultrapassado.
     *
     * @see ArrayList#add
     */
    public boolean addAttributeAssessment(Ea attribute) throws Exception {
        if(attributeAssessments.size() == 2)
            throw new Exception("can't have more than two attributes");
        
        return attributeAssessments.add(attribute, (T) this);
    }
    
    
    /**
     * Remove um atributo de comparação da assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser removido.
     * @return
     *          verdadeiro se o atributo for removido.
     *
     * @see ArrayList#remove
     */
    public boolean removeAttributeAssessment(Ea attribute) throws XMLException {
        return attributeAssessments.remove(attribute);
    }
    
    
    /**
     * Verifica se a assertiva possui um atributo.
     * 
     * @param attribute
     *          elemento representando o atributo a ser verificado.
     * @return
     *          verdadeiro se o atributo estiver presente.
     */
    public boolean hasAttributeAssessment(Ea attribute) throws XMLException {
        return attributeAssessments.contains(attribute);
    }
    
    
    /**
     * Verifica se a assertiva possui pelo menos um atributo.
     * 
     * @return
     *          verdadeiro se a assertiva possuir pelo menos um atributo.
     */
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }


    /**
     * Retorna os atributos da assertiva.
     *
     * @return
     *          lista contendo os atributos da assertiva.
     */
    public ElementList<Ea, T> getAttributeAssessments() {
        return attributeAssessments;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<assessmentStatement";
        if(getComparator() != null)
            content += " comparator='" + getComparator().toString() + "'";
        content += ">\n";

        if(hasAttributeAssessment()){
            for(Ea attribute : attributeAssessments)
                content += attribute.parse(ident + 1);
        }

        if(getValueAssessment() != null)
            content += getValueAssessment().parse(ident + 1);
        
        content += space + "</assessmentStatement>\n";

        return content;
    }
    
    
    public boolean compare(T other) {
        boolean comp = true;

        String this_stat, other_stat;
        NCLAssessmentStatementPrototype other_asses;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLAssessmentStatementPrototype))
            return false;

        other_asses = (NCLAssessmentStatementPrototype) other;
        
        // Compara pelo comparador
        if(getComparator() == null) this_stat = ""; else this_stat = getComparator().toString();
        if(other_asses.getComparator() == null) other_stat = ""; else other_stat = other_asses.getComparator().toString();
        comp &= this_stat.equals(other_stat);

        // Compara o número de attributeAssessment
        comp &= attributeAssessments.size() == other_asses.getAttributeAssessments().size();

        // Compara os attributeAssessment
        Iterator it = other_asses.getAttributeAssessments().iterator();
        for(NCLAttributeAssessmentPrototype att : attributeAssessments){
            NCLAttributeAssessmentPrototype other_att = (NCLAttributeAssessmentPrototype) it.next();
            comp &= att.compare(other_att);
            if(comp)
                break;
        }

        // Compara os valueAssessment
        if(getValueAssessment() != null && other_asses.getValueAssessment() != null)
            comp &= getValueAssessment().compare(other_asses.getValueAssessment());


        return comp;
    }
}
