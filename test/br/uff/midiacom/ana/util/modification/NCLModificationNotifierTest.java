package br.uff.midiacom.ana.util.modification;

import br.uff.midiacom.ana.util.exception.XMLException;
import br.uff.midiacom.ana.NCLDoc;
import br.uff.midiacom.ana.util.exception.NCLModificationException;
import org.junit.Test;
import static org.junit.Assert.*;


public class NCLModificationNotifierTest {
    
    private NCLDoc element;
    
    public NCLModificationNotifierTest() throws XMLException {
        element = new NCLDoc();
    }
    
    
    @Test
    public void test1() throws NCLModificationException, InterruptedException {
        NCLModificationNotifier notifier = NCLModificationNotifier.getInstance();
        Foo foo = new Foo();
        
        notifier.clearListeners();
        notifier.addListener(foo);
//        notifier.start();
        
        notifier.addNotification(new NCLNotification(element));
        notifier.addNotification(new NCLNotification(element));
        Thread.sleep(1000);
        notifier.addNotification(new NCLNotification(element));
        notifier.finish();
        
        assertEquals(3, foo.received);
    }
    
    
    @Test
    public void test2() throws NCLModificationException, InterruptedException {
        NCLModificationNotifier notifier = NCLModificationNotifier.getInstance();
        Foo foo1 = new Foo();
        Foo foo2 = new Foo();
        Foo foo3 = new Foo();
        
        notifier.clearListeners();
        notifier.addListener(foo1);
//        notifier.start();
        
        notifier.addNotification(new NCLNotification(element));
        Thread.sleep(1000);
        notifier.addListener(foo2);
        notifier.addNotification(new NCLNotification(element));
        Thread.sleep(1000);
        notifier.addListener(foo3);
        notifier.addNotification(new NCLNotification(element));
        notifier.finish();
        
        assertEquals(3, foo1.received);
        assertEquals(2, foo2.received);
        assertEquals(1, foo3.received);
    }
    
    
    private class Foo implements NCLModificationListener {
        
        public int received = 0;

        @Override
        public void modifiedElement(NCLNotification notification) {
            received++;
//            System.out.println("received = " + received);
        }
    }
}
