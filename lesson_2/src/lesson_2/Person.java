package lesson_2;

public class Person implements Community {
    private String first_name;

    public Person() {
    }

    public Person(String first_name) {
        this.first_name = first_name;
    }

    public String first_name() {
        return "____" + first_name + "____";
    }
    @Override
    public boolean equals(Object obj) {
        return obj != null && this.first_name.equals(((Person) obj).first_name);
    }
}