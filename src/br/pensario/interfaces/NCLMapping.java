package br.pensario.interfaces;

import br.pensario.NCLElement;
import br.pensario.node.NCLNode;


/**
 * Esta classe define um mapeamento da porta do switch da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLMapping<M extends NCLMapping, N extends NCLNode, I extends NCLInterface> extends NCLElement implements Comparable<M> {

    private N component;
    private I interfac;


    /**
     * Determina o componente mapeado pelo mapeamento.
     *
     * @param component
     *          elemento representando o componente mapeado.
     */
    public void setComponent(N component) {
        this.component = component;
    }


    /**
     * Retorna o componente mapeado pelo mapeamento.
     *
     * @return
     *          elemento representando o componente mapeado.
     */
    public N getComponent() {
        return component;
    }


    /**
     * Determina a interface mapeada pelo mapeamento.
     *
     * @param interfac
     *          elemento representando a interface mapeada.
     */
    public void setInterface(I interfac) {
        this.interfac = interfac;
    }


    /**
     * Retorna a interface mapeada pelo mapeamento.
     *
     * @return
     *          elemento representando a interface mapeada.
     */
    public I getInterface() {
        return interfac;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // param element and attributes declaration
        content = space + "<mapping";

        content += " component='" + getComponent().getId() + "'";
        content += " interface='" + getInterface().getId() + "'";
        content += "/>\n";

        return content;
    }

    public int compareTo(M other) {
        int comp = 0;

        // Compara pelo componente
        comp = getComponent().compareTo(other.getComponent());

        // Compara pela interface
        if(comp == 0){
            comp = getInterface().compareTo(other.getInterface());
        }

        return comp;
    }

}
