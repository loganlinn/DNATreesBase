
/**
 * I
 * 
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
	public InternalNode(SequenceLeafNode existingSequenceNode, Sequence newSequence){
		final Sequence existingSequence = existingSequenceNode.getSequence(); // get reference to existing node's sequence
		
		if(existingSequence.hasNext()){
			final char existingSequenceChar = existingSequence.next();
			
			// Set the appropriate child with the existing node. Give an error if we are unable to locate child
			if(!setChild(existingSequenceChar, existingSequenceNode)){
				P2.Error.invalidSequence(existingSequence);
				return;
			}
			
		}else{ // Existing sequence must be a prefix
			// Assumes existingSequence != newSequence
			$ = existingSequenceNode;
			
		}
		// Fill rest of children with flyweight
		fillEmptyChildren();
		
		// Insert the new sequence 
		insert(newSequence);
	}
	
	/**
	 * Fills empty child references with references to flyweight.
	 * Always called from constructor
	 */
	private void fillEmptyChildren(){
		if(A == null){
			A = EmptyLeafNode.getInstance();
		}
		if(C == null){
			C = EmptyLeafNode.getInstance();
		}
		if(G == null){
			G = EmptyLeafNode.getInstance();
		}
		if(T == null){
			T = EmptyLeafNode.getInstance();
		}
		if($ == null){
			$ = EmptyLeafNode.getInstance();
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
	
	/**
	 * Insert
	 */
	@Override
	public Node insert(Sequence sequence) {
		if(sequence.hasNext()){
			// Take the next character
			final char sequenceChar = sequence.next();
			// Get the associated child node
			Node child = getChild(sequenceChar);
			
			// Determine if we have a prefix sequence
			// 	Check if child isn't empty and sequence doesn't has more characters
			//	If we have a prefix, but other children, we need to expand by inserting into SequenceNode (not set prefix yet)
			if(!sequence.hasNext() && (numNonEmptyLeafChildren() < 1)){
				setPrefix(sequence);
			}else{
				// Assign the child to the result of inserting into it:
				// 	If child is SequenceNode, insert will return the replacement InternalNode
				// 	If child is Flyweight, insert will return new SequenceNode
				// 	If child is InternalNode, insert will return the same InternalNode
				setChild(sequenceChar, child.insert(sequence));
			}
		}else{
			// We have looked at all characters in sequence. It must be a prefix of parent
			// Check if parent's prefix child is already defined. If it is, we have duplicate sequences
			// Compare the parent's $ to the flyweight to determine if its empty 
//			if(parent == null){
//				// If parent is null, we must be root, and if the sequence doesn't have any characters, it must be empty
//				P2.Error.invalidSequence(sequence);
//			}else{
//				((InternalNode) parent).setPrefix(sequence);
//			}
			// TODO: Confirm we set the prefix here
			setPrefix(sequence);
		}
		// InternalNode are static for insert, so return this (nothing changes in parent)
		return this;
	}
	
	/**
	 * Counts the number of children that are not EmptyLeafNodes or InternalNodes
	 * @return
	 */
	private int numNonEmptyLeafChildren(){
		int nonEmptyChildren = 0;
		if(A instanceof SequenceLeafNode){
			nonEmptyChildren++;
		}
		if(C instanceof SequenceLeafNode){
			nonEmptyChildren++;
		}
		if(G instanceof SequenceLeafNode){
			nonEmptyChildren++;
		}
		if(T instanceof SequenceLeafNode){
			nonEmptyChildren++;
		}
		
		// TODO: determine if we count & here
		return nonEmptyChildren;
	}
	
	/**
	 * 
	 */
	@Override
	public Node remove(Sequence sequence) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Sets the prefix child, $.
	 * 
	 * @param sequence
	 */
	public void setPrefix(Sequence sequence){
		// Ensure that the prefix is empty
		if($ instanceof EmptyLeafNode){
			$ = new SequenceLeafNode(sequence);
		}else{
			// Prefix isn't empty, this should indicate a duplicate sequence
			P2.Error.duplicateSequence(sequence);
		}
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
