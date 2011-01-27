package br.pensario;

/*
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 * @see <a href="../README.html">Detalhes da API NCL</a>
 *
 */

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