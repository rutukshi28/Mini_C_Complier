import java.util.*;

public class SemanticAnalyzer {

    private Set<String> symbolTable =
            new HashSet<>();

    public void analyze(
            List<Statement> statements) {

        for (Statement statement : statements) {
            analyzeStatement(statement);
        }
    }

    private void analyzeStatement(
            Statement statement) {

        if (statement
                instanceof VariableDeclaration) {

            VariableDeclaration declaration =
                    (VariableDeclaration) statement;

            if (symbolTable.contains(
                    declaration.name)) {

                throw new RuntimeException(
                        "Semantic Error: Variable '"
                                + declaration.name
                                + "' is already declared"
                );
            }

            analyzeExpression(
                    declaration.value
            );

            symbolTable.add(
                    declaration.name
            );
        }

        else if (statement
                instanceof Assignment) {

            Assignment assignment =
                    (Assignment) statement;

            checkVariable(
                    assignment.name
            );

            analyzeExpression(
                    assignment.value
            );
        }

        else if (statement
                instanceof PrintStatement) {

            PrintStatement print =
                    (PrintStatement) statement;

            analyzeExpression(
                    print.expression
            );
        }

        else if (statement
                instanceof IfStatement) {

            IfStatement ifStatement =
                    (IfStatement) statement;

            analyzeExpression(
                    ifStatement.condition
            );

            for (Statement body :
                    ifStatement.ifBody) {

                analyzeStatement(body);
            }

            if (ifStatement.elseBody != null) {

                for (Statement body :
                        ifStatement.elseBody) {

                    analyzeStatement(body);
                }
            }
        }

        else if (statement
                instanceof WhileStatement) {

            WhileStatement whileStatement =
                    (WhileStatement) statement;

            analyzeExpression(
                    whileStatement.condition
            );

            for (Statement body :
                    whileStatement.body) {

                analyzeStatement(body);
            }
        }
    }

    private void analyzeExpression(
            Expressions expression) {

        if (expression
                instanceof VariableExpression) {

            VariableExpression variable =
                    (VariableExpression) expression;

            checkVariable(variable.name);
        }

        else if (expression
                instanceof BinaryExpression) {

            BinaryExpression binary =
                    (BinaryExpression) expression;

            analyzeExpression(binary.left);

            analyzeExpression(binary.right);
        }
    }

    private void checkVariable(String name) {

        if (!symbolTable.contains(name)) {

            throw new RuntimeException(
                    "Semantic Error: Variable '"
                            + name
                            + "' is not declared"
            );
        }
    }
}