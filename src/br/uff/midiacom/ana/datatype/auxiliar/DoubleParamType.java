package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class DoubleParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<DoubleParamType, Double, P>{


    public DoubleParamType(Double value) throws XMLException {
        super(value);
    }


    public DoubleParamType(P value) throws XMLException {
        super(value);
    }


    public DoubleParamType(String value) throws XMLException {
        super(value);
    }


    @Override
    protected P createParam(String param) {
        //@todo: criar método para procurar o parâmetro
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    protected Double createValue(String value) throws XMLException {
        return new Double(value);
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
