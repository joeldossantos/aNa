package br.pensario.transition;

import br.pensario.NCLIdentifiableElement;
import br.pensario.reuse.NCLImport;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define uma base de transições da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLTransitionBase<T extends NCLTransition, I extends NCLImport> extends NCLIdentifiableElement {

    private Set<T> transitions = new TreeSet<T>();
    private Set<I> imports = new TreeSet<I>();


    /**
     * Adiciona uma transição a base de transições.
     *
     * @param rule
     *          elemento representando a transição a ser adicionada.
     * @return
     *          verdadeiro se a transição foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addTransition(T rule) {
        return transitions.add(rule);
    }


    /**
     * Remove uma transição da base de transições.
     *
     * @param rule
     *          elemento representando a transição a ser removida.
     * @return
     *          verdadeiro se a transição foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeTransition(T rule) {
        return transitions.remove(rule);
    }


    /**
     * Verifica se a base de transições possui uma transição.
     *
     * @param rule
     *          elemento representando a transição a ser verificada.
     * @return
     *          verdadeiro se a transição existir.
     */
    public boolean hasTransition(T rule) {
        return transitions.contains(rule);
    }


    /**
     * Verifica se a base de transições possui alguma transição.
     *
     * @return
     *          verdadeiro se a base de transições possui alguma transição.
     */
    public boolean hasTransition() {
        return !transitions.isEmpty();
    }


    /**
     * Retorna as transições da base de transições.
     *
     * @return
     *          objeto Iterable contendo as transições da base de transições.
     */
    public Iterable<T> getTransitions() {
        return transitions;
    }


    /**
     * Adiciona um importador de base à base de transições.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(I importBase) {
        return imports.add(importBase);
    }


    /**
     * Remove um importador de base da base de transições.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(I importBase) {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de transições contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de transições possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de transições possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de transições.
     *
     * @return
     *          objeto Iterable contendo os importadores de base da base de transições.
     */
    public Iterable<I> getImportBases() {
        return imports;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<transitionBase";

        if (getId() != null)
            content += " id='" + getId() + "'";

        content += ">\n";

        if(hasImportBase()){
            for(I imp : imports)
                content += imp.parse(ident + 1);
        }

        if(hasTransition()){
            for(T transition : transitions)
                content += transition.parse(ident + 1);
        }

        content += space + "</transitionBase>\n";

        return content;
    }
}
