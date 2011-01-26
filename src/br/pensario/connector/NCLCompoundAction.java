package br.pensario.connector;

import br.pensario.NCLElement;
import br.pensario.NCLInvalidIdentifierException;
import java.util.Set;
import br.pensario.NCLValues.NCLActionOperator;
import java.util.Iterator;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;


/**
 * Esta classe define o elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma ação composta de um conector de um documento NCL.<br>
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
public class NCLCompoundAction<A extends NCLAction, P extends NCLConnectorParam> extends NCLElement implements NCLAction<A, P> {

    private NCLActionOperator operator;
    private Integer delay;
    private P parDelay;
    
    private Set<A> actions = new TreeSet<A>();

    private boolean insideAction;


    /**
     * Construtor do elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLCompoundAction() {}


    /**
     * Construtor do elemento <i>compoundAction</i> da <i>Nested Context Language</i> (NCL).
     *
     * @param reader
     *          elemento representando o leitor XML do parser SAX.
     * @param parent
     *          elemento NCL representando o elemento pai.
     */
    public NCLCompoundAction(XMLReader reader, NCLElement parent) {
        setReader(reader);
        setParent(parent);

        getReader().setContentHandler(this);

        insideAction = false;
    }
    
    
    /**
     * Determina o operador da ação composta.
     *
     * @param operator
     *          elemento representando o operador a ser atribuido.
     */
    public void setOperator(NCLActionOperator operator) {
        this.operator = operator;
    }
    
    
    /**
     * Retorna o operador atribuido a ação composta.
     *
     * @return
     *          elemento representando o operador atribuido.
     */
    public NCLActionOperator getOperator() {
        return operator;
    }

    
    /**
     * Adiciona uma ação a ação composta.
     *
     * @param action
     *          elemento representando a ação a ser adicionada
     * @return
     *          verdadeiro se a ação foi adicionada.
     *
     * @see TreeSet#add(java.lang.Object) 
     */
    public boolean addAction(A action) {
        if(actions.add(action)){
            //Se metadata existe, atribui este como seu parente
            if(action != null)
                action.setParent(this);

            return true;
        }
        return false;
    }


    /**
     * Remove uma ação a ação composta.
     *
     * @param action
     *          elemento representando a ação a ser removida
     * @return
     *          verdadeiro se a ação foi removida.
     *
     * @see TreeSet#remove(java.lang.Object)
     */
    public boolean removeAction(A action) {
        if(actions.remove(action)){
            //Se action existe, retira o seu parentesco
            if(action != null)
                action.setParent(null);

            return true;
        }
        return false;
    }


    /**
     * Verifica se a ação composta possui uma ação.
     *
     * @param action
     *          elemento representando a ação a ser verificada
     * @return
     *          verdadeiro se a ação existe.
     */
    public boolean hasAction(A action) {
        return actions.contains(action);
    }


    /**
     * Verifica se a ação composta possui alguma ação.
     *
     * @return
     *          verdadeiro se a ação composta possui alguma ação.
     */
    public boolean hasAction() {
        return !actions.isEmpty();
    }


    /**
     * Retorna as ações da ação composta.
     *
     * @return
     *          objeto Iterable contendo as ações da ação composta.
     */
    public Iterable<A> getActions() {
        return actions;
    }


    public void setDelay(Integer delay) throws IllegalArgumentException {
        if(delay != null && delay < 0)
            throw new IllegalArgumentException("Invalid delay");

        this.delay = delay;
        this.parDelay = null;
    }


    public void setDelay(P delay) {
        this.parDelay = delay;
        this.delay = null;
    }


    public Integer getDelay() {
        return delay;
    }


    public P getParamDelay() {
        return parDelay;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if(ident < 0)
            ident = 0;

        // Element indentation
        space = "";
        for(int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<compoundAction";
        if(getOperator() != null)
            content += " operator='" + getOperator() + "'";
        if(getDelay() != null)
            content += " delay='" + getDelay() + "s'";
        if(getParamDelay() != null)
            content += " delay='$" + getParamDelay().getId() + "'";
        content += ">\n";

        if(hasAction()){
            for(A action : actions)
                content += action.parse(ident + 1);
        }

        content += space + "</compoundAction>\n";

        return content;
    }


    public int compareTo(A other) {
        //retorna 0 se forem iguais e 1 se forem diferentes (mantem a ordem de insercao)
        int comp = 0;

        String this_act, other_act;
        NCLCompoundAction other_comp;

        // Verifica se sao do mesmo tipo
        if(!(other instanceof NCLCompoundAction))
            return 1;

        other_comp = (NCLCompoundAction) other;
        
        // Compara pelo operador
        if(comp == 0){
            if(getOperator() == null) this_act = ""; else this_act = getOperator().toString();
            if(other_comp.getOperator() == null) other_act = ""; else other_act = other_comp.getOperator().toString();
            comp = this_act.compareTo(other_act);
        }

        // Compara pelo delay
        if(comp == 0){
            int this_del, other_del;
            if(getDelay() == null) this_del = 0; else this_del = getDelay();
            if(other_comp.getDelay() == null) other_del = 0; else other_del = other_comp.getDelay();
            comp = this_del - other_del;
        }

        // Compara pelo delay (parametro)
        if(comp == 0){
            if(getParamDelay() == null && other_comp.getParamDelay() == null)
                comp = 0;
            else if(getParamDelay() != null && other_comp.getParamDelay() != null)
                comp = getParamDelay().compareTo(other_comp.getParamDelay());
            else
                comp = 1;
        }

        // Compara o número de acoes
        if(comp == 0)
            comp = actions.size() - ((Set) other_comp.getActions()).size();

        // Compara as acoes
        if(comp == 0){
            Iterator it = other_comp.getActions().iterator();
            for(NCLAction a : actions){
                NCLAction other_a = (NCLAction) it.next();
                comp = a.compareTo(other_a);
                if(comp != 0)
                    break;
            }
        }

        
        if(comp != 0)
            return 1;
        else
            return 0;
    }


    public boolean validate() {
        boolean valid = true;

        valid &= (getOperator() != null);
        valid &= hasAction();

        if(hasAction()){
            for(A action : actions)
                valid &= action.validate();
        }

        return valid;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try{
            if(localName.equals("compoundAction") && !insideAction){
                cleanWarnings();
                cleanErrors();
                insideAction = true;
                for(int i = 0; i < attributes.getLength(); i++){
                    if(attributes.getLocalName(i).equals("operator")){
                        for(NCLActionOperator o : NCLActionOperator.values()){
                            if(o.toString().equals(attributes.getValue(i)))
                                setOperator(o);
                        }
                    }
                    else if(attributes.getLocalName(i).equals("delay")){
                        String var = attributes.getValue(i);
                        if(var.contains("$")){
                            var = var.substring(1);
                            setDelay((P) new NCLConnectorParam(var));//TODO: precisa retirar cast?
                        }
                        else{
                            var = var.substring(0, var.length() - 1);
                            setDelay(new Integer(var));
                        }
                    }
                }
            }
            else if(localName.equals("simpleAction")){
                A child = createSimpleAction();
                child.startElement(uri, localName, qName, attributes);
                addAction(child);
            }
            else if(localName.equals("compoundAction") && insideAction){
                A child = createCompoundAction();
                child.startElement(uri, localName, qName, attributes);
                addAction(child);
            }
        }
        catch(NCLInvalidIdentifierException ex){
            addError(ex.getMessage());
        }
    }


    @Override
    public void endDocument() {
        if(getParent() != null){
            if(getParamDelay() != null)
                setDelay(parameterReference(getParamDelay().getId()));
        }

        if(hasAction()){
            for(A action : actions){
                action.endDocument();
                addWarning(action.getWarnings());
                addError(action.getErrors());
            }
        }
    }


    private P parameterReference(String id) {
        NCLElement connector = getParent();

        while(!(connector instanceof NCLCausalConnector)){
            connector = connector.getParent();
            if(connector == null){
                addWarning("Could not find a parent connector");
                return null;
            }
        }

        Iterable<P> params = ((NCLCausalConnector) connector).getConnectorParams();
        for(P param : params){
            if(param.getId().equals(id))
                return param;
        }

        addWarning("Could not find connectorParam in connector with id: " + id);
        return null;
    }


    /**
     * Função de criação do elemento filho <i>simpleAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>simpleAction</i>.
     */
    protected A createSimpleAction() {
        return (A) new NCLSimpleAction(getReader(), this);
    }


    /**
     * Função de criação do elemento filho <i>compoundAction</i>.
     * Esta função deve ser sobrescrita em classes que estendem esta classe.
     *
     * @return
     *          elemento representando o elemento filho <i>compoundAction</i>.
     */
    protected A createCompoundAction() {
        return (A) new NCLCompoundAction(getReader(), this);
    }
}
