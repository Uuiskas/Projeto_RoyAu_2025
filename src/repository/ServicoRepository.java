package repository;

import model.Servico;

public class ServicoRepository extends GenericRepository<Servico> {

    private static ServicoRepository INSTANCIA;

    private ServicoRepository() {}

    public static ServicoRepository getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ServicoRepository();
        }
        return INSTANCIA;
    }

    @Override
    public Servico buscarPorId(int id) {
        for (Servico servico : data) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }
}
