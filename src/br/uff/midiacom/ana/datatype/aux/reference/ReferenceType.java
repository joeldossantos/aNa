package br.uff.midiacom.ana.datatype.aux.reference;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;


/**
 * This class represents a reference inside an xml document.
 *
 * @param <O>
 *          the type of the reference owner element.
 * @param <T>
 *          the type of the referred (target) element.
 * @param <I>
 *          the type of the element representing the external base where the
 *          referred element is.
 */
public class ReferenceType<O extends NCLElement,
                           T extends NCLElement,
                           I extends NCLImportPrototype>
        extends br.uff.midiacom.xml.datatype.reference.ReferenceType<O, T, NCLElementAttributes> {

    protected I alias;
    

    /**
     * Reference constructor. Creates a reference to an element without presenting
     * any reference to where the element is.
     *
     * @param target
     *          referred element.
     * @param targetAtt
     *          referred element attribute.
     * @throws XMLException
     *          if one of the parameters is null.
     */
    public ReferenceType(T target, NCLElementAttributes targetAtt) throws XMLException {
        super(target, targetAtt);
    }


    /**
     * Reference constructor. Creates a reference to an element also indicating
     * where the element is.
     *
     * @param alias
     *          element representing the element location.
     * @param target
     *          referred element.
     * @param targetAtt
     *          referred element attribute.
     * @throws XMLException
     *          if one of the parameters is null.
     */
    public ReferenceType(I alias, T target, NCLElementAttributes targetAtt) throws XMLException {
        super(target, targetAtt);
        setAlias(alias);
    }


    /**
     * Set the reference alias. The alias is null if the referred element is in
     * the same document.
     * 
     * @param alias
     *          element representing the element location.
     */
    private void setAlias(I alias) throws XMLException {
        this.alias = alias;
        
        if(alias != null)
            alias.addReference(this);
    }
    
    
    /**
     * Get the reference alias. The alias is null if the referred element is in
     * the same document.
     * 
     * @return
     *          element representing the element location.
     */
    public I getAlias() {
        return alias;
    }
    
    
    @Override
    public void clean() throws XMLException {
        super.clean();
        
        if(alias != null)
            alias.removeReference(this);
    }


    /**
     * Returns the string that represents the element reference.
     *
     * @return
     *          string representing the element complete reference string.
     */
    public String parse() {
        String content = "";
        
        if(alias != null){
            content += alias.getAlias();
            content += "#";
        }
        
        switch(targetAtt){
            case ID:
                content += ((NCLIdentifiableElement) target).getId();
                break;
//            case NAME:
//                content += target.getName();
//                break;
        }
        
        return content;
    }
}
