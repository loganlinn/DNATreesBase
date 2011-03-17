/**
 * 
 * @author loganlinn
 *
 */
public class ExactSearchCommand extends SearchCommand {
	private int mode = SEARCH_MODE_EXACT; // Override the default search mode with exact mode
	
	/**
	 * Construct an ExactSearchOperation given a sequenceDescriptor and pass the appropriate superclass' 
	 * @param sequenceDescriptor
	 * @throws SequenceException 
	 */
	public ExactSearchCommand(String sequenceDescriptor) throws SequenceException{
		super(sequenceDescriptor);
	}
}
