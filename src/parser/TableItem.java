package parser;
public class TableItem extends TreeNode {
    public char type;
    public TableItem(int number, String tokenClass, String snippet){
        super(number, tokenClass, snippet);
        type = '\0';
    }
}
