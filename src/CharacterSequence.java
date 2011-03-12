import java.util.NoSuchElementException;


public class CharacterSequence implements Sequence{
	private int position = 0;		// Stores the current position in the sequence to compare during insert
	private final char[] characters;	// Sequence characters
	
	/**
	 * Constructs a sequence given a string of sequence characters
	 * 
	 * @param sequenceCharacters
	 */
	public CharacterSequence(String sequenceInput) {
		characters = sequenceInput.toCharArray();
	}
	
	/**
	 * Returns character at current position
	 */
	@Override
	public char current(){
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		return characters[position];
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
	public int length(){
		return characters.length;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { 
		return characters.toString();
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
