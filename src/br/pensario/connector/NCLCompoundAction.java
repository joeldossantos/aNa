package br.pensario.connector;

import java.util.Set;

import br.pensario.NCLValues.NCLActionOperator;

public class NCLCompoundAction extends NCLAction {

    private NCLActionOperator operator;
    
    private Set<NCLAction> actions; 
    
    
    /**
     * Atribui um operador para a ação composta NCL.
     * @param operator Operador NCL
     */
    public void setOperator(NCLActionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador utilizado pela ação composta NCL    
     * @return Operador utilizado pela ação
     */
    public NCLActionOperator getOperator() {
        return operator;
    }

    
    /**
     * Adiciona uma a ação NCL na ação composta.
     * 
     * @param action NCLAction Ação a ser adicionada 
     * @return Se a adição foi realizada
     */
    public boolean addAction(NCLAction action) {
        return actions.add(action);
    }

    
    /**
     * Remove uma ação NCL da ação composta.
     * 
     * @param action NCLAction Ação a ser removida
     * @return Se a remoção foi realizada
     */
    public boolean removeAction(NCLAction action) {
        return actions.remove(action);
    }

    
    /**
     * Indica se a ação NCL está presente na ação composta (em um único nível da hierarquia).
     * 
     * @param action NCLAction Ação a ser buscada
     * @return Se a ação está presente na ação composta
     */
    public boolean hasAction(NCLAction action) {
        return actions.contains(action);
    }

    
    /**
     * Indica se a ação composta NCL possui ao menos uma ação.
     * 
     * @return Se a ação composta possui ações
     */
    //REV: suplementar ao getActions
    public boolean hasActions() {
        return actions.size() > 0;
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

        content = space + "<compoundAction";

        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";        
        
        content += ">\n";
        
        for(NCLAction action : actions)
            content += action.parse(ident + 1);
        
        content += space + "</compoundAction>";

        return content;
    }

    @Override
    public String toString() {
        return parse(0);        
    }
    
    
    public boolean compareActions(NCLAction other) {
        NCLCompoundAction cact = (NCLCompoundAction) other;
        
        for(NCLAction action : actions)
            if(!cact.hasAction(action)) return false;
        
        return true;
    }
    
    
    public boolean equals(NCLAction other) {
        //nao sao do mesmo tipo?
        if (!(other instanceof NCLCompoundAction))
            return false;
        //tem o mesmo operador?
        if (!((NCLCompoundAction) other).getOperator().toString().equals(operator.toString()))
            return false;
        if (!compareActions(other))
            return false;
        else
            return true;
    }
    
    
    public int compareTo(NCLAction other) {
        if (equals(other))
            return 0;
        else
            return -1;
    }
}
