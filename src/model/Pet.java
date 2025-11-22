package model;

import java.util.ArrayList;
import java.util.List;

import enums.Comportamento;
import enums.Porte;
import enums.TipoPelo;

public class Pet {
    private static int contador = 1;

    private int id;
    private String nome;
    private String raca;
    private int idade;


    private Porte porte;
    private Comportamento comportamento;
    private TipoPelo tipoPelo;

    private Tutor tutor;

    private List<Agendamento> historico;

    public Pet(String nome, String raca, int idade, Porte porte, Comportamento comportamento, TipoPelo tipoPelo, Tutor tutor){
        this.id = contador++;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.porte = porte;
        this.comportamento = comportamento;
        this.tipoPelo = tipoPelo;
        this.tutor = tutor;
        this.historico = new ArrayList<>();
    }

    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getRaca(){
        return raca;
    }
    public int getIdade(){
        return idade;
    }
    public Porte getporte(){
        return porte;
    }
    public Comportamento getComportamento(){
        return comportamento;
    }
    public TipoPelo getTipoPelo(){
        return tipoPelo;
    }
    public Tutor getTutor(){
        return tutor;
    }
    public List<Agendamento> getHistorico() {
        return historico;
    }
    public void adicionarHistorico(Agendamento agendamento) {
        historico.add(agendamento);
    }


}
