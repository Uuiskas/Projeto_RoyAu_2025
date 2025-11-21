package repository;

import model.Agendamento;

// Estende a classe genérica para herdar os métodos salvar e listar
public class AgendamentoRepository extends GenericRepository<Agendamento> {

    // Padrão Singleton
    private static AgendamentoRepository INSTANCIA;

    // Construtor privado: impede a criação de instâncias externas
    private AgendamentoRepository() {
        // Inicializado de forma vazia; a lista 'data' é herdada de GenericRepository
    }

    // Método de acesso público ao Singleton
    public static AgendamentoRepository getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new AgendamentoRepository();
        }
        return INSTANCIA;
    }

    // Método obrigatório: Implementa a busca por ID (Herdado do GenericRepository)
    @Override
    public Agendamento buscarPorId(int id) {
        // Itera sobre a lista de dados 'data' (herdada de GenericRepository)
        for (Agendamento agendamento : data) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }
        return null; // Retorna null se não encontrar
    }
}