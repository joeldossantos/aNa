package br.pensario;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Esta classe define um elemento da <i>Nested Context Language</i> (NCL).<br>
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
public abstract class NCLElement extends DefaultHandler implements Element {

    private NCLElement parent;
    private XMLReader reader;


    /**
     * Atribui um elemento pai ao elemento NCL.
     *
     * @param parent
     *          elemento NCL representando o elemento pai.
     * @return
     *          verdadeiro se o elemento pai foi atribuido. Caso o elemento já possua um elemento pai, o retorno será falso.
     */
    public boolean setParent(NCLElement parent) {
        if(this.parent != null && parent != null)
            return false;

        this.parent = parent;
        return true;
    }


    /**
     * Retorna o elemento pai do elemento NCL.
     *
     * @return
     *          elemento NCL representando o elemento pai.
     */
    public NCLElement getParent() {
        return parent;
    }


    /**
     * Atribui um leitor XML ao elemento NCL.
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @return
     *          verdadeiro se o leitor XML foi atribuido. Caso o elemento já possua um leitor, o retorno será falso.
     */
    public boolean setReader(XMLReader reader) {
        if(this.reader != null && reader != null)
            return false;

        this.reader = reader;
        return true;
    }


    /**
     * Retorna o leitor XML do elemento NCL.
     *
     * @return
     *          elemento representando o leitor XML do parser SAX.
     */
    public XMLReader getReader() {
        return reader;
    }


    /**
     * Cria o código XML do elemento da <i>Nested Context Language</i> (NCL).<br>
     * 
     * @param ident 
     *          Inteiro indicando o nível de indentação do elemento, ou seja,
     *          o número de tabulações usadas na indentação do elemento.
     *
     * @return
     *          String contendo o código XML do elemento.
     */
    public abstract String parse(int ident);


    /**
     * Valida o elemento da <i>Nested Context Language</i> (NCL) criado.<br>
     * Verifica se o elemento está de acordo com o padrão da linguagem.
     *
     * @return
     *          verdadeiro se o elemento estiver de acordo com o padrão.
     */
    public abstract boolean validate();


    /**
     * Implementa o método startElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    @Override
    public abstract void startElement(String uri, String localName, String qName, Attributes attributes);


    /**
     * Implementa o método endElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        getReader().setContentHandler(getParent());
    }
}
