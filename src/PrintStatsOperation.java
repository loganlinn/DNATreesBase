/**
 * This PrintOperation is the same as a normal print, but additionally appends the letter break down for each sequence
 * 
 * @author loganlinn
 *
 */
public class PrintStatsOperation extends PrintOperation{
	/*
	 * Change the mode property to print in stats mode
	 */
	public PrintStatsOperation(){
		this.mode = PRINT_MODE_STATS;
	}
	
}
