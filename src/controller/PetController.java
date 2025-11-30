package controller;

import exceptions.RegraNegocioException;
import model.Pet;
import service.PetService;

public class PetController {

    private final PetService petService;

    public PetController() {
        this.petService = PetService.getInstancia();
    }

    public void cadastrarPet(Pet pet) throws RegraNegocioException {
        petService.cadastrarPet(pet);
        System.out.println(" Pet cadastrado com sucesso!");
    }

    public Pet buscarPorId(int id) throws RegraNegocioException {
        return petService.buscarPorId(id);
    }

    public void listarPets() {
        for (Pet p : petService.getRepository().listar()) {
            System.out.println(p);
        }
    }

    /** * Método adicionado para listagem ids do tutor epet
     * Usado no Case 4 da Main.
     */
    public void listarIDsEPets() {
        System.out.println("\n--- PETS CADASTRADOS ---");
        boolean encontrado = false;

        // Acessa o Repositório através do Service
        for (Pet p : petService.getRepository().listar()) {

            System.out.println("  ID: " + p.getId() +
                    " | Nome: " + p.getNome() +
                    " (Tutor ID: " + p.getTutor().getId() + ")");
            encontrado = true;
        }

        if (!encontrado) {
            System.out.println("Nenhum pet cadastrado. Cadastre um primeiro (Opção 2).");
        }
        System.out.println("------------------------------------");
    }

    public PetService getService() {
        return petService;
    }
}