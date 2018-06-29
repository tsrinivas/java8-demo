package java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuildingBiMap {
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

        Map<Integer, List<Person>> map = mapByAge(persons);
        map.forEach((age, list) -> System.out.println(age + "->"+list));

        // BiMap
        Map<Integer, Map<String, List<Person>>> biMap =
                new HashMap<>();

        persons.forEach(
                person ->
                        biMap.computeIfAbsent(
                                person.getAge(),
                                HashMap::new
                        ).merge(
                                person.getGender(),
                                new ArrayList<>(Arrays.asList(person)),
                                (l1, l2) -> {
                                    l1.addAll(l2);
                                    return l1;
                                }
                        )
        );

        System.out.println("biMap...");
        biMap.forEach(
                (age, m) -> System.out.println(age+ "->"+m)
        );
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
