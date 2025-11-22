package repository;

import model.Tutor;

public class TutorRepository extends GenericRepository<Tutor> {

    private static TutorRepository INSTANCIA;

    private TutorRepository() {}

    public static TutorRepository getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new TutorRepository();
        }
        return INSTANCIA;
    }

    @Override
    public Tutor buscarPorId(int id) {
        for (Tutor tutor : data) {
            if (tutor.getId() == id) {
                return tutor;
            }
        }
        return null;
    }
}