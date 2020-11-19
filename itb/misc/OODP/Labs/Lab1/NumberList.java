package Lab1;

public abstract class NumberList {

  protected int[] intList;
  protected double[] doubleList;
  protected char[] charList;
  protected int[] hexList;
  
  //Excercise 2
  protected int[] currentACList;
  protected int[] investmentACList;

  NumberList() {
    intList = null;
    doubleList = null;
    charList = null;
    hexList = null;
  }

  public int[] getIntList() { return intList; }
  public double[] getDoubleList() { return doubleList; }
  public char[] getCharList() { return charList; }
  public int[] getHexList() { return hexList; }
  public void display() {}
  public Number sum() { return null; }

}
