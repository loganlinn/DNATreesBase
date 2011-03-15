
/**
 * 
 * @author loganlinn
 * 
 */
public class SequenceLeafNode extends Node {
	private Sequence sequence;	// Sequence data contained in this node

	/**
	 * Constructs a SequenceNode given a Sequence
	 * 
	 * @param sequence
	 */
	public SequenceLeafNode(Sequence sequence) {
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
	 *  Note: Since we are a leaf node, we can assume parent (what we are returning to) is an InteralNode
	 */
	@Override
	public Node insert(Sequence sequence) {
		// At least one of the sequences should have more characters.
		// If they don' that indicates a duplicate
		if(this.sequence.hasNext() || sequence.hasNext()){
			// Both sequences have more
			Node replacement =  new InternalNode(this, sequence);
			// Return the new InternalNode to move this SequenceNode down
			return replacement;
		}else{
			// Otherwise, we must have the identical sequence
			P2.Error.duplicateSequence(sequence);
			return this;
		}
	}

	@Override
	public Node remove(Sequence sequence) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public String toString(){
		return sequence.toString();
	}
}
