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

	//set Junit to be able to catch exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	//To make it easy to print objects and turn this output on and off
	static final boolean doPrint = true;
	private void show(Object input) {
		if (doPrint) {
			System.out.println(input.toString());
		}
	}



	/**
	 * Simple test case with an empty program.  This test 
	 * expects an SyntaxException because all legal programs must
	 * have at least an identifier
	 *   
	 * @throws LexicalException
	 * @throws SyntaxException 
	 */
	@Test
	public void testEmpty() throws LexicalException, SyntaxException {
		String input = "";  //The input is the empty string.  This is not legal
		show(input);        //Display the input 
		Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
		//show(scanner);   //Display the Scanner
		SimpleParser parser = new SimpleParser(scanner);  //Create a parser
		thrown.expect(SyntaxException.class);
		try {
		parser.parse();  //Parse the program
		}
		catch (SyntaxException e) {
			show(e);
			throw e;
		}
	}

	
	/** Another example.  This is a legal program and should pass when 
	 * your parser is implemented.
	 * 
	 * @throws LexicalException
	 * @throws SyntaxException
	 */

	@Test
	public void testDec1() throws LexicalException, SyntaxException {
		String input = "prog "
		        + " int k; "
		        + " image[56/34,67/34] cgh ; "
		        + " image svf <- @(x+y+2/4*5*r*Z);"
		        + " image vkdj ; "
		        + " boolean k;"
		        + " int k = a + 76/9 * 67; "
		        + " url vgjvbhvj = \"vhvjhm\"; "
		        + " url fbdjob = vjsdlv ;"
		        + " file file_name = @(x+y+a*X) ; "
		        + " //this is a commment  ; ";
		show(input);
		Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
		//show(scanner);   //Display the Scanner
		SimpleParser parser = new SimpleParser(scanner);  //
		parser.parse();
	}
	//positive test cases
	@Test
    public void statement() throws LexicalException, SyntaxException {
        String input = "prog sai [[x,y]] = (67/x*y);"
                + "sgf[[r,A]] = 534657+x ;"
                + "nath -> screen ;"
                + "gsf ->vfgh ;"
                + "reddy <- @x+y ;"
                + "dtgbg = u[534/x,67+y];";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void expressioncheck() throws LexicalException, SyntaxException {
        String input = "65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.expression();
    }
	@Test
    public void testDec4() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec5() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec6() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec7() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec8() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec9() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec10() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec11() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	@Test
    public void testDec12() throws LexicalException, SyntaxException {
        String input = "prog int k = 65/7;";
        show(input);
        Scanner scanner = new Scanner(input).scan();  //Create a Scanner and initialize it
        //show(scanner);   //Display the Scanner
        SimpleParser parser = new SimpleParser(scanner);  //
        parser.parse();
    }
	/**
	 * This example invokes the method for expression directly. 
	 * Effectively, we are viewing Expression as the start
	 * symbol of a sub-language.
	 *  
	 * Although a compiler will always call the parse() method,
	 * invoking others is useful to support incremental development.  
	 * We will only invoke expression directly, but 
	 * following this example with others is recommended.  
	 * 
	 * @throws SyntaxException
	 * @throws LexicalException
	 */
	@Test
	public void expression1() throws SyntaxException, LexicalException {
		String input = "2+3 / 4/0";
		show(input);
		Scanner scanner = new Scanner(input).scan();  
		//show(scanner);   
		SimpleParser parser = new SimpleParser(scanner);  
		parser.expression();  //Call expression directly.  
	}


	
	}

