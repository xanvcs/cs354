package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents a node statement subclass that is a begin-end statement
 */
public class NodeBeginEnd extends NodeStmt {

    protected NodeBlock block;

    /**
     * creates a new instance of node.NodeBeginEnd
     * 
     * @param block
     */
    public NodeBeginEnd(NodeBlock block) {
        this.block = block;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        env.createScope();
        double result =  block.eval(env);
        env.exitScope();

        return result;
    }
}
