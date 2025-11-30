package model;

import java.time.LocalDateTime;

import enums.StatusAgendamento;

public class Agendamento {

    private static int contador = 1;

    private final int id; // Usar final é boa prática para IDs
    private final Pet pet;
    private final Tutor tutor;
    private final Servico servico;
    private final LocalDateTime horario;
    private StatusAgendamento status;
    private double precoFinal; // Novo atributo para guardar o custo final

    public Agendamento(Pet pet, Tutor tutor, Servico servico, LocalDateTime horario) {
        this.id = contador++;
        this.pet = pet;
        this.tutor = tutor;
        this.servico = servico;
        this.horario = horario;
        this.status = StatusAgendamento.AGENDADO;

        // Inicializaremos com 0.0, pois o cálculo real acontece no Service.
        this.precoFinal = 0.0;
    }

    // --- Getters e Seters ---

    public int getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Servico getServico() {
        return servico;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    // Método de exibição
    public void exibirAgendamento() {
        System.out.printf("ID: %d | Horário: %s | Status: %s\n", id, horario.toLocalTime(), status);
        System.out.printf("  Pet: %s (Tutor: %s) | Custo Final: R$ %.2f\n",
                pet.getNome(), tutor.getNome(), precoFinal);
    }

    public void setStatus(StatusAgendamento statusAgendamento) {
        this.status = statusAgendamento;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal){
        this.precoFinal = precoFinal;
    }
}