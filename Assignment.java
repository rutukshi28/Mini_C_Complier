public class Assignment implements Statement {

    String name;
    Expressions value;

    public Assignment(String name, Expressions value) {
        this.name = name;
        this.value = value;
    }
}