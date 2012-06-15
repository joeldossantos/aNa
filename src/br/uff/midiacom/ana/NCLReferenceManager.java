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
package br.uff.midiacom.ana;

import br.uff.midiacom.ana.util.exception.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.util.reference.PostReferenceElement;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.util.ElementList;
import java.util.ArrayList;


public class NCLReferenceManager {

    private static NCLReferenceManager instance = new NCLReferenceManager();
    private ArrayList<PostReferenceElement> references = new ArrayList<PostReferenceElement>();
    
    
    private NCLReferenceManager() {}
    
    
    public static NCLReferenceManager getInstance() {
        return instance;
    }
    
    
    public Object findRegionReference(NCLDoc doc, String id) throws XMLException {
        Object result = null;
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");

        if(!head.hasRegionBase())
            throw new NCLParsingException("Could not find regionBase element");
        
        ElementList<NCLRegionBase> list = head.getRegionBases();

        for(NCLRegionBase base : list){
            result = base.findRegion(id);
            if(result != null)
                break;
        }

        if(result == null)
            throw new NCLParsingException("Could not find region in regionBase with id: " + id);
        
        return result;
    }
    
    
    public Object findDescriptorReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        Object result = base.findDescriptor(id);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with id: " + id);
        
        return result;
    }


    public Object findTransitionReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLTransitionBase base = (NCLTransitionBase) head.getTransitionBase();
        if(base == null)
            throw new NCLParsingException("Could not find document transitionBase element");

        Object result = base.findTransition(id);

        if(result == null)
            throw new NCLParsingException("Could not find transition in transitionBase with id: " + id);
        
        return result;
    }


    public Object findConnectorReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLConnectorBase base = (NCLConnectorBase) head.getConnectorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document connectorBase element");

        Object result = base.findConnector(id);

        if(result == null)
            throw new NCLParsingException("Could not find connector in connectorBase with id: " + id);
        
        return result;
    }
    
    
    public void waitReference(PostReferenceElement element) {
        references.add(element);
    }
    
    
    public void fixReferences() throws XMLException {
        for(PostReferenceElement el : references)
            el.fixReference();
    }
}
