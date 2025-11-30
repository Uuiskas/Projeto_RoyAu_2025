package service;

import enums.StatusAgendamento;
import enums.TipoPagamento;
import exceptions.RegraNegocioException;
import model.Agendamento;
import model.Pet;
import model.Servico;
import model.Tutor;
import repository.AgendamentoRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    // --- Métodos de CRUD ---

    public void agendarServico(Agendamento novo) {
        // ... (Lógica de agendar Servico e existe Choque de  horario)
        if (novo == null || novo.getHorario() == null || novo.getPet() == null || novo.getServico() == null) {
            throw new RegraNegocioException("Dados do agendamento incompletos.");
        }


        Servico servico = novo.getServico();
        double precoFinal = servico.calcularCustoTotal(novo.getPet());
        novo.setPrecoFinal(precoFinal);

        novo.setStatus(StatusAgendamento.AGENDADO);
        agendamentoRepository.salvar(novo);
    }

    public void finalizarAgendamento(int id, TipoPagamento pagamento) {
        // ... (Lógica de finalização, fidelidade e ficha de pagamento)
        Agendamento ag = agendamentoRepository.buscarPorId(id);

        if (ag == null) {
            throw new RegraNegocioException("Agendamento não encontrado.");
        }

        if (ag.getStatus() != StatusAgendamento.AGENDADO) {
            throw new RegraNegocioException(
                    "Agendamento não está no status AGENDADO.");
        }

        ag.setStatus(StatusAgendamento.CONCLUIDO);

        // Chamada da Lógica de Fidelidade
        tutorService.adicionarPontos(ag.getTutor().getId(), ag.getPrecoFinal());

        gerarFichaPagamento(ag, pagamento);

        System.out.println("Serviço concluído. Pontos adicionados.");
    }

    private void gerarFichaPagamento(Agendamento agendamento, TipoPagamento pagamento) {
        // ... (Lógica da Ficha de Pagamento)
        String caixaResponsavel = (agendamento.getTutor().getId() % 2 != 0) ? "Alyere" : "Bruno";
        double custo = agendamento.getPrecoFinal();

        System.out.println("\n=============================================");
        System.out.println("             FICHA DE PAGAMENTO RoyAu PET (ID: " + agendamento.getId() + ")");
        System.out.println("---------------------------------------------");
        System.out.printf("VALOR TOTAL: R$ %.2f\n", custo);
        System.out.println("Forma de Pagamento: " + pagamento.name());
        System.out.println("CAIXA RESPONSÁVEL: " + caixaResponsavel);
        System.out.println("=============================================\n");
    }

    // ⬅ MÉTODO: Retorna a lista para o Controller
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.listar();
    }
}