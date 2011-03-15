
/**
 * Base node class
 * @author loganlinn
 *
 */
public abstract class Node {
	public static final String LEVEL_INDENT = "  ";		// Value to print for indentations
	public static final String INTERNAL_NODE = "I";		// Value to print for internal node
	public static final String EMPTY_LEAF_NODE = "E";	// Value to print for empty leaf nodes
	public static final int PRINT_MODE_NORMAL = 0;		// Passed to print for normal print mode
	public static final int PRINT_MODE_LENGTHS = 1;		// Passed to print for lengths print mode
	public static final int PRINT_MODE_STATS = 2;		// Passed to print for stats print mode
	
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
	
	public abstract void print(int level, int mode);	// prints node representation to P2.out
	public abstract Node insert(Sequence sequence);	// inserts the given sequence into the tree
	public abstract Node remove(Sequence sequence);	// deletes the given sequence from the tree
}
