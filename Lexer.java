import java.util.*;

public class Lexer {

    private String source;
    private int position = 0;

    public Lexer(String source) {
        this.source = source;
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        while (position < source.length()) {

            char c = source.charAt(position);

            // Ignore spaces
            if (Character.isWhitespace(c)) {
                position++;
                continue;
            }

            // Single line comments //
            if (c == '/'
                    && position + 1 < source.length()
                    && source.charAt(position + 1) == '/') {

                position += 2;

                while (position < source.length()
                        && source.charAt(position) != '\n') {

                    position++;
                }

                continue;
            }

            // Numbers
            if (Character.isDigit(c)) {

                StringBuilder value =
                        new StringBuilder();

                while (position < source.length()
                        && Character.isDigit(
                                source.charAt(position))) {

                    value.append(
                            source.charAt(position)
                    );

                    position++;
                }

                tokens.add(
                        new Token(
                                TokenType.NUMBER,
                                value.toString()
                        )
                );

                continue;
            }

            // Identifiers and keywords
            if (Character.isLetter(c) || c == '_') {

                StringBuilder value =
                        new StringBuilder();

                while (position < source.length()
                        && (Character.isLetterOrDigit(
                                source.charAt(position))
                        || source.charAt(position) == '_')) {

                    value.append(
                            source.charAt(position)
                    );

                    position++;
                }

                String word = value.toString();

                if (word.equals("int")) {
                    tokens.add(
                            new Token(TokenType.INT, word)
                    );
                }

                else if (word.equals("print")) {
                    tokens.add(
                            new Token(TokenType.PRINT, word)
                    );
                }

                else if (word.equals("if")) {
                    tokens.add(
                            new Token(TokenType.IF, word)
                    );
                }

                else if (word.equals("else")) {
                    tokens.add(
                            new Token(TokenType.ELSE, word)
                    );
                }

                else if (word.equals("while")) {
                    tokens.add(
                            new Token(TokenType.WHILE, word)
                    );
                }

                else {
                    tokens.add(
                            new Token(
                                    TokenType.IDENTIFIER,
                                    word
                            )
                    );
                }

                continue;
            }

            if (c == '+') {
                tokens.add(
                        new Token(TokenType.PLUS, "+")
                );
                position++;
                continue;
            }

            if (c == '-') {
                tokens.add(
                        new Token(TokenType.MINUS, "-")
                );
                position++;
                continue;
            }

            if (c == '*') {
                tokens.add(
                        new Token(TokenType.MULTIPLY, "*")
                );
                position++;
                continue;
            }

            // Division
            if (c == '/') {
                tokens.add(
                        new Token(TokenType.DIVIDE, "/")
                );
                position++;
                continue;
            }

            // = or ==
            if (c == '=') {

                if (position + 1 < source.length()
                        && source.charAt(position + 1)
                        == '=') {

                    tokens.add(
                            new Token(
                                    TokenType.EQUAL_EQUAL,
                                    "=="
                            )
                    );

                    position += 2;

                } else {

                    tokens.add(
                            new Token(
                                    TokenType.ASSIGN,
                                    "="
                            )
                    );

                    position++;
                }

                continue;
            }

            // > or >=
            if (c == '>') {

                if (position + 1 < source.length()
                        && source.charAt(position + 1)
                        == '=') {

                    tokens.add(
                            new Token(
                                    TokenType.GREATER_EQUAL,
                                    ">="
                            )
                    );

                    position += 2;

                } else {

                    tokens.add(
                            new Token(
                                    TokenType.GREATER,
                                    ">"
                            )
                    );

                    position++;
                }

                continue;
            }

            // < or <=
            if (c == '<') {

                if (position + 1 < source.length()
                        && source.charAt(position + 1)
                        == '=') {

                    tokens.add(
                            new Token(
                                    TokenType.LESS_EQUAL,
                                    "<="
                            )
                    );

                    position += 2;

                } else {

                    tokens.add(
                            new Token(
                                    TokenType.LESS,
                                    "<"
                            )
                    );

                    position++;
                }

                continue;
            }

            // !=
            if (c == '!') {

                if (position + 1 < source.length()
                        && source.charAt(position + 1)
                        == '=') {

                    tokens.add(
                            new Token(
                                    TokenType.NOT_EQUAL,
                                    "!="
                            )
                    );

                    position += 2;
                    continue;
                }

                throw new RuntimeException(
                        "Unexpected character: !"
                );
            }

            if (c == ';') {
                tokens.add(
                        new Token(
                                TokenType.SEMICOLON,
                                ";"
                        )
                );
                position++;
                continue;
            }

            if (c == '(') {
                tokens.add(
                        new Token(
                                TokenType.LEFT_PAREN,
                                "("
                        )
                );
                position++;
                continue;
            }

            if (c == ')') {
                tokens.add(
                        new Token(
                                TokenType.RIGHT_PAREN,
                                ")"
                        )
                );
                position++;
                continue;
            }

            if (c == '{') {
                tokens.add(
                        new Token(
                                TokenType.LEFT_BRACE,
                                "{"
                        )
                );
                position++;
                continue;
            }

            if (c == '}') {
                tokens.add(
                        new Token(
                                TokenType.RIGHT_BRACE,
                                "}"
                        )
                );
                position++;
                continue;
            }

            throw new RuntimeException(
                    "Unexpected character: " + c
            );
        }

        tokens.add(
                new Token(TokenType.EOF, "")
        );

        return tokens;
    }
}