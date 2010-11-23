package br.pensario.connector;

import br.pensario.NCLValues.NCLComparator;
import br.pensario.NCLValues.NCLDefaultValueAssessment;
import java.util.Set;
import java.util.TreeSet;


/**
 * Esta classe define o elemento <i>assessmentStatement</i> da <i>Nested Context Language</i> (NCL).
 * Este elemento é o elemento que define uma assertiva de um conector de um documento NCL.<br>
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
public class NCLAssessmentStatement extends NCLStatement {

    private NCLComparator comparator;
    
    private NCLValueAssessment valueAssessment;
    private Set<NCLAttributeAssessment> attributeAssessments = new TreeSet();
    
    
    /**
     * Determina o comparador da assertiva.
     * 
     * @param comparator
     *          comparador utilizado pela assertiva.
     */
    public void setComparator(NCLComparator comparator) {
        this.comparator = comparator;
    }
    
    
    /**
     * Retorna o comparador da assertiva.
     * 
     * @return
     *          comparador utilizado pela assertiva.
     */
    public NCLComparator getComparator() {
        return comparator;
    }
    
    
    /**
     * Determina um valor de comparação a assertiva.
     * 
     * @param value
     *          String representando o valor de comparação a ser utilizado.
     * @throws java.lang.IllegalArgumentException
     *          Se o valor for uma String vazia.
     *
     * @see NCLValueAssessment
     */
    public void setValueAssessment(String value) throws IllegalArgumentException {
        this.valueAssessment = new NCLValueAssessment(value);
    }
    
    
    /**
     * Determina um valor de comparação a assertiva. Usa um dos valores padrão.
     * 
     * @param value
     *          elemento representando o valor de comparação a ser utilizado.
     */
    public void setValueAssessment(NCLDefaultValueAssessment value) {
        try{
            this.valueAssessment = new NCLValueAssessment(value.toString());
        }
        catch(IllegalArgumentException e){

        }
    }
    
    
    /**
     * Retorna o valor de comparação a assertiva.
     * 
     * @return
     *          elemento representando o valor de comparação utilizado.
     */
    public NCLValueAssessment getValueAssessment() {
        return valueAssessment;
    }
    
    
    /**
     * Adiciona um atributo de comparação a assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser adicionado.
     * @return
     *          verdadeiro se o atributo foi adicionado.
     * @throws java.lang.Exception
     *          se o número máximo de atributos for ultrapassado.
     *
     * @see TreeSet#add
     */
    public boolean addAttributeAssessment(NCLAttributeAssessment attribute) throws Exception {
        if (attributeAssessments.size() == 2)
            throw new Exception("can't have more than two attributes"); //TODO: Criar tipo de excecao
        
        return attributeAssessments.add(attribute);
    }
    
    
    /**
     * Remove um atributo de comparação da assertiva.
     * 
     * @param attribute
     *          elemento representando o atributo a ser removido.
     * @return
     *          verdadeiro se o atributo for removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeAttributeAssessment(NCLAttributeAssessment attribute) {
        return attributeAssessments.remove(attribute);
    }
    
    
    /**
     * Verifica se a assertiva possui um atributo.
     * 
     * @param attribute
     *          elemento representando o atributo a ser verificado.
     * @return
     *          verdadeiro se o atributo estiver presente.
     */
    public boolean hasAttributeAssessment(NCLAttributeAssessment attribute) {
        return attributeAssessments.contains(attribute);
    }
    
    
    /**
     * Verifica se a assertiva possui pelo menos um atributo.
     * 
     * @return
     *          verdadeiro se a assertiva possuir pelo menos um atributo.
     */
    public boolean hasAttributeAssessment() {
        return !attributeAssessments.isEmpty();
    }


    /**
     * Retorna os atributos da assertiva.
     *
     * @return
     *          objeto Iterable contendo os atributos da assertiva.
     */
    public Iterable<NCLAttributeAssessment> getAttributeAssessments() {
        return attributeAssessments;
    }
    
    
    public String parse(int ident) {
        String space, content;

        if (ident < 0)
            ident = 0;
        
        // Element indentation
        space = "";
        for (int i = 0; i < ident; i++)
            space += "\t";

        content = space + "<assessmentStatement";

        content += " comparator='" + getComparator().toString() + "'";
        
        content += ">\n";
        
        for(NCLAttributeAssessment attribute : attributeAssessments)
            content += attribute.parse(ident + 1);
        
        if (valueAssessment != null)
            content += valueAssessment.parse(ident + 1);
        
        content += space + "</assessmentStatement>\n";

        return content;
    }
    
    
    public int compareTo(NCLStatement other) {
        int comp = 0;

        String this_stat, other_stat;
        NCLAssessmentStatement other_asses = (NCLAssessmentStatement) other;

        // Verifica se sao do mesmo tipo
        if (!(other instanceof NCLAssessmentStatement))
            comp = -1;

        // Compara pelo comparador
        if (comp == 0){
            if (getComparator() == null) this_stat = ""; else this_stat = getComparator().toString();
            if (other_asses.getComparator() == null) other_stat = ""; else other_stat = other_asses.getComparator().toString();
            comp = this_stat.compareTo(other_stat);
        }

        // Compara o número de attributeAssessment
        if (comp == 0)
            comp = attributeAssessments.size() - ((Set) other_asses.getAttributeAssessments()).size();

        // Compara os attributeAssessment
        if (comp == 0){
            NCLAttributeAssessment atts[] = (NCLAttributeAssessment[]) attributeAssessments.toArray();
            NCLAttributeAssessment other_atts[] = (NCLAttributeAssessment[]) ((Set) other_asses.getAttributeAssessments()).toArray();

            for (int i = 0; i < atts.length; i++){
                comp = atts[i].compareTo(other_atts[i]);
                if (comp != 0)
                    break;
            }
        }

        // Compara os valueAssessment
        if (comp == 0){
            if (getValueAssessment() == null) this_stat = ""; else this_stat = getValueAssessment().getValue();
            if (other_asses.getValueAssessment() == null) other_stat = ""; else other_stat = other_asses.getValueAssessment().getValue();
            comp = this_stat.compareTo(other_stat);
        }


        return comp;
    }

}
