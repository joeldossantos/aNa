package br.pensario;


public class NCLParsingException extends Exception {


    public NCLParsingException() {
        super();
    }


    public NCLParsingException(String msg) {
        super(msg);
    }


    public NCLParsingException(Throwable ex) {
        super(ex);
    }


    public NCLParsingException(String msg, Throwable ex) {
        super(msg,ex);
    }
}