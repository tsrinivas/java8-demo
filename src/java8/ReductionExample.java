package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReductionExample {

    public static void main(String[] a){

        List<Integer> list = Arrays.asList(-10);

        /*Integer red = list.stream()
                .reduce(0, Integer :: max);*/

        Optional<Integer> red = list.stream()
                                        .reduce(Integer :: max);

        System.out.println(red);
    }
}
