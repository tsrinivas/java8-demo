package java8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyLambda {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("one","two");
        List<String> ls2 = new ArrayList<>();

        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = ls2::add;

        Objects.requireNonNull(ls);

        ls.forEach(c1.andThen(c2));
        System.out.println(ls2.size());

        Predicate<String> p1 = Predicate.isEqual("one");
        ls2 = ls.stream().filter(p1).collect(Collectors.toList());
        System.out.println(ls2.size());

        Map<Integer, String> myMap = new ConcurrentHashMap<>();
        myMap.put(1, "One");
        myMap.put(2, "Two");
        myMap.put(3, "Three");

        Predicate<String> p2 = Predicate.isEqual("1");
        String result = myMap.entrySet()
                .stream()
                .filter(x -> "One".equals(x.getValue()))
                .map(x->x.getValue())
                .collect(Collectors.joining());

        System.out.println("--"+result);
    }
}
