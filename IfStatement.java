import java.util.List;

public class IfStatement implements Statement {

    Expressions condition;

    List<Statement> ifBody;

    List<Statement> elseBody;

    public IfStatement(
            Expressions condition,
            List<Statement> ifBody,
            List<Statement> elseBody) {

        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }
}