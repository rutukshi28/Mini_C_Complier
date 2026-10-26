public class PrintStatement implements Statement {

    Expressions expression;

    public PrintStatement(Expressions expression) {
        this.expression = expression;
    }
}