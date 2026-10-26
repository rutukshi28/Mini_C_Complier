import java.util.List;

public class ASTPrinter {

    public void printProgram(List<Statement> statements) {

        System.out.println("\nSTEP 3: PARSE TREE / AST\n");

        System.out.println("Program");

        for (Statement statement : statements) {
            printStatement(statement, "  ");
        }
    }

    private void printStatement(
            Statement statement,
            String indent) {

        // Variable Declaration
        if (statement instanceof VariableDeclaration) {

            VariableDeclaration declaration =
                    (VariableDeclaration) statement;

            System.out.println(
                    indent + "VariableDeclaration"
            );

            System.out.println(
                    indent + "├── Name: "
                            + declaration.name
            );

            System.out.println(
                    indent + "└── Value:"
            );

            printExpression(
                    declaration.value,
                    indent + "    "
            );
        }

        // Assignment
        else if (statement instanceof Assignment) {

            Assignment assignment =
                    (Assignment) statement;

            System.out.println(
                    indent + "Assignment"
            );

            System.out.println(
                    indent + "├── Variable: "
                            + assignment.name
            );

            System.out.println(
                    indent + "└── Value:"
            );

            printExpression(
                    assignment.value,
                    indent + "    "
            );
        }

        // Print
        else if (statement instanceof PrintStatement) {

            PrintStatement printStatement =
                    (PrintStatement) statement;

            System.out.println(
                    indent + "PrintStatement"
            );

            printExpression(
                    printStatement.expression,
                    indent + "  "
            );
        }

        // If
        else if (statement instanceof IfStatement) {

            IfStatement ifStatement =
                    (IfStatement) statement;

            System.out.println(
                    indent + "IfStatement"
            );

            System.out.println(
                    indent + "├── Condition:"
            );

            printExpression(
                    ifStatement.condition,
                    indent + "│   "
            );

            System.out.println(
                    indent + "├── If Body:"
            );

            for (Statement body :
                    ifStatement.ifBody) {

                printStatement(
                        body,
                        indent + "│   "
                );
            }

            if (ifStatement.elseBody != null) {

                System.out.println(
                        indent + "└── Else Body:"
                );

                for (Statement body :
                        ifStatement.elseBody) {

                    printStatement(
                            body,
                            indent + "    "
                    );
                }
            }
        }

        // While
        else if (statement instanceof WhileStatement) {

            WhileStatement whileStatement =
                    (WhileStatement) statement;

            System.out.println(
                    indent + "WhileStatement"
            );

            System.out.println(
                    indent + "├── Condition:"
            );

            printExpression(
                    whileStatement.condition,
                    indent + "│   "
            );

            System.out.println(
                    indent + "└── Body:"
            );

            for (Statement body :
                    whileStatement.body) {

                printStatement(
                        body,
                        indent + "    "
                );
            }
        }
    }

    private void printExpression(
            Expressions expression,
            String indent) {

        // Number
        if (expression instanceof NumberExpression) {

            NumberExpression number =
                    (NumberExpression) expression;

            System.out.println(
                    indent + "Number: "
                            + number.value
            );
        }

        // Variable
        else if (expression
                instanceof VariableExpression) {

            VariableExpression variable =
                    (VariableExpression) expression;

            System.out.println(
                    indent + "Variable: "
                            + variable.name
            );
        }

        // Binary Expression
        else if (expression
                instanceof BinaryExpression) {

            BinaryExpression binary =
                    (BinaryExpression) expression;

            System.out.println(
                    indent + "BinaryExpression: "
                            + getOperator(
                                    binary.operator
                            )
            );

            System.out.println(
                    indent + "├── Left:"
            );

            printExpression(
                    binary.left,
                    indent + "│   "
            );

            System.out.println(
                    indent + "└── Right:"
            );

            printExpression(
                    binary.right,
                    indent + "    "
            );
        }
    }

    private String getOperator(
            TokenType operator) {

        switch (operator) {

            case PLUS:
                return "+";

            case MINUS:
                return "-";

            case MULTIPLY:
                return "*";

            case DIVIDE:
                return "/";

            case GREATER:
                return ">";

            case LESS:
                return "<";

            case GREATER_EQUAL:
                return ">=";

            case LESS_EQUAL:
                return "<=";

            case EQUAL_EQUAL:
                return "==";

            case NOT_EQUAL:
                return "!=";

            default:
                return operator.toString();
        }
    }
}