package model;

import exceptions.RegraNegocioException;

public class Microchipagem extends Servico {

    // Taxa de serviço governamental obrigatória (Exemplo)
    private static final double TAXA_GOVERNO = 0.05;

    public Microchipagem(String nome, double valorBase, String data) {
        super(nome, valorBase, data);
    }

    @Override
    public double calcularCustoTotal(Pet pet) {
        // Cálculo Otimizado: Valor Base * (1 + Taxa)
        double custo = this.valorBase * (1 + TAXA_GOVERNO);

        if (custo <= 0) {
            throw new RegraNegocioException("O valor do serviço Microchipagem deve ser positivo.");
        }
        return custo;
    }
}