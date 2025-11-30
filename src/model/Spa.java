package model;

import exceptions.RegraNegocioException;

public class Spa extends Servico {


    private static final double CUSTO_POR_HORA = 30.00;
    private final int duracaoHoras; // Atributo específico para o SPA

    public Spa(String nome, double valorBase, String data, int duracaoHoras) {
        super(nome, valorBase, data);
        if (duracaoHoras <= 0) {
            throw new RegraNegocioException("A duração do SPA deve ser maior que zero.");
        }
        this.duracaoHoras = duracaoHoras;
    }


    @Override
    public double calcularCustoTotal(Pet pet) {
        double custo = this.valorBase + (duracaoHoras * CUSTO_POR_HORA);

        return custo;
    }
}