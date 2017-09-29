package cop5556fa17;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cop5556fa17.Scanner.LexicalException;
import cop5556fa17.Scanner.Token;
import cop5556fa17.SimpleParser.SyntaxException;

import static cop5556fa17.Scanner.Kind.*;

public class SimpleParserTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	//To make it easy to print objects and turn this output on and off
	static final boolean doPrint = true;
	private void show(Object input) {
    	if (doPrint) {
        	System.out.println(input.toString());
    	}
	}

	@Test
	public void testEmpty() throws LexicalException, SyntaxException {
    	String input = "";  //The input is the empty string.  This is not legal
    	show(input);    	//Display the input
    	Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
    	show(scanner);   //Display the Scanner
    	SimpleParser parser = new SimpleParser(scanner);  //Create a parser
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void testDec1() throws LexicalException, SyntaxException {
    	String input = "prog int k;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
    	show(scanner);   //Display the Scanner
    	SimpleParser parser = new SimpleParser(scanner);  //
    	parser.parse();
	}

	@Test
	public void expression1() throws SyntaxException, LexicalException
	{
    	String input = "2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression2() throws SyntaxException, LexicalException
	{
    	String input = "bansari int b";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void expression3() throws SyntaxException, LexicalException
	{
    	String input = "bansari int b;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	//checking image declaration
	public void expression4() throws SyntaxException, LexicalException
	{
    	String input = "bansari image pratham;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression5() throws SyntaxException, LexicalException
	{
    	String input = "bansari file ban=\"bans\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression6() throws SyntaxException, LexicalException
	{
    	String input = "bansari image [m+p,b+y] jh;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression7() throws SyntaxException, LexicalException
	{
    	String input = "bansari image [m+p,b+y] jh <- \"Pratham\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression8() throws SyntaxException, LexicalException
	{
    	String input = "bansari int p=a+b;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression9() throws SyntaxException, LexicalException
	{
    	String input = "bansari bansari = a+b;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression10() throws SyntaxException, LexicalException
	{
    	String input = "bansari bansari -> pratham;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression11() throws SyntaxException, LexicalException
	{
    	String input = "bansari bansari <- @123+456;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression12() throws SyntaxException, LexicalException
	{
    	String input = "bansari bansari [[x,y]]=a+b;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression13() throws SyntaxException, LexicalException
	{
    	String input = "bansari bansari [[x,y]]=123;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression14() throws SyntaxException, LexicalException
	{
    	String input = "atan(a+b)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression18() throws SyntaxException, LexicalException
	{
    	String input = "atan(a+b)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression15() throws SyntaxException, LexicalException
	{
    	String input = "log(a+b)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}    
	}

	@Test
	public void expression16() throws SyntaxException, LexicalException
	{
    	String input = "bansari (a+b,m+n)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Parse the program  
	}

	@Test
	public void expression17() throws SyntaxException, LexicalException
	{
    	String input = "bansari ";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Parse the program  
	}

	@Test
	public void expression19() throws SyntaxException, LexicalException
	{
    	String input = "atan(a+b)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression20() throws SyntaxException, LexicalException
	{
    	String input = "atan(a+b)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression21() throws SyntaxException, LexicalException
	{
    	String input = "2 + 3 * 4/5 != 2 & 2 & 6";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression22() throws SyntaxException, LexicalException
	{
    	String input = "2?a+b:s+t";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression23() throws SyntaxException, LexicalException
	{
    	String input = "one image img <- Bansari; image [a+b,m+n] img <- @123; image img <- \"Bansu\"; image cb;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression24() throws SyntaxException, LexicalException
	{
    	String input = "one two=123; three [[r,A]]=123+234*678;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression25() throws SyntaxException, LexicalException
	{
    	String input = "one two=123; three [[r,A]];";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	} 	 
	}

	@Test
	public void expression26() throws SyntaxException, LexicalException
	{
    	String input = "one two=123; three [[r,A]]=123+234*678; pratham <- sandip; pratham <- \"nipa\"; pratham <- @123;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression27() throws SyntaxException, LexicalException
	{
    	String input = "one two=123; three [[r,A]]=123+234*678; pratham -> sandip; pratham -> SCREEN;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression28() throws SyntaxException, LexicalException
	{
    	String input = "one boolean pratham; int d; int g=0; boolean val=false; file name=pratham; url fi=\"him\"; file name=@123; image img;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression29() throws SyntaxException, LexicalException
	{
    	String input = "one boolean pratham; int d; int g=0; boolean val=false; file name=pratham; url fi=\"him\"; file name=@123; image img;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression30() throws SyntaxException, LexicalException
	{
    	String input = "a=a+-b;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression31() throws SyntaxException, LexicalException
	{
    	String input = "+x=x";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression32() throws SyntaxException, LexicalException
	{
    	String input = "+++x = +(+(+x)) = x";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression33() throws SyntaxException, LexicalException {
    	String input = "HelloShal92898_$ int shalaka = 5*3;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}


	@Test
	public void expression34() throws SyntaxException, LexicalException
	{
    	String input = "myProg boolean val = false;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();    
	}

	@Test
	public void expression35() throws SyntaxException, LexicalException
	{
    	String input = "bansari bansari [[x,y]];";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}  
	}

	@Test
	public void expression36() throws SyntaxException, LexicalException
	{
    	String input = "+ban?!a+b:c+2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression37() throws SyntaxException, LexicalException
	{
    	String input = "2?";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}	 
	}

	@Test
	public void expression38() throws SyntaxException, LexicalException
	{
    	String input = "2?a:";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}	 
	}

	@Test
	public void expression39() throws SyntaxException, LexicalException
	{
    	String input = "+-!DEF_X?+-!DEF_Y:+-!c";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression40() throws SyntaxException, LexicalException
	{
    	String input = "(+-!DEF_X)?polar_r(234):+-!cart_y(a+b) 345?atan[a+b,c+d]:abc";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Parse the program
	}

	@Test
	public void expression411() throws SyntaxException, LexicalException
	{
    	String input = "+-!DEF_X?+-!DEF_Y:bansari [(a*b),c*d]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();    
	}

	@Test
	public void expression42() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}

	@Test
	public void expression43() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t%a+g-u";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}

	@Test
	public void expression44() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t%a+g-u+";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}  
	}

	@Test
	public void expression45() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t%a+g-u>2<8>=9<=12==0!=9 & ";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void expression46() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t%a+g-u>2<8>=9<=12==0!=9 & x+y";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();  
	}

	@Test
	public void expression47() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t%a+g-u>2<8>=9<=12==0!=9 & x+y & t+s | f+g";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();  
	}

	@Test
	public void expression48() throws SyntaxException, LexicalException
	{
    	String input = "+x*y/t%a+g-u>2<8>=9<=12==0!=9 & x+y & t+s | f+g ? +x*y/t%a+g-u>2<8>=9<=12==0!=9 & x+y & t+s | f+g:+x*y/t%a+g-u>2<8>=9<=12==0!=9 & x+y & t+s | f+g";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();  
	}

	@Test
	public void expression49() throws SyntaxException, LexicalException
	{
    	String input = "a:b:c";
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();  //Parse the program
	}

	@Test
	public void expression50() throws SyntaxException, LexicalException
	{
    	String input = "one image img <- Bansari; image [a+b-c*d,m+n] img <- @123; image img <- \"Bansu\"; image;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}    
	}

	@Test
	public void expression511() throws SyntaxException, LexicalException
	{
    	String input = "when?a?b?c:d:e:f";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Parse the program
	}

	@Test
	public void expression52() throws SyntaxException, LexicalException
	{
    	String input = "a?b:c:d";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}    
	}

	@Test
	public void expression_valid() throws SyntaxException, LexicalException {
    	String input = "+x?(true):sin[false,false]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}

	/*
 	* To check simple unary expressions
 	*/
	@Test
	public void unaryExpression_invalid() throws SyntaxException, LexicalException {
    	String input = "+";
    	//String input = "-";
    	//String input = "!";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.expression();  //Parse the program
    	}catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void unaryExpression_valid() throws SyntaxException, LexicalException {
    	String input = "+1";
    	//String input = "true";
    	//String input = "+++++x";
    	//String input = "+-+-!!true";
    	//String input = "!x!"; //Only !x is a valid expression and the ending ! is handled by program()
    	//String input = "identifier [exp, exp]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void multiExpression_valid() throws SyntaxException, LexicalException {
    	String input = "+1*+1/+1%+1";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void addExpression_valid() throws SyntaxException, LexicalException {
    	String input = "+1*+1/+1%+1++1*+1/+1%+1-+1*+1/+1%+1";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void relExpression_valid() throws SyntaxException, LexicalException {
    	String input = "+1*+1/+1%+1++1*+1/+1%+1-+1*+1/+1%+1<+1*+1/+1%+1++1*+1/+1%+1-+1*+1/+1%+1<=+1*+1/+1%+1++1*+1/+1%+1-+1*+1/+1%+1";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void eqExpression_valid() throws SyntaxException, LexicalException {
    	String input = "true==false==true";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void andExpression_valid() throws SyntaxException, LexicalException {
    	String input = "sin[+1,1230]&x&!!!!!false";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void andExpression_invalid() throws SyntaxException, LexicalException {
    	String input = "sin[+1,0123]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.expression();  //Parse the program
    	}catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void orExpression_valid() throws SyntaxException, LexicalException {
    	String input = "sin[+1,1230]&x&!!!!!false | 123*0/true%(!y)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void program_variableDeclaration_valid() throws SyntaxException, LexicalException {
    	//String input = "prog int k = sin(c+b/2);//comment\nint k = sin(c+b/2);";
    	//String input = "identifier boolean bool;";
    	String input = "identifier boolean bool = sin[+1,1230]&x&!!!!!false | 123*0/true%(!y);";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	@Test
	public void program_imageDeclaration_valid() throws SyntaxException, LexicalException {
    	//String input = "ident image [x, y] ident2 <- \"jugraj\";";
    	//String input = "ident1 image ident2 <- @ (123);";
    	String input = "ident1 image ident2;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	@Test
	public void program_sourceSinkDeclaration_valid() throws SyntaxException, LexicalException {
    	//String input = "ident1 url ident2 = \"me1333\";";
    	String input = "ident1 file ident2 = @ sin[(123), true];";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	@Test
	public void program_assignmentStatement_valid() throws SyntaxException, LexicalException {
    	//String input = "ident1 ident2 [[x,y]] = !!!!!!!123 ;";
    	String input = "ident1 ident2 = true;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	@Test
	public void program_Statement_valid() throws SyntaxException, LexicalException {
    	String input = "ident1 ident2 -> SCREEN;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	//UTSA

	@Test
	public void test1() throws SyntaxException, LexicalException {
    	String input = "x=g+2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Call expression directly.  
	}

	@Test
	public void test2() throws SyntaxException, LexicalException {
    	String input = "x?a:b";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Call expression directly.  
	}

	@Test
	public void test3() throws SyntaxException, LexicalException {
    	String input = "+++++++++++c";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Call expression directly.  
	}

	@Test
	public void test4() throws SyntaxException, LexicalException {
    	String input = "(!x!)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}   
	}

	@Test
	public void test5() throws SyntaxException, LexicalException {
    	String input = "2?(true):(true)";
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Call expression directly.  
	}

	@Test
	public void test6() throws SyntaxException, LexicalException {
    	String input = "+x?(true):atan[false,\"false\"]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void test7() throws SyntaxException, LexicalException {
    	String input = "1234|cos[(a<b),(g!=h)]&x%k==++DEF_X!=Z------!!!!!k[a+b,a%b]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Call expression directly.  
	}

	@Test
	public void test8() throws SyntaxException, LexicalException {
    	String input = "";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.expression();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}   
	}

	@Test
	public void testprogram1() throws SyntaxException, LexicalException {
    	String input = "prog int k=(a+b)/2;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram2() throws SyntaxException, LexicalException {
    	String input = "prog image [(a+b/2),(c*y+67)] k <- @(g+h/2-6);";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram3() throws SyntaxException, LexicalException {
    	//String input = "prog int k = sin(c+b/2);//comment\nint k = sin(c+b/2);";
    	String input = "prog int k = polar_r(c+b/2);//comment starts here\r\n\rint k=polar_r(c+b/2);";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram4() throws SyntaxException, LexicalException {
    	String input = "prog int k = ++x;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram5() throws SyntaxException, LexicalException {
    	String input = "prog url k = \"he is a good boy\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram6() throws SyntaxException, LexicalException {
    	String input = "prog file k = @k[s+t,a+b];";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram7() throws SyntaxException, LexicalException {
    	String input = "prog k [[x,y]] = abs(!!!!!sin[true,(a+b%6/g)]);";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram8() throws SyntaxException, LexicalException {
    	String input = "prog k -> SCREEN;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram9() throws SyntaxException, LexicalException {
    	String input = "prog k <- SCREEN;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void testprogram10() throws SyntaxException, LexicalException {
    	String input = "prog k <- \"I am Utsa\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();  //Call expression directly.  
	}

	@Test
	public void testprogram11() throws SyntaxException, LexicalException {
    	String input = "prog k <- \"I am Utsa\""; // missing semi
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}

	@Test
	public void testprogram12() throws SyntaxException, LexicalException {
    	String input = "prog int k = ;"; // missing semi
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}

	@Test
	public void testprogram13() throws SyntaxException, LexicalException {
    	String input = "prog k[] = a+b ;"; // missing semi
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
	@Test
	public void testShareProgDec() throws SyntaxException, LexicalException{

    	String input = "abcd boolean abcd + - y | A;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}


	@Test
	public void testShareProgSSD() throws SyntaxException, LexicalException{

    	String input = "abcd file idents = \"this is literal\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();

	}

	@Test
	public void testShareStatement() throws SyntaxException, LexicalException
	{
    	String input = "idents stidents -> sinkHere; stidents = + - x|y; stidents ->SCREEN; \n stidents <-\"str literal\"; stidents <-@+-x|y; stidents<-againident;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();

	}

	@Test
	public void testShareImageDec() throws SyntaxException, LexicalException{

    	String input="idents image [+-x|y,+-x|y] idents <- srcIdents; image idents;\n"
            	+ "image idents<-\"StringLiteral\";stidents->sinkHere;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();

	}

	@Test
	public void multExpressionTestShare() throws SyntaxException, LexicalException
	{
    	String input = "1**b * c";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();

	}

	@Test
	public void progImageDec() throws SyntaxException, LexicalException
	{
    	String input = "abcd image [+-x|y,-+y|x] abcd <- @ +-x|y;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	@Test
	public void progSourceSinkDec() throws SyntaxException, LexicalException
	{
    	String input = "idents url abcd = \"this is SSD string literal\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	@Test
	public void progStatement() throws SyntaxException, LexicalException
	{
    	String input = "PLP abcd ([x,y]] = + - x|y; int thisisit; boolean abcd = - + x | y;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void errorDec() throws SyntaxException, LexicalException
	{
    	String input = "thisIdent A";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try
    	{
        	parser.parse();  //Parse the program
    	}
    	catch (SyntaxException e)
    	{
        	show(e);
        	throw e;
    	}
	}

	@Test
	public void orExpressionTest1() throws SyntaxException, LexicalException
	{
    	String input = "a*b | b+c | b/c | d & f";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.OrExpression();
	}

	@Test
	public void andExpressionTest1() throws SyntaxException, LexicalException
	{
    	String input = "a==b&c==d";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.AndExpression();
	}

	@Test
	public void eqExpressionTest() throws SyntaxException, LexicalException
	{
    	String input = "d>c==f<e";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.EqExpression();
	}

	@Test
	public void relExpressionTest() throws SyntaxException, LexicalException
	{
    	String input = "a+b < d-c";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.RelExpression();
	}


	@Test
	public void addExpressionTest() throws SyntaxException, LexicalException
	{
    	String input = "f*d++c+d-d-c+b/a";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.expression();
	}

	@Test
	public void progVarDec() throws SyntaxException, LexicalException
	{
    	String input = "lap int tcs; int abcd; boolean abcd = - + x | y;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();
    	show(scanner);
    	SimpleParser parser = new SimpleParser(scanner);
    	parser.parse();
	}

	public void expression123() throws SyntaxException, LexicalException {
    	String input = "2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();  //Call expression directly.  
	}
	@Test
	public void expression211() throws SyntaxException, LexicalException {
    	String input = "9+2*3";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}
	@Test
	public void expression311() throws SyntaxException, LexicalException {
    	String input = "2?1:2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}
    
	@Test
	public void expression41() throws SyntaxException, LexicalException {
    	String input = "p-+q";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}

	@Test
	public void expression51() throws SyntaxException, LexicalException {
    	String input = "p-+q";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}
	@Test
	public void expression61() throws SyntaxException, LexicalException {
    	String input = "_habdhdb_$ int ident = 4*3;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.expression();
	}
	@Test
	public void expression71() throws SyntaxException, LexicalException {
    	String input = "lo int j = sin [x+y,a+b] ;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();
	}
	@Test
	public void expression81() throws SyntaxException, LexicalException {
    	String input = "hello boolean j = cos [x+y,a+b ; int j = cos [x+y,a+b] ; int j = cos [x+y,a+b] ;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();
    	}
    	catch(SyntaxException e)
    	{
        	show(e);
        	throw(e);
    	}
	}
	@Test
	public void expression91() throws SyntaxException, LexicalException {
    	String input = "hello boolean i = cos [x+y,a+b] ; boolean i = cos [x+y,a+b] ; shaks <- \"shaka\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();
	}
	@Test
	public void expression101() throws SyntaxException, LexicalException {
    	String input = "hello boolean i = cos [x+y,a+b] ; hal = 5+-*9 ; shaks <- \"shaka\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	thrown.expect(SyntaxException.class);
    	try {
        	parser.parse();
    	}
    	catch(SyntaxException e)
    	{
        	show(e);
        	throw(e);
    	}
	}
	@Test
	public void expression111() throws SyntaxException, LexicalException {
    	String input = "hello boolean i = cos [x+y,a+b] ; hal = 5+--++++9 ; shaks <- \"shaka\";";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser parser = new SimpleParser(scanner);  
    	parser.parse();
	}

	@Test
	public void expression0() throws SyntaxException, LexicalException {
    	String input = "Kw_a";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);  
    	SimpleParser.expression();  //Call expression directly.  
	}
    
    
	@Test
	public void testcase1() throws SyntaxException, LexicalException {
    	String input = "2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}

    
	@Test
	public void testcase2() throws SyntaxException, LexicalException {
    	String input = "a bcd";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase3() throws SyntaxException, LexicalException {
    	String input = "cart_x cart_y";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase4() throws SyntaxException, LexicalException {
    	String input = "prog int 2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase5() throws SyntaxException, LexicalException {
    	String input = "prog image[filepng,png] imageName <- imagepng"; //Error as there is not semicolon for ending the statement
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	//SimpleParser.parse();
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase6() throws SyntaxException, LexicalException {
    	String input = "imageDeclaration image[\"abcd\"] "; //Should fail for image[""]
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}

    
	@Test
	public void testcase7() throws SyntaxException, LexicalException {
    	String input = "prog image[filepng,png] imageName <- imagepng; \n boolean ab=true;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();
	}
    
	@Test
	public void testcase8() throws SyntaxException, LexicalException {
    	String input = "prog image[filepng,jpg] imageName;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();
	}
    
	@Test
	public void testcase9() throws SyntaxException, LexicalException {
    	String input = "prog image imageName;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();
	}
    
	@Test
	public void testcase10() throws SyntaxException, LexicalException {
    	String input = "prog @expr k=12;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase11() throws SyntaxException, LexicalException {
    	String input = "prog \"abcded\" boolean a=true;";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase12() throws SyntaxException, LexicalException {
    	String input = "isBoolean boolean ab=true; boolean cd==true; abcd=true ? return true: return false;"; //Should fail for ==
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase13() throws SyntaxException, LexicalException {
    	String input = "isBoolean boolean ab=true; boolean cd==true; abcd=true ? return true: return false;"; //Should fail for =
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
    
	@Test
	public void testcase14() throws SyntaxException, LexicalException {
    	String input = "isUrl url filepng=\"abcd\"; \n @expr=12; url awesome=@expr; \n url filepng=abcdefg";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
   	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    
	@Test
	public void testcase15() throws SyntaxException, LexicalException {
    	String input = "isUrl url filepng=\"abcd\" \n @expr=12; url awesome=@expr; \n url filepng=abcdefg"; //Should fail for ; in line one
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    

	@Test
	public void testcase16() throws SyntaxException, LexicalException {
    	String input = "isFile file filepng=\"abcd\"; \n @expr=12; url filepng=@expr; \n url filepng=abcdefg";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    
	@Test
	public void testcase17() throws SyntaxException, LexicalException {
    	String input =  "isFile file filepng=\"abcd\" \n @expr=12; url filepng=@expr; \n url filepng=abcdefg";  //Should fail for ; in line one
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    
	@Test
	public void testcase18() throws SyntaxException, LexicalException {
    	String input =  "isurl url urlname;";  //Should fail for url as url can only be initalised
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    
	@Test
	public void testcase19() throws SyntaxException, LexicalException {
    	String input =  "declaration int xyz;\n boolean zya;\n image imagename;";  
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();  //Parse the program
	}
    
	@Test
	public void testcase20() throws SyntaxException, LexicalException {
    	String input =  "imageProgram image imageName;"
            	+ "\n imageName->abcdpng; "
            	+ "\n imageName -> SCREEN; "
            	+ "\n imageName <- \"awesome\";"
            	+ "\n imageName <- @express; \n"
            	+ "\n imageName <- abcdpng;";  // Image related Test cases
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();  //Parse the program
	}
    
	@Test
	public void testcase21() throws SyntaxException, LexicalException {
    	String input =  "assign int abc=123456;\n"
            	+ "abc[[x,y]]=123456;\n"
            	+ "abc[[r,A]]=123244;\n";//Assignment statement
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();  //Parse the program
	}


	@Test
	public void testcase22() throws SyntaxException, LexicalException {
    	String input =  "assign int abc=123456;\n"
            	+ "abc[[x]]=123456;\n"
            	+ "abc[[r,A]]=123244;\n";  //Error
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    
	@Test
	public void testcase23() throws SyntaxException, LexicalException {
    	String input =  "assign int abc=123456;\n"
            	+ "abc[[x,y]]=123456;\n"
            	+ "abc[[A]]=123244;\n";//Error
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
    	SimpleParser.parse();  //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}  
	}
    
	// Function Name Testcases
	@Test
	public void testcase24() throws SyntaxException, LexicalException {
    	String input =  " sin \n cos \n atan \n abs \n cart_x \n cart_y \n polar_a \n polar_r\n";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.Functionname();  //Parse the program
	}
    
	//LhsSelector ::= LSQUARE  ( XySelector  | RaSelector  )   RSQUARE
    
	@Test
	public void testcase25() throws SyntaxException, LexicalException {
    	String input =  "[x,y] \n [r,A] \n []";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.lhsSelector();  //Parse the program
	}
    
	@Test
	public void testcase26() throws SyntaxException, LexicalException {
    	String input =  "[x,]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.lhsSelector();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
   	 
	}
    
	@Test
	public void testcase27() throws SyntaxException, LexicalException {
    	String input =  "[,A]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.lhsSelector();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
   	 
	}
	//    	XySelector ::= KW_x COMMA KW_y
	@Test
	public void testcase28() throws SyntaxException, LexicalException {
    	String input =  "x,y";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.XySelector();
	}
    
	@Test
	public void testcase29() throws SyntaxException, LexicalException {
    	String input =  "x";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.lhsSelector();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
	//    	RaSelector ::= KW_r COMMA KW_A
	@Test
	public void testcase30() throws SyntaxException, LexicalException {
    	String input =  "r,A";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.RaSelector();
	}
    
	@Test
	public void testcase31() throws SyntaxException, LexicalException {
    	String input =  ",A";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.RaSelector();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    

	@Test
	public void testcase32() throws SyntaxException, LexicalException {
    	String input =  "r,";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.RaSelector();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	// Expression
	@Test
	public void testcase33() throws SyntaxException, LexicalException {
    	String input =  "x y r a X Y Z A R DEF_X DEF_Y";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase34() throws SyntaxException, LexicalException {
    	String input =  "5+3*4+5%3";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	/**
	UnaryExpression ::= OP_PLUS UnaryExpression
         	| OP_MINUS UnaryExpression
         	| UnaryExpressionNotPlusMinus
        	 
	UnaryExpressionNotPlusMinus ::=  OP_EXCL  UnaryExpression  | Primary
| IdentOrPixelSelectorExpression | KW_x | KW_y | KW_r | KW_a | KW_X | KW_Y | KW_Z | KW_A | KW_R | KW_DEF_X | KW_DEF_Y
    
	Primary ::= INTEGER_LITERAL | LPAREN Expression RPAREN | FunctionApplication | BOOLEAN_LITERAL
    
	IdentOrPixelSelectorExpression::=  IDENTIFIER LSQUARE Selector RSQUARE   | IDENTIFIER
    
	FunctionApplication ::= FunctionName LPAREN Expression RPAREN  | FunctionName  LSQUARE Selector RSQUARE	 
    
	FunctionName ::= KW_sin | KW_cos | KW_atan | KW_abs | KW_cart_x | KW_cart_y | KW_polar_a | KW_polar_r
    
	Selector ::=  Expression COMMA Expression  

	*/
    
	@Test
	public void testcase35() throws SyntaxException, LexicalException {
    	String input =  "x+y+y+r+X-Y-Z-A+R+DEF_X+DEF_Y+!(5+6+(true+false))\n"
            	+ " abcd[(x+tyxzsd),(12+123+45)] \n"
            	+ " sin(x+y) cos(x+y) atan(x+y) abs(x+y) cart_x(x+y) cart_y(x+y) polar_a(x+y) polar_r(r+a)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
    
	@Test
	public void testcase36() throws SyntaxException, LexicalException {
    	String input =  "r==IdentOrPixelSelectorExpression[5,6]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
   	 
	@Test
	public void testcase37() throws SyntaxException, LexicalException {
    	String input =  "sin(x)+cos(x)-atan(x)+cart_x(x)+cart_y(y)+polar_a(a)+polar_r(a)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase38() throws SyntaxException, LexicalException {
    	String input =  "sin(x)+cos(x)-atan(x)+cart_x(x)+cart_y(y)+polar_a(a)+polar_r(a)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase39() throws SyntaxException, LexicalException {
    	String input =  "sin[(x+y),(x+z)]+cos[(x+y),(x+z)]+polar_r[vyz+xx,z+xx]";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
    
	/**
	OrExpression ::= AndExpression   (  OP_OR  AndExpression)*
    
	AndExpression ::= EqExpression ( OP_AND  EqExpression )*

 	EqExpression ::= RelExpression  (  (OP_EQ | OP_NEQ )  RelExpression )*
	 
 	RelExpression ::= AddExpression (  ( OP_LT  | OP_GT |  OP_LE  | OP_GE )   AddExpression)*
	 
 	AddExpression ::= MultExpression   (  (OP_PLUS | OP_MINUS ) MultExpression )*

 	MultExpression := UnaryExpression ( ( OP_TIMES | OP_DIV  | OP_MOD ) UnaryExpression )*
	 
  	UnaryExpression ::= OP_PLUS UnaryExpression
         	| OP_MINUS UnaryExpression
         	| UnaryExpressionNotPlusMinus
        	 
	UnaryExpressionNotPlusMinus ::=  OP_EXCL  UnaryExpression  | Primary
| IdentOrPixelSelectorExpression | KW_x | KW_y | KW_r | KW_a | KW_X | KW_Y | KW_Z | KW_A | KW_R | KW_DEF_X | KW_DEF_Y
    
	Primary ::= INTEGER_LITERAL | LPAREN Expression RPAREN | FunctionApplication | BOOLEAN_LITERAL
    
	IdentOrPixelSelectorExpression::=  IDENTIFIER LSQUARE Selector RSQUARE   | IDENTIFIER
    
	FunctionApplication ::= FunctionName LPAREN Expression RPAREN  | FunctionName  LSQUARE Selector RSQUARE	 
    
	FunctionName ::= KW_sin | KW_cos | KW_atan | KW_abs | KW_cart_x | KW_cart_y | KW_polar_a | KW_polar_r
    
	Selector ::=  Expression COMMA Expression  
 
	*/
    
	@Test
	public void testcase40() throws SyntaxException, LexicalException {
    	String input =  "y*Y";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase41() throws SyntaxException, LexicalException {
    	String input =  "y*+"; //Should through an error
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.expression();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase42() throws SyntaxException, LexicalException {
    	String input =  "y/(x+Y*Y)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase43() throws SyntaxException, LexicalException {
    	String input =  "y/(x+Y*Y-sin(x+y)*cos(x+y)%2)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase44() throws SyntaxException, LexicalException {
    	String input =  "y/(x+Y*Y-sin(x+y)*cos(x+y)%2)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase45() throws SyntaxException, LexicalException {
    	String input =  "5+5-(x+y)*2%2";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase46() throws SyntaxException, LexicalException {
    	String input =  "(5+10)>(6*2+4+sin(x)-atan(y)-x/y/z)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase47() throws SyntaxException, LexicalException {
    	String input =  "(5+10)<(6/2/3/4*2)";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase48() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))<=(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();
	}
    
	@Test
	public void testcase49() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))<=(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z,y,x))";  //Should fail as () can hold only expression
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.expression();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase50() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))>=(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z,y,x))";  //Should fail as () can hold only expression
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.expression();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase51() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))==(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase52() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))!=(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase53() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))&(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))|false";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase54() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))|(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))&true";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    

	@Test
	public void testcase55() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))||(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))&true";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.expression();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase56() throws SyntaxException, LexicalException {
    	String input =  "(6*2/23/4*22*sin(x))|(abs(6*2*12)+cart_x[x,y]+cart_y[(6/23),(7/23)]+polar_a[6/2/2,2/3/4]+polar_r(z))&&true";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	thrown.expect(SyntaxException.class);
    	try {
        	SimpleParser.expression();   //Parse the program
    	}
    	catch (SyntaxException e) {
        	show(e);
        	throw e;
    	}
	}
    
	@Test
	public void testcase57() throws SyntaxException, LexicalException {
    	String input =  "x idntifier";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.expression();   //Parse the program
	}
    
	@Test
	public void testcase58() throws SyntaxException, LexicalException {
    	String input =  "prog int abcd=(1000*1000+2);\n int b=(100-2+3);";
    	show(input);
    	Scanner scanner = new Scanner(input).scan();  
    	show(scanner);   
    	SimpleParser SimpleParser = new SimpleParser(scanner);
    	SimpleParser.parse();   //Parse the program
	}
    
    @Test
    public void expression01() throws SyntaxException, LexicalException {
   	 String input = "2";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  
   	 show(scanner);   
   	 SimpleParser parser = new SimpleParser(scanner);  
   	 parser.expression();  //Call expression directly.  
    }
    
    @Test
    public void expression02() throws SyntaxException, LexicalException {
   	 String input = "x != y";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  
   	 show(scanner);   
   	 SimpleParser parser = new SimpleParser(scanner);  
   	 parser.expression();  //Call expression directly.  
    }
    
    @Test
    public void expressioninfra() throws SyntaxException, LexicalException {
   	 String input = "a:b:c";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  
   	 show(scanner);   
   	 SimpleParser parser = new SimpleParser(scanner);  
   	 parser.expression();    
    }
    
    @Test
    public void andExpressionTest() throws SyntaxException, LexicalException {
   	 String input = "x / y - x * y >= x / y - x * y != x / y - x * y >= x / y - x * y & x / y - x * y >= x / y - x * y != x / y - x * y >= x / y - x * y";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.expression();
    }
    
    @Test
    public void orExpressionTest() throws SyntaxException, LexicalException {
   	 String input = "x / y - x * y >= x / y - x * y != x / y - x * y >= x / y - x * y & x / y - x * y >= x / y - x * y != x / y - x * y >= x / y - x * y & x / y - x * y >= x / y - x * y != x / y - x * y >= x / y - x * y & x / y - x * y >= x / y - x * y != x / y - x * y >= x / y - x * y";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.expression();
    }
    
    @Test
    public void expressionTest2() throws SyntaxException, LexicalException {
   	 String input = "a = (c > d) ? e : f ";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.expression();
    }
    
    @Test
    public void imageDeclarationTest() throws SyntaxException, LexicalException {
   	 String input = "image [ 3 + 4, 5 + 6 ]flag <- \"nikita\"";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.ImageDeclaration();
    }
    
    @Test
    public void expressionTest3() throws SyntaxException, LexicalException {
   	 String input = "flag1 + flag2";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.expression();
    }
    
    @Test
    public void identOrPixelSelectorExpressionTest() throws SyntaxException, LexicalException {
   	 String input = "flag1";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.IndentOrPixSelExpr();
    }
    
    @Test
    public void identOrPixelSelectorExpressionTes2() throws SyntaxException, LexicalException {
   	 String input = "flag1[1 + 2, 3 + 4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.IndentOrPixSelExpr();
    }
    
    @Test
    public void selectorTest() throws SyntaxException, LexicalException {
   	 String input = "1 + 2, 3 + 4";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.Selector();
    }
    
    @Test
    public void unaryExpressionNotPlusMinusTest() throws SyntaxException, LexicalException {
   	 String input = "flag1[1 + 2, 3 + 4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.UnaryExpression();
    }
    
    @Test
    public void unaryExpressionTest() throws SyntaxException, LexicalException {
   	 String input = "flag1[1 + 2, 3 + 4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.UnaryExpression();
    }
    
    @Test
    public void sourceSinkDeclarationTest() throws SyntaxException, LexicalException {
   	 String input = "url flag = \"nikita\"";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.SourceSinkDeclaration();
    }
    
    @Test
    public void functionApplicationTest1() throws SyntaxException, LexicalException {
   	 String input = "polar_r (num1 + num2)";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.FunctionApplication();
    }
    
    @Test
    public void functionApplicationTest2() throws SyntaxException, LexicalException {
   	 String input = "cos [num1 + num2, num3 - num4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.FunctionApplication();
    }
    
    @Test
    public void functionApplicationTest3() throws SyntaxException, LexicalException {
   	 String input = "sin [&, num3 - num4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.FunctionApplication();// Parse the program
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void primaryTest1() throws SyntaxException, LexicalException {
   	 String input = "1234";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.Primary();
    }
    
    @Test
    public void primaryTest2() throws SyntaxException, LexicalException {
   	 String input = "(num1 + num2)";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.Primary();
    }
    
    @Test
    public void primaryTest3() throws SyntaxException, LexicalException {
   	 String input = "(num1 + num2";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.Primary();// Parse the program
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void primaryTest4() throws SyntaxException, LexicalException {
   	 String input = "(&)";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.Primary();// Parse the program
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void primaryTest5() throws SyntaxException, LexicalException {
   	 String input = "sin (num1 + num2)";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.Primary();
    }
    
    @Test
    public void primaryTest6() throws SyntaxException, LexicalException {
   	 String input = "nikita (num1 + num2)";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.Primary();
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void unaryExpressionNotPlusMinusTest1() throws SyntaxException, LexicalException {
   	 String input = "DEF_Y";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.UnaryExpression();
    }
    
    @Test
    public void unaryExpressionNotPlusMinusTest2() throws SyntaxException, LexicalException {
   	 String input = "sin [";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.UnaryExpression();
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void identOrPixelSelectorExpressionTest1() throws SyntaxException, LexicalException {
   	 String input = "nikita";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.IndentOrPixSelExpr();
    }
    
    @Test
    public void identOrPixelSelectorExpressionTest2() throws SyntaxException, LexicalException {
   	 String input = "nikita[num1 + num2, num3 - num4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.IndentOrPixSelExpr();
    }
    
    @Test
    public void identOrPixelSelectorExpressionTest3() throws SyntaxException, LexicalException {
   	 String input = "nikita[num1 + num2 num3 - num4]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.IndentOrPixSelExpr();
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void unaryExpressionNotPlusMinusTest3() throws SyntaxException, LexicalException {
   	 String input = "!-x";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.UnaryExpression();
    }
    
    @Test
    public void unaryExpressionTest1() throws SyntaxException, LexicalException {
   	 String input = "+!x";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.UnaryExpression();
    }
    
    @Test
    public void multExpressionTest1() throws SyntaxException, LexicalException {
   	 String input = "+!x%-3";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.MultExpression();
    }
    
    @Test
    public void wholeTest1() throws SyntaxException, LexicalException {
   	 String input = "nikitaIdentifier";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.parse();
    }
    
    @Test
    public void wholeTest2() throws SyntaxException, LexicalException {
   	 String input = "nikita int flag = num1 + num2;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.parse();
    }
    
    @Test
    public void wholeTest3() throws SyntaxException, LexicalException {
   	 String input = "nikita boolean flag = num1 + num2;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.parse();
    }
    
    @Test
    public void wholeTest4() throws SyntaxException, LexicalException {
   	 String input = "nikita boolean flag;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.parse();
    }
    
    @Test
    public void wholeTest5() throws SyntaxException, LexicalException {
   	 String input = "nikita boolean flag";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.parse();
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void wholeTest6() throws SyntaxException, LexicalException {
   	 String input = "nikita float flag";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.parse();
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void selectorTestDashboard1() throws SyntaxException, LexicalException {
   	 String input = "a*b,c*d";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.Selector();
    }
    
    @Test
    public void selectorTestDashboard2() throws SyntaxException, LexicalException {
   	 String input = "(+-!DEF_X)?polar_r(234):+-!cart_y(a+b) 345?atan[a+b,c+d]:abc";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 thrown.expect(SyntaxException.class);
   	 try {
   		 parser.parse();
   	 } catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void selectorTestDashboard8() throws SyntaxException, LexicalException {
   	 String input = "xx=x";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 
   		 parser.expression();
   	 
    }
    
    @Test
    public void selectorTestDashboard3() throws SyntaxException, LexicalException {
   	 String input = "(a*b),c*d";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.Selector();
    }
    
    @Test
    public void expressionDashboardRandom() throws SyntaxException, LexicalException {
   	 String input = "++++x";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.expression();
    }
    
    @Test
    public void expressionDashboardRandom1() throws SyntaxException, LexicalException {
   	 String input = "prog int k\n;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();
   	 show(scanner);
   	 SimpleParser parser = new SimpleParser(scanner);
   	 parser.expression();
    }
    
    @Test
    public void testvarDec() throws LexicalException, SyntaxException {
   	 String input = "int _arnav=a";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Declaration();
    }
    
    @Test
    public void testimageDec() throws LexicalException, SyntaxException {
   	 String input = "image [a,y] arnav <- \"arnav\"";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Declaration();
    }
    
    @Test
    public void testimageDec2() throws LexicalException, SyntaxException {
   	 String input = "image [a,y] arnav <- @ arnavIdent [R,DEF_X]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Declaration();
    }
    
    @Test
    public void testmultExp() throws LexicalException, SyntaxException {
   	 String input = "+++++! 4 *-x /+R";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.MultExpression();
    }
    
    @Test
    public void teststatement1() throws LexicalException, SyntaxException {
   	 String input = "_abc -> SCREEN";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Statement();
    }
    
    @Test
    public void teststatement2() throws LexicalException, SyntaxException {
   	 String input = "_abc <- screen";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Statement();
    }
    
    @Test
    public void teststatement3() throws LexicalException, SyntaxException {
   	 String input = "_abc <- \"arnav\"";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Statement();
    }
    
    @Test
    public void teststatement4() throws LexicalException, SyntaxException {
   	 String input = "_abc <- @ true";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Statement();
    }
    
    @Test
    public void teststatement5() throws LexicalException, SyntaxException {
   	 String input = "_abc = a";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Statement();
    }
    
    @Test
    public void teststatement6() throws LexicalException, SyntaxException {
   	 String input = "_abc [[x,y]] = a";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.Statement();
    }
    
    @Test
    public void teststatement7() throws LexicalException, SyntaxException {
   	 String input = "_abc [[x,A]] = a";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 thrown.expect(SyntaxException.class);
   	 try {
   	 parser.Statement();  //Parse the program
   	 }
   	 catch (SyntaxException e) {
   		 show(e);
   		 throw e;
   	 }
    }
    
    @Test
    public void testprogram1ghc() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    } //
    
    @Test
    public void testprogram209() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }
    
    @Test
    public void testprogram3999() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN;_abc <- \"arnav\";";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }
    
    @Test
    public void testprogram49() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN; _pqr [[x,y]] = a;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }
    
    @Test
    public void testprogram59() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN; _abc <- @ false;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }
    
    @Test
    public void testprogram69() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN; _abc <- @ true;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }
    
    @Test
    public void testprogram79() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN; _abc <- \"_abcarnav\";";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }
    
    @Test
    public void testprogram89() throws LexicalException, SyntaxException {
   	 String input = "arnav image _abc;_abc -> SCREEN;_abc <- _abc;";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
   	 show(scanner);   //Display the Scanner
   	 SimpleParser parser = new SimpleParser(scanner);  //
   	 parser.program();
    }


    @Test
    public void expression299() throws SyntaxException, LexicalException {
   	 String input = "a:b:c";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  
   	 show(scanner);   
   	 SimpleParser parser = new SimpleParser(scanner);  
   	 parser.expression();  //Call expression directly.  
    }
    
    @Test
    public void expression399() throws SyntaxException, LexicalException {
   	 String input = "++++x";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  
   	 show(scanner);   
   	 SimpleParser parser = new SimpleParser(scanner);  
   	 parser.expression();  //Call expression directly.  
    }
    
    @Test
    public void expression499() throws SyntaxException, LexicalException {
   	 String input = "cos(a+b)]";
   	 show(input);
   	 Scanner scanner = new Scanner(input).scan();  
   	 show(scanner);   
   	 SimpleParser parser = new SimpleParser(scanner);  
   	 parser.expression();  //Call expression directly.  
    }

    
}

