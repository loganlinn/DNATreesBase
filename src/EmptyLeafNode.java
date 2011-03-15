
/**
 * Flyweight represents a leaf node. Singleton pattern is used for a single 
 * flyweight object is used for all empty leaf nodes in the tree.
 * 
 * @author loganlinn
 *
 */
public class EmptyLeafNode extends Node{
	
	private static EmptyLeafNode flyweight = null;	// Single flyweight for all empty leaf nodes
	
	/**
	 * Factory method to access flyweight
	 * @return
	 */
	public static EmptyLeafNode getInstance(){
		if(flyweight == null){
			flyweight = new EmptyLeafNode();
		}
		return flyweight;
	}
	
	/**
	 * Factory method to access new/reused SequenceNode 
	 * @return
	 */
//	public static LeafNode getNonEmptyLeafNode(Sequence s){
//		return new SequenceNode(s);
//	}
	
	 
	/**
	 * Constructs a Flyweight
	 * Private constructor for Singleton pattern.
	 * Can only be instantiated internally
	 * @see EmptyLeafNode#getInstance()
	 */
	private EmptyLeafNode(){
		
	}

	@Override
	public void print(int level, int mode) {
		indentedPrint(level, EMPTY_LEAF_NODE);
	}
	
	/**
	 * Only called when an empty leaf-node. Replace self with new non-empty leaf node (SequenceNode)
	 */
	@Override
	public Node insert(Sequence sequence) {
		return new SequenceLeafNode(sequence);
	}

	@Override
	public Node remove(Sequence sequence) {
		// TODO Auto-generated method stub
		return null;
	}
}
