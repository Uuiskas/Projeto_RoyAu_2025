package model;

import java.util.Objects;

public class Tutor extends Pessoa { // Herda de Pessoa

    public static int contador = 1;

    private final int id;
    private final String cpf;
    private String endereco;
    private int pontos;
    public Tutor(String nome, String telefone, String cpf, String endereco) {
        super(nome, telefone);
        this.id = contador++;
        this.cpf = cpf;
        this.endereco = endereco;
        this.pontos = 0;
    }

    // método abstrato da Superclasse Pessoa
    @Override
    public void identificar() {
        System.out.println("Tutor(a) da Royal Pet: " + this.nome + " | ID: " + this.id);
    }

    // --- Getters e Setters específicos do Tutor ---

    public int getId() { return id; }
    public String getCpf() { return cpf; }
    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    // Fidelidade ---

    public int getPontos() { return pontos; }

    // Método privado/interno (opcional, mas o Service usa adicionarPontos)
    public void setPontos(int pontos) { this.pontos = pontos; }

    public void adicionarPontos(int quantidade) {
        if (quantidade > 0) {
            this.pontos += quantidade;
        }
    }

    // --- Métodos de Comparação e Exibição ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return id == tutor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        // Usa o getter herdado 'getNome()' para acessar 'nome'
        return "Tutor ID: " + id +
                " | Nome: " + getNome() +
                " | CPF: " + cpf +
                " | Telefone: " + getTelefone() +
                " | Endereço: " + endereco +
                " | Pontos: " + pontos;
    }
}
