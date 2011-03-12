/**
 * Base node class
 * @author loganlinn
 *
 */
public abstract class Node {
	static final String LEVEL_INDENT = "  ";	// Value to print for indentations
	static final String INTERNAL_NODE = "I";	// Value to print for internal node
	static final String EMPTY_LEAF_NODE = "E";	// Value to print for empty leaf nodes
	
	protected static final Node flyweight = LeafNode.getEmptyLeafNode();
	
	/**
	 * Helper method to print value with indents
	 * @param level
	 */
	protected static void indentedPrint(int level, String value){
		// Print the indents
		for(int i = 0; i < level; i++){
			P2.out.print(LEVEL_INDENT);
		}
		// Print the value
		P2.out.println(value);
	}
	
	abstract void print(int level);				// prints node representation to P2.out
	abstract void insert(Node parent, Sequence sequence);	// inserts the given sequence into the tree
	abstract void delete(Sequence sequence);	// deletes the given sequence from the tree
}
