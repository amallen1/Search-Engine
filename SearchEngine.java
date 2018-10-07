import java.util.ArrayList;
import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (P10 Search Engine)
// Files:           (UTF-8)
// Course:          (300, Spring, and 2018)
//
// Author:          (Aniya Allen)
// Email:           (aallen27@wisc.edu)
// Lecturer's Name: (Gary Dahl)
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * The purpose of this class is a search engine in which users
 * can enter ID's and weblinks.
 * 
 * @author aniyaallen
 *
 */
public class SearchEngine {

	private WebPageNode root; // root of the BST-based search engine

	// Constructor that creates an empty search engine
	public SearchEngine() {
		root = null;
	};

	/**
	 * Main Method where the execution of the search engine takes place
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Scanner object to read in input
		Scanner scnr = new Scanner(System.in);

		// Stores the user's input command
		char command = ' ';

		// Do while condition
		boolean continueSearch = true;

		// Creates a search engine object 
		SearchEngine search = new SearchEngine();

		do {
			
			//Begins the search engine
			System.out.println();
			System.out.println("=========================== Search Engine ============================");
			System.out.println("1. Enter 'i <id> <webLink>' to insert a web page in the search engine");
			System.out.println("2. Enter 's <id>' to search a web link in the search engine");
			System.out.println("3. Enter 'p' to print all the web page ids in the search engine");
			System.out.println("4. Enter 'c' to get the count of all web pages in the search engine");
			System.out.println("5. Enter 'q' to quit the program");
			System.out.println("======================================================================");
			System.out.println();
			System.out.print("Please enter your command:");

			// stores the user's input for insert and search methods
			String[] temp = scnr.nextLine().trim().split("\\s+");

			// Get's the user's command in order to execute the corresponding method
			command = temp[0].charAt(0);

			// for i, if throw indexOutOfBounds (ex user types in just 'i') so catch
			// exception
			// and print error statement
			// also s will throw indexOutBounds exception

			// Temp[1] is the web ID
			// Temp[2] is the weblink
			
			
			try {
				
				//Inserts an id and weblink into the search engine
				if (command == 'i' || command == 'I') {

					search.insert(temp[1], temp[2]);
				}
				
				//Searches for id in the search engine
				else if (command == 's' || command == 'S') {
				
					System.out.println(temp[1] + " - " + search.searchWebPage(temp[1]));
					
					
				}
				
				//Print all the webpage ids currently in the search engine
				else if (command == 'p' || command == 'P') {

					// When the search engine has no pages, print this message
					if (search.getAllWebPages().isEmpty()) {
						System.out.println("Search Engine is empty");
					} else {
						
						//Prints out the ids
						for(int i = 0; i < search.getAllWebPages().size(); i++) {
							System.out.print(search.getAllWebPages().get(i));
							if(i != search.getAllWebPages().size() - 1) {
								System.out.print(", ");
							}
						}
						System.out.println("");
						                                     
					}
				}
				
				//Gets the count of webpages in the search engine
				else if (command == 'c' || command == 'C') {
					System.out.println(search.getWebPageCount());
					continueSearch = true;
				}

				else if (command == 'q' || command == 'Q') {

					// Quits the program
					continueSearch = false; 

					System.out.println("============================== END ===================================");
				}

				else {
					// If none of the proper commands are typed in, prints warning
					System.out.println("WARNING: Unrecognized command.");
				}

				//If user tries to insert duplicate web page
			} catch (IllegalArgumentException e) {
				System.out.println("WARNING: failed to insert duplicate web page: " + "<" + temp[1] + ">");
				
				//Error message for blank entry
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("WARNING: failed to insert web page: Id/web link can’t be blank!");
			}

		} while (continueSearch); 
	}

	/**
	 * Returns true if the search engine is empty, 
	 * false if otherwise
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Inserts an instance of WebPageNode and weblink into the search engine
	 * conforming to the search order property of a BST.
	 * 
	 * @param id
	 * @param webLink
	 */
	public void insert(String id, String webLink) {
		// Instance of WebPageNode
		WebPageNode node = new WebPageNode(id, webLink);

		// If the tree is empty, assign the node to the root position
		if (root == null) {
			root = node;
		} else {
			// Starts at the root and traverses onwards
			insertHelper(root, node);
		}

	}

	/**
	 * Insert helper method finds a spot for the node you're adding to the
	 * search engine and helps to conform to the search order property of 
	 * a BST the tree
	 * 
	 * @param node
	 * @param nodeToAdd
	 */
	private void insertHelper(WebPageNode node, WebPageNode nodeToAdd) {

		//If the id being added matches one already in the tree,
		//throw an exception
		if (node.getId().equals(nodeToAdd.getId())) {
			throw new IllegalArgumentException();
		}

		//If the node being added is less than the node its being compared to
		//it's in the left subtree
		if (nodeToAdd.getId().compareTo(node.getId()) < 0) {

			// If there is no left child, set the nodeToAdd to become the left child
			if (node.getLeftChild() == null) {
				node.setLeftChild(nodeToAdd);
			} else {
				// If there is a left child, perform recursive call on the node's left child
				// until a spot is found

				insertHelper(node.getLeftChild(), nodeToAdd);
			}

		} else if (nodeToAdd.getId().compareTo(node.getId()) > 0) {

			// If there is no right child, set the nodeToAdd to become the right child
			if (node.getRightChild() == null) {
				node.setRightChild(nodeToAdd);
			} else {
				// If there is a right child, perform recursive call on the node's right child
				// until a spot is found

				insertHelper(node.getRightChild(), nodeToAdd);
			}
		}

	}

	/**
	 * Search method that returns a weblink of the matching id that is
	 * passed in
	 */
	public String searchWebPage(String id) {
		
		String temp;
		//Variable that stores the string returned from searchHelper
		temp = searchHelper(root, id);
		
		//If no match is found, return error message
		if(temp == ""){
			return "No web link found for the web page " + "<" + id + ">";
		}
		
		//Print the id and matching weblink
		else {
			return searchHelper(root, id);
		}
		
		

	}

	/**
	 * SearchHelper that finds the matching id's weblink
	 * if it exists or not
	 * 
	 * @param node
	 * @param id
	 * @return
	 */
	private String searchHelper(WebPageNode node, String id) {

		//If there is an id that matches return the weblink
		if(node.getId().equals(id)) {
			return node.getWebLink();
		}
		
		
		// if the value of the id is less than the node, search in it's left child
		if (id.compareTo(node.getId()) < 0) {
			if(node.getLeftChild() != null) {
				return searchHelper(node.getLeftChild(), id);
			}
		}
		
		//If value is greater, search in it's right child
		if(id.compareTo(node.getId()) > 0) {
			
			if(node.getRightChild() != null) {
				return searchHelper(node.getRightChild(), id);
			}
		}
		
		//If no matching id is found return empty string
		return "";
		

	}

	/**
	 * Webpage count method returns number of webpages(nodes) in the
	 * search engine
	 * 
	 * @return
	 */
	public int getWebPageCount() {

		return webPageCountHelper(root);

	}

	/**
	 * Helper WebPageCounter method that returns the number of nodes in 
	 * the search engine
	 * 
	 * @param node
	 * @return
	 */
	private int webPageCountHelper(WebPageNode node) {
		int count = 0; // counter

		//If there is a node, count it
		if (node != null) {
			count++;
		}

		// Base case to stop recursion, if a null node is reached,
		// the counting is done
		if (node == null) {
			return count;
		} else {

			// Continues down the root's left subtree
			if (node.getLeftChild() != null) {

				count += webPageCountHelper(node.getLeftChild());
			}

			// Continues down the root's right subtree
			if (node.getRightChild() != null) {

				count += webPageCountHelper(node.getRightChild());
			}
		}

		return count;
	}

	/**
	 * Method that returns the webpage ids that are currently
	 * in the search engine 
	 * 
	 * @return
	 */
	public ArrayList<String> getAllWebPages() {
		
		// Creates a new arraylist to store the ID values
		ArrayList<String> IDs = new ArrayList<String>();

		return getAllWebPagesHelper(root, IDs);
	}

	/**
	 * Helper method for getAllWebPages
	 * Returns an ArrayList with current webpage ids in it
	 * in sorted order
	 * 
	 * @param node
	 * @param IDs
	 * @return
	 */
	private ArrayList<String> getAllWebPagesHelper(WebPageNode node, ArrayList<String> IDs) {

		// Base case if there is no next node to perform the recursive call on
		if (node == null) {
			return IDs;
		} else {

			if (node.getLeftChild() != null) {
				getAllWebPagesHelper(node.getLeftChild(), IDs);
			}

			if (node.getId() != null) {
				IDs.add(node.getId());
			}

			if (node.getRightChild() != null) {
				getAllWebPagesHelper(node.getRightChild(), IDs);
			}

			return IDs;
		}
	}

}
