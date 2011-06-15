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
import br.uff.midiacom.ana.datatype.NCLElementSets;


/*******************************************************************************
 * Interface para os objetos que serão notificados de modificações feitas nos
 * Elementos da API.
 ******************************************************************************/
public interface NCLModificationListener {


    /***************************************************************************
     * Método para notificar um objeto de que um elemento NCL (filho) foi inserido
     * em um conjunto de elementos NCL mantidos por um elemento NCL (pai).
     *
     * @param source
     *          elemento que teve seu conjunto de elementos filhos alterada (pai).
     * @param setName
     *          nome do conjunto de elementos que foi alterada.
     * @param inserted
     *          elemento que foi inserido na lista de elemento filhos (filho).
     **************************************************************************/
    public void insertedElement(Element source, NCLElementSets setName, Element inserted);


    /***************************************************************************
     * Método para notificar um objeto de que um elemento NCL (filho) foi removido
     * em um conjunto de elementos NCL mantidos por um elemento NCL (pai).
     *
     * @param source
     *          elemento que teve seu conjunto de elementos filhos alterada (pai).
     * @param setName
     *          nome do conjunto de elementos que foi alterada.
     * @param inserted
     *          elemento que foi removido da lista de elemento filhos (filho).
     **************************************************************************/
    public void removedElement(Element source, NCLElementSets setName, Element removed);


    /***************************************************************************
     * Método para notificar um objeto de que um elemento NCL teve um atributo
     * modificado.
     *
     * @param source
     *          elemento que teve seu atributo modificado.
     * @param attributeName
     *          nome do atributo que foi modificado.
     * @param oldValue
     *          valor anterior do atributo.
     * @param newValue
     *          valor do atributo após a alteração.
     **************************************************************************/
    public void alteredElement(Element source, NCLElementAttributes attributeName, Object oldValue, Object newValue);
}
