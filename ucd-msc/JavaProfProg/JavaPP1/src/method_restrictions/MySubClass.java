package method_restrictions;

/**
 * Created by ngrogan on 18/06/2014.
 */
public class MySubClass extends MySuperClass {

    MySubClass(){}

    void message(){ // weaker access then superclass, will not work!
        System.out.println("From the subclass!");
    }
}
