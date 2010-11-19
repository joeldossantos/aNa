package br.pensario.connector;

import br.pensario.NCLValues.NCLComparator;
import br.pensario.NCLValues.NCLDefaultValueAssessment;
import java.util.Set;
import java.util.TreeSet;

public class NCLAssessmentStatement extends NCLStatement {

    private NCLComparator comparator;
    
    private NCLValueAssessment valueAssessment;
    private Set<NCLAttributeAssessment> attributeAssessments = new TreeSet();
    
    
    /**
     * Determina o comparador da assertiva.
     * 
     * @param comparator comparador da assertiva.
     */
    public void setComparator(NCLComparator comparator) {
        this.comparator = comparator;
    }
    
    
    /**
     * Retorna o comparador da assertiva.
     * 
     * @return NCLComparator representando o comparador.
     */
    public NCLComparator getComparator() {
        return comparator;
    }
    
    
    /**
     * Adiciona um valor a assertiva.
     * 
     * @param value String com o valor a ser utilizado.
     */
    public void addValueAssessment(String value) {
        this.valueAssessment = new NCLValueAssessment(value);
    }
    
    
    /**
     * Adiciona um valor a assertiva.
     * 
     * @param value NCLDefaultValueAssessment com o valor a ser utilizado.
     */
    public void addValueAssessment(NCLDefaultValueAssessment value) {
        this.valueAssessment = new NCLValueAssessment(value.toString());
    }
    
    
    /**
     * Retorna o valueAssessment.
     * 
     * @return NCLValueAssessment representando o valueAssessment
     */
    public NCLValueAssessment getValueAssessment() {
        return valueAssessment;
    }
    
    
    /**
     * Remove o valor da assertiva.
     */
    public void removeValueAssessment() {
        this.valueAssessment = null;
    }
    
    
    /**
     * Adiciona um atributo a assertiva.
     * 
     * @param attribute atributo a ser adicionado.
     * @return verdadeiro se o atributo foi adicionado.
     * @throws java.lang.Exception se o número máximo de atributos for ultrapassado.
     */
    public boolean addAttributeAssessment(NCLAttributeAssessment attribute) throws Exception {
        if (attributeAssessments.size() == 2){
            Exception ex = new Exception("can't have more than two attributes"); //TODO: Criar tipo de excecao
            throw ex;
        }
        
        return attributeAssessments.add(attribute);
    }
    
    
    /**
     * Remove um atributo da assertiva.
     * 
     * @param attribute atributo a ser removido.
     * @return verdadeiro se o atributo for removido.
     */
    public boolean removeAttributeAssessment(NCLAttributeAssessment attribute) {
        return attributeAssessments.remove(attribute);
    }
    
    
    /**
     * Verifica se um atributo está presente.
     * 
     * @param attribute atributo a ser verificado.
     * @return verdadeiro se o atributo estiver presente.
     * @throws java.lang.Exception
     */
    public boolean hasAttributeAssessment(NCLAttributeAssessment attribute) {
        return attributeAssessments.contains(attribute);
    }
    
    
    /**
     * Verifica se pelo menos um atributo está presente.
     * 
     * @return verdadeiro so pelo menos um atributo existir.
     */
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }
    
    
    /**
     * Retorna o código XML do elemento AssessmentStatement.
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

        content = space + "<assessmentStatement";

        content += " comparator='" + getComparator().toString() + "'";
        
        content += ">\n";
        
        for(NCLAttributeAssessment attribute : attributeAssessments)
            content += attribute.parse(ident + 1);
        
        if (valueAssessment != null)
            content += valueAssessment.parse(ident + 1);
        
        content += space + "</assessmentStatement>\n";

        return content;
    }
    
    
    /**
     * Retorna o código XML do elemento AssessmentStatement sem indentação.
     * 
     * @return String com o código XML.
     */
    public String toString() {
        return parse(0);
    }
    
    
    public boolean compareAssessments(NCLStatement other) {
        NCLAssessmentStatement astat = (NCLAssessmentStatement) other;
        
        for(NCLAttributeAssessment attribute : attributeAssessments)
            if(!astat.hasAttributeAssessment(attribute)) return false;
        
        if (valueAssessment == null && astat.getValueAssessment() != null)
            return false;
        if (valueAssessment != null && astat.getValueAssessment() == null)
            return false;
        if (!valueAssessment.getValue().equals(astat.getValueAssessment().getValue()))
            return false;
        
        return true;
    }
    
    
    public boolean equals(NCLStatement other) {
        //nao sao do mesmo tipo?
        if (!(other instanceof NCLAssessmentStatement))
            return false;
        //tem o mesmo operador?
        if (!((NCLAssessmentStatement) other).getComparator().toString().equals(comparator.toString()))
            return false;
        if (!compareAssessments(other))
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
