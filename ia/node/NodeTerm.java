package node;

import eval.Environment;
import eval.EvalException;

/**
 * a node object that represents the grammar for term
 */
public class NodeTerm extends Node {

    protected NodeFact fact;
    protected NodeMulop mulop;
    protected NodeTerm term;

    /**
     * creates a node object for term
     * 
     * @param fact  a fact node
     * @param mulop a mulop node
     * @param term  a term node
     */
    public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
        this.fact = fact;
        this.mulop = mulop;
        this.term = term;
    }

    /**
     * creates a node object for term
     * 
     * @param fact a fact node
     */
    public NodeTerm(NodeFact fact) {
        this.fact = fact;
        this.mulop = null;
        this.term = null;
    }

    /**
     * appends a term node to the current term node
     * 
     * @param term a term node
     */
    public void append(NodeTerm term) {
        if (this.term == null) {
            this.mulop = term.mulop;
            this.term = term;
            term.mulop = null;
        } else {
            this.term.append(term);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (term != null) {
            return mulop.compute(term.eval(env), fact.eval(env));
        }

        return fact.eval(env);
    }
}
