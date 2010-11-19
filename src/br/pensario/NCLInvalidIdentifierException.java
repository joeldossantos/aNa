package br.pensario;


public class NCLInvalidIdentifierException extends Exception{

    
    public NCLInvalidIdentifierException() {
        super();
    }


    public NCLInvalidIdentifierException(String msg) {
        super(msg);
    }


    public NCLInvalidIdentifierException(Throwable ex) {
        super(ex);
    }


    public NCLInvalidIdentifierException(String msg, Throwable ex) {
        super(msg,ex);
    }
}
