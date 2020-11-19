package Project2.Adaptor;


public class Tiger extends Animal {

  protected final double feedingRate = 0.15; //kg-animal per kg-food feeding rate

  public Tiger(int weight) {
    species = this.getClass().getName();
    this.weight = weight;
    this.foodType = "Meat chunks";
  }


  public void feed() {
    System.out.println("You are feeding an animal of type " + species);
    System.out.println("This animal requires " + weight * feedingRate + " kg of " + foodType);
  }
}