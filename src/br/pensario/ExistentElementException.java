package br.pensario;

public class ExistentElementException extends Exception{
    
    public ExistentElementException()
    {
        super();
    }
    
    public ExistentElementException(String msg)
    {
        super(msg);
    }
    
    public ExistentElementException(Throwable ex)
    {
        super(ex);
    }
    
    public ExistentElementException(String msg, Throwable ex)
    {
        super(msg,ex);
    }

}
