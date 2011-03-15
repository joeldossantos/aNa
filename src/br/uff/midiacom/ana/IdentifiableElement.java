package br.uff.midiacom.ana;


/**
 * Esta interface define a interface básica dos elementos que tem um identificador da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
