/**
 * 
 * @author loganlinn
 *
 */
public class ExactSearchOperation extends SearchOperation {
	/**
	 * Construct an ExactSearchOperation given a sequenceDescriptor and pass the appropriate superclass' 
	 * @param sequenceDescriptor
	 */
	private int mode = SEARCH_MODE_EXACT;
	
	public ExactSearchOperation(String sequenceDescriptor){
		super(sequenceDescriptor);
	}
}
