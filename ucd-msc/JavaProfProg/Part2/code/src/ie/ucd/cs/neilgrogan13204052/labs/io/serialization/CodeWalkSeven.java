package ie.ucd.cs.neilgrogan13204052.labs.io.serialization;

import java.io.*;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class CodeWalkSeven {
    public static void main(String[] args) {
        Car c = new Car("Nissan", 1500, "blue");
        System.out.println("before: " + c.make + " "+ c.weight);
        try{
            FileOutputStream fs = new FileOutputStream("Car.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(c);
            os.close();
        }catch (Exception e){ e.printStackTrace(); }

        try{
            FileInputStream fis = new FileInputStream("Car.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (Car) ois.readObject();
            ois.close();
        }catch (Exception e){ e.printStackTrace(); }
        System.out.println("after: " + c.make + " "+ c.weight);
    }

    static class NonLiving{}

    static class Vehicle extends NonLiving {
        String make = "Lexus";
        String color = "brown";
    }

    static class Car extends Vehicle implements Serializable {
        protected int weight = 1000;

        Car(String n, int w, String c){
            color = c;
            make = n;
            weight = w;
        }

    }
}
