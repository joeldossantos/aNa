package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class IntegerParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<IntegerParamType, Integer, P>{


    public IntegerParamType(Integer value) throws XMLException {
        super(value);
    }


    public IntegerParamType(P value) throws XMLException {
        super(value);
    }


    public IntegerParamType(String value) throws XMLException {
        super(value);
    }


    @Override
    protected P createParam(String param) {
        //@todo: criar método para procurar o parâmetro
        throw new UnsupportedOperationException("Not supported yet.");
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
