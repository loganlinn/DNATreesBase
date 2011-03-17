/**
 * SequenceException is thrown when an invalid sequence is detected (used during
 * parsing)
 * 
 * @author loganlinn
 * 
 */
public class SequenceException extends Exception {
	public SequenceException(String sequenceDescriptor) {
		super("Invalid sequence, \"" + sequenceDescriptor + "\"");
	}
}