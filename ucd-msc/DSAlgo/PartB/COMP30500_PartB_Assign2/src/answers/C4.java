package answers;

import java.util.Random;

public class C4 {

	public static void main(String[] args) {
		
		Instrument i = new Instrument(5);
		i.start();
		for(int f=0; f<5; f++){
			int a = Instrument.randInt(5,10);
			int b = Instrument.randInt(5,10);
			System.out.println(difference(a,b));
		}
		i.stop();
		
		i.calculateRunningTime();
		
	}
	
	public static int difference(int a, int b){
		if(a > b){
        	return a-b;
		}else{
			return b-a;
		}
	}
}
