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
package br.uff.midiacom.ana.region;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.NCLElementImpl;
import br.uff.midiacom.ana.NCLIdentifiableElement;
import br.uff.midiacom.ana.NCLModificationListener;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionBasePrototype;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.string.StringType;
import java.util.TreeSet;
import org.w3c.dom.Element;


/**
 * Esta classe define o elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
 * NCL nos permite definir onde cada no de midia sera apresentado, tanto em que classe de dispositivos como em qual regiao
 * de apresentaçao de cada dispositivo. Para cada classe de dispositivos de saida definimos, no cabeçalho do documento ( dentro do  elemento <i>head</i>),
 * uma colecao de regioes atraves do elemento <i>regionBase</i>.
 * Dentro de uma base de regioes definimos os elementos <i>region</i> que especificam
 * em que regioes da tela do dispositivo de saida serao exibidos os nos de conteudo. Alem de elementos <i>region</i>, uma base de regioes tambem pode
 * conter elementos <i>importBase</i> para importar uma base definida em outro documento NCL. Ela ainda pode conter o atributo device, que especifica o dispositivo
 * de exibicao relacionado aquela base.<br>
 *
 * 
 * @see br.pensario.region.NCLRegion
 *
 * @see <a href="http://www.dtv.org.br/download/pt-br/ABNTNBR15606-2_2007Vc3_2008.pdf">
 *          ABNT NBR 15606-2:2007</a>
 */
public class NCLRegionBase<T extends NCLRegionBase, P extends NCLElement, I extends NCLElementImpl, Er extends NCLRegion, Ei extends NCLImport>
        extends NCLRegionBasePrototype<T, P, I, Er, Ei> implements NCLIdentifiableElement<T, P> {


    /**
     * Construtor do elemento <i>regionBase</i> da <i>Nested Context Language</i> (NCL).
     */
    public NCLRegionBase() {}


    /**
     * Define um dispositivo de exibicao associado ao elemento <i>regionBase</i>. Ou seja, esse metodo determina para qual
     * dispositivo as regioes contidas naquela base serao validas.
     * @param device
     *          O metodo recebe como parametro um String representando o dispositivo de exibicao a que se quer relacionar a base de regioes atual.
     * @throws java.lang.IllegalArgumentException
     *          O metodo dispara uma excecao caso o usuario passe uma string vazia como parametro.
     */
    @Override
    public void setDevice(String device) throws XMLException {
        StringType aux = this.device;
        super.setDevice(device);
        impl.notifyAltered(NCLElementAttributes.DEVICE, aux, device);
    }


    /**
     * NCL permite que regioes sejam aninhadas. Dessa forma, o posicionamento e tamanho de uma regiao filha pode ser relativa a uma regiao pai
     * e expressa como porcentagem. Esse metodo atribui a <i>regionBase</i> a regiao pai, ou seja, a regiao mais externa do aninhamento
     * 
     * @param region
     *          Elemento representando a regiao a ser utilizada como pai.
     */
    @Override
    public void setParentRegion(Er region) {
        super.setParentRegion(region);
    }


    /**
     * Uma base de regioes pode conter como filhos elementos <i>region</i> e elementos <i>importBase</i>. Este metodo adiciona a base uma
     * um elemento <i>region</i>, uma regiao da tela do dispositivo relacionado a esta base.
     *
     * @see br.pensario.region.NCLRegion
     * 
     * @param region
     *          O método recebe como parametro um elemento <i>region</i> a ser adicionado.
     * @return
     *          Retorna verdadeiro se a regiao for adicionada com sucesso.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addRegion(Er region) throws XMLException {
        if(super.addRegion(region)){
            impl.notifyInserted(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Este metodo remove um elemento <i>region</i> da base. Nesta implementacao, o metodo recebe o identificador da regiao a ser removida.
     *
     * @param id
     *          O metodo recebe com parametro uma string representando o  identificador da regiao a ser removida.
     * @return
     *          Retorna verdadeiro se a regiao for removida com sucesso.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeRegion(String id) throws XMLException {
        for(Er region : regions){
            if(region.getId().equals(id))
                return removeRegion(region);
        }
        return false;
    }


    /**
     * Remove uma regiao da base de regioes. Nesta implementacao, o metodo recebe como parametro um objeto <i>region</i> a ser retirado da base.
     * 
     * @param region
     *          Recebe como parametro o proprio elemento region a ser removido.
     * @return
     *          Retorna verdadeiro se a regiao for removida com sucesso.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeRegion(Er region) throws XMLException {
        if(super.removeRegion(region)){
            impl.notifyRemoved(NCLElementSets.REGIONS, region);
            return true;
        }
        return false;
    }


    /**
     * Adiciona um elemento importBase a base de regioes. O elemento <i>importBase</i>, permite ao documento importar uma base de regioes
     * pertencente a outro documento NCL.
     *
     * @param importBase
     *          O método recebe como parametro um elemento <i>importBase</i> a ser adicionado.
     *
     * @see TreeSet#add
     */
    @Override
    public boolean addImportBase(Ei importBase) throws XMLException {
        if(super.addImportBase(importBase)){
            impl.notifyInserted(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


    /**
     * Remove um elemento <i>importBase</i> da base de regioes.
     *
     * @param importBase
     *           O metodo recebe como parâmetro um elemento <i>importBase</i> a ser removido.
     *
     * @see TreeSet#remove
     */
    @Override
    public boolean removeImportBase(Ei importBase) throws XMLException {
        if(super.removeImportBase(importBase)){
            impl.notifyRemoved(NCLElementSets.IMPORTS, importBase);
            return true;
        }
        return false;
    }


//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) {
//        try{
//            if(localName.equals("regionBase")){
//                cleanWarnings();
//                cleanErrors();
//                for(int i = 0; i < attributes.getLength(); i++){
//                    if(attributes.getLocalName(i).equals("id"))
//                        setId(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("device"))
//                        setDevice(attributes.getValue(i));
//                    else if(attributes.getLocalName(i).equals("region"))
//                        setParentRegion((R) new NCLRegion(attributes.getValue(i)));//cast retirado na correcao das referencias
//                }
//            }
//            else if(localName.equals("importBase")){
//                I child = createImportBase();
//                child.startElement(uri, localName, qName, attributes);
//                addImportBase(child);
//            }
//            else if(localName.equals("region")){
//                R child = createRegion();
//                child.startElement(uri, localName, qName, attributes);
//                addRegion(child);
//            }
//        }
//        catch(NCLInvalidIdentifierException ex){
//            addError(ex.getMessage());
//        }
//    }
//
//
//    @Override
//    public void endDocument() {
//        if(getParent() != null){
//            if(getParentRegion() != null)
//                setParentRegion(findRegion(getRegions()));
//        }
//
//        if(hasImportBase()){
//            for(I imp : imports){
//                imp.endDocument();
//                addWarning(imp.getWarnings());
//                addError(imp.getErrors());
//            }
//        }
//        if(hasRegion()){
//            for(R region : regions){
//                region.endDocument();
//                addWarning(region.getWarnings());
//                addError(region.getErrors());
//            }
//        }
//    }

   
//    private R findRegion(Set<R> regions) {
//        for(R reg : regions){
//            if(reg.hasRegion()){
//                NCLRegion r = findRegion(reg.getRegions());
//                if(r != null)
//                    return (R) r;
//            }
//            else{
//                if(reg.getId().equals(getParentRegion().getId()))
//                    return (R) reg;
//            }
//        }
//
//        addWarning("Could not find region in regionBase with id: " + getParentRegion().getId());
//        return null;
//    }


    public void load(Element element) throws XMLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void setModificationListener(NCLModificationListener listener) {
        impl.setModificationListener(listener);
    }


    public NCLModificationListener getModificationListener() {
        return impl.getModificationListener();
    }


//    /**
//     * Função de criação do elemento filho <i>importBase</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>importBase</i>.
//     */
//    protected I createImportBase() {
//        return (I) new NCLImport(NCLImportType.BASE, getReader(), this);
//    }
//
//
//    /**
//     * Função de criação do elemento filho <i>region</i>.
//     * Esta função deve ser sobrescrita em classes que estendem esta classe.
//     *
//     * @return
//     *          elemento representando o elemento filho <i>region</i>.
//     */
//    protected R createRegion() {
//        return (R) new NCLRegion(getReader(), this);
//    }
}
