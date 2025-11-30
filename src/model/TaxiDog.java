package model;

import exceptions.RegraNegocioException;

public class TaxiDog extends Servico {


    private static final double CUSTO_POR_KM = 2.50;

    // Distância que será percorrida neste serviço específico
    private final double distanciaKm;

    // Construtor: (Nome, ValorBase, Data, Distância)
    public TaxiDog(String nome, double valorBase, String data, double distanciaKm) {
        super(nome, valorBase, data);

        if (distanciaKm < 0) {
            throw new RegraNegocioException("A distância do Taxi Dog não pode ser negativa.");
        }
        this.distanciaKm = distanciaKm;
    }

    // Polimorfismo: Regra de Custo Base + (Distância * Custo/Km)
    @Override
    public double calcularCustoTotal(Pet pet) {
        // A assinatura exige Pet, mas o cálculo aqui depende da distância fixa do serviço

        double custoTotal = this.valorBase + (this.distanciaKm * CUSTO_POR_KM);

        return custoTotal;
    }

    @Override
    public String toString() {
        return super.toString() + " | Distância: " + distanciaKm + "km";
    }
}