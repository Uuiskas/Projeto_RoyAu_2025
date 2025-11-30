package controller;

import model.Tutor;
import service.TutorService;
import exceptions.RegraNegocioException;

public class TutorController {

    private final TutorService tutorService;

    public TutorController() {
        this.tutorService = TutorService.getInstancia();
    }

    // 1. Método ajustado para reportar o ID
    public void cadastrarTutor(Tutor tutor) throws RegraNegocioException {
        tutorService.cadastrarTutor(tutor);
        System.out.println("Tutor " + tutor.getNome() + " cadastrado com sucesso!");
        // Imprime o ID gerado automaticamente para o usuário
        System.out.println(" ID do Tutor: " + tutor.getId());
    }

    public Tutor buscarPorId(int id) throws RegraNegocioException {
        return tutorService.buscarPorId(id);
    }

    // 2. Método de listagem
    public void listarTutores() {
        System.out.println("\n--- LISTAGEM COMPLETA DE TUTORES ---");
        for (Tutor t : tutorService.getRepository().listar()) {
            System.out.println("ID: " + t.getId() +
                    " | Nome: " + t.getNome() +
                    " | Telefone: " + t.getTelefone());
        }
        System.out.println("------------------------------------");
    }

    // 3. NOVO metodo: para o Case 2
    /** Lista  ID e o Nome, facilitando a escolha ao cadastrar Pet. */
    public void listarIDsETutores() {
        System.out.println("\n--- TUTORES DISPONÍVEIS (Selecione o ID) ---");
        boolean encontrado = false;
        for (Tutor t : tutorService.getRepository().listar()) {
            System.out.println("  ID: " + t.getId() + " | Nome: " + t.getNome());
            encontrado = true;
        }
        if (!encontrado) {
            System.out.println("Nenhum tutor cadastrado. Cadastre um primeiro (Opção 1).");
        }
        System.out.println("------------------------------------------");
    }

    public TutorService getService() {
        return tutorService;
    }
}