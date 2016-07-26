import java.util.HashMap;

public class Dictionary {

	/*
	 * Since a trie node can have so many children, the children it has are
	 * stored in a map.
	 */
	private HashMap<String, String> myStartLetters;
	public Dictionary() {

		myStartLetters = new HashMap<>();
	}

	/**
	 * Associates the input word with the input definition in the dictionary.
	 */
	public void addDefinition(String word, String definition) {
		// YOUR CODE
	    myStartLetters.put(word, definition);
	}

	/**
	 * Return the definition associated with this word in the Dictionary. Return
	 * null if there is no definition for the word.
	 */

	private class TrieNode {
		private HashMap<Character, TrieNode> myNextLetters;
		// Leave this null if this TrieNode is not the end of a complete word.
		private String myDefinition;

		private TrieNode() {
			myNextLetters = new HashMap<>();
		}
	}

	public String lookupDefinition(String word) {
		// YOUR CODE HERE
		return myStartLetters.get(word);
	}
}