
import java.util.Map;

/**
 * Sequence interface represents a DNA sequence that can be used to 
 * interact with a DNA sequence regardless of how it is implemented (linked-list, char array, etc)
 * 
 * @author loganlinn
 * 
 */
public interface Sequence {
	public static final char[] ALPHABET = {'A','C','G','T'};
	public static final String RE_ALPHABET = "[ACGT]+";
	public char next();				// provides next character in sequence
	public char prev();				// provides previous character in sequence 
	public char current();			// provides current character in sequence
	public boolean hasNext();		// returns true if able to move the current character forward
	public boolean hasPrev();		// returns true if able to move the current character backward
	public String getSequence();	// provides the sequence as a String
	public boolean isPrefixOf(Sequence otherSequence); // determines if this sequence is a prefix of the otherSequence
	public int length();			// provides length of sequence
	public String stats();			// provides formatting string with percentages of character representation
}
