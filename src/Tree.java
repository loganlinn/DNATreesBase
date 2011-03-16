import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author loganlinn
 *
 */
public class Tree{
	private final static int ROOT_LEVEL = 0; // Numerical value for level of the root node
	private Node root = null;	// Root of tree
	
	/**
	 * Constructs a Tree object given a sequence. 
	 * The first node in the tree is always a SequenceNode
	 * 
	 * @param sequenceDescriptor
	 */
	public Tree(String sequenceDescriptor){
		createRoot(sequenceDescriptor);
	}
	
	private void createRoot(String sequenceDescriptor){
		root = new SequenceLeafNode(new ArraySequence(sequenceDescriptor));
	}
	
	/**
	 * Executes a queue of Operation objects
	 * @param operations
	 */
	public void executeOperations(Queue<Operation> operations){
		// Require that operations isn't null and has Operations
		if(operations == null || operations.size() < 1){
			return;
		}
		// Iterate over all operations and call the execute method
		for(Operation operation : operations){
			root = operation.execute(root);
		}
	}
	
	/**
	 * Sample test: http://courses.cs.vt.edu/~cs3114/Spring11/P2sampleinputTree.pdf
	 */
	public static void main(String[] args) {	
		Tree dnaTree = new Tree("AAAA");
		
		Queue<Operation> sampleOperations = new LinkedList<Operation>();
		
		sampleOperations.add(new InsertOperation("ACGT"));
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new InsertOperation("AA"));
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new InsertOperation("AAACCCCGGTGAAAACGTA"));
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new InsertOperation("ACTGGGAA"));
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new RemoveOperation("ACGT"));
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new InsertOperation("ACCTT"));		
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new InsertOperation("ACTTA"));
//		sampleOperations.add(new PrintOperation());
		
		sampleOperations.add(new InsertOperation("TATA"));
//		sampleOperations.add(new PrintOperation());		
		
		sampleOperations.add(new InsertOperation("TCG"));
//		sampleOperations.add(new PrintOperation());
		
//		sampleOperations.add(new PrintLengthsOperation());
//		sampleOperations.add(new PrintStatsOperation());
		
		sampleOperations.add(new ExactSearchOperation("AAAA"));
		sampleOperations.add(new SearchOperation("AA"));
		sampleOperations.add(new ExactSearchOperation("ACGT"));
		
		dnaTree.executeOperations(sampleOperations);
		
	}
}
