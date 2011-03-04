package br.pensario.descriptor;

import br.pensario.IdentifiableElement;


/**
 * Esta classe define um descritor de layout da <i>Nested Context Language</i> (NCL).
 * Este descritor pode ser um descritor simples ou um switch de descritor.<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public interface NCLLayoutDescriptor<T> extends Comparable<T>, IdentifiableElement {

}
