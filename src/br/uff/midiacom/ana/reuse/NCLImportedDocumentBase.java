package br.uff.midiacom.ana.reuse;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLInvalidIdentifierException;
import br.uff.midiacom.ana.NCLValues.NCLImportType;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma base de documentos importados da <i>Nested Context Language</i> (NCL).<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
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
        if(imports.add(importNCL)){
            //Se importNCL existe, atribui este como seu parente
            if(importNCL != null)
                importNCL.setParent(this);

            return true;
        }
        return false;
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
        if(imports.remove(importNCL)){
            //Se importNCL existe, retira o seu parentesco
            if(importNCL != null)
                importNCL.setParent(null);

            return true;
        }
        return false;
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
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(!hasImportNCL()){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir ao menos um importNCL.");
            valid = false;
        }

        if(hasImportNCL()){
            for(NCLImport imp : imports){
                valid &= imp.validate();
                addWarning(imp.getWarnings());
                addError(imp.getErrors());
            }
        }

        return valid;
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
                I child = createImportNCL();
                child.startElement(uri, localName, qName, attributes);
                addImportNCL(child);
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


    /**
     * Função de criação do elemento filho <i>importNCL</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>importNCL</i>.
     */
    protected I createImportNCL() {
        return (I) new NCLImport(NCLImportType.NCL, getReader(), this);
    }
}
