package service;

import exceptions.RegraNegocioException;
import model.Servico;
import model.Banho;
import model.Tosa;
import repository.ServicoRepository;

public class ServicoService {

    private static ServicoService INSTANCIA;

    private final ServicoRepository servicoRepository;

    private ServicoService() {
        this.servicoRepository = ServicoRepository.getInstancia();
        inicializarServicosPadrao();
    }

    public static ServicoService getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ServicoService();
        }
        return INSTANCIA;
    }

    private void inicializarServicosPadrao() {
        servicoRepository.salvar(new Banho());
        servicoRepository.salvar(new Tosa());
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

    public ServicoRepository getRepository() {
        return servicoRepository;
    }
}
