import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyRunnable2 implements Runnable {

    private Map<Integer, String> myMap;

    public MyRunnable2(Map<Integer, String> myMap) {
        this.myMap = myMap;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-start");
        Set<Entry<Integer, String>> s = myMap.entrySet();
        Iterator<Entry<Integer, String>> ir = s.iterator();
        while(ir.hasNext()){
            System.out.println(ir.next().getValue());
        }
        System.out.println(Thread.currentThread().getName()+"-end");
    }
}
