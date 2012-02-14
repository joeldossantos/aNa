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

import br.uff.midiacom.ana.datatype.enums.NCLOperator;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLElementPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import java.util.Iterator;


public class NCLCompoundStatementPrototype<T extends NCLCompoundStatementPrototype, P extends NCLElement, I extends XMLElementImpl, Es extends NCLStatement>
        extends XMLElementPrototype<Es, P, I> implements NCLStatement<Es, P> {

    protected NCLOperator operator;
    protected Boolean isNegated;
    protected ElementList<Es, T> statements;


    /**
     * Construtor do elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundStatementPrototype() throws XMLException {
        super();
        statements = new ElementList<Es, T>();
    }
    
    
    /**
     * Determina o operador da assertiva composta.
     * 
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a assertiva composta.
     * 
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLOperator getOperator() {
        return operator;
    }
    
    
    /**
     * Determina se a assertiva composta está negada.
     * 
     * @param isNegated
     *          booleano que define se a assertiva está negada.
     */
    public void setIsNegated(Boolean isNegated) {
        this.isNegated = isNegated;
    }
    
    
    /**
     * Retorna se a assertiva composta está negada.
     * 
     * @return
     *          booleano que define se a assertiva está negada.
     */
    public Boolean getIsNegated() {
        return isNegated;
    }
    
    
    /**
     * Adiciona uma assertiva a assertiva composta.
     * 
     * @param statement
     *          elemento representando a assertiva a ser adicionada.
     * @return
     *          verdadeiro se a assertiva foi adicionada.
     *
     * @see ArrayList#add
     */
    public boolean addStatement(Es statement) throws XMLException {
        return statements.add(statement, (T) this);
    }
    
    
    /**
     * Remove uma assertiva da assertiva composta.
     *
     * @param statement
     *          elemento representando a assertiva a ser removida.
     * @return
     *          verdadeiro se a assertiva foi removida.
     *
     * @see ArrayList#remove
     */
    public boolean removeStatement(Es statement) throws XMLException {
        return statements.remove(statement);
    }
    
    
    /**
     * Verifica se a assertiva composta possui uma assertiva.
     * 
     * @param statement
     *          elemento representando a assertiva a ser verificada.
     * @return
     *          verdadeiro se a assertiva existe.
     */
    public boolean hasStatement(Es statement) throws XMLException {
        return statements.contains(statement);
    }
    
    
    /**
     * Verifica se a assertiva composta possui pelo menos uma assertiva.
     * 
     * @return
     *          verdadeiro se a assertiva composta possuir pelo menos uma assertiva.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }
    
    
    /**
     * Retorna as assertivas da assertiva composta.
     *
     * @return
     *          lista contendo as assertivas da assertiva composta.
     */
    public ElementList<Es, T> getStatements() {
        return statements;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundStatement";
        content += parseAttributes();
        content += ">\n";

        content += parseElements(ident + 1);

        content += space + "</compoundStatement>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseOperator();
        content += parseIsNegated();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseStatements(ident);
        
        return content;
    }
    
    
    protected String parseOperator() {
        NCLOperator aux = getOperator();
        if(aux != null)
            return " operator='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseIsNegated() {
        Boolean aux = getIsNegated();
        if(aux != null)
            return " isNegated='" + aux.toString() + "'";
        else
            return "";
    }
    
    
    protected String parseStatements(int ident) {
        if(!hasStatement())
            return "";
        
        String content = "";
        for(Es aux : statements)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    public boolean compare(Es other) {
        boolean comp = true;

        String this_stat, other_stat;
        NCLCompoundStatementPrototype other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundStatementPrototype))
            return false;

        other_comp = (NCLCompoundStatementPrototype) other;
        
        // Compara pelo operador
        if(getOperator() == null) this_stat = ""; else this_stat = getOperator().toString();
        if(other_comp.getOperator() == null) other_stat = ""; else other_stat = other_comp.getOperator().toString();
        comp &= this_stat.equals(other_stat);

        // Compara pelo isNegated
        if(getIsNegated() == null) this_stat = ""; else this_stat = getIsNegated().toString();
        if(other_comp.getIsNegated() == null) other_stat = ""; else other_stat = other_comp.getIsNegated().toString();
        comp &= this_stat.equals(other_stat);

        // Compara o número de statements
        comp &= statements.size() == other_comp.getStatements().size();

        // Compara as statements
        Iterator it = other_comp.getStatements().iterator();
        for(NCLStatement st : statements){
            if(!it.hasNext())
                continue;
            NCLStatement other_st = (NCLStatement) it.next();
            comp &= st.compare(other_st);
            if(comp)
                break;
        }


        return comp;
    }
}
