/**
 * 
 * @author loganlinn
 *
 */
public class ExactSearchOperation extends SearchOperation {
	private int mode = SEARCH_MODE_EXACT; // Override the default search mode with exact mode
	
	/**
	 * Construct an ExactSearchOperation given a sequenceDescriptor and pass the appropriate superclass' 
	 * @param sequenceDescriptor
	 * @throws SequenceException 
	 */
	public ExactSearchOperation(String sequenceDescriptor) throws SequenceException{
		super(sequenceDescriptor);
	}
}
