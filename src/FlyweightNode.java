/**
 * Flyweight represents a leaf node. Singleton pattern is used for a single 
 * flyweight object is used for all empty leaf nodes in the tree.
 * 
 * @author loganlinn
 *
 */
public class FlyweightNode implements Node{
	
	private static FlyweightNode flyweight = null;	// Single flyweight for all empty leaf nodes
	
	/**
	 * Singleton access to the flyweight
	 * @return
	 */
	public static FlyweightNode getInstance(){
		if(flyweight == null){
			flyweight = new FlyweightNode();
		}
		return flyweight;
	}
	
	/**
	 * Constructs a Flyweight
	 * Private constructor for Singleton pattern.
	 * Can only be instantiated internally
	 * @see FlyweightNode#getInstance()
	 */
	private FlyweightNode(){
		
	}
}
