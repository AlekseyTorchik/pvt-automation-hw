package lesson_2;

import java.util.Scanner;

public class Example {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число a: ");
        int a = scanner.nextInt();
        System.out.print("Введите число b: ");
        int b = scanner.nextInt();
        Methods methods = new Methods();
        System.out.println("Минимальное число: " + methods.compare(a, b));
        System.out.print("Введите число для проверки его на чётность: ");
        int c = scanner.nextInt();
        System.out.println("Результат проверки: " + methods.evenNumber(c));
        System.out.print("Введите число, которе хотите возвести в квадрат и в куб: ");
        int d = scanner.nextInt();
        System.out.println(d + "^2=" + methods.squareOfNumber(d) + " " + d + "^3=" + methods.cubeOfNumber(d));
//        System.out.print("Введите имя: ");
//        String name = scanner.next();
        Person person = new Person("Alexei");
        Person person1 = new Person("Alexei");

        System.out.println(person.first_name());
        System.out.println(person1.first_name());
        System.out.println(person.equals(person1));

        Student student = new Student();
        Student student1 = new Student();

        System.out.println(student.equals(student1));
        System.out.println(student.gender());
        System.out.println(student.first_name());
        System.out.println(student.goals());
    }
}
