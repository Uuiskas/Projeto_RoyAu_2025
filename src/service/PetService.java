package service;

import exceptions.RegraNegocioException;
import model.Agendamento;
import model.Pet;
import repository.PetRepository;

public class PetService {

    private static PetService INSTANCIA;

    private final PetRepository petRepository;

    private PetService() {
        this.petRepository = PetRepository.getInstancia();
    }

    public static PetService getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new PetService();
        }
        return INSTANCIA;
    }

    // -----------------------------------------------------
    // Cadastrar Pet
    // -----------------------------------------------------
    public void cadastrarPet(Pet pet) {
        if (pet == null || pet.getNome() == null || pet.getTutor() == null) {
            throw new RegraNegocioException("Dados do pet incompletos.");
        }
        petRepository.salvar(pet);
    }

    // -----------------------------------------------------
    // Buscar por ID
    // -----------------------------------------------------
    public Pet buscarPorId(int id) {
        Pet pet = petRepository.buscarPorId(id);
        if (pet == null) {
            throw new RegraNegocioException("Pet não encontrado.");
        }
        return pet;
    }

    // -----------------------------------------------------
    // RF005 – Registrar histórico do pet
    // -----------------------------------------------------
    public void registrarHistorico(Agendamento agendamento) {
        Pet pet = agendamento.getPet();
        pet.getHistorico().add(agendamento);
    }

    // -----------------------------------------------------
    // Listagem simples
    // -----------------------------------------------------
    public void listarPets() {
        for (Pet p : petRepository.listar()) {
            System.out.println(p);
        }
    }

    public PetRepository getRepository() {
        return petRepository;
    }
}
