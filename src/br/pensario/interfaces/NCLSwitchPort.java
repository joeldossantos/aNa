package br.pensario.interfaces;

import br.pensario.NCLIdentifiableElement;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define uma porta de um switch da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLSwitchPort<I extends NCLInterface, M extends NCLMapping> extends NCLIdentifiableElement implements NCLInterface<I> {

    private Set<M> mappings = new TreeSet<M>();


    /**
     * Adiciona um mapeamento a porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser adicionado.
     * @return
     *          Verdadeiro se o mapeamento foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addMapping(M mapping) {
        return mappings.add(mapping);
    }


    /**
     * Remove um mapeamento da porta de switch.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser removido.
     * @return
     *          Verdadeiro se o mapeamento foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeMapping(M mapping) {
        return mappings.remove(mapping);
    }


    /**
     * Verifica se a porta de switch possui um mapeamento.
     *
     * @param mapping
     *          elemento representando o mapeamento a ser verificado.
     * @return
     *          verdadeiro se o mapeamento existir.
     */
    public boolean hasMapping(M mapping) {
        return mappings.contains(mapping);
    }


    /**
     * Verifica se a porta de switch possui algum mapeamento.
     *
     * @return
     *          verdadeiro se a porta de switch possuir algum mapeamento.
     */
    public boolean hasMapping() {
        return !mappings.isEmpty();
    }


    /**
     * Retorna os mapeamentos da porta de switch.
     *
     * @return
     *          objeto Iterable contendo os mapeamentos da porta de switch.
     */
    public Iterable<M> getMappings() {
        return mappings;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";


        // <port> element and attributes declaration
        content = space + "<switchPort";
        content += " id='" + getId() + "'";
        content += ">\n";

        if(hasMapping()){
            for(M mapping : mappings)
                content += mapping.parse(ident + 1);
        }

        content += "</switchPort>\n";

        return content;
    }


    public int compareTo(I other) {
        return getId().compareTo(other.getId());
    }
}
