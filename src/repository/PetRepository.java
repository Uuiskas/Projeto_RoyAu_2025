package repository;

import model.Pet;

public class PetRepository extends GenericRepository<Pet> {

    private static PetRepository INSTANCIA;

    private PetRepository() {}

    public static PetRepository getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new PetRepository();
        }
        return INSTANCIA;
    }

    @Override
    public Pet buscarPorId(int id) {
        for (Pet p : data) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}