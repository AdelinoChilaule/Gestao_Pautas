package gestaopauta;

import java.util.List;

public interface Lista {
    void add(Object x);
    void remove(Object x);
    int size();
    boolean isEmpty();
    List<Estudante> getAll();
    void clear();
}
