package br.pensario;


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
public abstract class NCLElement implements Element {

    private NCLElement parent;


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
}
