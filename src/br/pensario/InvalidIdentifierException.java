package br.pensario;

public class InvalidIdentifierException extends Exception{
    
    public InvalidIdentifierException()
    {
        super();
    }
    
    public InvalidIdentifierException(String msg)
    {
        super(msg);
    }
    
    public InvalidIdentifierException(Throwable ex)
    {
        super(ex);
    }
    
    public InvalidIdentifierException(String msg, Throwable ex)
    {
        super(msg,ex);
    }

}
