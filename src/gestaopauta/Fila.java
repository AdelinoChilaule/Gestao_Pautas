package gestaopauta;

public interface Fila {
	void enqueue(Object x) throws FilaException;
    Object front() throws FilaException;
    Object dequeue() throws FilaException;
    boolean isEmpty();
    void makeEmpty();
    int numElem();
}
