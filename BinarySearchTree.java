package com.berkanish;
import java.lang.String;

public class BinarySearchTree 
{
	public Node root;
	public BinarySearchTree()
	{
		this.root = null;
	}
	public String search(String name )
	{
		String value;
		Node current = root;
		while(current!=null)
		{
			if(current.name.equals(name))
			{
				value = current.value;
				return value;
			}
			else if(current.name.compareTo(name) > 0)
			{
				current = current.left;
			}
			else
			{
				current = current.right;
			}
		}
		return null;
	}
	public boolean delete(String name)
	{
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(!current.name.equals(name))
		{
			parent = current;
			if(current.name.compareTo(name)>0)
			{
				isLeftChild = true;
				current = current.left;
			}
			else
			{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null)
			{
				return false;
			}
		}
//if i am here that means we have found the node
//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null)
		{
			if(current==root)
			{
				root = null;
			}
			if(isLeftChild ==true)
			{
				parent.left = null;
			}
			else
			{
				parent.right = null;
			}
		}
//Case 2 : if node to be deleted has only one child
		else if(current.right==null)
		{
			if(current==root)
			{
				root = current.left;
			}
			else if(isLeftChild)
			{
				parent.left = current.left;
			}
			else
			{
				parent.right = current.left;
			}
		}
		else if(current.left==null)
		{
			if(current==root)
			{
				root = current.right;
			}
			else if(isLeftChild)
			{
				parent.left = current.right;
			}
			else
			{
				parent.right = current.right;
			}
		}
		else if(current.left!=null && current.right!=null)
		{
//now we have found the minimum element in the right sub tree
			Node successor = getSuccessor(current);
			if(current==root)
			{
				root = successor;
			}
			else if(isLeftChild)
			{
				parent.left = successor;
			}
			else
			{
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}
	public Node getSuccessor(Node deleleNode)
	{
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null)
		{
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
//check if successor has the right child, it cannot have left child for sure
// if it does have the right child, add it to the left of successorParent.
// successsorParent
		if(successsor!=deleleNode.right)
		{
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	public void insert(String name, String value)
	{
		Node newNode = new Node(name, value );
		if(root==null)
		{
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true)
		{
			parent = current;
			if(current.name.compareTo(name)>0)
			{
				current = current.left;
				if(current==null)
				{
					parent.left = newNode;
					return;
				}
			}
			else
			{
				current = current.right;
				if(current==null)
				{
					parent.right = newNode;
					return;
				}
			}
		}
	}
	public void display(Node root)
	{
		if(root!=null)
		{
			display(root.left);
			String outstr = " " + root.name;
			outstr+=": ";
			outstr+=root.value;
			System.out.println(outstr);
			display(root.right);
		}
	}
	/*public static void main(String arg[]){
		BinarySearchTree b = new BinarySearchTree();
		b.insert("Apple", "is a fruit");
		b.insert("Ferrari", "is a car");
		b.insert("mansion", "is a big house");
		b.insert("whale", "is a mammal");
		b.insert("cat", "is an animal");
		b.insert("ant", "is an insect");
		System.out.println("Original Tree : ");
		b.display(b.root);
		System.out.println("");
		System.out.println("Check whether Node with value Ferrari exists : " + b.search("Ferrari"));
		b.display(b.root);
		System.out.println("\n Delete Node ant: " + b.delete("ant"));
		b.display(b.root);
		System.out.println("\n Delete Node Apple: " + b.delete("Apple"));
		b.display(b.root);
	}*/
}

	class Node
	{
		String name;
		String value;
		Node left;
		Node right;
		public Node(String name, String value)
		{
			this.name = name;
			this.value = value;
			left = null;
			right = null;
		}
	}
