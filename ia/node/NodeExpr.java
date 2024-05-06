package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents an expr nonterminal
 */
public class NodeExpr extends Node {

    protected NodeTerm term;
    protected NodeAddop addop;
    protected NodeExpr expr;

    /**
     * creates a new node.NodeExpr object with term, addop, and expr
     * 
     * @param term  - the term
     * @param addop - the addop
     * @param expr  - the expression
     */
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
        this.term = term;
        this.addop = addop;
        this.expr = expr;
    }

    /**
     * creates a new node.NodeExpr object with term
     * 
     * @param term - the term
     */
    public NodeExpr(NodeTerm term) {
        this.term = term;
        this.addop = null;
        this.expr = null;
    }

    /**
     * appends an expression to the current expression
     * 
     * @param expr
     */
    public void append(NodeExpr expr) {
        if (this.expr == null) {
            this.addop = expr.addop;
            this.expr = expr;
            expr.addop = null;
        } else {
            this.expr.append(expr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (expr != null) {
            return addop.compute(expr.eval(env), term.eval(env));
        }

        return term.eval(env);
    }
}
