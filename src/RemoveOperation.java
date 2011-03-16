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
	public Node execute(Node root) {
		return root.remove(createSequence(sequenceDescriptor));
	}
	
	/**
	 * Output a message when a sequence is not found when trying to remove
	 * @param sequence
	 */
	public static void sequenceNotFound(Sequence sequence){
		out.println("Could not find sequence, \""+sequence+"\", to remove.");
	}
}
