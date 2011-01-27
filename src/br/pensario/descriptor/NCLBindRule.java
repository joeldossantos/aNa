package br.pensario.descriptor;

import br.pensario.NCLElement;
import br.pensario.NCLHead;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.rule.NCLRule;
import br.pensario.rule.NCLTestRule;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>bindRule</i> de um switch de descritor da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define um bind de um switch de descritor de um documento NCL.<br>
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
public class NCLBindRule<B extends NCLBindRule, D extends NCLDescriptor, R extends NCLTestRule> extends NCLElement implements Comparable<B> {

    private D constituent;
    private R rule;


    /**
     * Construtor do elemento <i>bindRule</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLBindRule() {}


    /**
     * Construtor do elemento <i>bindRule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLBindRule(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui um constituent ao bind.
     *
     * @param constituent
     *          elemento representando o descritor mapeado pelo bind.
     */
    public void setConstituent(D constituent) {
        this.constituent = constituent;
    }


    /**
     * Retorna o constituent do bind.
     *
     * @return
     *          elemento representando o descritor mapeado pelo bind.
     */
    public D getConstituent() {
        return constituent;
    }


    /**
     * Atribui uma regra de avaliação ao bind.
     *
     * @param rule
     *          elemento representando a regra de avaliação do bind.
     */
    public void setRule(R rule) {
        this.rule = rule;
    }


    /**
     * Retorna a regra de avaliação do bind.
     * 
     * @return
     *          elemento representando a regra de avaliação do bind.
     */
    public R getRule() {
        return rule;
    }


    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<bindRule";
        if(getRule() != null)
            content += " rule='" + getRule().getId() + "'";
        if(getConstituent() != null)
            content += " constituent='" + getConstituent().getId() + "'";
        content += "/>\n";


        return content;
    }


    public int compareTo(B other) {
        //retorna 0 se forem iguais e 1 se forem diferentes (mantem a ordem de insercao)
        int comp = 0;

        // Compara pela regra
        comp = getRule().compareTo(other.getRule());

        // Compara pelo constituent
        if(comp == 0){
            comp = getConstituent().compareTo(other.getConstituent());
        }

        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        cleanWarnings();
        cleanErrors();

        boolean valid = true;

        if(getRule() != null){
            addError("Elemento não possui atributo obrigatório rule.");
            valid = false;
        }
        if(getConstituent() != null){
            addError("Elemento não possui atributo obrigatório constituent.");
            valid = false;
        }

        if(getConstituent() != null && getConstituent().compareTo((NCLDescriptorSwitch) getParent()) != 0){
            addError("Atributo constituent deve fazer referência a um descritor contido no descriptorSwitch.");
            valid = false;
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("rule"))
                    setRule((R) new NCLRule(attributes.getValue(i)));//TODO: precisa retirar cast?
                else if(attributes.getLocalName(i).equals("constituent"))
                    setConstituent((D) new NCLDescriptor(attributes.getValue(i)));//TODO: precisa retirar cast?
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() == null)
            return;

        if(getConstituent() != null)
            constituentReference();

        if(getRule() != null)
            ruleReference();
    }


    private Iterable<R> getRules() {
        NCLElement head = getParent();

        while(!(head instanceof NCLHead)){
            head = head.getParent();
            if(head == null){
                addWarning("Could not find a head");
                return null;
            }
        }

        if(((NCLHead) head).getRuleBase() == null){
            addWarning("Could not find a ruleBase");
            return null;
        }

        return ((NCLHead) head).getRuleBase().getRules();
    }


    private void constituentReference() {
        //Search for a component node in its parent
        Iterable<D> descriptors = ((NCLDescriptorSwitch) getParent()).getDescriptors();

        for(D descriptor : descriptors){
            if(descriptor.getId().equals(getConstituent().getId())){
                setConstituent(descriptor);
                return;
            }
        }

        addWarning("Could not find descriptor in descriptorSwitch with id: " + getConstituent().getId());
    }


    private void ruleReference() {
        //Search for the interface inside the node
        Iterable<R> rules = getRules();
        if(rules == null)
            return;

        for(R rul : rules){
            if(rul.getId().equals(getRule().getId())){
                setRule(rul);
                return;
            }
        }
        //@todo: regras internas a regras compostas podem ser utilizadas?

        addWarning("Could not find rule in ruleBase with id: " + getRule().getId());
    }
}