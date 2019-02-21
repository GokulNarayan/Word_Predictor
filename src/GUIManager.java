
// import statements
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GUIManager {

	// global variables:
	private Frame frame;
	private Label header;
	private Label predictions;
	private Panel controlPanel;
	private TextField textField;
	private Trie myTrie;

	// class constants:
	final int ASCII_a = 97;
	final int ASCII_z = 123;

	public GUIManager(Trie trie) {
		myTrie = trie;
		setupGUI();
	}

	private void setupGUI() {
		// set the title of the window.
		frame = new Frame("Trie Experiment");
		// set the size of the window.
		frame.setSize(400, 400);
		// add
		frame.setLayout(new GridLayout(3, 1));

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wEvent) {
				System.exit(0);
			}
		});

		header = new Label();
		header.setAlignment(Label.CENTER);
		predictions = new Label();
		predictions.setAlignment(Label.CENTER);
		predictions.setSize(350, 100);

		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());

		frame.add(header);
		frame.add(predictions);
		frame.add(controlPanel);
		frame.setVisible(true);
	}

	public void showGUI() {
		header.setText("Word Suggester Proto:");
		predictions.setText("");
		// provide enough columns for the text field.
		textField = new TextField(20);
		// add the key listener to the text field.
		// (any key pressed when sending input to the text field is registered)
		textField.addKeyListener(new CustomKeyListener());

		controlPanel.add(textField);
		frame.setVisible(true);
	}

	class CustomKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				if (myTrie.updateTriePositionDelete()) {
					List<String> possibleWords = myTrie.findPossibleWords();
					if (possibleWords.size() > 0)
						predictions.setText(getText(possibleWords));
					else
						predictions.setText("");
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// System.out.println("Key released");
		}

		@Override
		public void keyTyped(KeyEvent e) {
			char input = e.getKeyChar();
			input = Character.toLowerCase(input);
			if ((int) input >= ASCII_a && e.getKeyCode() <= ASCII_z) {
				if (myTrie.updateTriePositionAdd(input)) {
					List<String> possibleWords = myTrie.findPossibleWords();
					if (possibleWords.size() > 0)
						predictions.setText(getText(possibleWords));
					else
						predictions.setText("");
				}
			}

		}

		private String getText(List<String> possibleWords) {
			int maxNumWords = 3;
			int index = 0;
			int wordsSelected = 0;
			StringBuilder wordsContainer = new StringBuilder();
			while (index < possibleWords.size() && wordsSelected < maxNumWords) {
				wordsContainer.append(possibleWords.get(index) + " ");
				index++;
				wordsSelected++;
			}
			return wordsContainer.toString();
		}

	}
}
