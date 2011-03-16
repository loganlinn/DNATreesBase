
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.NoSuchElementException;


public class ArraySequence implements Sequence{
	private int position = 0;			// Stores the index of the next unseen character in sequence
	private final char[] characters;	// Sequence characters
	
	/**
	 * Constructs a sequence given a string of sequence characters
	 * 
	 * @param sequenceCharacters
	 */
	public ArraySequence(String sequenceInput) {
		characters = sequenceInput.toCharArray();
	}
	
	@Override
	public String stats() {
		
		char[] alphabet = Sequence.ALPHABET;
		double[] averages = new double[alphabet.length];	// we can assume values are initialized to 0
		
		// Count the number of times each character of the alphabet appears
		for(char c : characters){
			for(int i = 0; i < alphabet.length; i++){
				if(alphabet[i] == c){
					averages[i]++;	// increment the sum
					break;		// we have found a match, no need to compare rest of characters in alphabet
				}
			}
		}
		// Divide the sums by total to get the average
		int totalCharacters = characters.length;
		for(int i = 0; i < averages.length; i++){
			averages[i] = (averages[i] / totalCharacters) * 100;
		}
		
		// Create format string
		NumberFormat formatter = new DecimalFormat("0.00");
		String formattedStrings = "", delimiter = ", ";
		for(int i = 0; i < alphabet.length; i++){
			formattedStrings += alphabet[i]+ "(" + formatter.format(averages[i]) + ")"+delimiter;
		}
		
		return formattedStrings.substring(0, formattedStrings.length() - delimiter.length());
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


	/**
	 * Compare with another Sequence 
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Sequence){
			return (this.toString().equals(((Sequence) obj).toString()));
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
