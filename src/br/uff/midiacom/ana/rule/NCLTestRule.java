package br.pensario.rule;

import br.pensario.IdentifiableElement;


/**
 * Esta classe define uma regra de teste da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public interface NCLTestRule<T> extends IdentifiableElement, Comparable<T> {

}
