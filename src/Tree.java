/**
 * 
 * @author loganlinn
 *
 */
public class Tree{
	private final static int ROOT_LEVEL = 0; // Numerical value for level of the root node
	private Node root;	// Root of tree
	
	/**
	 * Constructs a Tree object given a sequence. 
	 * The first node in the tree is always a SequenceNode
	 * 
	 * @param sequence
	 */
	public Tree(Sequence sequence){
		root = new SequenceLeafNode(sequence);
	}
	
	/**
	 * Print the tree in "normal" mode
	 * 
	 */
	public void print() {
		print(Node.PRINT_MODE_NORMAL);
	}

	/**
	 * Print the tree in "lengths" mode
	 * 
	 */
	public void printLengths(){
		print(Node.PRINT_MODE_LENGTHS);
	}
	
	/**
	 * Print the tree in "stats" mode
	 * 
	 */
	public void printStats(){
		print(Node.PRINT_MODE_STATS);
	}
	
	/**
	 * Print the tree with the specified mode
	 * 
	 * @param mode
	 */
	public void print(int mode){
		// Start the preorder traversal at level 0
		root.print(ROOT_LEVEL, mode);
	}
	
	/**
	 * Inserts a Sequence into the tree
	 * 
	 * @param sequence
	 */
	void insert(Sequence sequence) {
		// Insert the sequence into the root node
		root = root.insert(sequence);
	}
	
	/**
	 * Removes a Sequence from the tree
	 * 
	 * @param sequence
	 */
	void remove(Sequence sequence){
		root = root.remove(sequence);
	}
	
	/**
	 * Searches for sequence in tree
	 * TODO: Describe output
	 * 
	 * @param sequence
	 */
	void search(Sequence sequence){
		
	}

	public static void main(String[] args) {
		
		/**
		 * Sample test
		 */
		Tree dnatree = new Tree(new ArraySequence("AAAA"));
		
		dnatree.insert(new ArraySequence("ACGT"));
		
		dnatree.insert(new ArraySequence("AA"));
		
		dnatree.insert(new ArraySequence("AAACCCCGGTGAAAACGTA"));
		
		dnatree.insert(new ArraySequence("ACTGGGAA"));
//		dnatree.print();
		
		dnatree.remove(new ArraySequence("ACGT"));
		dnatree.print();
		
//		dnatree.insert(new ArraySequence("ACCTT"));		
//		dnatree.print();
		
//		dnatree.insert(new ArraySequence("ACTTA"));
//		dnatree.print();
		
//		dnatree.insert(new ArraySequence("TATA"));
//		dnatree.print();		
		
//		dnatree.insert(new ArraySequence("TCG"));
//		dnatree.print();
		
	}
}
