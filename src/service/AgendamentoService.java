package service;

import model.Agendamento;
import repository.AgendamentoRepository;
import exceptions.RegraNegocioException;
import enums.StatusAgendamento;
import java.time.LocalDateTime;


public class AgendamentoService {

    private static AgendamentoService INSTANCIA;

    private AgendamentoRepository agendamentoRepository;

    private AgendamentoService() {

        this.agendamentoRepository = AgendamentoRepository.getInstancia();
    }

    public static AgendamentoService getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new AgendamentoService();
        }
        return INSTANCIA;
    }


    /** RF004: Agenda Inteligente  */
    public void agendarServico(Agendamento novoAgendamento) {

        // 1. Validação de Composição e Horário
        if (novoAgendamento == null || novoAgendamento.getHorario() == null || novoAgendamento.getPet() == null || novoAgendamento.getServico() == null) {
            throw new RegraNegocioException("Dados do agendamento (horário, pet ou serviço) incompletos.");
        }

        // 2. Regra de Negócio:
        if (existeChoqueDeHorario(novoAgendamento)) {

            throw new RegraNegocioException("Conflito de horário detectado para " + novoAgendamento.getHorario() + ". Sugira outro horário.");
        }

        // 3. Salva no Repositório
        agendamentoRepository.salvar(novoAgendamento);
    }

    /** Lógica de verificação de horário usando LocalDateTime. */
    private boolean existeChoqueDeHorario(Agendamento novoAgendamento) {
        // Usa o tipo correto para comparação (LocalDateTime)
        LocalDateTime horarioNovo = novoAgendamento.getHorario();

        for (Agendamento existente : agendamentoRepository.listar()) {

            if (existente.getHorario().isEqual(horarioNovo)) {
                return true;
            }
        }
        return false;
    }

    /** RF005: Finaliza o agendamento e muda o status. */
    public void finalizarAgendamento(int idAgendamento) throws RegraNegocioException {
        Agendamento ag = agendamentoRepository.buscarPorId(idAgendamento);

        if (ag == null) {
            throw new RegraNegocioException("Agendamento não encontrado.");
        }

        // Verifica se está no status AGENDADO antes de CONCLUIR
        if (ag.getStatus() == StatusAgendamento.AGENDADO) {
            ag.setStatus(StatusAgendamento.CONCLUIDO);

            // RF002: A lógica de Sistema de Fidelidade seria inserida aqui.

            System.out.println("✅ Agendamento ID " + idAgendamento + " finalizado e status alterado.");
        } else {
            throw new RegraNegocioException("Agendamento ID " + idAgendamento + " não está no status AGENDADO.");
        }
    }

    // Método auxiliar para o Controller acessar a lista (CRUD)
    public AgendamentoRepository getRepository() {
        return agendamentoRepository;
    }
}