package br.uff.midiacom.ana.datatype.aux.reference;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.node.NCLNode;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;


public class NodeReference<T extends NCLNode,
                           O extends NCLElement,
                           I extends NCLImportPrototype>
        extends ReferenceType<O, T, I> {

    
    public NodeReference(T target, NCLElementAttributes targetAtt) throws XMLException {
        super(target, targetAtt);
    }


    public NodeReference(I alias, T target, NCLElementAttributes targetAtt) throws XMLException {
        super(alias, target, targetAtt);
    }
    
    
    @Override
    protected String parseIdent() {
        return getTarget().getId();
    }
}