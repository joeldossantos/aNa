package br.uff.midiacom.ana.datatype.aux.parameterized;

import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCausalConnectorPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class IntegerParamType<P extends NCLConnectorParamPrototype,
                              O extends NCLElement,
                              Ip extends NCLImportPrototype,
                              R extends ReferenceType<O, P, Ip>>
        extends ParameterizedValueType<IntegerParamType, O, P, Integer, NCLElementAttributes, R> {

    
    public IntegerParamType(Integer value) throws XMLException {
        super(value);
    }


    public IntegerParamType(R value) throws XMLException {
        super(value);
    }
    
    
    public IntegerParamType(String value) throws XMLException {
        super(value);
    }


    @Override
    protected R createParam(String param, O owner) throws XMLException {
        NCLElement connector = (NCLElement) owner.getParent();
        while(!(connector instanceof NCLCausalConnectorPrototype)){
            connector = (NCLElement) connector.getParent();
            if(connector == null)
                throw new NCLParsingException("Could not find a parent connector");
        }
        
        P par = (P) ((NCLCausalConnectorPrototype) connector).getConnectorParams().get(param);
        if(par == null)
            throw new NCLParsingException("Could not find a param in connector with name: " + param);
        
        R ref = (R) new ReferenceType(par, NCLElementAttributes.NAME);
        return ref;
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
            return getParam().getTarget().getName();
    }
}
