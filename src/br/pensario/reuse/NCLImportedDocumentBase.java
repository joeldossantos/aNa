package br.pensario.reuse;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


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
     * Construtor do elemento <i>importedDocumentBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLImportedDocumentBase() {}


    /**
     * Construtor do elemento <i>importedDocumentBase</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLImportedDocumentBase(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


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


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("importedDocumentBase")){
                cleanWarnings();
                cleanErrors();
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("importNCL")){
                NCLImport i = new NCLImport(NCLImportType.NCL, getReader(), this);
                i.startElement(uri, localName, qName, attributes);
                addImportNCL((I) i); //TODO: retirar o cast. Como melhorar isso?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasImportNCL()){
            for(I imp : imports){
                imp.endDocument();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }
    }
}
