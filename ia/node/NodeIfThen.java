package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents a node statement subclass that is an if-then statement
 */
public class NodeIfThen extends NodeStmt {
    
    protected NodeBoolExpr boolExpr;
    protected NodeStmt stmt;

    /**
     * creates a new instance of node.NodeIfThen
     * 
     * @param boolExpr
     * @param stmt
     */
    public NodeIfThen(NodeBoolExpr boolExpr, NodeStmt stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (boolExpr.eval(env)) {
            return stmt.eval(env);
        } else {
            return 0;
        }
    }
}
