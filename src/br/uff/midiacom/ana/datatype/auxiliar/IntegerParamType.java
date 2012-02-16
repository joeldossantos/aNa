package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class IntegerParamType<P extends NCLConnectorParamPrototype, O extends NCLElement> extends ParameterizedValueType<IntegerParamType, O, Integer, P>{

    
    public IntegerParamType(Integer value) throws XMLException {
        super(value);
    }


    public IntegerParamType(P value) throws XMLException {
        super(value);
    }
    
    
    public IntegerParamType(String value) throws XMLException {
        super(value);
    }
    
    
    public IntegerParamType(String value, O owner) throws XMLException {
        super(value, owner);
    }


    @Override
    protected P createParam(String param, O owner) throws XMLException {
        NCLElement connector = (NCLElement) owner.getParent();
        while(!(connector instanceof NCLCausalConnector)){
            connector = (NCLElement) connector.getParent();
            if(connector == null)
                throw new NCLParsingException("Could not find a parent connector");
        }
        
        P par = (P) ((NCLCausalConnector) connector).getConnectorParams().get(param);
        if(par == null)
            throw new NCLParsingException("Could not find a param in connector with name: " + param);
        
        return par;
    }


    @Override
    protected Integer createValue(String value) throws XMLException {
        return new Integer(value);
    }


    @Override
    protected String getStringValue() {
        if(getValue() == null)
            return null;
        else
            return getValue().toString();
    }


    @Override
    protected String getStringParam() {
        if(getParam() == null)
            return null;
        else
            return getParam().getName();
    }
}
