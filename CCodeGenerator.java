import java.util.*;

public class CCodeGenerator {

    public String generate(
            List<Statement> statements) {

        StringBuilder code =
                new StringBuilder();

        code.append(
                "#include <stdio.h>\n\n"
        );

        code.append("int main() {\n");

        for (Statement statement : statements) {

            generateStatement(
                    statement,
                    code,
                    1
            );
        }

        code.append(
                "    return 0;\n"
        );

        code.append("}\n");

        return code.toString();
    }

    private void generateStatement(
            Statement statement,
            StringBuilder code,
            int indentLevel) {

        String indent = getIndent(
                indentLevel
        );

        if (statement
                instanceof VariableDeclaration) {

            VariableDeclaration declaration =
                    (VariableDeclaration) statement;

            code.append(indent);
            code.append("int ");
            code.append(declaration.name);
            code.append(" = ");
            code.append(
                    generateExpression(
                            declaration.value
                    )
            );
            code.append(";\n");
        }

        else if (statement
                instanceof Assignment) {

            Assignment assignment =
                    (Assignment) statement;

            code.append(indent);
            code.append(assignment.name);
            code.append(" = ");
            code.append(
                    generateExpression(
                            assignment.value
                    )
            );
            code.append(";\n");
        }

        else if (statement
                instanceof PrintStatement) {

            PrintStatement print =
                    (PrintStatement) statement;

            code.append(indent);
            code.append(
                    "printf(\"%d\\n\", "
            );

            code.append(
                    generateExpression(
                            print.expression
                    )
            );

            code.append(");\n");
        }

        else if (statement
                instanceof IfStatement) {

            IfStatement ifStatement =
                    (IfStatement) statement;

            code.append(indent);
            code.append("if (");

            code.append(
                    generateExpression(
                            ifStatement.condition
                    )
            );

            code.append(") {\n");

            for (Statement body :
                    ifStatement.ifBody) {

                generateStatement(
                        body,
                        code,
                        indentLevel + 1
                );
            }

            code.append(indent);
            code.append("}");

            if (ifStatement.elseBody != null) {

                code.append(" else {\n");

                for (Statement body :
                        ifStatement.elseBody) {

                    generateStatement(
                            body,
                            code,
                            indentLevel + 1
                    );
                }

                code.append(indent);
                code.append("}");
            }

            code.append("\n");
        }

        else if (statement
                instanceof WhileStatement) {

            WhileStatement whileStatement =
                    (WhileStatement) statement;

            code.append(indent);
            code.append("while (");

            code.append(
                    generateExpression(
                            whileStatement.condition
                    )
            );

            code.append(") {\n");

            for (Statement body :
                    whileStatement.body) {

                generateStatement(
                        body,
                        code,
                        indentLevel + 1
                );
            }

            code.append(indent);
            code.append("}\n");
        }
    }

    private String generateExpression(
            Expressions expression) {

        if (expression
                instanceof NumberExpression) {

            NumberExpression number =
                    (NumberExpression) expression;

            return String.valueOf(
                    number.value
            );
        }

        if (expression
                instanceof VariableExpression) {

            VariableExpression variable =
                    (VariableExpression) expression;

            return variable.name;
        }

        if (expression
                instanceof BinaryExpression) {

            BinaryExpression binary =
                    (BinaryExpression) expression;

            String left =
                    generateExpression(
                            binary.left
                    );

            String right =
                    generateExpression(
                            binary.right
                    );

            String operator =
                    getOperator(
                            binary.operator
                    );

            return "("
                    + left
                    + " "
                    + operator
                    + " "
                    + right
                    + ")";
        }

        throw new RuntimeException(
                "Unknown expression"
        );
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

                throw new RuntimeException(
                        "Unsupported operator: "
                                + operator
                );
        }
    }

    private String getIndent(
            int indentLevel) {

        StringBuilder indent =
                new StringBuilder();

        for (int i = 0;
             i < indentLevel;
             i++) {

            indent.append("    ");
        }

        return indent.toString();
    }
}