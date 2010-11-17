package br.pensario.connector;

import br.pensario.ExistentElementException;
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
     * @throws java.lang.NullPointerException se o operador for nulo.
     */
    public void setOperator(NCLOperator operator) throws NullPointerException {
        if (operator == null)
            throw new NullPointerException("null operator");
        
        
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
    public void setIsNegated(boolean isNegated) {
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
     * Verifica se o compoundStatement possui atributo isNegated.
     * 
     * @return Verdadeiro se possuir.
     */
    public boolean hasIsNegated() {
        return (isNegated != null);
    }
    
    
    /**
     * Adiciona um NCLStatement ao compoundStatement.
     * 
     * @param statement NCLStatement a ser adicionado.
     * @throws br.pensario.ExistentElementException se o elemento adicionado já existir.
     */
    public void addStatement(NCLStatement statement) throws ExistentElementException {
        if (!statements.add(statement))
            throw new ExistentElementException("statement already exists");
    }
    
    
    /**
     * Remove um NCLStatement do compoundStatement.
     * 
     * @param statement NCLStatement a ser removido.
     * @throws br.pensario.ExistentElementException se o elemento a ser removido não existir.
     */
    public void removeStatement(NCLStatement statement) throws ExistentElementException {
        if (!statements.remove(statement))
            throw new ExistentElementException("statement does not exists");
    }
    
    
    /**
     * Verifica se o compoundStatement possui um statement.
     * 
     * @param statement NCLStatement a ser verificado.
     * @return Verdadeiro se possuir.
     * @throws java.lang.Exception
     */
    public boolean hasStatement(NCLStatement statement) throws Exception {
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
     * 
     * @param ident
     * @return
     */
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String toString() {
        return parse(0);
    }
    
    public boolean equals(NCLStatement statement) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int compareTo(NCLStatement arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
