package gestaopauta;

import java.util.ArrayList;
import java.util.List;

public class ListaConcreta implements Lista {
    private List<Object> lista;

    public ListaConcreta() {
        lista = new ArrayList<>();
    }

    @Override
    public void add(Object x) {
        lista.add(x);
    }

    @Override
    public void remove(Object x) {
        lista.remove(x);
    }

    @Override
    public int size() {
        return lista.size();
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public List<Estudante> getAll() {
        List<Estudante> estudantes = new ArrayList<>();
        for (Object obj : lista) {
            if (obj instanceof Estudante) {
                estudantes.add((Estudante) obj);
            }
        }
        return estudantes;
    }

    @Override
    public void clear() {
        lista.clear();
    }
}
