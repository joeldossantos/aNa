package br.pensario;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


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

    private XMLReader reader;


    public NCLParsingErrorHandler(XMLReader reader) {
        this.reader = reader;
    }


    public void warning(SAXParseException exception) throws SAXException {
        NCLElement el = (NCLElement) reader.getContentHandler();
        el.addWarning("PARSER WARNING: " + exception.getMessage());
    }


    public void error(SAXParseException exception) throws SAXException {
        NCLElement el = (NCLElement) reader.getContentHandler();
        el.addError("PARSER ERROR: " + exception.getMessage());
    }


    public void fatalError(SAXParseException exception) throws SAXException {
        NCLElement el = (NCLElement) reader.getContentHandler();
        el.addError("PARSER FATALERROR: " + exception.getMessage());
    }
}
