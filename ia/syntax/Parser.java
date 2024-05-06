package syntax;

import node.*;

/**
 * A recursive descent parser
 */
public class Parser {

    private Scanner scanner;
    protected java.util.Scanner rdScanner;

    /**
     * Parse an input program and return a node.Node that is the root of the
     * resulting parse tree.
     *
     * @param program String to be scanned and parsed
     * @return the Root node.Node of a parse tree
     * @throws SyntaxException - If there is a syntax error
     */
    public Node parse(String program) throws SyntaxException {
        scanner = new Scanner(program);
        rdScanner = new java.util.Scanner(System.in);
        scanner.next(); // "prime the pump"
        NodeBlock block = parseBlock();
        match("EOF");
        return block;
    }

    /**
     * Parses a declare nonterminal and returns it
     * 
     * @return
     * @throws SyntaxException
     */
    private NodeDeclare parseDeclare() throws SyntaxException {
        match("var");
        Token id = current();
        match("id");

        NodeExpr expr = null;
        if (current().equalType(new Token("="))) {
            match("=");
            expr = parseExpr();

            return new NodeDeclare(id, expr);
        } else {
            return new NodeDeclare(id);
        }
    }

    /**
     * Parses a expr nonterminal and returns it
     * 
     * @return a node.Node that represents a expr
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeExpr parseExpr() throws SyntaxException {

        NodeTerm term = parseTerm();
        NodeAddop addop = parseAddop();

        if (addop == null) {
            return new NodeExpr(term);
        } else {
            NodeExpr expr = parseExpr();
            expr.append(new NodeExpr(term, addop, null));
            return expr;
        }
    }

    /**
     * Parses a term nonterminal and returns it
     * 
     * @return a node.Node that represents a term
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeTerm parseTerm() throws SyntaxException {
        NodeFact fact = parseFact();
        NodeMulop mulop = parseMulop();

        if (mulop == null) {
            return new NodeTerm(fact);
        } else {
            NodeTerm term = parseTerm();
            term.append(new NodeTerm(fact, mulop, null));
            return term;
        }
    }

    /**
     * Parses a fact nonterminal and returns it
     * 
     * @return a node.Node that represents a fact
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeFact parseFact() throws SyntaxException {
        Token current = scanner.getCurrent();

        if (current.equalType(new Token("id"))) {
            match("id");
            return new NodeFactId(scanner.getPosition(), current);

        } else if (current.equalType(new Token("num"))) {
            match("num");
            return new NodeFactNum(scanner.getPosition(), current);

        } else if (current.equalType(new Token("("))) {
            match("(");
            NodeExpr expr = parseExpr();
            match(")");
            return new NodeFactExpr(expr);

        } else if (current.equalType(new Token("-"))) {
            match("-");
            NodeFact fact = parseFact();
            return new NodeFactDash(fact);
        }

        throw new SyntaxException(scanner.getPosition(), new Token("fact"), current);
    }

    /**
     * Parses an addop nonterminal and returns it.
     * 
     * @return a node.Node that represent an addop
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeAddop parseAddop() throws SyntaxException {
        Token addop = scanner.getCurrent();

        if (addop.equalType(new Token("+"))) {
            match("+");
            return new NodeAddop(scanner.getPosition(), addop);

        } else if (addop.equalType(new Token("-"))) {
            match("-");
            return new NodeAddop(scanner.getPosition(), addop);

        } else {
            return null;
        }
    }

    /**
     * Parses a mulop nonterminal and returns it
     * 
     * @return a node.Node that represents a mulop
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeMulop parseMulop() throws SyntaxException {
        Token mulop = scanner.getCurrent();

        if (mulop.equalType(new Token("*"))) {
            match("*");
            return new NodeMulop(scanner.getPosition(), mulop);

        } else if (mulop.equalType(new Token("/"))) {
            match("/");
            return new NodeMulop(scanner.getPosition(), mulop);

        } else {
            return null;
        }
    }

    /**
     * Parses a relop nonterminal and returns it
     * 
     * @return a node.Node that represents a relop
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeRelop parseRelop() throws SyntaxException {
        Token relop = scanner.getCurrent();

        switch (relop.getToken()) {
            case "<":
                match("<");
                return new NodeRelop(scanner.getPosition(), relop);
            case "<=":
                match("<=");
                return new NodeRelop(scanner.getPosition(), relop);
            case ">":
                match(">");
                return new NodeRelop(scanner.getPosition(), relop);
            case ">=":
                match(">=");
                return new NodeRelop(scanner.getPosition(), relop);
            case "<>":
                match("<>");
                return new NodeRelop(scanner.getPosition(), relop);
            case "==":
                match("==");
                return new NodeRelop(scanner.getPosition(), relop);
            default:
                return null;
        }
    }

    /**
     * Parses a relop nonterminal and returns it
     * 
     * @return a node.NodeBoolExpr that represents a relop
     * @throws SyntaxException
     */
    private NodeBoolExpr parseBoolExpr() throws SyntaxException {
        NodeExpr expr1 = parseExpr();
        NodeRelop relop = parseRelop();
        NodeExpr expr2 = parseExpr();

        return new NodeBoolExpr(expr1, relop, expr2);
    }

    /**
     * Parses a stmt nonterminal and returns it
     * 
     * @return a node.Node that represents a stmt
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeStmt parseStmt() throws SyntaxException {

        Token current = scanner.getCurrent();

        switch (current.getToken()) {
            case "wr":
                match("wr");
                NodeExpr expr = parseExpr();
                return new NodeWr(expr);

            case "rd":
                match("rd");
                Token id = current();
                match("id");
                return new NodeRd(id, rdScanner);

            case "if":
                match("if");
                NodeBoolExpr boolExpr = parseBoolExpr();
                match("then");
                NodeStmt stmt = parseStmt();
                if (current().equalType(new Token("else"))) {
                    match("else");
                    NodeStmt stmt2 = parseStmt();
                    return new NodeIfThenElse(boolExpr, stmt, stmt2);
                }
                return new NodeIfThen(boolExpr, stmt);

            case "while":
                match("while");
                NodeBoolExpr boolExpr2 = parseBoolExpr();
                match("do");
                NodeStmt stmt2 = parseStmt();
                return new NodeWhileDo(boolExpr2, stmt2);

            case "begin":
                match("begin");
                NodeBlock block = parseBlock();
                match("end");
                return new NodeBeginEnd(block);

            case "id":
                return parseAssn();

            case "var":
                return parseDeclare();
            
            default:
                throw new SyntaxException(scanner.getPosition(), new Token("stmt"), current);
        }
    }

    /**
     * Parses an assn nonterminal and returns it
     * 
     * @return a node.NodeAssn that represents an assn
     * @throws SyntaxException
     */
    private NodeAssn parseAssn() throws SyntaxException {
        Token id = scanner.getCurrent();
        match("id");
        match("=");
        NodeExpr expr = parseExpr();
        return new NodeAssn(id, expr);
    }

    /**
     * Parses a block nonterminal and returns it
     * 
     * @return a node.Node that represents a block
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private NodeBlock parseBlock() throws SyntaxException {
        NodeStmt stmt = parseStmt();
        Token current = scanner.getCurrent();
        System.out.println(current);
        NodeBlock block = null;

        if (current.equalType(new Token(";"))) {
            match(";");
            block = parseBlock();
            System.out.println(block);
            return new NodeBlock(stmt, block);
        } else {
            return new NodeBlock(stmt);
        }
    }

    /**
     * Created to simplify the code when token is more than length of one
     * and for the removal of having to use scanner.getCurrent() multiple times
     */
    private Token current() throws SyntaxException {
        return scanner.getCurrent();
    }

    /**
     * Utilizes the scanner's match function to check if the current token
     * is the expected token
     * 
     * @param s the string that is being matched
     * @throws SyntaxException if an invalid string is provided
     */
    private void match(String s) throws SyntaxException {
        scanner.match(new Token(s));
    }
}
