package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class IntegerParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<IntegerParamType, Integer, P>{

    private NCLElement parent;
    

    public IntegerParamType(Integer value) throws XMLException {
        super(value);
    }


    public IntegerParamType(P value) throws XMLException {
        super(value);
    }


    public IntegerParamType(String value) throws XMLException {
        super(value);
    }
    
    
    public IntegerParamType(String value, NCLElement parent) throws XMLException {
        super(value);
        
        this.parent = parent;
    }


    @Override
    protected P createParam(String param) throws XMLException {
        NCLElement connector = (NCLElement) parent.getParent();
        while(!(connector instanceof NCLCausalConnector)){
            connector = (NCLElement) connector.getParent();
            if(connector == null)
                throw new NCLParsingException("Could not find a parent connector");
        }
        
        return (P) ((NCLCausalConnector) connector).getConnectorParams().get(param);
    }


    @Override
    protected Integer createValue(String value) throws XMLException {
        return new Integer(value);
    }


    @Override
    protected String getStringValue() {
        return getValue().toString();
    }


    @Override
    protected String getStringParam() {
        return getParam().getName();
    }
}
