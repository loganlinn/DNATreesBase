/**
 * This PrintOperation is the same as a normal print, but additionally appends the length of each sequence
 * 
 * @author loganlinn
 *
 */
public class PrintLengthsOperation extends PrintOperation{
	/*
	 * Change the mode property to print in lengths mode
	 */
	public PrintLengthsOperation(){
		this.mode = PRINT_MODE_LENGTHS;
	}
}
