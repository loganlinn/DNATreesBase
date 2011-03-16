/**
 * RemoveOperation executes the root node's remove operation to delete an existing sequence from the tree.
 * @author loganlinn
 *
 */
public class RemoveOperation extends Operation {
	private String sequenceDescriptor;
	
	/**
	 * Construct a RemoveOperation given a sequenceDescriptor
	 * @param sequenceDescriptor
	 */
	public RemoveOperation(String sequenceDescriptor){
		this.sequenceDescriptor = sequenceDescriptor;
	}
	
	/**
	 * Call the remove method on the tree's root
	 */
	@Override
	public void execute(Node root) {
		root.remove(createSequence(sequenceDescriptor));
	}

}
