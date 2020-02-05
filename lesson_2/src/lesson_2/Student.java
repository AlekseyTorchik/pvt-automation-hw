package lesson_2;

public class Student extends Person {
    private String first_name = "Alexei";

    public String first_name() {
        return "override method in Student";
    }

    private String lastName() {
        return first_name + " last name";
    }

    protected String gender() {
        return first_name + " male";
    }

    String goals() {
        return first_name + " goals";
    }
    @Override
    public boolean equals(Object obj) {
        return obj != null && this.first_name.equals(((Student) obj).first_name);
    }
}
