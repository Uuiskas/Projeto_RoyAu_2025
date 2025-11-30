package model;

import interfaces.Remuneravel;
import exceptions.RegraNegocioException;

public class Veterinario extends Pessoa implements Remuneravel {

    public static int contador = 1;

    private final int id;
    private final String crmv;
    private String especialidade; // Atributo específico
    private double salarioBruto;

    public Veterinario(String nome, String telefone, String crmv, String especialidade, double salarioBruto) {
        super(nome, telefone); // Chama o construtor do Pai (Pessoa)

        this.id = contador++;
        this.crmv = crmv;
        this.especialidade = especialidade;
        this.salarioBruto = salarioBruto;
    }

    // Implementação obrigatória da Abstração de Pessoa
    @Override
    public void identificar() {

        System.out.println("Veterinário(a) ID: " + this.id + " | Nome: " + this.nome + " | CRMV: " + this.crmv);
    }

    @Override
    public double calcularSalarioLiquido() {

        if (salarioBruto <= 0) {
            throw new RegraNegocioException("Salário bruto deve ser positivo para cálculo líquido.");
        }
        return salarioBruto * 0.85;
    }

    @Override
    public void aplicarBonus(double percentual) {
        if (percentual < 0) return;
        double bonus = this.salarioBruto * (percentual / 100.0);
        this.salarioBruto += bonus;
    }

    // --- Getters Faltantes ---

    public int getId() { return id; }
    public String getCrmv() { return crmv; }
    public String getEspecialidade() { return especialidade; }
    public double getSalarioBruto() { return salarioBruto; }

    // Método ToString
    @Override
    public String toString() {
        return "Veterinario ID: " + id +
                " | Nome: " + getNome() +
                " | CRMV: " + crmv +
                " | Especialidade: " + especialidade;
    }
}