import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dsai.core.Iterator;
import dsai.core.List;
import dsai.core.Position;
import dsai.impl.LinkedList;
import dsai.impl.ListIterator;
import dsaii.core.Map;
import dsaii.core.Tree;
import dsaii.impl.ChainMap;
import dsaii.impl.LinkedTree;

/**
 * This class implements the dictionary component of the predictive text module
 * of our mobile phone demonstrator. Underlying the implementation is a tree
 * data structure in which each node contains:
 * 
 * <ul>
 * <li>an integer value representing the current keystroke
 * <li>a list of strings that contains the word fragments that correspond to the
 * sequence of keystrokes in the path from the root node to that node.
 * </ul>
 * 
 * This node data is modelled through the inner Keystroke class.
 * 
 * @author remcollier
 */
public class Dictionary {
	/**
	 * This class represents the contents of each node in the tree-based
	 * implementation of the dictionary. Each node basically represents a single
	 * keystroke, this class associates that keystroke with a list of words the
	 * sequence of keystrokes corresponding to the path from the root node to
	 * this node.
	 */
	private class Keystroke {
		int key;
		TreeMap<String, Double> words;

		/**
		 * Constructor for the Keystroke class, that takes an integer (the
		 * keystroke) as a parameter.
		 * 
		 * @param key
		 */
		public Keystroke(int key) {
			this.key = key;
			words = new TreeMap<String, Double>();
		}

		/**
		 * Add another word to this node (this means that the word is a
		 * potential word for the combination of keystrokes that matches the
		 * path from the root node to this node).
		 * 
		 * @param word
		 */
		public void addWord(String word) {

			if(words.get(word) !=null){
				double i = words.get(word);
				words.put(word, i++);
			}else{
				words.put(word, 1.0);
			}
		}
	

		/**
		 * Return the list of words that is associated with this keystroke. The
		 * current implementation does not impose any ordering on the list (it
		 * is built based on the order in which words are inserted into the
		 * node). In part B of the assignment, you will need to modify this
		 * method to return an ordered list of words.
		 * 
		 * @return a list of words
		 */
		public List<String> getWords() {
			List<String> wordsToReturn = new LinkedList<String>();
			double lastKey=0;
			double currentKey;
			
			for(String s : words.descendingKeySet()){
				currentKey = words.get(s);
				if(currentKey > lastKey){
					wordsToReturn.insertFirst(s);
				}else{
					wordsToReturn.insertLast(s);
				}
				lastKey=currentKey;
			}
			
			return wordsToReturn;
		}

		/**
		 * Generate a string representation of the node data for outputing of
		 * the state of the tree during testing.
		 * 
		 * @return
		 */
		@Override
		public String toString() {
			StringBuffer buf = new StringBuffer();
			buf.append(key);
			buf.append(":");
			for(String s : words.keySet()){
					buf.append(" ");
					buf.append(s + " (p:" + words.get(s) + ")");
			}
			
			
			return buf.toString();
		}
	}

	/**
	 * This map associates characters with keystrokes and is used by the
	 * insertion algorithm to work out how to add words to the tree.
	 */
	private static Map<Character, Integer> characterMap;

	/**
	 * Initialization block for the characterMap
	 */
	static {
		characterMap = new ChainMap<Character, Integer>();
		characterMap.put('a', 2);
		characterMap.put('b', 2);
		characterMap.put('c', 2);
		characterMap.put('d', 3);
		characterMap.put('e', 3);
		characterMap.put('f', 3);
		characterMap.put('g', 4);
		characterMap.put('h', 4);
		characterMap.put('i', 4);
		characterMap.put('j', 5);
		characterMap.put('k', 5);
		characterMap.put('l', 5);
		characterMap.put('m', 6);
		characterMap.put('n', 6);
		characterMap.put('o', 6);
		characterMap.put('p', 7);
		characterMap.put('q', 7);
		characterMap.put('r', 7);
		characterMap.put('s', 7);
		characterMap.put('t', 8);
		characterMap.put('u', 8);
		characterMap.put('v', 8);
		characterMap.put('w', 9);
		characterMap.put('x', 9);
		characterMap.put('y', 9);
		characterMap.put('z', 9);
	}

	/**
	 * The tree
	 */
	private Tree<Keystroke> tree;
	private Position<Keystroke> lastUsedKeyStroke;

	/**
	 * Default Constructor that creates an empty dictionary.
	 */
	public Dictionary() {
		tree = new LinkedTree<Keystroke>();
		tree.addRoot(new Keystroke(-1));
	}

	/**
	 * Load the specified dictionary file. Each word in the file must be
	 * inserted into the dictionary.
	 * 
	 * @param filename
	 *            the dictionary file to be loaded
	 */
	public void load(String filename) {
		// TODO: This method must be completed for question A4
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.out.println("No Such File: " + filename);
		}
		try {
			Pattern pattern = Pattern.compile("[A-Za-z]*");
			ArrayList<String> wordsToAdd = new ArrayList<String>();
			String line = in.readLine();
			while(line != null){
				Matcher matcher = pattern.matcher(line);
				 if(matcher.matches()){
					 wordsToAdd.add(line);
				 }
				 line = in.readLine();
			}
			for(String s: wordsToAdd){
				insert(s);
			}
		} catch (IOException ioe) {
			System.out.println("Error reading from: " + filename);
		}
		try {
			in.close(); // closes the file
		} catch (IOException ioe) {
			System.out.println("Error closing: " + filename);
		}

	}

	/**
	 * Insert the word into the dictionary. This algorithm loops through the
	 * characters in the word, and uses the character map to work out what
	 * keystroke should be used to select that character (e.g. a,b,c would be
	 * selected by pressing the 2 key).
	 * 
	 * For each sequence of keystrokes, the substring that corresponds to that
	 * sequence is stored at the corresponding node so that
	 * 
	 * @param word
	 */
	public void insert(String word) {
		char[] charWord = word.toLowerCase().toCharArray();
		String wordUntilNow = "";
		Position<Keystroke> p = tree.root();
		HashMap<Integer, Position<Keystroke>> keylist = null;
		HashMap<Integer, Keystroke> keylist2 = null;
		for (int i = 0; i <= charWord.length - 1; i++) {
			wordUntilNow = wordUntilNow + charWord[i];

			Keystroke k = new Keystroke(characterMap.get(charWord[i]));
			k.addWord(wordUntilNow);
			k.getWords();

			keylist = new HashMap<Integer, Position<Keystroke>>();
			keylist2 = new HashMap<Integer, Keystroke>();
			Iterator<Position<Keystroke>> list = tree.children(p);
			while (list.hasNext()) { //process children of current parent node

				Position<Keystroke> pos = list.next();
				Keystroke key = pos.element();
				int no = key.key;
				keylist.put(no, pos);
				keylist2.put(no, key);
			}

			if (!keylist.isEmpty()) {
				int alreadykey = characterMap.get(charWord[i]);
				if (keylist.containsKey(alreadykey)) { // check if child nodes have same number as we want to add
					Position<Keystroke> alreadyPosition1 = keylist.get(alreadykey);
					Keystroke alreadyPosition = keylist2.get(alreadykey);
					for(String s : k.words.keySet()){
						if(alreadyPosition.words.keySet().contains(s)){
								double noHits = alreadyPosition.words.get(s);
								noHits++;
								alreadyPosition.words.put(s, noHits);
								k.words = alreadyPosition.words;
								k.addWord(wordUntilNow);
								p = alreadyPosition1;
								tree.replace(alreadyPosition1, k);
						}else{
								k.words = alreadyPosition.words;
								k.addWord(s);
								p = alreadyPosition1;
								tree.replace(alreadyPosition1, k);
						}
					}
				} else {
					p = tree.addChild(p, k);
				}
			} else {
				p = tree.addChild(p, k);
			}

		}

	}

	/**
	 * Output the state of the dictionary (via delegation to the underlying tree
	 * implementation).
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return tree.toString();
	}

	/**
	 * Find the list of words that corresponds to the given sequence of
	 * keystrokes.
	 * 
	 * @param keystrokes
	 *            a sequence of keystrokes
	 * 
	 * @return a list of words
	 */
	public List<String> findWords(List<Integer> keystrokes) {
		// TODO: This method must be completed for question A3

		/**
		 * The default null response will cause the invoking method to create a
		 * default word based on the sequence of keystrokes (i.e. the same
		 * behaviour as if there was not entry in the dictionary for the
		 * sequence of keystrokes).
		 */
		Position<Keystroke> p = tree.root();
		Iterator<Position<Keystroke>> treeList = tree.children(p);
		ListIterator<Integer> li = new ListIterator(keystrokes);

		int value;
		while(li.hasNext()){
			value = li.next().intValue();
				while (treeList.hasNext()) {
					Position<Keystroke> pos = treeList.next();
					Keystroke key = pos.element();
					Integer no = key.key;
					
					if(no == value){
						p = pos;
						treeList = tree.children(p);
						System.out.println("Found!: " + p);
						if(li.hasNext()){
							value = li.next().intValue();
						}
					}
				}
		}
		
		if(p.element().words.isEmpty()){
			return null;
		}
		
		List<String> wordsToReturn = new LinkedList<String>();
		double lastKey=0;
		double currentKey;
		
		for(String s : p.element().words.descendingKeySet()){
			currentKey = p.element().words.get(s);
			if(currentKey > lastKey){
				wordsToReturn.insertFirst(s);
			}else{
				wordsToReturn.insertLast(s);
			}
			lastKey=currentKey;
		}
		
		 lastUsedKeyStroke = p;
		
		return p.element().getWords();
	}
	
	public void updateScore(String usedWord){
		if(lastUsedKeyStroke!=null & lastUsedKeyStroke.element().words.containsKey(usedWord) & usedWord!=null){
			double score = lastUsedKeyStroke.element().words.get(usedWord);
			double newscore = score+0.5;
			System.out.println("Score for: " + usedWord + " was: " + score + " (is now: " + newscore + ")");
			lastUsedKeyStroke.element().words.put(usedWord, newscore);
		}
	}
}
