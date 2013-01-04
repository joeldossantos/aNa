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

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Class that implements a notifier for modifications in the elements.
 */
public class NCLModificationNotifier extends Thread implements Serializable {
    
    private int index;
    private boolean run;
    private ArrayList<NCLNotification> notifications;
    private ArrayList<NCLModificationListener> listeners;
    
    private static NCLModificationNotifier instance;
    
    
    private NCLModificationNotifier() {
        index = 0;
        run = true;
        notifications = new ArrayList<NCLNotification>();
        listeners = new ArrayList<NCLModificationListener>();
        start();
    }
    
    
    public synchronized static NCLModificationNotifier getInstance() {
        if(instance == null)
            instance = new NCLModificationNotifier();
        
        return instance;
    }
    
    
    public synchronized void addNotification(NCLNotification notification) {
        if(listeners.isEmpty())
            return;
        
        index++;
        notification.setOrderNumber(index);
        notifications.add(notification);
        notify();
    }
    
    
    public void addListener(NCLModificationListener listener) {
        listeners.add(listener);
    }
    
    
    public void removeListener(NCLModificationListener listener) {
        listeners.remove(listener);
    }
    
    
    public void clearListeners() {
        listeners.clear();
    }
    
    
    public void finish() throws InterruptedException {
        if(notifications.isEmpty())
            interrupt();
        else
            run = false;
        
        instance = null;
        join();
    }
    
    
    protected synchronized void sendNotification() throws InterruptedException{
        if(!notifications.isEmpty()){
            NCLNotification notification = notifications.get(0);
            notifications.remove(0);
            
            for (NCLModificationListener listener : listeners) {
                // Avoiding user errors
                try{
                    listener.modifiedElement(notification);
                }catch(Exception e){}
            }
        }
        else
            wait();
    }
    
    
    protected void sendRemainingNotifications() throws InterruptedException{
        if(notifications.isEmpty())
            return;
        
        for (NCLNotification notification : notifications) {
            for (NCLModificationListener listener : listeners) {
                listener.modifiedElement(notification);
            }
        }
        
        notifications.clear();
    }
    
    
    @Override
    public void run() {
        try{
            while(run)
                sendNotification();
            
            sendRemainingNotifications();
        }catch(Exception ex){}
    }
}
