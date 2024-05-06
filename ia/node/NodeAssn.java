package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;
/**
 * represents an assignment node that is a subclass of nodestmt
 */
public class NodeAssn extends NodeStmt {

    protected Token id;
    protected NodeExpr expr;

    /**
     * creates a new instance of node.NodeAssn
     * 
     * @param id   - the token of the id
     * @param expr - the expression to be stored
     */
    public NodeAssn(Token id, NodeExpr expr) {
        this.id = id;
        this.expr = expr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return env.put(id.getLexeme(), expr.eval(env));
    }
}