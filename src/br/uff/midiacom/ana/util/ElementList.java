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
package br.uff.midiacom.ana.util;

import br.uff.midiacom.ana.util.xml.XMLElement;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.ncl.NCLIdentifiableElementPrototype;
import br.uff.midiacom.ana.util.ncl.NCLNamedElementPrototype;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * This class represents a list of xml elements. This element list does not
 * stores null elements or repeated elements.
 *
 * @param <T>
 *          the type of element stored in the list.
 */
public class ElementList<T extends XMLElement>
        implements Iterable<T>, Serializable {

    protected List<T> elements;


    /**
     * Element list constructor.
     */
    public ElementList() {
        elements = new ArrayList<T>();
    }


    /**
     * Returns the size of the list.
     *
     * @return
     *          integer representing the list size.
     */
    public int size() {
        return elements.size();
    }


    /**
     * Verifies if the list is empty.
     *
     * @return
     *          boolean indicating if the list is empty.
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    

    /**
     * Verifies if the list contains one specific element.
     *
     * @param element
     *          element to be verified.
     * @return
     *          true if the element exists in the list.
     * @throws XMLException
     *          if the element is null.
     */
    public boolean contains(T element) throws XMLException {
        if(element == null)
            throw new XMLException("Null element.");

        return elements.contains(element);
    }


    /**
     * Returns the list iterator.
     *
     * @return
     *          iterator of the list elements.
     */
    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
    

    /**
     * Adds an element to the list. If the element to be added is equals to an
     * element already in the list, the new element has preference.
     *
     * @param element
     *          element to be added.
     * @return
     *          true if the element was added to the list.
     * @throws XMLException
     *          if the element is null.
     */
    public boolean add(T element) throws XMLException {
        if(element == null)
            throw new XMLException("Null element.");

        for(T el : elements){
            if(el.compare(element)){
                elements.remove(el);
                break;
            }
        }

        return elements.add(element);
    }
    

    /**
     * Removes an element of the list.
     *
     * @param element
     *          element to be removed from the list.
     * @return
     *          true if the element was removed.
     * @throws XMLException
     *          if the element is null.
     */
    public boolean remove(T element) throws XMLException {
        if(element == null)
            throw new XMLException("Null element.");

        if(elements.remove(element)){
            element.setParent(null);
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes an element with an specific identification.
     *
     * @param ident
     *          identification of the element to be removed.
     * @return
     *          true if the element was removed.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public boolean remove(String ident) throws XMLException {
        if(ident == null)
            throw new XMLException("Null identification string.");
        if("".equals(ident.trim()))
            throw new XMLException("Empty identification string");

        T aux = get(ident);
        if(aux != null)
            return elements.remove(aux);
        else
            return false;
    }


    /**
     * Adds a list of elements in the list. If the list received has an element
     * equals to an element in this list, the element already in the list gets
     * preference.
     *
     * @param list
     *          list of elements to be added.
     * @return
     *          true if all elements were added and false if at least one
     *          element was not added.
     */
    public boolean addAll(ElementList<T> list) {
        boolean result = true;
        
        for(T aux : list){
            if(elements.contains(aux))
                result = false;
            else
                elements.add(aux);
        }
        
        return result;
    }


    /**
     * Removes a collection of elements of the list.
     *
     * @param clctn
     *          collection of elements to be removed.
     * @return
     *          true if the elements were removed.
     */
    public boolean removeAll(Collection<?> clctn) {
        return elements.removeAll(clctn);
    }


    /**
     * Removes all the elements from the list.
     */
    public void clear() {
        elements.clear();
    }


    /**
     * Returns the element at an specific position.
     *
     * @param i
     *          position of the element to get.
     * @return
     *          the element at position i in the list.
     * @throws XMLException
     *          if the position is out of bounds.
     */
    public T get(int i) throws XMLException {
        if(i < 0 || i >= elements.size())
            throw new XMLException("Index out of bounds.");

        return elements.get(i);
    }


    /**
     * Returns the element with an specific identification. The element
     * identification can be its id, name or alias.
     *
     * @param ident
     *          identification of the element to be retrieved.
     * @return
     *          element of the list with the identification. Return <i>null</i>
     *          if the element was not found or the element type does not have
     *          an identification.
     * @throws XMLException
     *          if the string is null or empty.
     */
    public T get(String ident) throws XMLException {
        if(ident == null)
            throw new XMLException("Null identification string.");
        if("".equals(ident.trim()))
            throw new XMLException("Empty identification string");
        
        if(elements.isEmpty())
            return null;
        
        T aux = elements.get(0);
        if(aux instanceof NCLIdentifiableElementPrototype){
            for(T el : elements){
                if(((NCLIdentifiableElementPrototype) el).getId().equals(ident))
                    return el;
            }
        }
        else if(aux instanceof NCLNamedElementPrototype){
            for(T el : elements){
                Object name = ((NCLNamedElementPrototype) el).getName();
                if(name instanceof NCLNamedElementPrototype)
                    name = ((NCLNamedElementPrototype) name).getName();
                
                if(name.toString().equals(ident))
                    return el;
            }
        }
        
        return null;
    }
    
    
    /**
     * Returns the position of an element.
     *
     * @param element
     *          element to get the position.
     * @return
     *          the element position in the list.
     */
    public int getPosition(T element) {
        return elements.indexOf(element);
    }
}
