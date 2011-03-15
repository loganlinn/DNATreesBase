
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
	 * 
	 */
	@Override
	public void print(int level, int mode) {
		String sequenceValue = sequence.toString();
		
		if(mode == PRINT_MODE_LENGTHS){
			sequenceValue += ": length "+sequence.length();
		}else if(mode == PRINT_MODE_STATS){
			sequenceValue += ": "+sequence.stats();
		}
		
		indentedPrint(level, sequenceValue);
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
	 *  
	 *  @return the Node that should replace this Node OR self to keep the same
	 */
	@Override
	public Node insert(Sequence sequence) {
		// At least one of the sequences should have more characters.
		// If they don't that indicates a duplicate. We could also compare sequences here
		if(this.sequence.hasNext() || sequence.hasNext()){
			// Return the new InternalNode to move this SequenceNode down
			return new InternalNode(this, sequence);
		}else{
			// Otherwise, we must have the identical sequence
			P2.Error.duplicateSequence(sequence);
			return this;
		}
	}

	/**
	 * Removes a LeafNode from the tree once it is found
	 * 
	 * @return the Node that should replace this Node OR self to keep the same
	 */
	@Override
	public Node remove(Sequence sequence) {
		// Verify this is a matching sequence
		if(this.sequence.equals(sequence)){
			return EmptyLeafNode.getInstance();
		}else{
			P2.out.println(this.sequence+" != "+sequence);
			P2.Error.removeSequenceNotFound(sequence);
			return this;
		}
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
