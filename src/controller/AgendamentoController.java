package controller;

import model.Agendamento;
import service.AgendamentoService;
import exceptions.RegraNegocioException;
import enums.TipoPagamento;

public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController() {
        this.agendamentoService = AgendamentoService.getInstancia();
    }

    public void cadastrarAgendamento(Agendamento agendamento) throws RegraNegocioException {
        agendamentoService.agendarServico(agendamento);
        System.out.println("Agendamento criado com sucesso...");
    }

    public void finalizarAgendamento(int idAgendamento, TipoPagamento pagamento) throws RegraNegocioException {
        agendamentoService.finalizarAgendamento(idAgendamento, pagamento);
        System.out.println("Agendamento " + idAgendamento + " finalizado com sucesso.");
    }

    // Método auxiliar para obter a instância do Service (útil para a Main)
    public AgendamentoService getService() {
        return agendamentoService;
    }

    public void listarTodos() {
        System.out.println("--- Agendamentos Registrados ---");

        // método público do Service para listar
        for (Agendamento a : agendamentoService.listarAgendamentos()) {
            a.exibirAgendamento();
        }
    }
}