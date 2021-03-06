import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Represents and parses a command file passed to the program. - Parse the
 * commands in command file for logical correctness - Create and collect Command
 * objects for the Tree - Notify the program of any other parsing errors
 * 
 * @author loganlinn
 * 
 */
public class CommandFile {
	/* Literal Constants */
	private static final String INSERT_COMMAND = "insert";
	private static final String REMOVE_COMMAND = "remove";
	private static final String PRINT_COMMAND = "print";
	private static final String SEARCH_COMMAND = "search";
	private static final String PRINT_LENGTHS_ARGUMENT = "lengths";
	private static final String PRINT_STATS_ARGUMENT = "stats";
	private static final String SEARCH_EXACT_SUFFIX = "$";
	private static final String NO_INSERT_COMMAND_FOUND_ERROR = "Command file must contain an insert command";
	private static final String UNKNOWN_COMMAND_ERROR_PREFIX = "Unknown command, ";
	private static final String UNKNOWN_PRINT_MODE_ERROR_PREFIX = "Unknown print mode, ";
	private static final String LINE_NUMBER_MESSAGE_PREFIX = "(Line ";
	private static final String LINE_NUMBER_MESSAGE_SUFFIX = ")";
	
	private String commandFilePath; // Path to command file
	private Queue<Command> commandList; // Collection of commands extracted from
										// command file
	private int lineNumber = 0; // Tracks which line of the command file we are parsing
	
	/**
	 * Constructs a CommandFile given the path to a command file
	 * 
	 * @param path
	 */
	public CommandFile(String path) {
		commandFilePath = path;
	}

	/**
	 * Checks if the tokenizer has more tokens. If it does, return the next
	 * token, otherwise return null
	 * 
	 * @param tokenizer
	 * @return
	 */
	private String getNextArgument(StringTokenizer tokenizer) {
		if (tokenizer.hasMoreTokens()) {
			return tokenizer.nextToken();
		}
		return null;
	}

	/**
	 * Parses the command file
	 * Throws an appropriate exception if an error is encountered
	 * Checks for the following errors:
	 * - Invalid character in sequence
	 * - Unknown command
	 * - Expected argument missing
	 *  
	 * @throws SequenceException
	 * @throws IOException
	 * @throws P2Exception
	 */
	public void parse() throws SequenceException, IOException, P2Exception {
		commandList = new LinkedList<Command>();

		File commandFile = new File(this.commandFilePath);
		FileInputStream fileStream;

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new DataInputStream(new FileInputStream(commandFile))));
		String line, argument;
		boolean commandHasArgument;
		while ((line = br.readLine()) != null) {
			lineNumber++;
			StringTokenizer lineTokens = new StringTokenizer(line);
			if (lineTokens.hasMoreTokens()) {
				String command = lineTokens.nextToken();

				if (INSERT_COMMAND.equals(command)) {
					/*
					 * Insert command
					 */
					argument = getNextArgument(lineTokens);
					commandList.add(new InsertCommand(argument));
				} else if (REMOVE_COMMAND.equals(command)) {
					/*
					 * Remove command
					 */
					argument = getNextArgument(lineTokens);
					commandList.add(new RemoveCommand(argument));
				} else if (PRINT_COMMAND.equals(command)) {
					/*
					 * Print command, find the mode
					 */
					argument = getNextArgument(lineTokens); // argument (optional) is the print mode
					if (argument == null) {
						// regular print command
						commandList.add(new PrintCommand());
					} else if (PRINT_LENGTHS_ARGUMENT.equals(argument)) {
						// print lengths command
						commandList.add(new PrintLengthsCommand());
					} else if (PRINT_STATS_ARGUMENT.equals(argument)) {
						// print stats command
						commandList.add(new PrintStatsCommand());
					} else {
						throw new P2Exception(UNKNOWN_PRINT_MODE_ERROR_PREFIX+argument+getLineNumberMessage());
					}
				} else if (SEARCH_COMMAND.equals(command)) {
					/*
					 * Search command, find the mode
					 */
					argument = getNextArgument(lineTokens); // argument is a sequence descriptor
					
					// Check the sequenceDescriptor if it has a $ suffix
					if (argument != null) {
						if (argument.endsWith(SEARCH_EXACT_SUFFIX)) {
							// Create an exact search command, add it to the command queue
							commandList.add(new ExactSearchCommand(argument
									.substring(0, argument.length() - 2)));
						} else {
							// Create a normal search command, add it to the command queue
							commandList.add(new SearchCommand(argument));
						}
					}
				} else {
					// The command isn't recognized, throw an exception
					throw new P2Exception(UNKNOWN_COMMAND_ERROR_PREFIX
							+ command+getLineNumberMessage());
				}
			}
		}
		
	}
	private String getLineNumberMessage(){
		return LINE_NUMBER_MESSAGE_PREFIX + lineNumber + LINE_NUMBER_MESSAGE_SUFFIX;
	}
	/**
	 * @return the commandFilePath
	 */
	public String getCommandFilePath() {
		return commandFilePath;
	}

	/**
	 * @param commandFilePath
	 *            the commandFilePath to set
	 */
	public void setCommandFilePath(String commandFilePath) {
		this.commandFilePath = commandFilePath;
	}

	/**
	 * @return the commandList
	 */
	public Queue<Command> getCommandList() {
		return commandList;
	}

	/**
	 * @param commandList
	 *            the commandList to set
	 */
	public void setCommandList(Queue<Command> commandList) {
		this.commandList = commandList;
	}

}
