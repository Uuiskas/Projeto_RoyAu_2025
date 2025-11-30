package model;

import enums.Comportamento;
import enums.Porte;
import exceptions.RegraNegocioException;

public class Banho extends Servico {



    //  Armazenamos o porte no construtor para fins de inicialização do Service
    private final Porte porteAnimal;



    public Banho(String nome, double valorBase, String data, Porte porteAnimal) {
        super(nome, valorBase, data);
        this.porteAnimal = porteAnimal;
    }

    // Método 'calcularCustoTotal' ajustado para usar o Pet (CORRETO)
    @Override
    public double calcularCustoTotal(Pet pet) {
        double custo = this.valorBase;

        // Custo por Porte
        if (pet.getPorte() == Porte.MEDIO){
            custo +=10.00;
        } else if(pet.getPorte() == Porte.GRANDE){
            custo +=20.00;
        }

        // Custo por Comportamento

        if(pet.getComportamento() == Comportamento.AGITADO){
            custo += 5.00;
        } else if(pet.getComportamento() == Comportamento.AGRESSIVO){
            custo +=15.00;
        }


        if (custo <= 0) {
            throw new RegraNegocioException("O valor final do Banho deve ser positivo.");
        }

        return custo;
    }
}