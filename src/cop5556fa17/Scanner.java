/* *
 * Scanner for the class project in COP5556 Programming Language Principles 
 * at the University of Florida, Fall 2017.
 * 
 * This software is solely for the educational benefit of students 
 * enrolled in the course during the Fall 2017 semester.  
 * 
 * This software, and any software derived from it,  may not be shared with others or posted to public web sites,
 * either during the course or afterwards.
 * 
 *  @Beverly A. Sanders, 2017
  */

package cop5556fa17;


import java.util.ArrayList;
import java.util.Arrays;


public class Scanner {
    
    static final char EOFchar = 0;  
    final ArrayList<Token> tokens;  
    final char[] chars;     
    /**
     * position of the next token to be returned by a call to nextToken
     */
    private int nextTokenPos = 0; 
    
    @SuppressWarnings("serial")
    public static class LexicalException extends Exception {
        
        int pos;

        public LexicalException(String message, int pos) {
            super(message);
            this.pos = pos;
        }
        
        public int getPos() { return pos; }

    }

    public static enum Kind {
        IDENTIFIER, INTEGER_LITERAL, BOOLEAN_LITERAL, STRING_LITERAL, 
        KW_x/* x */, KW_X/* X */, KW_y/* y */, KW_Y/* Y */, KW_r/* r */, KW_R/* R */, KW_a/* a */, 
        KW_A/* A */, KW_Z/* Z */, KW_DEF_X/* DEF_X */, KW_DEF_Y/* DEF_Y */, KW_SCREEN/* SCREEN */, 
        KW_cart_x/* cart_x */, KW_cart_y/* cart_y */, KW_polar_a/* polar_a */, KW_polar_r/* polar_r */, 
        KW_abs/* abs */, KW_sin/* sin */, KW_cos/* cos */, KW_atan/* atan */, KW_log/* log */, 
        KW_image/* image */,  KW_int/* int */, 
        KW_boolean/* boolean */, KW_url/* url */, KW_file/* file */, OP_ASSIGN/* = */, OP_GT/* > */, OP_LT/* < */, 
        OP_EXCL/* ! */, OP_Q/* ? */, OP_COLON/* : */, OP_EQ/* == */, OP_NEQ/* != */, OP_GE/* >= */, OP_LE/* <= */, 
        OP_AND/* & */, OP_OR/* | */, OP_PLUS/* + */, OP_MINUS/* - */, OP_TIMES/* * */, OP_DIV/* / */, OP_MOD/* % */, 
        OP_POWER/* ** */, OP_AT/* @ */, OP_RARROW/* -> */, OP_LARROW/* <- */, LPAREN/* ( */, RPAREN/* ) */, 
        LSQUARE/* [ */, RSQUARE/* ] */, SEMI/* ; */, COMMA/* , */, EOF;
    }

    public class Token {
        public final Kind kind;
        public final int pos;
        public final int length;
        public final int line;
        public final int pos_in_line;

        public Token(Kind kind, int pos, int length, int line, int pos_in_line) {
            super();
            this.kind = kind;
            this.pos = pos;
            this.length = length;
            this.line = line;
            this.pos_in_line = pos_in_line;
        }

        public String getText() {
            if (kind == Kind.STRING_LITERAL) {
                return chars2String(chars, pos, length);
            }
            else return String.copyValueOf(chars, pos, length);
        }

        /**
         * To get the text of a StringLiteral, we need to remove the
         * enclosing " characters and convert escaped characters to
         * the represented character.  For example the two characters \ t
         * in the char array should be converted to a single tab character in
         * the returned String
         * 
         * @param chars
         * @param pos
         * @param length
         * @return
         */
        private String chars2String(char[] chars, int pos, int length) {
            StringBuilder sb = new StringBuilder();
            for (int i = pos + 1; i < pos + length - 1; ++i) {// omit initial and final "
                char ch = chars[i];
                if (ch == '\\') { // handle escape
                    i++;
                    ch = chars[i];
                    switch (ch) {
                    case 'b':
                        sb.append('\b');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'r':
                        sb.append('\r'); //for completeness, line termination chars not allowed in String literals
                        break;
                    case 'n':
                        sb.append('\n'); //for completeness, line termination chars not allowed in String literals
                        break;
                    case '\"':
                        sb.append('\"');
                        break;
                    case '\'':
                        sb.append('\'');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    default:
                        assert false;
                        break;
                    }
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }

        /**
         * precondition:  This Token is an INTEGER_LITERAL
         * 
         * @returns the integer value represented by the token
         */
        public int intVal() {
            assert kind == Kind.INTEGER_LITERAL;
            return Integer.valueOf(String.copyValueOf(chars, pos, length));
        }

        public String toString() {
            return "[" + kind + "," + String.copyValueOf(chars, pos, length)  + "," + pos + "," + length + "," + line + ","
                    + pos_in_line + "]";
        }

        /** 
         * Since we overrode equals, we need to override hashCode.
         * https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
         * 
         * Both the equals and hashCode method were generated by eclipse
         * 
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((kind == null) ? 0 : kind.hashCode());
            result = prime * result + length;
            result = prime * result + line;
            result = prime * result + pos;
            result = prime * result + pos_in_line;
            return result;
        }

        /**
         * Override equals method to return true if other object
         * is the same class and all fields are equal.
         * 
         * Overriding this creates an obligation to override hashCode.
         * 
         * Both hashCode and equals were generated by eclipse.
         * 
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Token other = (Token) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (kind != other.kind)
                return false;
            if (length != other.length)
                return false;
            if (line != other.line)
                return false;
            if (pos != other.pos)
                return false;
            if (pos_in_line != other.pos_in_line)
                return false;
            return true;
        }

        /**
         * used in equals to get the Scanner object this Token is 
         * associated with.
         * @return
         */
        private Scanner getOuterType() {
            return Scanner.this;
        }

    }

    

    Scanner(String inputString) {
        int numChars = inputString.length();
        this.chars = Arrays.copyOf(inputString.toCharArray(), numChars + 1); // input string terminated with null char
        chars[numChars] = EOFchar;
        tokens = new ArrayList<Token>();
    }


    /**
     * Method to scan the input and create a list of Tokens.
     * 
     * If an error is encountered during scanning, throw a LexicalException.
     * 
     * @return
     * @throws LexicalException
     */
    public Scanner scan() throws LexicalException {
        /* TODO  Replace this with a correct and complete implementation!!! */
        int pos = 0;
        int line = 1;
        int posInLine = 1;
        while(pos<chars.length){
           
            char ch = chars[pos];
            switch(ch){
            case EOFchar:  {
                if(pos == chars.length-1)
                    tokens.add(new Token(Kind.EOF, pos, 0, line, posInLine)); 
                else new LexicalException("Encountered EOF before end of file ", pos);
                pos++; break;
            }
            case ' '  : pos++; posInLine++; break;
            case '\n' : pos++; posInLine = 1; line++;  break;
            case '\t' : pos++ ; posInLine += 1 ; break;
            case '\r' : {
                //tokens.add(new Token(Kind.OP_Q, pos, 1, line, posInLine));
                if(chars[pos+1] == '\n') { pos++ ;break; }
                pos++;
                posInLine = 1;
                line++;
                break;
            }
            case '\f': {
                pos++;
                posInLine++;
                break;
            }
            case '\"':{
                
                int end_ind = pos+1;
                while(end_ind <chars.length-1 && (chars[end_ind] != '\"')) {
                    if(chars[end_ind] == '\n' || chars[end_ind] == '\r') throw new LexicalException("found a line terminator in string literal", end_ind);
                    if(chars[end_ind] == '\\' && (chars[end_ind+1] =='n' || chars[end_ind+1] =='t' ||  chars[end_ind+1] =='f' ||  chars[end_ind+1] =='r' || 
                            chars[end_ind+1] =='b' || chars[end_ind+1] == '\"' || chars[end_ind+1] =='\'' || chars[end_ind+1] =='\\')){
                        end_ind++;
                }
                    else if(chars[end_ind] == '\\') throw new LexicalException("found an illegal escapse sequence", end_ind+1);
                
                    end_ind++;
                }
                if(end_ind == chars.length-1) throw new LexicalException("unclosed string literal",end_ind);
                else {
                    end_ind++;
                    tokens.add(new Token(Kind.STRING_LITERAL, pos, end_ind - pos, line, posInLine));
                    posInLine += end_ind - pos;
                    pos = end_ind ;
                                        
                }
                break;
            }
            case '?': {
                tokens.add(new Token(Kind.OP_Q, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case ':':{
                tokens.add(new Token(Kind.OP_COLON, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case '&' :{
                tokens.add(new Token(Kind.OP_AND, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case '|' : {
                tokens.add(new Token(Kind.OP_OR, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            
            case '+' : {
                tokens.add(new Token(Kind.OP_PLUS, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case '/': {
                if(chars[pos+1] == '/'){
                    int end_ind = pos+2;
                    while(end_ind<chars.length-1 && chars[end_ind] != '\n') {
                        if(chars[end_ind] == '\r' && chars[end_ind+1] == '\n'){ end_ind++;  break; }
                        else if(chars[end_ind] == '\r') break;
                        end_ind++;
                    }
                    pos = end_ind;
                    
                    break;
                }
                else{ 
                    tokens.add(new Token(Kind.OP_DIV, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                    break;   
                }
                
            }
            case '%' : {
                tokens.add(new Token(Kind.OP_MOD, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
                
            }
            case '@': {
                tokens.add(new Token(Kind.OP_AT, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            } 
            case '(':{
            
                tokens.add(new Token(Kind.LPAREN, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case ')' :{
            
                tokens.add(new Token(Kind.RPAREN, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case '[' :{
                tokens.add(new Token(Kind.LSQUARE, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case ']' :{
                tokens.add(new Token(Kind.RSQUARE, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case ';' :{
                tokens.add(new Token(Kind.SEMI, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case ',':{
                
                tokens.add(new Token(Kind.COMMA, pos, 1, line, posInLine));
                pos++;
                posInLine++;
                break;
            }
            case '=' : {
                if(chars[pos+1] == '=') {
                    tokens.add(new Token(Kind.OP_EQ, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                    
                }
                else {
                    tokens.add(new Token(Kind.OP_ASSIGN, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                    
                }
                break;
            }
            
            case '!' :{
                if(chars[pos+1] == '=') {
                    tokens.add(new Token(Kind.OP_NEQ, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                    
                }
                else {
                    tokens.add(new Token(Kind.OP_EXCL, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                    
                }
                break;
            }
            case '<' : {
                if(chars[pos+1] == '=') {
                    tokens.add(new Token(Kind.OP_LE, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                    
                }
                else if(chars[pos+1] == '-'){
                    tokens.add(new Token(Kind.OP_LARROW, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                    
                }
                else {
                    tokens.add(new Token(Kind.OP_LT, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                    
                }
                break;
            }
            case '>' :{
                if(chars[pos+1] == '=') {
                    tokens.add(new Token(Kind.OP_GE, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                    
                }
                else {
                    tokens.add(new Token(Kind.OP_GT, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                    
                }
                break;
            }
            case '-' : {
                if(chars[pos+1] == '>') {
                    tokens.add(new Token(Kind.OP_RARROW, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                    
                }
                else {
                    tokens.add(new Token(Kind.OP_MINUS, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                
                }
                break;
            }
            case '*' : {
                if(chars[pos+1] == '*') {
                    tokens.add(new Token(Kind.OP_POWER, pos, 2, line, posInLine));
                    pos += 2;
                    posInLine += 2;
                }
                else {
                    tokens.add(new Token(Kind.OP_TIMES, pos, 1, line, posInLine));
                    pos++;
                    posInLine++;
                    
                }
                break;
            }
            default:{
                if(Character.isDigit(ch)){
                    if (ch == '0' || (pos<chars.length && !Character.isDigit(chars[pos+1]) )){
                        tokens.add(new Token(Kind.INTEGER_LITERAL, pos, 1, line, posInLine));
                        pos++;
                        posInLine++;
                        
                    }
                    else {
                        int end_ind = pos+1;
                        while(end_ind<chars.length && Character.isDigit(chars[end_ind])) end_ind++;
                        try {
                            Integer.parseInt(new String(chars,pos,end_ind - pos));
                        }
                        catch(Exception e) {
                            throw new LexicalException("Number exceeds 32 bits", pos);
                        }
                        tokens.add(new Token(Kind.INTEGER_LITERAL,pos,end_ind - pos ,line,posInLine));
                        posInLine += end_ind - pos;
                        pos = end_ind;
                        
                    }
                }
                else if(Character.isLetter(ch) || ch == '_' || ch == '$'){
                    int end_ind = pos+1;
                    while(end_ind<chars.length && Character.isLetterOrDigit(chars[end_ind]) || chars[end_ind] == '_' || chars[end_ind] == '$' ) end_ind++;
                    String S = new String(chars,pos,end_ind - pos);
                    switch(S){
                    case "true": tokens.add(new Token(Kind.BOOLEAN_LITERAL,pos,end_ind - pos ,line,posInLine)); break;
                    case "false": tokens.add(new Token(Kind.BOOLEAN_LITERAL,pos,end_ind - pos ,line,posInLine)); break;
                    case "X": tokens.add(new Token(Kind.KW_X,pos,end_ind - pos ,line,posInLine)); break;
                    case "x": tokens.add(new Token(Kind.KW_x,pos,end_ind - pos ,line,posInLine)); break;
                    case "Y": tokens.add(new Token(Kind.KW_Y,pos,end_ind - pos ,line,posInLine)); break;
                    case "y": tokens.add(new Token(Kind.KW_y,pos,end_ind - pos ,line,posInLine)); break;
                    case "r": tokens.add(new Token(Kind.KW_r,pos,end_ind - pos ,line,posInLine)); break;
                    case "R": tokens.add(new Token(Kind.KW_R,pos,end_ind - pos ,line,posInLine)); break;
                    case "A": tokens.add(new Token(Kind.KW_A,pos,end_ind - pos ,line,posInLine)); break;
                    case "a": tokens.add(new Token(Kind.KW_a,pos,end_ind - pos ,line,posInLine)); break;
                    case "Z": tokens.add(new Token(Kind.KW_Z,pos,end_ind - pos ,line,posInLine)); break;
                    case "DEF_X": tokens.add(new Token(Kind.KW_DEF_X,pos,end_ind - pos ,line,posInLine)); break;
                    case "DEF_Y": tokens.add(new Token(Kind.KW_DEF_Y,pos,end_ind - pos ,line,posInLine)); break;
                    case "SCREEN": tokens.add(new Token(Kind.KW_SCREEN,pos,end_ind - pos ,line,posInLine)); break;
                    case "cart_x": tokens.add(new Token(Kind.KW_cart_x,pos,end_ind - pos ,line,posInLine)); break;
                    case "cart_y": tokens.add(new Token(Kind.KW_cart_y,pos,end_ind - pos ,line,posInLine)); break;
                    case "polar_a": tokens.add(new Token(Kind.KW_polar_a,pos,end_ind - pos ,line,posInLine)); break;
                    case "polar_r": tokens.add(new Token(Kind.KW_polar_r,pos,end_ind - pos ,line,posInLine)); break;
                    case "abs": tokens.add(new Token(Kind.KW_abs,pos,end_ind - pos ,line,posInLine)); break;
                    case "sin": tokens.add(new Token(Kind.KW_sin,pos,end_ind - pos ,line,posInLine)); break;
                    case "cos": tokens.add(new Token(Kind.KW_cos,pos,end_ind - pos ,line,posInLine)); break;
                    case "atan": tokens.add(new Token(Kind.KW_atan,pos,end_ind - pos ,line,posInLine)); break;
                    case "log": tokens.add(new Token(Kind.KW_log,pos,end_ind - pos ,line,posInLine)); break;
                    case "image": tokens.add(new Token(Kind.KW_image,pos,end_ind - pos ,line,posInLine)); break;
                    case "int": tokens.add(new Token(Kind.KW_int,pos,end_ind - pos ,line,posInLine)); break;
                    case "boolean": tokens.add(new Token(Kind.KW_boolean,pos,end_ind - pos ,line,posInLine)); break;
                    case "url": tokens.add(new Token(Kind.KW_url,pos,end_ind - pos ,line,posInLine)); break;
                    case "file": tokens.add(new Token(Kind.KW_file,pos,end_ind - pos ,line,posInLine)); break;
                    default : tokens.add(new Token(Kind.IDENTIFIER,pos,end_ind - pos ,line,posInLine)); break;
                    }   
                    posInLine += end_ind - pos;
                    pos = end_ind;
                    
                    
                }
                else throw new LexicalException("No suitable Token detected" , pos);
                
                
                
            }
            
            
            
            }
        }
        
        return this;
        

    }


    /**
     * Returns true if the internal interator has more Tokens
     * 
     * @return
     */
    public boolean hasTokens() {
        return nextTokenPos < tokens.size();
    }

    /**
     * Returns the next Token and updates the internal iterator so that
     * the next call to nextToken will return the next token in the list.
     * 
     * It is the callers responsibility to ensure that there is another Token.
     * 
     * Precondition:  hasTokens()
     * @return
     */
    public Token nextToken() {
        return tokens.get(nextTokenPos++);
    }
    
    /**
     * Returns the next Token, but does not update the internal iterator.
     * This means that the next call to nextToken or peek will return the
     * same Token as returned by this methods.
     * 
     * It is the callers responsibility to ensure that there is another Token.
     * 
     * Precondition:  hasTokens()
     * 
     * @return next Token.
     */
    public Token peek() {
        return tokens.get(nextTokenPos);
    }
    
    
    /**
     * Resets the internal iterator so that the next call to peek or nextToken
     * will return the first Token.
     */
    public void reset() {
        nextTokenPos = 0;
    }

    /**
     * Returns a String representation of the list of Tokens 
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Tokens:\n");
        for (int i = 0; i < tokens.size(); i++) {
            sb.append(tokens.get(i)).append('\n');
        }
        return sb.toString();
    }

}