package br.pensario.link;

import br.pensario.NCLElement;
import java.util.Set;
import java.util.TreeSet;

import br.pensario.connector.NCLRole;
import br.pensario.descriptor.NCLDescriptor;
import br.pensario.interfaces.NCLInterface;
import br.pensario.node.NCLNode;
import java.util.Iterator;


/**
 * Esta classe define o elemento <i>bind</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um bind de um elo de um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.1
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 */
public class NCLBind<B extends NCLBind, R extends NCLRole, N extends NCLNode, I extends NCLInterface, D extends NCLDescriptor, P extends NCLParam>
        extends NCLElement implements Comparable<B>{

    private R role;
    private N component;
    private I interfac;
    private D descriptor;
    
    private Set<P> bindParams = new TreeSet<P>();
    
    
    /**
     * Atribui um papel ao bind.
     * 
     * @param role
     *          elemento representando o papel ao qual o bind será associado.
     */
    public void setRole(R role) {
        this.role = role;
    }
    
    
    /**
     * Retorna o papel relacionado ao bind.
     * 
     * @return
     *          elemento representando o papel ao qual o bind será associado.
     */
    public R getRole() {
        return role;
    }
    
    
    /**
     * Atribui um nó ao bind.
     * 
     * @param component
     *          elemento representando o nó mapeado pelo bind.
     */
    public void setComponent(N component) {
        this.component = component;
    }
    
    
    /**
     * Retorna o nó atribuído ao bind.
     * 
     * @return
     *          elemento representando o nó mapeado pelo bind.
     */
    public N getComponent() {
        return component;
    }
    
    
    /**
     * Determina a interface do nó atribuído ao bind.
     * 
     * @param interfac
     *          elemento representando a interface do nó.
     */
    public void setInterface(I interfac) {
        this.interfac = interfac;
    }
    
    
    /**
     * Retorna a interface do nó atribuído ao bind.
     * 
     * @return
     *          elemento representando a interface do nó.
     */
    public I getInterface() {
        return interfac;
    }
    
    
    /**
     * Atribui um descritor ao bind.
     * 
     * @param descriptor
     *          elemento representando o descritor a ser atribuido.
     */
    public void setDescriptor(D descriptor) {
        this.descriptor = descriptor;
    }
    
    
    /**
     * Retorna o descritor atribuido ao bind.
     * 
     * @return
     *          elemento representando o descritor a ser atribuido.
     */
    public D getDescriptor() {
        return descriptor;
    }
    
    
    /**
     * Adiciona um parâmetro ao bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser adicionado.
     * @return
     *          verdadeiro se o parâmetro foi adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addBindParam(P param) {
        return bindParams.add(param);
    }


    /**
     * Remove um parâmetro do bind.
     *
     * @param param
     *          elemento representando o parâmetro a ser removido.
     * @return
     *          verdadeiro se o parâmetro foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeBindParam(P param) {
        return bindParams.remove(param);
    }


    /**
     * Verifica se o bind possui um parâmetro.
     *
     * @param param
     *          elemento representando o parâmetro a ser verificado.
     * @return
     *          verdadeiro se o parâmetro existir.
     */
    public boolean hasBindParam(P param) {
        return bindParams.contains(param);
    }


    /**
     * Verifica se o bind possui algum parâmetro.
     *
     * @return
     *          verdadeiro se o bind possui algum parâmetro.
     */
    public boolean hasBindParam() {
        return !bindParams.isEmpty();
    }


    /**
     * Retorna os parâmetros do bind.
     *
     * @return
     *          objeto Iterable contendo os parâmetros do bind.
     */
    public Iterable<P> getBindParams() {
        return bindParams;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";
        
        
        // <bind> element and attributes declaration
        content = space + "<bind";
        content += " role='" + getRole().getName() + "'";
        content += " component='" + getComponent().getId() + "'";
        if(getInterface() != null)
            content += " interface='" + getInterface().getId() + "'";
        if(getDescriptor() != null)
            content += " descriptor='" + getDescriptor().getId() + "'";
        
        // <bind> element content
        if(hasBindParam()){
            content += ">\n";

            for(P param : bindParams)
                content += param.parse(ident + 1);
            
            content += space + "</bind>\n";
        }
        else
            content += "/>\n";
        
        return content;
    }
    
    
    public int compareTo(B other) {
        int comp = 0;

        String this_bind, other_bind;

        // Compara pelo role
        if(getRole() == null) this_bind = ""; else this_bind = getRole().getName();
        if(other.getRole() == null) other_bind = ""; else other_bind = other.getRole().getName();
        comp = this_bind.compareTo(other_bind);

        // Compara pelo componente
        if(comp == 0){
            comp = getComponent().compareTo(other.getComponent());
        }

        // Compara pela interface
        if(comp == 0){
            comp = getInterface().compareTo(other.getInterface());
        }

        // Compara pelo descritor
        if(comp == 0){
            comp = getDescriptor().compareTo(other.getDescriptor());
        }

        // Compara o número de parâmetros
        if(comp == 0)
            comp = bindParams.size() - ((Set) other.getBindParams()).size();

        // Compara os parâmetros
        if(comp == 0){
            Iterator it = other.getBindParams().iterator();
            for(P param : bindParams){
                P other_param = (P) it.next();
                comp = param.compareTo(other_param);
                if(comp != 0)
                    break;
            }
        }


        if(comp != 0)
            return 1;
        else
            return 0;
    }

}
