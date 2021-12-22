import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        // Количество несовершеннолетних
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних - " + count);

        // список призывников (фамилии мужчин возраста от 18 до 27 лет
        List<String> conscriptFamilie = (List<String>) persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Призывники");
        for (int i = 0; i < 10; i++) {
            System.out.println(conscriptFamilie.get(i));
        }

        // потенциально работоспособные (ж - 18-60 лет, м - 18-65 лет, высшее образование

        List<Person> wokers = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getAge() <= 60 && person.getSex() == Sex.WOMAN) || (person.getAge() <= 65 && person.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Работоспособное население");
        for (int i = 0; i < 10; i++) {
            System.out.println(wokers.get(i).toString());
        }

    }
}
