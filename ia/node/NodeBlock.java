package node;

import eval.Environment;
import eval.EvalException;

/**
 * node.NodeBlock is a node class that represents block nonterminal
 */
public class NodeBlock extends Node {

    protected NodeStmt stmt;
    protected NodeBlock block;

    /**
     * creates a new instance of node.NodeBlock with nodestmt and nodeblock
     * 
     * @param stmt  - node statement object
     * @param block - node block object
     */
    public NodeBlock(NodeStmt stmt, NodeBlock block) {
        this.stmt = stmt;
        this.block = block;
    }

    /**
     * creates a new instance of node.NodeBlock with nodestmt
     * 
     * @param stmt - node statement object
     */
    public NodeBlock(NodeStmt stmt) {
        this.stmt = stmt;
        this.block = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (block != null) {
            stmt.eval(env);
            double blockResult = block.eval(env);
            return blockResult;
        } else {
            return stmt.eval(env);
        }
    }
}
