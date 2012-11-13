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
package br.uff.midiacom.ana.util.ncl;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.exception.NCLModificationException;
import br.uff.midiacom.ana.util.xml.*;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.util.modification.NCLModificationNotifier;
import br.uff.midiacom.ana.util.modification.NCLNotification;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class that implements the XMLElement interface.
 *
 * @param <T>
 *          XML element type.
 * @param <P>
 *          XML element parent type.
 */
public abstract class NCLElementPrototype<T extends NCLElement>
        extends XMLElementPrototype<T>
        implements NCLElement<T> {

    private NCLModificationNotifier notifier;
    

    public NCLElementPrototype() {
        notifier = NCLModificationNotifier.getInstance();
    }


    @Override
    @Deprecated
    public void setParent(T parent) throws XMLException {
        T aux = getParent();
        if(this.parent != null && parent != null)
            throw new XMLException("This element already has a parent element.");

        this.parent = parent;
        if(parent == null)
            this.doc = null;
        else
            setDoc((T) parent.getDoc());
        
        try {
            notifyAltered(NCLElementAttributes.PARENT, aux, parent);
        } catch (Exception ex) {}
    }
    
    
    @Deprecated
    public void setDoc(T doc) {
        this.doc = doc;
    }


    protected boolean validate(String id) {
        Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }
    
    
    protected String[] adjustReference(String element_id) {
        String[] result = new String[2];
        
        if(element_id.contains("#")){
            int index = element_id.indexOf("#");
            result[0] = element_id.substring(0, index);
            result[1] = element_id.substring(index + 1);
        }
        else{
            result[0] = null;
            result[1] = element_id;
        }
        
        return result;
    }


    /**
     * Notify the listener about a child node inserted.
     *
     * @param inserted
     *          element inserted.
     */
    @Deprecated
    public void notifyInserted(T inserted) throws NCLModificationException {
        notifier.addNotification(new NCLNotification(inserted));
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param removed
     *          element removed.
     */
    @Deprecated
    public void notifyRemoved(T removed) throws NCLModificationException {
        notifier.addNotification(new NCLNotification(this, removed));
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
    @Deprecated
    public void notifyAltered(NCLElementAttributes attributeName, Object oldValue, Object newValue) throws NCLModificationException {
        notifier.addNotification(new NCLNotification(this, attributeName, oldValue, newValue));
    }
}
