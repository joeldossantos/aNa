package br.pensario;

/*
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 * @see <a href="../README.html">Detalhes da API NCL</a>
 *
 */

public class NCLInvalidIdentifierException extends Exception {

    
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
