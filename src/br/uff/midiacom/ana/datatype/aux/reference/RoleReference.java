package br.uff.midiacom.ana.datatype.aux.reference;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.connector.NCLRolePrototype;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;


public class RoleReference<T extends NCLRolePrototype,
                           O extends NCLElement,
                           I extends NCLImportPrototype>
        extends ReferenceType<O, T, I> {

    
    public RoleReference(T target, NCLElementAttributes targetAtt) throws XMLException {
        super(target, targetAtt);
    }


    public RoleReference(I alias, T target, NCLElementAttributes targetAtt) throws XMLException {
        super(alias, target, targetAtt);
    }
    
    
    @Override
    protected String parseIdent() {
        return getTarget().getName();
    }
}
