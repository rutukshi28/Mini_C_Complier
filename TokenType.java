public enum TokenType {

    // Keywords
    INT,
    PRINT,
    IF,
    ELSE,
    WHILE,

    // Identifiers and numbers
    IDENTIFIER,
    NUMBER,

    // Arithmetic
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,

    // Assignment
    ASSIGN,

    // Comparison
    GREATER,
    LESS,
    GREATER_EQUAL,
    LESS_EQUAL,
    EQUAL_EQUAL,
    NOT_EQUAL,

    // Symbols
    SEMICOLON,
    LEFT_PAREN,
    RIGHT_PAREN,
    LEFT_BRACE,
    RIGHT_BRACE,

    EOF
}