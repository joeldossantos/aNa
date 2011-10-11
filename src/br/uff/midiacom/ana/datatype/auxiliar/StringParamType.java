package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.ncl.connector.NCLConnectorParamPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import br.uff.midiacom.xml.parameterized.ParameterizedValueType;


public class StringParamType<P extends NCLConnectorParamPrototype> extends ParameterizedValueType<StringParamType, StringType, P>{

    
    public StringParamType(StringType value) throws XMLException {
        super(value);
    }


    public StringParamType(P value) throws XMLException {
        super(value);
    }


    public StringParamType(String value) throws XMLException {
        super(value);
    }
    

    @Override
    protected P createParam(String param) {
        //@todo: criar método para procurar o parâmetro
        throw new UnsupportedOperationException("Not supported yet.");
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
