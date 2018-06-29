package java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergingMaps {

    public static void main(String[] args){
        List<Person> persons = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                CollectrosExample.class.getResourceAsStream("person.txt")));

                Stream<String> stream = br.lines()
        ) {

            stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(Integer.parseInt(s[1].trim()), s[0].trim(), s[2].trim());
                persons.add(p);
                return p;
            }).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Person> list1 = persons.subList(1, 5);
        List<Person> list2 = persons.subList(5, persons.size());

        Map<Integer, List<Person>> map1 = mapByAge(list1);
        /*System.out.println("Map1");
        map1.forEach((age, list) -> System.out.println(age + "->"+list));*/

        Map<Integer, List<Person>> map2 = mapByAge(list2);
        /*System.out.println("Map2");
        map2.forEach((age, list) -> System.out.println(age + "->"+list));*/

        // merge map2 keys to map1
        map2.entrySet().stream()
                .forEach(
                        entry ->
                                map1.merge(
                                        entry.getKey(),
                                        entry.getValue(),
                                        (l1, l2) -> {
                                            l1.addAll(l2);
                                            return l1;
                                        }
                                )
                );
        // map1 merged
        map1.forEach((age, list) -> System.out.println(age + "->"+list));
    }

    private static Map<Integer, List<Person>> mapByAge(List<Person> list) {
        Map<Integer, List<Person>> map =
                list.stream().collect(
                        Collectors.groupingBy(
                                Person::getAge
                        )
                );
        return map;
    }
}
