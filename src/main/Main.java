

import parser.InfoTable;
import scope_crawler.ScopeAnalyser;
import type_crawler.TypeChecker;
import lexer.Lexer;
import parser.Parser;
import java.io.*;

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
					prsr.writeToTreeFile();
					prsr.writeToPrunedTreeFile();
				}

				//The resulting tree and table is then passed to the typechecker
				//which does the algorithm and displays appropriate messages

                InfoTable it = prsr.getTable();
                System.out.println("\n\nThe symbol table before typechecker: \n" + prsr.getTable());

			//	TypeChecker tc = new TypeChecker();
			//	tc.doTypeChecking(prsr.getRoot(), prsr.getTable());
              //  System.out.println("\n\nThe symbol table after typechecker: \n" + prsr.getTable());

				//The resulting tree is then also checked for proper scope
			InfoTable myIT = new InfoTable();
				myIT.insert(105, "Q", "none");
				myIT.insert(104, "P", "none");
				myIT.insert(103, "C", "none");
				myIT.insert(63, "C", "none");
				myIT.insert(62, "I", "none");
				myIT.insert(61, "A", "none");
				myIT.insert(56, "T", "none");
				myIT.insert(55, "S", "none");
				myIT.insert(54, "user-defined name", "none");
				myIT.insert(57, "assignment operator", "none");
				myIT.insert(66, "U", "none");
				myIT.insert(59, "X", "none");
				myIT.insert(58, "integer", "none");
				myIT.insert(102, "I", "none");
				myIT.insert(101, "Z", "none");
				myIT.insert(65, "keyword", "none");
				myIT.insert(68, "N", "none");
				myIT.insert(67, "user-defined name", "none");
				myIT.insert(69, "assignment operator", "none");
				myIT.insert(70, "integer", "none");
				myIT.insert(73, "N", "none");
				myIT.insert(72, "user-defined name", "none");
				myIT.insert(74, "boolean operator", "none");
				myIT.insert(76, "N", "none");
				myIT.insert(75, "user-defined name", "none");
				myIT.insert(79, "N", "none");
				myIT.insert(78, "user-defined name", "none");
				myIT.insert(80, "Q", "none");
				myIT.insert(81, "Q", "none");
				myIT.insert(84, "Q", "none");
				myIT.insert(83, "Q", "none");
				myIT.insert(86, "Q", "none");
				myIT.insert(99, "Q", "none");
				myIT.insert(98, "Q", "none");
				myIT.insert(97, "Q", "none");
				myIT.insert(92, "Q", "none");
				myIT.insert(91, "Q", "none");
				myIT.insert(90, "Q", "none");
				myIT.insert(93, "Q", "none");
				myIT.insert(96, "Q", "none");
				myIT.insert(95, "Q", "none");
				myIT.insert(94, "Q", "none");

				//	ScopeAnalyser sa = new ScopeAnalyser();
				//sa.doScopeAnalysis(prsr.getRoot(), it);

				//System.out.println("\n\nThe symbol table after scope analysis: \n" + prsr.getTable());
			}

	}
}
