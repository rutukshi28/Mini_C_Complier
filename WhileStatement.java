import java.util.List;

public class WhileStatement implements Statement {

    Expressions condition;

    List<Statement> body;

    public WhileStatement(
            Expressions condition,
            List<Statement> body) {

        this.condition = condition;
        this.body = body;
    }
}