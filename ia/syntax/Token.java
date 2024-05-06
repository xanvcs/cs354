package syntax;// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

    private String token;
    private String lexeme;

    /**
     * creates a token object
     * 
     * @param token  the toekn
     * @param lexeme the token's lexeme
     */
    public Token(String token, String lexeme) {
        this.token = token;
        this.lexeme = lexeme;
    }

    /**
     * creates a token object
     * 
     * @param token the token
     */
    public Token(String token) {
        this(token, token);
    }

    /**
     * gets the token
     * 
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * gets the lexeme
     * 
     * @return the lexeme
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * checks if type of the token is equal to the type of the other
     * 
     * @param t the token to compare
     * @return true if the token types are equal, false otherwise
     */
    public boolean equalType(Token t) {
        return this.token.equals(t.token);
    }

    /**
     * checks if the token is equal to the other
     * 
     * @param t the token to compare
     * @return true if the tokens are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        Token t = null;
        if (o instanceof Token) {
            t = (Token) o;
        } else {
            return false;
        }
        return token.equals(t.getToken()) && lexeme.equals(t.getLexeme());
    }

    /**
     * returns a string representation of the token
     * 
     * @return a string representation of the token
     */
    public String toString() {
        return "<" + getToken() + "," + getLexeme() + ">";
    }

}
