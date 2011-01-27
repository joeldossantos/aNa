package br.pensario.rule;

import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLOperator;
import java.util.Set;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma regra de teste composta da <i>Nested Context Language</i> (NCL).<br>
 *
 * @see <a href="
http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
      ABNT NBR 15606-2:2007</a>
 *
 *@see <a href="../../README.html">Detalhes da API NCL</a>
 *
 */
public class NCLCompositeRule<T extends NCLTestRule> extends NCLIdentifiableElement implements NCLTestRule<T> {

    private NCLOperator operator;
    private Set<T> rules = new TreeSet<T>();

    private boolean insideRule;


    /**
     * Construtor do elemento <i>compositeRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLCompositeRule(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>compositeRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCompositeRule(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideRule = false;
    }


    /**
     * Atribui um operador a regra composta.
     *
     * @param operator
     *          elemento representando o operador da regra composta.
     */
    public void setOperator(NCLOperator operator) {
        this.operator = operator;
    }


    /**
     * Retorna o operador da regra composta.
     *
     * @return
     *          elemento representando o operador da regra composta.
     */
    public NCLOperator getOperator() {
        return operator;
    }


    /**
     * Adiciona uma regra a regra composta.
     *
     * @param rule
     *          elemento representando a regra a ser adicionada.
     * @return
     *          verdadeiro se a regra foi adicionada.
     *
     * @see TreeSet#add
     */
    public boolean addRule(T rule) {
        if(rules.add(rule)){
            //Se rule existe, atribui este como seu parente
            if(rule != null)
                rule.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove uma regra da regra composta.
     *
     * @param rule
     *          elemento representando a regra a ser removida.
     * @return
     *          verdadeiro se a regra foi removida.
     *
     * @see TreeSet#remove
     */
    public boolean removeRule(T rule) {
        if(rules.remove(rule)){
            //Se rule existe, retira o seu parentesco
            if(rule != null)
                rule.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se a regra composta possui uma regra.
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
     * Verifica se a regra composta possui alguma regra.
     *
     * @return
     *          verdadeiro se a regra composta possui alguma regra.
     */
    public boolean hasRule() {
        return !rules.isEmpty();
    }


    /**
     * Retorna as regras da regra composta.
     *
     * @return
     *          objeto Iterable contendo as regras da regra composta.
     */
    public Iterable<T> getRules() {
        return rules;
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
        content = space + "<compositeRule";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getOperator() != null)
            content += " operator='" + getOperator() + "'";
        content += ">\n";

        if(hasRule()){
            for(T rule : rules)
                content += rule.parse(ident + 1);
        }

        content += "</compositeRule>\n";

        return content;
    }


    public int compareTo(T other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getId() == null){
            addError("Elemento não possui atributo obrigatório id.");
            valid = false;
        }
        if(getOperator() == null){
            addError("Elemento não possui atributo obrigatório operator.");
            valid = false;
        }
        if(!hasRule()){
            addError("Elemento não possui elementos filhos em cardinalidade correta. Deve possuir ao menos uma regra.");
            valid = false;
        }

        if(hasRule()){
            for(T rule : rules){
                valid &= rule.validate();
                addWarning(rule.getWarnings());
                addError(rule.getErrors());
            }
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("compositeRule") && !insideRule){
                cleanWarnings();
                cleanErrors();
                insideRule = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("id"))
                        setId(attributes.getValue(i));
                    else if(attributes.getLocalName(i).equals("operator")){
                        for(NCLOperator op : NCLOperator.values()){
                        if(op.toString().equals(attributes.getValue(i)))
                            setOperator(op);
                        }
                    }
                }
            }
            else if(localName.equals("compositeRule") && insideRule){
                // compositeRule e um elemento interno
                T child = createCompositeRule();
                child.startElement(uri, localName, qName, attributes);
                addRule(child);
            }
            else if(localName.equals("rule")){
                T child = createRule();
                child.startElement(uri, localName, qName, attributes);
                addRule(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(hasRule()){
            for(T rule : rules){
                rule.endDocument();
                addWarning(rule.getWarnings());
                addError(rule.getErrors());
            }
        }
    }


    /**
     * Função de criação do elemento filho <i>compositeRule</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compositeRule</i>.
     */
    protected T createCompositeRule() {
        return (T) new NCLCompositeRule(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>rule</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>rule</i>.
     */
    protected T createRule() {
        return (T) new NCLRule(getReader(), this);
    }
}
