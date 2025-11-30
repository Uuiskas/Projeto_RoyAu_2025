package model;
public abstract class Pessoa {

    // Atributos base. 'protected' para Herança.
    protected String nome;
    protected String telefone;

    // Construtor
    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public abstract void identificar();

    public void exibirDadosBase() {
        System.out.println("Nome: " + nome + ", Telefone: " + telefone);
    }

    // Geters
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }

    // Setters
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}