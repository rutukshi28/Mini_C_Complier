public class BinaryExpression implements Expressions {
  
  Expressions left;
  TokenType operator;
  Expressions right;

  public BinaryExpression(
    Expressions left,
    TokenType operator,
    Expressions right
  ) {

    this.left = left;
    this.operator = operator;
    this.right = right;
  }
}
