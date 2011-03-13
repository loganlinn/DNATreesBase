/**
 * 
 * If ____ the InternalNode collapses into a SequenceNode
 * @author loganlinn
 *
 */
public class InternalNode extends Node{
	/*
	 * Children references
	 */
	private Node A = null;
	private Node C = null;
	private Node G = null;
	private Node T = null;
	private Node $ = null;
	
	/**
	 * Constructs an InternalNode given the SequenceNode to be pushed down and the new Sequence.
	 * Internal nodes are always created with exactly 2 sequences (one of which is an existing SequenceNode)
	 * 
	 * @param existingSequence
	 * @param newSequence
	 */
	public InternalNode(SequenceNode existingSequenceNode, Sequence newSequence){
		final Sequence existingSequence = existingSequenceNode.getSequence(); // get reference to existing node's sequence
		if(existingSequence.hasNext()){
			final char existingSequenceChar = existingSequence.next();
			
			// Set the appropriate child with the existing node. Give an error if we are unable to locate child
			if(!setChild(existingSequenceChar, existingSequenceNode)){
				P2.Error.invalidSequence(existingSequence);
			}
			
			// Fill rest of children with flyweight
			fillEmptyChildren();
			
			// Insert the new sequence 
			insert(this, newSequence);
			
		}else{ // Existing sequence must be a prefix
			// Assumes existingSequence != newSequence
			$ = existingSequenceNode;
		}
	}
	
	/**
	 * Fills empty child references with references to flyweight.
	 * Always called from constructor
	 */
	private void fillEmptyChildren(){
		if(A == null){
			A = LeafNode.getEmptyLeafNode();
		}
		if(C == null){
			C = LeafNode.getEmptyLeafNode();
		}
		if(G == null){
			G = LeafNode.getEmptyLeafNode();
		}
		if(T == null){
			T = LeafNode.getEmptyLeafNode();
		}
		if($ == null){
			$ = LeafNode.getEmptyLeafNode();
		}
	}
	
	/**
	 * Sets the appropriate child given a letter of the DNA alphabet. If the character isn't valid, returns false
	 * NOTE: Character validation should happen before getting this far. We could have already modified the tree with erronous data 
	 * 
	 * @param sequenceChar
	 * @param child
	 * @return
	 */
	public boolean setChild(char sequenceChar, Node child){
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
	protected Node getChild(char sequenceCharacter){
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
		return null;// This type of error should already be handled
	}
	
	/**
	 * Preorder traversal
	 */
	@Override
	public void print(int level, int mode) {
		// Visit self
		indentedPrint(level, INTERNAL_NODE);
		// Visit children left to right: indent
		level++;
		A.print(level, mode);
		C.print(level, mode);
		G.print(level, mode);
		T.print(level, mode);
		$.print(level, mode);
	}
	
	@Override
	public void insert(Node parent, Sequence sequence) {
		if(sequence.hasNext()){
			final char sequenceChar = sequence.next();
			
			// Get the associated child node
			Node child = getChild(sequenceChar);
			
			// Error check that we had a valid child name
//			if(child == null){
//				P2.out.println("Invalid sequence, "+sequence+". Contains character not in DNA alphabet.");
//				return;
//			}
			
			child.insert(this, sequence);
			
			
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
