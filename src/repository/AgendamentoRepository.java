package repository;

import model.Agendamento;


public class AgendamentoRepository extends GenericRepository<Agendamento> {

    private static AgendamentoRepository INSTANCIA;

    private AgendamentoRepository() {
    }

    public static AgendamentoRepository getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new AgendamentoRepository();
        }
        return INSTANCIA;
    }

    @Override
    public Agendamento buscarPorId(int id) {
        for (Agendamento agendamento : data) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }
        return null;
    }
}
