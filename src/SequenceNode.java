/**
 * 
 * @author loganlinn
 * 
 */
public class SequenceNode extends Node {
	private Sequence sequence;	// Sequence data contained in this node

	/**
	 * Constructs a SequenceNode given a Sequence
	 * 
	 * @param sequence
	 */
	public SequenceNode(Sequence sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * Print the Sequence within this Node
	 */
	@Override
	public void print(int level, int mode) {
		indentedPrint(level, sequence.toString());
	}
	
	/**
	 * Inserting into a Sequence into a SequenceNode
	 * 
	 * Potential outcomes:
	 * 	- Expand tree by creating new InternalNode and inserting into that
	 *  - Sequence to insert is a prefix to this node's sequence
	 *  - Sequence to insert is the same as this node's sequence (error)
	 *  
	 *  Note: Since we are a leaf node, we can assume parent is an InteralNode
	 */
	@Override
	public void insert(Node parent, Sequence sequence) {
		final boolean thisHasNext = this.sequence.hasNext();
		
		if(thisHasNext && sequence.hasNext()){
			// Both sequences have more
			((InternalNode) parent).setChild(sequence.current(), new InternalNode(this, sequence));
			
		}else if(thisHasNext){
			// This node's sequence still has characters, but inserting sequence doesn't
			// Therefore, sequence we are inserting is a prefix of this sequence
			
			// Sanity check: if parent is null (aka we are root) inserting sequence should have characters, 
			// but would have only gotten to here if it didn't
			if(parent == null){
				P2.Error.invalidSequence(sequence);
			}
			
			// Check if parent's prefix child is already defined. If it is, we have duplicate sequences
			// Compare the parent's $ to the flyweight to determine if its empty 
			if(LeafNode.getEmptyLeafNode() != ((InternalNode) parent).get$()){
				((InternalNode) parent).set$(new SequenceNode(sequence));
			}
		}else{
			// Otherwise, we must have the identical sequence
			P2.Error.duplicateSequence(sequence);
		}
	}

	@Override
	public void delete(Sequence sequence) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the sequence
	 */
	public Sequence getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

}
