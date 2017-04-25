package parser;
import java.util.ArrayList;

public class InfoTable
{
	public class TableItem extends TreeNode {
		private char type;
		public TableItem(int number, String tokenClass, String snippet){
			super(number, tokenClass, snippet);
			type = '\0';
		}
	}

	private ArrayList<TableItem> symbols = new ArrayList<TableItem>();

	public int index(TableItem item){
		return symbols.indexOf(item);
	}
	
	public void insert(int ID, String tokenClass, String snippet){
		TableItem item = new TableItem(ID, tokenClass, snippet);
		symbols.add(item);
	}

	public char getType(int ID){
		try {
			TableItem item = symbols.get(ID);
			if (item == null) {
				return '\0';
			} else {
				return item.type;
			}
		} catch (IndexOutOfBoundsException e){
			System.out.println("Error: Ooops..failed to get the type\nReason: IndexOutOfBoundsException");
			return '\0';
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
