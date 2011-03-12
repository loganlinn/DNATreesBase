/**
 * 
 * @author loganlinn
 * 
 */
public class SequenceNode implements Node {
	private Sequence sequence;	// Sequence contained in this node

	/**
	 * Constructs a SequenceNode given a Sequence
	 * 
	 * @param sequence
	 */
	public SequenceNode(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public void print() {
		P2.out.println(sequence);
	}

	@Override
	public void insert(Sequence sequence) {
		// TODO Auto-generated method stub
		
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
