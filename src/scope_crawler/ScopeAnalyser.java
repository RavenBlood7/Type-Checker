package scope_crawler;

import parser.InfoTable;
import parser.TreeNode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScopeAnalyser {
    SymbolTable stable = new SymbolTable();
    int newScope = 0;

    private class StringException extends Throwable
    {
        public String e;
        public StringException(String s)
        {
            e = "ERROR: " + s;
        }
    }

    public void doScopeAnalysis(TreeNode node, InfoTable table)
    {
        System.out.println("\nStarting scope analysis process: ");
        System.out.println("--------------------------------------------------- ");
        try {
            recursiveScope(node, table, 0);
            System.out.println("\nSuccessfully completed scope analysis process: ");
            System.out.println("\t writing symbol table to file... ");
            toFile(table);
            System.out.println("--------------------------------------------------- ");

        }
        catch (StringException e)
        {
            System.out.println("Scope Analysis Error: " + e.e);
            System.out.println("Exiting scope checker...\n");
        }
    }

    private void recursiveScope(TreeNode node, InfoTable table, int curScope) throws StringException
    {
        // Q
        // P
        // C
        // D
        // I
        //// O input() is assigning
        // A
        // U
        // T
        // W
        // L
        // X
        // B
        // V
        if (       node.tokenClass.equals("Q")
                || node.tokenClass.equals("P")
                || node.tokenClass.equals("C")
                || node.tokenClass.equals("D")
                || node.tokenClass.equals("I")
                || node.tokenClass.equals("O")
                || node.tokenClass.equals("A")
                || node.tokenClass.equals("U")
                || node.tokenClass.equals("T")
                || node.tokenClass.equals("W")
                || node.tokenClass.equals("L")
                || node.tokenClass.equals("X")
                || node.tokenClass.equals("B")
                || node.tokenClass.equals("V"))
        {

            for (int i = 0; i < node.getChildren().size(); i++)
            {
                recursiveScope(node.getChild(i), table, curScope);
            }
        }
       /*
        if (node.tokenClass.equals("A"))
        {
            TreeNode tempLeft = node.getChild(0).getChild(0).getChild(0);
            if (!stable.lookup(tempLeft.snippet, table.getType(tempLeft.tokenNo)))
                stable.bind(tempLeft.snippet, table.getType(tempLeft.tokenNo));

            TreeNode tempRight = node.getChild(1).getChild(0);
            if (tempRight.tokenClass.equals("S"))
                tempRight = tempRight.getChild(0);
            if (!stable.lookup(tempRight.snippet, table.getType(tempRight.tokenNo)))
                throw new StringException("assignment failed: undeclared variable: " + tempRight.snippet);
        }
        */

        // Z

        if (node.tokenClass.equals("Z"))
        {
            if (node.getChildren().size() == 3)
            {
                for (int i = 0; i < node.getChildren().size(); i++)
                {
                    recursiveScope(node.getChild(i), table, curScope);
                }
            }
            else if (node.getChildren().size() == 13)
            {
                stable.enter();
                newScope++;
                stable.bind(node.getChild(1).getChild(0).snippet, table.getType(node.getChild(1).getChild(0).tokenNo));
                table.setScope(node.getChild(1).getChild(0).tokenNo, newScope);
                stable.bind(node.getChild(4).getChild(0).snippet, table.getType(node.getChild(4).getChild(0).tokenNo));
                table.setScope(node.getChild(4).getChild(0).tokenNo, newScope);
                if (!stable.lookup(node.getChild(6).snippet, table.getType(node.getChild(6).tokenNo)))
                {
                    stable.bind(node.getChild(6).getChild(0).snippet, table.getType(node.getChild(6).getChild(0).tokenNo));
                    table.setScope(node.getChild(6).getChild(0).tokenNo, newScope);
                }
                stable.bind(node.getChild(7).getChild(0).snippet, table.getType(node.getChild(7).getChild(0).tokenNo));
                table.setScope(node.getChild(7).getChild(0).tokenNo, newScope);
                stable.exit();
                recursiveScope(node.getChild(10), table, curScope);
            }
        }

        // S
        // N
        if (node.tokenClass.equals("N") || node.tokenClass.equals("S"))
        {
//            if (!stable.lookup(node.getChild(0).snippet, table.getType(node.getChild(0).tokenNo)))
  //          {
    //            throw new StringException("undeclared variable: " + node.getChild(0).snippet);
      //      }
            stable.bind(node.getChild(0).snippet, table.getType(node.getChild(0).tokenNo));
            table.setScope(node.getChild(0).tokenNo, newScope);
        }

        // R
        if (node.tokenClass.equals("R"))
        {
            if (stable.lookup(node.getChild(1).snippet, table.getType(node.getChild(1).tokenNo)))
            {
                throw new StringException("proc " + node.getChild(1).snippet + " already declared in this scope");
            }
            else
            {
                stable.bind(node.getChild(1).snippet, table.getType(node.getChild(1).tokenNo));
                table.setScope(node.getChild(1).tokenNo, curScope);
                stable.enter();
                newScope++;
                recursiveScope(node.getChild(2), table, newScope);
                stable.exit();
            }
        }

        // Y
        if (node.tokenClass.equals("Y"))
        {
            stable.bind(node.getChild(0).snippet, table.getType(node.getChild(0).tokenNo));
            table.setScope(node.getChild(0).tokenNo, curScope);
          // if (!stable.lookup(node.getChild(0).snippet, table.getType(node.getChild(0).tokenNo)))
          //  {
          //      throw new StringException("calling undefined proc: " + node.getChild(0).snippet);
          //  }
        }

    }

    void toFile(InfoTable table)
    {
        try
        {
            String file = "SymbolTable";

            FileWriter fw = new FileWriter(file);
            fw.write(table.toString());
            fw.close();
        }
        catch (IOException e)
        {
            //e.printStackTrace();
            System.out.println("Error opening file: SymbolTable");
        }
    }

}
