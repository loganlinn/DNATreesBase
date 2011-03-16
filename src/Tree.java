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
	 * @param sequenceDescriptor
	 */
	public Tree(String sequenceDescriptor){
		root = new SequenceLeafNode(new ArraySequence(sequenceDescriptor));
	}
	
	/**
	 * Print the tree in "normal" mode
	 * 
	 */
	public void print() {
		print(PrintOperation.PRINT_MODE_NORMAL);
	}

	/**
	 * Print the tree in "lengths" mode
	 * 
	 */
	public void printLengths(){
		print(PrintOperation.PRINT_MODE_LENGTHS);
	}
	
	/**
	 * Print the tree in "stats" mode
	 * 
	 */
	public void printStats(){
		print(PrintOperation.PRINT_MODE_STATS);
	}
	
	/**
	 * Print the tree with the specified mode
	 * 
	 * @param mode
	 */
	protected void print(int mode){
		// Start the preorder traversal at level 0
		root.print(ROOT_LEVEL, mode);
	}
	
	/**
	 * Inserts a Sequence into the tree
	 * 
	 * @param sequenceDescriptor
	 */
	public void insert(String sequenceDescriptor) {
		// Insert the sequence into the root node
		root = root.insert(new ArraySequence(sequenceDescriptor));
	}
	
	/**
	 * Removes a Sequence from the tree
	 * 
	 * @param sequenceDescriptor
	 */
	public void remove(String sequenceDescriptor){
		root = root.remove(new ArraySequence(sequenceDescriptor));
	}
	
	/**
	 * Searches for sequence in tree
	 * TODO: Describe output
	 * 
	 * @param sequenceDescriptor
	 */
	protected void search(int mode, String sequenceDescriptor){
		root.search(ROOT_LEVEL, mode, new ArraySequence(sequenceDescriptor));
	}
	
	/**
	 * Searches the tree for a given sequence prefix (including exact match)
	 * 
	 * @param sequenceDescriptor
	 */
	public void searchPrefix(String sequenceDescriptor){
		search(SearchOperation.SEARCH_MODE_PREFIX, sequenceDescriptor);
	}
	
	/**
	 * Searches the tree for the exact sequence
	 * 
	 * @param sequenceDescriptor
	 */
	public void searchExact(String sequenceDescriptor){
		search(SearchOperation.SEARCH_MODE_EXACT, sequenceDescriptor);
	}
	
	/**
	 * Sample test: http://courses.cs.vt.edu/~cs3114/Spring11/P2sampleinputTree.pdf
	 */
	public static void main(String[] args) {	
		Tree dnatree = new Tree("AAAA");
		
		dnatree.insert("ACGT");
//		dnatree.print();
		
		dnatree.insert("AA");
//		dnatree.print();
		
		dnatree.insert("AAACCCCGGTGAAAACGTA");
//		dnatree.print();
		
		dnatree.insert("ACTGGGAA");
//		dnatree.print();
		
		dnatree.remove("ACGT");
//		dnatree.print();
		
		dnatree.insert("ACCTT");		
//		dnatree.print();
		
		dnatree.insert("ACTTA");
//		dnatree.print();
		
		dnatree.insert("TATA");
//		dnatree.print();		
		
		dnatree.insert("TCG");
//		dnatree.print();
		dnatree.printLengths();
		dnatree.printStats();
		
	}
}
