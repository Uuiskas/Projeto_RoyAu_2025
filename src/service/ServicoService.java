package service;

import exceptions.RegraNegocioException;
import model.Servico;
import model.Banho;
import model.Tosa;
import model.Microchipagem;
import model.Spa;
import model.TaxiDog;
import model.Agendamento;
import repository.ServicoRepository;
import enums.Porte;
import enums.TipoPelo;
import enums.StatusAgendamento;

public class ServicoService {

    private static ServicoService INSTANCIA;
    private final ServicoRepository servicoRepository;

    private ServicoService() {
        this.servicoRepository = ServicoRepository.getInstancia();
        inicializarServicosPadrao(); // Chamada inicial
    }

    public static ServicoService getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ServicoService();
        }
        return INSTANCIA;
    }

    // Método para inicializar todos os serviços no repositório
    private void inicializarServicosPadrao() {

        String dataFixa = "2026-01-01";


        // Passando valores de Enum VÁLIDOS (PEQUENO, CURTO)
        servicoRepository.salvar(new Banho("Banho", 45.00, dataFixa, Porte.PEQUENO));
        servicoRepository.salvar(new Tosa("Tosa", 55.00, dataFixa, TipoPelo.CURTO));
        servicoRepository.salvar(new Microchipagem("Microchipagem", 120.00, dataFixa));
        servicoRepository.salvar(new Spa("SPA Relax", 60.00, dataFixa, 3));
        servicoRepository.salvar(new TaxiDog("Taxi Dog", 15.00, dataFixa, 5.0));
    }

    public Servico buscarPorId(int id) throws RegraNegocioException {
        Servico s = servicoRepository.buscarPorId(id);
        if (s == null) throw new RegraNegocioException("Serviço não encontrado.");
        return s;
    }

    public void listarServicos() {
        for (Servico s : servicoRepository.listar()) {
            System.out.println(s);
        }
    }

    // - Relatórios Polimórficos

    public double calcularFaturamentoTotal() {
        double totalFaturado = 0.0;

        // Acessa o AgendamentoService para pegar a lista de agendamentos
        AgendamentoService agendamentoService = AgendamentoService.getInstancia();

        for (Agendamento ag : agendamentoService.listarAgendamentos()) {
            // Soma apenas se o serviço foi realizado (CONCLUIDO)
            if (ag.getStatus() == StatusAgendamento.CONCLUIDO) {
                totalFaturado += ag.getPrecoFinal(); // Usa o valor final calculado (com taxas/adicionais)
            }
        }
        return totalFaturado;
    }

    public ServicoRepository getRepository() {
        return servicoRepository;
    }
}