package node;

import eval.EvalException;
import syntax.Token;

/**
 * NodeRelop is a node class that represents relational operator nonterminal
 */
public class NodeRelop extends Node {

    protected Token relop;

    /**
     * creates a node object for relop
     * 
     * @param position the position of the relop
     * @param relop    a token representing the relop grammar ("<", "<=", ">", ">=", "==", "!=")
     */
    public NodeRelop(int position, Token relop) {

        this.position = position;
        this.relop = relop;
    }

    /**
     * checks the relop operation and performs it
     * 
     * @param op1 - first operand
     * @param op2 - second operand
     * @return the result of the operation
     * @throws EvalException - if the operator is not recognized
     */
    public boolean compute(double op1, double op2) throws EvalException {
        String operator = relop.getLexeme();

        switch(operator) {
            case "<":
                return op1 < op2;
            case "<=":
                return op1 <= op2;
            case ">":
                return op1 > op2;
            case ">=":
                return op1 >= op2;
            case "<>":
                return op1 != op2;
            case "==":
                return op1 == op2;
            default:
                throw new EvalException(position, "relop" + operator + "failed to evaluate");
        }
    }
}
