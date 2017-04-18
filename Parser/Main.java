import java.io.*;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		//testing the new parser tokenList
		/*TokenList tl = new TokenList("lexeroutput");
		System.out.println(tl);
		while (!tl.isEmpty()){
			TokenNode temp = tl.removeFromHead();
			System.out.println("Removed: " +  temp.tokenClass);
		}
		System.out.println(tl);
	}*/ 
		Parser prsr = new Parser();
		
		if(prsr.parse()) {
			String pTree = "ParseTree";
			Scanner scan = new Scanner(System.in);
			FileWriter fw = new FileWriter(pTree);
			fw.write(prsr.toString());
			System.out.println("Parse Tree saved to file '" + pTree + "'");
			scan.close();
			fw.close(); 


			prsr.prune();
			pTree = "PrunedParseTree";
			scan = new Scanner(System.in);
			fw = new FileWriter(pTree);
			fw.write(prsr.toString());
			System.out.println("Pruned Parse Tree saved to file '" + pTree + "'");
			scan.close();
			fw.close();
		}
		
		

	}


}

