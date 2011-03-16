/**
 * I
 * 
 * @author loganlinn
 * 
 */
public class InternalNode implements Node {
	public static final int MIN_NON_EMPTY_LEAF_CHILREN = 2;

	/*
	 * Children references
	 */
	private Node A = null;
	private Node C = null;
	private Node G = null;
	private Node T = null;
	private Node $ = null;

	/**
	 * Constructs an InternalNode given the SequenceNode to be pushed down and
	 * the new Sequence. Internal nodes are always created with exactly 2
	 * sequences (one of which is an existing SequenceNode)
	 * 
	 * @param existingSequence
	 * @param newSequence
	 */
	public InternalNode(SequenceLeafNode existingSequenceNode,
			Sequence newSequence) {
		// get reference to existing node's sequence
		final Sequence existingSequence = existingSequenceNode.getSequence();

		if (existingSequence.hasNext()) {
			final char existingSequenceChar = existingSequence.next();

			// Set the appropriate child with the existing node. Give an error
			// if we are unable to locate child
			if (!setChild(existingSequenceChar, existingSequenceNode)) {
				InsertOperation.invalidSequence(existingSequence);
				return;
			}

		} else { // Existing sequence must be a prefix
					// Assumes existingSequence != newSequence
			$ = existingSequenceNode;

		}
		// Fill rest of children with flyweight
		fillEmptyChildren();

		// Insert the new sequence
		insert(newSequence);
	}

	/**
	 * Fills empty child references with references to flyweight. Always called
	 * from constructor
	 */
	private void fillEmptyChildren() {
		if (A == null) {
			A = EmptyLeafNode.getInstance();
		}
		if (C == null) {
			C = EmptyLeafNode.getInstance();
		}
		if (G == null) {
			G = EmptyLeafNode.getInstance();
		}
		if (T == null) {
			T = EmptyLeafNode.getInstance();
		}
		if ($ == null) {
			$ = EmptyLeafNode.getInstance();
		}
	}

	/**
	 * Sets the appropriate child given a letter of the DNA alphabet. If the
	 * character isn't valid, returns false NOTE: Character validation should
	 * happen before getting this far. We could have already modified the tree
	 * with erronous data
	 * 
	 * @param sequenceChar
	 * @param child
	 * @return
	 */
	public boolean setChild(char sequenceChar, Node child) {
		switch (sequenceChar) {
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
	 * Gets the child node given a character of the sequence, consequently
	 * defining the alphabet.
	 * 
	 * @param sequenceCharacter
	 * @return
	 * @throws Exception
	 */
	protected Node getChild(char sequenceCharacter) {
		switch (sequenceCharacter) {
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
		PrintOperation.printInternalNode(level);
		// Visit children left to right: indent
		level++;
		A.print(level, mode);
		C.print(level, mode);
		G.print(level, mode);
		T.print(level, mode);
		$.print(level, mode);
	}

	/**
	 * Insert a sequence in one of this node's children
	 * 
	 * @return the Node that should replace this Node OR self to keep the same
	 */
	@Override
	public Node insert(Sequence sequence) {
		if (sequence.hasNext()) {
			// Take the next character
			final char sequenceChar = sequence.next();
			// Get the associated child node
			Node child = getChild(sequenceChar);

			// Determine if we have a prefix sequence
			// - Check again (after taking sequenceChar) sequence has more
			// characters
			// - Check if we have other non-empty children that would prevent
			// prefix on this level:
			// -- If we have a prefix, but other children, we need to expand by
			// inserting into the SequenceNode
			if (!sequence.hasNext()
					&& (numNonEmptyLeafChildren() < MIN_NON_EMPTY_LEAF_CHILREN)) {
				// TODO: Determine if there's a case where we should swap a
				// currently assigned child with prefix
				insertPrefix(sequence);
			} else {
				/*
				 * Assign the child to the result of inserting into it: - If
				 * child is SequenceNode, insert will return the replacement
				 * InternalNode - If child is Flyweight, insert will return new
				 * SequenceNode - If child is InternalNode, insert will return
				 * the same InternalNode
				 */
				setChild(sequenceChar, child.insert(sequence));
			}
		} else {
			// We have looked at all characters in sequence. It must be a prefix
			// of parent
			// Check if parent's prefix child is already defined. If it is, we
			// have duplicate sequences
			// Compare the parent's $ to the flyweight to determine if its empty

			// TODO: Confirm we set the prefix here, or try to set to a child.
			// Do we know child is empty?
			insertPrefix(sequence);
		}
		// InternalNode are static for insert, so return this (nothing changes
		// in parent)
		return this;
	}

	/**
	 * 
	 * @return the Node that should replace this Node OR self to keep the same
	 */
	@Override
	public Node remove(Sequence sequence) {
		if (sequence.hasNext()) {
			// Take the next character
			final char sequenceChar = sequence.next();
			// Get the associated child node
			Node child = getChild(sequenceChar);
			/*
			 * Call remove on the child - possible outcomes: - Removes the
			 * Sequence by assigning child to EmptyLeafNode (when child is
			 * SequenceNode) - Fails to remove because the sequence isn't in the
			 * tree (when child is SequenceNode or EmptyLeafNode) - Refines the
			 * search to find the Sequence we are trying to remove (when child
			 * is InternalNode)
			 */
			setChild(sequenceChar, child.remove(sequence));
		} else {
			// We have looked at all of the characters -- if we have the
			// sequence, its this prefix
			$ = $.remove(sequence);
		}

		/*
		 * Determine if we need to collapse by checking if we have only 1
		 * non-empty leaf node
		 */
		Node collapsible = null;
		for (Node child : getChildren()) {
			// We cannot collapse if a child is an InternalNode
			if (child instanceof InternalNode) {
				return this;
			} else if (child instanceof SequenceLeafNode) {
				// If we find more than 1 SequenceLeafNode, we can't collapse
				if (collapsible == null) {
					collapsible = child;
				} else {
					return this;
				}
			}

		}
		/*
		 * If we havn't returned: - We are going to collapse - collapsible is a
		 * SequenceLeafNode Move the pointer in the sequence back so comparisons
		 * are made at the correct character
		 */
		((SequenceLeafNode) collapsible).getSequence().prev();
		return collapsible;
	}

	@Override
	public void search(SearchOperation searchData) {
		// Count this node as visisted
		searchData.incrementNodesVisited();

		final Sequence searchSequence = searchData.getSearchSequence();

		// Check if we have seen all of the characters in the searchSequence
		if (searchSequence.hasNext()) {
			/*
			 * We have not gone down far enough to determine if we have matches,
			 * continue search into tree
			 */
			final char currentSearchChar = searchSequence.next();
			// Get the corresponding child node
			Node child = getChild(currentSearchChar);
			// Call search on child
			child.search(searchData);
		} else if (searchData.matchExact()) {
			/*
			 * Check to see if the prefix is a match when we do not have any
			 * more characters in search sequence, the prefix is our last chance
			 * to find a match
			 */
			$.search(searchData);
		} else {
			/*
			 * searchSequence.hasNext()==false AND we aren't finding exact match
			 * This means we have traversed down the the search path, so all
			 * non-empty leaf-nodes must be matches (searchSequence is a prefix
			 * to everything below)
			 */
			A.search(searchData);
			C.search(searchData);
			G.search(searchData);
			T.search(searchData);
			$.search(searchData); // search the prefix too
		}
	}

	/**
	 * Counts the number of children that are not EmptyLeafNodes or
	 * InternalNodes
	 * 
	 * @return
	 */
	private int numNonEmptyLeafChildren() {
		int nonEmptyChildren = 0;
		if (A instanceof SequenceLeafNode) {
			nonEmptyChildren++;
		}
		if (C instanceof SequenceLeafNode) {
			nonEmptyChildren++;
		}
		if (G instanceof SequenceLeafNode) {
			nonEmptyChildren++;
		}
		if (T instanceof SequenceLeafNode) {
			nonEmptyChildren++;
		}

		// TODO: determine if we count $ here
		return nonEmptyChildren;
	}

	/**
	 * Gets an array of child nodes
	 * 
	 * @return a Node array containing the children
	 */
	private Node[] getChildren() {
		return new Node[] { A, C, G, T, $ };
	}

	/**
	 * Sets the prefix child, $.
	 * 
	 * @param sequence
	 */
	public void insertPrefix(Sequence sequence) {
		// Ensure that the prefix is empty
		if ($ instanceof EmptyLeafNode) {
			$ = new SequenceLeafNode(sequence);
		} else {
			// Prefix isn't empty, this should indicate a duplicate sequence
			InsertOperation.duplicateSequence(sequence);
		}
	}

	/**
	 * @return the a
	 */
	public Node getA() {
		return A;
	}

	/**
	 * @param a
	 *            the a to set
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
	 * @param c
	 *            the c to set
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
	 * @param g
	 *            the g to set
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
	 * @param t
	 *            the t to set
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
	 * @param $
	 *            the $ to set
	 */
	public void set$(Node $) {
		this.$ = $;
	}
}
