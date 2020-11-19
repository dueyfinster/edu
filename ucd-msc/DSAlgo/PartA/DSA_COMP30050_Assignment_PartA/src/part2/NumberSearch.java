package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberSearch {
	private String fileName;
	private ArrayList<String> allWordsInFile = new ArrayList<String>();
	private TreeMap<Integer, String> allNumbersInFile = new TreeMap<Integer, String>();

	NumberSearch() {
		askForFileAndWord();
		processFileToStrings();
		ArrayList<String> resultsOfFind = searchArrayForNumbers();
		displayNumbersSorted(resultsOfFind);
	}

	private void askForFileAndWord() {
		JPanel jOptPanePanel = new JPanel();
		jOptPanePanel.add(new JLabel("Filename:"));
		JTextField fileNameField = new JTextField(10);
		fileNameField.setText("cakeQuestion.txt");
		jOptPanePanel.add(fileNameField);

		int result = JOptionPane.showConfirmDialog(null, jOptPanePanel, "Word in File Search",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			fileName = fileNameField.getText();
		}
	}

	private ArrayList<String> processFileToStrings() {
		String scan = null;
		try {
			scan = new Scanner(new File(fileName)).useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringTokenizer st = new StringTokenizer(scan);
		while (st.hasMoreTokens()) {
			allWordsInFile.add(st.nextToken());

		}

		for (int i = 0; i < allWordsInFile.size(); i++) {
			if (testIfStringIsANumber(allWordsInFile.get(i))) {
				allNumbersInFile.put(i, allWordsInFile.get(i));
			}

		}

		return allWordsInFile;
	}

	private ArrayList<String> searchArrayForNumbers() {
		ArrayList<String> resultsOfFind = new ArrayList<String>();

		int sizeOfArray = allWordsInFile.size();
		
		
		if (allNumbersInFile.keySet().size()>0) {
			for (int i : allNumbersInFile.keySet()) {
				// System.out.println( i );
				if (!(i - 1 < 0) & !(i + 1 >= sizeOfArray))
					resultsOfFind.add(allWordsInFile.get(i - 1) + " " + allWordsInFile.get(i) + " "
							+ allWordsInFile.get(i + 1));
				else if ((i - 1 < 0) & (i + 1 < sizeOfArray))
					resultsOfFind.add(allWordsInFile.get(i) + " " + allWordsInFile.get(i + 1));
				else if ((i - 1 > 0) & (i + 1 >= sizeOfArray))
					resultsOfFind.add(allWordsInFile.get(i - 1) + " " + allWordsInFile.get(i));
				else
					resultsOfFind.add(allWordsInFile.get(i));
			}

		} else {
			JOptionPane.showMessageDialog(null, "File: " + fileName + " does not contain a number",
					"Error finding number(s)!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		return resultsOfFind;
	}

	private void displayNumbersSorted(ArrayList<String> arrayToSearch) {
		String stringToPrint = "";

		for (String s : arrayToSearch) {
			stringToPrint = stringToPrint + s + "\n";
		}

		JOptionPane.showMessageDialog(null, "Numbers in the file: \n" + stringToPrint);
	}

	private boolean testIfStringIsANumber(String testWord) {
		try {
			double test = Double.parseDouble(testWord);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		NumberSearch p1 = new NumberSearch();
	}
}
