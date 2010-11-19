package br.pensario.node;

import br.pensario.NCLIdentifiableElement;


/**
 * Esta classe define um nó da <i>Nested Context Language</i> (NCL).<br>
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
public abstract class NCLNode extends NCLIdentifiableElement implements Comparable<NCLNode> {


    public int compareTo(NCLNode other) {
        return getId().compareTo(other.getId());
    }
}
