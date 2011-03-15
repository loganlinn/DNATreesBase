

public class Tree{
	private final static int ROOT_LEVEL = 0;
	private Node root;
	
	/**
	 * Constructs a Tree object given a sequence. 
	 * The first node in the tree is always a SequenceNode
	 * 
	 * @param sequence
	 */
	public Tree(Sequence sequence){
		root = new SequenceNode(sequence);
	}
	
	
	public void print() {
		print(Node.PRINT_MODE_NORMAL);
	}

	public void printLengths(){
		print(Node.PRINT_MODE_LENGTHS);
	}
	
	public void printStats(){
		print(Node.PRINT_MODE_STATS);
	}
	
	public void print(int mode){
		// Start the preorder traversal at level 0
		root.print(ROOT_LEVEL, mode);
	}
	
	/**
	 * Inserts a Sequence into the tree
	 * @param sequence
	 */
	void insert(Sequence sequence) {
		// Insert the sequence into the root node
		root = root.insert(null, sequence);
		
	}


	void delete(Sequence sequence) {
		// TODO Auto-generated method stub
		
	}

}
