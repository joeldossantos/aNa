package br.pensario.connector;

public class NCLValueAssessment {

    private String value;
    
    
    public NCLValueAssessment(String value) throws Exception {
        setValue(value);
    }
    
    public void setValue(String value) throws Exception {
        if (value == null){
            Exception ex = new NullPointerException("null value");
            throw ex;
        }
        
        this.value = value;
    }
    
    public String parse(int ident) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String toString() {
        return parse(0);
    }
}
