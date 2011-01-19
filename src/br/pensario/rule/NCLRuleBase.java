package br.pensario.rule;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLImportType;
import br.pensario.reuse.NCLImport;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma base de regras de teste da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLRuleBase<T extends NCLTestRule, I extends NCLImport> extends NCLIdentifiableElement {

    private Set<T> rules = new TreeSet<T>();
    private Set<I> imports = new TreeSet<I>();


    /**
     * Construtor do elemento <i>ruleBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLRuleBase() {}


    /**
     * Construtor do elemento <i>ruleBase</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLRuleBase(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Adiciona uma regra a base de regras.
     *
     * @param rule
     *          elemento representando a regra a ser adicionada.
     * @return
     *          verdadeiro se a regra foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRule(T rule) {
        return rules.add(rule);
    }


    /**
     * Remove uma regra da base de regras.
     *
     * @param rule
     *          elemento representando a regra a ser removida.
     * @return
     *          verdadeiro se a regra foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRule(T rule) {
        return rules.remove(rule);
    }


    /**
     * Verifica se a base de regras possui uma regra.
     *
     * @param rule
     *          elemento representando a regra a ser verificada.
     * @return
     *          verdadeiro se a regra existir.
     */
    public boolean hasRule(T rule) {
        return rules.contains(rule);
    }


    /**
     * Verifica se a base de regras possui alguma regra.
     *
     * @return
     *          verdadeiro se a base de regras possui alguma regra.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Retorna as regras da base de regras.
     *
     * @return
     *          objeto Iterable contendo as regras da base de regras.
     */
    public Iterable<T> getRules() {
        return rules;
    }


    /**
     * Adiciona um importador de base à base de regras.
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
     * Remove um importador de base da base de regras.
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
     * Verifica se a base de regras contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(I importBase) {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de regras possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de regras possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de regras.
     *
     * @return
     *          objeto Iterable contendo os importadores de base da base de regras.
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

        content = space + "<ruleBase";
        if(getId() != null)
            content += " id='" + getId() + "'";

        if(hasImportBase() || hasRule()){
            content += ">\n";

            if(hasImportBase()){
                for(I imp : imports)
                    content += imp.parse(ident + 1);
            }

            if(hasRule()){
                for(T rule : rules)
                    content += rule.parse(ident + 1);
            }

            content += space + "</ruleBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (hasImportBase() || hasRule());

        if(hasImportBase()){
            for(I imp : imports)
                valid &= imp.validate();
        }
        if(hasRule()){
            for(T rule : rules)
                valid &= rule.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("ruleBase")){
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                }
            }
            else if(localName.equals("importBase")){
                NCLImport i = new NCLImport(NCLImportType.BASE, getReader(), this);
                i.startElement(uri, localName, qName, attributes);
                addImportBase((I) i); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("rule")){
                NCLRule r = new NCLRule(getReader(), this);
                r.startElement(uri, localName, qName, attributes);
                addRule((T) r); //TODO: retirar o cast. Como melhorar isso?
            }
            else if(localName.equals("compositeRule")){
                NCLCompositeRule r = new NCLCompositeRule(getReader(), this);
                r.startElement(uri, localName, qName, attributes);
                addRule((T) r); //TODO: retirar o cast. Como melhorar isso?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            //TODO: fazer o que?
        }
    }
}
