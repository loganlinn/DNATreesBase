
/**
 * Base node class
 * @author loganlinn
 *
 */
public abstract class Node {
	public static final String LEVEL_INDENT = "  ";		// Value to print for indentations
	public static final String INTERNAL_NODE = "I";		// Value to print for internal node
	public static final String EMPTY_LEAF_NODE = "E";	// Value to print for empty leaf nodes
	
	/**
	 * Helper method to print value with indents
	 * 
	 * @param level
	 */
	protected static void indentedPrint(int level, String value){
		// Print the indents
		for(int i = 0; i < level; i++){
			PrintOperation.out.print(LEVEL_INDENT);
		}
		// Print the value
		PrintOperation.out.println(value);
	}
	
	public abstract void print(int level, int mode);	// prints node representation to P2.out
	public abstract Node insert(Sequence sequence);	// inserts the given sequence into the tree
	public abstract Node remove(Sequence sequence);	// deletes the given sequence from the tree
	public abstract void search(SearchOperation searchData);
}
