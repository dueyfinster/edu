package dsai.core;

import java.util.ListIterator;

public class WordsListIterator<T> {

	private final WordsListIterator<T> list;

	private boolean nextWasCalled = false;
	private boolean previousWasCalled = false;

	public WordsListIterator(WordsListIterator<T> listIterator) {
		this.list = listIterator;
	}

	public T next() {
		nextWasCalled = true;
		if (previousWasCalled) {
			previousWasCalled = false;
			list.next();
		}
		return list.next();
	}

	public T previous() {
		if (nextWasCalled) {
			list.previous();
			nextWasCalled = false;
		}
		previousWasCalled = true;
		return list.previous();
	}

}
