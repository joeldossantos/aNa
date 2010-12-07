package br.pensario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Esta classe define um elemento identificável da <i>Nested Context Language</i> (NCL), isto é,
 * um elemento que possui um identificador único (atributo <i>id</i> ou <i>name</i>.<br>
 *
 * @see <a
 *      href="http://www.abnt.org.br/imagens/Normalizacao_TV_Digital/ABNTNBR15606-5_2008Ed1.pdf">ABNT
 *      NBR 15606-5:2008</a>
 *
 *
 * @version 1.0.1
 * @author <a href="http://www.cos.ufrj.br/~schau/">Wagner Schau<a/>
 * @author <a href="http://joel.dossantos.eng.br">Joel dos Santos<a/>
 */
public abstract class NCLIdentifiableElement extends NCLElement{
    
    private String id;    


    /**
     * Determina o identificador único do elemento da <i>Nested Context Language</i> (NCL).
     * 
     * @param id
     *          String representando o atributo <i>id</i> ou <i>name</i> do elemento.
     * @throws br.pensario.NCLInvalidIdentifierException
     *          se o identificador não for válido.
     */
    protected void setId(String id) throws NCLInvalidIdentifierException {
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
