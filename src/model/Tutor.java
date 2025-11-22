package model;

public class Tutor {

    public static int contador = 1;

    private int id;
    private String nome;
    private String telefone;
    private int pontos;  // pontos de fidelidade

    public Tutor(String nome, String telefone) {
        this.id = contador++;
        this.nome = nome;
        this.telefone = telefone;
        this.pontos = 0; // inicia com 0 pontos
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void adicionarPontos(int quantidade) {
        this.pontos += quantidade;
    }

    @Override
    public String toString() {
        return "Tutor ID: " + id +
                " | Nome: " + nome +
                " | Telefone: " + telefone +
                " | Pontos: " + pontos;
    }
}
