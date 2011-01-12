package br.pensario.meta;

import br.pensario.NCLElement;


/**
 * Esta classe define o elemento <i>meta</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um metadado para o documento NCL.<br>
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
public class NCLMeta<M extends NCLMeta> extends NCLElement implements Comparable<M> {

    private String name;
    private String mcontent;


    /**
     * Atribui um nome ao metadado.
     *
     * @param name
     *          String representando o nome do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name != null && "".equals(name.trim()))
            throw new IllegalArgumentException("Empty String");

        this.name = name;
    }


    /**
     * Retorna o nome do metadado.
     *
     * @return
     *          String representando o nome do metadado.
     */
    public String getName() {
        return name;
    }


    /**
     * Atribui um conteúdo ao metadado.
     *
     * @param content
     *          String representando o conteúdo do metadado.
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setContent(String content) throws IllegalArgumentException {
        if (content != null && "".equals(content.trim()))
            throw new IllegalArgumentException("Empty String");

        this.mcontent = content;
    }


    /**
     * Retorna o conteúdo do metadado.
     * 
     * @return
     *          String representando o conteúdo do metadado.
     */
    public String getContent() {
        return mcontent;
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
        content = space + "<meta";

        content += " name='" + getName() + "'";
        content += " content='" + getContent() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(M other) {
        return getName().compareTo(other.getName());
    }
}
