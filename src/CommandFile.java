import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Represents and parses a command file passed to the program
 * @author loganlinn
 * open the file

and create a LinkedList

and start filling it with Operations

we will call that function from P2's main method
 */
public class CommandFile {
	private String commandFilePath;
	private ArrayList<Command> commands;
	private ArrayList<String> commandList;
	
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
		File commandFile = new File(this.commandFilePath);
		FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(commandFile);
			DataInputStream dataInputStream = new DataInputStream(fileStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream));
			String line;
			while ((line = br.readLine()) != null)	{
				this.commandList.add(line);
			}
			for(int i = 0; i < this.commandList.size(); i++)	{
				String temp = this.commandList.get(i);
				StringTokenizer a = new StringTokenizer(temp);
				temp = a.nextToken();
				if(temp == "insert")	{
					temp = a.nextToken().trim();
					// add correct value check!
					this.commandList.add(i, "insert "+temp);
				}
				else if (temp == "remove")	{
					temp = a.nextToken().trim();
					this.commandList.add(i, "remove "+temp);
				}
				else if (temp == "print")	{
					if (a.hasMoreTokens())	{
						temp = a.nextToken().trim();
						this.commandList.add(i, "print "+temp);
					}
					else	{
						this.commandList.add("print");
					}
				}
				else if (temp == "search")	{
					temp = a.nextToken().trim();
					this.commandList.add(i, "search "+temp);
				}
				else	{
					//There is a problem with the file if this happens I'm pretty sure.
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
