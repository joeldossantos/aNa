package br.pensario.link;

import br.pensario.NCLIdentifiableElement;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLCausalConnector;


/**
 * Esta classe define o elemento <i>link</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um elo de um documento NCL.<br>
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
public class NCLLink extends NCLIdentifiableElement implements Comparable<NCLLink>{

    private NCLCausalConnector xconnector;
    
    private Set<NCLParam> linkParams = new TreeSet();
    private Set<NCLBind> binds = new TreeSet();
    
    
    /**
     * Atribui um conector ao link.
     * 
     * @param xconnector
     *          conector a ser atribuido ao link.
     */
    public void setXconnector(NCLCausalConnector xconnector) {
        this.xconnector = xconnector;
    }
    
    
    /**
     * Retorna o conector do link.
     * 
     * @return
     *          conector atribuido ao link.
     */
    public NCLCausalConnector getXconnector() {
        return xconnector;
    }
    
    
    /**
     * Adiciona um parâmetro ao link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addLinkParam(NCLParam param) {
        return linkParams.add(param);
    }
    
    
    /**
     * Remove um parâmetro do link.
     * 
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeLinkParam(NCLParam param) {
        return linkParams.remove(param);
    }
    
    
    /**
     * Verifica se o link possui um parâmetro.
     * 
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasLinkParam(NCLParam param) {
        return linkParams.contains(param);
    }
    
    
    /**
     * Verifica se o link possui algum parâmetro.
     * 
     * @return
     *          verdadeiro se o link possui algum parâmetro.
     */
    public boolean hasLinkParam() {
        return !linkParams.isEmpty();
    }
    
    
    /**
     * Retorna os parâmetros do link.
     * 
     * @return
     *          objeto Iterable contendo os parâmetros do link.
     */
    public Iterable<NCLParam> getLinkParams() {
        return linkParams;
    }
    
    
    /**
     * Adiciona um bind ao link.
     * 
     * @param bind
     *          elemento representando o bind a ser adicionado.
     * @return
     *          verdadeiro se o bind for adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBind(NCLBind bind) {
        return binds.add(bind);
    }
    
    
    /**
     * Remove um bind do link.
     * 
     * @param bind
     *          elemento representando o bind a ser removido.
     * @return
     *          verdadeiro se o bind for removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBind(NCLBind bind) {
        return binds.remove(bind);
    }
    
    
    /**
     * Verifica se o link possui um bind.
     * 
     * @param bind
     *          elemento representando o bind a ser verificado.
     * @return
     *          verdadeiro se o bind existir.
     */
    public boolean hasBind(NCLBind bind) {
        return binds.contains(bind);
    }


    /**
     * Verifica se o link possui algum bind.
     *
     * @return
     *          verdadeiro se o link possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }
    
    
    /**
     * Retorna os binds do link
     * 
     * @return
     *          objeto Iterable contendo os binds do link.
     */
    public Iterable<NCLBind> getBinds() {
        return binds;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <link> element and attributes declaration
        content = space + "<link";
        if (getId() != null)
            content += " id='" + getId() + "'";
        content += " xconnector='" + getXconnector().getId() + "'";
        content += ">\n";
        
        // <link> element content
        if (hasLinkParam()){
            for (NCLParam param : linkParams)
                content += param.parse(ident + 1);
        }

        for (NCLBind bind : binds)
            content += bind.parse(ident + 1);
        
        // <link> element end declaration
        content += space + "</link>\n";
        
        return content;
    }


    public int compareTo(NCLLink other) {
        int comp = 0;

        String this_link, other_link;

        // Compara pelo xconnector
        if (getXconnector() == null) this_link = ""; else this_link = getXconnector().getId();
        if (other.getXconnector() == null) other_link = ""; else other_link = other.getXconnector().getId();
        comp = this_link.compareTo(other_link);

        // Compara o número de parâmetros
        if (comp == 0)
            comp = linkParams.size() - ((Set) other.getLinkParams()).size();

        // Compara o número de binds
        if (comp == 0)
            comp = binds.size() - ((Set) other.getBinds()).size();

        // Compara os parâmetros
        if (comp == 0){
            NCLParam params[] = (NCLParam[]) linkParams.toArray();
            NCLParam other_params[] = (NCLParam[]) ((Set) other.getLinkParams()).toArray();

            for (int i = 0; i < params.length; i++){
                comp = params[i].compareTo(other_params[i]);
                if (comp != 0)
                    break;
            }
        }

        // Compara os binds
        if (comp == 0){
            NCLBind bind[] = (NCLBind[]) binds.toArray();
            NCLBind other_bind[] = (NCLBind[]) ((Set) other.getBinds()).toArray();

            for (int i = 0; i < bind.length; i++){
                comp = bind[i].compareTo(other_bind[i]);
                if (comp != 0)
                    break;
            }
        }


        return comp;
    }
}
