package cop5556fa17;


import java.util.ArrayList;

import org.omg.PortableInterceptor.INACTIVE;

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
	    ArrayList<ASTNode> ar = new ArrayList<ASTNode>();
	    Token ft =t; 
        match(IDENTIFIER);
        while(t.kind.equals(KW_int) || t.kind.equals(KW_boolean) || t.kind.equals(KW_image)  
                || t.kind.equals(KW_url) || t.kind.equals(KW_file)  || t.kind.equals(IDENTIFIER)){
            if(t.kind.equals(KW_int) || t.kind.equals(KW_boolean) || 
                    t.kind.equals(KW_image) || t.kind.equals(KW_url) || t.kind.equals(KW_file)) {
                ar.add(Declaration());
                match(SEMI);
            }
            else if(t.kind.equals(IDENTIFIER)){
                ar.add(Statement());
                match(SEMI);
            }
        
         }
        return new Program(ft, ft, ar);
	 }

	

	 ASTNode Statement()  throws SyntaxException {
        // TODO Auto-generated method stub
        
        match(IDENTIFIER);
        if(t.kind.equals(OP_ASSIGN)|| t.kind.equals(LSQUARE)) return(Assignment());
        else if(t.kind.equals(OP_RARROW)) return(ImageOutStatement());
        else if(t.kind.equals(OP_LARROW)) return(ImageInStatement());
        else throw new SyntaxException(t, "Syntax error "+t);
    
    }

     Statement_In ImageInStatement() throws SyntaxException {
        // TODO Auto-generated method stub
         Token ft = t;   
         match(OP_LARROW);
         Source s = Source();
         return new Statement_In(ft, ft, s);
        
    }

     Sink sink() throws SyntaxException  {
        // TODO Auto-generated method stub
        Token ft = t;
         if(t.kind.equals(IDENTIFIER)) {
            match(IDENTIFIER);
            return new Sink_Ident(ft, ft);
        }
        else if(t.kind.equals(KW_SCREEN)) {
            match(KW_SCREEN);
            return new Sink_SCREEN(ft);
        }
        else throw new SyntaxException(t, "Syntax error "+t.kind);
        
    }

     Statement_Out ImageOutStatement() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t; 
         match(OP_RARROW);
        Sink s = sink();
        return new Statement_Out(ft, ft, s);
    }

     Statement_Assign Assignment() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
        LHS l = lhs();
        match(OP_ASSIGN);
        Expression e = expression();
        return new Statement_Assign(ft, l, e);
    }

     LHS lhs() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(LSQUARE)){
            Token ft = t;
            match(LSQUARE);
            Index i =  lhsSelector();
            match(RSQUARE);
            return new LHS(ft, ft, i);
        }
        return null;
        
    }

     Index lhsSelector() throws SyntaxException {
        // TODO Auto-generated method stub
        match(LSQUARE);
        if(t.kind.equals(KW_x)){
            Index i =   XySelector();
            match(RSQUARE);
            return i;
        }
        else if(t.kind.equals(KW_r)) {
            Index i =  RaSelector();
            match(RSQUARE);
            return i;
        }
        else throw new SyntaxException(t, "Syntax error "+t.kind);
        
             
    }

     Index RaSelector() throws SyntaxException  {
        // TODO Auto-generated method stub
         Token ft = t;
         match(KW_r);
         Expression e0 = new Expression_PredefinedName(ft,ft.kind);
         match(COMMA);
         Token e = t;
         match(KW_A);
         Expression e1 = new Expression_PredefinedName(e,e.kind); 
        return new Index(ft, e0, e1);
    }

     Index XySelector() throws SyntaxException  {
        // TODO Auto-generated method stub
         Token ft = t;
         match(KW_x);
         Expression e0 = new Expression_PredefinedName(ft,ft.kind);
         match(COMMA);
         Token e = t;
         match(KW_y);
         Expression e1 = new Expression_PredefinedName(e,e.kind); 
        return new Index(ft, e0, e1); 
    }

     ASTNode Declaration() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_int) || t.kind.equals(KW_boolean)  ) return VariableDeclaration();
        else if(t.kind.equals(KW_image))  return ImageDeclaration();
        else if(t.kind.equals(KW_url) || t.kind.equals(KW_file)) return SourceSinkDeclaration(); 
        else throw new SyntaxException(t, "syntax error in token:"+ t);
    }

     Declaration_Variable VariableDeclaration() throws SyntaxException  {
        // TODO Auto-generated method stub
        Token ft = varType();
        Token name = t;
        match(IDENTIFIER);
        Expression e = null;
        if(t.kind.equals(OP_ASSIGN)){
            match(OP_ASSIGN);
            e = expression();  
        }
        return new Declaration_Variable(ft, ft, name, e);
              
    }

     Token varType()  throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_int)) {
            Token ret = t;
            match(KW_int);
            return ret;
        }
        else if(t.kind.equals(KW_boolean)) {
            Token ret = t;
            match(KW_boolean);
            return ret;
        }
        else throw new SyntaxException(t, "syntax error in token:" + t);
        
    }

     Declaration_SourceSink SourceSinkDeclaration() throws SyntaxException{
        // TODO Auto-generated method stub
        Token ft = t;
        SourceSinkType();
        Token name = t;
        match(IDENTIFIER);
        match(OP_ASSIGN);
        Source s = Source();
        return new Declaration_SourceSink(ft, ft, name, s);
    }

     void SourceSinkType() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(KW_url)) match(KW_url);
        else if(t.kind.equals(KW_file)) match(KW_file);
        else throw new SyntaxException(t, "Syntax error:"+t);        
    }

     Declaration_Image ImageDeclaration() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
        match(KW_image);
        Expression e0 = null;
        Expression e1 = null;
        Source s = null;
        if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            e0 = expression();
            match(COMMA);
            e1 = expression();
            match(RSQUARE);
        }
        Token name = t;
        match(IDENTIFIER);
        if(t.kind.equals(OP_LARROW)){
            match(OP_LARROW);
            s = Source();
        }
        return new Declaration_Image(ft, e0, e1, name, s); 
        
        
    }

     Source Source() throws SyntaxException  {
        // TODO Auto-generated method stub
         Token ft = t;
         if(t.kind.equals(STRING_LITERAL)) {
            String s = t.getText();
            match(STRING_LITERAL);
            return new Source_StringLiteral(ft, s);
        }
        else if(t.kind.equals(OP_AT)){
            match(OP_AT);
            Expression e = expression();
            return new Source_CommandLineParam(ft, e);
        }
        else if(t.kind.equals(IDENTIFIER)) {
            Token name = t;
            match(IDENTIFIER);
            return new Source_Ident(ft, name);
        }
        else throw new SyntaxException(t, "Syntax error:"+t);
        
    }

    /**
	 * Expression ::=  OrExpression  OP_Q  Expression OP_COLON Expression    | OrExpression
	 * 
	 * Our test cases may invoke this routine directly to support incremental development.
	 * 
	 * @throws SyntaxException
	 */
	Expression expression() throws SyntaxException {
		//TODO implement this.
	    Token ft = t;
	    Expression condition = OrExpression();
	    Expression trueCondition = null;
	    Expression falseCondition = null;
	    if(t.kind.equals(OP_Q)){
            match(OP_Q);
            trueCondition = expression();
            match(OP_COLON);
            falseCondition = expression();
            return new Expression_Conditional(ft, condition, trueCondition, falseCondition);
        }
	    return condition;
	    
	}



	 Expression OrExpression() throws SyntaxException {
        // TODO Auto-generated method stub
	     Token ft = t;
         Expression e0 = null;
         Expression e1 = null;
         
	    e0 = AndExpression();
	    while(t.kind.equals(OP_OR)){
	        
	        match(OP_OR);
	        e1 = AndExpression();
	        e0 = new Expression_Binary(ft, e0, t, e1);
	    }
        return e0;
    }

     Expression AndExpression() throws SyntaxException {
        // TODO Auto-generated method stub
         Token ft = t;
         Expression e0 = null;
         Expression e1 = null;
         
        e0 = EqExpression();
        while(t.kind.equals(OP_AND)){
            Token op = t;
            match(OP_AND);
            e1 = EqExpression();
            e0 = new Expression_Binary(ft, e0, op, e1);
        }
        return e0;
    }

     Expression EqExpression() throws SyntaxException {
        // TODO Auto-generated method stub
         Token ft = t;
         Expression e0 = null;
         Expression e1 = null;
         
        e0 = RelExpression();
        while(t.kind.equals(OP_EQ) || t.kind.equals(OP_NEQ)){
            Token op = t;
            if(t.kind.equals(OP_EQ)) match(OP_EQ);
            else if(t.kind.equals(OP_NEQ)) match(OP_NEQ);
            e1 = RelExpression();
            e0 = new Expression_Binary(ft, e0, op, e1);
        }
        return e0;
        
    }

     Expression RelExpression() throws SyntaxException {
        // TODO Auto-generated method stub
         Token ft = t;
         Expression e0 = null;
         Expression e1 = null;
        e0 = AddExpression();
        while(t.kind.equals(OP_LT) || t.kind.equals(OP_GT) || t.kind.equals(OP_LE) || t.kind.equals(OP_GE) ){
            Token op = t;
            if(t.kind.equals(OP_LT)) match(OP_LT);
            else if(t.kind.equals(OP_GT)) match(OP_GT);
            else if(t.kind.equals(OP_LE)) match(OP_LE);
            else if(t.kind.equals(OP_GE)) match(OP_GE);
            e1 = AddExpression();
            e0 = new Expression_Binary(ft, e0, op, e1);
        }
        return e0;
        
    }

     Expression AddExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
        Expression e0 = null;
        Expression e1 = null;
        
        e0 =  MultExpression();
        while(t.kind.equals(OP_PLUS) || t.kind.equals(OP_MINUS)){
            Token op = t;
            if(t.kind.equals(OP_PLUS)) match(OP_PLUS);
            else if(t.kind.equals(OP_MINUS)) match(OP_MINUS);
            e1 = MultExpression();
            e0 = new Expression_Binary(ft, e0, op, e1);
        }
        return e0;
        
    }

     Expression MultExpression() throws SyntaxException {
        // TODO Auto-generated method stub
         Expression e0 = null;
         Expression e1 = null;
        Token ft = t;
        e0 = UnaryExpression();
        while(t.kind.equals(OP_TIMES) || t.kind.equals(OP_DIV) || t.kind.equals(OP_MOD)){
            Token op = t;
            if(t.kind.equals(OP_TIMES)) match(OP_TIMES);
            else if(t.kind.equals(OP_DIV)) match(OP_DIV);
            else if(t.kind.equals(OP_MOD))match(OP_MOD);
            e1 = UnaryExpression();
            e0 = new Expression_Binary(ft, e0, op, e1);
        }
        return e0;
    }

     Expression UnaryExpression() throws SyntaxException {
        // TODO Auto-generated method stub
        if(t.kind.equals(OP_PLUS)){
            match(OP_PLUS);
            return UnaryExpression();
            
        }
        else if(t.kind.equals(OP_MINUS)){
            match(OP_MINUS);
            return UnaryExpression();
        }
        else return UnaryExpressionNotPlusMinus();
    }

     Expression UnaryExpressionNotPlusMinus() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
         if(t.kind.equals(OP_EXCL)){
            match(OP_EXCL);
            return UnaryExpression();
        }
        else if(t.kind.equals(IDENTIFIER)) return IndentOrPixSelExpr();
        else if(t.kind.equals(KW_x)) {
            match(KW_x);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_y)){
            match(KW_y);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_r)) {
            match(KW_r);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_a)) {
            match(KW_a);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_X)) {
            match(KW_X);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_Y)) {
            match(KW_Y);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_Z)) {
            match(KW_Z);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_A)){
            match(KW_A);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_R)) {
            match(KW_R);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_DEF_X)) {
            match(KW_DEF_X);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else if(t.kind.equals(KW_DEF_Y)) {
            match(KW_DEF_Y);
            return new Expression_PredefinedName(ft, ft.kind);
        }
        else return Primary();
        
    }

     Expression IndentOrPixSelExpr() throws SyntaxException {
        // TODO Auto-generated method stub
         Token ft = t;
        match(IDENTIFIER);
        Expression_PixelSelector ep = IndentOrPixNext();
        if(ep == null){
            return new Expression_Ident(ft, ft);
        }
        return ep;
        
    }
    

     Expression_PixelSelector IndentOrPixNext() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
         if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            Index i = Selector();
            match(RSQUARE);
            return new Expression_PixelSelector(ft, ft, i);
        }
         return null;
        
    }

     Index Selector() throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
        Expression e0 = expression();
        match(COMMA);
        Expression e1 = expression();
        return new Index(ft, e0, e1);
    }

     Expression Primary() throws SyntaxException {
        // TODO Auto-generated method stub
         Token ft = t;
        if(t.kind.equals(INTEGER_LITERAL)){
            match(INTEGER_LITERAL);
            return new Expression_IntLit(ft, ft.intVal());
        }
        else if(t.kind.equals(BOOLEAN_LITERAL)) {
            boolean boolVal = false;
            if(t.getText().equals("true")) boolVal = true;
            match(BOOLEAN_LITERAL);
            return new Expression_BooleanLit(ft, boolVal);
        }
        else if(t.kind.equals(LPAREN)){ 
            match(LPAREN);
            Expression e = expression();
            match(RPAREN);
            return e;
        }
        else return FunctionApplication();
        
    }

     Expression FunctionApplication()throws SyntaxException {
        // TODO Auto-generated method stub
        Token ft = t;
         Functionname();
        if(t.kind.equals(LPAREN)){
            match(LPAREN);
            Expression e = expression();
            match(RPAREN);
            return new Expression_FunctionAppWithExprArg(ft, ft.kind, e);
            
        }
        else if(t.kind.equals(LSQUARE)){
            match(LSQUARE);
            Index i = Selector();
            match(RSQUARE);
            return new Expression_FunctionAppWithIndexArg(ft, ft.kind, i);
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
