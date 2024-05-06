package eval;

import java.util.HashMap;
import java.util.Stack;

/**
 * A referencing environment for bindings
 */
public class Environment {

    private Stack<HashMap<String, Double>> stack;

    /**
     * initializes the map for identifiers
     */
    public Environment() {
        stack = new Stack<>();
        stack.push(new HashMap<>());
    }

    /**
     * enters a new scope
     */
    public void createScope() {
        stack.push(new HashMap<>());
    }

    /**
     * exits the current scope
     */
    public void exitScope() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    /**
     * declares a variable in the map
     * 
     * @param id - id of the variable
     * @return the value of the variable
     * @throws EvalException - if the variable is already in the stack
     */
    public void put(String id) throws EvalException {
        if (stack.peek().containsKey(id)) {
            System.out.println("Variable " + id + " is being redeclared");
            throw new EvalException(0, "var " + id + " has been declared");
        }
        stack.peek().put(id, 0.0);
    }

    /**
     * puts a value in the map only if id is in the map
     * 
     * @param var - id
     * @param val - value
     * @return the previous value of the variable
     * @throws EvalException - if the variable is not in the stack
     */
    public double put(String id, double val) throws EvalException {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).containsKey(id)) {
                return stack.get(i).put(id, val);
            }
        }

        throw new EvalException(0, "var " + id + " has not been declared");
    }

    /**
     * checks if the variable is in the stack
     * 
     * @param pos - position of the variable
     * @param var - variable
     * @return the value of the variable
     * @throws EvalException - if the variable is not in the stack
     */
    public double get(int pos, String var) throws EvalException {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).containsKey(var)) {
                return stack.get(i).get(var);
            }
        }

        throw new EvalException(pos, "var " + var + " has not been declared");
    }
}