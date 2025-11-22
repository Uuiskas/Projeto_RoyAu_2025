package service;

import exceptions.RegraNegocioException;
import model.Tutor;
import repository.TutorRepository;

public class TutorService {

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

    // -----------------------------------------------------
    // Cadastrar Tutor
    // -----------------------------------------------------
    public void cadastrarTutor(Tutor tutor) {
        if (tutor == null || tutor.getNome() == null || tutor.getTelefone() == null) {
            throw new RegraNegocioException("Dados do tutor incompletos.");
        }
        tutorRepository.salvar(tutor);
    }

    // -----------------------------------------------------
    // Buscar Tutor por ID
    // -----------------------------------------------------
    public Tutor buscarPorId(int id) {
        Tutor t = tutorRepository.buscarPorId(id);
        if (t == null) {
            throw new RegraNegocioException("Tutor não encontrado.");
        }
        return t;
    }

    // -----------------------------------------------------
    // RF002 – Adicionar pontos de fidelidade
    // -----------------------------------------------------
    public void adicionarPontos(Tutor tutor, int pontos) {
        if (tutor == null) throw new RegraNegocioException("Tutor inválido.");
        tutor.setPontos(tutor.getPontos() + pontos);
    }

    // -----------------------------------------------------
    // Listagem
    // -----------------------------------------------------
    public void listarTutores() {
        for (Tutor t : tutorRepository.listar()) {
            System.out.println(t);
        }
    }

    public TutorRepository getRepository() {
        return tutorRepository;
    }
}
