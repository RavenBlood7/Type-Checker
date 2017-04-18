public class LexerTree {
	private int count; 
	private TreeNode root; 

	public LexerTree() {
		root = null; 
		count = 0; 
	}
	
	public void insert(String tokenClass, String snippet) {
		if (root == null) {
			root = new TreeNode(count++, tokenClass, snippet); 
		} else {
			// unimplemented
		}
	}


}