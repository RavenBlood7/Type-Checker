import lexer.Lexer;
import parser.Parser;
import java.io.*;
import java.util.Scanner;

//here is the main file which we will run on the demo date for typechecking
public class Main
{
	public static void main(String[] args) throws IOException
	{
			if(args.length == 0) {
				System.out.println("Please put in the name of the file you would like to Lexically Analyse.");
			} else {
				//A Lexer takes in a fileName of the file that needs to be processed.
				//This is the source file of spl code.
				//A lexer then throws an error or produces a file called
				//"lexeroutput" in the directory. This file is ready to be processed
				//by the parser.
				Lexer lex = new Lexer(args[0]);
				///System.out.print(lex);

				//A Parser reads the "lexeroutput" file and produces a syntax tree
				//which has a root and a infoTable already built in. These two
				//attributes are accessed as follows:
				//	prsr.getRoot()
				//	prsr.getTable()
				Parser prsr = new Parser();
				if (prsr.parse())
				{
					///prsr.writeToTreeFile();
					///prsr.writeToPrunedTreeFile();
				}

				//The resulting tree and table is then passed to the typechecker
				//which does the algorithm and displays appropriate messages
				//TypeChecker tc = new TypeChecker();
				//tc.doTypeChecking(prsr.getRoot(), prsr.getTable());
			}

	}
}
