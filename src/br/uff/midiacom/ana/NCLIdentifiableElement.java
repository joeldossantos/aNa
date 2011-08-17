/********************************************************************************
 * This file is part of the api for NCL authoring - aNa.
 *
 * Copyright (c) 2011, MídiaCom Lab (www.midiacom.uff.br)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * All advertising materials mentioning features or use of this software must
 *    display the following acknowledgement:
 *        This product includes the Api for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MÍDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE MÍDIACOM LAB OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *******************************************************************************/
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.datatype.NCLElementAttributes;
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

        notifyAltered(NCLElementAttributes.ID, this.id, id);
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
