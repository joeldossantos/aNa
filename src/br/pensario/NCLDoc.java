package br.pensario;

import br.pensario.NCLValues.NCLNamespace;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Esta classe define o elemento <i>ncl</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento raiz de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 */
public class NCLDoc<H extends NCLHead, B extends NCLBody> extends NCLIdentifiableElement {

    private String title;
    private NCLNamespace xmlns; //atributo obrigatório

    private H head;
    private B body;

    
    /**
     * Determina o título do documento NCL.
     * 
     * @param title
     *          String contendo o título a ser atribuído ao documento.
     *
     * @throws java.lang.IllegalArgumentException
     *          Se o título a ser atribuído for uma String vazia.
     */
    public void setTitle(String title) throws IllegalArgumentException {
        if(title != null && "".equals(title.trim()))
            throw new IllegalArgumentException("Empty title String");
        
        
        this.title = title;
    }


    /**
     * Retorna o título do documento NCL.
     * 
     * @return
     *          String contendo o título do documento NCL.
     */
    public String getTitle() {
        return title;
    }


    /**
     * Determina o namespace utilizado pelo documento NCL.
     * 
     * @param xmlns
     *          namespace usado pelo documento NCL.
     */
    public void setXmlns(NCLNamespace xmlns) {
        this.xmlns = xmlns;
    }


    /**
     * Retorna o namespace utilizado pelo documento NCL.
     * 
     * @return
     *          NCLNamespace representando o namespace usado pelo documento NCL.
     */
    public NCLNamespace getXmlns() {
        return xmlns;
    }


    /**
     * Atribui um cabeçalho ao documento NCL.
     * 
     * @param head
     *          elemento representando o cabeçalho do documento NCL.
     */
    public void setHead(H head) {
        this.head = head;
    }


    /**
     * Retorna o cabeçalho do documento NCL.
     * 
     * @return
     *          elemento representando o cabeçalho do documento NCL.
     */
    public H getHead() {
        return head;
    }


    /**
     * Atribui um corpo ao documento NCL.
     * 
     * @param body
     *          elemento representando o corpo do documento NCL.
     */
    public void setBody(B body) {
        this.body = body;
    }


    /**
     * Retorna o corpo de um documento NCL.
     * 
     * @return
     *          elemento representando o cabeçalho do documento NCL.
     */
    public B getBody() {
        return body;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        // XML document start declaration
        content = space + "<?xml version='1.0' encoding='ISO-8859-1'?>\n";

        content += space + "<!-- Generated with NCL API -->\n\n";

        // <ncl> element and attributes declaration
        content += space + "<ncl";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getTitle() != null)
            content += " title='" + getTitle() + "'";
        if(getXmlns() != null)
            content += " xmlns='" + getXmlns() + "'";
        content += ">\n";

        // <ncl> element content
        if(getHead() != null)
            content += getHead().parse(ident + 1);
        if(getBody() != null)
            content += getBody().parse(ident + 1);

        // <ncl> element end declaration
        content += space + "</ncl>\n";

        return content;
    }


    public boolean validate() {
        boolean valid = true;

        // Documento nao pode ser vazio
        valid &= (getHead() != null || getBody() != null);
        
        if(getHead() != null)
            valid &= getHead().validate();
        if(getBody() != null)
            valid &= getBody().validate();

        return valid;
    }


    /**
     * Recupera a estrutura de classes que representam elementos NCL a partir
     * de um arquivo XML especificado de acordo com a linguagem NCL.
     *
     * @param path
     *          String contendo o caminho absoluto para o arquivo XML.
     * @throws NCLParsingException
     *          se algum erro ocorrer durante a recuperação do arquivo.
     */
    public void loadXML(String path) throws NCLParsingException {
        try{
            URI fileURI = new URI(path);
            setReader(XMLReaderFactory.createXMLReader());

            getReader().setContentHandler(this);
            getReader().setErrorHandler(new NCLParsingErrorHandler(getReader()));

            FileReader r = new FileReader(fileURI.toString());
            getReader().parse(new InputSource(r));
        }
        catch(URISyntaxException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(SAXException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(FileNotFoundException ex){
            throw new NCLParsingException(ex.getMessage());
        }
        catch(IOException ex){
            throw new NCLParsingException(ex.getMessage());
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("ncl")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("title"))
                        setTitle(attributes.getValue(i));
                }
                if(!uri.equals("")){
                    for(NCLNamespace ns : NCLNamespace.values()){
                        if(ns.toString().equals(uri))
                            setXmlns(ns);
                    }
                }
            }
            else if(localName.equals("head")){
                setHead((H) new NCLHead(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
                getHead().startElement(uri, localName, qName, attributes);
            }
            else if(localName.equals("body")){
                setBody((B) new NCLBody(getReader(), this)); //TODO: retirar o cast. Como melhorar isso?
                getBody().startElement(uri, localName, qName, attributes);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getHead() != null){
            getHead().endDocument();
            addWarning(getHead().getWarnings());
            addError(getHead().getErrors());
        }
        if(getBody() != null){
            getBody().endDocument();
            addWarning(getBody().getWarnings());
            addError(getBody().getErrors());
        }
    }
}
