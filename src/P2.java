import java.io.IOException;
import java.io.PrintStream;

/**
 * === CS3114 Project 2 - DNA Trees ===
 * 
 * DUE Thursday, March 17, 2011, 11:00 PM
 * 
 * == DESCRIPTION ==
 * 
 * This program stores, searches, and outputs DNA sequences in a DNA Tree data
 * structure. The DNA tree is a 5-way branching tree wherein DNA sequences are
 * stored in the tree's leaf nodes.
 * 
 * There are 3 types of Nodes: InternalNode, SequenceLeafNode, EmptyLeafNode
 * 
 * InternalNodes - This node has 5 children nodes; one for each character of the
 * DNA alphabet, A, C, G, T. The remaining child can store a sequence that is a
 * prefix to the other sequence in the node. Moreover, InternalNodes that store
 * a prefix, may contain only 1 other child.
 * 
 * EmptyLeafNode - This node implements a flyweight design. A single
 * EmptyLeafNode is used to represent all empty children. It is instantiated at
 * the beginning of the program and exists until the end of execution.
 * 
 * SequenceLeafNode - This node stores a DNA sequence and is the only type of
 * non-empty leaf node. A tree begins with a single SequenceLeafNode. This is
 * the only type of node that can be inserted or deleted by a command.
 * 
 * The tree's hierarchy is similar to that of a trie. All the descendants of a
 * node have a common prefix of the DNA sequence associated with that node.
 * 
 * TODO: Describe command objects
 * 
 * == USAGE ==
 * 
 * P2 <command-file>
 * 
 * == COMMAND FILE ==
 * 
 * The command file contains a list of commands and sequences descriptors to
 * control the DNA tree operations. The valid commands are defined in the
 * following section. The command file must contain at least 1 insert statement.
 * Sequences described in commands must only contain characters in the DNA
 * alphabet (A, C, G, T). The parser is tolerant of spaces or other whitespace
 * in the command file, however commands are separated by new line characters.
 * 
 * == COMMANDS ==
 * 
 * 1) insert <sequence-descriptor>
 * 
 * 2) remove <sequence-descriptor>
 * 
 * 3) print
 * 
 * 4) print lengths
 * 
 * 5) print stats
 * 
 * 6) search <sequence-descriptor>
 * 
 * 
 * @author loganlinn
 * @author matthewibarra
 */
public class P2 {
	/*
	 * On my honor:
	 * 
	 * - I have not used source code obtained from another student, or any other
	 * unauthorized source, either modified or unmodified.
	 * 
	 * - All source code and documentation used in my program is either my
	 * original, or was derived by me from the source code published in the
	 * textbook for this course.
	 * 
	 * - I have not discussed coding details about this project with anyone
	 * other than my partner (in the case of a join submission), instructor,
	 * ACM/UPE tutors or the TAs assigned to this course. I understand that I
	 * may discuss the concepts of this program with other students, and that
	 * other students may help me debug my program so long as neither of us
	 * writes anything during the discussion or modifies any computer file
	 * during the discussion. I have violated neither in the spirit nor the
	 * letter of this restriction.
	 */
	/**
	 * Logan Linn COMPILER: Eclipse JDT OS: Mac OS X 10.6.6
	 * 
	 * Matthew Ibarra COMPILER: Eclipse JDT OS: Mac OS X 10.6.6
	 * 
	 * DATE-COMPLETED: 03/16/2010
	 * 
	 * @param args
	 */
	public static void main(String[] arg) {
		// if(arg.length < 1){
		// System.out.println("usage: P2 <command-file>");
		// return;
		// }
		// String commandFilePath = arg[0];
		String commandFilePath = "P2sampleinput.txt";
		CommandFile commandFile = new CommandFile(commandFilePath);
		/**
		 * Attempt to parse the command file, catching any errors
		 */
		try {
			commandFile.parse();
		} catch (SequenceException e) {
			System.out.println(e.getMessage());
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (P2Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		/*
		 * Create tree with the first sequence
		 */
		Tree dnaTree = new Tree(commandFile.getFirstSequence());

		/*
		 * Execute the remaining commands from the command file
		 */
		dnaTree.executeOperations(commandFile.getCommandList());
	}
}
