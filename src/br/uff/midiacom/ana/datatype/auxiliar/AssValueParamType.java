package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class AssValueParamType<P extends NCLConnectorParamPrototype, O extends NCLElement> extends ParameterizedValueType<AssValueParamType, O, AssValueType, P>{

    
    public AssValueParamType(AssValueType value) throws XMLException {
        super(value);
    }


    public AssValueParamType(P value) throws XMLException {
        super(value);
    }
    
    
    public AssValueParamType(String value) throws XMLException {
        super(value);
    }
    
    
    public AssValueParamType(String value, O owner) throws XMLException {
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
    protected AssValueType createValue(String value) throws XMLException {
        return new AssValueType(value);
    }


    @Override
    protected String getStringValue() {
        if(getValue() == null)
            return null;
        else
            return getValue().parse();
    }


    @Override
    protected String getStringParam() {
        if(getParam() == null)
            return null;
        else
            return getParam().getName();
    }
}
