package br.uff.midiacom.ana.datatype.aux.reference;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLDescriptorPrototype;
import br.uff.midiacom.ana.datatype.ncl.descriptor.NCLLayoutDescriptor;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;


public class DescriptorReference<T extends NCLLayoutDescriptor,
                                 O extends NCLElement,
                                 I extends NCLImportPrototype>
        extends ReferenceType<O, T, I> {

    
    public DescriptorReference(T target, NCLElementAttributes targetAtt) throws XMLException {
        super(target, targetAtt);
    }


    public DescriptorReference(I alias, T target, NCLElementAttributes targetAtt) throws XMLException {
        super(alias, target, targetAtt);
    }
    
    
    @Override
    protected String parseIdent() {
        switch(getTargetAtt()){
            case ID:
                return getTarget().getId();
            case FOCUSINDEX:
                return ((NCLDescriptorPrototype) getTarget()).getFocusIndex().toString();
        }
        return null;
    }
}
