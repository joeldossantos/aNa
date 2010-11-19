package br.pensario.connector;

import br.pensario.NCLValues.NCLOperator;
import java.util.Set;
import java.util.TreeSet;

public class NCLCompoundStatement extends NCLStatement {

    private NCLOperator operator;
    private Boolean isNegated;
    
    private Set<NCLStatement> statements = new TreeSet();
    
    
    /**
     * Compound Statement constructor.
     * 
     * @param operator compoundStatement operator
     * @throws java.lang.Exception if the operator is invalid
     */
    public NCLCompoundStatement(NCLOperator operator) throws Exception {
        setOperator(operator);
    }
    
    
    /**
     * Determina o operador da compoundStatement.
     * 
     * @param operator operador a ser utilizado.
     */
    public void setOperator(NCLOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador da compoundStatement.
     * 
     * @return NCLOperator representando o operador.
     */
    public NCLOperator getOperator() {
        return operator;
    }
    
    
    /**
     * Determina o atributo isNegated do compoundStatement.
     * 
     * @param isNegated valor a ser utilizado.
     */
    public void setIsNegated(Boolean isNegated) {
        this.isNegated = isNegated;
    }
    
    
    /**
     * Retorna o valor do atributo isNegated do compoundStatement.
     * 
     * @return Boolean representando o atributo.
     */
    public Boolean getIsNegated() {
        return isNegated;
    }
    
    
    /**
     * Adiciona um NCLStatement ao compoundStatement.
     * 
     * @param statement NCLStatement a ser adicionado.
     * @throws br.pensario.ExistentElementException se o elemento adicionado já existir.
     */
    public boolean addStatement(NCLStatement statement) {
        return statements.add(statement);
    }
    
    
    /**
     * Remove um NCLStatement do compoundStatement.
     * 
     * @param statement NCLStatement a ser removido.
     * @throws br.pensario.ExistentElementException se o elemento a ser removido não existir.
     */
    public boolean removeStatement(NCLStatement statement) {
        return statements.remove(statement);
    }
    
    
    /**
     * Verifica se o compoundStatement possui um statement.
     * 
     * @param statement NCLStatement a ser verificado.
     * @return Verdadeiro se possuir.
     * @throws java.lang.Exception
     */
    public boolean hasStatement(NCLStatement statement) {
        return statements.contains(statement);
    }
    
    
    /**
     * Verifica se o compoundStetement possui algum statement.
     * 
     * @return Verdadeiro se possuir.
     */
    public boolean hasStatement() {
        return !statements.isEmpty();
    }
    
    
    /**
     * Retorna um iterador de NCLStatements.
     * 
     * @return Iterable
     */
    public Iterable<NCLStatement> getStatements() {
        return statements;
    }
    
    
    /**
     * Retorna o código XML do elemento CompoundStatement.
     * 
     * @param ident nível de indentação do código.
     * @return String com o código XML.
     */
    public String parse(int ident) {
        String space, content;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundStatement";

        if(getOperator() != null)
            content += " operator='" + getOperator().toString() + "'";
        
        if(getIsNegated() != null)
            content += " isNegated='" + getIsNegated().toString() + "'";
        
        content += ">\n";
        
        for(NCLStatement statement : statements)
            content += statement.parse(ident + 1);
        
        content += space + "</compoundStatement>\n";

        return content;
    }
    
    
    /**
     * Retorna o código XML do elemento CompoundStatement se indentação.
     * 
     * @return String com o código XML.
     */
    public String toString() {
        return parse(0);
    }
    
    
    public boolean compareStatements(NCLStatement other) {
        NCLCompoundStatement cstat = (NCLCompoundStatement) other;
        
        for(NCLStatement statement : statements)
            if(!cstat.hasStatement(statement)) return false;
        
        return true;
    }
    
    
    public boolean equals(NCLStatement other) {
        //nao sao do mesmo tipo?
        if (!(other instanceof NCLCompoundStatement))
            return false;
        //tem o mesmo operador?
        if (!((NCLCompoundStatement) other).getOperator().toString().equals(operator.toString()))
            return false;
        //tem o mesmo isNegated?
        if (((NCLCompoundStatement) other).getIsNegated() != isNegated)
            return false;
        if (!compareStatements(other))
            return false;
        else
            return true;
    }
    
    
    public int compareTo(NCLStatement other) {
        if (equals(other))
            return 0;
        else
            return -1;
    }
}
