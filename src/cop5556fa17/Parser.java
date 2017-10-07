package cop5556fa17;


import cop5556fa17.Scanner.Kind;
import cop5556fa17.Scanner.Token;
import cop5556fa17.AST.*;

import static cop5556fa17.Scanner.Kind.*;

public class Parser {

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

	Parser(Scanner scanner) {
		this.scanner = scanner;
		t = scanner.nextToken();
	}

	/**
	 * Main method called by compiler to parser input.
	 * Checks for EOF
	 * 
	 * @throws SyntaxException
	 */
	public ASTNode parse() throws SyntaxException {
		ASTNode astnode = program();
		matchEOF();
		return astnode;
	}
	

	/**
	 * Program ::=  IDENTIFIER   ( Declaration SEMI | Statement SEMI )*   
	 * 
	 * Program is start symbol of our grammar.
	 * 
	 * @throws SyntaxException
	 */
	Program program() throws SyntaxException {
		//TODO  implement this
	    Program ret ; //= new Program(firstToken, name, decsAndStatements);
        match(IDENTIFIER);
        while(t.kind.equals(KW_int) || t.kind.equals(KW_boolean) || t.kind.equals(KW_image)  
                || t.kind.equals(KW_url) || t.kind.equals(KW_file)  || t.kind.equals(IDENTIFIER)){
            if(t.kind.equals(KW_int) || t.kind.equals(KW_boolean) || 
                    t.kind.equals(KW_image) || t.kind.equals(KW_url) || t.kind.equals(KW_file)) {
                Declaration();
                match(SEMI);
            }
            else if(t.kind.equals(IDENTIFIER)){
                Statement();
                match(SEMI);
            }
        
         }
        return ret;
	 }

	

	 void Statement()  throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(IDENTIFIER)){
            match(IDENTIFIER);
            if(t.kind.equals(OP_ASSIGN)|| t.kind.equals(LSQUARE)) Assignment();
            else if(t.kind.equals(OP_RARROW)) ImageOutStatement();
            else if(t.kind.equals(OP_LARROW)) ImageInStatement();
            else throw new SyntaxException(t, "Syntax error "+t);
        }
    }

     void ImageInStatement() throws SyntaxException {
        // TODO Auto-generated method stub
            match(OP_LARROW);
            Source();
        
    }

     void Sink() throws SyntaxException  {
        // TODO Auto-generated method stub
        if(t.kind.equals(IDENTIFIER)) match(IDENTIFIER);
        else if(t.kind.equals(KW_SCREEN)) match(KW_SCREEN);
        else throw new SyntaxException(t, "Syntax error "+t.kind);
        
    }

     void ImageOutStatement() throws SyntaxException {
        // TODO Auto-generated method stub
        match(OP_RARROW);
        Sink(); 
    }

     void Assignment() throws SyntaxException {
        // TODO Auto-generated method stub
        lhs();
        match(OP_ASSIGN);
        expression();
    }

     void lhs() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            lhsSelector();
            match(RSQUARE);
        }
        
    }

     void lhsSelector() throws SyntaxException {
        // TODO Auto-generated method stub
        match(LSQUARE);
        if(t.kind.equals(KW_x)) XySelector();
        else if(t.kind.equals(KW_r)) RaSelector();
        else throw new SyntaxException(t, "Syntax error "+t.kind);
        match(RSQUARE);
             
    }

     void RaSelector() throws SyntaxException  {
        // TODO Auto-generated method stub
        match(KW_r);
        match(COMMA);
        match(KW_A);    
    }

     void XySelector() throws SyntaxException  {
        // TODO Auto-generated method stub
            match(KW_x);
            match(COMMA);
            match(KW_y);   
    }

     void Declaration() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_int) || t.kind.equals(KW_boolean)  ) VariableDeclaration();
        else if(t.kind.equals(KW_image))  ImageDeclaration();
        else if(t.kind.equals(KW_url) || t.kind.equals(KW_file)) SourceSinkDeclaration(); 
        else throw new SyntaxException(t, "syntax error in token:"+ t);
    }

     void VariableDeclaration() throws SyntaxException  {
        // TODO Auto-generated method stub
        varType();
        match(IDENTIFIER);
        if(t.kind.equals(OP_ASSIGN)){
            match(OP_ASSIGN);
            expression();  
        }
              
    }

     void varType()  throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_int)) match(KW_int);
        else if(t.kind.equals(KW_boolean)) match(KW_boolean);
        else throw new SyntaxException(t, "syntax error in token:" + t);
        
    }

     void SourceSinkDeclaration() throws SyntaxException{
        // TODO Auto-generated method stub
        SourceSinkType();
        match(IDENTIFIER);
        match(OP_ASSIGN);
        Source();
    }

     void SourceSinkType() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_url)) match(KW_url);
        else if(t.kind.equals(KW_file)) match(KW_file);
        else throw new SyntaxException(t, "Syntax error:"+t);        
    }

     void ImageDeclaration() throws SyntaxException {
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

     void Source() throws SyntaxException  {
        // TODO Auto-generated method stub
        if(t.kind.equals(STRING_LITERAL)) match(STRING_LITERAL);
        else if(t.kind.equals(OP_AT)){
            match(OP_AT);
            expression();
        }
        else if(t.kind.equals(IDENTIFIER)) match(IDENTIFIER);
        else throw new SyntaxException(t, "Syntax error:"+t);
        
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
	    OrExpression();
	    ExtraExpression();
	}



	 void OrExpression() throws SyntaxException {
        // TODO Auto-generated method stub
	    AndExpression();
	    while(t.kind.equals(OP_OR)){
	        match(OP_OR);
	        AndExpression();
	    }
        
    }

     void AndExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        EqExpression();
        while(t.kind.equals(OP_AND)){
            match(OP_AND);
            EqExpression();
        }
    }

     void EqExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        RelExpression();
        while(t.kind.equals(OP_EQ) || t.kind.equals(OP_NEQ)){
            if(t.kind.equals(OP_EQ)) match(OP_EQ);
            else if(t.kind.equals(OP_NEQ)) match(OP_NEQ);
            RelExpression();
        }
        
    }

     void RelExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        AddExpression();
        while(t.kind.equals(OP_LT) || t.kind.equals(OP_GT) || t.kind.equals(OP_LE) || t.kind.equals(OP_GE) ){
            if(t.kind.equals(OP_LT)) match(OP_LT);
            else if(t.kind.equals(OP_GT)) match(OP_GT);
            else if(t.kind.equals(OP_LE)) match(OP_LE);
            else if(t.kind.equals(OP_GE)) match(OP_GE);
            AddExpression();
        }
        
    }

     void AddExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        MultExpression();
        while(t.kind.equals(OP_PLUS) || t.kind.equals(OP_MINUS)){
            if(t.kind.equals(OP_PLUS)) match(OP_PLUS);
            else if(t.kind.equals(OP_MINUS)) match(OP_MINUS);
            MultExpression();
            
        }
        
    }

     void MultExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        UnaryExpression();
        while(t.kind.equals(OP_TIMES) || t.kind.equals(OP_DIV) || t.kind.equals(OP_MOD)){
            if(t.kind.equals(OP_TIMES)) match(OP_TIMES);
            else if(t.kind.equals(OP_DIV)) match(OP_DIV);
            else if(t.kind.equals(OP_MOD))match(OP_MOD);
            UnaryExpression();
        }
        
    }

     void UnaryExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(OP_PLUS)){
            match(OP_PLUS);
            UnaryExpression();
        }
        else if(t.kind.equals(OP_MINUS)){
            match(OP_MINUS);
            UnaryExpression();
        }
        else UnaryExpressionNotPlusMinus();
    }

     void UnaryExpressionNotPlusMinus() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(OP_EXCL)){
            match(OP_EXCL);
            UnaryExpression();
        }
        
        else if(t.kind.equals(IDENTIFIER)) IndentOrPixSelExpr();
        else if(t.kind.equals(KW_x)) match(KW_x);
        else if(t.kind.equals(KW_y)) match(KW_y);
        else if(t.kind.equals(KW_r)) match(KW_r);
        else if(t.kind.equals(KW_a)) match(KW_a);
        else if(t.kind.equals(KW_X)) match(KW_X);
        else if(t.kind.equals(KW_Y)) match(KW_Y);
        else if(t.kind.equals(KW_Z)) match(KW_Z);
        else if(t.kind.equals(KW_A)) match(KW_A);
        else if(t.kind.equals(KW_R)) match(KW_R);
        else if(t.kind.equals(KW_DEF_X)) match(KW_DEF_X);
        else if(t.kind.equals(KW_DEF_Y)) match(KW_DEF_Y);
        else Primary();
        
    }

     void IndentOrPixSelExpr() throws SyntaxException {
        // TODO Auto-generated method stub
        match(IDENTIFIER);
        IndentOrPixNext();
        
    }
    

     void IndentOrPixNext() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            Selector();
            match(RSQUARE);
        }
        
    }

     void Selector() throws SyntaxException {
        // TODO Auto-generated method stub
        expression();
        match(COMMA);
        expression();
    }

     void Primary() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(INTEGER_LITERAL)) match(INTEGER_LITERAL);
        else if(t.kind.equals(BOOLEAN_LITERAL)) match(BOOLEAN_LITERAL);
        else if(t.kind.equals(LPAREN)){ 
            match(LPAREN);
            expression();
            match(RPAREN);
        }
        else FunctionApplication();
        
    }

     void FunctionApplication()throws SyntaxException {
        // TODO Auto-generated method stub
        Functionname();
        if(t.kind.equals(LPAREN)){
            match(LPAREN);
            expression();
            match(RPAREN);
        }
        else if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            Selector();
            match(RSQUARE);
        }
        else throw new SyntaxException(t, "Syntax error "+t.kind); 
        
    }

     void Functionname() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_sin)) match(KW_sin);
        else if(t.kind.equals(KW_cos)) match(KW_cos);
        else if(t.kind.equals(KW_atan)) match(KW_atan);
        else if(t.kind.equals(KW_abs)) match(KW_abs);
        else if(t.kind.equals(KW_cart_x)) match(KW_cart_x);
        else if(t.kind.equals(KW_cart_y)) match(KW_cart_y);
        else if(t.kind.equals(KW_polar_a)) match(KW_polar_a);
        else if(t.kind.equals(KW_polar_r)) match(KW_polar_r);
        else throw new SyntaxException(t, "Syntax error "+t.kind);
        
    }

     void ExtraExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(OP_Q)){
            match(OP_Q);
            expression();
            match(OP_COLON);
            expression();
            
        }
        //else throw new SyntaxException(t, "Syntax error "+t.kind);
        
    }

    /**
	 * Only for check at end of program. Does not "consume" EOF so no attempt to get
	 * nonexistent next Token.
	 * 
	 * @return
	 * @throws SyntaxException
	 */
	 Token matchEOF() throws SyntaxException {
		if (t.kind == EOF) {
			return t;
		}
		String message =  "Expected EOL at " + t;
		throw new SyntaxException(t, message);
	}
	
	
	 void match(Kind kind) throws SyntaxException {
        if (t.kind.equals(kind)) {
            //System.out.println(t.kind);
            t = scanner.nextToken();
        }
        else throw new SyntaxException(t, "Syntax error : expected :" + kind + " got :" +t.kind);
	}
    
}
