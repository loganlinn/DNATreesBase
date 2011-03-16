/**
 * SearchOperation executes the root node's search operation to find a matching sequences.
 * There can be 2 different types of SearchOperations
 * - Normal: looks for all sequences in the tree which sequenceDescriptor is a prefix (including exact matches)
 * - Exact: only finds exact matches to the sequenceDescriptor in the tree.
 * 
 * @author loganlinn
 *
 */
public class SearchOperation extends Operation{
	private static final int ROOT_LEVEL = 0; 
	public static final int SEARCH_MODE_EXACT = 1;
	public static final int SEARCH_MODE_PREFIX = 0;
	
	private int mode;
	private String sequenceDescriptor;
	
	/**
	 * Creates a default search operation (prefix mode) with the given searchDescriptor
	 * @param sequenceDescriptor
	 */
	public SearchOperation(String sequenceDescriptor){
		this(sequenceDescriptor, SEARCH_MODE_PREFIX);
	}
	
	/**
	 * Protected constructor to hide the mode. This allow us to not have to validate the mode.
	 * @param sequenceDescriptor
	 * @param mode
	 */
	protected SearchOperation(String sequenceDescriptor, int mode){
		this.sequenceDescriptor = sequenceDescriptor;
		this.mode = mode;
	}
	
	/**
	 * Run a search operation on the DNA tree's root
	 */
	@Override
	public void execute(Node root) {
		root.search(ROOT_LEVEL, mode, createSequence(sequenceDescriptor));
	}

}
