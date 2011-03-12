import java.util.NoSuchElementException;

/**
 * Sequence interface represents a DNA sequence that can be used to 
 * interact with a DNA sequence regardless of how it is implemented (linked-list, char array, etc)
 * 
 * @author loganlinn
 * 
 */
public interface Sequence {
	public char next();				// returns next character in sequence
	public char prev();
	public char current();
	public boolean hasNext();		// returns true if characters in sequence have not been seen
	public boolean hasPrev();
	public String getSequence();	// returns the sequence as a String
}
