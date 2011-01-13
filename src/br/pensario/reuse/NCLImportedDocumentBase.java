package br.pensario.reuse;

import br.pensario.NCLIdentifiableElement;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define uma base de documentos importados da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLImportedDocumentBase<I extends NCLImport> extends NCLIdentifiableElement {

    private Set<I> imports = new TreeSet<I>();


    /**
     * Adiciona um importador de documento à base de documentos importados.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportNCL(I importNCL) {
        return imports.add(importNCL);
    }


    /**
     * Remove um importador de documento da base de documentos importados.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportNCL(I importNCL) {
        return imports.remove(importNCL);
    }


    /**
     * Verifica se a base de documentos importados contém um importador de documento.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportNCL(I importNCL) {
        return imports.contains(importNCL);
    }


    /**
     * Verifica se a base de documentos importados possui algum importador de documento.
     *
     * @return
     *          verdadeiro se a base de documentos importados possuir algum importador de documento.
     */
    public boolean hasImportNCL() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de documento da base de documentos importados.
     *
     * @return
     *          objeto Iterable contendo os importadores de documento da base de documentos importados.
     */
    public Iterable<I> getImportNCLs() {
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

        content = space + "<importedDocumentBase";
        if(getId() != null)
            content += " id='" + getId() + "'";

        if(hasImportNCL()){
            content += ">\n";

            for(I imp : imports)
                content += imp.parse(ident + 1);

            content += space + "</importedDocumentBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public boolean validate() {
        return hasImportNCL();
    }
}
