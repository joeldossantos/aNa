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
import br.uff.midiacom.ana.datatype.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.NCLElementSets;
import br.uff.midiacom.ana.datatype.NCLOperator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma assertiva composta de um conector de um documento NCL.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLCompoundStatement<S extends NCLStatement> extends NCLElement implements NCLStatement<S> {

    private NCLOperator operator;
    private Boolean isNegated;
    
    private List<S> statements = new ArrayList<S>();

    private boolean insideStatement;


    /**
     * Construtor do elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundStatement() {}


    /**
     * Construtor do elemento <i>compoundStatement</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCompoundStatement(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideStatement = false;
    }
    
    
    /**
     * Determina o operador da assertiva composta.
     * 
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLOperator operator) {
        notifyAltered(NCLElementAttributes.OPERATOR, this.operator, operator);
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
        notifyAltered(NCLElementAttributes.ISNEGATED, this.isNegated, isNegated);
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
    public boolean addStatement(S statement) {
        if(statement!= null && statements.add(statement)){
            //atribui este como parente do atributo
            statement.setParent(this);

            notifyInserted(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
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
    public boolean removeStatement(S statement) {
        if(statements.remove(statement)){
            //Se statement existe, retira o seu parentesco
            if(statement != null)
                statement.setParent(null);

            notifyRemoved(NCLElementSets.STATEMENTS, statement);
            return true;
        }
        return false;
    }
    
    
    /**
     * Verifica se a assertiva composta possui uma assertiva.
     * 
     * @param statement
     *          elemento representando a assertiva a ser verificada.
     * @return
     *          verdadeiro se a assertiva existe.
     */
    public boolean hasStatement(S statement) {
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
    public List<S> getStatements() {
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
        if(getOperator() != null)
            content += " operator='" + getOperator().toString() + "'";
        if(getIsNegated() != null)
            content += " isNegated='" + getIsNegated().toString() + "'";
        content += ">\n";

        if(hasStatement()){
            for(S statement : statements)
                content += statement.parse(ident + 1);
        }

        content += space + "</compoundStatement>\n";

        return content;
    }
    
    
    public int compareTo(S other) {
        int comp = 0;

        String this_stat, other_stat;
        NCLCompoundStatement other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundStatement))
            return 1;

        other_comp = (NCLCompoundStatement) other;
        
        // Compara pelo operador
        if(comp == 0){
            if(getOperator() == null) this_stat = ""; else this_stat = getOperator().toString();
            if(other_comp.getOperator() == null) other_stat = ""; else other_stat = other_comp.getOperator().toString();
            comp = this_stat.compareTo(other_stat);
        }

        // Compara pelo isNegated
        if(comp == 0){
            if(getIsNegated() == null) this_stat = ""; else this_stat = getIsNegated().toString();
            if(other_comp.getIsNegated() == null) other_stat = ""; else other_stat = other_comp.getIsNegated().toString();
            comp = this_stat.compareTo(other_stat);
        }

        // Compara o número de statements
        if(comp == 0)
            comp = statements.size() - ((List) other_comp.getStatements()).size();

        // Compara as statements
        if(comp == 0){
            Iterator it = other_comp.getStatements().iterator();
            for(NCLStatement st : statements){
                NCLStatement other_st = (NCLStatement) it.next();
                comp = st.compareTo(other_st);
                if(comp != 0)
                    break;
            }
        }


        return comp;
    }
    

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(localName.equals("compoundStatement") && !insideStatement){
            cleanWarnings();
            cleanErrors();
            insideStatement = true;
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("operator")){
                    for(NCLOperator o : NCLOperator.values()){
                        if(o.toString().equals(attributes.getValue(i)))
                            setOperator(o);
                    }
                }
                else if(attributes.getLocalName(i).equals("isNegated"))
                    setIsNegated(new Boolean(attributes.getValue(i)));
            }
        }
        else if(localName.equals("assessmentStatement")){
            S child = createAssessmentStatement();
            child.startElement(uri, localName, qName, attributes);
            addStatement(child);
        }
        else if(localName.equals("compoundStatement") && insideStatement){
            S child = createCompoundStatement();
            child.startElement(uri, localName, qName, attributes);
            addStatement(child);
        }
    }


    @Override
    public void endDocument() {
        if(hasStatement()){
            for(S statement : statements){
                statement.endDocument();
                addWarning(statement.getWarnings());
                addError(statement.getErrors());
            }
        }
    }


    /**
     * Função de criação do elemento filho <i>assessmentStatement</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>assessmentStatement</i>.
     */
    protected S createAssessmentStatement() {
        return (S) new NCLAssessmentStatement(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundStatement</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundStatement</i>.
     */
    protected S createCompoundStatement() {
        return (S) new NCLCompoundStatement(getReader(), this);
    }
}
