/**
 * Base node class
 * @author loganlinn
 *
 */
public interface Node {
	void print();					// prints node representation to P2.out
	void insert(Sequence sequence);	// inserts the given sequence into the tree
	void delete(Sequence sequence);	// deletes the given sequence from the tree
}
