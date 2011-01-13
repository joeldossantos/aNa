package br.pensario.node;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.interfaces.NCLSwitchPort;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define a apresentação de conteudos alternativos em um documento NCL.<br>
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
public class NCLSwitch<N extends NCLNode, S extends NCLSwitch, P extends NCLSwitchPort, B extends NCLBindRule> extends NCLIdentifiableElement implements NCLNode<N> {

    private S refer;
    private N defaultComponent;
    private Set<P> ports = new TreeSet<P>();
    private Set<B> binds = new TreeSet<B>();
    private Set<N> nodes = new TreeSet<N>();


    /**
     * Construtor do elemento <i>switch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch for inválido.
     */
    public NCLSwitch(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Atribui um switch para ser reutilizado pelo switch.
     *
     * @param refer
     *          elemento representando o switch a ser reutilizado.
     */
    public void setRefer(S refer) {
        this.refer = refer;
    }


    /**
     * Retorna o switch reutilizado pelo switch.
     *
     * @return
     *          elemento representando o switch a ser reutilizado.
     */
    public S getRefer() {
        return refer;
    }


    /**
     * Adiciona uma porta ao switch.
     *
     * @param port
     *          elemento representando a porta a ser adicionada.
     * @return
     *          Verdadeiro se a porta foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addPort(P port) {
        return ports.add(port);
    }


    /**
     * Remove uma porta do switch.
     *
     * @param id
     *          identificador da porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(String id) {
        for(P port : ports){
            if(port.getId().equals(id))
                return ports.remove(port);
        }
        return false;
    }


    /**
     * Remove uma porta do switch.
     *
     * @param port
     *          elemento representando a porta a ser removida.
     * @return
     *          Verdadeiro se a porta foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removePort(P port) {
        return ports.remove(port);
    }


    /**
     * Verifica se o switch possui uma porta.
     *
     * @param id
     *          identificador da porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(String id) {
        for(P port : ports){
            if(port.getId().equals(id))
                return true;
        }
        return false;
    }


    /**
     * Verifica se o switch possui uma porta.
     *
     * @param port
     *          elemento representando a porta a ser verificada.
     * @return
     *          verdadeiro se a porta existir.
     */
    public boolean hasPort(P port) {
        return ports.contains(port);
    }


    /**
     * Verifica se o switch possui alguma porta.
     *
     * @return
     *          verdadeiro se o switch possuir alguma porta.
     */
    public boolean hasPort() {
        return !ports.isEmpty();
    }


    /**
     * Retorna as portas do switch.
     *
     * @return
     *          objeto Iterable contendo as portas do switch.
     */
    public Iterable<P> getPorts() {
        return ports;
    }


    /**
     * Determina o componente padrão do switch.
     *
     * @param defaultComponent
     *          elemento representando o componente padrão.
     */
    public void setDefaultComponent(N defaultComponent) {
        this.defaultComponent = defaultComponent;
    }


    /**
     * Retorna o componente padrão do switch.
     *
     * @return
     *          elemento representando o componente padrão.
     */
    public N getDefaultComponent() {
        return defaultComponent;
    }


    /**
     * Adiciona um bind ao switch.
     *
     * @param bind
     *          elemento representando o bind a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBind(B bind) {
        return binds.add(bind);
    }


    /**
     * Remove um bind do switch.
     *
     * @param bind
     *          elemento representando o bind a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBind(B bind) {
        return binds.remove(bind);
    }


    /**
     * Verifica se o switch contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(B bind) {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch possui algum bind.
     *
     * @return
     *          verdadeiro se o switch possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch.
     *
     * @return
     *          objeto Iterable contendo os binds do switch.
     */
    public Iterable<B> getBinds() {
        return binds;
    }


    /**
     * Adiciona um nó ao switch.
     *
     * @param node
     *          elemento representando o nó a ser adicionado.
     * @return
     *          Verdadeiro se o nó foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addNode(N node) {
        return nodes.add(node);
    }


    /**
     * Remove um nó do switch.
     *
     * @param id
     *          identificador do nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(String id) {
        for(N node : nodes){
            if(node.getId().equals(id))
                return nodes.remove(node);
        }
        return false;
    }


    /**
     * Remove um nó do switch.
     *
     * @param node
     *          elemento representando um nó a ser removido.
     * @return
     *          Verdadeiro se o nó foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeNode(N node) {
        return nodes.remove(node);
    }


    /**
     * Verifica se o switch possui um nó.
     *
     * @param id
     *          identificador do nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(String id) {
        for(N node : nodes){
            if(node.getId().equals(id))
                return true;
        }
        return false;
    }


    /**
     * Verifica se o switch possui um nó.
     *
     * @param node
     *          elemento representando o nó a ser verificado.
     * @return
     *          verdadeiro se o nó existir.
     */
    public boolean hasNode(N node) {
        return nodes.contains(node);
    }


    /**
     * Verifica se o switch possui algum nó.
     *
     * @return
     *          verdadeiro se o switch possuir algum nó.
     */
    public boolean hasNode() {
        return (!nodes.isEmpty());
    }


    /**
     * Retorna os nós do switch.
     *
     * @return
     *          objeto Iterable contendo os nós do switch.
     */
    public Iterable<N> getNodes() {
        return nodes;
    }

    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<switch";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getRefer() != null)
            content += " refer='" + getRefer() + "'";

        if(hasPort() || hasBind() || hasNode()){
            content += ">\n";

            if(hasPort()){
                for(P port : ports)
                    content += port.parse(ident + 1);
            }

            if(hasBind()){
                for(B bind : binds)
                    content += bind.parse(ident + 1);
            }

            if(getDefaultComponent() != null)
                content += space + "\t" + "<defaultComponent component='" + getDefaultComponent().getId() + "'/>\n";

            if(hasNode()){
                for(N node : nodes)
                    content += node.parse(ident + 1);
            }

            content += space + "</switch>\n";
        }
        else
            content += "/>\n";

        return content;
    }

    public int compareTo(N other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        if(getRefer() != null)
            valid &= (getRefer().compareTo(this) != 0);

        if(hasPort()){
            for(P port : ports)
                valid &= port.validate();
        }
        if(hasBind()){
            for(B bind : binds)
                valid &= bind.validate();
        }
        if(hasNode()){
            for(N node : nodes)
                valid &= node.validate();
        }

        return valid;
    }
}
