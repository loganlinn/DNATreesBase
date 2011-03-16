
/**
 * Base node class
 * @author loganlinn
 *
 */
public abstract class Node {
	public abstract void print(int level, int mode);	// prints node representation to P2.out
	public abstract Node insert(Sequence sequence);	// inserts the given sequence into the tree
	public abstract Node remove(Sequence sequence);	// deletes the given sequence from the tree
	public abstract void search(SearchOperation searchData);
}
