package br.pensario.connector;

import br.pensario.ExistentElementException;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.NCLValues.NCLConditionOperator;

public class NCLCompoundCondition extends NCLCondition {
    
    private NCLConditionOperator operator;
    
    private Set<NCLCondition> conditions = new TreeSet<NCLCondition>();
    private Set<NCLStatement> statements = new TreeSet();
    
    
    /**
     * Atribui um operador para a condição composta NCL.
     * @param operator Operador NCL
     */
    public void setOperator(NCLConditionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador utilizado pela condição composta NCL    
     * @return Operador utilizado pela condição
     */
    public NCLConditionOperator getOperator() {
        return operator;
    }


    /**
     * Adiciona uma condição NCL na condição composta.
     * 
     * @param condition Condição a ser adicionada 
     * @return Se a adição foi realizada
     */
    public void addCondition(NCLCondition condition) throws ExistentElementException {
        if(!conditions.add(condition))
            throw new ExistentElementException("Condition already exists");
    }


    /**
     * Remove uma condição NCL da condição composta.
     * 
     * @param condition Condição a ser removida
     * @return Se a remoção foi realizada
     */
    public boolean removeCondition(NCLCondition condition) {
        return conditions.remove(condition);
    }

    
    /**
     * Indica se a condição NCL passada como parâmetro está presente na condição composta (em um único nível da hierarquia).
     * 
     * @param Condition NCLCondition Condição a ser buscada
     * @return Se a condição está presente na condição composta
     */
    public boolean hasCondition(NCLCondition condition) {
        return conditions.contains(condition);
    }

    
    /**
     * Indica se a condição composta NCL possui ao menos uma condição interna.
     * 
     * @return Se a condição composta possui condições internas
     */
    //REV: suplementar ao getConditions
    public boolean hasConditions() {
        return conditions.size() > 0;
    }
    
    
    /**
     * Adiciona uma asertiva NCL na condição composta.
     * 
     * @param statement asertiva a ser adicionada 
     * @return Se a adição foi realizada
     */
    public void addStatement(NCLStatement statement) throws ExistentElementException {
        if(!statements.add(statement))
            throw new ExistentElementException("Statement already exists");
    }


    /**
     * Remove uma asertiva NCL da condição composta.
     * 
     * @param statement asertiva a ser removida
     * @return Se a remoção foi realizada
     */
    public boolean removeStatement(NCLStatement statement) {
        return statements.remove(statement);
    }

    
    /**
     * Indica se a asertiva NCL passada como parâmetro está presente na condição composta (em um único nível da hierarquia).
     * 
     * @param statement asertiva a ser buscada
     * @return Se a asertiva está presente na condição composta
     */
    public boolean hasStatement(NCLStatement statement) {
        return statements.contains(statement);
    }

    
    /**
     * Indica se a condição composta NCL possui ao menos uma asertiva interna.
     * 
     * @return Se a condição composta possui asertivas internas
     */
    public boolean hasStatement() {
        return statements.size() > 0;
    }

    
    /**
     * Retorna a representação do elemento em XML.
     * @return Trecho XML referente ao elemento
     */
    public String parse(int ident) {

        String space, content;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundCondition";

        if(hasDelay())
            content += " delay='" + getDelay() + "s'";        
        
        content += ">\n";
        
        for(NCLCondition condition : conditions)
            content += condition.parse(ident + 1);
        
        for(NCLStatement statement : statements)
            content += statement.parse(ident + 1);
        
        content += space + "</compoundCondition>\n";

        return content;
    }


    public String toString() {
        return parse(0);        
    }


    public int compareTo(NCLCondition arg0) {        
                
        if(!(arg0 instanceof NCLCompoundCondition))
            return -1;        
        
        NCLCompoundCondition ccond = (NCLCompoundCondition) arg0;        
        
        for(NCLCondition condition : conditions)        
            if(!ccond.hasCondition(condition)) return -1;        
        
        if(ccond.compareTo(this) != 0 ) return -1;
        
        return 0;
    }    
}
