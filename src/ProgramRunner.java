import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramRunner {

	public static void main(String[] args) {

		List<String> words = readWords("d3.txt");
		Trie dictionaryTrie = new Trie(words);
		GUIManager manager = new GUIManager(dictionaryTrie);
		manager.showGUI();
	}

	private static List<String> readWords(String dictionaryName) {
		// create list
		List<String> toReturn;
		// must execute code in a try catch block to ensure no error occurs.
		try {
			// initialize a scanner object to the dictionary file.
			Scanner s = new Scanner(new File(dictionaryName));
			// initialize the list.
			toReturn = new ArrayList<String>();
			// loop goes through each line in the file.
			while (s.hasNextLine()) {
				String word = s.nextLine().trim();
				// ensure the word is not blank.
				if (word.length() > 0) {
					toReturn.add(word);
				}
			}
			// close the scanner to prevent any leak.
			s.close();
		} catch (Exception e) {
			System.out.println("I'm sorry");
			toReturn = null;
		}
		return toReturn;
	}

}
