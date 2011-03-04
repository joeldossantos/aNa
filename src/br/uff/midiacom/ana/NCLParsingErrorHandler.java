package br.uff.midiacom.ana;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


/**
 * Esta classe implementa a interface do tratador de erros do parser SAX.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
