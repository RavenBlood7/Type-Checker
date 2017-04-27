package crawler;

import parser.*;

public class TypeChecker {
	private char [] validTypes = {
		'\0', // signals error state i.e unbounded 0
		'w', // well-typed 1
		'p', // procedure 2
		'n', // numbers 3
		'o', // output 4
		's', // strings 5
		'b', // booleans 6
		'h' // halt 7
	};

	public void doTypeChecking(TreeNode node, InfoTable table){
		initializeTable(node, table);
		visitAST(node, table);
	}

	/**
	 * This function will recursively walk through the Abstract Syntaxt Tree (AST)
	 * and create table entries for each node within the AST
	 * @param node
	 * @param table
	 */
	private void initializeTable(TreeNode node, InfoTable table) {
		if(node == null){ return; }
		table.insert(node.tokenNo, node.tokenClass, node.snippet);
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
	 * @param node1
	 * @param table
	 */
	private void visitAST(TreeNode node1, InfoTable table){
	/*	TableItem node = (TableItem) node1;
		// trivial case
		if(node == null){ return; }

		// Number Syntactic Category, Symbol: b
		if(node.tokenClass.equals("number")){
			if(node.type == '\0'){
				node.type = validTypes[3];
				table.setType(findIndex(node, table), validTypes[3]);
			}
		}

		// user-defined name Syntactic Category, Symbol: u
		if(node.tokenClass.equals("user-defined name")){
			if(node.getParent().equals("N")){
				node.type = validTypes[3]; // number
				table.setType(findIndex(node, table), validTypes[3]);
			} else if(node.getParent().equals("S")){
				node.type = validTypes[5]; // string
				table.setType(findIndex(node, table), validTypes[5]);
			} else {
				// report an error
			}
		}

		// short string Syntactic Category, Symbol: s
		if(node.tokenClass.equals("short string")){
			if(node.type.equals('\0')){
				node.type = validTypes[5];
				table.setType(findIndex(node, table), validTypes[5]);
			}
		}

		// Boolean Syntactic Category, Symbol: e, n, a, o
	/*	Pattern p = Pattern.compile("eq|not|and|or");
		Matcher m = p.matcher(node.tokenClass);
		if(m.find()){
			if(node.type.equals('\0')){
				node.type = validTypes[6]; // booleans
				table.setType(findIndex(node, table), validTypes[6]);
			}
		}

		// NVAR Syntactic Category, Symbol: N
		if(node.tokenClass.equals("N")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(0), table);
				if (node.getChild(0).type.equals(validTypes[3])){
					node.type = validTypes[3]; // number
					table.setType(findIndex(node, table), validTypes[3]);
				} else {
					// guess a type
					//reportError();
				}
			}
		}

		// SVAR Syntactic Category, Symbol: S
		if(node.tokenClass.equals("S")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(0), table);
				if(node.getChild(0).type.equals(validTypes[5])){
					node.type = validTypes[5]; //string
					table.setType(findIndex(node, table), validTypes[5]);
				} else {
					// guess a type
					// reportError
				}
			}
		}

		// Variable Syntactic Category, Symbol: V
		if(node.tokenClass.equals("V")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(0), table);
				if(node.getChild(0).type.equals(validTypes[3]) || node.getChild(0).type.equals(validTypes[5])){
					node.type = validTypes[4];
					table.setType(findIndex(node, table), validTypes[4]); // output
				} else {
					// guess type
					// reportError
				}
			}
		}

		// Calculation Syntactic Category Symbol: L
		if(node.tokenClass.equals("L")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(1), table);
				visitAST(node.getChild(2), table);
				if(node.getChild(1).type.equals(validTypes[3]) && node.getChild(2).type.equals(validTypes[3])){
					node.type = validTypes[3]; // number
					table.setType(findIndex(node, table), validTypes[3]);
				} else {
					// guess a type
					// reportError();
					System.out.println("Calc TypeErr");
				}
			}
		}

		// Boolean Syntactic Category, Symbol: B
		if(node.tokenClass.equals("B")){
			if(node.type.equals('\0')){
				if(node.childrenSize() == 2){ // not
					//visitAST(node.getChild(0), table);
					visitAST(node.getChild(1), table);
					if(node.getChild(1).type.equals(validTypes[6])){
						node.type = validTypes[6];
						table.setType(findIndex(node, table), validTypes[6]);
					} else {
						// guess type
						// reportError
					}
				} else if (node.childrenSize() == 3){ // eq, and, or, <, >
					visitAST(node.getChild(0), table);
					visitAST(node.getChild(2), table);
					if(node.getChild(1).type.equals(validTypes[4]) && node.getChild(2).type.equals(validTypes[4])){ // VAR
						node.type = validTypes[6];
						table.setType(findIndex(node, table), validTypes[6]);
					} else if(node.getChild(0).type.equals(validTypes[3]) && node.getChild(2).type.equals(validTypes[3])){ // NVAR
						node.type = validTypes[6];
						table.setType(findIndex(node, table), validTypes[6]);
					} else if(node.getChild(0).type.equals(validTypes[6]) && node.getChild(2).type.equals(validTypes[6])){ // Boolean
						node.type = validTypes[6];
						table.setType(findIndex(node, table), validTypes[6]);
					} else {
						// guess type
						// reportError
					}
				} else {
					System.out.println("Boolean Expression Error: Too many arguments");
				}
			}
		}

		// Conditional Branch Syntactic Category, Symbol: W
		if(node.tokenClass.equals("W")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(1), table); // bool
				visitAST(node.getChild(3), table); // code
				if(node.childrenSize() == 4) {
			   		if(node.getChild(1).type.equals(validTypes[6]) && node.getChild(3).type.equals(validTypes[1])){
				   		node.type = validTypes[1]; // well-typed
				   		table.setType(findIndex(node, table), validTypes[1]);
			   		} else {
					   // guess type
					   // reportError
			   		}
				} else if(node.childrenSize() == 6){
					visitAST(node.getChild(5), table);
					if(node.getChild(1).type.equals(validTypes[6]) && node.getChild(3).type.equals(validTypes[1]) && node.getChild(5).type.equals(validTypes[1])){
						node.type = validTypes[1]; // well-typed
						table.setType(findIndex(node, table), validTypes[1]);
					} else {
						// guess type
						// reportError
					}
				} else {
					System.out.println("Conditional Branch Expression Error: Invalid number of arguments");
				}
			}
		}

		// Intermediate Syntactic Category, Symbol: T, U
		if(node.tokenClass.equals("T") || node.tokenClass.equals("U")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(0), table);
				if(node.getChild(0).type.equals(validTypes[5])){ // SVAR
					node.type = validTypes[1]; // well-typed
					table.setType(findIndex(node, table), validTypes[1]);
				} else if(node.getChild(0).type.equals(validTypes[3])){ // NVAR
					node.type = validTypes[1]; // well-typed
					table.setType(findIndex(node, table), validTypes[1]);
				} else {
					// guess type
					// reportError
				}
			}
		}

		// Assign Syntactic Category, Symbol: A
		if(node.tokenClass.equals("A")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(2), table);
				if(node.getChild(0).type.equals(validTypes[5]) && node.getChild(2).type.equals(validTypes[5])){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				} else if(node.getChild(0).type.equals(validTypes[3]) && node.getChild(2).type.equals(validTypes[3])){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				} else {
					// guess type
					// reportError
				}
			}
		}

		// Conditional Loop Syntactic Category, Symbol: Z
		if(node.tokenClass.equals("Z")){
			if(node.type.equals('\0')){
				if(node.childrenSize() == 3){
					visitAST(node.getChild(1), table);
					visitAST(node.getChild(2), table);
					if(node.getChild(1).type.equals(validTypes[6]) && node.getChild(2).type.equals(validTypes[1])){
						node.type = validTypes[1]; // well-typed
				   		table.setType(findIndex(node, table), validTypes[1]);
					} else {
						// guess type
						// reportError
					}
				} else if (node.childrenSize() == 13){
					Boolean isOk = true;
					visitAST(node.getChild(1), table);
					visitAST(node.getChild(3), table);
					if(!node.getChild(1).type.equals(validTypes[3]) ||
					   !node.getChild(3).type.equals(validTypes[3])){
						isOk = false;
						// reportError
					}
					if(isOk){
						visitAST(node.getChild(4), table);
						visitAST(node.getChild(6), table);
						if(!node.getChild(4).type.equals(validTypes[3]) ||
						   !node.getChild(6).type.equals(validTypes[3])){
							isOk = false;
							// reportError
						}
						if(isOk){
							visitAST(node.getChild(7), table);
							visitAST(node.getChild(10), table);
							visitAST(node.getChild(11), table);
							if(!node.getChild(7).type.equals(validTypes[3]) ||
							  !node.getChild(10).type.equals(validTypes[3]) ||
							  !node.getChild(11).type.equals(validTypes[3])){
								isOk = false;
								// reportError
							}
							if(isOk){
								visitAST(node.getChild(12), table);
								if(!node.getChild(12).type.equals(validTypes[1])){
									isOk = false;
									// reportError
								}
							}
						}
					}

					if(isOk){
						node.type = validTypes[1]; // well-typed
				   		table.setType(findIndex(node, table), validTypes[1]);
					} else {
						// guess type
						// reportError
					}
				} else {
					System.out.println("Conditional Loop Expression Error: Invalid number of arguments");
				}
			}
		}

		if(node.tokenClass.equals("Q")){
			if(node.type.equals('\0')){
				visitAST(node.getChild(0), table);
				if(node.getChild(0).type.equals(validTypes[1])){
					node.type = validTypes[1]; // well-typed
					table.setType(findIndex(node, table), validTypes[1]);
				} else {
					// reportError
				}
			}
			//this means tree is done, so check if all have type symbols
		}

		// P Syntactic Category : PROG
		if(node.tokenClass.equals("P")){
			if (node.childrenSize() == 1){
				visitAST(node.getChild(0), table);
				if(node.type.equals('\0') && node.getChild(0).type.equals('w')){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				}
			}
			else if (node.childrenSize() == 2){
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(1), table);
				if (node.getChild(0).type.equals('w') && node.getChild(1).type.equals('w')){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				}
			}
			//this means tree is done, so check if all have type symbols
		}

		// D Syntactic Category : PROC_DEFS
		if(node.tokenClass.equals("D")){
			if (node.childrenSize() == 1){
				visitAST(node.getChild(0), table);
				if(node.type.equals('\0') && node.getChild(0).type.equals('w')){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				}
			}
			else if (node.childrenSize() == 2){
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(1), table);
				if (node.type.equals('\0') && node.getChild(0).type.equals('w') && node.getChild(1).type.equals('w')){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				}
			}
		}

		// R Syntactic Category : PROC
		if(node.tokenClass.equals("R")){
			//enter symbol table
			visitAST(node.getChild(2), table);
			//exit symbol table
			if(node.type.equals('\0') && node.getChild(1).type.equals('\0')
				&& node.getChild(2).type.equals('w')){
				node.getChild(1).type = validTypes[2];
				table.setType(findIndex(node.getChild(1), table), validTypes[2]);
				//perhaps bind in symbol table

				node.type = validTypes[1];
				table.setType(findIndex(node, table), validTypes[1]);
			}
		}

		// C Syntactic Category : CODE
		if(node.tokenClass.equals("C")){
			if (node.childrenSize() == 1){
				visitAST(node.getChild(0), table);
				if(node.type.equals('\0') && node.getChild(0).type.equals('w')){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				}
			}
			else if (node.childrenSize() == 2) {
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(1), table);
				if (node.type.equals('\0') && node.getChild(0).type.equals('w') && node.getChild(1).type.equals('w')){
					node.type = validTypes[1];
					table.setType(findIndex(node, table), validTypes[1]);
				}
			}
		}

		// I Syntactic Category : INSTR
		if(node.tokenClass.equals("I")){
			if (node.childrenSize() >0){
				if (node.getChild(0).tokenClass.equals("keyword")){
					if (node.type.equals('\0')){
						node.getChild(0).type = validTypes[7]; // for halt
						table.setType(findIndex(node.getChild(0), table), validTypes[7]);
						node.type = validTypes[1];
						table.setType(findIndex(node, table), validTypes[1]);
					}
				}
				else  { //for O A W Z Y
					visitAST(node.getChild(0), table);
					if (node.type.equals('\0') && node.getChild(0).type.equals('w')){
						node.type = validTypes[1];
						table.setType(findIndex(node, table), validTypes[1]);
					}
				}
			}

			// O Syntactic Category : IO
			if(node.tokenClass.equals("O")){
				if (node.childrenSize() >0){
					if (node.type.equals('\0')){
						node.getChild(1).type = validTypes[3];
						table.setType(findIndex(node.getChild(1), table), validTypes[3]);
						node.type = validTypes[1];
						table.setType(findIndex(node, table), validTypes[1]);
					}
				}
			}

			// Y Syntactic Category : CALL
			if(node.tokenClass.equals("Y")){
				if (node.childrenSize() > 0){
					if (node.type.equals('\0')){
						node.getChild(1).type = validTypes[3];
						table.setType(findIndex(node.getChild(1), table), validTypes[3]);
						node.type = validTypes[1];
						table.setType(findIndex(node, table), validTypes[1]);
					}
				}
			}
		}
		*/
	}

	private void reportError(TreeNode node){
		// do some error handling
	}

	private int findIndex(TreeNode node, InfoTable table){
		TableItem item = new TableItem(node.tokenNo, node.tokenClass, node.snippet);
		return 1;//table.index(item);
	}
}
