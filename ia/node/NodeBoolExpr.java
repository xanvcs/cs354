package node;

import eval.Environment;
import eval.EvalException;

/**
 * node.NodeBoolExpr is a node class that represents boolean expression nonterminal
 */
public class NodeBoolExpr {

    protected NodeExpr expr1;
    protected NodeRelop relop;
    protected NodeExpr expr2;

    /**
     * creates a new instance of a boolean expression
     * 
     * @param expr1
     * @param relop
     * @param expr2
     */
    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2) {
        this.expr1 = expr1;
        this.relop = relop;
        this.expr2 = expr2;
    }

    /**
     * evaluates the boolean expression. Does not override eval() method
     * from node since it needs to return a boolean
     * 
     * @param env - the environment to evaluate the expression
     * @return the result of the boolean expression
     */
    public boolean eval(Environment env) throws EvalException {
        return relop.compute(expr1.eval(env), expr2.eval(env));
    }
}