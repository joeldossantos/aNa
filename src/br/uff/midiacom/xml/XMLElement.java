/********************************************************************************
 * This file is part of the API for NCL Authoring - aNa.
 *
 * Copyright (c) 2011, MidiaCom Lab (www.midiacom.uff.br)
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
 *    display the following acknowledgment:
 *        This product includes the API for NCL Authoring - aNa
 *        (http://joeldossantos.github.com/aNa).
 *
 *  * Neither the name of the lab nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific
 *    prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MIDIACOM LAB AND CONTRIBUTORS ``AS IS'' AND
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
package br.uff.midiacom.xml;

import org.w3c.dom.Element;


/**
 * This interface defines the basic type of an XML element.
 *
 * @param <T>
 *          XML element type.
 * @param <P>
 *          XML element parent type.
 */
public interface XMLElement<T extends XMLElement,
                            P extends XMLElement> {


    /**
     * Sets the element parent.
     *
     * @param parent
     *          element representing the element parent.
     * @return
     *          true if the parent element was defined. If the element already
     *          has a parent element it returns false.
     */
    public boolean setParent(P parent);


    /**
     * Returns the parent element.
     *
     * @return
     *          element representing the parent element.
     */
    public P getParent();


    /**
     * Returns the XML code that represents the XML element.
     *
     * @param ident
     *          integer indicating the indentation level. The XML code will be
     *          indented by a tab "\t".
     * @return
     *          string representing the XML code.
     */
    public String parse(int ident);
    
    
    /**
     * Reads the XML code representing the XML element and creates the class.
     * 
     * @param element
     *          representation of the element to read.
     * @throws XMLException 
     *          if any error occur.
     */
    public void load(Element element) throws XMLException;


    /**
     * Compares two element.
     *
     * @param other
     *          the element to be compared with.
     * @return
     *          true if the elements are equal and false otherwise.
     */
    public boolean compare(T other);
}
