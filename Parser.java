import java.util.*;

public class Parser {

    private List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token currentToken() {
        return tokens.get(position);
    }

    private Token advance() {
        return tokens.get(position++);
    }

    public List<Statement> parse() {

        List<Statement> statements =
                new ArrayList<>();

        while (currentToken().type != TokenType.EOF) {
            statements.add(parseStatement());
        }

        return statements;
    }

    private Statement parseStatement() {

        if (currentToken().type == TokenType.INT) {
            return parseVariableDeclaration();
        }

        if (currentToken().type
                == TokenType.IDENTIFIER) {

            return parseAssignment();
        }

        if (currentToken().type
                == TokenType.PRINT) {

            return parsePrintStatement();
        }

        if (currentToken().type
                == TokenType.IF) {

            return parseIfStatement();
        }

        if (currentToken().type
                == TokenType.WHILE) {

            return parseWhileStatement();
        }

        throw new RuntimeException(
                "Unexpected token: "
                        + currentToken()
        );
    }

    private Statement parseVariableDeclaration() {

        advance();

        Token nameToken = advance();

        if (nameToken.type
                != TokenType.IDENTIFIER) {

            throw new RuntimeException(
                    "Expected variable name"
            );
        }

        String name = nameToken.value;

        Token assignToken = advance();

        if (assignToken.type
                != TokenType.ASSIGN) {

            throw new RuntimeException(
                    "Expected '='"
            );
        }

        Expressions value = parseExpression();

        expect(TokenType.SEMICOLON,
                "Expected ';'");

        return new VariableDeclaration(
                name,
                value
        );
    }

    private Statement parseAssignment() {

        Token nameToken = advance();

        String name = nameToken.value;

        expect(TokenType.ASSIGN,
                "Expected '='");

        Expressions value = parseExpression();

        expect(TokenType.SEMICOLON,
                "Expected ';'");

        return new Assignment(name, value);
    }

    private Statement parsePrintStatement() {

        advance();

        expect(TokenType.LEFT_PAREN,
                "Expected '(' after print");

        Expressions expression =
                parseExpression();

        expect(TokenType.RIGHT_PAREN,
                "Expected ')'");

        expect(TokenType.SEMICOLON,
                "Expected ';'");

        return new PrintStatement(expression);
    }

    private Statement parseIfStatement() {

        advance();

        expect(TokenType.LEFT_PAREN,
                "Expected '(' after if");

        Expressions condition =
                parseCondition();

        expect(TokenType.RIGHT_PAREN,
                "Expected ')' after condition");

        List<Statement> ifBody =
                parseBlock();

        List<Statement> elseBody = null;

        if (currentToken().type
                == TokenType.ELSE) {

            advance();

            elseBody = parseBlock();
        }

        return new IfStatement(
                condition,
                ifBody,
                elseBody
        );
    }

    private Statement parseWhileStatement() {

        advance();

        expect(TokenType.LEFT_PAREN,
                "Expected '(' after while");

        Expressions condition =
                parseCondition();

        expect(TokenType.RIGHT_PAREN,
                "Expected ')' after condition");

        List<Statement> body =
                parseBlock();

        return new WhileStatement(
                condition,
                body
        );
    }

    private List<Statement> parseBlock() {

        expect(TokenType.LEFT_BRACE,
                "Expected '{'");

        List<Statement> body =
                new ArrayList<>();

        while (currentToken().type
                != TokenType.RIGHT_BRACE) {

            if (currentToken().type
                    == TokenType.EOF) {

                throw new RuntimeException(
                        "Expected '}' before end of file"
                );
            }

            body.add(parseStatement());
        }

        advance();

        return body;
    }

    private Expressions parseCondition() {

        Expressions left =
                parseExpression();

        Token operator = currentToken();

        if (operator.type != TokenType.GREATER
                && operator.type != TokenType.LESS
                && operator.type != TokenType.GREATER_EQUAL
                && operator.type != TokenType.LESS_EQUAL
                && operator.type != TokenType.EQUAL_EQUAL
                && operator.type != TokenType.NOT_EQUAL) {

            throw new RuntimeException(
                    "Expected comparison operator, found: "
                            + operator
            );
        }

        advance();

        Expressions right =
                parseExpression();

        return new BinaryExpression(
                left,
                operator.type,
                right
        );
    }

    private Expressions parseExpression() {

        Expressions left = parseTerm();

        while (currentToken().type
                == TokenType.PLUS
                || currentToken().type
                == TokenType.MINUS) {

            Token operator = advance();

            Expressions right =
                    parseTerm();

            left = new BinaryExpression(
                    left,
                    operator.type,
                    right
            );
        }

        return left;
    }

    private Expressions parseTerm() {

        Expressions left = parsePrimary();

        while (currentToken().type
                == TokenType.MULTIPLY
                || currentToken().type
                == TokenType.DIVIDE) {

            Token operator = advance();

            Expressions right =
                    parsePrimary();

            left = new BinaryExpression(
                    left,
                    operator.type,
                    right
            );
        }

        return left;
    }

    private Expressions parsePrimary() {

        Token token = advance();

        if (token.type == TokenType.NUMBER) {

            return new NumberExpression(
                    Integer.parseInt(
                            token.value
                    )
            );
        }

        if (token.type
                == TokenType.IDENTIFIER) {

            return new VariableExpression(
                    token.value
            );
        }

        if (token.type
                == TokenType.LEFT_PAREN) {

            Expressions expression =
                    parseExpression();

            expect(TokenType.RIGHT_PAREN,
                    "Expected ')'");

            return expression;
        }

        throw new RuntimeException(
                "Unexpected token in expression: "
                        + token
        );
    }

    private void expect(
            TokenType expected,
            String message) {

        if (currentToken().type != expected) {

            throw new RuntimeException(
                    message
                            + ". Found: "
                            + currentToken()
            );
        }

        advance();
    }
}