import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Map<String, Integer> variables = new HashMap<>();

    public void set(String name, int value) {
        variables.put(name, value);
    }

    public int get(String name) {

        if (!variables.containsKey(name)) {
            throw new RuntimeException(
                "Variable '" + name + "' is not declared"
            );
        }

        return variables.get(name);
    }

    public void printVariables() {
        System.out.println(variables);
    }
}