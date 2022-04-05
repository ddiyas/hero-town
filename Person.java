abstract public class Person {
    private String name;
    private String job;

    public Person(String n, String j) {
        name = n;
        job = j;
    }

    //getter method start
    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
    //getter method end
}
