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
 * Represents and parses a command file passed to the program
 * 
 * @author loganlinn open the file
 * 
 *         and create a LinkedList
 * 
 *         and start filling it with Operations
 * 
 *         we will call that function from P2's main method
 */
public class CommandFile {
	private static final String INSERT_COMMAND = "insert";
	private static final String REMOVE_COMMAND = "remove";
	private static final String PRINT_COMMAND = "print";
	private static final String SEARCH_COMMAND = "search";
	private static final String PRINT_LENGTHS_ARGUMENT = "lengths";
	private static final String PRINT_STATS_ARGUMENT = "stats";
	private static final String SEARCH_EXACT_SUFFIX = "$";
	private String commandFilePath;
	private Queue<Operation> commandList;
	private String firstSequence;

	/**
	 * Constructs a CommandFile given the path to a command file
	 * 
	 * @param path
	 */
	public CommandFile(String path) {
		commandFilePath = path;
	}

	/**
	 * 
	 * @param tokenizer
	 * @return
	 */
	private String getNextArgument(StringTokenizer tokenizer) {
		if (tokenizer.hasMoreTokens()) {
			return tokenizer.nextToken();
		} else {
			return null;
		}
	}

	/**
	 * Parses the command file
	 * 
	 * @throws SequenceException
	 * @throws IOException
	 * @throws P2Exception
	 */
	public void parse() throws SequenceException, IOException, P2Exception {
		commandList = new LinkedList<Operation>();

		File commandFile = new File(this.commandFilePath);
		FileInputStream fileStream;

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new DataInputStream(new FileInputStream(commandFile))));
		String line, argument;
		boolean commandHasArgument;
		while ((line = br.readLine()) != null) {
			StringTokenizer lineTokens = new StringTokenizer(line);
			if (lineTokens.hasMoreTokens()) {
				String command = lineTokens.nextToken();

				if (INSERT_COMMAND.equals(command)) {
					/*
					 * Insert command
					 */
					argument = getNextArgument(lineTokens);
					commandList.add(new InsertOperation(argument));
				} else if (REMOVE_COMMAND.equals(command)) {
					/*
					 * Remove command
					 */
					argument = getNextArgument(lineTokens);
					commandList.add(new RemoveOperation(argument));
				} else if (PRINT_COMMAND.equals(command)) {
					/*
					 * Print command, find the mode
					 */
					argument = getNextArgument(lineTokens);
					if (argument == null) {
						// regular print command
						commandList.add(new PrintOperation());
					} else if (PRINT_LENGTHS_ARGUMENT.equals(argument)) {
						// print lengths command
						commandList.add(new PrintLengthsOperation());
					} else if (PRINT_STATS_ARGUMENT.equals(argument)) {
						// print stats command
						commandList.add(new PrintStatsOperation());
					} else {
						throw new P2Exception("Unknown print command");
					}
				} else if (SEARCH_COMMAND.equals(command)) {
					argument = getNextArgument(lineTokens);
					// Check the sequenceDescriptor if it has a $ suffix
					if (argument != null) {
						if (argument.endsWith(SEARCH_EXACT_SUFFIX)) {
							commandList.add(new ExactSearchOperation(argument
									.substring(0, argument.length() - 2)));
						} else {
							commandList.add(new SearchOperation(argument));
						}
					}
				} else {
					throw new P2Exception("Unknown command, " + command);
				}

			}
		}
		if (!commandList.isEmpty()) {
			Operation firstOperation = commandList.remove();
			if (firstOperation instanceof InsertOperation) {
				firstSequence = ((InsertOperation) firstOperation)
						.getSequence().toString();
			} else {
				throw new P2Exception("First command must be an insert.");
			}
		}
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
	public Queue<Operation> getCommandList() {
		return commandList;
	}

	/**
	 * @param commandList
	 *            the commandList to set
	 */
	public void setCommandList(Queue<Operation> commandList) {
		this.commandList = commandList;
	}

	/**
	 * @return the firstSequence
	 */
	public String getFirstSequence() {
		return firstSequence;
	}

	/**
	 * @param firstSequence
	 *            the firstSequence to set
	 */
	public void setFirstSequence(String firstSequence) {
		this.firstSequence = firstSequence;
	}
}
