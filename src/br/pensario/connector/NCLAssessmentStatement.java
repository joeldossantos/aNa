package br.pensario.connector;

import br.pensario.NCLValues.NCLComparator;
import java.util.Set;
import java.util.TreeSet;

public class NCLAssessmentStatement extends NCLStatement {

    private NCLComparator comparator;
    
    private NCLValueAssessment valueAssessment;
    private Set<NCLAttributeAssessment> attributeAssessments = new TreeSet();
    
    
    public NCLAssessmentStatement(NCLComparator comparator, String value, NCLAttributeAssessment attribute) throws Exception {
        setComparator(comparator);
        addValueAssessment(value);
        addAttributeAssessment(attribute);
    }
    
    public NCLAssessmentStatement(NCLComparator comparator, NCLAttributeAssessment attribute1, NCLAttributeAssessment attribute2) throws Exception {
        setComparator(comparator);
        addAttributeAssessment(attribute1);
        addAttributeAssessment(attribute2);
    }
    
    public void setComparator(NCLComparator comparator) throws Exception {
        if (comparator == null){
            Exception ex = new NullPointerException("null comparator");
            throw ex;
        }
        
        this.comparator = comparator;
    }
    
    public NCLComparator getComparator() {
        return comparator;
    }
    
    public void addValueAssessment(String value) throws Exception {
        this.valueAssessment = new NCLValueAssessment(value);
    }
    
    public void removeValueAssessment() {
        this.valueAssessment = null;
    }
    
    public boolean hasValueAssessment() {
        return (valueAssessment != null);
    }
    
    public void addAttributeAssessment(NCLAttributeAssessment attribute) throws Exception {
        if (attributeAssessments.size() == 2){
            Exception ex = new Exception("can't have more than two attributes"); //TODO: Criar tipo de excecao
            throw ex;
        }
        
        if (!attributeAssessments.add(attribute)){
            Exception ex = new Exception("attribute already exists");
            throw ex;
        }
    }
    
    public void removeAttributeAssessment(NCLAttributeAssessment attribute) throws Exception {
        if (attributeAssessments.size() == 1){
            Exception ex = new Exception("can't have less then one attribute"); //TODO: Criar tipo de excecao
            throw ex;
        }
        
        if (!attributeAssessments.remove(attribute)){
            Exception ex = new Exception("attribute does not exists"); //TODO: Criar tipo de excecao
            throw ex;
        }
    }
    
    public boolean hasAttributeAssessment(NCLAttributeAssessment attribute) throws Exception {
        return attributeAssessments.contains(attribute);
    }
    
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }
    
    @Override
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return parse(0);
    }

    public int compareTo(NCLStatement arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
