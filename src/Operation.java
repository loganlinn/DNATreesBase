/**
 * TreeOperation 
 * - Encapsulates DNA tree operations extracted from a command file
 * - Enables us to represent a command file as a queue of TreeOperations 
 * 
 * @author loganlinn
 *
 */
public abstract class Operation {
	/**
	 * Descendants implement execute to perform operations on the DNA Tree
	 * @param root
	 */
	public abstract Node execute(Node root);
	
	/**
	 * A factory method for controlling the implementation of the Sequence interface
	 * 
	 * @param sequenceDescriptor
	 * @return
	 */
	protected static Sequence createSequence(String sequenceDescriptor){
		return new ArraySequence(sequenceDescriptor);
	}
	
	public class SequenceException extends Exception{
		public SequenceException(String sequenceDescriptor){
			super("Invalid sequence, \""+sequenceDescriptor+"\"");
		}
	}
}
