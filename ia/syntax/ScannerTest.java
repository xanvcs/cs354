package syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for syntax.Scanner
 *
 * Uses Junit5.
 */
public class ScannerTest {

    /**
     * Simply creates a 'program' that has only one token. When scanned,
     * the test checks to see that the current token is of the correct type.
     *
     * Try more than one syntax.Token in a different test case!
     * 
     * @throws SyntaxException - This suppresses the need for a try/catch block.
     */
    @Test
    public void test() throws SyntaxException {

        String prg = "4";
        Scanner scanner = new Scanner(prg);
        assertTrue(scanner.next());
        assertEquals(new Token("num", "4"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an identifier
     *
     * @throws SyntaxException
     */
    @Test
    public void testOneIdentifier() throws SyntaxException {

        String prg = "x";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an identifier with multiple numbers
     * 
     * @throws SyntaxException
     */
    @Test
    public void testIdentifier2() throws SyntaxException {

        String prg = "x1x1";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x1x1"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an invalid identifier
     * 
     * @throws SyntaxException
     */
    @Test
    public void testInvalidIdentifier() throws SyntaxException {

        String prg = "1x";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an invalid identifier and number
     * 
     * @throws SyntaxException
     */
    @Test
    public void testInvalidIdentifier2() throws SyntaxException {

        String prg = "3x2";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertNotEquals(new Token("id", "3x2"), scanner.getCurrent());
        assertEquals(new Token("num", "3"), scanner.getCurrent());
    }

    /**
     * Tests that the scanner can recognize an invalid identifier with an illegal
     * character
     * 
     * @throws SyntaxException
     */
    @Test
    public void testInvalidIdentifierWithIllegalChar() throws SyntaxException {

        String prg = "1x2!";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x2"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize a number
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneNumber() throws SyntaxException {

        String prg = "123";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("num", "123"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize a number with a period
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneNumberWithPeriod() throws SyntaxException {

        String prg = "1.";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1."), scanner.getCurrent());

        assertFalse(scanner.next());

        String prg2 = ".1";
        Scanner scanner2 = new Scanner(prg2);

        assertTrue(scanner2.next());
        assertEquals(new Token("num", ".1"), scanner2.getCurrent());

        assertFalse(scanner2.next());
    }

    /**
     * Tests that the scanner can recognize two numbers
     * 
     * @throws SyntaxException
     */
    @Test
    public void testMultipleNumbersWithPeriod() throws SyntaxException {

        String prg = "2.0.3";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("num", "2.0"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", ".3"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize a multiple floating point numbers
     * 
     * @throws SyntaxException
     */
    @Test
    public void testMultipleNumbersWithPeriod2() throws SyntaxException {

        String prg = "2.0.3.0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("num", "2.0"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", ".3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", ".0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the semicolon)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator() throws SyntaxException {

        String prg = ";";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the equals sign)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator2() throws SyntaxException {

        String prg = "=";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the plus sign)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator3() throws SyntaxException {

        String prg = "+";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("+", "+"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the minus sign)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator4() throws SyntaxException {

        String prg = "-";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("-", "-"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the multiplication sign)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator5() throws SyntaxException {

        String prg = "*";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("*", "*"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the division sign)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator6() throws SyntaxException {

        String prg = "/";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("/", "/"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an operator (the left and right
     * parentheses)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator7() throws SyntaxException {

        String prg = "()";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("(", "("), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(")", ")"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize more than one token in a program
     * 
     * @throws SyntaxException
     */
    @Test
    public void testMoreThanOneToken() throws SyntaxException {

        String prg = "x = 3;";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize a comment
     * 
     * @throws SyntaxException
     */
    @Test
    public void testMultilineComment() throws SyntaxException {

        String prg = "# this is a comment #" + "x = 3;";
        Scanner scanner = new Scanner(prg);

        scanner.next();

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize comments
     * 
     * @throws SyntaxException
     */
    @Test
    public void testMultilineComment2() throws SyntaxException {

        String prg = "# this is a comment # x = 3; # this is another comment #";
        Scanner scanner = new Scanner(prg);

        scanner.next();

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        scanner.next();

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an illegal character
     * 
     * @throws SyntaxException
     */
    @Test
    public void testIllegalCharacter() throws SyntaxException {

        String prg = "x = 3; $";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertTrue(scanner.next());
        assertTrue(scanner.next());
        assertTrue(scanner.next());

        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner can recognize an illegal character
     * 
     * @throws SyntaxException
     */
    @Test
    public void testIllegalCharacter2() throws SyntaxException {

        String prg = "%$@!";
        Scanner scanner = new Scanner(prg);

        assertFalse(scanner.next());
        assertFalse(scanner.next());
        assertFalse(scanner.next());
        assertFalse(scanner.next());
    }

    /**
     * Tests that the scanner will skip newlines
     * 
     * @throws SyntaxException
     */
    @Test
    public void testNewLines() throws SyntaxException {

        String prg = "x = 3;\n" + "y = 4;";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "y"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "4"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    @Test
    public void testRelopEquals() throws SyntaxException {

        String prg = "x = 3; if x == 3 then wr 1 else wr 0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("if", "if"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("==", "=="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("then", "then"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("else", "else"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    @Test
    public void testRelopNotEquals() throws SyntaxException {

        String prg = "x = 3; if x <> 3 then wr 1 else wr 0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("if", "if"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("<>", "<>"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("then", "then"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("else", "else"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    @Test
    public void testRelopLessThan() throws SyntaxException {

        String prg = "x = 3; if x < 3 then wr 1 else wr 0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("if", "if"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("<", "<"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("then", "then"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("else", "else"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    @Test
    public void testRelopLessThanEquals() throws SyntaxException {

        String prg = "x = 3; if x <= 3 then wr 1 else wr 0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("if", "if"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("<=", "<="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("then", "then"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("else", "else"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    @Test
    public void testRelopGreaterThan() throws SyntaxException {

        String prg = "x = 3; if x > 3 then wr 1 else wr 0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("if", "if"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(">", ">"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("then", "then"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("else", "else"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }

    @Test
    public void testRelopGreaterThanEquals() throws SyntaxException {

        String prg = "x = 3; if x >= 3 then wr 1 else wr 0";
        Scanner scanner = new Scanner(prg);

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("=", "="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(";", ";"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("if", "if"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("id", "x"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token(">=", ">="), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "3"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("then", "then"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "1"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("else", "else"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("wr", "wr"), scanner.getCurrent());

        assertTrue(scanner.next());
        assertEquals(new Token("num", "0"), scanner.getCurrent());

        assertFalse(scanner.next());
    }
}
