package node;

import eval.EvalException;
import syntax.Token;

/**
 * represents an addop nonterminal
 */
public class NodeAddop extends Node {

    protected Token addop;

    /**
     * creates a new node.NodeAddop object
     * 
     * @param position - position of the addop
     * @param addop    - the addop token
     */
    public NodeAddop(int position, Token addop) {
        this.position = position;
        this.addop = addop;
    }

    /**
     * checks the addop operation and performs it
     * 
     * @param op1 - first operand
     * @param op2 - second operand
     * @return the result of the operation
     * @throws EvalException - if the operator is not recognized
     */
    public double compute(double op1, double op2) throws EvalException {
        String operator = addop.getLexeme();

        if (operator.equals("+")) {
            return op1 + op2;
        }

        if (operator.equals("-")) {
            return op1 - op2;
        }

        throw new EvalException(position, "eval of" + operator + "failed to evaluate");
    }
}
