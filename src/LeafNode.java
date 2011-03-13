/**
 * Flyweight represents a leaf node. Singleton pattern is used for a single 
 * flyweight object is used for all empty leaf nodes in the tree.
 * 
 * @author loganlinn
 *
 */
public class LeafNode extends Node{
	
	private static LeafNode flyweight = null;	// Single flyweight for all empty leaf nodes
	
	/**
	 * Factory method to access flyweight
	 * @return
	 */
	public static LeafNode getEmptyLeafNode(){
		if(flyweight == null){
			flyweight = new LeafNode();
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
	 * @see LeafNode#getFlyweight()
	 */
	private LeafNode(){
		
	}

	@Override
	public void print(int level, int mode) {
		indentedPrint(level, EMPTY_LEAF_NODE);
	}
	
	/**
	 * Only called when an empty leaf-node. Replace self with new non-empty leaf node (SequenceNode)
	 */
	@Override
	public void insert(Node parent, Sequence sequence) {
		((InternalNode) parent).setChild(sequence.current(), new SequenceNode(sequence));
	}

	@Override
	public void delete(Sequence sequence) {
		// TODO Auto-generated method stub
		
	}
}
