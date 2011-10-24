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

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.xml.XMLException;


public class NCLElementImpl<T extends NCLIdentifiableElement, P extends NCLElement>
        extends br.uff.midiacom.ana.datatype.ncl.NCLElementImpl<T, P> {

    private NCLModificationListener listener;
    private NCLElement owner;


    public NCLElementImpl(NCLElement owner) throws XMLException {
        if(owner == null)
            throw new XMLException("Null owner element.");

        this.owner = owner;
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        super.setId(id);
        notifyAltered(NCLElementAttributes.ID, this.id, id);
    }


    @Override
    public boolean setParent(P parent) {
        P aux = getParent();
        if(super.setParent(parent)){
            notifyAltered(NCLElementAttributes.PARENT, aux, parent);
            return true;
        }
        return false;
    }
    
    
    /**
     * Walk through the element parents until it finds the document root element.
     * 
     * @return 
     *          element representing the document root.
     */
    public NCLDoc getDoc() throws NCLParsingException {
        NCLElement doc = getParent();

        while(!(doc instanceof NCLDoc)){
            doc = (NCLElement) doc.getParent();
            if(doc == null){
                throw new NCLParsingException("Could not find document root element.");
            }
        }
        
        return (NCLDoc) doc;
    }


    /**
     * Sets the notification listener for the element. If the listener is null,
     * the element will not notify its modifications.
     *
     * @param listener
     *          object to listen to modification notifications.
     */
    public void setModificationListener(NCLModificationListener listener) {
        this.listener = listener;
    }


    /**
     * Returns the element notification listener.
     *
     * @return
     *          object to listen to modification notifications or null if no
     *          listener is used.
     */
    public NCLModificationListener getModificationListener() {
        return listener;
    }


    /**
     * Notify the listener about a child node inserted.
     *
     * @param setName
     *          name of the child set.
     * @param inserted
     *          element inserted.
     */
    public void notifyInserted(NCLElementSets setName, NCLElement inserted) {
        if(listener != null)
            (new NCLNotifier(0, listener, owner, setName, inserted)).start();
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param setName
     *          name of the child set.
     * @param removed
     *          element removed.
     */
    public void notifyRemoved(NCLElementSets setName, NCLElement removed) {
        if(listener != null)
            (new NCLNotifier(1, listener, owner, setName, removed)).start();
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param setName
     *          name of the child set.
     * @param removed
     *          id of the element removed.
     */
    public void notifyRemoved(NCLElementSets setName, String removed) {
        if(listener != null)
            (new NCLNotifier(listener, owner, setName, removed)).start();
    }


    /**
     * Notify the listener about an attribute changed.
     *
     * @param attributeName
     *          the attribute changed.
     * @param oldValue
     *          the attribute old value.
     * @param newValue
     *          the attribute new value.
     */
    public void notifyAltered(NCLElementAttributes attributeName, Object oldValue, Object newValue) {
        if(listener != null)
            (new NCLNotifier(listener, owner, attributeName, oldValue, newValue)).start();
    }


    public class NCLNotifier extends Thread {

        private NCLElement source, other;
        private NCLElementSets setName;
        private NCLElementAttributes attName;
        private NCLModificationListener listener;
        private Object oldV, newV;
        private String other_id;
        private int type;


        public NCLNotifier(int type, NCLModificationListener listener, NCLElement source, NCLElementSets setName, NCLElement other) {
            super();
            this.type = type;
            this.listener = listener;
            this.source = source;
            this.setName = setName;
            this.other = other;
        }
        
        
        public NCLNotifier(NCLModificationListener listener, NCLElement source, NCLElementSets setName, String other) {
            super();
            this.type = 2;
            this.listener = listener;
            this.source = source;
            this.setName = setName;
            this.other_id = other;
        }


        public NCLNotifier(NCLModificationListener listener, NCLElement source, NCLElementAttributes attName, Object oldV, Object newV) {
            super();
            this.type = 3;
            this.listener = listener;
            this.source = source;
            this.attName = attName;
            this.oldV = oldV;
            this.newV = newV;
        }


        @Override
        public void run() {
            switch(type){
                case 0:
                    listener.insertedElement(source, setName, other);
                    return;
                case 1:
                    listener.removedElement(source, setName, other);
                    return;
                case 2:
                    listener.removedElement(source, setName, other_id);
                    return;
                case 3:
                    listener.alteredElement(source, attName, oldV, newV);
                    return;
            }
        }
    }
}
