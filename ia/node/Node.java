package node;

import eval.Environment;
import eval.EvalException;

import java.lang.reflect.Field;

/**
 * an abstract class for nodes in the grammar
 */
public abstract class Node {

    protected int position = 0;

    /**
     * an abstract method to evaluate the node
     * 
     * @param env - the environment to evaluate the node in
     * @return - the result of the evaluation
     * @throws EvalException - if the node cannot be evaluated
     */
    public double eval(Environment env) throws EvalException {
        throw new EvalException(position, "cannot eval() node!");
    }

    /**
     * From http://www.javapractices.com/topic/TopicAction.do?Id=55
     * Using reflection to recursively print each member of each subclass in the tree.
     * @return a string of parenthesised tree nodes.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.getClass().getName());
        result.append(" (");

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append(" ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        result.append(" )");

        return result.toString();
    }
}
