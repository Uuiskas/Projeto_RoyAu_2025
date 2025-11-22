package repository;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T> {

    protected List<T> data = new ArrayList<>();

    public void salvar(T entity) {
        if (entity != null) {
            data.add(entity);
        }
    }

    public List<T> listar() {
        return new ArrayList<>(data);
    }

    public abstract T buscarPorId(int id);
}