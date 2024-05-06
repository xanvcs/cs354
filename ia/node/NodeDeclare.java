package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * represents the declare node that is a subclass of nodestmt
 */
public class NodeDeclare extends NodeStmt {
    
    protected Token id;
    protected NodeExpr expr;

    /**
     * creates a new instance of node.NodeDeclare
     * 
     * @param id
     */
    public NodeDeclare(Token id) {
        this.id = id;
        this.expr = null;
    }
    
    /**
     * creates a new instance of node.NodeDeclare
     * 
     * @param id
     * @param expr
     */
    public NodeDeclare(Token id, NodeExpr expr) {
        this.id = id;
        this.expr = expr;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        env.put(id.getLexeme());
        if (expr != null) {
            return env.put(id.getLexeme(), expr.eval(env));
        }
        
        return 0.0;
    }
}
