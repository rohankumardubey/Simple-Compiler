package cop5556fa17;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import cop5556fa17.Scanner.Kind;
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
import cop5556fa17.AST.Source;
import cop5556fa17.AST.Source_CommandLineParam;
import cop5556fa17.AST.Source_Ident;
import cop5556fa17.AST.Source_StringLiteral;
import cop5556fa17.AST.Statement_In;
import cop5556fa17.AST.Statement_Out;
import cop5556fa17.AST.Statement_Assign;
//import cop5556fa17.image.ImageFrame;
//import cop5556fa17.image.ImageSupport;

public class CodeGenVisitor implements ASTVisitor, Opcodes {

	/**
	 * All methods and variable static.
	 */


	/**
	 * @param DEVEL
	 *            used as parameter to genPrint and genPrintTOS
	 * @param GRADE
	 *            used as parameter to genPrint and genPrintTOS
	 * @param sourceFileName
	 *            name of source file, may be null.
	 */
	public CodeGenVisitor(boolean DEVEL, boolean GRADE, String sourceFileName) {
		super();
		this.DEVEL = DEVEL;
		this.GRADE = GRADE;
		this.sourceFileName = sourceFileName;
	}
	BufferedImage b = new BufferedImage(1, 1, 1);
	ClassWriter cw;
	String className;
	
	String classDesc;
	String sourceFileName;

	MethodVisitor mv; // visitor of method currently under construction

	/** Indicates whether genPrint and genPrintTOS should generate code. */
	final boolean DEVEL;
	final boolean GRADE;
	


	@Override
	public Object visitProgram(Program program, Object arg) throws Exception {
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		className = program.name;  
		classDesc = "L" + className + ";";
		String sourceFileName = (String) arg;
		cw.visit(52, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object", null);
		cw.visitSource(sourceFileName, null);
		// create main method
		mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
		// initialize
		mv.visitCode();		
		//add label before first instruction
		Label mainStart = new Label();
		mv.visitLabel(mainStart);		
		// if GRADE, generates code to add string to log
//		CodeGenUtils.genLog(GRADE, mv, "entering main");

		// visit decs and statements to add field to class
		//  and instructions to main method, respectivley
		
		
         FieldVisitor fv = cw.visitField(ACC_STATIC, "x", "I", null, false);
         fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "y", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "X", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "Y", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "r", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "a", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "R", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "A", "I", null, 0);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "DEF_X", "I", null, 256);fv.visitEnd();
        fv = cw.visitField(ACC_STATIC, "DEF_Y", "I", null, 256);fv.visitEnd();        
        fv = cw.visitField(ACC_STATIC, "Z", "I", null, 0xFFFFFF);fv.visitEnd();
		
		
		ArrayList<ASTNode> decsAndStatements = program.decsAndStatements;
		for (ASTNode node : decsAndStatements) {
			node.visit(this, arg);
		}

		//generates code to add string to log
//		CodeGenUtils.genLog(GRADE, mv, "leaving main");
		
		//adds the required (by the JVM) return statement to main
		mv.visitInsn(RETURN);
		
		//adds label at end of code
		Label mainEnd = new Label();
		mv.visitLabel(mainEnd);
		
		//handles parameters and local variables of main. Right now, only args
		mv.visitLocalVariable("args", "[Ljava/lang/String;", null, mainStart, mainEnd, 0);

		//Sets max stack size and number of local vars.
		//Because we use ClassWriter.COMPUTE_FRAMES as a parameter in the constructor,
		//asm will calculate this itself and the parameters are ignored.
		//If you have trouble with failures in this routine, it may be useful
		//to temporarily set the parameter in the ClassWriter constructor to 0.
		//The generated classfile will not be correct, but you will at least be
		//able to see what is in it.
		mv.visitMaxs(0, 0);
		
		//terminate construction of main method
		mv.visitEnd();
		
		//terminate class construction
		cw.visitEnd();

		//generate classfile as byte array and return
		return cw.toByteArray();
	}

	@Override
	public Object visitDeclaration_Variable(Declaration_Variable declaration_Variable, Object arg) throws Exception {
		// TODO 
	    if (declaration_Variable.vtype == Type.INTEGER)
        {
            FieldVisitor fv = cw.visitField(ACC_STATIC, declaration_Variable.name, "I", null, 0);
            fv.visitEnd();
        }
        else if (declaration_Variable.vtype == Type.BOOLEAN)
        {
            FieldVisitor fv = cw.visitField(ACC_STATIC, declaration_Variable.name, "Z", null, false);
            fv.visitEnd();
        }
        if (declaration_Variable.e != null)
        {
            declaration_Variable.e.visit(this, arg);
            if (declaration_Variable.vtype == Type.INTEGER)
            {
                mv.visitFieldInsn(PUTSTATIC, className, declaration_Variable.name, "I");
            }
            else if (declaration_Variable.vtype == Type.BOOLEAN)
            {
                mv.visitFieldInsn(PUTSTATIC, className, declaration_Variable.name, "Z");
            }
        }
        return null;
	}

	@Override
	public Object visitExpression_Binary(Expression_Binary expression_Binary, Object arg) throws Exception {
		// TODO 
	    
	    expression_Binary.e0.visit(this, null);
	    expression_Binary.e1.visit(this, null);
	    Label tLabel = new Label();
	    Label fLabel = new Label();
	    switch (expression_Binary.op) {
        case OP_PLUS:
            mv.visitInsn(IADD);
            break;
        case OP_MINUS:
            mv.visitInsn(ISUB);
            break;
        case OP_DIV:
            mv.visitInsn(IDIV);
            break;
        case OP_TIMES:
            mv.visitInsn(IMUL);
            break;
        case OP_MOD:
            mv.visitInsn(IREM);
            break;
        case OP_AND:
            mv.visitInsn(IAND);
            break;
        case OP_OR:
            mv.visitInsn(IOR);
            break;
        case OP_EQ:
            mv.visitJumpInsn(IF_ICMPEQ, tLabel);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, fLabel);
            mv.visitLabel(tLabel);
            mv.visitInsn(ICONST_1);
            
            break;
        case OP_NEQ:
            mv.visitJumpInsn(IF_ICMPNE, tLabel);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, fLabel);
            mv.visitLabel(tLabel);
            mv.visitInsn(ICONST_1);
            break;
        case OP_GE:
            mv.visitJumpInsn(IF_ICMPGE, tLabel);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, fLabel);
            mv.visitLabel(tLabel);
            mv.visitInsn(ICONST_1);
            break;
        case OP_GT:
            mv.visitJumpInsn(IF_ICMPGT, tLabel);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, fLabel);
            mv.visitLabel(tLabel);
            mv.visitInsn(ICONST_1);
            break;
        case OP_LE:
            mv.visitJumpInsn(IF_ICMPLE, tLabel);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, fLabel);
            mv.visitLabel(tLabel);
            mv.visitInsn(ICONST_1);
            break;
        case OP_LT:
            mv.visitJumpInsn(IF_ICMPLT, tLabel);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, fLabel);
            mv.visitLabel(tLabel);
            mv.visitInsn(ICONST_1);
            break;

            
        default:        
            
            break;
        }
	    mv.visitLabel(fLabel);
		
		return null;
	}

	@Override
	public Object visitExpression_Unary(Expression_Unary expression_Unary, Object arg) throws Exception {
		// TODO 
		expression_Unary.e.visit(this, null);
		switch(expression_Unary.op){
		case OP_MINUS:mv.visitInsn(INEG);break;
		case OP_EXCL: 
		    if(expression_Unary.e.vtype == Type.BOOLEAN){
		        mv.visitInsn(ICONST_1);
		        mv.visitInsn(IXOR);
		    }
		    else {
		        Integer i = new Integer(0x0fffffff);
		        mv.visitLdcInsn(i);
		        mv.visitInsn(IXOR);
		    }
		    break;
		 default : break;
		}
		if(expression_Unary.op == Kind.OP_PLUS){
		    
		}
		
		return null;
	}

	// generate code to leave the two values on the stack
	@Override
	public Object visitIndex(Index index, Object arg) throws Exception {
		// TODO HW6
		index.e0.visit(this, null);
		index.e1.visit(this, null);
		if(index.isCartesian()){}
		else {
		    mv.visitInsn(DUP2);
		    mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "cart_x", RuntimeFunctions.cart_xSig, false);
		    mv.visitInsn(DUP_X2);
		    mv.visitInsn(POP);
		    mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "cart_y", RuntimeFunctions.cart_ySig, false);
		    
		    
		}
		
		return null;
	}

	@Override
	public Object visitExpression_PixelSelector(Expression_PixelSelector expression_PixelSelector, Object arg)
			throws Exception {
		// TODO HW6
	    mv.visitFieldInsn(GETSTATIC, className, expression_PixelSelector.name, ImageSupport.ImageDesc);
	    expression_PixelSelector.index.visit(this, null);
	    mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "getPixel", ImageSupport.getPixelSig, false);
	    return null;
	}

	@Override
	public Object visitExpression_Conditional(Expression_Conditional expression_Conditional, Object arg)
			throws Exception {
		// TODO 
        Label falseLabel = new Label();
        Label fLabel = new Label();
	    expression_Conditional.condition.visit(this, arg);
        mv.visitJumpInsn(IFEQ, falseLabel);
        expression_Conditional.trueExpression.visit(this, arg);
        mv.visitJumpInsn(GOTO, fLabel);
        mv.visitLabel(falseLabel);
        expression_Conditional.falseExpression.visit(this, arg);
        mv.visitLabel(fLabel);        
//		CodeGenUtils.genLogTOS(GRADE, mv, expression_Conditional.trueExpression.vtype);
		return null;
	}


	@Override
	public Object visitDeclaration_Image(Declaration_Image declaration_Image, Object arg) throws Exception {
		// TODO HW6
	    FieldVisitor fv = cw.visitField(ACC_STATIC, declaration_Image.name
                , ImageSupport.ImageDesc, null, null);
        fv.visitEnd();
        
        if(declaration_Image.source == null && declaration_Image.xSize == null){
            
           mv.visitFieldInsn(GETSTATIC, className, "DEF_X", "I");
           mv.visitFieldInsn(GETSTATIC, className, "DEF_Y", "I");
            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "makeImage", ImageSupport.makeImageSig, false);
        } 
        else if(declaration_Image.source == null && declaration_Image.xSize != null){
            declaration_Image.xSize.visit(this, null);
            declaration_Image.ySize.visit(this, null);
            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "makeImage",ImageSupport.makeImageSig, false);
        }
        else if(declaration_Image.source != null && declaration_Image.xSize == null){
            declaration_Image.source.visit(this, null);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "readImage",ImageSupport.readImageSig, false);
            
        }
        else if(declaration_Image.source != null && declaration_Image.xSize != null){
            declaration_Image.source.visit(this, null);
            declaration_Image.xSize.visit(this, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf","(I)"+ImageSupport.IntegerDesc, false);
            declaration_Image.ySize.visit(this, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf","(I)"+ImageSupport.IntegerDesc, false);
            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "readImage", ImageSupport.readImageSig, false);         
            
        }  
        mv.visitFieldInsn(PUTSTATIC, className, declaration_Image.name, ImageSupport.ImageDesc);
        return null;
	}
	
  
	@Override
	public Object visitSource_StringLiteral(Source_StringLiteral source_StringLiteral, Object arg) throws Exception {
		// TODO HW6
	    mv.visitLdcInsn(source_StringLiteral.fileOrUrl);
	    return null;
	}

	

	@Override
	public Object visitSource_CommandLineParam(Source_CommandLineParam source_CommandLineParam, Object arg)
			throws Exception {
		// TODO 
	    mv.visitVarInsn(ALOAD, 0);
        source_CommandLineParam.paramNum.visit(this, arg);
        mv.visitInsn(AALOAD);
        return null;
	}

	@Override
	public Object visitSource_Ident(Source_Ident source_Ident, Object arg) throws Exception {
		// TODO HW6
	    mv.visitFieldInsn(GETSTATIC, className, source_Ident.name, ImageSupport.StringDesc);
        return null;
	}


	@Override
	public Object visitDeclaration_SourceSink(Declaration_SourceSink declaration_SourceSink, Object arg)
			throws Exception {
		// TODO HW6
		
		    FieldVisitor fv = cw.visitField(ACC_STATIC, declaration_SourceSink.name
		            , ImageSupport.StringDesc, null, null);
		    fv.visitEnd();
		    if(declaration_SourceSink.source != null){
		        declaration_SourceSink.source.visit(this, null);
		        mv.visitFieldInsn(PUTSTATIC, className, declaration_SourceSink.name,ImageSupport.StringDesc);
		    }
		
		
	    return null;
	}
	


	@Override
	public Object visitExpression_IntLit(Expression_IntLit expression_IntLit, Object arg) throws Exception {
		// TODO 	    
	    mv.visitLdcInsn(expression_IntLit.value);
//		CodeGenUtils.genLogTOS(GRADE, mv, Type.INTEGER);
		return null;
	}

	@Override
	public Object visitExpression_FunctionAppWithExprArg(
			Expression_FunctionAppWithExprArg expression_FunctionAppWithExprArg, Object arg) throws Exception {
		// TODO HW6
		expression_FunctionAppWithExprArg.arg.visit(this, null);
		switch (expression_FunctionAppWithExprArg.function) {
        case KW_abs:mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "abs", RuntimeFunctions.absSig,
                false); break;
        case KW_log:
            mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "log", RuntimeFunctions.logSig,
                    false); break;
        default:
            break;
        }
		return null;
	}

	@Override
	public Object visitExpression_FunctionAppWithIndexArg(
			Expression_FunctionAppWithIndexArg expression_FunctionAppWithIndexArg, Object arg) throws Exception {
		// TODO HW6
		expression_FunctionAppWithIndexArg.arg.e0.visit(this, null);
		expression_FunctionAppWithIndexArg.arg.e1.visit(this, null);
		
		switch (expression_FunctionAppWithIndexArg.function){
        case KW_cart_x: mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "cart_x", RuntimeFunctions.cart_xSig,
                false); break;
        case KW_cart_y: mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "cart_y", RuntimeFunctions.cart_ySig,
                false); break;
        case KW_polar_a: mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "polar_a", RuntimeFunctions.polar_aSig,
                false); break;
        case KW_polar_r: mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "polar_r", RuntimeFunctions.polar_rSig,
                false); break;
      
        default:
            break;
        }
		return null;
	}

	@Override
	public Object visitExpression_PredefinedName(Expression_PredefinedName expression_PredefinedName, Object arg)
			throws Exception {
		// TODO HW6
	    
	    switch (expression_PredefinedName.kind) {
            case KW_x: mv.visitFieldInsn(GETSTATIC, className, "x", "I");break;
            case KW_y: mv.visitFieldInsn(GETSTATIC, className, "y", "I");break;
            case KW_X: mv.visitFieldInsn(GETSTATIC, className, "X", "I");break;
            case KW_Y: mv.visitFieldInsn(GETSTATIC, className, "Y", "I");break;
            case KW_r: mv.visitFieldInsn(GETSTATIC, className, "r", "I");break;
            case KW_a: mv.visitFieldInsn(GETSTATIC, className, "a", "I");break;
            case KW_R: mv.visitFieldInsn(GETSTATIC, className, "R", "I");break;
            case KW_A: mv.visitFieldInsn(GETSTATIC, className, "A", "I");break;
            case KW_DEF_X: mv.visitFieldInsn(GETSTATIC, className, "DEF_X", "I");break;        
            case KW_DEF_Y: mv.visitFieldInsn(GETSTATIC, className, "DEF_Y", "I");break;           
            case KW_Z: mv.visitFieldInsn(GETSTATIC, className, "Z", "I");break;   
            default: break;
        }
	    return null;
	    
		
	}

	/** For Integers and booleans, the only "sink"is the screen, so generate code to print to console.
	 * For Images, load the Image onto the stack and visit the Sink which will generate the code to handle the image.
	 */
	@Override
	public Object visitStatement_Out(Statement_Out statement_Out, Object arg) throws Exception {
		// TODO in HW5:  only INTEGER and BOOLEAN
		// TODO HW6 remaining case
	    //statement_Out.sink.visit(this, arg);
        
	    String type = null;
	    if(statement_Out.getDec().vtype == Type.INTEGER) type = "I";
	    else if(statement_Out.getDec().vtype == Type.BOOLEAN) type = "Z";
	    else if(statement_Out.getDec().vtype == Type.IMAGE) type = ImageSupport.ImageDesc;
	    if(statement_Out.getDec().vtype != Type.IMAGE )
	    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	    mv.visitFieldInsn(GETSTATIC, className, statement_Out.name, type);
	    CodeGenUtils.genLogTOS(GRADE, mv, statement_Out.getDec().vtype);
	    if(statement_Out.getDec().vtype == Type.IMAGE) statement_Out.sink.visit(this, arg);
	    else if (statement_Out.getDec().vtype == Type.INTEGER) {
            //statement_Out.sink.visit(this, arg);
	        
	        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        }
        
        else if (statement_Out.getDec().vtype == Type.BOOLEAN) {
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Z)V", false);         
        }
        return null;
	    
	   
        
       
	}

	/**
	 * Visit source to load rhs, which will be a String, onto the stack
	 * 
	 *  In HW5, you only need to handle INTEGER and BOOLEAN
	 *  Use java.lang.Integer.parseInt or java.lang.Boolean.parseBoolean 
	 *  to convert String to actual type. 
	 *  
	 *  TODO HW6 remaining types
	 */
	@Override
	public Object visitStatement_In(Statement_In statement_In, Object arg) throws Exception {
		// TODO (see comment )
		
		if(statement_In.getDec().vtype== Type.INTEGER){
		    statement_In.source.visit(this, null);
		    mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I",false);
		    mv.visitFieldInsn(PUTSTATIC, className, statement_In.name, "I" );
		}
		else if(statement_In.getDec().vtype== Type.BOOLEAN) {
		    statement_In.source.visit(this, null);
		    mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "parseBoolean", "(Ljava/lang/String;)Z",false);
		    mv.visitFieldInsn(PUTSTATIC, className, statement_In.name,"Z" );
		}
		else {
		    
		    Declaration_Image declaration_image = (Declaration_Image)statement_In.getDec();
		    
		    if(declaration_image.xSize == null){
		            statement_In.source.visit(this, null);
		            mv.visitInsn(ACONST_NULL);
		            mv.visitInsn(ACONST_NULL);
		            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "readImage",ImageSupport.readImageSig, false);
		            //CodeGenUtils.genLogTOS(GRADE, mv, statement_In.getDec().vtype);
		   
		    }
		   else if( declaration_image.xSize != null){
		              statement_In.source.visit(this, null);
		            declaration_image.xSize.visit(this, null);
		            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf","(I)"+ImageSupport.IntegerDesc, false);
		            declaration_image.ySize.visit(this, null);
		            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf","(I)"+ImageSupport.IntegerDesc, false);
		            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "readImage", ImageSupport.readImageSig, false);         
		            //CodeGenUtils.genLogTOS(GRADE, mv, statement_In.getDec().vtype);
		   
		   }  
		   mv.visitFieldInsn(PUTSTATIC, className, declaration_image.name, ImageSupport.ImageDesc);
		        
		    
		}
		return null;
	}

	
	/**
	 * In HW5, only handle INTEGER and BOOLEAN types.
	 */
	/**
	 * In HW5, only handle INTEGER and BOOLEAN types.
	 */
	@Override
	public Object visitLHS(LHS lhs, Object arg) throws Exception {
		//TODO  (see comment)
	    
	    if(lhs.vtype == Type.INTEGER){
	        mv.visitFieldInsn(PUTSTATIC, className, lhs.name, "I");
	        
	    }
	    else if(lhs.vtype == Type.BOOLEAN){
	        mv.visitFieldInsn(PUTSTATIC, className, lhs.name, "Z");
	        
	    }
	    else if(lhs.vtype == Type.IMAGE){
	        //lhs.index.visit(this, null);
	        mv.visitFieldInsn(GETSTATIC, className, lhs.name, ImageSupport.ImageDesc);
	        mv.visitFieldInsn(GETSTATIC, className, "x", "I");
	        mv.visitFieldInsn(GETSTATIC, className, "y", "I");
	        mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "setPixel", ImageSupport.setPixelSig,false);
	        
	        
	        
	    }
	    return null;
	}
	

	@Override
	public Object visitSink_SCREEN(Sink_SCREEN sink_SCREEN, Object arg) throws Exception {
		//TODO HW6
	    mv.visitMethodInsn(INVOKESTATIC, ImageFrame.className, "makeFrame", "("
	            +ImageSupport.ImageDesc 
                +")"+ImageSupport.JFrameDesc,false);
	    mv.visitInsn(POP);    
	    return null;
		
	}

	@Override
	public Object visitSink_Ident(Sink_Ident sink_Ident, Object arg) throws Exception {
		//TODO HW6
	    
	    mv.visitFieldInsn(GETSTATIC, className, sink_Ident.name, ImageSupport.StringDesc);
		
		mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className, "write", ImageSupport.writeSig,false);
		
		return null;
	}

	@Override
	public Object visitExpression_BooleanLit(Expression_BooleanLit expression_BooleanLit, Object arg) throws Exception {
		//TODO
	    mv.visitLdcInsn(expression_BooleanLit.value);        
		//CodeGenUtils.genLogTOS(GRADE, mv, Type.BOOLEAN);
		return null;
	}

	@Override
	public Object visitExpression_Ident(Expression_Ident expression_Ident,
			Object arg) throws Exception {
		//TODO
	    switch(expression_Ident.vtype){
	    case INTEGER : mv.visitFieldInsn(GETSTATIC, className, expression_Ident.name, "I");
	                        break;
	    case BOOLEAN : mv.visitFieldInsn(GETSTATIC, className, expression_Ident.name, "Z");
	                        break;
	    default: break;
	    }
        
		//CodeGenUtils.genLogTOS(GRADE, mv, expression_Ident.vtype);
		return null;
	}

    @Override
    public Object visitStatement_Assign(Statement_Assign statement_Assign,
            Object arg) throws Exception {
        // TODO Auto-generated method stub
        if(statement_Assign.lhs.vtype == Type.INTEGER || statement_Assign.lhs.vtype == Type.BOOLEAN){
            statement_Assign.e.visit(this, arg);
            statement_Assign.lhs.visit(this, arg);
            
        }
        else if(statement_Assign.lhs.vtype == Type.IMAGE){
            Label OloopS = new Label();
            Label OloopE = new Label();
            Label IloopS = new Label();
            Label IloopE = new Label();
            
            
            mv.visitFieldInsn(GETSTATIC, className, statement_Assign.lhs.name, ImageSupport.ImageDesc);
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className,
                    "getX",ImageSupport.getXSig, false);
            mv.visitFieldInsn(PUTSTATIC, className, "X", "I");
            
            mv.visitMethodInsn(INVOKESTATIC, ImageSupport.className,
                    "getY",ImageSupport.getYSig, false); 
            mv.visitFieldInsn(PUTSTATIC, className, "Y", "I");
            mv.visitFieldInsn(GETSTATIC, className, "X", "I");
            mv.visitFieldInsn(GETSTATIC, className, "Y", "I");          
            mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "polar_r", 
                    RuntimeFunctions.polar_rSig, false);
            mv.visitFieldInsn(PUTSTATIC, className, "R", "I");           
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(GETSTATIC, className, "Y", "I");
            mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "polar_a", 
                    RuntimeFunctions.polar_aSig, false);
            mv.visitFieldInsn(PUTSTATIC, className, "A", "I");
            
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTSTATIC, className, "x", "I");         
            mv.visitLabel(OloopS);            
            mv.visitFieldInsn(GETSTATIC, className, "x", "I");            
            mv.visitFieldInsn(GETSTATIC, className, "X", "I");          
            mv.visitJumpInsn(IF_ICMPGE, OloopE);   
            
            mv.visitInsn(ICONST_0);
            mv.visitFieldInsn(PUTSTATIC, className, "y", "I");           
            mv.visitLabel(IloopS);           
            mv.visitFieldInsn(GETSTATIC, className, "y", "I");            
            mv.visitFieldInsn(GETSTATIC, className, "Y", "I");            
            mv.visitJumpInsn(IF_ICMPGE, IloopE);
            mv.visitFieldInsn(GETSTATIC, className, "x", "I");
            mv.visitFieldInsn(GETSTATIC, className, "y", "I");            
            mv.visitInsn(DUP2);            
            mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "polar_r", 
                    RuntimeFunctions.polar_rSig, false);
            mv.visitFieldInsn(PUTSTATIC, className, "r", "I");            
            mv.visitMethodInsn(INVOKESTATIC, RuntimeFunctions.className, "polar_a", 
                    RuntimeFunctions.polar_aSig, false);
            mv.visitFieldInsn(PUTSTATIC, className, "a", "I");
            statement_Assign.e.visit(this, arg);           
            statement_Assign.lhs.visit(this, arg);
            
            mv.visitInsn(ICONST_1);
            mv.visitFieldInsn(GETSTATIC, className, "y", "I");
            mv.visitInsn(IADD);
            mv.visitFieldInsn(PUTSTATIC, className, "y", "I");
            mv.visitJumpInsn(GOTO, IloopS);
            mv.visitLabel(IloopE);           
            mv.visitInsn(ICONST_1);
            mv.visitFieldInsn(GETSTATIC, className, "x", "I");
            mv.visitInsn(IADD);
            mv.visitFieldInsn(PUTSTATIC, className, "x", "I");            
            mv.visitJumpInsn(GOTO, OloopS);           
            mv.visitLabel(OloopE);
        }
        
        return null;
    }

}
