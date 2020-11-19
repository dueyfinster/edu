package Lab1;

import java.util.StringTokenizer;

public class CharList extends NumberList {
	char[] ch;
	
	CharList(String list) {
		ch = list.toCharArray();
	}
	
		
	public void display() {
		System.out.print("Character List");
		for(int i = 0; i < ch.length; i++){
				System.out.println("[" + i + "] = " + ch[i]);
		}
	}
}


