/**
 * InsertOperation executes the root node's insert operation to put a new sequence into the tree.
 * @author loganlinn
 *
 */
public class InsertOperation extends Operation{
	private String sequenceDescriptor;
	
	/**
	 * Construct an InsertOperation from a sequenceDescriptor
	 * @param sequenceDescriptor
	 */
	public InsertOperation(String sequenceDescriptor){
		this.sequenceDescriptor = sequenceDescriptor;
	}
	
	/**
	 * Give a new sequence to the root node
	 */
	@Override
	public Node execute(Node root) {
		return root.insert(createSequence(sequenceDescriptor));
	}
	
	/**
	 * Reports a duplicate sequence has been detected
	 * 
	 * @param sequence
	 */
	public static void duplicateSequence(Sequence sequence){
		out.println("ERROR: Sequence, \""+sequence.toString()+"\" already exists in DNA Tree.");
	}

}