package Project2.Composite;

public class Produce {
	private String ProdName;
	private int Amount;

    public Produce(String name, int AmntSold) {
    	ProdName = name;
    	Amount = AmntSold;
    }

    public String toString() {
        return ProdName;
    }
}
