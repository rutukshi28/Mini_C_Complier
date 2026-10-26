import java.util.List;

public class Executor {

    private Environment environment = new Environment();

    public void execute(List<Statement> statements) {

        for (Statement statement : statements) {
            executeStatement(statement);
        }
    }

    private void executeStatement(Statement statement) {

    // Variable declaration
    if (statement instanceof VariableDeclaration) {

        VariableDeclaration declaration =
                (VariableDeclaration) statement;

        int value = evaluate(declaration.value);

        environment.set(declaration.name, value);

        System.out.println(
                declaration.name + " = " + value
        );
    }

    // Assignment
    else if (statement instanceof Assignment) {

        Assignment assignment = (Assignment) statement;

        int value = evaluate(assignment.value);

        environment.set(assignment.name, value);

        System.out.println(
                assignment.name + " = " + value
        );
    }
}

    private int evaluate(Expressions expression) {

        // Number
        if (expression instanceof NumberExpression) {

            NumberExpression number =
                    (NumberExpression) expression;

            return number.value;
        }

        // Variable
        if (expression instanceof VariableExpression) {

            VariableExpression variable =
                    (VariableExpression) expression;

            return environment.get(variable.name);
        }

        // Binary Expression
        if (expression instanceof BinaryExpression) {

            BinaryExpression binary =
                    (BinaryExpression) expression;

            int left = evaluate(binary.left);
            int right = evaluate(binary.right);

            switch (binary.operator) {

                case PLUS:
                    return left + right;

                case MINUS:
                    return left - right;

                default:
                    throw new RuntimeException(
                        "Unsupported operator"
                    );
            }
        }

        throw new RuntimeException(
            "Unknown expression"
        );
    }
}