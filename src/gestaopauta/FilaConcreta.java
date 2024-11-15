package gestaopauta;

import java.util.LinkedList;

public class FilaConcreta implements Fila {
    private LinkedList<Object> fila;

    public FilaConcreta() {
        fila = new LinkedList<>();
    }

    @Override
    public void enqueue(Object x) {
        fila.addLast(x);
    }

    @Override
    public Object front() throws FilaException {
        if (isEmpty()) {
            throw new FilaException("Fila está vazia.");
        }
        return fila.getFirst();
    }

    @Override
    public Object dequeue() throws FilaException {
        if (isEmpty()) {
            throw new FilaException("Fila está vazia.");
        }
        return fila.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return fila.isEmpty();
    }

    @Override
    public void makeEmpty() {
        fila.clear();
    }

    @Override
    public int numElem() {
        return fila.size();
    }
}
