import java.io.*;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		/*
	//testing tokenlist
		TokenList tl1= new TokenList();
		
		tl1.addToken("variable", "hello1");
		System.out.println("The list: \n" + tl1 + "\n");
		
		tl1.addToken("variable", "hello2");
		System.out.println("The list: \n" + tl1 + "\n");
		
		tl1.addToken("condition", "if");
		System.out.println("The list: \n" + tl1 + "\n");
		
		tl1.addToken("number", "12");
		System.out.println("The list: \n" + tl1 + "\n");
		*/
		
	//testing context
		Context c1 = new Context();
		/*//getCharIndex
		System.out.println("a: " + c1.getCharIndex('a'));
		System.out.println("z: " + c1.getCharIndex('z'));
		System.out.println("0: " + c1.getCharIndex('0'));
		System.out.println("1: " + c1.getCharIndex('1'));
		System.out.println("9: " + c1.getCharIndex('9'));
		System.out.println("\\n: " + c1.getCharIndex('\n'));
		System.out.println("\\r: " + c1.getCharIndex('\r'));
		System.out.println("space: " + c1.getCharIndex(' '));
		System.out.println("\": " + c1.getCharIndex('\"'));
		System.out.println("@: " + c1.getCharIndex('@'));
		*/
		
		//getNextState
		int curstate = 0;
			//integers		
			/*System.out.println("integer: " + c1.getNextState(curstate, '-'));
			System.out.println("integer: " + c1.getNextState(curstate, '1'));
			System.out.println("integer: " + c1.getNextState(curstate, '2'));
			System.out.println("integer: " + c1.getNextState(curstate, ' '));
			curstate = 0;
			System.out.println("integer: " + c1.getNextState(curstate, '1'));
			System.out.println("integer: " + c1.getNextState(curstate, '2'));
			//shortstrings
			curstate = 0;
			curstate = c1.getNextState(curstate, '"');			
			System.out.println("ss1: " + curstate);
			curstate = c1.getNextState(curstate, '"');		
			System.out.println("ss: " + curstate);
			curstate = 0;
			curstate = c1.getNextState(curstate, '"');		
			System.out.println("ss1: " + curstate);
			curstate = c1.getNextState(curstate, ' ');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, '"');
			curstate = 0;		
			System.out.println("ss1: " + curstate);
			curstate = c1.getNextState(curstate, '"');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, '4');		
			System.out.println("ss: " + curstate);
			curstate = c1.getNextState(curstate, '"');		
			System.out.println("ss: " + curstate);
			//user defined	*/		
			/*curstate = 0;
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			curstate = c1.getNextState(curstate, 'n');		
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			curstate = c1.getNextState(curstate, 'd');		
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			curstate = c1.getNextState(curstate, 'y');		
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			curstate = c1.getNextState(curstate, ' ');	
			curstate = 0;
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			curstate = c1.getNextState(curstate, 'a');		
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			curstate = c1.getNextState(curstate, ';');		
			System.out.println("udn1: " + curstate + ":"+ c1.getDescription(curstate));
			*/
			if(args.length == 0) {
				System.out.println("Please put in the name of the file you would like to Lexically Analyse.");
			} else {
				Lexer lex = new Lexer(args[0]);
				//System.out.print(lex);

				String file = "../lexeroutput";
				Scanner scan = new Scanner(System.in);

				FileWriter fw = new FileWriter(file);
				fw.write(lex.toString());
				System.out.println("Lexical Analysis output saved to file '"+ file +"'");
				scan.close();
				fw.close();
			}
			

			//error states
		//isAccepting
		//isKeyword
		//getDescription
	}
}