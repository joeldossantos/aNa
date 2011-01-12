package br.pensario.descriptor;

import br.pensario.IdentifiableElement;


/**
 * Esta classe define um descritor de layout da <i>Nested Context Language</i> (NCL).
 * Este descritor pode ser um descritor simples ou um switch de descritor.<br>
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
public interface NCLLayoutDescriptor<T> extends Comparable<T>, IdentifiableElement {

}
