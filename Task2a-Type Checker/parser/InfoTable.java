package parser;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class InfoTable
{
	public class TableItem extends TreeNode {
		private char type;
		public TableItem(int number, String tokenClass, String snippet){
			super(number, tokenClass, snippet);
			type = '/0';
		}
	}

	private ArrayList<TableItem> symbols = new ArrayList<TableItem>();

	public void insert(int ID, String tokenClass, String snippet){
		TableItem item = new TableItem(ID, tokenClass, snippet);
		symbols.add(item);
	}

	public char getType(int ID){
		try {
			TableItem item = symbols.get(ID);
			if (item == null) {
				return '!';
			} else {
				System.out.println("Error: Ooops..failed to get the type\nReason: IndexOutOfBoundsException");
				return item.type;
			}
		} catch (IndexOutOfBoundsException e){
			return '!';
		}
	}

	public void setType(int ID, char type){
		try {
			TableItem item = symbols.get(ID);
			item.type = type;
		} catch (IndexOutOfBoundsException e){
			System.out.println("Error: Ooops..failed to set type.\nReason: IndexOutOfBoundsException");
		}
	}

	public String getText(int ID){
		return "";
	}
}