package huffmanCoding;

public class HuffmanSymbol 
{
	private int freq;
	private String symbol;
	
	/***
	 * @param f
	 * @param s
	 */
	
	public HuffmanSymbol(int f, String s)
	{
		freq=f;
		symbol=s;
		
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
