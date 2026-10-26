public class VariableDeclaration implements Statement {
  
  String name;
  Expressions value;

  public VariableDeclaration(String name, Expressions value) {
    this.name = name;
    this.value = value;
  }
  
}
