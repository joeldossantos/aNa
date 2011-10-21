package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class AssValueParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<AssValueParamType, AssValueType, P>{


    public AssValueParamType(AssValueType value) throws XMLException {
        super(value);
    }


    public AssValueParamType(P value) throws XMLException {
        super(value);
    }


    public AssValueParamType(String value) throws XMLException {
        super(value);
    }


    @Override
    protected P createParam(String param) {
        //@todo: criar método para procurar o parâmetro
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    protected AssValueType createValue(String value) throws XMLException {
        return new AssValueType(value);
    }


    @Override
    protected String getStringValue() {
        return getValue().parse();
    }


    @Override
    protected String getStringParam() {
        return getParam().getName();
    }
}
