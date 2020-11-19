/**
 * @(#) Arith.java
 * @author Qusay H. Mahmoud
 */

public interface Arith extends java.rmi.Remote {
    int[] add(int a[], int b[]) throws java.rmi.RemoteException; 
}
