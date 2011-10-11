package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class ByParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<ByParamType, ByType, P>{


    public ByParamType(ByType value) throws XMLException {
        super(value);
    }


    public ByParamType(P value) throws XMLException {
        super(value);
    }


    public ByParamType(String value) throws XMLException {
        super(value);
    }


    @Override
    protected P createParam(String param) {
        //@todo: criar método para procurar o parâmetro
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    protected ByType createValue(String value) throws XMLException {
        return new ByType(value);
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
