package Lab1;
public class NumberFactory {


public NumberList getNumberList(String list){
	int h = list.indexOf("1");
	int i = list.indexOf("."); // Doubles
	int j = list.indexOf("x"); // Hex Numbers
	int k = list.indexOf("a"); // Letters
	int l = list.indexOf("C"); //Current Accounts
	int m = list.indexOf("I"); //Investment Accounts
	
	
	/*if(i!=-1){
		return new DoubleList(list);
	} else if (h!= -1){
		return new IntList(list);
	} else if(j!= -1){
		return new HexList(list);
	} else if(k!= -1){
		return new CharList(list);
	} else */if(l!=-1){
		return new CurrentACList(list);
	} else if (m!=-1) {
		return new InvestACList(list);
	}
	return null;
	
	}// End of method
} // End of Class