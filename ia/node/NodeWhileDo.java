package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents a node statement subclass that is a while-do statement
 */
public class NodeWhileDo extends NodeStmt {

    protected NodeBoolExpr boolExpr;
    protected NodeStmt stmt;
    
    /**
     * creates a new instance of node.NodeWhileDo
     * 
     * @param boolExpr
     * @param stmt
     */
    public NodeWhileDo(NodeBoolExpr boolExpr, NodeStmt stmt) {
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        while (boolExpr.eval(env)) {
            stmt.eval(env);
        }
        return 0;
    }
}
