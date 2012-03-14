package br.uff.midiacom.ana.datatype.aux.parameterized;

import br.uff.midiacom.ana.datatype.aux.basic.AssValueType;
import br.uff.midiacom.ana.datatype.aux.reference.ConParamReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLCausalConnectorPrototype;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.parameterized.ParameterizedValueType;


public class AssValueParamType<P extends NCLConnectorParamPrototype,
                               O extends NCLElement,
                               R extends ConParamReference>
        extends ParameterizedValueType<AssValueParamType, O, AssValueType, NCLElementAttributes, R> {

    
    public AssValueParamType(AssValueType value) throws XMLException {
        super(value);
    }


    public AssValueParamType(R value) throws XMLException {
        super(value);
    }
    
    
    public AssValueParamType(String value) throws XMLException {
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
        
        return createParamRef(par);
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
            return getParam().parse();
    }


    /**
     * Function to create a reference to element <i>connectorParam</i>.
     * This function must be overwritten in classes that extends this one.
     *
     * @return
     *          element representing a reference to element <i>connectorParam</i>.
     */
    protected R createParamRef(P ref) throws XMLException {
        return (R) new ConParamReference(ref, NCLElementAttributes.NAME);
    }
}
