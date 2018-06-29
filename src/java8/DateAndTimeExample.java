package java8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DateAndTimeExample {

    public static void main (String[] a){

        List<Person2> persons = new ArrayList<>();

        try(
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                DateAndTimeExample.class.getResourceAsStream("person2.txt")
                        )
                );
                Stream<String> stream = br.lines();
         ){
            stream.map(line -> {
                String[] s = line.split(" ");
                String name = s[0].trim();
                int year = Integer.parseInt(s[1]);
                Month month = Month.of(Integer.parseInt(s[2]));
                int day = Integer.parseInt(s[2]);

                Person2 p = new Person2(name, LocalDate.of(year, month, day));
                persons.add(p);
                return p;
            }).forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDate now = LocalDate.now();

        persons.stream().forEach(
                p -> {
                    Period period = Period.between(p.getDateOfBirth(), now);
                    System.out.println(p.getName() +" was born "
                            + period.get(ChronoUnit.YEARS) + " years and "
                            + period.get(ChronoUnit.MONTHS) +" months "
                            + "[" + p.getDateOfBirth().until(now, ChronoUnit.MONTHS) + " months]");
                }
        );
    }
}
