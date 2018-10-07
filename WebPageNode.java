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
 * This class represents the web pages for the search engine.
 * @author aniyaallen
 *
 */
public class WebPageNode{
 
    private final String id; 		// The id of the web page
	private final String webLink;   // The web link of the web page
    private WebPageNode leftChild;  // The leftChild of the the current WebPageNode
    private WebPageNode rightChild; // The rightChild of the the current WebPageNode
 
    //Constructor that creates a webpage node
    public WebPageNode(String id, String webLink) { 
    		this.id = id;
    		this.webLink= webLink;
    		leftChild = null;
    		rightChild = null;
    		
     } 
   
    
	// Add public setters and getters methods
    /**
     * Gets WebPageNode's left child
     * @return leftChild
     */
	public WebPageNode getLeftChild() {
		return leftChild;
	}
	
	/**
	 * Sets the WebPageNode's left child
	 * @param leftChild
	 */
	public void setLeftChild(WebPageNode leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * Gets the WebPageNode's right child
	 * @return rightChild
	 */
	public WebPageNode getRightChild() {
		return rightChild;
	}
	
	/**
	 * Sets the WebPageNode's right child
	 * @param rightChild
	 */
	public void setRightChild(WebPageNode rightChild) {
		this.rightChild = rightChild;
	};
	
	/**
	 * Gets the WebPageNode's string id
	 * @return
	 */
	public String getId() {
		return id;
	};
	
	/**
	 * Gets the WebPageNode's weblink
	 * @return
	 */
	public String getWebLink() {
		return webLink;
	};
	
}
