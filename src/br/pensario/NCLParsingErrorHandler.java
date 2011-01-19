package br.pensario;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * Esta classe implementa a interface do tratador de erros do parser SAX.<br>
 *
 * @see <a
 *      href="http://download.oracle.com/javase/1.4.2/docs/api/org/xml/sax/ErrorHandler.html">Error Handler</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLParsingErrorHandler implements ErrorHandler {

    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("WARNING: " + exception.getMessage());
    }

    public void error(SAXParseException exception) throws SAXException {
        System.out.println("ERROR: " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("FATALERROR: " + exception.getMessage());
    }
}
