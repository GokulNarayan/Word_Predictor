
// import statements.
import java.util.ArrayList;
import java.util.List;

public class TrieNode {

	// instance vars:
	private char letterStored;
	private boolean completesWord;
	private TrieNode parent;
	private List<TrieNode> children;

	// constants:
	private final char ROOT_CHAR = '~';

	// constructor used for root of the trie.
	public TrieNode() {
		// root is a special node. does not hold any letter.
		letterStored = ROOT_CHAR;
		completesWord = false;
		parent = null;
		// initialize list.
		children = new ArrayList<TrieNode>();
	}

	// constructor:
	public TrieNode(char letterStored, boolean completesWord, TrieNode parent) {
		// update all instance vars:
		this.letterStored = letterStored;
		this.completesWord = completesWord;
		this.parent = parent;
		// initialize the list
		children = new ArrayList<TrieNode>();
	}

	// add comments
	public boolean addChild(TrieNode child) {
		if (!containsChild(child.letterStored)) {
			return children.add(child);
		}
		return false;
	}

	private boolean containsChild(char nodeLetter) {
		boolean result = false;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).letterStored == nodeLetter) {
				return true;
			}
		}
		return result;
	}

	// accessors:

	public TrieNode getParent() {
		return parent;
	}

	public boolean isCompletingLetter() {
		return completesWord;
	}

	public char getLetterStored() {
		return letterStored;
	}

	public TrieNode getChild(char nodeLetter) {
		for (TrieNode n : children) {
			if (n.letterStored == nodeLetter) {
				return n;
			}
		}
		return null;
	}

	public List<TrieNode> getChildren() {
		return children;
	}

}
