package br.uff.midiacom.ana;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Esta classe define um elemento identificável da <i>Nested Context Language</i> (NCL), isto é,
 * um elemento que possui um identificador único (atributo <i>id</i> ou <i>name</i>.<br/>
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public abstract class NCLIdentifiableElement extends NCLElement implements IdentifiableElement {
    
    private String id;    


    /**
     * Determina o identificador único do elemento da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          String representando o atributo <i>id</i> ou <i>name</i> do elemento.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador não for válido.
     */
    public void setId(String id) throws NCLInvalidIdentifierException {
        if(!validate(id))
            throw new NCLInvalidIdentifierException("Invalid identifier");
    
        this.id = id;
    }
    
    
    /**
     * Retorna o identificador único do elemento da <i>Nested Context Language</i> (NCL).
     * Este identificador pode representar o atributo <i>id</i> ou <i>name</i> do elemento.
     * 
     * @return
     *          String representando o identificador do elemento.
     */
    public String getId() {
        return id;
    }


    /**
     * Método utilizado para validar o identificador do elemento.
     * A validação segue o formato: [_:A-Za-z] \c = [-._:A-Za-z0-9]
     * 
     * @param id
     *          String representando o identificador a ser validado.
     * @return
     *          verdadeiro se o identificador for válido e falso caso contrário.
     */
    private boolean validate(String id) {
    
        Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");

        Matcher matcher = pattern.matcher(id);

        return matcher.matches();  
        
    }
    
}
