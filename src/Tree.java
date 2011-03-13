
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
	
	
	void print(int mode) {
		// Start the preorder traversal at level 0
		root.print(ROOT_LEVEL, mode);
	}

	/**
	 * Inserts a Sequence into the tree
	 * @param sequence
	 */
	void insert(Sequence sequence) {
		// Insert the sequence into the root node
		root.insert(null, sequence);
		
	}


	void delete(Sequence sequence) {
		// TODO Auto-generated method stub
		
	}

}
