package syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * To assist you in creating correct parse trees.
 */
class ParserTest {

    /**
     * Test the parser with a simple identifier
     * 
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifier() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with two identifiers and an addop ("+")
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifierWithAddOp() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x + y";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with two identifiers and an addop ("-")
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifierWithAddOp_2() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x - y";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with two identifiers and a mulop ("*")
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifierWithMulop() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x * y";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with two identifiers and a mulop ("/")
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifierWithMulop_2() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x / y";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with an node.NodeFactExpr; i.e. an identifier with parentheses
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifierWithParentheses() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "(x)";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with an node.NodeFactExpr; i.e. an identifier with parentheses
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testIdentifierWithParentheses_2() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "(((x)))";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with an expr; i.e. a term addop expr
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testAddition() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x + 3";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with an expr; i.e. a term addop expr
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testAddition_2() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x + y + 3";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with an expr; i.e. a term addop expr
     * The expression contains parenthesis
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testAdditionWithParenthesis() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x + (y + 3)";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with order of operations with subtraction
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testSubtraction() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "10 - 4 - 3";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with order of operations with addition and multiplication
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testAddopAndMulop() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x + 3 * y";

        System.out.println(formatString(parser.parse(prg).toString()));
    }

    /**
     * Test the parser with order of operations with addition and addition with parenthesis
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testAddopAndMulop_2() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "(x + 2) / 4";

        System.out.println(formatString(parser.parse(prg).toString()));

    }

    /**
     * Test the parser with invalid Syntax
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testSyntax_1() {
        Parser parser = new Parser();
        String prg = "x * *";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    /**
     * Test the parser with invalid Syntax (missing operand)
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testSyntax_2() {
        Parser parser = new Parser();
        String prg = "x +";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    /**
     * Test the parser with invalid Syntax (missing parenthesis)
     * @throws SyntaxException if an invalid terminal is discovered
     */
    @Test
    void testSyntax_3() {
        Parser parser = new Parser();
        String prg = "((x + 3)";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    /**
     * Helper method that simply adds newlines and tabs to a string where there are ( and )
     * @param tree - Non formatted version of the tree
     * @return formatted version of the String
     */
    private String formatString(String tree) {
        StringBuilder sb = new StringBuilder();

        int indents = 0;
        int position = 0;
        int old = 0;

        while (position < tree.length()) {
            if (tree.charAt(position) == '(') {
                sb.append(tree, old, position + 1);
                old = position + 1;
                indents++;
                sb.append('\n');
                sb.append("\t".repeat(indents));
            }
            if (tree.charAt(position) == ')') {
                sb.append(tree, old, position);
                old = position + 1;
                sb.append('\n');
                indents--;
                sb.append("\t".repeat(indents));
                sb.append(" )\n");
                sb.append("\t".repeat(indents));
            }
            position++;
        }

        return sb.toString();
    }

}