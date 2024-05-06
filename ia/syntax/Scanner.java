package syntax;

import java.util.*;

/**
 * syntax.Scanner for programming language
 */
public class Scanner {

    private String program; // source program being interpreted
    private int position; // index of next char in program
    private Token currentToken; // current (most recently scanned token)

    private Set<String> whitespace = new HashSet<>();
    private Set<String> letters = new HashSet<>();
    private Set<String> keywords = new HashSet<>();
    private Set<String> numbers = new HashSet<>();
    private Set<String> operators = new HashSet<>();
    private Set<String> comments = new HashSet<>();
    private Set<String> validID = new HashSet<>();

    /**
     * Creates a new scanner
     *
     * @param program - the program text to scan
     */
    public Scanner(String program) {
        this.program = program;
        position = 0;
        currentToken = null;

        initWhitespace(whitespace);
        initLetters(letters);
        initKeywords(keywords);
        initNumbers(numbers);
        initOperators(operators);
        initComments(comments);
        initIDStrings(validID);
    }

    /**
     * Initializes the set of whitespace characters
     * 
     * @param s - the set to initialize
     */
    private void initWhitespace(Set<String> s) {
        s.add(" ");
        s.add("\n");
        s.add("\t");
        s.add("\r");
    }

    /**
     * Initializes the set of letters with A-Z and a-z
     * 
     * @param s - the set to initialize
     */
    private void initLetters(Set<String> s) {
        fill(s, 'A', 'Z');
        fill(s, 'a', 'z');
    }

    /**
     * Initializes the set of keywords
     * 
     * @param keywords2 - the set to initialize
     */
    private void initKeywords(Set<String> keywords2) {
        keywords2.add("wr");
        keywords2.add("rd");
        keywords2.add("var");

        keywords2.add("if");
        keywords2.add("then");
        keywords2.add("else");
        keywords2.add("while");
        keywords2.add("do");
        keywords2.add("begin");
        keywords2.add("end");
    }

    /**
     * Initializes the set of numbers with 0-9 and "."
     * 
     * @param s - the set to initialize
     */
    private void initNumbers(Set<String> s) {
        fill(s, '0', '9');
    }

    /**
     * Initializes the set of operators
     * 
     * @param s - the set to initialize
     */
    private void initOperators(Set<String> s) {
        s.add("+");
        s.add("-");
        s.add("*");
        s.add("/");
        s.add(";");
        s.add("=");
        s.add("(");
        s.add(")");

        s.add("<");
        s.add("<=");
        s.add(">");
        s.add(">=");
        s.add("<>");
        s.add("==");
    }

    /**
     * Initializes the set of comments
     * 
     * @param s
     */
    private void initComments(Set<String> s) {
        s.add("#");
    }

    /**
     * Initializes a set with letters and numbers
     * 
     * @param s
     */
    private void initIDStrings(Set<String> s) {
        s.addAll(letters);
        s.addAll(numbers);
    }

    /**
     * Fills a set with characters from lo to hi
     * 
     * @param s  - the set to fill
     * @param lo - the lower bound
     * @param hi - the upper bound
     */
    private void fill(Set<String> s, char lo, char hi) {
        for (char c = lo; c <= hi; c++) {
            s.add(c + "");
        }
    }

    /**
     * Advances the position of the scanner
     */
    private void advance() {
        this.position++;
    }

    /**
     * Returns the current character in the program
     * 
     * @return the current character
     */
    private String posAsString() {
        return program.charAt(position) + "";
    }

    /**
     * Scans the next keyword or identifier
     * 
     * @return the next keyword or identifier
     */
    private Token nextKwID() {

        int old = this.position;
        advance();

        while (hasChar() && (letters.contains(posAsString()) || numbers.contains(posAsString()))) {
            advance();
        }

        String lexeme = program.substring(old, position);

        if (keywords.contains(lexeme))
            return new Token(lexeme, lexeme);
        else
            return new Token("id", lexeme);
    }

    /**
     * Scans the next number
     * 
     * @return the next number
     */
    private Token nextNum() {

        int old = this.position;

        while (hasChar() && numbers.contains(posAsString())) {
            advance();
        }

        if (hasChar() && program.charAt(position) == '.') {
            advance();
        }

        while (hasChar() && numbers.contains(posAsString())) {
            advance();
        }

        String lexeme = program.substring(old, position);

        if (lexeme.equals(".")) {
            return new Token(".");
        }

        return new Token("num", lexeme);
    }

    /**
     * Scans the next operator
     * 
     * @return the next operator
     */
    private Token nextOp() {
        int old = position;
        advance();
        String lexeme = program.substring(old, position);
        if (hasChar()) {
            String next = lexeme + program.charAt(position);
            if (operators.contains(next)) {
                advance();
                lexeme = next;
            }
        }
        return new Token(lexeme, lexeme);
    }

    /**
     * Scans the next comment
     */
    private void nextComment() {
        advance();
        while (hasChar() && '#' != program.charAt(position)) {
            advance();
        }

        if (hasChar() && '#' == program.charAt(position)) {
            advance();
        }
    }

    /**
     * Determines the kind of the next token (e.g., "id") and calls the
     * appropriate method to scan the token's lexeme (e.g., "foo").
     *
     * @return boolean indicating if there are more tokens to scan.
     */
    public boolean next() {

        while (hasChar() && whitespace.contains(posAsString())) {
            advance();
        }

        if (!hasChar()) {
            return false;
        }

        String c = posAsString();

        if (letters.contains(c)) {
            currentToken = nextKwID();
        } else if (operators.contains(c)) {
            currentToken = nextOp();
        } else if (numbers.contains(c) || c.equals(".")) {
            currentToken = nextNum();
            if (currentToken == null) {
                return next();
            }
        } else if (comments.contains(c)) {
            nextComment();
        } else {
            System.err.println("illegal character at position " + position);
            advance();
            return next();
        }

        return true;
    }

    /**
     * Determines if the current position of the scanner is in the bounds of the
     * program
     * 
     * @return true if there are more characters in program
     */
    public boolean hasChar() {
        if (position < program.length()) {
            return true;
        } else {
            currentToken = new Token("EOF");
            return false;
        }
    }

    /**
     * Scan's the next lexeme if the current token is the expected token.
     * advances the scanner if the current token is the expected token.
     * 
     * @param t - the expected token
     * @throws SyntaxException - if current token is not the expected token
     */
    public void match(Token t) throws SyntaxException {
        if (!t.equalType(getCurrent())) {
            throw new SyntaxException(position, t, getCurrent());
        }
        next();
    }

    /**
     * Returns the current token
     * 
     * @return the current token
     * @throws SyntaxException - if there is no current token
     */
    public Token getCurrent() throws SyntaxException {
        if (currentToken == null) {
            throw new SyntaxException(position, new Token("ANY"), new Token("EMPTY"));
        }
        return currentToken;
    }

    /**
     * Returns the current position of the scanner
     * 
     * @return the current position
     */
    public int getPosition() {
        return position;
    }
}