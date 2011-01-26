package br.pensario;

import org.xml.sax.Attributes;


/**
 * Esta interface define a interface básica dos elementos da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public interface Element {

    /**
     * Atribui um elemento pai ao elemento NCL.
     *
     * @param parent
     *          elemento NCL representando o elemento pai.
     * @return
     *          verdadeiro se o elemento pai foi atribuido. Caso o elemento já possua um elemento pai, o retorno será falso.
     */
    public boolean setParent(NCLElement parent);


    /**
     * Retorna o elemento pai do elemento NCL.
     *
     * @return
     *          elemento NCL representando o elemento pai.
     */
    public NCLElement getParent();


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
    public String parse(int ident);


    /**
     * Valida o elemento da <i>Nested Context Language</i> (NCL) criado.<br>
     * Verifica se o elemento está de acordo com o padrão da linguagem.
     *
     * @return
     *          verdadeiro se o elemento estiver de acordo com o padrão.
     */
    public boolean validate();


    /**
     * Adiciona uma mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param warning
     *          String contendo a mensagem de aviso.
     */
    public void addWarning(String warning);


    /**
     * Adiciona mensagens de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param warnings
     *          Lista contendo mensagens de aviso.
     */
    public void addWarning(Iterable<String> warnings);


    /**
     * Retorna a mensagem de aviso relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de aviso.
     */
    public Iterable<String> getWarnings();


    /**
     * Limpa a lista de mensagens de aviso.
     */
    public void cleanWarnings();


    /**
     * Adiciona uma mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param error
     *          String contendo a mensagem de erro.
     */
    public void addError(String error);


    /**
     * Adiciona mensagens de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @param errors
     *          Lista contendo mensagens de erro.
     */
    public void addError(Iterable<String> errors);


    /**
     * Retorna a mensagem de erro relacionado ao elemento NCL em questão.
     * Uma mensagem será adicionada durante a recuperação do
     * arquivo XML ou da validação feita pela api.
     *
     * @return
     *          lista de mensagens de erro.
     */
    public Iterable<String> getErrors();


    /**
     * Limpa a lista de mensagens de erro.
     */
    public void cleanErrors();


    /**
     * Implementa o método startElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes);


    /**
     * Implementa o método endElement do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    public void endElement(String uri, String localName, String qName);


    /**
     * Implementa o método endDocument do parser SAX para a recuperação dos objetos
     * representativos dos elementos NCL a partir de um arquivo XML.
     */
    public void endDocument();
}
