package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * represents the num subclass of nodefact
 */
public class NodeFactNum extends NodeFact {

    protected Token num;

    /**
     * creates a new instance of node.NodeFactNum
     * 
     * @param position the position of the num
     * @param num      the token of the num to be stored
     */
    public NodeFactNum(int position, Token num) {
        this.position = position;
        this.num = num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        return Double.parseDouble(num.getLexeme());
    }
}
