
import java.io.IOException;
import java.io.PrintStream;

/**
 * CS3114 Project 2 - DNA Trees
 * 
 * DUE
 * 	Thursday, March 17, 2011, 11:00 PM
 * 
 * DESCRIPTION
 * 
 * 	This program stores, searches, and outputs DNA sequences in a DNA
 * 	Tree data structure. The DNA tree is a 5-way branching tree wherein DNA
 * 	sequences are stored in the tree's leaf nodes and all internal nodes empty
 * 
 * TODO: Describe flyweight...
 * 
 * TODO: Describe command objects
 * 
 * USAGE
 * 
 * 	P2 <command-file>
 * 
 * 		COMMANDS
 * 
 * 		insert <sequence>
 * 
 * 		remove <sequence>
 * 
 * 		print
 * 
 * 		print lengths
 * 
 * 		print stats
 * 
 * 		search <sequence-descriptor>
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
	 * Logan Linn
	 * COMPILER:	Eclipse JDT
	 * OS: 			Mac OS X 10.6.6
	 * 
	 * Matthew Ibarra
	 * COMPILER:	Eclipse JDT
	 * OS:			Mac OS X 10.6.6
	 * 
	 * DATE-COMPLETED: 03/16/2010
	 * 
	 * @param args
	 */
	public static void main(String[] arg) {
//		if(arg.length < 1){
//			System.out.println("usage: P2 <command-file>");
//			return;
//		}
//		String commandFilePath = arg[0];
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
