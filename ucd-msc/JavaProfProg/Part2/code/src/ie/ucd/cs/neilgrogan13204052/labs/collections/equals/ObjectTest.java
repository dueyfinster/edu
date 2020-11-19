package ie.ucd.cs.neilgrogan13204052.labs.collections.equals;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ObjectTest {
    public static void main(String[] args) {
        ObjectOne obj1a = new ObjectOne(1,2);
        ObjectOne obj1b = new ObjectOne(1,2);
        ObjectTwo obj2 = new ObjectTwo();

        if(obj1a.equals(obj1b)){
            System.out.println("obj1a is equal to obj1b");
        }else{
            System.out.println("obj1a is not equal to obj1b");
        }

        if(obj2.equals(obj1a)){
            System.out.println("obj2 is equal to obj1a");
        }else{
            System.out.println("obj2 is not equal to obj1a");
        }
    }

    static class ObjectOne{
        private int x =0;
        private int y = 0;

        ObjectOne(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }

    static class ObjectTwo{
        private int x = 1;
        private int y = 1;

        public boolean equals(Object object){
            ObjectOne obj = (ObjectOne) object;
            if((obj.getX() == x) && (obj.getY() == y)){
                return true;
            }else {
                return false;
            }
        }
    }
}
