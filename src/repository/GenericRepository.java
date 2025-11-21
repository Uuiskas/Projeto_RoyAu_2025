package repository;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> {

    protected List<T> data = new ArrayList<>();

    // O método 'salvar' (que também dá erro)
    public void salvar(T entity) {
        if (entity != null) {
            data.add(entity);
        }
    }

    // O método 'listar' (que causa o erro 'Cannot resolve method')
    public List<T> listar() {
        return new ArrayList<>(data);
    }

    public abstract T buscarPorId(int id);
}