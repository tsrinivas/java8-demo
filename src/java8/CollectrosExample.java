package java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectrosExample {

    public static void main(String[] a) {

        List<Person> persons = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            CollectrosExample.class.getResourceAsStream("person.txt")));

            Stream<String> stream = br.lines()
        ) {

            /*stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(Integer.parseInt(s[1].trim()), s[0].trim());
                persons.add(p);
                return p;
            }).forEach(System.out::println);*/

            persons = stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(Integer.parseInt(s[1].trim()), s[0].trim());
                return p;
            }).collect(Collectors.toList());

            // Convert string to map
            /*Map<String, String> m = stream
                    .map(line -> line.split(" "))
                    .collect(Collectors.toMap(k -> k[0],
                            k -> k[1]
                    ));
            System.out.println(m);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Yougest person older than 20
        Optional<Person> opt =
                persons.stream()
                        .filter(p -> p.getAge() >= 20)
                        .min(Comparator.comparing(Person::getAge));

        System.out.println(opt);

        // Older person
        Optional<Person> opt2 =
                persons.stream()
                        .max(Comparator.comparing(Person::getAge));

        System.out.println(opt2);

        // Collectors

        // Group by age and list of persons
        Map<Integer, List<Person>> map =
        persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge
                        )
                );
        System.out.println(map);

        // Group by age and count of persons
        Map<Integer, Long> map2 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getAge,
                                        Collectors.counting()
                                )
                        );
        System.out.println(map2);

        // Group by age and list of person name
        Map<Integer, List<String>> map3 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getAge,
                                        Collectors.mapping(
                                                Person::getName,
                                                Collectors.toList()
                                        )
                                )
                        );
        System.out.println(map3);

        // Group by age and list of person name ordered
        Map<Integer, Set<String>> map4 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getAge,
                                        Collectors.mapping(
                                                Person::getName,
                                                Collectors.toCollection(TreeSet::new)
                                        )
                                )
                        );
        System.out.println(map4);

        // Group by age and list of person name separated by comma(,)
        Map<Integer, String> map5 =
                persons.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getAge,
                                        Collectors.mapping(
                                                Person::getName,
                                                Collectors.joining(",")
                                        )
                                )
                        );
        System.out.println(map5);
    }
}
