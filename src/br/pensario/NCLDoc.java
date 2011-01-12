package br.pensario;

import br.pensario.NCLValues.NCLNamespace;


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
     * Construtor do elemento <i>ncl</i> da <i>Nested Context Language</i> (NCL).
     * 
     * @param xmlns
     *          namespace usado pelo documento NCL.
     */
    public NCLDoc(NCLNamespace xmlns) {
        setXmlns(xmlns);
    }    


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

}
