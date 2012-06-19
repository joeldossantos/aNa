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
package br.uff.midiacom.ana.util.modification;

import br.uff.midiacom.ana.NCLElement;
import br.uff.midiacom.ana.util.enums.NCLElementAttributes;
import br.uff.midiacom.ana.util.exception.NCLModificationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Class that represents a notification of a modification in an element.
 */
public class NCLNotification {
    
    public enum NCLNotificationType {INSERTED_CHILD, REMOVED_CHILD, ALTERED_ATT;}
    
    private int orderNumber;
    private String timeStamp;
    private NCLNotificationType type;
    private NCLElementAttributes attName;
    private NCLElement source, element;
    private Object old_value, new_value;
    
    
    public NCLNotification(NCLElement element) throws NCLModificationException {
        super();
        setTimeStamp();
        type = NCLNotificationType.INSERTED_CHILD;
        setElement(element);
    }


    public NCLNotification(NCLElement source, NCLElement element) throws NCLModificationException {
        super();
        setTimeStamp();
        type = NCLNotificationType.REMOVED_CHILD;
        setSource(source);
        setElement(element);
    }


    public NCLNotification(NCLElement source, NCLElementAttributes attName, Object old_value, Object new_value) throws NCLModificationException {
        super();
        setTimeStamp();
        type = NCLNotificationType.ALTERED_ATT;
        setSource(source);
        setAttribute(attName);
        setOldValue(old_value);
        setNewValue(new_value);
    }
    
    
    protected void setOrderNumber(int number) {
        this.orderNumber = number;
    }
    
    
    private void setTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       //get current date time with Date()
       Date date = new Date();
       timeStamp = dateFormat.format(date);
    }
    
    
    private void setSource(NCLElement source) throws NCLModificationException {
        if(source == null)
            throw new NCLModificationException("Null source element.");
        
        this.source = source;
    }
    
    
    private void setElement(NCLElement element) throws NCLModificationException {
        if(element == null)
            throw new NCLModificationException("Null element.");
        
        this.element = element;
    }
    
    
    private void setAttribute(NCLElementAttributes attName) throws NCLModificationException {
        if(attName == null)
            throw new NCLModificationException("Null attribute name.");
        
        this.attName = attName;
    }
    
    
    private void setOldValue(Object old_value) throws NCLModificationException {
        this.old_value = old_value;
    }
    
    
    private void setNewValue(Object new_value) throws NCLModificationException {
        this.new_value = new_value;
    }
    
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    
    public String getTimeStamp() {
        return timeStamp;
    }
    
    
    public NCLNotificationType getType() {
        return type;
    }
    
    
    public NCLElement getSource() {
        return source;
    }
    
    
    public NCLElement getElement() {
        return element;
    }
    
    
    public NCLElementAttributes getAttribute() {
        return attName;
    }
    
    
    public Object getOldValue() {
        return old_value;
    }
    
    
    public Object getNewValue() {
        return new_value;
    }
}
