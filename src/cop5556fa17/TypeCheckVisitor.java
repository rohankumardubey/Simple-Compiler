package cop5556fa17;

import java.net.URL;

import cop5556fa17.Scanner.Kind;
import cop5556fa17.Scanner.Token;
import cop5556fa17.TypeUtils.Type;
import cop5556fa17.AST.ASTNode;
import cop5556fa17.AST.ASTVisitor;
import cop5556fa17.AST.Declaration;
import cop5556fa17.AST.Declaration_Image;
import cop5556fa17.AST.Declaration_SourceSink;
import cop5556fa17.AST.Declaration_Variable;
import cop5556fa17.AST.Expression;
import cop5556fa17.AST.Expression_Binary;
import cop5556fa17.AST.Expression_BooleanLit;
import cop5556fa17.AST.Expression_Conditional;
import cop5556fa17.AST.Expression_FunctionAppWithExprArg;
import cop5556fa17.AST.Expression_FunctionAppWithIndexArg;
import cop5556fa17.AST.Expression_Ident;
import cop5556fa17.AST.Expression_IntLit;
import cop5556fa17.AST.Expression_PixelSelector;
import cop5556fa17.AST.Expression_PredefinedName;
import cop5556fa17.AST.Expression_Unary;
import cop5556fa17.AST.Index;
import cop5556fa17.AST.LHS;
import cop5556fa17.AST.Program;
import cop5556fa17.AST.Sink_Ident;
import cop5556fa17.AST.Sink_SCREEN;
import cop5556fa17.AST.Source_CommandLineParam;
import cop5556fa17.AST.Source_Ident;
import cop5556fa17.AST.Source_StringLiteral;
import cop5556fa17.AST.Statement_Assign;
import cop5556fa17.AST.Statement_In;
import cop5556fa17.AST.Statement_Out;

public class TypeCheckVisitor implements ASTVisitor {
	
    SymbolTable s = new SymbolTable();
    
		@SuppressWarnings("serial")
		public static class SemanticException extends Exception {
			Token t;

			public SemanticException(Token t, String message) {
				super("line " + t.line + " pos " + t.pos_in_line + ": "+  message);
				this.t = t;
			}

		}		
		
		
		

	
	/**
	 * The program name is only used for naming the class.  It does not rule out
	 * variables with the same name.  It is returned for convenience.
	 * 
	 * @throws Exception 
	 */
	@Override
	public Object visitProgram(Program program, Object arg) throws Exception {
		for (ASTNode node: program.decsAndStatements) {
			node.visit(this, arg);
		}
		return program.name;
	}

	@Override
	public Object visitDeclaration_Variable(
			Declaration_Variable declaration_Variable, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    if(s.iscontains(declaration_Variable.name)) 
            throw  new SemanticException(declaration_Variable.firstToken, 
                    "Symantic exeption at : "+ declaration_Variable.firstToken.toString());
       
	    declaration_Variable.vtype = TypeUtils.getType(declaration_Variable.firstToken);
	    if(declaration_Variable.e!=null)  {
	        Type e0t = (Type) declaration_Variable.e.visit(this, null);
	        if(e0t == declaration_Variable.vtype){
	            
	        }
	        else
	            throw  new SemanticException(declaration_Variable.firstToken, 
	                    "Symantic exeption at "+ declaration_Variable.firstToken.toString());                   
	        
	    }
        s.insert(declaration_Variable.name, declaration_Variable);
        return declaration_Variable.vtype;
	}

	@Override
	public Object visitExpression_Binary(Expression_Binary expression_Binary,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
		Type e0t = (Type) expression_Binary.e0.visit(this, null);
		Type e1t = (Type) expression_Binary.e1.visit(this, null);
		
		switch (expression_Binary.op) {
            case OP_EQ:
            case OP_NEQ:
                expression_Binary.vtype = Type.BOOLEAN;
                break;
                
            case OP_GE :
            case OP_GT:
            case OP_LT:
            case OP_LE:
                if(e0t == Type.INTEGER){
                    expression_Binary.vtype = Type.BOOLEAN;
                }          
                break;
            case OP_AND:
            case OP_OR:
                if(e0t == Type.INTEGER || e0t == Type.BOOLEAN){
                    expression_Binary.vtype = e0t;
                }
                break;
            case OP_DIV:
            case OP_MINUS:
            case OP_MOD:
            case OP_PLUS:
            case OP_POWER:
            case OP_TIMES:
                if(e0t == Type.INTEGER){
                    expression_Binary.vtype = Type.INTEGER;
                }
                break;
            default:
                break;
        }
		if(e0t == e1t && expression_Binary.vtype != Type.NONE){
		
		}
		else{
		    throw  new SemanticException(expression_Binary.firstToken, 
                    "Symantic exeption at "+ expression_Binary.firstToken.toString());   
		    	    
		}
		return expression_Binary.vtype;
		
		
	}

	@Override
	public Object visitExpression_Unary(Expression_Unary expression_Unary,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
		Type expType = (Type) expression_Unary.e.visit(this, null);
		switch (expression_Unary.op) {
        case OP_EXCL:
            if((expType == Type.BOOLEAN || expType ==Type.INTEGER)) expression_Unary.vtype = expType;
            break;
        case OP_PLUS:
        case OP_MINUS:
            if(expType == Type.INTEGER) expression_Unary.vtype = expType;
            break;

        default:
            break;
        }
		if(expression_Unary.vtype != Type.NONE){
		}
		else{
		    throw  new SemanticException(expression_Unary.firstToken, 
                    "Symantic exeption at "+ expression_Unary.firstToken.toString());   
		    		    
		}
		return expression_Unary.vtype;
		
	}

	@Override
	public Object visitIndex(Index index, Object arg) throws Exception {
		// TODO Auto-generated method stub
		Type e0type = (Type)index.e0.visit(this, null);
		Type e1Type = (Type)index.e1.visit(this, null);
		if(e0type == Type.INTEGER && e1Type == Type.INTEGER){
		    
		}
		else {
		    throw  new SemanticException(index.firstToken, 
                    "Symantic exeption at "+ index.firstToken.toString()); 
		}
		index.setCartesian(!(index.e0.firstToken.kind ==Kind.KW_r && index.e0.getClass() == Expression_PredefinedName.class && 
		        index.e1.firstToken.kind == Kind.KW_a && index.e1.getClass() == Expression_PredefinedName.class));
		return index.isCartesian();
	}

	@Override
	public Object visitExpression_PixelSelector(
			Expression_PixelSelector expression_PixelSelector, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    if(expression_PixelSelector.index != null) expression_PixelSelector.index.visit(this, null);
	    if(!s.iscontains(expression_PixelSelector.name)){
	        throw  new SemanticException(expression_PixelSelector.firstToken,
	                "Symantic exeption at "+ expression_PixelSelector.firstToken.toString());
	    }
	    
		Type nameType = s.getfromSymboltable(expression_PixelSelector.name).vtype;
		
		if(nameType == Type.IMAGE){
		    expression_PixelSelector.vtype = Type.INTEGER;
		}
		else if(expression_PixelSelector.index == null) 
		    expression_PixelSelector.vtype = nameType;
		if(expression_PixelSelector.vtype != Type.NONE){
		}
		else{
		    throw  new SemanticException(expression_PixelSelector.firstToken, 
                    "Symantic exeption at "+ expression_PixelSelector.firstToken.toString()); 
		}
		return expression_PixelSelector.vtype;
	}

	@Override
	public Object visitExpression_Conditional(
			Expression_Conditional expression_Conditional, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		Type econType = (Type)expression_Conditional.condition.visit(this, null);
		Type etrueType = (Type) expression_Conditional.trueExpression.visit(this, null);
		Type efalseType = (Type) expression_Conditional.falseExpression.visit(this, null);
		expression_Conditional.vtype = etrueType;
		if(econType == Type.BOOLEAN && etrueType == efalseType){
		
		}
		else{
		    throw  new SemanticException(expression_Conditional.firstToken, 
                    "Symantic exeption at "+ expression_Conditional.firstToken.toString()); 
		    
		}
		return expression_Conditional.vtype;
	}

	@Override
	public Object visitDeclaration_Image(Declaration_Image declaration_Image,
			Object arg) throws Exception {
	    if(s.iscontains(declaration_Image.name)) {
	        throw  new SemanticException(declaration_Image.firstToken, 
                    "Symantic exeption at "+ declaration_Image.firstToken.toString()); 
	    }
	    //declaration_Image.xSize.
	    
	    if(declaration_Image.xSize != null){
	        Type xtype = (Type) declaration_Image.xSize.visit(this, null);
	        Type ytype = Type.NONE;
	        if(declaration_Image.ySize!= null)
	            ytype = (Type) declaration_Image.ySize.visit(this, null);
	        else
	            throw  new SemanticException(declaration_Image.firstToken, 
	                    "Symantic exeption at "+ declaration_Image.firstToken.toString()); 
	       if(xtype == Type.INTEGER && ytype == Type.INTEGER){
	           
	       }
	       else{
	           throw  new SemanticException(declaration_Image.firstToken, 
                       "Symantic exeption at "+ declaration_Image.firstToken.toString());
	       }
	            
	    }
	    if(declaration_Image.source != null)
            declaration_Image.source.visit(this, null);
	    
	    declaration_Image.vtype = Type.IMAGE;
	    s.insert(declaration_Image.name, declaration_Image);
	    return declaration_Image.vtype;
	}
	                                                                                // for URL verificaltion
	public static boolean isValidUrl(String surl)
    {
        try
        {
            URL url = new URL(surl);
            url.toURI();
            return true;
        } catch (Exception exception)
        {
            return false;
        }
    }

	@Override
	public Object visitSource_StringLiteral(
			Source_StringLiteral source_StringLiteral, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    
	    //implement URL validator
	    boolean isvalid = isValidUrl(source_StringLiteral.fileOrUrl);
	    
	    if(isvalid){
	        source_StringLiteral.vtype = Type.URL;
	    }
	    else source_StringLiteral.vtype = Type.FILE;
	    return source_StringLiteral.vtype;
	    
	}

	@Override
	public Object visitSource_CommandLineParam(
			Source_CommandLineParam source_CommandLineParam, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    source_CommandLineParam.paramNum.visit(this, null);
		source_CommandLineParam.vtype = source_CommandLineParam.paramNum.vtype;
		if(source_CommandLineParam.vtype == Type.INTEGER){
		    
		}
		else {
            throw  new SemanticException(source_CommandLineParam.firstToken, 
                    "Symantic exeption at "+ source_CommandLineParam.firstToken.toString()); 
        }
		return source_CommandLineParam.vtype;
	}

	@Override
	public Object visitSource_Ident(Source_Ident source_Ident, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    if(!s.iscontains(source_Ident.name)){
	        throw  new SemanticException(source_Ident.firstToken,
	                "Symantic exeption at "+ source_Ident.firstToken.toString());
	    }
		Declaration d = s.getfromSymboltable(source_Ident.name);
		if (d == null){
		    throw  new SemanticException(source_Ident.firstToken, 
                    "Symantic exeption at "+ 
                    source_Ident.firstToken.toString()); 
        
		    
		}
		source_Ident.vtype = d.vtype;
		if(source_Ident.vtype == Type.FILE || source_Ident.vtype == Type.URL){
		    
		}
		else {
            throw  new SemanticException(source_Ident.firstToken, 
                    "Symantic exeption at "+ source_Ident.firstToken.toString()); 
        }
		return source_Ident.vtype;
	}

	@Override
	public Object visitDeclaration_SourceSink(
			Declaration_SourceSink declaration_SourceSink, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    if(s.iscontains(declaration_SourceSink.name)) 
            throw  new SemanticException(declaration_SourceSink.firstToken, 
                    "Symantic exeption at "+ declaration_SourceSink.firstToken.toString());
	    Type sourceType = (Type) declaration_SourceSink.source.visit(this, null);
	    declaration_SourceSink.vtype = TypeUtils.getType(declaration_SourceSink.firstToken);
        s.insert(declaration_SourceSink.name, declaration_SourceSink);
        if(sourceType == declaration_SourceSink.vtype ){}
        else {
            throw  new SemanticException(declaration_SourceSink.firstToken, 
                    "Symantic exeption at "+ declaration_SourceSink.firstToken.toString());
            
        }
        return declaration_SourceSink.vtype;
	}

	@Override
	public Object visitExpression_IntLit(Expression_IntLit expression_IntLit,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
		expression_IntLit.vtype = Type.INTEGER;
		return expression_IntLit.vtype;
	}

	@Override
	public Object visitExpression_FunctionAppWithExprArg(
			Expression_FunctionAppWithExprArg expression_FunctionAppWithExprArg,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
	    Type expType = (Type) expression_FunctionAppWithExprArg.arg.visit(this, null);
	    if(expType == Type.INTEGER){
	    }
	    else{
	        throw  new SemanticException(expression_FunctionAppWithExprArg.firstToken, 
                    "Symantic exeption at "+ expression_FunctionAppWithExprArg.firstToken.toString());
	    }
	    expression_FunctionAppWithExprArg.vtype =  expType;
		return expression_FunctionAppWithExprArg.vtype;
	}

	@Override
	public Object visitExpression_FunctionAppWithIndexArg(
			Expression_FunctionAppWithIndexArg expression_FunctionAppWithIndexArg,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
		expression_FunctionAppWithIndexArg.vtype = Type.INTEGER;
		return expression_FunctionAppWithIndexArg.vtype;
	}

	@Override
	public Object visitExpression_PredefinedName(
			Expression_PredefinedName expression_PredefinedName, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		expression_PredefinedName.vtype = Type.INTEGER;
		return expression_PredefinedName.vtype;
	}

	@Override
	public Object visitStatement_Out(Statement_Out statement_Out, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    if(!s.iscontains(statement_Out.name)){
            throw  new SemanticException(
                    statement_Out.firstToken,"Symantic exeption at "+ statement_Out.firstToken.toString());
            
        }
	    
	    Type sinkType = (Type)statement_Out.sink.visit(this, null);
	    Declaration d = s.getfromSymboltable(statement_Out.name);
	    statement_Out.setDec(d);
		if(d != null && ((d.vtype == Type.INTEGER || d.vtype == Type.BOOLEAN) && sinkType == Type.SCREEN) || 
		        (d.vtype == Type.IMAGE && (sinkType == Type.FILE || sinkType == Type.SCREEN))) {
		            
		}
		else{
		     throw  new SemanticException(
	                 statement_Out.firstToken,"Symantic exeption at "+ statement_Out.firstToken.toString());
		}
		return statement_Out.vtype;
		
	}

	@Override
	public Object visitStatement_In(Statement_In statement_In, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    Type sourceType = (Type)statement_In.source.visit(this, null);
		if(!s.iscontains(statement_In.name)){
		    }
		Declaration d = s.getfromSymboltable(statement_In.name);
	    statement_In.setDec(d);
		if( d != null && d.vtype == sourceType){
		    
		}
		else{
		    throw  new SemanticException(
                    statement_In.firstToken,"Symantic exeption at "
                            + statement_In.firstToken.toString());   
		    
		}
		return statement_In.vtype;
	}

	@Override
	public Object visitStatement_Assign(Statement_Assign statement_Assign,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
	    Type lhsType = (Type)statement_Assign.lhs.visit(this, null);
	    Type expType = (Type)statement_Assign.e.visit(this, null);
	    if (lhsType!=expType ) {
	        throw  new SemanticException(
                statement_Assign.firstToken,"Symantic exeption at "+ 
                        statement_Assign.firstToken.toString());
	    }
	    statement_Assign.setCartesian(statement_Assign.lhs.isCartesian);
	    return statement_Assign.vtype;
	}

	@Override
	public Object visitLHS(LHS lhs, Object arg) throws Exception {
		// TODO Auto-generated method stub
	    if(lhs.index != null){
	        boolean indexcart = (boolean) lhs.index.visit(this, arg);
	        lhs.setCartesian(indexcart);
	    }
	    
	    if(!s.iscontains(lhs.name)){
	        throw  new SemanticException(
	                lhs.firstToken,
	                "Symantic exeption at "+ lhs.firstToken.toString());
	        
	    }
		lhs.setDec(s.getfromSymboltable(lhs.name));
		lhs.vtype = lhs.getDec().vtype;
		
		return lhs.vtype;
	}

	@Override
	public Object visitSink_SCREEN(Sink_SCREEN sink_SCREEN, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    sink_SCREEN.vtype = Type.SCREEN;
	    return sink_SCREEN.vtype;
	}

	@Override
	public Object visitSink_Ident(Sink_Ident sink_Ident, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
	    if(!s.iscontains(sink_Ident.name)){
	        throw  new SemanticException(sink_Ident.firstToken,
	                "Symantic exeption at "+
	                 sink_Ident.firstToken.toString());
	    }
	    
		sink_Ident.vtype = s.getfromSymboltable(sink_Ident.name).vtype;
		if(sink_Ident.vtype == Type.FILE){
		}
		else{
		    throw  new SemanticException(sink_Ident.firstToken,
                    "Symantic exeption at "+ sink_Ident.firstToken.toString());   
		}
		return sink_Ident.vtype;
	}

	@Override
	public Object visitExpression_BooleanLit(
			Expression_BooleanLit expression_BooleanLit, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		expression_BooleanLit.vtype = Type.BOOLEAN;
		return expression_BooleanLit.vtype;
	}

	@Override
	public Object visitExpression_Ident(Expression_Ident expression_Ident,
			Object arg) throws Exception {
		// TODO Auto-generated method stub
	    if(!s.iscontains(expression_Ident.name)){
	        throw  new SemanticException(expression_Ident.firstToken,
                    "Symantic exeption at "+ expression_Ident.firstToken.toString());
	        
	    }
		expression_Ident.vtype = s.getfromSymboltable(expression_Ident.name).vtype;
		return expression_Ident.vtype;
	}

}
