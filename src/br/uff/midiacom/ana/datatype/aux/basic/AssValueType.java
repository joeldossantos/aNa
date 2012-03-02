package br.uff.midiacom.ana.datatype.aux.basic;

import br.uff.midiacom.ana.datatype.enums.NCLDefaultValueAssessment;


public class AssValueType {

    private String value;
    private NCLDefaultValueAssessment defValue;


    public AssValueType(NCLDefaultValueAssessment value) throws NullPointerException {
        if(value == null)
            throw new NullPointerException("null value");

        this.defValue = value;
    }


    public AssValueType(String value) throws NullPointerException, IllegalArgumentException {
        if(value == null)
            throw new NullPointerException("Null value String");
        if("".equals(value.trim()))
            throw new IllegalArgumentException("Empty value String");

        if(value != null){
            for(NCLDefaultValueAssessment def : NCLDefaultValueAssessment.values()){
                if(value.equals(def.toString())){
                    defValue = def;
                    return;
                }
            }
        }

        this.value = value;
    }


    public NCLDefaultValueAssessment getValue() {
        return defValue;
    }


    public boolean isDefaultValue() {
        return value == null;
    }


    public String parse() {
        if(defValue != null)
            return defValue.toString();
        else
            return value;
    }
}
