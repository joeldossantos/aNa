package br.uff.midiacom.ana.datatype.aux.reference;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.region.NCLRegionPrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;


public class RegionReference<T extends NCLRegionPrototype,
                             O extends NCLElement,
                             I extends NCLImportPrototype>
        extends ReferenceType<O, T, I> {

    
    public RegionReference(T target, NCLElementAttributes targetAtt) throws XMLException {
        super(target, targetAtt);
    }


    public RegionReference(I alias, T target, NCLElementAttributes targetAtt) throws XMLException {
        super(alias, target, targetAtt);
    }
    
    
    @Override
    protected String parseIdent() {
        return getTarget().getId();
    }
}