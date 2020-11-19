package Lab9.Iterator;

import java.util.Enumeration;

public interface Sample{
	public boolean hasMoreElements();
	public Object nextElement();




public Enumeration elements();

Enumeration e = vector.elements();
	while(e.hasMoreElements()){
		String name = (String)e.nextElement();
		System.out.println(s);
	}
}
