import java.util.Stack;

public class SymbolTable
{
	private Stack<STNode> stack;
	private class STNode
	{
		public String name;
		public Character type;
		
		STNode(String name, Character type)		
		{
			this.name = name;
			this.type = type;			
		}
	}
	
	public boolean empty()
	{
		return stack.empty();
	}
	
	public void bind(String name, Character type)
	{
		stack.push(new STNode(name, type));
	}
	
	public Character lookup(String name)
	{
		for (int i = 0; i < stack.size(); i++)
		{
			if (stack.elementAt(i).name.equals(name))
				return stack.elementAt(i).type;
		}
		return "!";
	}
	
	public void enter()
	{
		stack.push(new STNode("mark", '#'));		
	}
	
	public void exit()
	{
		while (!stack.empty() && stack.pop().name != "mark") {};
	}
}