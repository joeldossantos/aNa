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
package br.uff.midiacom.ana.datatype.ncl.connector;

import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLElementImpl;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.ElementList;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;


public class NCLConnectorBasePrototype<T extends NCLConnectorBasePrototype, P extends NCLElement, I extends NCLElementImpl, Ec extends NCLCausalConnectorPrototype, Ei extends NCLImportPrototype>
        extends NCLIdentifiableElementPrototype<T, P, I> implements NCLIdentifiableElement<T, P> {

    protected IdentifiableElementList<Ec, T> connectors;
    protected ElementList<Ei, T> imports;
    

    /**
     * Construtor do elemento <i>connectorBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLConnectorBasePrototype() throws XMLException {
        super();
        connectors = new IdentifiableElementList<Ec, T>();
        imports = new ElementList<Ei, T>();
    }


    /**
     * Adiciona um conector a base de conectores.
     * 
     * @param connector
     *          elemento representando o conector a ser adicionado.
     * @return
     *          verdadeiro se o conector foi adicionado.
     *
     * @see TreeSet#add(java.lang.Object)
     */
    public boolean addCausalConnector(Ec connector) throws XMLException {
        return connectors.add(connector, (T) this);
    }
    
    
    /**
     * Remove um conector da base de conectores.
     * 
     * @param connector
     *          elemento representando o conector a ser removido.
     * @return
     *          verdadeiro se o conector foi removido.
     *
     * @see TreeSet#remove(java.lang.Object)
     */    
    public boolean removeCausalConnector(Ec connector) throws XMLException {
        return connectors.remove(connector);
    }
    
    
    public boolean removeCausalConnector(String id) throws XMLException {
        return connectors.remove(id);
    }
    
    
    /**
     * Verifica se a base de conectores possui um conector.
     * 
     * @param connector
     *          elemento representando o conector a ser verificado.
     * @return
     *          verdadeiro se o conector existir.
     */
    public boolean hasCausalConnector(Ec connector) throws XMLException {
        return connectors.contains(connector);        
    }
    
    
    public boolean hasCausalConnector(String id) throws XMLException {
        return connectors.get(id) != null;
    }
    
    
    /**
     * Verifica se a base de conectores possui pelo menos um conector.
     * 
     * @return
     *          verdadeiro se a base de conectores possui pelo menos um conector.
     */
    public boolean hasCausalConnector() {
        return !connectors.isEmpty();
    }

    
    /**
     * Retorna os conectores da base de conectores.
     * 
     * @return
     *          lista contendo os conectores da base de conectores.
     */
    public IdentifiableElementList<Ec, T> getCausalConnectors() {
        return connectors;        
    }


    /**
     * Adiciona um importador de base à base de conectores.
     *
     * @param importBase
     *          elemento representando o importador a ser adicionado.
     *
     * @see TreeSet#add
     */
    public boolean addImportBase(Ei importBase) throws XMLException {
        return imports.add(importBase, (T) this);
    }


    /**
     * Remove um importador de base da base de conectores.
     *
     * @param importBase
     *          elemento representando o importador a ser removido.
     *
     * @see TreeSet#remove
     */
    public boolean removeImportBase(Ei importBase) throws XMLException {
        return imports.remove(importBase);
    }


    /**
     * Verifica se a base de conectores contém um importador de base.
     *
     * @param importBase
     *          elemento representando o importador a ser verificado.
     */
    public boolean hasImportBase(Ei importBase) throws XMLException {
        return imports.contains(importBase);
    }


    /**
     * Verifica se a base de conectores possui algum importador de base.
     *
     * @return
     *          verdadeiro se a base de conectores possuir algum importador de base.
     */
    public boolean hasImportBase() {
        return !imports.isEmpty();
    }


    /**
     * Retorna os importadores de base da base de conectores.
     *
     * @return
     *          lista contendo os importadores de base da base de conectores.
     */
    public ElementList<Ei, T> getImportBases() {
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

        content = space + "<connectorBase";
        content += parseAttributes();

        if(hasImportBase() || hasCausalConnector()){
            content += ">\n";

            content += parseElements(ident + 1);

            content += space + "</connectorBase>\n";
        }
        else
            content += "/>\n";

        return content;
    }
    
    
    protected String parseAttributes() {
        String content = "";
        
        content += parseId();
        
        return content;
    }
    
    
    protected String parseElements(int ident) {
        String content = "";
        
        content += parseImportBases(ident);
        content += parseCausalConnectors(ident);
        
        return content;
    }
    
    
    protected String parseId() {
        String aux = getId();
        if(aux != null)
            return " id='" + aux + "'";
        else
            return "";
    }
    
    
    protected String parseImportBases(int ident) {
        if(!hasImportBase())
            return "";
        
        String content = "";
        for(Ei aux : imports)
            content += aux.parse(ident);
        
        return content;
    }
    
    
    protected String parseCausalConnectors(int ident) {
        if(!hasCausalConnector())
            return "";
        
        String content = "";
        for(Ec aux : connectors)
            content += aux.parse(ident);
        
        return content;
    }
}
