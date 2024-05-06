package node;

import eval.EvalException;
import syntax.Token;

/**
 * a node object that represents the grammar for mulop
 */
public class NodeMulop extends Node {

    protected Token mulop;

    /**
     * creates a node object for mulop
     * 
     * @param position the position of the mulop
     * @param mulop    a token representing the mulop grammar ("*", "/")
     */
    public NodeMulop(int position, Token mulop) {

        this.position = position;
        this.mulop = mulop;
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
        String operator = mulop.getLexeme();

        if (operator.equals("*")) {
            return op1 * op2;
        }

        if (operator.equals("/")) {
            return op1 / op2;
        }

        throw new EvalException(position, "mulop" + operator + "failed to evaluate");
    }
}