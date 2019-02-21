import java.util.ArrayList;
import java.util.List;

public class Trie {

	// instance vars
	private TrieNode root;
	private TrieNode traversalHelper;
	private String currentInput;
	private int numKeysToValidInput;

	public Trie(List<String> words) {
		root = new TrieNode();
		traversalHelper = root;
		currentInput = "";
		numKeysToValidInput = 0;
		constructTree(words);
	}

	public void constructTree(List<String> words) {
		TrieNode currentNode = root;
		for (String word : words) {
			currentNode = root;
			for (int i = 0; i < word.length(); i++) {
				char letter = word.charAt(i);
				boolean completesWord = i == word.length() - 1;
				TrieNode child = new TrieNode(letter, completesWord, currentNode);
				boolean added = currentNode.addChild(child);
				if (added) {
					currentNode = child;
				} else {
					currentNode = currentNode.getChild(letter);
				}
			}
		}
	}

	public boolean updateTriePositionAdd(char input) {
		currentInput = currentInput + input;
		if (traversalHelper != null) {
			TrieNode nextNode = traversalHelper.getChild(input);
			if (nextNode != null) {
				traversalHelper = nextNode;
				return true;
			} else {
				numKeysToValidInput++;
				return false;
			}
		} else {
			numKeysToValidInput++;
			return false;
		}
	}

	public boolean updateTriePositionDelete() {
		if (currentInput.length() > 0) {
			currentInput = currentInput.substring(0, currentInput.length() - 1);
			numKeysToValidInput--;
			if (numKeysToValidInput < 0) {
				traversalHelper = traversalHelper.getParent();
				numKeysToValidInput = 0;
			}
			return true;
		} else {
			return false;
		}
	}

	public List<String> findPossibleWords() {
		List<String> possibleWords = new ArrayList<>();
		if (currentInput.length() > 0 && numKeysToValidInput == 0) {
			findPossibleWordsHelper(currentInput, traversalHelper, possibleWords);
		}
		// Collections.sort(possibleWords);
		return possibleWords;
	}

	private void findPossibleWordsHelper(String currentWord, TrieNode currentNode, List<String> possibleWords) {
		if (currentNode != null) {
			if (currentNode.isCompletingLetter()) {
				possibleWords.add(currentWord);
			}
			for (TrieNode child : currentNode.getChildren()) {
				char letterStored = child.getLetterStored();
				String newCurrentWord = currentWord + letterStored;
				findPossibleWordsHelper(newCurrentWord, child, possibleWords);
			}
		}
	}
}
