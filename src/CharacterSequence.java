
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


public class CharacterSequence implements Sequence{
	private int position = 0;			// Stores the index of the next unseen character in sequence
	private final char[] characters;	// Sequence characters
	
	/**
	 * Constructs a sequence given a string of sequence characters
	 * 
	 * @param sequenceCharacters
	 */
	public CharacterSequence(String sequenceInput) {
		characters = sequenceInput.toCharArray();
	}
	
	@Override
	public Map<Character, Double> stats() {
		HashMap<Character, Double> stats = new HashMap<Character, Double>();
		// Iterate through all characters in sequence, count the number of occurances
		for(char c : characters){
			Double count;
			if(!stats.containsKey(c)){	// Add it for the first time if it doesnt exist
				count = 1.0;
			}else{
				count = stats.get(c);
			}
			stats.put(c, count);
		}
		
		// Divide sums by total size
		final int length = length();
		for(Map.Entry<Character, Double> entry : stats.entrySet()){
			
		}
		
		return null;
	}
	
	/**
	 * Returns character at current position
	 */
	@Override
	public char current(){
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		return characters[position-1];
	}
	
	/**
	 * Gets next character in sequence
	 * @return
	 */
	public char next(){
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		return characters[position++];
	}
	
	/**
	 * Moves the position back one character. Used when a SequenceNode moves up an level when deleting a sequence.
	 */
	@Override
	public char prev() {
		if(!hasPrev()){
			throw new NoSuchElementException();
		}
		return characters[--position];
	}

	/**
	 * Returns true if has characters in sequence have not been seen
	 * @return
	 */
	public boolean hasNext(){
		return (position < characters.length);
	}
	
	/**
	 * Returns true if sequence can move back one position
	 */
	@Override
	public boolean hasPrev(){
		return position > 0;
	}
	
	/**
	 * Returns the length of the sequence
	 * @return
	 */
	@Override
	public int length(){
		return characters.length;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { 
		return new String(characters);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Sequence){
			return (this.toString() == ((Sequence) obj).toString());
		}
		return super.equals(obj);
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String getSequence() {
		return characters.toString();
	}

	/**
	 * @return the characters
	 */
	public char[] getCharacters() {
		return characters;
	}

}
