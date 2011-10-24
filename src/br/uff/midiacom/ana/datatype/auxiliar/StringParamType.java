package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class StringParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<StringParamType, StringType, P>{

    private NCLElement parent;
    
    
    public StringParamType(StringType value) throws XMLException {
        super(value);
    }


    public StringParamType(P value) throws XMLException {
        super(value);
    }


    public StringParamType(String value) throws XMLException {
        super(value);
    }
    
    
    public StringParamType(String value, NCLElement parent) throws XMLException {
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
    protected StringType createValue(String value) throws XMLException {
        return new StringType(value);
    }


    @Override
    protected String getStringValue() {
        return getValue().getValue();
    }


    @Override
    protected String getStringParam() {
        return getParam().getName();
    }
}
