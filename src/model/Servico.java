package model;

import exceptions.RegraNegocioException; // Necessário para validação

public abstract class Servico {

    private static int contador = 1;

    // 1. Usar 'final' no ID: Garante que o ID não mude após a criação
    protected final int id;

    // 2. Usar o tipo 'String' e 'double' simples para consistência com o Java
    protected String nome;
    protected double valorBase; // Renomeado de precoBase para valorBase (consistência com a Main e outros Servicos)

    // O construtor DEVE incluir a data para o agendamento (consistência com a Main)
    protected String data;

    public Servico(String nome, double valorBase, String data) {

        // 3. Validação de Negócio: Garante que o objeto nasça válido
        if (valorBase <= 0) {
            throw new RegraNegocioException("O valor base do serviço deve ser positivo.");
        }

        this.id = contador++;
        this.nome = nome;
        this.valorBase = valorBase;
        this.data = data; // Atribui a data
    }

    // 4. Renomear o método abstrato para 'calcularCustoTotal' (consistência)
    // O método deve receber o Pet ou usar os atributos herdados, mas Pet é mais flexível.
    public abstract double calcularCustoTotal(Pet pet);


    // --- Getters ---
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValorBase() { // Renomeado para getValorBase()
        return valorBase;
    }

    public String getData() {
        return data;
    }


    @Override
    public String toString() {
        return this.id + " - " + this.nome + " (R$ " + String.format("%.2f", this.valorBase) + ")";
    }
}