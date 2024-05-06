package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * represents the id subclass of nodefact
 */
public class NodeFactId extends NodeFact {

    protected Token id;

    /**
     * creates a new instance of node.NodeFactId
     * 
     * @param position the position of the id
     * @param id       the token of the id to be stored
     */
    public NodeFactId(int position, Token id) {
        this.position = position;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return env.get(position, id.getLexeme());
    }
}
