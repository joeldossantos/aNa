package br.uff.midiacom.ana;


import br.uff.midiacom.ana.util.exception.NCLParsingException;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLLoader {

    private Document doc;
    
    
    public XMLLoader(String xml) throws NCLParsingException {
        try{
            ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            fac.setNamespaceAware(true);
            DocumentBuilder builder = fac.newDocumentBuilder();
            doc = builder.parse(is);
            is.close();
        }
        catch(Exception e){
            throw new NCLParsingException(e.fillInStackTrace());
        }
    }

    public Element getElement() {
        return doc.getDocumentElement();
    }
}
