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

import br.uff.midiacom.ana.datatype.ncl.NCLParsingException;
import br.uff.midiacom.ana.connector.NCLCausalConnector;
import br.uff.midiacom.ana.connector.NCLConnectorBase;
import br.uff.midiacom.ana.connector.NCLConnectorParam;
import br.uff.midiacom.ana.datatype.aux.reference.ConnectorReference;
import br.uff.midiacom.ana.datatype.aux.reference.DescriptorReference;
import br.uff.midiacom.ana.datatype.aux.reference.InterfaceReference;
import br.uff.midiacom.ana.datatype.aux.reference.PostReferenceElement;
import br.uff.midiacom.ana.datatype.aux.reference.ReferenceType;
import br.uff.midiacom.ana.datatype.aux.reference.RegionReference;
import br.uff.midiacom.ana.datatype.aux.reference.RuleReference;
import br.uff.midiacom.ana.datatype.aux.reference.TransitionReference;
import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.descriptor.NCLDescriptor;
import br.uff.midiacom.ana.descriptor.NCLDescriptorBase;
import br.uff.midiacom.ana.interfaces.NCLProperty;
import br.uff.midiacom.ana.link.NCLLink;
import br.uff.midiacom.ana.node.NCLNode;
import br.uff.midiacom.ana.region.NCLRegion;
import br.uff.midiacom.ana.region.NCLRegionBase;
import br.uff.midiacom.ana.reuse.NCLImport;
import br.uff.midiacom.ana.rule.NCLRuleBase;
import br.uff.midiacom.ana.rule.NCLTestRule;
import br.uff.midiacom.ana.transition.NCLTransition;
import br.uff.midiacom.ana.transition.NCLTransitionBase;
import br.uff.midiacom.xml.XMLException;
import br.uff.midiacom.xml.datatype.elementList.IdentifiableElementList;
import java.util.ArrayList;


public class NCLReferenceManager {

    private static NCLReferenceManager instance = new NCLReferenceManager();
    private ArrayList<PostReferenceElement> references = new ArrayList<PostReferenceElement>();
    
    
    private NCLReferenceManager() {}
    
    
    public static NCLReferenceManager getInstance() {
        return instance;
    }
    
    
    public RegionReference findRegionReference(NCLDoc doc, String id) throws XMLException {
        RegionReference result = null;
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");

        if(!head.hasRegionBase())
            throw new NCLParsingException("Could not find regionBase element");
        
        IdentifiableElementList<NCLRegionBase, NCLHead> list = head.getRegionBases();

        for(NCLRegionBase base : list){
            result = base.findRegion(id);
            if(result != null)
                break;
        }

        if(result == null)
            throw new NCLParsingException("Could not find region in regionBase with id: " + id);
        
        return result;
    }
    
    
    public InterfaceReference findPropertyReference(NCLDoc doc, String name) throws XMLException {
        NCLBody body = (NCLBody) doc.getBody();
        
        if(body == null)
            throw new NCLParsingException("Could not find document body element");

        NCLProperty result = (NCLProperty) body.findInterface(name);
        
        if(result == null)
            throw new NCLParsingException("Could not find property in body with name: " + name);
        
        return new InterfaceReference(result, NCLElementAttributes.NAME);
    }
    
    
    public NCLDescriptor findDescriptorReference(NCLDoc doc, Integer focusIndex) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        NCLDescriptor result = (NCLDescriptor) base.findDescriptor(focusIndex);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with focusIndex: " + focusIndex);
        
        return result;
    }
    
    
    public DescriptorReference findDescriptorReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLDescriptorBase base = (NCLDescriptorBase) head.getDescriptorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document descriptorBase element");

        DescriptorReference result = base.findDescriptor(id);

        if(result == null)
            throw new NCLParsingException("Could not find descriptor in descriptorBase with id: " + id);
        
        return result;
    }


    public TransitionReference findTransitionReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLTransitionBase base = (NCLTransitionBase) head.getTransitionBase();
        if(base == null)
            throw new NCLParsingException("Could not find document transitionBase element");

        TransitionReference result = base.findTransition(id);

        if(result == null)
            throw new NCLParsingException("Could not find transition in transitionBase with id: " + id);
        
        return result;
    }
    
    
    public RuleReference findRuleReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLRuleBase base = (NCLRuleBase) head.getRuleBase();
        if(base == null)
            throw new NCLParsingException("Could not find document ruleBase element");

        RuleReference result = base.findRule(id);

        if(result == null)
            throw new NCLParsingException("Could not find rule in ruleBase with id: " + id);
        
        return result;
    }
    
    
    public NCLConnectorParam findParamReference(NCLDoc doc, String name) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLConnectorBase base = (NCLConnectorBase) head.getConnectorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document connectorBase element");
        
        IdentifiableElementList<NCLCausalConnector, NCLConnectorBase> list = base.getCausalConnectors();
        NCLConnectorParam result = null;
        
        for(NCLCausalConnector conn : list){
            result = (NCLConnectorParam) conn.getConnectorParams().get(name);
            if(result != null)
                break;
        }

        if(result == null)
            throw new NCLParsingException("Could not find connectorParam in connectorBase with name: " + name);
        
        return result;
    }


    public ConnectorReference findConnectorReference(NCLDoc doc, String id) throws XMLException {
        NCLHead head = (NCLHead) doc.getHead();
        
        if(head == null)
            throw new NCLParsingException("Could not find document head element");
        
        NCLConnectorBase base = (NCLConnectorBase) head.getConnectorBase();
        if(base == null)
            throw new NCLParsingException("Could not find document connectorBase element");

        ConnectorReference result = base.findConnector(id);

        if(result == null)
            throw new NCLParsingException("Could not find connector in connectorBase with id: " + id);
        
        return result;
    }
    
    
    public NCLNode findNodeReference(NCLDoc doc, String id) throws XMLException {
        NCLBody body = (NCLBody) doc.getBody();
        
        if(body == null)
            throw new NCLParsingException("Could not find document body element");

        NCLNode result = body.findNode(id);

        if(result == null)
            throw new NCLParsingException("Could not find node in ruleBase with id: " + id);
        
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
