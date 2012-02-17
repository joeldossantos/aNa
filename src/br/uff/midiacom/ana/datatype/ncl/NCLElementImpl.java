package br.uff.midiacom.ana.datatype.ncl;

import br.uff.midiacom.ana.datatype.enums.NCLElementAttributes;
import br.uff.midiacom.ana.datatype.enums.NCLElementSets;
import br.uff.midiacom.xml.XMLElementImpl;
import br.uff.midiacom.xml.XMLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NCLElementImpl<T extends NCLIdentifiableElement,
                            P extends NCLElement>
        extends XMLElementImpl<T, P> {

    private NCLModificationListener listener;
    private NCLElement owner;


    public NCLElementImpl(NCLElement owner) throws XMLException {
        if(owner == null)
            throw new XMLException("Null owner element.");

        this.owner = owner;
    }
    
    
    @Override
    public void setId(String id) throws XMLException {
        super.setId(id);
        notifyAltered(NCLElementAttributes.ID, this.id, id);
    }


    @Override
    public boolean setParent(P parent) {
        P aux = getParent();
        if(super.setParent(parent)){
            notifyAltered(NCLElementAttributes.PARENT, aux, parent);
            return true;
        }
        return false;
    }
    
    
    @Override
    protected boolean validate(String id) {
        Pattern pattern = Pattern.compile("[_:A-Za-z][-._:A-Za-z0-9]*");
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }


    /**
     * Sets the notification listener for the element. If the listener is null,
     * the element will not notify its modifications.
     *
     * @param listener
     *          object to listen to modification notifications.
     */
    public void setModificationListener(NCLModificationListener listener) {
        this.listener = listener;
    }


    /**
     * Returns the element notification listener.
     *
     * @return
     *          object to listen to modification notifications or null if no
     *          listener is used.
     */
    public NCLModificationListener getModificationListener() {
        return listener;
    }


    /**
     * Notify the listener about a child node inserted.
     *
     * @param setName
     *          name of the child set.
     * @param inserted
     *          element inserted.
     */
    public void notifyInserted(NCLElementSets setName, NCLElement inserted) {
        if(listener != null)
            (new NCLNotifier(0, listener, owner, setName, inserted)).start();
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param setName
     *          name of the child set.
     * @param removed
     *          element removed.
     */
    public void notifyRemoved(NCLElementSets setName, NCLElement removed) {
        if(listener != null)
            (new NCLNotifier(1, listener, owner, setName, removed)).start();
    }


    /**
     * Notify the listener about a child node removed.
     *
     * @param setName
     *          name of the child set.
     * @param removed
     *          id of the element removed.
     */
    public void notifyRemoved(NCLElementSets setName, String removed) {
        if(listener != null)
            (new NCLNotifier(listener, owner, setName, removed)).start();
    }


    /**
     * Notify the listener about an attribute changed.
     *
     * @param attributeName
     *          the attribute changed.
     * @param oldValue
     *          the attribute old value.
     * @param newValue
     *          the attribute new value.
     */
    public void notifyAltered(NCLElementAttributes attributeName, Object oldValue, Object newValue) {
        if(listener != null)
            (new NCLNotifier(listener, owner, attributeName, oldValue, newValue)).start();
    }


    public class NCLNotifier extends Thread {

        private NCLElement source, other;
        private NCLElementSets setName;
        private NCLElementAttributes attName;
        private NCLModificationListener listener;
        private Object oldV, newV;
        private String other_id;
        private int type;


        public NCLNotifier(int type, NCLModificationListener listener, NCLElement source, NCLElementSets setName, NCLElement other) {
            super();
            this.type = type;
            this.listener = listener;
            this.source = source;
            this.setName = setName;
            this.other = other;
        }
        
        
        public NCLNotifier(NCLModificationListener listener, NCLElement source, NCLElementSets setName, String other) {
            super();
            this.type = 2;
            this.listener = listener;
            this.source = source;
            this.setName = setName;
            this.other_id = other;
        }


        public NCLNotifier(NCLModificationListener listener, NCLElement source, NCLElementAttributes attName, Object oldV, Object newV) {
            super();
            this.type = 3;
            this.listener = listener;
            this.source = source;
            this.attName = attName;
            this.oldV = oldV;
            this.newV = newV;
        }


        @Override
        public void run() {
            switch(type){
                case 0:
                    listener.insertedElement(source, setName, other);
                    return;
                case 1:
                    listener.removedElement(source, setName, other);
                    return;
                case 2:
                    listener.removedElement(source, setName, other_id);
                    return;
                case 3:
                    listener.alteredElement(source, attName, oldV, newV);
                    return;
            }
        }
    }
}
