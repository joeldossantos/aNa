package br.pensario;


/**
 * Esta interface define a interface básica dos elementos que tem um identificador da <i>Nested Context Language</i> (NCL).<br>
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
public interface IdentifiableElement extends Element {

    /**
     * Determina o identificador único do elemento da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          String representando o atributo <i>id</i> ou <i>name</i> do elemento.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador não for válido.
     */
    public void setId(String id) throws NCLInvalidIdentifierException;


    /**
     * Retorna o identificador único do elemento da <i>Nested Context Language</i> (NCL).
     * Este identificador pode representar o atributo <i>id</i> ou <i>name</i> do elemento.
     *
     * @return
     *          String representando o identificador do elemento.
     */
    public String getId();
}
