package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents a node statement subclass that is an if-then-else statement
 */
public class NodeIfThenElse extends NodeStmt {

    protected NodeBoolExpr boolExpr;
    protected NodeStmt stmt1;
    protected NodeStmt stmt2;

    /**
     * creates a new instance of node.NodeIfThenElse
     * 
     * @param boolExpr
     * @param stmt1
     * @param stmt2
     */
    public NodeIfThenElse(NodeBoolExpr boolExpr, NodeStmt stmt1, NodeStmt stmt2) {
        this.boolExpr = boolExpr;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (boolExpr.eval(env)) {
            return stmt1.eval(env);
        } else {
            return stmt2.eval(env);
        }
    }
}
