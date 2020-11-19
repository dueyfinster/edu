package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordSearch {
	private String fileName;
	private String word;

	WordSearch() {
		askForFileAndWord();
		ArrayList<String> allWords = processFileToStrings();
		ArrayList<String> foundWords = searchArrayForWord(allWords, word);
		displayWordsSorted(foundWords);
	}

	private void askForFileAndWord() {
		JPanel jOptPanePanel = new JPanel();
		jOptPanePanel.add(new JLabel("Filename:"));
		JTextField fileNameField = new JTextField(10);
		fileNameField.setText("myNovel.txt");
		jOptPanePanel.add(fileNameField);
		
		jOptPanePanel.add(new JLabel("Word to search for:"));
		JTextField wordSearchField = new JTextField(10);
		wordSearchField.setText("I");
		jOptPanePanel.add(wordSearchField);

		
		int result = JOptionPane.showConfirmDialog(null, jOptPanePanel,
				"Word in File Search", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			fileName = fileNameField.getText();
			word = wordSearchField.getText();
		}
	}

	private ArrayList<String> processFileToStrings() {
		ArrayList<String> allWordsInFile = new ArrayList<String>();
		
		String scan = null;
		try {
			 scan = new Scanner(new File(fileName)).useDelimiter("\\A")
					.next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringTokenizer st = new StringTokenizer(scan);
	     while (st.hasMoreTokens()) {
	         allWordsInFile.add(st.nextToken());
	     }
	     
		return allWordsInFile;
	}

	private ArrayList<String> searchArrayForWord(
			ArrayList<String> arrayToSearch, String word) {
		 ArrayList<String> resultsOfFind = new ArrayList<String>();

		if(arrayToSearch.contains(word)){
			int sizeOfArray = arrayToSearch.size();
			
			for(int i = 0; i < sizeOfArray; i++){
				if(arrayToSearch.get(i).equals(word)){
					if(!(i-1 < 0) & !(i+1>=sizeOfArray))
						resultsOfFind.add(arrayToSearch.get(i-1) + " " + arrayToSearch.get(i) + " " + arrayToSearch.get(i+1));
					else if((i-1 < 0) & (i+1<sizeOfArray))
						resultsOfFind.add(arrayToSearch.get(i) + " " + arrayToSearch.get(i+1));
					else if((i-1 > 0) & (i+1>=sizeOfArray))
						resultsOfFind.add(arrayToSearch.get(i-1) + " " +  arrayToSearch.get(i));
					else
						resultsOfFind.add(arrayToSearch.get(i));
				}
			}
		}else{
			JOptionPane.showMessageDialog (null, "File: " + fileName + " does not contain word: "+ 
		word, "Error finding word!", JOptionPane.ERROR_MESSAGE);		
		}
		
		return resultsOfFind;
	}

	private void displayWordsSorted(ArrayList<String> arrayToSearch) {
		 String stringToPrint = "";
		 
		for(String s : arrayToSearch) {   
    	    stringToPrint = stringToPrint + s + "\n";
		}
		
		JOptionPane.showMessageDialog(null, "Words in the file: \n" +stringToPrint);
	}

	public static void main(String[] args) {
		WordSearch p1 = new WordSearch();
	}
}
