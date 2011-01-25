package br.pensario.rule;

import br.pensario.NCLDoc;
import br.pensario.NCLElement;
import br.pensario.NCLIdentifiableElement;
import br.pensario.NCLInvalidIdentifierException;
import br.pensario.NCLValues.NCLComparator;
import br.pensario.interfaces.NCLProperty;
import br.pensario.node.NCLContext;
import br.pensario.node.NCLMedia;
import br.pensario.node.NCLNode;
import br.pensario.node.NCLSwitch;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define uma regra de teste da <i>Nested Context Language</i> (NCL).<br>
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
public class NCLRule<P extends NCLProperty, T extends NCLTestRule> extends NCLIdentifiableElement implements NCLTestRule<T> {

    private P var;
    private NCLComparator comparator;
    private String value;


    /**
     * Construtor do elemento <i>rule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param id
     *          identificador da regra.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador da regra não for válido.
     */
    public NCLRule(String id) throws NCLInvalidIdentifierException {
        setId(id);
    }


    /**
     * Construtor do elemento <i>rule</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLRule(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);
    }


    /**
     * Atribui uma propriedade ao atributo var.
     *
     * @param var
     *          elemento representando a propriedade associada ao atributo.
     */
    public void setVar(P var) {
        this.var = var;
    }


    /**
     * Retorna a propriedade relacionada ao atributo var.
     *
     * @return
     *          elemento representando a propriedade associada ao atributo.
     */
    public P getVar() {
        return var;
    }


    /**
     * Atribui um comparador a regra.
     *
     * @param comparator
     *          elemento representando o comparador da regra.
     */
    public void setComparator(NCLComparator comparator) {
        this.comparator = comparator;
    }


    /**
     * Retorna o comparador da regra.
     *
     * @return
     *          elemento representando o comparador da regra.
     */
    public NCLComparator getComparator() {
        return comparator;
    }


    /**
     * Atribui um valor de comparação a regra.
     *
     * @param value
     *          String representando o valor de comparação.
     *
     * @throws IllegalArgumentException
     *          se a String for vazia.
     */
    public void setValue(String value) throws IllegalArgumentException {
        if (value != null && "".equals(value.trim()))
            throw new IllegalArgumentException("Empty String");

        this.value = value;
    }


    /**
     * Retorna o valor de comparação da regra.
     *
     * @return
     *          String representando o valor de comparação.
     */
    public String getValue() {
        return value;
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
        content = space + "<rule";
        if(getId() != null)
            content += " id='" + getId() + "'";
        if(getVar() != null)
            content += " var='" + getVar().getName() + "'";
        if(getComparator() != null)
            content += " comparator='" + getComparator().toString() + "'";
        if(getValue() != null)
            content += " value='" + getValue() + "'";
        content += "/>\n";

        return content;
    }


    public int compareTo(T other) {
        return getId().compareTo(other.getId());
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getId() != null);
        valid &= (getVar() != null);
        valid &= (getComparator() != null);
        valid &= (getValue() != null);

        //TODO var deve ser do tipo settings (seu elemento pai)

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            cleanWarnings();
            cleanErrors();
            for(int i = 0; i < attributes.getLength(); i++){
                if(attributes.getLocalName(i).equals("id"))
                    setId(attributes.getValue(i));
                else if(attributes.getLocalName(i).equals("var"))
                    setVar((P) new NCLProperty(attributes.getValue(i)));
                else if(attributes.getLocalName(i).equals("comparator")){
                    for(NCLComparator c : NCLComparator.values()){
                        if(c.toString().equals(attributes.getValue(i)))
                            setComparator(c);
                    }
                }
                else if(attributes.getLocalName(i).equals("value"))
                    setValue(attributes.getValue(i));
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

        if(getVar() != null)
            propertyReference();
    }


    private void propertyReference() {
        //Search for the interface inside the node
        NCLElement doc = getParent();

        while(!(doc instanceof NCLDoc)){
            doc = doc.getParent();
            if(doc == null){
                addWarning("Could not find a root element");
                return;
            }
        }

        if(((NCLDoc) doc).getBody() == null){
            addWarning("Could not find a body");
        }

        setVar(findProperty(((NCLDoc) doc).getBody().getNodes()));
    }


    private P findProperty(Iterable<NCLNode> nodes) {
        for(NCLNode n : nodes){
            if(n instanceof NCLMedia){
                if( ((NCLMedia) n).hasProperty()){
                    Iterable<P> properties = ((NCLMedia) n).getProperties();
                    for(P prop : properties){
                        if(prop.getName().equals(getVar().getName()))
                            return prop;
                    }
                }
            }
            else if(n instanceof NCLContext){
                if( ((NCLContext) n).hasNode()){
                    Iterable<NCLNode> cnodes = ((NCLContext) n).getNodes();
                    NCLProperty p = findProperty(cnodes);
                    if(p != null)
                        return (P) p;
                }
            }
            else if(n instanceof NCLSwitch){
                if( ((NCLSwitch) n).hasNode()){
                    Iterable<NCLNode> snodes = ((NCLSwitch) n).getNodes();
                    NCLProperty p = findProperty(snodes);
                    if(p != null)
                        return (P) p;
                }
            }
        }

        addWarning("Could not find property with name: " + getVar().getName());
        return null;
    }
}
