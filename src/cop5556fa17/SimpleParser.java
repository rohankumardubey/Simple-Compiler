package cop5556fa17;



import java.beans.Expression;
import java.util.Arrays;

import cop5556fa17.Scanner.Kind;
import cop5556fa17.Scanner.Token;
import cop5556fa17.SimpleParser.SyntaxException;
import static cop5556fa17.Scanner.Kind.*;

public class SimpleParser {

	@SuppressWarnings("serial")
	public class SyntaxException extends Exception {
		Token t;

		public SyntaxException(Token t, String message) {
			super(message);
			this.t = t;
		}

	}


	Scanner scanner;
	Token t;

	SimpleParser(Scanner scanner) {
		this.scanner = scanner;
		t = scanner.nextToken();
	}

	/**
	 * Main method called by compiler to parser input.
	 * Checks for EOF
	 * 
	 * @throws SyntaxException
	 */
	public void parse() throws SyntaxException {
		program();
		matchEOF();
	}
	

	/**
	 * Program ::=  IDENTIFIER   ( Declaration SEMI | Statement SEMI )*   
	 * 
	 * Program is start symbol of our grammar.
	 * 
	 * @throws SyntaxException
	 */
	void program() throws SyntaxException {
		//TODO  implement this
	    if(t.kind.equals(IDENTIFIER)){
	        match(IDENTIFIER);
	        while(t.kind.equals(KW_int) || t.kind.equals(KW_image)  || t.kind.equals(KW_url) || t.kind.equals(IDENTIFIER)){
	            if(t.kind.equals(KW_int) || t.kind.equals(KW_image) || t.kind.equals(KW_url)) {
	                Declaration();
	                match(SEMI);
	            }
	            else if(t.kind.equals(IDENTIFIER)){
	                Statement();
	                match(SEMI);
	            }
	        
	         }
	        matchEOF();
	    }
	    else throw new SyntaxException(t, "Empty language not supported");
	    
	}

	

	private void Statement()  throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(IDENTIFIER)){
            match(IDENTIFIER);
            if(t.kind.equals(OP_ASSIGN)|| t.kind.equals(LSQUARE)) Assignment();
            else if(t.kind.equals(OP_RARROW)) ImageOutStatement();
            else if(t.kind.equals(OP_LARROW)) ImageInStatement();
            else throw new SyntaxException(t, "Syntax error");
        }
    }

    private void ImageInStatement() throws SyntaxException {
        // TODO Auto-generated method stub
            match(OP_LARROW);
            Sink();
        
    }

    private void Sink() throws SyntaxException  {
        // TODO Auto-generated method stub
        if(t.kind.equals(IDENTIFIER)) match(IDENTIFIER);
        else if(t.kind.equals(KW_SCREEN)) match(KW_SCREEN);
        else throw new SyntaxException(t, "Syntax error");
        
    }

    private void ImageOutStatement() throws SyntaxException {
        // TODO Auto-generated method stub
        match(OP_RARROW);
        Source(); 
    }

    private void Assignment() throws SyntaxException {
        // TODO Auto-generated method stub
        lhs();
        match(OP_ASSIGN);
        expression();
    }

    private void lhs() throws SyntaxException {
        // TODO Auto-generated method stub
        match(LSQUARE);
        lhsSelector();
        match(RSQUARE);
    }

    private void lhsSelector() throws SyntaxException {
        // TODO Auto-generated method stub
        match(LSQUARE);
        if(t.kind.equals(KW_x)) XySelector();
        else if(t.kind.equals(KW_r)) RaSelector();
        else throw new SyntaxException(t, "Syntax error");
        match(RSQUARE);
             
    }

    private void RaSelector() throws SyntaxException  {
        // TODO Auto-generated method stub
        match(KW_r);
        match(COMMA);
        match(KW_A);    
    }

    private void XySelector() throws SyntaxException  {
        // TODO Auto-generated method stub
            match(KW_x);
            match(COMMA);
            match(KW_y);   
    }

    private void Declaration() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_int)) VariableDeclaration();
        else if(t.kind.equals(KW_image))  ImageDeclaration();
        else if(t.kind.equals(KW_url)) SourceSinkDeclaration(); 
        else throw new SyntaxException(t, "syntax error in token");
    }

    private void VariableDeclaration() throws SyntaxException  {
        // TODO Auto-generated method stub
        varType();
        match(IDENTIFIER);
        if(t.kind.equals(OP_ASSIGN)){
            match(OP_ASSIGN);
            expression();  
        }
              
    }

    private void varType()  throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_int)) match(KW_int);
        else if(t.kind.equals(KW_boolean)) match(KW_boolean);
        else throw new SyntaxException(t, "syntax error in token");
        
    }

    private void SourceSinkDeclaration() throws SyntaxException{
        // TODO Auto-generated method stub
        SourceSinkType();
        match(IDENTIFIER);
        match(OP_ASSIGN);
        Source();
    }

    private void SourceSinkType() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_url)) match(KW_url);
        else if(t.kind.equals(KW_file)) match(KW_file);
        else throw new SyntaxException(t, "Syntax error");        
    }

    private void ImageDeclaration() throws SyntaxException {
        // TODO Auto-generated method stub
       
        match(KW_image);
        if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            expression();
            match(COMMA);
            expression();
            match(RSQUARE);
        }
        match(IDENTIFIER);
        if(t.kind.equals(OP_LARROW)){
            match(OP_LARROW);
            Source();
        }
            
        
        
    }

    private void Source() throws SyntaxException  {
        // TODO Auto-generated method stub
        if(t.kind.equals(STRING_LITERAL)) match(STRING_LITERAL);
        else if(t.kind.equals(OP_AT)){
            match(OP_AT);
            expression();
        }
        else if(t.kind.equals(IDENTIFIER)) match(IDENTIFIER);
        
    }

    /**
	 * Expression ::=  OrExpression  OP_Q  Expression OP_COLON Expression    | OrExpression
	 * 
	 * Our test cases may invoke this routine directly to support incremental development.
	 * 
	 * @throws SyntaxException
	 */
	void expression() throws SyntaxException {
		//TODO implement this.
	    
	    
		throw new UnsupportedOperationException();
	}



	/**
	 * Only for check at end of program. Does not "consume" EOF so no attempt to get
	 * nonexistent next Token.
	 * 
	 * @return
	 * @throws SyntaxException
	 */
	private Token matchEOF() throws SyntaxException {
		if (t.kind == EOF) {
			return t;
		}
		String message =  "Expected EOL at " + t.line + ":" + t.pos_in_line;
		throw new SyntaxException(t, message);
	}
	
	
	private void match(Kind kind) throws SyntaxException {
        if (t.kind.equals(kind)) {
            t = scanner.nextToken();
        }
        else throw new SyntaxException(t,"Syntax error");
	}
    
}
