/**
 * 
 * @author loganlinn
 *
 */
public class PrintOperation extends Operation{
	private static final int ROOT_LEVEL = 0;
	public static final int PRINT_MODE_NORMAL = 0;		// Passed to print for normal print mode
	public static final int PRINT_MODE_LENGTHS = 1;		// Passed to print for lengths print mode
	public static final int PRINT_MODE_STATS = 2;		// Passed to print for stats print mode
	
	private int mode;
	
	public PrintOperation(int mode){
		this.mode = mode;
	}
	
	@Override
	public void execute(Node root) {
		root.print(ROOT_LEVEL, mode);
	}

}
