package br.uff.midiacom.ana.datatype.auxiliar;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.ncl.NCLElement;
import br.uff.midiacom.ana.datatype.ncl.NCLIdentifiableElement;
import br.uff.midiacom.ana.datatype.ncl.reuse.NCLImportPrototype;
import br.uff.midiacom.xml.XMLException;


/**
 * This class represents a reference inside an xml document.
 *
 * @param <E>
 *          the type of the referred element.
 */
public abstract class ReferenceType<T extends NCLElement, E extends NCLElement, I extends NCLImportPrototype> {

    protected I alias;
    protected E target;
    protected NCLElementAttributes targetAtt;
    protected T owner;
    protected NCLElementAttributes ownerAtt;
    


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
    public ReferenceType(E target, NCLElementAttributes targetAtt) throws XMLException {
        setTarget(target);
        setTargetAtt(targetAtt);
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
    public ReferenceType(I alias, E target, NCLElementAttributes targetAtt) throws XMLException {
        setAlias(alias);
        setTarget(target);
        setTargetAtt(targetAtt);
    }


    /**
     * Set the reference alias. The alias is null if the referred element is in
     * the same document.
     * 
     * @param alias
     *          element representing the element location.
     */
    private void setAlias(I alias) {
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
    
    
    /**
     * Set the reference target element. The target element can not be null.
     * 
     * @param target
     *          referred element.
     * @throws XMLException 
     *          if the target element is null.
     */
    private void setTarget(E target) throws XMLException {
        if(target == null)
            throw new XMLException("Null target element.");
        if(!(target instanceof ReferredElement))
            throw new XMLException("Wrong target element type.");
        
        this.target = target;
        ((ReferredElement) this.target).addReference(this);
    }
    
    
    /**
     * Get the reference target element.
     * 
     * @return
     *          referred element.
     */
    public E getTarget() {
        return target;
    }
    
    
    /**
     * Set the attribute used to make the reference to the target element. The
     * attribute can not be null.
     * 
     * @param targetAtt
     *          referred element attribute.
     * @throws XMLException 
     *          if the attribute is null.
     */
    private void setTargetAtt(NCLElementAttributes targetAtt) throws XMLException {
        if(targetAtt == null)
            throw new XMLException("Null target attribute.");
        
        this.targetAtt = targetAtt;
    }
    
    
    /**
     * Get the attribute used to make the reference to the target element.
     * 
     * @return
     *          referred element attribute.
     */
    public NCLElementAttributes getTargetAtt() {
        return targetAtt;
    }
    
    
    /**
     * Set the reference owner element. The owner element can not be null.
     * 
     * @param owner
     *          reference owner element.
     * @throws XMLException 
     *          if the owner element is null.
     */
    public void setOwner(T owner) throws XMLException {
        if(owner == null)
            throw new NullPointerException("Null owner element.");
        
        this.owner = owner;
    }
    
    
    /**
     * Get the reference owner element.
     * 
     * @return
     *          reference owner element.
     */
    public T getOwner() {
        return owner;
    }
    
    
    /**
     * Set the attribute that makes the reference. The attribute can not be null.
     * 
     * @param ownerAtt
     *          owner element attribute.
     * @throws XMLException 
     *          if the attribute is null.
     */
    public void setOwnerAtt(NCLElementAttributes ownerAtt) throws XMLException {
        if(ownerAtt == null)
            throw new XMLException("Null target attribute.");
        
        this.ownerAtt = ownerAtt;
    }
    
    
    /**
     * Get the attribute that makes the reference.
     * 
     * @return
     *          owner element attribute.
     */
    public NCLElementAttributes getOwnerAtt() {
        return ownerAtt;
    }
    
    
    public void clean() {
        if(alias != null)
            alias.removeReference(this);
        if(target != null)
            ((ReferredElement) target).removeReference(this);
    }


    /**
     * Returns the string that represents the element reference.
     *
     * @return
     *          string representing the element complete reference string.
     */
    protected String parse() {
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
