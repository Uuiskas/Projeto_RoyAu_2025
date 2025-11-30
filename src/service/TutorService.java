package service;

import exceptions.RegraNegocioException;
import model.Tutor;
import repository.TutorRepository;

public class TutorService {
    // ... (Código do Singleton e Construtor omitidos por brevidade) ...

    private static TutorService INSTANCIA;
    private final TutorRepository tutorRepository;

    private TutorService() {
        this.tutorRepository = TutorRepository.getInstancia();
    }

    public static TutorService getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new TutorService();
        }
        return INSTANCIA;
    }


    public void cadastrarTutor(Tutor tutor) {
        if (tutor == null || tutor.getNome() == null || tutor.getTelefone() == null || tutor.getCpf() == null) {
            // Ajuste na validação: CPF é obrigatório no modelo final
            throw new RegraNegocioException("Dados do tutor incompletos (Nome, Telefone ou CPF).");
        }
        tutorRepository.salvar(tutor);
    }


    public Tutor buscarPorId(int id) {
        Tutor t = tutorRepository.buscarPorId(id);
        if (t == null) {
            throw new RegraNegocioException("Tutor não encontrado.");
        }
        return t;
    }

    public void adicionarPontos(int tutorId, double valorServico) {
        Tutor tutor = buscarPorId(tutorId); // Reutiliza o buscarPorId com validação

        if (valorServico <= 0) {
            // Se o valor for zero ou negativo, não dá pontos
            return;
        }

        // Regra: 1 ponto por cada R$10 gasto (Exemplo: R$15 gasto = 1 ponto)
        int pontosGanhos = (int) (valorServico / 10.0);

        if (pontosGanhos > 0) {
            tutor.adicionarPontos(pontosGanhos); // Chama o método do Model
            System.out.println("🎉 Tutor " + tutor.getNome() + " ganhou " + pontosGanhos + " pontos de fidelidade!");
        }
    }


    public void listarTutores() {
        for (Tutor t : tutorRepository.listar()) {
            System.out.println(t);
        }
    }

    public TutorRepository getRepository() {
        return tutorRepository;
    }
}