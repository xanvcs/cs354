package node;

import eval.Environment;
import eval.EvalException;

/**
 * represents a nodefact subclass that is a "-"
 */
public class NodeFactDash extends NodeFact {

    protected NodeFact fact;

    /**
     * creates a new instance of node.NodeFactDash
     * 
     * @param fact the node fact non terminal
     */
    public NodeFactDash(NodeFact fact) {
        this.fact = fact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return fact.eval(env) * (-1);
    }
}
