import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ConcurrencyTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<Integer, String> myMap;

        //myMap = new HashMap<>();
        myMap = new ConcurrentHashMap<>();
        myMap.put(1, "One");
        myMap.put(2, "Two");
        myMap.put(3, "Three");

        Thread t1 = new Thread(new MyRunnable(myMap));
        Thread t2 = new Thread(new MyRunnable2(myMap));

        //t1.start();
        //t2.start();
        Runnable myLambda = () -> {
            System.out.println(Thread.currentThread().getName()+"-start");
            Set<Map.Entry<Integer, String>> s = myMap.entrySet();
            Iterator<Map.Entry<Integer, String>> ir = s.iterator();
            while(ir.hasNext()){
                System.out.println(ir.next().getValue());
            }
            System.out.println(Thread.currentThread().getName()+"-end");
        };
        Thread t3 = new Thread(myLambda);
        //t3.start();

        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(t1);
        es.execute(t2);
        es.execute(t3);


    }
}
