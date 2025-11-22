package service;

import enums.StatusAgendamento;
import exceptions.RegraNegocioException;
import model.Agendamento;
import model.Pet;
import model.Servico;
import model.Tutor;
import repository.AgendamentoRepository;

import java.time.LocalDateTime;

public class AgendamentoService {

    private static AgendamentoService INSTANCIA;

    private final AgendamentoRepository agendamentoRepository;
    private final PetService petService;
    private final TutorService tutorService;
    private final ServicoService servicoService;

    private AgendamentoService() {
        this.agendamentoRepository = AgendamentoRepository.getInstancia();
        this.petService = PetService.getInstancia();
        this.tutorService = TutorService.getInstancia();
        this.servicoService = ServicoService.getInstancia();
    }

    public static AgendamentoService getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new AgendamentoService();
        }
        return INSTANCIA;
    }

    // -------------------------------------------------------------
    // RF004 – Agenda Inteligente: Agendar serviço
    // -------------------------------------------------------------
    public void agendarServico(Agendamento novo) {

        if (novo == null ||
                novo.getHorario() == null ||
                novo.getPet() == null ||
                novo.getServico() == null) {
            throw new RegraNegocioException("Dados do agendamento incompletos.");
        }

        // Valida horário
        if (existeChoqueDeHorario(novo.getHorario())) {
            LocalDateTime sugestao = sugerirProximoHorario(novo.getHorario());
            throw new RegraNegocioException(
                    "Horário indisponível. Horário sugerido: " + sugestao);
        }

        // Ajuste: recalcular preço usando o serviço
        Servico servico = novo.getServico();
        double precoFinal = servico.calcularPreco(novo.getPet());
        novo.setPrecoFinal(precoFinal);

        novo.setStatus(StatusAgendamento.AGENDADO);
        agendamentoRepository.salvar(novo);
    }

    // -------------------------------------------------------------
    // RF004 – Verifica se horário está ocupado
    // -------------------------------------------------------------
    private boolean existeChoqueDeHorario(LocalDateTime horarioNovo) {
        return agendamentoRepository.listar().stream()
                .anyMatch(a -> a.getHorario().isEqual(horarioNovo));
    }

    // -------------------------------------------------------------
    // RF004 – Sugere próximo horário livre (incremento de 30 min)
    // -------------------------------------------------------------
    public LocalDateTime sugerirProximoHorario(LocalDateTime horario) {
        LocalDateTime tentativa = horario.plusMinutes(30);

        while (existeChoqueDeHorario(tentativa)) {
            tentativa = tentativa.plusMinutes(30);
        }
        return tentativa;
    }

    // -------------------------------------------------------------
    // RF005 + RF002 – Finalizar serviço:
    // 1. Muda status
    // 2. Atualiza histórico do pet
    // 3. Dá pontos ao tutor
    // -------------------------------------------------------------
    public void finalizarAgendamento(int id) {

        Agendamento ag = agendamentoRepository.buscarPorId(id);

        if (ag == null) {
            throw new RegraNegocioException("Agendamento não encontrado.");
        }

        if (ag.getStatus() != StatusAgendamento.AGENDADO) {
            throw new RegraNegocioException(
                    "Agendamento não está no status AGENDADO.");
        }

        // 1. Muda status
        ag.setStatus(StatusAgendamento.CONCLUIDO);

        // 2. Atualiza histórico do pet
        Pet pet = ag.getPet();
        petService.registrarHistorico(ag);

        // 3. Fidelidade
        Tutor tutor = pet.getTutor();
        int pontos = calcularPontosDoServico(ag.getServico());
        tutorService.adicionarPontos(tutor, pontos);

        System.out.println("✔ Serviço concluído. Pontos adicionados: " + pontos);
    }

    // Pontos padrão: você pode ajustar isso depois
    private int calcularPontosDoServico(Servico s) {
        if (s.getNome().equalsIgnoreCase("Banho")) return 10;
        if (s.getNome().equalsIgnoreCase("Tosa")) return 15;
        return 5;
    }

    public AgendamentoRepository getRepository() {
        return agendamentoRepository;
    }
}
