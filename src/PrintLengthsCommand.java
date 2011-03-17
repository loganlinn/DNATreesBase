/**
 * This PrintOperation is the same as a normal print, but additionally appends the length of each sequence
 * 
 * @author loganlinn
 *
 */
public class PrintLengthsCommand extends PrintCommand{
	/*
	 * Change the mode property to print in lengths mode
	 */
	public PrintLengthsCommand(){
		this.mode = PRINT_MODE_LENGTHS;
	}
}
