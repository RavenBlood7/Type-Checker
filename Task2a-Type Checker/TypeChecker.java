
import parser.InfoTable;
import parser.TreeNode;

public class TypeChecker {
	private char [] validTypes = {
		'!', // signals error state i.e unbounded 0
		'w', // well-typed 1
		'p', // procedure 2
		'n', // numbers 3
		'o', // output 4
		's', // strings 5
		'b'	 // booleans 6
	};

	public void doTypeChecking(TreeNode node, InfoTable table){
		initializeTable(node, table);
		visitAST(table);
	}

	/**
	 * This function will recursively walk through the Abstract Syntaxt Tree (AST)
	 * and create table entries for each node within the AST
	 * @param node
	 * @param table
	 */
	private void initializeTable(TreeNode node, InfoTable table) {
		if(node == null){ return; }
		table.insert(node.getTableEntry(), node.tokenClass, node.snippet);
		if(node.childrenSize() > 0){
			int size = node.childrenSize();
			for(int i = 0; i < size; i++){
				initializeTable(node.getChild(i), table);
			}
		}
	}

	/**
	 * This function will recursively walk through the Abstract Syntaxt Tree (AST)
	 * and based on the Grammar rules defined for the SPL language,
	 * it will assign types to each node in the AST if successful, or report an error
	 * @param node
	 * @param table
	 */
	private void visitAST(TreeNode node, InfoTable table){
		// trivial case
		if(node == null){ return; }
		// Number Syntactic Category
		if(node.tokenClass.equals("number")){
			if(node.type.equals('\0')){
				node.type = validTypes[3];
				table.setType(node.tokenNo, validTypes[3]);
			}
		}

		if(node.tokenClass.equal("user-defined name")){
			if(node.getParent.equals("N")){
				node.type = validTypes[3]; // number
				table.setType(node.tokenNo, validTypes[3]);
			} else if(node.getParent.equals("N")){
				node.type = validTypes[5]; // string
				table.setType(node.tokenNo, validTypes[5]);
			} else {
				// report an error
			}
		}

		// NVAR Syntactic Category
		if(node.tokenClass.equals("N")){
			if(node.type.equals('\0')){
				if (node.getChild(0).type == validTypes[3]){
					node.type = validTypes[3];
					table.setType(node.tokenNo, validTypes[3]);
				} else {
					// guess a type
					//reportError();
				}
			}
		}

		// Calculation Syntactic Category
		if(node.tokenClass.equals('L')){
			if(node.type.equals('\0')){
				if(node.getChild(0).type == node.getChild(1).type && node.getChild(1).type.equals('n')){
					node.type = validTypes[3]; // number
					table.setType(node.tokenNo, validTypes[3]);
				} else {
					// guess a type
					// reportError();
				}
			}
		}

	}

	private void reportError(TreeNode node){
		// do some error handling
	}
}

///table has the following interface
//~ public class InfoTable 
//~ {	
	//~ public insert(int ID, String snippet, String type)	
	//~ public getType(int ID)
	//~ public setType(int ID, String type)
	//~ public getText(int ID)
//~ }

///the TreeNode and the TokenNode are also relevant