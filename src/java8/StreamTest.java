package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args){

        Predicate<String> p3 = Predicate.isEqual("two");
        Predicate<String> p4 = Predicate.isEqual("three");

        Stream<String> st1 = Stream.of("one", "two", "three", "four", "five");
        /*st1
                .filter(p3.or(p4))
                .forEach(System.out::println);*/

        /*List<String> re1 = new ArrayList<>();
        st1
                .peek(System.out::println)
                .filter(p3.or(p4))
                .forEach(re1::add);
        System.out.println("size: " + re1.size());*/

        List<Integer> l1 = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> l2 = Arrays.asList(2,4,6);
        List<Integer> l3 = Arrays.asList(3,5,7);
        List<List<Integer>> l4 = Arrays.asList(l1, l2, l3);

        Function<List<?>, Integer> size = List :: size;
        Function<List<Integer>, Stream<Integer>> flatMapper = l -> l.stream();

        /*l4.stream()
                .map(size)
                .forEach(System.out::println);*/

        /*l4.stream()
                .flatMap(flatMapper)
                .filter(i -> i > 3)
                .forEach(System.out::println);*/

        /**
         * Reduction
         */
        Stream<Integer> stream1 = Stream.of(1,2,3,4,5);

        BinaryOperator<Integer> biOp = (i1, i2) -> i1+i2;
        int sum = stream1.reduce(0, biOp);
        System.out.println(sum);

        Stream<Integer> stream2 = Stream.empty();
        int sum2 = stream2.reduce(0, biOp);
        System.out.println(sum2);

        Stream<Integer> stream3 = Stream.of(1);
        int sum3 = stream3.reduce(0, biOp);
        System.out.println(sum3);

    }
}
