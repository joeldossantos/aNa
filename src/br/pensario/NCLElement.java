package br.pensario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Esta classe define um elemento da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 * @see <a href="../README.html">Detalhes da API NCL</a>
 *
 */
public abstract class NCLElement extends DefaultHandler implements Element {

    private NCLElement parent;
    private XMLReader reader;
    private List<String> warnings = new ArrayList<String>();
    private List<String> errors = new ArrayList<String>();


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
     * Adiciona uma mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     * 
     * @param warning
     *          String contendo a mensagem de aviso.
     */
    public void addWarning(String warning) {
        warnings.add(warning);
    }


    /**
     * Adiciona mensagens de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param warnings
     *          Lista contendo mensagens de aviso.
     */
    public void addWarning(Iterable<String> warnings) {
        this.warnings.addAll((Collection<? extends String>) warnings);
    }


    /**
     * Retorna a mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de aviso.
     */
    public Iterable<String> getWarnings() {
        return warnings;
    }


    /**
     * Limpa a lista de mensagens de aviso.
     */
    public void cleanWarnings() {
        warnings.clear();
    }


    /**
     * Adiciona uma mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param error
     *          String contendo a mensagem de erro.
     */
    public void addError(String error) {
        errors.add(error);
    }


    /**
     * Adiciona mensagens de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param errors
     *          Lista contendo mensagens de erro.
     */
    public void addError(Iterable<String> errors) {
        this.errors.addAll((Collection<? extends String>) errors);
    }


    /**
     * Retorna a mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de erro.
     */
    public Iterable<String> getErrors() {
        return errors;
    }


    /**
     * Limpa a lista de mensagens de erro.
     */
    public void cleanErrors() {
        errors.clear();
    }


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
        if(getParent() != null)
            getReader().setContentHandler(getParent());
    }


    /**
     * Implementa o método endDocument do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    @Override
    public void endDocument() {}
}
