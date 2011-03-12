/**
 * 
 * If ____ the InternalNode collapses into a SequenceNode
 * @author loganlinn
 *
 */
public class InternalNode implements Node{
	
	private Node A = null;
	private Node C = null;
	private Node G = null;
	private Node T = null;
	private Node $ = null;
	
	/**
	 * Constructs an InternalNode given the SequenceNode to be pushed down and the new Sequence.
	 * Internal nodes are always created with exactly 2 sequences
	 * 
	 * @param existingSequence
	 * @param newSequence
	 */
	public InternalNode(SequenceNode existingSequenceNode, Sequence newSequence){
		final Sequence existingSequence = existingSequenceNode.getSequence(); // get reference to existing node's sequence
		if(existingSequence.hasNext()){
			final char existingSequenceChar = existingSequence.next();
			
			if(!setChild(existingSequenceChar, existingSequenceNode)){
				P2.out.println("Invalid sequence, "+existingSequence+". Contains character not in DNA alphabet.");
			}
			
			
			
			insert(newSequence);
			
		}else{ // Existing sequence must be a prefix
			// Assumes existingSequence != newSequence
			$ = existingSequenceNode;
		}
	}
	
	private fillWithFlyweight(){
		if(A == null)
	}
	
	/**
	 * Sets the appropriate child given a letter of the DNA alphabet. If the character isn't valid, returns false
	 * NOTE: Character validation should happen before getting this far. We could have already modified the tree with erronous data 
	 * 
	 * @param sequenceChar
	 * @param child
	 * @return
	 */
	private boolean setChild(char sequenceChar, Node child){
		switch(sequenceChar){
		case 'A':
			A = child;
			return true;
		case 'C':
			C = child;
			return true;
		case 'G':
			G = child;
			return true;
		case 'T':
			T = child;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the child node given a character of the sequence, consequently defining the alphabet.
	 * 
	 * @param sequenceCharacter
	 * @return
	 * @throws Exception
	 */
	private Node getChild(char sequenceCharacter){
		switch(sequenceCharacter){
		case 'A':
			return A;
		case 'C':
			return C;
		case 'G':
			return G;
		case 'T':
			return T;
		}
		return null;// Throw exception? Current no; minimize stack overhead in recursive operations 
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insert(Sequence sequence) {
		if(sequence.hasNext()){
			final char sequenceChar = sequence.next();
			
			// Get the associated child node
			Node child = getChild(sequenceChar);
			
			// Error check that we had a valid child name
			if(child == null){
				P2.out.println("Invalid sequence, "+sequence+". Contains character not in DNA alphabet.");
				return;
			}
			
			if(child instanceof InternalNode){
				
				child.insert(sequence);	// Traverse further into tree
				
			}else if(child instanceof FlyweightNode){
				
				child = new SequenceNode(sequence);	// Replace flyweight with new sequence leaf node
				
			}else{ // Child is instanceof SequenceNode
				// Expand the tree to fit new sequence
				if(sequence == ((SequenceNode)child).getSequence()){
					P2.out.println("Invalid sequence, "+sequence+". Sequence already exists.");
					return;
				}
				child = new InternalNode((SequenceNode) child, sequence);
			}
		}else{
			// We have looked at all characters in sequence. It must be a prefix
			set$(new SequenceNode(sequence));
		}
		
	}
	
	@Override
	public void delete(Sequence sequence) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the a
	 */
	public Node getA() {
		return A;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(Node a) {
		A = a;
	}
	/**
	 * @return the c
	 */
	public Node getC() {
		return C;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(Node c) {
		C = c;
	}
	/**
	 * @return the g
	 */
	public Node getG() {
		return G;
	}
	/**
	 * @param g the g to set
	 */
	public void setG(Node g) {
		G = g;
	}
	/**
	 * @return the t
	 */
	public Node getT() {
		return T;
	}
	/**
	 * @param t the t to set
	 */
	public void setT(Node t) {
		T = t;
	}
	/**
	 * @return the $
	 */
	public Node get$() {
		return $;
	}
	/**
	 * @param $ the $ to set
	 */
	public void set$(Node $) {
		this.$ = $;
	}
}
