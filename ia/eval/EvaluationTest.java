package eval;

import node.Node;
import syntax.Parser;
import syntax.SyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Test the evaluation of a program
 */
class EvaluationTest {

    private Parser parser;
    private Environment env;


    @BeforeEach
    void setUp() {

        parser = new Parser();
        env = new Environment();
    }

    @Test
    void testAssignment() throws SyntaxException, EvalException{
        String prg = "x = 1; wr x";
        System.out.println(formatString(parser.parse(prg).toString()));
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testAssignment2() throws SyntaxException, EvalException{
        String prg = "x = 1; y = 2; wr x + y";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testAssignment3() throws SyntaxException, EvalException{
        String prg = "x = 1; x = x + 400; y = x + 1";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(402, evaluation);
    }

    @Test
    void testAssignment4() throws SyntaxException, EvalException{
        String prg = "x = 1; y = x + 1; wr y";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testEvalError() throws SyntaxException {

        String prg = "wr x";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testEvalErrorWithMultVariables() throws SyntaxException {

        String prg = "wr x; wr y";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testAssociation() throws SyntaxException, EvalException{
        String prg = "wr 10 - 4 - 3";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testAssociation2() throws SyntaxException, EvalException{
        String prg = "wr 10 - 2 - 6";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testAssociation3() throws SyntaxException, EvalException{
        String prg = "wr 20 - 10 - 10";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    void testAssociation4() throws SyntaxException, EvalException{
        String prg = "wr 20 - 10 - 15";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(-5, evaluation);
    }

    @Test
    void testPrecedence() throws SyntaxException, EvalException{
        String prg = "wr 6 / (10 - 8)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testPrecedence2() throws SyntaxException, EvalException{
        String prg = "wr 5 / (5 * 5)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0.2, evaluation);
    }

    @Test
    void testPrecedence3() throws SyntaxException, EvalException{
        String prg = "wr 5 / (5 + 5)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0.5, evaluation);
    }

    @Test
    void testPrecedence4() throws SyntaxException, EvalException{
        String prg = "wr (5 * 5) / (5 + 5)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2.5, evaluation);
    }

    @Test
    public void testRd() throws SyntaxException, EvalException {

        String input = "4.1";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd x; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(4.1, evaluation);
    }

    @Test
    public void testRd2() throws SyntaxException, EvalException {

        String input = "1\n" + "2\n" + "3\n";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd x; wr x; rd x; wr x; rd x; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    public void testIfThen() throws SyntaxException, EvalException {

        String prg = "if 1 == 1 then wr 1 else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testIf() throws SyntaxException, EvalException {
        String prg = "x = 4; " +
                "if x == 4 then " +
                "wr 1 " +
                "else " +
                "wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testIf2() throws SyntaxException, EvalException {
        String prg = "x = 4; " +
                    "y = 5;" +
                "if x == y then " +
                "wr 1";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test 
    public void testIf3() throws SyntaxException, EvalException {
        String prg = "if 1 == 0 then wr 1 else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    public void testWhile() throws SyntaxException, EvalException {
        String prg = "x = 0; " +
                "while x < 5 do " +
                "x = x + 1; " +
                "wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }

    @Test
    public void testWhile2() throws SyntaxException, EvalException {
        String prg = "x = 0; " +
                "while x < 5 do " +
                "x = x + 1; " +
                "wr x; " +
                "if x == 5 then " +
                "wr 1";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testWhile3() throws SyntaxException, EvalException {
        String prg = "x = 0;" +
                "while x < 5 do " +
                "x = x + 1.5; " +
                "wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(6.0, evaluation);
    }

    @Test
    public void testBegin() throws SyntaxException, EvalException {
        String prg = "begin x = 1; y = 2; wr x + y end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    public void testBegin2() throws SyntaxException, EvalException {
        String prg = "begin x = 1; y = 2; z = 3; wr x + y + z end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(6, evaluation);
    }
    
    @Test
    public void testRdWhile() throws SyntaxException, EvalException {
        String input = "8\n" + "2\n" + "1\n";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "x = 4; " +
                     "while x <> 1 do " +
                     "begin " +
                        "rd x;" +
                        "wr x " +
                     "end;" + 
                     "wr x ";
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testGCD() throws SyntaxException, EvalException {
        String input = "25.0\n15.0";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd a; rd b; while a<>b do if a > b then a = a-b else b = b-a; wr a";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }

    @Test
    public void testFibonacci() throws SyntaxException, EvalException {
        String input = "5";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "rd n; if n <= 2 then wr 1 else begin a = 1; b = 1; i = 3; while i <= n do begin temp = a; a = a + b; b = temp; i = i +1 end; wr a end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }

    @Test
    public void testRelopEquals() throws SyntaxException, EvalException {
        String prg = "if 3 == 3 then wr 1 else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testRelopGreaterThan() throws SyntaxException, EvalException {
        String prg = "if 1 > 1 then wr 1 else wr 0";
        
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    public void testRelopGreaterThanEquals() throws SyntaxException, EvalException {
        String prg = "if 1 >= 1 then wr 1 else wr 0";
        
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testRelopLessThan() throws SyntaxException, EvalException {
        String prg = "if 1 < 1 then wr 1 else wr 0";
        
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    public void testRelopLessThanEquals() throws SyntaxException, EvalException {
        String prg = "if 1 <= 1 then wr 1 else wr 0";
        
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    public void testRelopNotEquals() throws SyntaxException, EvalException {
        String prg = "if 1 <> 1 then wr 1 else wr 0";
        
        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    /**
     * ---------------------------------
     * NEW EVALUATION TESTS FOR IA5
     * ---------------------------------
     */

    @Test
    void testDeclare() throws SyntaxException, EvalException {
        String prg = "var x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    void testDeclare2() throws SyntaxException, EvalException {
        String prg = "var x = 1; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testDeclare3() throws SyntaxException, EvalException {
        String input = "6";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var x; rd x; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(6, evaluation);
    }

    @Test
    void testDeclareMultiple() throws SyntaxException, EvalException {
        String prg = "var x = 1; var y = 2; wr x + y";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testDeclareMultiple2() throws SyntaxException, EvalException {
        String prg = "var x = 1; var y = 2; var z = 3; wr x + y + z";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(6, evaluation);
    }

    @Test
    void testDeclareException() throws SyntaxException {
        String prg = "x = 1";

        assertThrows(EvalException.class, () -> {
            parser.parse(prg).eval(env);
        });
    }

    @Test
    void testDeclareException2() throws SyntaxException {
        String prg = "x = 1; wr x";

        assertThrows(EvalException.class, () -> {
            parser.parse(prg).eval(env);
        });
    }

    @Test
    void testDeclareException3() throws SyntaxException {
        String prg = "var x = 1; var x = 2";

        assertThrows(EvalException.class, () -> {
            parser.parse(prg).eval(env);
        });
    }

    @Test
    void testDeclareException4() throws SyntaxException {
        String prg = "wr x";

        assertThrows(EvalException.class, () -> {
            parser.parse(prg).eval(env);
        });
    }

    @Test
    void testDeclareException5() throws SyntaxException {
        String prg = "var x = 1; begin var y = 2; var y = 3 end";

        assertThrows(EvalException.class, () -> {
            parser.parse(prg).eval(env);
        });
    }

    @Test
    void testStaticScope() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin var x = 2; wr x end; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testStaticScope2() throws SyntaxException, EvalException {
        String prg = "var x = 2; begin var x = 1; wr x end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testStaticScope3() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin var y = 2; wr x + y end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testStaticScope4() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin x = 2; wr x end; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testStaticScope5() throws SyntaxException, EvalException {
        String prg = "var x = 2; begin var x = 5; begin x = 9 end end; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation, prg);
    }

    @Test
    void testFibonacciScope() throws SyntaxException, EvalException {
        String input = "5";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var n; rd n; if n <= 2 then wr 1 else begin var a = 1; var b = 1; var i = 3; while i <= n do begin var temp = a; a = a + b; b = temp; i = i +1 end; wr a end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }

    @Test
    void testFibonacciScope2() throws SyntaxException, EvalException {
        String input = "6";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var n; var a; var b; var i; var temp; rd n; if n <= 2 then wr 1 else begin a = 1; b = 1; i = 3; while i <= n do begin temp = a; a = a + b; b = temp; i = i +1 end; wr a end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(8, evaluation);
    }

    @Test
    void testGCDScope() throws SyntaxException, EvalException {
        String input = "255.0\n534.0";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var a; var b; rd a; rd b; while a<>b do if a > b then a = a-b else b = b-a; wr a";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testGCDScope2() throws SyntaxException, EvalException {
        String input = "25.0\n15.0";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var a; var b; rd a; rd b; while a<>b do if a > b then a = a-b else b = b-a; wr a";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
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
