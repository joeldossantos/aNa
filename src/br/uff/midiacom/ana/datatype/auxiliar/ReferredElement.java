package br.uff.midiacom.ana.datatype.auxiliar;

import java.util.TreeSet;


public interface ReferredElement {

    
    public boolean addReference(ReferenceType reference);
    
    
    public boolean removeReference(ReferenceType reference);
    
    
    public TreeSet<ReferenceType> getReferences();
}
