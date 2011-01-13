package br.pensario.descriptor;

import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um switch de descritor em um documento NCL.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.0
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 */
public class NCLDescriptorSwitch<D extends NCLDescriptor, B extends NCLBindRule, L extends NCLLayoutDescriptor> extends NCLIdentifiableElement implements NCLLayoutDescriptor<L> {

    private Set<D> descriptors = new TreeSet<D>();
    private Set<B> binds = new TreeSet<B>();
    private D defaultDescriptor;


    /**
     * Construtor do elemento <i>descriptorSwitch</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador do switch de descritor.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador do switch de descritor não for válido.
     */
    public NCLDescriptorSwitch(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Adiciona um descritor ao switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addDescriptor(D descriptor) {
        return descriptors.add(descriptor);
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param id
     *          identificador do descritor a ser removido.
     * @return
     *          Verdadeiro se o descritor foi removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(String id) {
        for(D descriptor : descriptors){
            if(descriptor.getId().equals(id))
                return descriptors.remove(descriptor);
        }
        return false;
    }


    /**
     * Remove um descritor do switch de descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeDescriptor(D descriptor) {
        return descriptors.remove(descriptor);
    }


    /**
     * Verifica se o switch de descritor contém um descritor.
     *
     * @param descriptor
     *          elemento representando o descritor a ser verificado.
     */
    public boolean hasDescriptor(D descriptor) {
        return descriptors.contains(descriptor);
    }


    /**
     * Verifica se o switch de descritor possui algum descritor.
     *
     * @return
     *          verdadeiro se o switch de descritor possuir algum descritor.
     */
    public boolean hasDescriptor() {
        return !descriptors.isEmpty();
    }


    /**
     * Retorna os descritores do switch de descritor.
     *
     * @return
     *          objeto Iterable contendo os descritores do switch de descritor.
     */
    public Iterable<D> getDescriptors() {
        return descriptors;
    }


    /**
     * Adiciona um bind ao switch de descritor.
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
     * Remove um bind do switch de descritor.
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
     * Verifica se o switch de descritor contém um bind.
     *
     * @param bind
     *          elemento representando o bind a ser verificado.
     */
    public boolean hasBind(B bind) {
        return binds.contains(bind);
    }


    /**
     * Verifica se o switch de descritor possui algum bind.
     *
     * @return
     *          verdadeiro se o switch de descritor possuir algum bind.
     */
    public boolean hasBind() {
        return !binds.isEmpty();
    }


    /**
     * Retorna os binds do switch de descritor.
     *
     * @return
     *          objeto Iterable contendo os binds do switch de descritor.
     */
    public Iterable<B> getBinds() {
        return binds;
    }


    /**
     * Determina o descritor padrão do switch de descritor.
     *
     * @param defaultDescriptor
     *          elemento representando o descritor padrão.
     */
    public void setDefaultDescriptor(D defaultDescriptor) {
        this.defaultDescriptor = defaultDescriptor;
    }


    /**
     * Retorna o descritor padrão do switch de descritor.
     *
     * @return
     *          elemento representando o descritor padrão.
     */
    public D getDefaultDescriptor() {
        return defaultDescriptor;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<descriptorSwitch";
        if(getId() != null)
            content += " id='" + getId() + "'";
        content += ">\n";

        if(hasBind()){
            for(B bind : binds)
                content += bind.parse(ident + 1);
        }

        if(getDefaultDescriptor() != null)
            content += space + "\t" + "<defaultDescriptor descriptor='" + getDefaultDescriptor().getId() + "'/>\n";

        if(hasDescriptor()){
            for(D descriptor : descriptors)
                content += descriptor.parse(ident + 1);
        }

        content += space + "</descriptorSwitch>\n";


        return content;
    }


    public int compareTo(L other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        valid &= (hasDescriptor() && hasBind());

        if(hasBind()){
            for(B bind : binds)
                valid &= bind.validate();
        }
        if(hasDescriptor()){
            for(D desc : descriptors)
                valid &= desc.validate();
        }

        return valid;
    }
}
