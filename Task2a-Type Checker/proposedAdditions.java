		// P Syntactic Category : PROG
		if(node.tokenClass.equals("P")){
			if (node.childrenSize() == 1){
				visitAST(node.getChild(0), table);
				if(node.type.equals('\0') && node.getChild(0).type.equals('w')){
					node.type = validTypes[1];
					table.setType(node.tokenNo, validTypes[1]);
				}
			}
			else if (node.childrenSize() == 2){
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(1), table);
				if (node.getChild(0).type.equals('w') && node.getChild(1).type.equals('w')){
					node.type = validTypes[1];
					table.setType(node.tokenNo, validTypes[1]);
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
					table.setType(node.tokenNo, validTypes[1]);
				}
			}
			else if (node.childrenSize() == 2){
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(1), table);
				if (node.type.equals('\0') && node.getChild(0).type.equals('w') && node.getChild(1).type.equals('w')){
					node.type = validTypes[1];
					table.setType(node.tokenNo, validTypes[1]);
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
				table.setType(node.getChild(1).tokenNo, validTypes[2]);
				//perhaps bind in symbol table

				node.type = validTypes[1];
				table.setType(node.tokenNo, validTypes[1]);
			}
		}

		// C Syntactic Category : CODE
		if(node.tokenClass.equals("C")){
			if (node.childrenSize() == 1){
				visitAST(node.getChild(0), table);
				if(node.type.equals('\0') && node.getChild(0).type.equals('w')){
					node.type = validTypes[1];
					table.setType(node.tokenNo, validTypes[1]);
				}
			}
			else if (node.childrenSize() == 2) {
				visitAST(node.getChild(0), table);
				visitAST(node.getChild(1), table);
				if (node.type.equals('\0') && node.getChild(0).type.equals('w') && node.getChild(1).type.equals('w')){
					node.type = validTypes[1];
					table.setType(node.tokenNo, validTypes[1]);
				}
			}
		}

		// I Syntactic Category : INSTR
		if(node.tokenClass.equals("I")){
			if (node.childrenSize() >0){
				if (node.getChild(0).tokenClass.equals("keyword"){
					if (node.type.equals('\0')){
						node.getChild(0).type = 'h'; //h is for halt
						table.setType(nod.getChild(0).tokenNo, 'h');
						node.type = validTypes[1];
						table.setType(node.tokenNo, validTypes[1]);
					}
				}
				else  { //for O A W Z Y
					visitAST(node.getChild(0), table);
					if (node.type.equals('\0') && node.getChild(0).type.equals('w')){
						node.type = validTypes[1];
						table.setType(node.tokenNo, validTypes[1]);
					}
				}
			}

			// O Syntactic Category : IO
			if(node.tokenClass.equals("O")){
				if (node.childrenSize() >0){
						if (node.type.equals('\0')){
							node.getChild(1).type = validTypes[3];
							table.setType(node.getChild(1).tokenNo, validTypes[3]);
							node.type = validTypes[1];
							table.setType(node.tokenNo, validTypes[1]);
						}
					}
				}

				// Y Syntactic Category : CALL
				if(node.tokenClass.equals("Y")){
					if (node.childrenSize() >0){
							if (node.type.equals('\0')){
								node.getChild(1).type = validTypes[3];
								table.setType(node.getChild(1).tokenNo, validTypes[3]);

								node.type = validTypes[1];
								table.setType(node.tokenNo, validTypes[1]);
							}
						}
					}

		}
