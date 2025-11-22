package model;

import java.time.LocalDateTime;

import enums.StatusAgendamento;

public class Agendamento {

    private static int contador = 1;

    private int id;
    private Pet pet;
    private Tutor tutor;
    private Servico servico;
    private LocalDateTime horario;
    private StatusAgendamento status;
    private double precoFinal;

    public Agendamento(Pet pet, Tutor tutor, Servico servico, LocalDateTime horario) {
        this.id = contador++;
        this.pet = pet;
        this.tutor = tutor;
        this.servico = servico;
        this.horario = horario;
        this.status = StatusAgendamento.AGENDADO;
    }

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

    public void concluir() {
        this.status = StatusAgendamento.CONCLUIDO;
    }

    public void cancelar() {
        this.status = StatusAgendamento.CANCELADO;
    }

    public void exibirAgendamento() {

    }

    public void setStatus(StatusAgendamento statusAgendamento) {
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal){
        this.precoFinal = precoFinal;
    }
}
