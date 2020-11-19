package Project2.Adaptor;

public class Zoo {

  private static final int currentMax = 6;
  private static Animal[] animalList = new Animal[currentMax];

  public static void main(String[] args) {
    Animal theo = new Tiger(300);
    Animal kirsty = new Elephant(3000);
    Animal tiny = new Giraffe(2000);
    Animal stilt = new Giraffe(1500);
    Animal roar = new Tiger(250);
    Animal snakey = new SnakeAdaptor(20);

    animalList[0] = theo;
    animalList[1] = kirsty;
    animalList[2] = tiny;
    animalList[3] = stilt;
    animalList[4] = roar;
    animalList[5] = snakey;

    for(int i = 0; i < animalList.length; i++) {
      animalList[i].feed();
    }

  }


}