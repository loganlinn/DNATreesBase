/**
 * InsertOperation executes the root node's insert operation to put a new sequence into the tree.
 * @author loganlinn
 *
 */
public class InsertCommand extends Command{
	private Sequence sequence;
	
	/**
	 * Construct an InsertOperation from a sequenceDescriptor
	 * @param sequenceDescriptor
	 * @throws SequenceException 
	 */
	public InsertCommand(String sequenceDescriptor) throws SequenceException{
		sequence = createSequence(sequenceDescriptor);
	}
	
	/**
	 * Give a new sequence to the root node
	 */
	@Override
	public Node execute(Node root) {
		return root.insert(sequence);
	}
	
	/**
	 * Reports a duplicate sequence has been detected
	 * 
	 * @param sequence
	 */
	public static void duplicateSequence(Sequence sequence){
		out.println("ERROR: Sequence, \""+sequence.toString()+"\" already exists in DNA Tree.");
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
