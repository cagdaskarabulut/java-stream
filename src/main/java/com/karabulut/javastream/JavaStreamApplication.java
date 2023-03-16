package com.karabulut.javastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class JavaStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaStreamApplication.class, args);

        exercise();
    }

    public static void exercise(){
        List<Person> people = getPeople();
        System.out.println();

        //Filter
        List<Person> maleList = people.stream()
                        .filter(x->x.getGender().equals(Gender.MALE)).collect(Collectors.toList());
        System.out.println("Filter Example");
        maleList.forEach(System.out::println);
        System.out.println();

        //Foreach
        people.stream().forEach(x->x.setAge(x.getAge()*2));
        System.out.println("Foreach Example");
        people.forEach(System.out::println);
        System.out.println();

        // Add if
        boolean is_Cagdas_exist = people.stream()
                .filter(x->x.getName().equals("Cagdas"))
                .collect(Collectors.toList())
                .isEmpty();
           if(is_Cagdas_exist){
               people.add(new Person("Cagdas", 36, Gender.MALE));
               System.out.println("Add if - Listeye Cagdas eklendi");
           } else {
               System.out.println("Add if - Listede Cagdas zaten var");
           }
        System.out.println();

       //Peek
        List<Person> person = people.stream().filter(x-> x.getName().equals("Cagdas")).peek(x-> System.out.println("Peek Example"+x)).collect(Collectors.toList());
        System.out.println();

        //Remove if
        people.removeIf(x-> {
            boolean is_equals = x.getName().equals("Cagdas");
            if(is_equals) System.out.println("Remove if - listeden Cagdas silindi");
            return  is_equals;
        });
        System.out.println();

        // Sort
        System.out.println("Sort Example");
        people.stream()
                .filter(x -> x.getAge()<15)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .forEach(System.out::println);
        System.out.println();

        // All match
        boolean allMatch = people.stream()
                .allMatch(x -> x.getName().contains("Al"));
        System.out.println("All match Example : " + allMatch);
        System.out.println();

        // Any match
        boolean anyMatch = people.stream()
                .anyMatch(x -> x.getAge()>50);
        System.out.println("Any match Example : " + anyMatch);
        System.out.println();

        // None match
        boolean noneMatch = people.stream()
                .noneMatch(x -> x.getAge()>50);
        System.out.println("None match Example : " + noneMatch);
        System.out.println();

        // Max
        System.out.println("Max Example");
        people.stream()
                .filter(x->x.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        System.out.println();

        // Min
        System.out.println("Min Example");
        people.stream()
                .filter(x-> x.getName().contains("Al"))
                .min(Comparator.comparing(Person::getAge));
        System.out.println();

        // Skip
        System.out.println("Skip Example");
        IntStream.of(61,62,63,64,65)
                .skip(2)
                        .filter(i-> i>5)
                                .forEach(i-> System.out.println(i));
        System.out.println();

        // Group
        System.out.println("Group Example");
        Map<Gender,List<Person>> youngs = people.stream()
                .filter(x->x.getAge()<70)
                .collect(Collectors.groupingBy(Person::getGender));
        youngs.forEach((gender,personList)-> {
            System.out.println("Cinsiyet:" + gender);
            personList.forEach(System.out::println);
        });
        System.out.println();

        //Map
        Integer sum = person.stream().filter(x->{
            return x.getAge()>30;
        }).map(Person::getAge)
                .peek(x-> System.out.println(x))
                .reduce(0,(a,b)-> {
                    return a+b;
                });
        System.out.println("Map Example - "+sum);
        System.out.println();

    }

    private static ArrayList<Person> getPeople() {
        ArrayList userList = new ArrayList();
        userList.addAll(List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        ));
        return userList;
    }
}
