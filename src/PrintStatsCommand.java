/**
 * This PrintOperation is the same as a normal print, but additionally appends the letter break down for each sequence
 * 
 * @author loganlinn
 *
 */
public class PrintStatsCommand extends PrintCommand{
	/*
	 * Change the mode property to print in stats mode
	 */
	public PrintStatsCommand(){
		this.mode = PRINT_MODE_STATS;
	}
	
}
