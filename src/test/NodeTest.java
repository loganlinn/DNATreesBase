package test;

public class NodeTest {
	static abstract class Node{
		static LeafNode flyweight = new LeafNode("flyweight");
		abstract void insert(Node parent, String value);
	}
	
	static class InternalNode extends Node{
		LeafNode left = flyweight;
		LeafNode right = flyweight;

		@Override
		void insert(Node parent, String value) {
			// TODO Auto-generated method stub
			if(left == flyweight){
				left = new LeafNode(value);
			}else{
				left.insert(this, value);
			}
		}
	}
	
	static class LeafNode extends Node{
		String value;
		LeafNode(String value){
			this.value = value;
		}
		
		@Override
		void insert(Node parent, String value) {
			parent = new LeafNode(value);
			System.out.println("\t"+value);
			((LeafNode)parent).value = value;
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InternalNode root = new InternalNode();
		root.insert(root, "A");
		System.out.println(root.left.value);
	}

}
