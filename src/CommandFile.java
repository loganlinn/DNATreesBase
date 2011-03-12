import java.io.File;
import java.util.ArrayList;

/**
 * Represents and parses a command file passed to the program
 * @author loganlinn
 *
 */
public class CommandFile {
	private String commandFilePath;
	private ArrayList<Command> commands;
	
	/**
	 * Constructs a CommandFile given the path to a command file
	 * @param path
	 */
	public CommandFile(String path){
		commandFilePath = path;
		
	}
	
	/**
	 * Parses the command file
	 */
	public void parse() {
		File commandFile = new File(commandFilePath);
		
		
	}

}
